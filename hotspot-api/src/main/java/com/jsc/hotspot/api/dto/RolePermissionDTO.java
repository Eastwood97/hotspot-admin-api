package com.jsc.hotspot.api.dto;

import com.jsc.hotspot.api.utils.Permission;
import lombok.Data;

import java.util.List;

/**
 * @author huixing
 * @description
 * @date 2020/2/25
 */
@Data
public class RolePermissionDTO {
    private Integer roleId;
    private String roleName;
    private String desc;
    private List<String> permissions;
}
