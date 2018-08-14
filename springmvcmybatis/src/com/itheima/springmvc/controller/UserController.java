package com.itheima.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 用户登录验证
 */

@Controller
public class UserController {

    //如果登录不进去,返回login页面
    @RequestMapping("/user/showlogin")
    public String showLogin(){
        return "login";
    }

    @RequestMapping("/user/login")
    public String userLogin(String username,
                            String password,
                            HttpSession session){

        //判断用户名密码是否正确
        System.out.println(username);
        System.out.println(password);
        //如果正确,想session中写入用户信息
        session.setAttribute("username",username);
        //返回登录成功,或者跳转到商品列表
        return "redirect:/itemList.action";

    }

}
