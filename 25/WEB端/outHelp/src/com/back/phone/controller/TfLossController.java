package com.back.phone.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import com.back.phone.model.TfLoss;
import com.back.phone.model.TfLossDeal;
import com.back.phone.modelNew.TfLossNew;
import com.back.phone.service.TfLossDealService;
import com.back.phone.service.TfLossService;

@Controller
public class TfLossController extends BaseController {

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
	private TfLossService tfLossService;
	@Autowired(required = true)
	private TfLossDealService tfLossDealService;

	/**
	 * 
	 * 损耗列表
	 * 
	 */
	@RequestMapping(value = "/phone/tfLoss_list")
	@ResponseBody
	public TfLossNew tfLossList(TfLoss tfLoss, ModelMap model, HttpServletRequest request, HttpSession session) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页

		String id = request.getParameter("id");

		tfLoss.setUserId(id);

		String yearMonth = request.getParameter("yearMonth");

		tfLoss.setLossSpare2(yearMonth);

		List<TfLoss> tfLosss = tfLossService.queryTempList(tfLoss);

		TfLossNew tln = new TfLossNew();

		tln.setTfLossList(tfLosss);

		return tln;
	}

	/**
	 * 
	 * 损耗明细
	 * 
	 */
	@RequestMapping(value = "/phone/tfLoss_view")
	@ResponseBody
	public TfLossNew tfLossView(TfLoss tfLoss, ModelMap model, HttpServletRequest request, HttpSession session) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页

		String lossid = request.getParameter("lossid");

		tfLoss = tfLossService.selectByPrimaryKey(lossid);

		TfLossNew tln = new TfLossNew();

		if (tfLoss != null) {

			TfLossDeal tfLossDeal = new TfLossDeal();

			tfLossDeal.setLossId(tfLoss.getLossId());

			List<TfLossDeal> tfLossDeals = tfLossDealService.queryTempList(tfLossDeal);

			tln.setTfLossDeal(tfLossDeals);

			tln.setTfLoss(tfLoss);
		}

		return tln;
	}

	/**
	 * 
	 * 损耗添加
	 * 
	 */
	@RequestMapping(value = "/phone/tfLoss_add")
	@ResponseBody
	public String tfLossAdd(TfLossDeal tfLossDeal, ModelMap model, HttpServletRequest request, HttpSession session) {

		String r = "f";

		DecimalFormat df = new DecimalFormat("######0.00");// 保存显示用保留二位

		String lossid = request.getParameter("lid");

		String ldSpare11 = request.getParameter("ldSpare11");
		String ldSpare12 = request.getParameter("ldSpare12");
		String ldSpare13 = request.getParameter("ldSpare13");

		String ldMoney1 = request.getParameter("ldMoney1");
		String ldMoney2 = request.getParameter("ldMoney2");
		String ldMoney3 = request.getParameter("ldMoney3");

		String ldRemark1 = request.getParameter("ldRemark1");
		String ldRemark2 = request.getParameter("ldRemark2");
		String ldRemark3 = request.getParameter("ldRemark3");

		ArrayList<TfLossDeal> tfLossDeals = new ArrayList<TfLossDeal>();

		try {
			if (ldSpare11 != null && ldSpare11.length() > 0 && ldMoney1 != null && ldMoney1.length() > 0) {
			TfLossDeal tfLossDeal1 = new TfLossDeal();
			tfLossDeal1.setLossId(lossid);
			tfLossDeal1.setLdSpare1(ldSpare11);
			tfLossDeal1.setLdMoney(df.format(Double.valueOf(ldMoney1)));
			if (ldRemark1 != null && ldRemark1.length() > 0) {
				tfLossDeal1.setLdRemark(ldRemark1);
			}
			tfLossDeal1.setLdSpare2(DateTime.getCurDate_yyyyMMddHHmmssRe());

			tfLossDeals.add(tfLossDeal1);
		}

		if (ldSpare12 != null && ldSpare12.length() > 0 && ldMoney2 != null && ldMoney2.length() > 0) {
			TfLossDeal tfLossDeal2 = new TfLossDeal();
			tfLossDeal2.setLossId(lossid);
			tfLossDeal2.setLdSpare1(ldSpare12);
			tfLossDeal2.setLdMoney(df.format(Double.valueOf(ldMoney2)));
			if (ldRemark2 != null && ldRemark2.length() > 0) {
				tfLossDeal2.setLdRemark(ldRemark2);
			}
			tfLossDeal2.setLdSpare2(DateTime.getCurDate_yyyyMMddHHmmssRe());

			tfLossDeals.add(tfLossDeal2);
		}

		if (ldSpare13 != null && ldSpare13.length() > 0 && ldMoney3 != null && ldMoney3.length() > 0) {
			TfLossDeal tfLossDeal3 = new TfLossDeal();
			tfLossDeal3.setLossId(lossid);
			tfLossDeal3.setLdSpare1(ldSpare13);
			tfLossDeal3.setLdMoney(df.format(Double.valueOf(ldMoney3)));
			if (ldRemark3 != null && ldRemark3.length() > 0) {
				tfLossDeal3.setLdRemark(ldRemark3);
			}
			tfLossDeal3.setLdSpare2(DateTime.getCurDate_yyyyMMddHHmmssRe());

			tfLossDeals.add(tfLossDeal3);
		}

			tfLossDealService.insertNew(lossid, tfLossDeals);
			r = "t";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r = "f";
			return r;
		}
		return r;
	}

	/**
	 * 
	 * 损耗新增
	 * 
	 */
	@RequestMapping(value = "/phone/tfLoss_new")
	@ResponseBody
	public String tfLossNew(TfLossDeal tfLossDeal, ModelMap model, HttpServletRequest request, HttpSession session) {

		String r = "f";

		DecimalFormat df = new DecimalFormat("######0.00");// 保存显示用保留二位

		String id = request.getParameter("id");

		String lossName = request.getParameter("lossName");

		String lossStatus = request.getParameter("lossStatus");

		String lossid = UUID.randomUUID().toString();

		String serviceDate = DateTime.getCurDate_yyyy_MM_dd();

		TfLoss tfLoss = new TfLoss();

		tfLoss.setLossId(lossid);
		tfLoss.setUserId(id);
		tfLoss.setLossName(lossName);
		tfLoss.setLossStatus(lossStatus);
		tfLoss.setLossSpare2(serviceDate);

		String ldSpare11 = request.getParameter("ldSpare11");
		String ldSpare12 = request.getParameter("ldSpare12");
		String ldSpare13 = request.getParameter("ldSpare13");

		String ldMoney1 = request.getParameter("ldMoney1");
		String ldMoney2 = request.getParameter("ldMoney2");
		String ldMoney3 = request.getParameter("ldMoney3");

		String ldRemark1 = request.getParameter("ldRemark1");
		String ldRemark2 = request.getParameter("ldRemark2");
		String ldRemark3 = request.getParameter("ldRemark3");

		ArrayList<TfLossDeal> tfLossDeals = new ArrayList<TfLossDeal>();

		try {

			if (ldSpare11 != null && ldSpare11.length() > 0 && ldMoney1 != null && ldMoney1.length() > 0) {
				TfLossDeal tfLossDeal1 = new TfLossDeal();
				tfLossDeal1.setLossId(lossid);
				tfLossDeal1.setLdSpare1(ldSpare11);
				tfLossDeal1.setLdMoney(df.format(Double.valueOf(ldMoney1)));
				if (ldRemark1 != null && ldRemark1.length() > 0) {
					tfLossDeal1.setLdRemark(ldRemark1);
				}
				tfLossDeal1.setLdSpare2(DateTime.getCurDate_yyyyMMddHHmmssRe());

				tfLossDeals.add(tfLossDeal1);
			}

			if (ldSpare12 != null && ldSpare12.length() > 0 && ldMoney2 != null && ldMoney2.length() > 0) {
				TfLossDeal tfLossDeal2 = new TfLossDeal();
				tfLossDeal2.setLossId(lossid);
				tfLossDeal2.setLdSpare1(ldSpare12);
				tfLossDeal2.setLdMoney(df.format(Double.valueOf(ldMoney2)));
				if (ldRemark2 != null && ldRemark2.length() > 0) {
					tfLossDeal2.setLdRemark(ldRemark2);
				}
				tfLossDeal2.setLdSpare2(DateTime.getCurDate_yyyyMMddHHmmssRe());

				tfLossDeals.add(tfLossDeal2);
			}

			if (ldSpare13 != null && ldSpare13.length() > 0 && ldMoney3 != null && ldMoney3.length() > 0) {
				TfLossDeal tfLossDeal3 = new TfLossDeal();
				tfLossDeal3.setLossId(lossid);
				tfLossDeal3.setLdSpare1(ldSpare13);
				tfLossDeal3.setLdMoney(df.format(Double.valueOf(ldMoney3)));
				if (ldRemark3 != null && ldRemark3.length() > 0) {
					tfLossDeal3.setLdRemark(ldRemark3);
				}
				tfLossDeal3.setLdSpare2(DateTime.getCurDate_yyyyMMddHHmmssRe());

				tfLossDeals.add(tfLossDeal3);
			}

			tfLossDealService.insertAddNew(tfLoss, tfLossDeals);
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
