package com.back.phone.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.base.AbstractEntity;
import com.back.base.controller.BaseController;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.base.utils.DateTime;
import com.back.phone.modelNew.CountNew;
import com.back.phone.modelNew.CountNo1New;
import com.back.phone.modelNew.CountNo2New;
import com.back.phone.modelNew.CountNo3New;
import com.back.phone.service.CountService;
import com.back.phone.service.TfBusinessListService;
import com.back.phone.service.TfBusinessReportService;
import com.back.phone.service.TfCustomerService;

@Controller
public class CountController extends BaseController {

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
	private CountService countService;

	/**
	 * 
	 * 业务分析---首页面
	 * 
	 */
	@RequestMapping(value = "/phone/count_on")
	@ResponseBody
	public CountNew CountOn(HttpServletRequest request, HttpSession session) {
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页

		CountNew cn = new CountNew();

		String id = request.getParameter("id");
		
		String did = request.getParameter("did");

		String serviceDate = DateTime.getCurDate_yyyy_MM_dd();
		
		String yearMonth = serviceDate.substring(0, 7);

		if (id != null && id.length() > 0 && did != null && did.length() > 0 && yearMonth != null && yearMonth.length() > 0 ) {

			cn = countService.selectNum(id,did,yearMonth);
		}

		return cn;
	}
	
	/**
	 * 
	 * 业务分析---客户分析图
	 * 
	 */
	@RequestMapping(value = "/phone/count_No1")
	@ResponseBody
	public CountNo1New CountNo1(HttpServletRequest request, HttpSession session) {
		
		String id = request.getParameter("id");
		
		String did = request.getParameter("did");

		String yearMonth = request.getParameter("yearMonth");
		
		CountNo1New countNo1New = new CountNo1New();
		
		if (id != null && id.length() > 0 && did != null && did.length() > 0 && yearMonth != null && yearMonth.length() > 0 ) {

			countNo1New = countService.selectNumNo(id,did,yearMonth);
		}
		
		return countNo1New;
	}
	/**
	 * 
	 * 业务分析---订单分析图
	 * 
	 */
	@RequestMapping(value = "/phone/count_No2")
	@ResponseBody
	public CountNo2New CountNo2(HttpServletRequest request, HttpSession session) {
		
		String id = request.getParameter("id");
		
		String did = request.getParameter("did");

		String yearMonth = request.getParameter("yearMonth");
		
		CountNo2New countNo2New = new CountNo2New();
		
		if (id != null && id.length() > 0 && did != null && did.length() > 0 && yearMonth != null && yearMonth.length() > 0 ) {

			countNo2New = countService.selectNumNo2(id,did,yearMonth);
		}
		
		return countNo2New;
	}
	/**
	 * 
	 * 业务分析---琅琊榜
	 * 
	 */
	@RequestMapping(value = "/phone/count_No3")
	@ResponseBody
	public CountNo3New CountNo3(HttpServletRequest request, HttpSession session) {
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页
		
		String id = request.getParameter("id");
		
		String did = request.getParameter("did");

		String yearMonth = request.getParameter("yearMonth");
		
		CountNo3New countNo3New = new CountNo3New();
		
		if (id != null && id.length() > 0 && did != null && did.length() > 0 && yearMonth != null && yearMonth.length() > 0 ) {

			countNo3New = countService.selectNumNo3(id,did,yearMonth);
		}
		
		
		
		return countNo3New;
	}

}
