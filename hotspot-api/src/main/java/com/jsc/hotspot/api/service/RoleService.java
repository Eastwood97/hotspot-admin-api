package com.jsc.hotspot.api.service;

import com.jsc.hotspot.api.dto.RolePermissionDTO;
import com.jsc.hotspot.api.vo.RoleVO;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.db.domain.Role;
import com.jsc.hotspot.db.domain.RolePermission;

import java.util.List;
import java.util.Set;

/**
 * @author huixing
 * @description
 * @date 2020/2/24
 */
public interface RoleService {
    /**
     * 根据角色ID查询相关路由信息
     * @param roleIds
     * @return
     */
    Set<String> queryByIds(Long[] roleIds);

    /**
     * 根據角色名分頁排序查詢
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    List<Role> querySelective(String name, Integer page, Integer limit, String sort, String order);

    /**
     * 通过ID查找角色
     * @param id
     * @return
     */
    Role findById(Long id);

    /**
     * 新增角色
     * @param role
     */
    void add(Role role);

    /**
     * 根据ID删除角色
     * @param id
     */
    void deleteById(Long id);

    /**
     * 更新角色信息
     * @param role
     */
    void updateById(Role role);

    /**
     * 检查角色是否存在
     * @param name
     * @return
     */
    boolean checkExist(String name);

    /**
     * 查找所有角色
     * @return
     */
    List<Role> queryAll();

    /**
     * 分页查找角色以及每个角色的权限
     * @param page
     * @param num
     * @return
     */
    List<RolePermission> queryByPage(int page, int num);

    /**
     * 增加角色和权限 —— 加上事务
     * @param rolePermissionDTO
     * @return
     */
    BizResult<String> addRolePermission(RolePermissionDTO rolePermissionDTO);

    /**
     * 根據角色ID刪除角色以及權限 —— 加上事務
     * @param id
     * @return
     */
    BizResult<Boolean> deleteRolePermissionsById(Integer id);

    /**
     * 更新角色以及對應權限信息 ——加上事務
     * @param rolePermissionDTO
     * @return
     */
    BizResult<Boolean> updateRolePermissionById(RolePermissionDTO rolePermissionDTO);
}
