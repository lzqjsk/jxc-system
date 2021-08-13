package com.tang.admin.controller;


import com.tang.admin.exceptions.ParamsException;
import com.tang.admin.model.RespBean;
import com.tang.admin.pojo.User;
import com.tang.admin.query.UserQuery;
import com.tang.admin.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 小开心
 * @since 2021-07-31
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    // @RequestMapping("login")
    // @ResponseBody
    // public RespBean login(String userName, String password, HttpSession session){
    //
    //     try {
    //         User user = userService.login(userName,password);
    //         session.setAttribute("user",user);
    //         return RespBean.success("用户登陆成功");
    //     } catch (ParamsException e) {
    //         e.printStackTrace();
    //         return RespBean.error(e.getMsg());
    //     } catch (Exception e){
    //         e.printStackTrace();
    //         return RespBean.error("用户登陆失败!");
    //     }
    // }
    // @RequestMapping("login")
    // @ResponseBody
    // public RespBean login(String userName, String password, HttpSession session){
    //
    //         User user = userService.login(userName,password);
    //         session.setAttribute("user",user);
    //         return RespBean.success("用户登陆成功");
    //
    // }

    /**
     * 用户信息设置页面
     * @return
     */
    @RequestMapping("setting")
    public String setting(Principal principal, Model model){
        User user = userService.findUserByUserName(principal.getName());
        model.addAttribute("user",user);
        return "user/setting";
    }

    /**
     * 用户信息更新
     * @param user
     * @return
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public RespBean updateUserInfo(User user){
        try {
            userService.updateUserInfo(user);
            return RespBean.success("用户信息更新成功");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("用户信息更新失败!");
        }
    }

    /**
     * 用户密码更新页
     * @return
     */
    @RequestMapping("password")
    public String password(){
        return  "user/password";
    }

    /**
     * 用户密码更新
     * @param principal
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @RequestMapping("updateUserPassword")
    @ResponseBody
    public RespBean updateUserPassword(Principal principal, String oldPassword, String newPassword, String confirmPassword){

            userService.updateUserPassword(principal.getName(),oldPassword,newPassword,confirmPassword);
            return RespBean.success("用户密码更新成功");
    }

    /**
     * 用户管理主页
     * @return
     */
    @RequestMapping("/index")
    @PreAuthorize("hasAnyAuthority('1010')")
    public String index(){
        return "user/user";
    }

    /**
     * 用户列表查旋接口
     * @param userQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('101003')")
    public Map<String,Object> userList(UserQuery userQuery){
        return userService.userList(userQuery);
    }

    /**
     * 添加｜更新用户页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdatePage(Integer id,Model model){
        if(id!=null){
            model.addAttribute("user", userService.getById(id));
        }

        return "user/add_update";
    }

    /**
     * 用户记录添加接口
     * @param user
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveUser(User user){
        userService.saveUser(user);
        return RespBean.success("用户记录添加成功！");
    }

    /**
     * 用户记录更新接口
     * @param user
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean update(User user){
        userService.updateUser(user);
        return RespBean.success("用户记录更新成功成功！");
    }

    /**
     * 用户记录删除接口
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteUser(Integer[] ids){
        userService.deleteUser(ids);
        return RespBean.success("用户记录删除成功");
    }



}
