package com.jsc.hotspot.api.service;

import com.jsc.hotspot.api.dto.PermissionVO;
import com.jsc.hotspot.db.domain.Permission;

import java.util.List;
import java.util.Set;

/**
 * @author huixing
 * @description
 * @date 2020/2/24
 */
public interface PermissionService {
    /**
     * 根据roleIDs查询roleName
     * @param roleIds
     * @return
     */
    Set<String> queryByRoleIds(Long[] roleIds);
    /**
     * 根据roleID查询roleName
     * @param roleId
     * @return
     */
    Set<String> queryByRoleId(Long roleId);

    /**
     * 检查权限
     * @param roleId
     * @return
     */
    boolean checkSuperPermission(Long roleId);

    /**
     * 根据roleID删除记录
     * @param roleId
     */
    void deleteByRoleId(Long roleId);

    /**
     * 增加权限
     * @param Permission
     */
    void add(Permission Permission);

    /**
     * 分页查询权限
     * @return
     */
    List<PermissionVO> queryPermissionByPage();
}
