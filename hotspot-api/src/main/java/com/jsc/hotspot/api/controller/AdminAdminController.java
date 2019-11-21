package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.AdminService;
import com.jsc.hotspot.common.util.bcrypt.BCryptPasswordEncoder;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.Admin;
import com.jsc.hotspot.db.utils.RegexUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jsc.hotspot.common.utils.response.AdminResponseCode.*;

/**
 * @Auther: WW
 * @Date: 2019/11/18 0018 15:44
 * @Description:
 */
@RestController
@RequestMapping("/admin/admin")
@Validated
public class AdminAdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/list")
    public Object list(String username,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        List<Admin> adminList = adminService.querySelective(username, page, limit);
        Subject currentUser = SecurityUtils.getSubject();
        Admin admin = (Admin) currentUser.getPrincipal();
        if(admin.getUsername().equalsIgnoreCase("admin123")){
            return ResponseUtil.okList(adminList);
        }
        Map map = new HashMap();
        map.put("type",false);
        return ResponseUtil.ok(map);
    }

    @PostMapping("/create")
    public Object create(@RequestBody Admin admin) {
        Object error = validate(admin);
        if (error != null) {
            return error;
        }
        String username = admin.getUsername();
        List<Admin> adminList = adminService.findAdmin(username);
        if (adminList.size() > 0) {
            return ResponseUtil.fail(ADMIN_NAME_EXIST, "管理员已经存在");
        }
        String rawPassword = admin.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);
        admin.setPassword(encodedPassword);
        adminService.add(admin);
        return ResponseUtil.ok(admin);
    }

    private Object validate(Admin admin) {
        String name = admin.getUsername();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isUsername(name)) {
            return ResponseUtil.fail(ADMIN_INVALID_NAME, "管理员名称不符合规定");
        }
        String password = admin.getPassword();
        if (StringUtils.isEmpty(password) || password.length() < 6) {
            return ResponseUtil.fail(ADMIN_INVALID_PASSWORD, "管理员密码长度不能小于6");
        }
        return null;
    }

    @PostMapping("/update")
    public Object update(@RequestBody Admin admin) {
        Object error = validate(admin);
        if (error != null) {
            return error;
        }
        Integer anotherAdminId = Math.toIntExact(admin.getId());
        if (anotherAdminId == null) {
            return ResponseUtil.badArgument();
        }
        // 不允许管理员通过编辑接口修改密码
        admin.setPassword(null);
        if (adminService.updateById(admin) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(admin);
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody Admin admin) {
        Integer anotherAdminId = Math.toIntExact(admin.getId());
        if (anotherAdminId == null) {
            return ResponseUtil.badArgument();
        }
        // 管理员不能删除自身账号
        Subject currentUser = SecurityUtils.getSubject();
        Admin currentAdmin = (Admin) currentUser.getPrincipal();
        if (currentAdmin.getId().equals(anotherAdminId)) {
            return ResponseUtil.fail(ADMIN_DELETE_NOT_ALLOWED, "管理员不能删除自己账号");
        }
        adminService.deleteById(admin.getId());
        return ResponseUtil.ok();
    }
}