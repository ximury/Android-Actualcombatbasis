package com.back.base.pageModel;

import java.util.List;
import java.util.Map;

/**
 * session信息模型
 * 
 * @author dragon
 * 
 */
public class SessionInfo {

	private Login login;

	private Map resourceMap;// 用户可以访问的资源地址列表


	public Map getResourceMap() {
		return resourceMap;
	}

	public void setResourceMap(Map resourceMap) {
		this.resourceMap = resourceMap;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}



}
