package com.tang.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author xiaokaixin
 * @Date 2021/7/31 09:46
 * @Version 1.0
 */
@Controller
public class MainController {

    /**
     * 系统登陆页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    /**
     * 系统主页面
     * @return
     */
    @RequestMapping("main")
    public String  main(){
        return "main";
    }

    /**
     * 系统欢迎页面
     * @return
     */
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }



}
