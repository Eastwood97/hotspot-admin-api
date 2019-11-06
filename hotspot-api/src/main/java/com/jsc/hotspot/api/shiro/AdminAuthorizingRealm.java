package com.jsc.hotspot.api.shiro;

import com.jsc.hotspot.api.service.AdminService;
import com.jsc.hotspot.common.utils.bcrypt.BCryptPasswordEncoder;
import com.jsc.hotspot.db.domain.Admin;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

public class AdminAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;



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
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
