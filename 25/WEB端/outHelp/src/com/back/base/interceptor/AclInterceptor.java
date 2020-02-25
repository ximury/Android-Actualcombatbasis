package com.back.base.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.back.base.dao.TResourceMapper;
import com.back.base.model.TResource;
import com.back.base.pageModel.Login;
import com.back.base.service.AclService;

public class AclInterceptor extends HandlerInterceptorAdapter {

	Logger logger = Logger.getLogger(AclInterceptor.class);

	@Autowired(required = true)
	private AclService aclService;

	@Autowired(required = true)
	private TResourceMapper resourceMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath() + "?cmd=" + request.getParameter("cmd");
		TResource resource = new TResource();
		resource.setPath(path);
		List<TResource> resources = resourceMapper.select(resource);
		if (null != resources && resources.size() > 0) {
			Login login = (Login) request.getSession().getAttribute("login");
			if (aclService.havePermit(login.getRoleId(), resources.get(0).getId())) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		throw new RuntimeException("您无权执行本操作，请联系系统管理员！");
	}

}
