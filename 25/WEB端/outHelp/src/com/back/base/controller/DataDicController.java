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
import com.back.base.model.DataDic;
import com.back.base.model.DicType;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.base.service.DataDicService;
import com.back.base.service.DicTypeService;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;

@Controller
@RequestMapping("/back/dataDic")
public class DataDicController extends BaseController {
	@Autowired(required = true)
	private DataDicService dataDicService;
	@Autowired(required = true)
	private DicTypeService dicTypeService;

	/**
	 * 新增更新操作
	 * 
	 * @param customer
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=saveOrUpdate")
	public String saveOrUpdate(DataDic dataDic, ModelMap model) {
		if (!StringUtils.hasText(dataDic.getId())) {
			dataDic.setId(UUID.randomUUID().toString());
			dataDic.setCreateTime(DateUtil.Time2String(new Date()));
			dataDic.setCreateId(getLoginSession().getId());
			dataDic.setStatus("00");// 状态 00： 启用
			dataDic.setUpdateFlag(false);
		} else {
			dataDic.setUpdateFlag(true);
			dataDic.setUpdateTime(DateUtil.Time2String(new Date()));
			dataDic.setUpdateId(getLoginSession().getId());
		}
		try {
			dataDicService.saveOrUpdate(dataDic);
			return "redirect:/back/dataDic.do?cmd=list";
		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}

	}

	/**
	 * 列表
	 * 
	 * @param customer
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=list")
	public String list(DataDic dataDic, ModelMap model,HttpServletRequest request) {
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(true);// 修改分页状态 是否分页
		List<DataDic> list = dataDicService.list(dataDic);
		model.put("list", list);
		model.put("dataDic", dataDic);
		model.put("page", page);
		return "backpage/base/datadic/datadic/list";
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=addInput")
	public String addInput(ModelMap model) {
		List<DicType> dicTypeList = dicTypeService.selectAll(new DicType());// 字段类型列表
		model.put("dicTypeList", dicTypeList);
		return "backpage/base/datadic/datadic/edit";
	}

	/**
	 * 更新页面
	 * 
	 * @param pkId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=updateInput")
	public String updateInput(String id, ModelMap model) {
		DataDic dataDic = dataDicService.find(id);
		List<DicType> dicTypeList = dicTypeService.selectAll(new DicType());// 字段类型列表
		for (DicType dicType : dicTypeList) {
			if (dataDic.getTypeId().equals(dicType.getPkId())) {
				dicType.setChecked(true);
			}
		}
		model.put("dicTypeList", dicTypeList);
		model.put("dataDic", dataDicService.find(id));
		return "backpage/base/datadic/datadic/edit";
	}

	/**
	 * 查看页面
	 * 
	 * @param pkId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=find")
	public String find(String id, ModelMap model) {
		model.put("dataDic", dataDicService.find(id));
		return "backpage/base/datadic/datadic/view";
	}

	/**
	 * 删除
	 * 
	 * @param pkId
	 * @return
	 */
	@RequestMapping(params = "cmd=delete")
	public String delete(String id, ModelMap model) {
		try {
			dataDicService.delete(id);
			return "redirect:/back/dataDic.do?cmd=list";
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
