package com.back.base.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.base.AbstractEntity;
import com.back.base.model.TMenu;
import com.back.base.model.TResource;
import com.back.base.pageModel.Login;
import com.back.base.service.MenuService;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;

@Controller
@RequestMapping("/back/menu")
public class MenuController extends BaseController{

	@Autowired(required=true)
	private MenuService menuService;

	@RequestMapping(params = "cmd=tree")
	public @ResponseBody
	List<TMenu> tree(HttpSession session) {
		String loginId = "";
		Login login = (Login)session.getAttribute("login");
		if(StringUtils.hasText(login.getUsername()) && "admin".equals(login.getUsername())){//超级管理员
			loginId = "admin";
		}else{
			loginId = login.getId();
		}
		List<TMenu> list = menuService.tree(loginId,login.getPersonid());
		return list;
	}
	
	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=main")
	public String main() {
		return "backpage/base/menu/main";
	}		

	/**
	 * 查询对象
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=find")
	public String find(String id, ModelMap model) {
		model.put("menu", menuService.find(id));
		return "backpage/base/menu/view";
	}

	/**
	 * 新增/修改
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(params = "cmd=saveOrUpdate")
	public String saveOrUpdate(TMenu menu,ModelMap model) {
		if (!StringUtils.hasText(menu.getId())) {
			menu.setId(UUID.randomUUID().toString());
			menu.setCreatetime(DateUtil.Time2String(new Date()));
			menu.setUpdateFlag(false);
		} else {
			menu.setUpdateFlag(true);
			menu.setUpdatetime(DateUtil.Time2String(new Date()));
		}		
		try {
			menuService.saveOrUpdate(menu);
			if (menu.isUpdateFlag()) {
				return "redirect:/back/menu.do?cmd=find&id="+menu.getId()+"&flag=update";//修改
			} else {
				return "redirect:/back/menu.do?cmd=find&id="+menu.getId()+"&flag=add";//新增
			}
		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}
	}

	/**
	 * 更新页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=updateInput")
	public String updateInput(String id, ModelMap model) {
		model.put("menu", menuService.find(id));
		return "backpage/base/menu/edit";
	}
	
	
	/**
	 * 新增页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=addInput")
	public String addInput() {
		return "backpage/base/menu/edit";
	}	
	
	
	
	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "cmd=delete")
	public String delete(String id,ModelMap model) {
		try {
			menuService.delete(id);
			model.put("msg", "删除成功！");
			model.put("flag", "remove");
			return IConstant.SUCCESS_PAGE;
		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}
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
