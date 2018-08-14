package com.itheima.springmvc.controller;

import com.itheima.springmvc.po.Items;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;

/**
 *  商品列表
 */

@Controller
public class ItemController {

    //通过访问某一个路径,找到这个方法
    //建立映射关系
    //方法是handler,类是controller
    //不同的handler,找不同的handler适配器
    @RequestMapping("/itemList.action")
    public ModelAndView itemList(){

        //查询商品列表
        ArrayList<Object> itemList = new ArrayList<>();
        itemList.add(new Items(1,"小米游戏本",6999,new Date(),"性价比不错不错"));

        //把商品列表传给jsp
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemList", itemList);

        //设置展示数据的jsp
        //modelAndView.setViewName("/WEB-INF/jsp/itemList.jsp");
        //配置视图解析器后,这两个不能同时使用
        modelAndView.setViewName("itemList");

        return modelAndView;
    }

}
