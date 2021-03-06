package com.tang.admin.mapper;

import com.tang.admin.pojo.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author 小开心
 * @since 2021-08-04
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Integer> queryRoleHasAllMenus(Integer roleId);

    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
