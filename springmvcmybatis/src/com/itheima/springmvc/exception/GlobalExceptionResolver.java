package com.itheima.springmvc.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 2. 编写全局异常处理器
 *  class GlobalExceptionResolver implements HandlerExceptionResolver
 * 3. 编写error错误界面提示的信息,进行页面显示
 * 4. 在springmvc中配置全局异常处理器
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception e) {
        //判断异常的种类,instanceof是判断对象类型
        String msg = null;
        if (e instanceof CustomerException) {
            CustomerException customerException = (CustomerException) e;
            msg = customerException.getExpMessage();
        } else {

        //如果是自定义异常,取错误消息
        //如果是运行时候异常,取错误的堆栈信息
            e.printStackTrace();//往网页打错误消息
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            msg = stringWriter.toString();
        }

        //编写日志,发短信,发邮件
        //....

        //返回友好错误页面,把错误消息显示
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", msg);
        modelAndView.setViewName("error");

        return modelAndView;
    }
}
