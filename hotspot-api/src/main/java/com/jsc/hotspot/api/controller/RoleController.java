package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.dto.PermissionVO;
import com.jsc.hotspot.api.dto.RolePermissionDTO;
import com.jsc.hotspot.api.service.PermissionService;
import com.jsc.hotspot.api.service.impl.RoleServiceImpl;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author huixing
 * @description 角色控制层
 * @date 2020/2/24
 */
@RequestMapping("admin/role/routes")
@RestController
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(BigScreenController.class);

    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 分页查询所有的角色
     * @return
     */
    @GetMapping("/roles")
    public Object queryAllRoles(){
        try {
            List<Role> roles = roleService.queryAll();
            return ResponseUtil.ok(roles);
        }catch (Exception e){
            logger.error("com.jsc.hotspot.api.controller.RoleController.queryAllRoles: " + e.getMessage());
            return ResponseUtil.fail();
        }
    }

    /**
     * 根据roleID查询当前角色的所有路由信息
     * @param id
     * @return
     */
    @GetMapping("/current")
    public Object queryCurrentRolePermission(Integer id){
        try {
            Set<String> roles = permissionService.queryByRoleId(id.longValue());
            return ResponseUtil.ok(roles);
        }catch (Exception e)
        {
            logger.error("com.jsc.hotspot.api.controller.RoleController.queryCurrentRolePermission: " + e.getMessage());
            return ResponseUtil.fail();
        }
    }

    /**
     * 查询所有的路由表信息
     * @return
     */
    @GetMapping("/routes")
    public Object queryAllPermissions(){
        try {
            List<PermissionVO> permissionVOList = permissionService.queryPermissionByPage();
            return ResponseUtil.ok(permissionVOList);
        }catch (Exception e)
        {
            logger.error("com.jsc.hotspot.api.controller.RoleController.queryAllPermissions(): " + e.getMessage());
            return ResponseUtil.fail();
        }
    }

    /**
     * 根据IDS删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Object deleteRolesById(@PathVariable(name = "id") Integer id){
        try {
            return ResponseUtil.ok(roleService.deleteRolePermissionsById(id));
        }catch (Exception e)
        {
            logger.error("com.jsc.hotspot.api.controller.RoleController.deleteRolesById: " + e.getMessage());
            return ResponseUtil.fail();
        }
    }

    /**
     * 分页查询所有的权限
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/role/permission")
    public Object queryAllPermissions(int page, int size){
        try {
            return ResponseUtil.ok(roleService.queryByPage(page*size,size));
        }catch (Exception e)
        {
            logger.error("com.jsc.hotspot.api.controller.RoleController.queryAllPermissions(int, int):" + e.getMessage());
            return ResponseUtil.fail();
        }
    }

    /**
     * 新增角色以及其對應的權限
     * @param rolePermissionDTO
     * @return
     */
    @PostMapping("/role")
    public Object addRolePermission(@RequestBody RolePermissionDTO rolePermissionDTO) {
        try {
            return ResponseUtil.ok(roleService.addRolePermission(rolePermissionDTO));
        }catch (Exception e)
        {
            logger.error("com.jsc.hotspot.api.controller.RoleController.addRolePermission: " + e.getMessage());
            return ResponseUtil.fail();
        }
    }

    /**
     * 更新權限信息
     * @param
     * @return
     */
    @PostMapping("/update")
    public Object updateRolePermission(@RequestBody RolePermissionDTO roleInfo){
        try {
            return ResponseUtil.ok(roleService.updateRolePermissionById(roleInfo));
        }catch (Exception e)
        {
            logger.error("com.jsc.hotspot.api.controller.RoleController.updateRolePermission: " + e.getMessage());
            return ResponseUtil.fail();
        }
    }
}
