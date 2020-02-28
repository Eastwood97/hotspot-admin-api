package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.Permission;
import com.jsc.hotspot.db.domain.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huixing
 * @description
 * @date 2020/2/24
 */
public interface RoleExtMapper {

    /**
     * 分頁查詢
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<RolePermission> queryRolePermissionbyPage(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    /**
     * 批量插入
     * @param permissionList
     */
    void insertBatch(List<Permission> permissionList);

    /**
     * 查找最大值
     * @return
     */
    long selectMaxId();
}
