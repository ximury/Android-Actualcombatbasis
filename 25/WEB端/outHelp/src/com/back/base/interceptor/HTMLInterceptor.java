package com.back.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HTMLInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//System.out.println(URLEncoder.encode(request.getRequestURI(), "ISO-8859-1"));
		request.setAttribute("ctx", request.getContextPath());
		return true;// 如果返回false，则不再调用之后的方法
	}

}
