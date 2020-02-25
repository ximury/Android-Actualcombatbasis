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
import com.back.base.model.DicType;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.base.service.DicTypeService;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;

@Controller
@RequestMapping("back/dictype")
public class DicTypeController extends BaseController{
	@Autowired(required = true)
	private DicTypeService dicTypeService;

	/**
	 * 新增更新操作
	 * @param customer
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=saveOrUpdate")
	public String saveOrUpdate(DicType dicType,ModelMap model) {
		if (!StringUtils.hasText(dicType.getPkId())) {
			dicType.setPkId(UUID.randomUUID().toString());
			dicType.setCreateTime(DateUtil.Time2String(new Date()));
			dicType.setCreateId(getLoginSession().getId());
			dicType.setStatus("00");// 状态 00： 启用
			dicType.setUpdateFlag(false);
		} else {
			dicType.setUpdateFlag(true);
			dicType.setUpdateTime(DateUtil.Time2String(new Date()));
			dicType.setUpdateId(getLoginSession().getId());
		}
		try {
			dicTypeService.saveOrUpdate(dicType);
			return "redirect:/back/dictype.do?cmd=list";
		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}
	}
	
	/**
	 * 列表
	 * @param customer
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=list")
	public String list(DicType dicType,ModelMap model,HttpServletRequest request) {
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(true);// 修改分页状态 是否分页
		List<DicType> list = dicTypeService.list(dicType);
		model.put("list", list);
		model.put("dicType", dicType);
		model.put("page", page);
		return "backpage/base/datadic/dictype/list";		
	}
	
	
	/**
	 * 新增页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=addInput")
	public String addInput() {	
		return "backpage/base/datadic/dictype/edit";
	}
	
	/**
	 * 更新页面
	 * 
	 * @param pkId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=updateInput")
	public String updateInput(String pkId, ModelMap model) {
		model.put("updateFlag", "isUpdate");
		model.put("dicType", dicTypeService.find(pkId));
		return "backpage/base/datadic/dictype/edit";
	}
	
	
	/**
	 * 查看页面
	 * 
	 * @param pkId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=find")
	public String find(String pkId, ModelMap model) {
		model.put("dicType", dicTypeService.find(pkId));
		return "backpage/base/datadic/dictype/view";
	}	
	
	
	/**
	 * 删除
	 * 
	 * @param pkId
	 * @return
	 */
	@RequestMapping(params = "cmd=delete")
	public String delete(String pkId,ModelMap model) {
		try {
			dicTypeService.delete(pkId);
			return "redirect:/back/dictype.do?cmd=list";
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
	public String getOperateButton(List<TResource> re, String[] params) {
		// TODO Auto-generated method stub
		return null;
	}	
}
