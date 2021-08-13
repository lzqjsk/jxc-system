package com.tang.admin.controller;


import com.tang.admin.model.RespBean;
import com.tang.admin.pojo.Role;
import com.tang.admin.pojo.User;
import com.tang.admin.query.RoleQuery;
import com.tang.admin.service.IRoleService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author 小开心
 * @since 2021-08-03
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;


    @RequestMapping("index")
    public String index(){
        return "role/role";
    }

    /**
     * 角色列表查询
     * @param roleQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> roleList(RoleQuery roleQuery){
        return roleService.roleList(roleQuery);
    }

    /**
     * 添加｜更新角色页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateRolePage")
    public String addOrUpdatePage(Integer id, Model model){
        if(id!=null){
            model.addAttribute("role", roleService.getById(id));
        }

        return "role/add_update";
    }



    /**
     * 角色记录添加接口
     * @param role
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveRole(Role role){
        roleService.saveRole(role);
        return RespBean.success("角色记录添加成功！");
    }

    /**
     * 角色记录更新接口
     * @param role
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean update(Role role){
        roleService.updateUser(role);
        return RespBean.success("角色记录更新成功！");
    }


    /**
     * 角色记录删除接口
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteRole(Integer id){
        roleService.deleteRole(id);
        return RespBean.success("角色记录删除成功");
    }

    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return roleService.queryAllRoles(userId);
    }

    /**
     * 权限添加页面
     * @param roleId
     * @param model
     * @return
     */
    @RequestMapping("toAddGrantPage")
    public String toAddGrantPage(Integer roleId,Model model){
        model.addAttribute("roleId",roleId);
        return "role/grant";
    }

    /**
     * 角色授权接口
     * @param roleId
     * @param mids
     * @return
     */

    @RequestMapping("addGrant")
    @ResponseBody
    public RespBean addGrant(Integer roleId,Integer[] mids){
        roleService.addGrant(roleId,mids);
        return RespBean.success("角色记录授权成功成功");
    }
}
