package com.jsc.hotspot.api.shiro;

import com.jsc.hotspot.api.service.impl.AdminService;
import com.jsc.hotspot.api.service.impl.PermissionService;
import com.jsc.hotspot.api.service.impl.RoleService;
import com.jsc.hotspot.common.utils.bcrypt.BCryptPasswordEncoder;
import com.jsc.hotspot.db.domain.Admin;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

public class AdminAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;


    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken= (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        String password =new String(upToken.getPassword());

        if (StringUtils.isEmpty(username)){
            throw new AccountException("用户名不能为空");
        }
        if(StringUtils.isEmpty(password)){
            throw new AccountException("密码不能为空");
        }
        List<Admin> adminList=adminService.findAdmin(username);
        Assert.state(adminList.size()<2,"同一个用户名存在两个账户");
        if(adminList.size()==0){
            throw  new UnknownAccountException("找不到用户（"+username+")的账号信息");
        }
        Admin admin=adminList.get(0);

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        if (!encoder.matches(password,admin.getPassword())){
            throw new UnknownAccountException("找不到用户（"+username+")的账号信息");
        }
        return new SimpleAuthenticationInfo(admin , password,getName());
    }

    //鉴权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        Admin admin = (Admin) getAvailablePrincipal(principals);
        Long[] roleIds = admin.getRoleIds();
        Set<String> roles = roleService.queryByIds(roleIds);
        Set<String> permissions = permissionService.queryByRoleIds(roleIds);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }
}
