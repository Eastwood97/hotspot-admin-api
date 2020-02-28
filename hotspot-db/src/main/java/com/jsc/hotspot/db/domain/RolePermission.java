package com.jsc.hotspot.db.domain;

import lombok.Data;

import java.util.List;

/**
 * @author huixing
 * @description
 * @date 2020/2/24
 */
@Data
public class RolePermission {
    private Integer roleId;
    private String roleName;
    private List<Permission> permissions;
}
