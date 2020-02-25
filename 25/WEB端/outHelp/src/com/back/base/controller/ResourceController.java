package com.back.base.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.back.base.AbstractEntity;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.base.service.ResourceService;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;


@Controller
@RequestMapping("/back/resource")
public class ResourceController extends BaseController{
	@Autowired(required = true)
	private ResourceService resourceService;
	
	
	
	/**
	 * 获取列表（可以带查询条件）
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=list")
	public String list(TResource resource, ModelMap model,HttpServletRequest request) {
		
		PageContext page = PageContext.getContext(request,rowPerPage);//获得分页标签
		
		page.setPagination(true);//修改分页状态  是否分页
		
		List<TResource> list = resourceService.list(resource);
		model.put("list", list);
		model.put("resource", resource);
		model.put("page", page);
		return "backpage/base/resource/list";
	}
	
	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=main")
	public String main() {
		return "backpage/base/resource/main";
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
		model.put("resource", resourceService.find(id));
		return "admin/resource/resourceDetail";
	}
	
	

	/**
	 * 新增/修改
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(params = "cmd=saveOrUpdate")
	public String saveOrUpdate(TResource resource,ModelMap model) {
		if (!StringUtils.hasText(resource.getId())) {
			resource.setId(UUID.randomUUID().toString());
			resource.setCreatetime(DateUtil.Time2String(new Date()));
			resource.setUpdateFlag(false);
		} else {
			resource.setUpdateFlag(true);
			resource.setUpdatetime(DateUtil.Time2String(new Date()));
		}		
		try {
			resourceService.saveOrUpdate(resource);
			return "redirect:/back/resource.do?cmd=list&menuid="+resource.getMenuid();
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
		model.put("resource", resourceService.find(id));
		return "backpage/base/resource/edit";
	}
	
	
	/**
	 * 新增页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=addInput")
	public String addInput() {
		return "backpage/base/resource/edit";
	}	
	
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "cmd=delete")
	public String delete(String id,ModelMap model) {
		try {
			String menuId = resourceService.find(id).getMenuid();
			resourceService.delete(id);
			return "redirect:/back/resource.do?cmd=list&menuid="+menuId;			
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
