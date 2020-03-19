package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.LogService;
import com.jsc.hotspot.api.service.RoleService;
import com.jsc.hotspot.api.service.impl.AdminService;
import com.jsc.hotspot.api.service.PermissionService;
import com.jsc.hotspot.api.utils.Permission;
import com.jsc.hotspot.api.utils.PermissionUtil;
import com.jsc.hotspot.common.bean.Response;
import com.jsc.hotspot.common.utils.JacksonUtil;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.Admin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.jsc.hotspot.common.utils.response.AdminResponseCode.ADMIN_INVALID_ACCOUNT;

/**
 * @author tzm
 * @desc 用户登录认证
 */
@RestController
@RequestMapping("/admin/auth")
@Validated
public class AdminAuthController {
    private final Log logger = LogFactory.getLog(AdminAuthController.class);

    @Autowired
    private AdminService adminService;

    @GetMapping("/1")
    public String test() {
        return "123";
    }

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
//    @Autowired
//    private LogHelper logHelper;

    /**
     * 登录
     * @param body { username : value, password : value }
     * @param request
     * @return
     */
    @LogService(value="用户登录")
    @PostMapping("/login")
    public Object login(@RequestBody String body, HttpServletRequest request) {
        logger.debug("正在登陆");
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseUtil.badArgument();
        }

        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new UsernamePasswordToken(username, password));
        } catch (UnknownAccountException uae) {
            logger.error("用户帐号或密码不正确");
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "用户帐号或密码不正确");
        } catch (LockedAccountException lae) {
            logger.error("用户帐号已锁定不可用");
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "用户帐号已锁定不可用");

        } catch (AuthenticationException ae) {
            logger.error("认证失败");
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "认证失败");
        }

        currentUser = SecurityUtils.getSubject();
        Admin admin = (Admin) currentUser.getPrincipal();
//        admin.setLastLoginIp(IpUtil.getIpAddr(request));
//        admin.setLastLoginTime(LocalDateTime.now());
        adminService.updateById(admin);
        System.out.println(currentUser.isAuthenticated());
        logger.info("登录");

        // userInfo
        Map<String, Object> adminInfo = new HashMap<String, Object>();
        adminInfo.put("nickName", admin.getUsername());
//        adminInfo.put("avatar", admin.getAvatar());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", currentUser.getSession().getId());
        result.put("adminInfo", adminInfo);
        return ResponseUtil.ok(result);
    }

    /**
     * 登出
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/logout")
    public Object logout() {
        Subject currentUser = SecurityUtils.getSubject();

        logger.info("退出");
        currentUser.logout();
        return ResponseUtil.ok();
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    public Object infos() {
        Subject currentUser = SecurityUtils.getSubject();
        Admin admin = (Admin) currentUser.getPrincipal();

        Map<String, Object> data = new HashMap<>();
        // 未登录
        if (admin == null){
            return ResponseUtil.fail();
        }
        data.put("name", admin.getUsername());
        data.put("avatar", admin.getAvatar());
        Long[] roleIds = admin.getRoleIds();
//        roleIds[0] = 1l;
        Set<String> roles = roleService.queryByIds(roleIds);
        Set<String> permissions = permissionService.queryByRoleIds(roleIds);
        data.put("roles", roles);
        // NOTE
        // 这里需要转换perms结构，因为对于前端而已API形式的权限更容易理解
        data.put("perms", permissions);
        // data.put("perms", toApi(permissions));
        return ResponseUtil.ok(data);
    }

    @Autowired
    private ApplicationContext context;
    private HashMap<String, String> systemPermissionsMap = null;

    private Collection<String> toApi(Set<String> permissions) {
        if (systemPermissionsMap == null) {
            systemPermissionsMap = new HashMap<>();
            final String basicPackage = "org.linlinjava.litemall.admin";
            List<Permission> systemPermissions = PermissionUtil.listPermission(context, basicPackage);
            for (Permission permission : systemPermissions) {
                String perm = permission.getRequiresPermissions().value()[0];
                String api = permission.getApi();
                systemPermissionsMap.put(perm, api);
            }
        }

        Collection<String> apis = new HashSet<>();
        for (String perm : permissions) {
            String api = systemPermissionsMap.get(perm);
            apis.add(api);

            if (perm.equals("*")) {
                apis.clear();
                apis.add("*");
                return apis;
                //                return systemPermissionsMap.values();

            }
        }
        return apis;
    }



    @GetMapping("/401")
    public Object page401() {
        return ResponseUtil.unlogin();
    }

    @GetMapping("/index")
    public Object pageIndex() {
        return ResponseUtil.ok();
    }

    @GetMapping("/403")
    public Object page403() {
        return ResponseUtil.unauthz();
    }
}
