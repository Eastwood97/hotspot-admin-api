package com.jsc.hotspot.api.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.dto.RolePermissionDTO;
import com.jsc.hotspot.api.service.RoleService;
import com.jsc.hotspot.api.vo.RoleVO;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.db.dao.PermissionMapper;
import com.jsc.hotspot.db.dao.RoleMapper;
import com.jsc.hotspot.db.dao.ext.RoleExtMapper;
import com.jsc.hotspot.db.domain.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleExtMapper roleExtMapper;
    @Resource
    private PermissionMapper permissionMapper;


    public Set<String> queryByIds(Long[] roleIds) {
        Set<String> roles = new HashSet<String>();
        if(roleIds.length == 0){
            return roles;
        }

        RoleExample example = new RoleExample();
        example.or().andIdIn(Arrays.asList(roleIds)).andEnabledEqualTo(true).andDeletedEqualTo(false);
        List<Role> roleList = roleMapper.selectByExample(example);

        for(Role role : roleList){
            roles.add(role.getName());
        }

        return roles;

    }

    public List<Role> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return roleMapper.selectByExample(example);
    }

    public Role findById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public void add(Role role) {
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insertSelective(role);
    }

    public void deleteById(Long id) {
        roleMapper.logicalDeleteByPrimaryKey(id);
    }

    public void updateById(Role role) {
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    public boolean checkExist(String name) {
        RoleExample example = new RoleExample();
        example.or().andNameEqualTo(name).andDeletedEqualTo(false);
        return roleMapper.countByExample(example) != 0;
    }

    public List<Role> queryAll() {
        RoleExample example = new RoleExample();
        example.or().andDeletedEqualTo(false);
        return roleMapper.selectByExample(example);
    }

    public List<RolePermission> queryByPage(int page, int num) {
        RoleExample example = new RoleExample();
        example.or().andDeletedEqualTo(false);
        PageHelper.startPage(page, num);
        List<Role> roleList = roleMapper.selectByExample(example);
        List<RolePermission> rolePermissions = roleExtMapper.queryRolePermissionbyPage(page, num);
        return rolePermissions;
    }

    @Override
    @Transactional
    public BizResult<String> addRolePermission(RolePermissionDTO rolePermissionDTO) {
        BizResult<String> bizResult = new BizResult<>();
        Role role = new Role();
        role.setCreateTime(LocalDateTime.now());
        role.setEnabled(true);
        role.setDesc(rolePermissionDTO.getDesc());
        role.setName(rolePermissionDTO.getRoleName());
        role.setUpdateTime(LocalDateTime.now());
        role.setDeleted(false);
        int i = roleMapper.insertSelective(role);
        if (i <= 0){
            bizResult.setDesc("插入角色失败");
            bizResult.setFlag(false);
        }else {
            long maxId = roleExtMapper.selectMaxId();
            List<Permission> permissionList = new ArrayList<>();
            rolePermissionDTO.getPermissions().forEach(permissionName-> {
                Permission permission = new Permission();
                permission.setCreateTime(LocalDateTime.now());
                permission.setPermission(permissionName);
                permission.setUpdateTime(LocalDateTime.now());
                permission.setRoleId(maxId);
                permissionList.add(permission);
                roleExtMapper.insertBatch(permissionList);
            });
        }
        return bizResult;
    }

    @Transactional
    @Override
    public BizResult<Boolean> deleteRolePermissionsById(Integer id) {
        int i = roleMapper.deleteByPrimaryKey(id.longValue());
        BizResult<Boolean> bizResult = new BizResult<>();
        bizResult.setFlag(false);
        bizResult.setData(false);
        if (i > 0)
        {
            PermissionExample permissionExample = new PermissionExample();
            PermissionExample.Criteria criteria = permissionExample.createCriteria();
            criteria.andRoleIdEqualTo(id.longValue());
            int j = permissionMapper.deleteByExample(permissionExample);
            if (j > 0){
                return BizResult.create(true);
            }
        }
        return bizResult;
    }

    @Override
    public BizResult<Boolean> updateRolePermissionById(RolePermissionDTO rolePermissionDTO) {
        BizResult<Boolean> bizResult = new BizResult<>();
        bizResult.setData(false);
        bizResult.setFlag(false);
        Role role = new Role();
        role.setDesc(rolePermissionDTO.getDesc());
        role.setName(rolePermissionDTO.getRoleName());
        role.setUpdateTime(LocalDateTime.now());
        role.setId(rolePermissionDTO.getRoleId().longValue());
        int i = roleMapper.updateByPrimaryKeySelective(role);
//        if (i > 0){
            PermissionExample permissionExample = new PermissionExample();
            PermissionExample.Criteria criteria = permissionExample.createCriteria();
            criteria.andRoleIdEqualTo(rolePermissionDTO.getRoleId().longValue());
            permissionMapper.deleteByExample(permissionExample);

            List<Permission> permissionList = new ArrayList<>();
            rolePermissionDTO.getPermissions().forEach(permissionName -> {
                Permission permission = new Permission();
                permission.setCreateTime(LocalDateTime.now());
                permission.setPermission(permissionName);
                permission.setUpdateTime(LocalDateTime.now());
                permission.setRoleId(rolePermissionDTO.getRoleId().longValue());
                permissionList.add(permission);
                roleExtMapper.insertBatch(permissionList);
            });
            return BizResult.create(true);
//        }
//        return bizResult;
    }
}
