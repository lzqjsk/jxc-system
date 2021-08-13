package com.tang.admin.service;

import java.util.List;

/**
 * @Author xiaokaixin
 * @Date 2021/8/4 09:31
 * @Version 1.0
 */
public interface IRbacService {
    /**
     * 根据登录用户名查询分配的角色
     * @param userName
     * @return
     */
    List<String> findRolesByUserName(String userName);

    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
