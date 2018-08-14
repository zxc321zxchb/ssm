package com.itheima.springmvc.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * preHandle方法:
	 * controller执行前调用此方法
	 * 返回true表示继续执行，返回false中止执行并拦截
	 * 这里可以加入登录校验、权限拦截等
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub

		//获取访问路径,判断是否包含 login 字符串
		String uri = request.getRequestURL().toString();
		if (uri.contains("login")) {
			return true;
		}
		//拦截用户请求,判断用户是否登录
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username");

		if (username != null) {
			//如果用户已经登录,放行
			return true;
		}
		//如果用户未登录,跳转到login
		response.sendRedirect(request.getContextPath() + "/user/showlogin");

		return false;
	}



	/**
     * postHandle方法:
	 * controller执行后但未返回视图前调用此方法
	 * 这里可在返回用户前对模型数据进行加工处理，比如这里加入公用信息以便页面显示
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}




	/** 
		afterCompletion方法:
	 * controller执行后且视图返回后调用此方法
	 * 这里可得到执行controller时的异常信息
	 * 这里可记录操作日志，资源清理等
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
