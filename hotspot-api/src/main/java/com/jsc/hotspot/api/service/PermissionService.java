package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.dao.PermissionMapper;
import com.jsc.hotspot.db.domain.Permission;
import com.jsc.hotspot.db.domain.PermissionExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    public Set<String> queryByRoleIds(Long[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds.length == 0){
            return permissions;
        }

        PermissionExample example = new PermissionExample();
        example.or().andRoleIdIn(Arrays.asList(roleIds)).andDeletedEqualTo(false);
        List<Permission> permissionList = permissionMapper.selectByExample(example);

        for(Permission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }


    public Set<String> queryByRoleId(Long roleId) {
        Set<String> permissions = new HashSet<String>();
        if(roleId == null){
            return permissions;
        }

        PermissionExample example = new PermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        List<Permission> permissionList = permissionMapper.selectByExample(example);

        for(Permission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public boolean checkSuperPermission(Long roleId) {
        if(roleId == null){
            return false;
        }

        PermissionExample example = new PermissionExample();
        example.or().andRoleIdEqualTo(roleId).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return permissionMapper.countByExample(example) != 0;
    }

    public void deleteByRoleId(Long roleId) {
        PermissionExample example = new PermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        permissionMapper.logicalDeleteByExample(example);
    }

    public void add(Permission Permission) {
        Permission.setCreateTime(LocalDateTime.now());
        Permission.setUpdateTime(LocalDateTime.now());
        permissionMapper.insertSelective(Permission);
    }
}
