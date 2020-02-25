package com.back.phone.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.base.AbstractEntity;
import com.back.base.controller.BaseController;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.phone.model.TfCommon;
import com.back.phone.modelNew.TfCommonNew;
import com.back.phone.service.TfCommonService;

@Controller
public class TfCommonController extends BaseController {

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

	@Autowired(required = true)
	private TfCommonService tfCommonService;

	/**
	 * 
	 * 常用地址列表
	 * 
	 */
	@RequestMapping(value = "/phone/tfCommon_list")
	@ResponseBody
	public TfCommonNew tfCommonList(TfCommon tfCommon, ModelMap model, HttpServletRequest request,
			HttpSession session) {
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页

		String id = request.getParameter("id");

		tfCommon.setUserId(id);

		List<TfCommon> tfCommons = tfCommonService.queryTempList(tfCommon);

		TfCommonNew tcn = new TfCommonNew();

		tcn.setTfCommonList(tfCommons);

		return tcn;
	}

	/**
	 * 
	 * 常用地址-详情
	 * 
	 */
	@RequestMapping(value = "/phone/tfCommon_view")
	@ResponseBody
	public TfCommonNew tfCommonView(TfCommon tfCommon, ModelMap model, HttpServletRequest request,
			HttpSession session) {
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页

		String cid = request.getParameter("cid");

		tfCommon = tfCommonService.selectByPrimaryKey(cid);

		TfCommonNew tcn = new TfCommonNew();

		tcn.setTfCommon(tfCommon);

		return tcn;
	}

	/**
	 * 
	 * 常用地址-新增
	 * 
	 */
	@RequestMapping(value = "/phone/tfCommon_add")
	@ResponseBody
	public String tfCommonAdd(TfCommon tfCommon, ModelMap model, HttpServletRequest request, HttpSession session) {
		String r = "f";

		String id = request.getParameter("id");
		
		tfCommon.setUserId(id);
		
		try {
			
			tfCommonService.insert(tfCommon);

			r = "t";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r = "f";
			return r;
		}


		return r;
	}

}
