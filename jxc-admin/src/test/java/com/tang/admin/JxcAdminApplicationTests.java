package com.tang.admin;

import com.tang.admin.config.security.JxcLogoutSuccessHandler;
import com.tang.admin.filters.CaptchaCodeFilter;
import com.tang.admin.service.IRbacService;
import com.tang.admin.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class JxcAdminApplicationTests {

    @Resource
    private IUserService userService;

    @Resource
    private JxcLogoutSuccessHandler jxcLogoutSuccessHandler;

    @Resource
    private CaptchaCodeFilter captchaCodeFilter;

    @Resource
    private DataSource dataSource;

    @Resource
    private IRbacService rbacService;
    @Test
    void contextLoads() {
        List<String> list = new ArrayList<>();
        list.add("管理员");
        List<String> authoritiesByRoleName = rbacService.findAuthoritiesByRoleName(list);
        System.out.println(authoritiesByRoleName);
    }

}
