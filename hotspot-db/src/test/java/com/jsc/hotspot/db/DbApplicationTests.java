package com.jsc.hotspot.db;

import com.jsc.hotspot.db.dao.ext.RoleExtMapper;
import com.jsc.hotspot.db.domain.RolePermission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DbApplicationTests {

    @Resource
    private RoleExtMapper roleExtMapper;

    @Test
    void contextLoads() {
        List<RolePermission> rolePermissions = roleExtMapper.queryRolePermissionbyPage(0,2);
        rolePermissions.forEach(x -> {
            System.out.println(x.getRoleName());
        });
    }

}
