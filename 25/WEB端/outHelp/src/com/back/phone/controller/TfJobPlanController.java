package com.back.phone.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.back.base.utils.DateTime;
import com.back.phone.model.TfJobPlan;
import com.back.phone.modelNew.TfJobPlanNew;
import com.back.phone.service.TfJobPlanService;

@Controller
public class TfJobPlanController extends BaseController {

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
	private TfJobPlanService tfJobPlanService;

	/**
	 * 
	 * 工作计划列表
	 * 
	 */
	@RequestMapping(value = "/phone/tfJobPlan_list")
	@ResponseBody
	public TfJobPlanNew tfJobPlanList(TfJobPlan tfJobPlan, ModelMap model, HttpServletRequest request,
			HttpSession session) {
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页

		String id = request.getParameter("id");

		tfJobPlan.setUserId(id);

		List<TfJobPlan> tfJobPlans = tfJobPlanService.queryTempList(tfJobPlan);

		TfJobPlanNew tjp = new TfJobPlanNew();

		tjp.setTfJobPlanList(tfJobPlans);

		return tjp;
	}

	/**
	 * 
	 * 工作计划详情
	 * 
	 */
	@RequestMapping(value = "/phone/tfJobPlan_view")
	@ResponseBody
	public TfJobPlanNew tfJobPlanView(TfJobPlan tfJobPlan, ModelMap model, HttpServletRequest request,
			HttpSession session) {

		String pid = request.getParameter("pid");

		tfJobPlan = tfJobPlanService.selectByPrimaryKey(pid);

		TfJobPlanNew tjp = new TfJobPlanNew();

		tjp.setTfJobPlan(tfJobPlan);

		return tjp;
	}

	/**
	 * 
	 * 工作计划详情
	 * 
	 */
	@RequestMapping(value = "/phone/tfJobPlan_add")
	@ResponseBody
	public String tfJobPlanAdd(TfJobPlan tfJobPlan, ModelMap model, HttpServletRequest request, HttpSession session) {

		String r = "f";

		String id = request.getParameter("id");

		tfJobPlan.setUserId(id);
		
		String serviceDate = DateTime.getCurDate_yyyy_MM_dd();

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

		String serviceTime = formatter.format(new Date());
		
		tfJobPlan.setJpData(serviceDate);
		
		tfJobPlan.setJpTime(serviceTime);

		try {
			
			tfJobPlanService.insert(tfJobPlan);

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
