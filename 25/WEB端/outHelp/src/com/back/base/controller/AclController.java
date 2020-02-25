package com.back.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.base.AbstractEntity;
import com.back.base.model.TAcl;
import com.back.base.model.TMenu;
import com.back.base.model.TResource;
import com.back.base.service.AclService;

@Controller
@RequestMapping("/back/acl")
public class AclController extends BaseController{
	@Autowired(required = true)
	private AclService aclService;

	/**
	 * 授权操作
	 * @param tacl
	 * @return
	 */
	@RequestMapping(params = "cmd=save")
	public @ResponseBody String save(TAcl tacl) {
		int count = aclService.save(tacl);
		if (count > 0) {
			return "success";
		} else {
			return "error";
		}
	}

	/**
	 * 查询权限菜单
	 * @param tacl
	 * @return
	 */
	@RequestMapping(params = "cmd=authorizationMenu")
	public @ResponseBody
	List<TMenu> authorizationMenu(TAcl tacl) {
		tacl.setResourcetype("menu");
		List<TMenu> list = aclService.authorizationMenu(tacl);
		return list;
	}
	
	
	/**
	 * 查询权限资源
	 * @param tacl
	 * @return
	 */
	@RequestMapping(params = "cmd=authorizationResource")
	public @ResponseBody
	List<TResource> authorizationResource(TAcl tacl) {
		tacl.setResourcetype("resource");
		List<TResource> list = aclService.authorizationResource(tacl);
		return list;
	}

	@Override
	public String getOperateColumn(List<TResource> re, AbstractEntity ae) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOperateButton(List<TResource> re,String[] params) {
		// TODO Auto-generated method stub
		return null;
	}
}
