package com.back.phone.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.back.base.AbstractEntity;
import com.back.base.controller.BaseController;
import com.back.base.model.TResource;
import com.back.base.utils.DateTime;
import com.back.phone.model.TfBusinessList;
import com.back.phone.model.TfBusinessListDeal;
import com.back.phone.model.TfCustomer;
import com.back.phone.model.TfLoss;
import com.back.phone.modelNew.TfBusinessListNew;
import com.back.phone.modelNew.TfBusinessListViewNew;
import com.back.phone.service.TfBusinessListDealService;
import com.back.phone.service.TfBusinessListService;
import com.back.phone.service.TfCustomerService;

@Controller
public class TfBusinessListController extends BaseController {

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
	private TfBusinessListService tfBusinessListService;

	@Autowired(required = true)
	private TfBusinessListDealService tfBusinessListDealService;

	@Autowired(required = true)
	private TfCustomerService tfCustomerService;

	/**
	 * 
	 * 业务上报---新增跟踪订单
	 * 
	 */
	@RequestMapping(value = "/phone/tfBusinessList_on")
	@ResponseBody
	public String tfBusinessListOn(TfBusinessListDeal tfBusinessListDeal, HttpServletRequest request,
			HttpSession session) {

		String r = "f";
		
		DecimalFormat df = new DecimalFormat("######0.00");// 保存显示用保留二位

		String serviceDate = DateTime.getCurDate_yyyy_MM_dd();

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

		String serviceTime = formatter.format(new Date());

		String id = request.getParameter("id");

		String brName = request.getParameter("brName");

		String brContent = request.getParameter("brContent");

		String brMoney = request.getParameter("brMoney");

		String brPlace = request.getParameter("brPlace");

		// 处理上传图片begin
		 String attachmentName = "";// 文件名
		 try {
		 MultipartFile attachmentFile = ((MultipartHttpServletRequest)
		 request).getFile("filename");
		 String uploadDir =
		 request.getSession().getServletContext().getRealPath("/upload");
		
		 String fileName = System.currentTimeMillis() + "";
		 InputStream stream = attachmentFile.getInputStream();
		 String attachmentOriginalName =
		 attachmentFile.getOriginalFilename();// 文件源名
		 String fileFormat =
		 attachmentOriginalName.substring(attachmentOriginalName.lastIndexOf(".")
		 + 1,
		 attachmentOriginalName.length());
		 attachmentName = fileName + "." + fileFormat;
		
		 File dirPath = new File(uploadDir);
		 if (!dirPath.exists()) {
		 dirPath.mkdirs();
		 }
		
		 OutputStream bos = null;
		 try {
		 bos = new FileOutputStream(uploadDir + "//" + attachmentName);
		 int bytesRead;
		 byte[] buffer = new byte[8192];
		 while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
		 bos.write(buffer, 0, bytesRead);
		 }
		 } finally {
		 if (bos != null) {
		 bos.close();
		 }
		 if (stream != null) {
		 stream.close();
		 }
		 }
		 } catch (Exception e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 r = "f";
		 }
		// 处理上传图片end

		ArrayList<TfCustomer> tfCustomerList = new ArrayList<TfCustomer>();// 客户集合

		ArrayList<TfLoss> tfLossList = new ArrayList<TfLoss>();// 损耗集合

		TfBusinessList tfBusinessList = new TfBusinessList();
		String tblid = UUID.randomUUID().toString();
		tfBusinessList.setBlId(tblid);
		tfBusinessList.setUserId(id);
		tfBusinessList.setBlPhoto(attachmentName);
		tfBusinessList.setBlData(serviceDate);
		tfBusinessList.setBlTime(serviceTime);
		tfBusinessList.setBlStatus(tfBusinessListDeal.getBldStatus());
		if (brName != null && brName.length() > 0) {
			tfBusinessList.setBlName(brName);
		}
		if (brContent != null && brContent.length() > 0) {
			tfBusinessList.setBlContent(brContent);
		}
		if (brMoney != null && brMoney.length() > 0) {
			tfBusinessList.setBlMoney(df.format(Double.valueOf(brMoney)));
		}
		if (brPlace != null && brPlace.length() > 0) {
			tfBusinessList.setBlPlace(brPlace);
		}

		/********* begin 客户 损耗 *************/
		String customerId1 = request.getParameter("customerId1");
		String customerId2 = request.getParameter("customerId2");

		String customerName1 = request.getParameter("customerName1");
		String customerName2 = request.getParameter("customerName2");

		String customerPhone1 = request.getParameter("customerPhone1");
		String customerPhone2 = request.getParameter("customerPhone2");

		String customerCompany1 = request.getParameter("customerCompany1");
		String customerCompany2 = request.getParameter("customerCompany2");

		String customerAddress1 = request.getParameter("customerAddress1");
		String customerAddress2 = request.getParameter("customerAddress2");

		String customerRemark1 = request.getParameter("customerRemark1");
		String customerRemark2 = request.getParameter("customerRemark2");

		if (customerName1 != null && customerName1.length() > 0) {
			TfCustomer tfCustomer1 = new TfCustomer();
			tfCustomer1.setUserId(id);
			tfCustomer1.setCustomerName(customerName1);
			tfCustomer1.setCustomerComplete("1");
			tfCustomer1.setCustomerSpare4(serviceDate);

			if (customerId1 != null && customerId1.length() > 0) {
				tfCustomer1.setCustomerId(customerId1);
			}
			if (customerPhone1 != null && customerPhone1.length() > 0) {
				tfCustomer1.setCustomerPhone(customerPhone1);
			}
			if (customerCompany1 != null && customerCompany1.length() > 0) {
				tfCustomer1.setCustomerCompany(customerCompany1);
			}
			if (customerAddress1 != null && customerAddress1.length() > 0) {
				tfCustomer1.setCustomerAddress(customerAddress1);
			}
			if (customerRemark1 != null && customerRemark1.length() > 0) {
				tfCustomer1.setCustomerRemark(customerRemark1);
			}

			tfCustomerList.add(tfCustomer1);
		}

		if (customerName2 != null) {
			TfCustomer tfCustomer2 = new TfCustomer();
			tfCustomer2.setUserId(id);
			// tfCustomer2.setCustomerId(uuid2);
			tfCustomer2.setCustomerName(customerName2);
			tfCustomer2.setCustomerComplete("1");
			tfCustomer2.setCustomerSpare4(serviceDate);

			if (customerId2 != null && customerId2.length() > 0) {
				tfCustomer2.setCustomerId(customerId2);
			}
			if (customerPhone2 != null && customerPhone2.length() > 0) {
				tfCustomer2.setCustomerPhone(customerPhone2);
			}
			if (customerCompany2 != null && customerCompany2.length() > 0) {
				tfCustomer2.setCustomerCompany(customerCompany2);
			}
			if (customerAddress2 != null && customerAddress2.length() > 0) {
				tfCustomer2.setCustomerAddress(customerAddress2);
			}
			if (customerRemark2 != null && customerRemark2.length() > 0) {
				tfCustomer2.setCustomerRemark(customerRemark2);
			}

			tfCustomerList.add(tfCustomer2);
		}

		String lossName1 = request.getParameter("lossName1");
		String lossName2 = request.getParameter("lossName2");
		String lossName3 = request.getParameter("lossName3");

		String lossMoney1 = request.getParameter("lossMoney1");
		String lossMoney2 = request.getParameter("lossMoney2");
		String lossMoney3 = request.getParameter("lossMoney3");

		String lossSpare11 = request.getParameter("lossSpare11");
		String lossSpare12 = request.getParameter("lossSpare12");
		String lossSpare13 = request.getParameter("lossSpare13");
		
		if (lossName1 != null && lossName1.length() > 0 && lossMoney1 != null && lossMoney1.length() > 0) {
			TfLoss tfLoss1 = new TfLoss();
			tfLoss1.setLossName(lossName1);
			tfLoss1.setLossMoney(df.format(Double.valueOf(lossMoney1)));
			if (lossSpare11 != null && lossSpare11.length() > 0) {
				tfLoss1.setLossSpare1(lossSpare11);
			}

			tfLossList.add(tfLoss1);
		}

		if (lossName2 != null && lossName2.length() > 0 && lossMoney2 != null && lossMoney2.length() > 0) {
			TfLoss tfLoss2 = new TfLoss();
			// tfLoss2.setLossId(uuLid2);
			tfLoss2.setLossName(lossName2);
			tfLoss2.setLossMoney(df.format(Double.valueOf(lossMoney2)));
			if (lossSpare12 != null && lossMoney2.length() > 0) {
				tfLoss2.setLossSpare1(lossSpare12);
			}

			tfLossList.add(tfLoss2);
		}

		if (lossName3 != null && lossName3.length() > 0 && lossMoney3 != null && lossMoney3.length() > 0) {
			TfLoss tfLoss3 = new TfLoss();
			tfLoss3.setLossName(lossName3);
			tfLoss3.setLossMoney(df.format(Double.valueOf(lossMoney3)));
			if (lossSpare13 != null && lossSpare13.length() > 0) {
				tfLoss3.setLossSpare1(lossSpare13);
			}

			tfLossList.add(tfLoss3);
		}

		/********** end **************/

		/********* begin 跟单子表 *************/

		tfBusinessListDeal.setUserId(id);
		tfBusinessListDeal.setBldData(serviceDate);
		tfBusinessListDeal.setBldTime(serviceTime);
		tfBusinessListDeal.setBlId(tblid);

		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String data = request.getParameter("data");
		String hour = request.getParameter("hour");
		

		if (tfBusinessListDeal.getBldVisit() != null && tfBusinessListDeal.getBldVisit().length() > 0
				&& tfBusinessListDeal.getBldVisit().equals("提醒")) {

			for (int i = 0; i < year.length(); i++) {
				if (!Character.isDigit(year.charAt(i))) {
					return "f";
				}
			}
			for (int i = 0; i < month.length(); i++) {
				if (!Character.isDigit(month.charAt(i))) {
					return "f";
				}
			}
			for (int i = 0; i < data.length(); i++) {
				if (!Character.isDigit(data.charAt(i))) {
					return "f";
				}
			}
			for (int i = 0; i < hour.length(); i++) {
				if (!Character.isDigit(hour.charAt(i))) {
					return "f";
				}
			}
			
			if(year.length()!=4){
				return "f";
			}
			if(month.length()<2){
				month = "0"+month;
			}
			if(data.length()<2){
				data = "0"+data;
			}
			if(hour.length()<2){
				hour = "0"+hour;
			}
			
			tfBusinessListDeal.setBldVisitdata(year+"-"+month+"-"+data+" "+hour+":00:00");

		}

		/********** end **************/

		try {

			tfBusinessListService.insertNew(id, tfBusinessList, tfBusinessListDeal, tfCustomerList, tfLossList);

			r = "t";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "f";
		}

		return r;
	}

	/**
	 * 
	 * 新增跟踪订单
	 * 
	 */
	@RequestMapping(value = "/phone/tfBusinessList_add")
	@ResponseBody
	public String tfBusinessListAdd(TfBusinessListDeal tfBusinessListDeal, HttpServletRequest request,
			HttpSession session) {

		String r = "f";

		String serviceDate = DateTime.getCurDate_yyyy_MM_dd();

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		
		String id = request.getParameter("id");

		String serviceTime = formatter.format(new Date());

		tfBusinessListDeal.setBldData(serviceDate);

		tfBusinessListDeal.setBldTime(serviceTime);
		
		tfBusinessListDeal.setUserId(id);
		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String data = request.getParameter("data");
		String hour = request.getParameter("hour");
		
		
		if (tfBusinessListDeal.getBldVisit() != null && tfBusinessListDeal.getBldVisit().length() > 0
				&& tfBusinessListDeal.getBldVisit().equals("提醒")) {

			for (int i = 0; i < year.length(); i++) {
				if (!Character.isDigit(year.charAt(i))) {
					return "f";
				}
			}
			for (int i = 0; i < month.length(); i++) {
				if (!Character.isDigit(month.charAt(i))) {
					return "f";
				}
			}
			for (int i = 0; i < data.length(); i++) {
				if (!Character.isDigit(data.charAt(i))) {
					return "f";
				}
			}
			for (int i = 0; i < hour.length(); i++) {
				if (!Character.isDigit(hour.charAt(i))) {
					return "f";
				}
			}
			
			if(year.length()!=4){
				return "f";
			}
			if(month.length()<2){
				month = "0"+month;
			}
			if(data.length()<2){
				data = "0"+data;
			}
			if(hour.length()<2){
				hour = "0"+hour;
			}
			
			tfBusinessListDeal.setBldVisitdata(year+"-"+month+"-"+data+" "+hour+":00:00");

		}

		try {

			tfBusinessListDealService.insert(tfBusinessListDeal);

			TfBusinessList tfBusinessList = tfBusinessListService.selectByPrimaryKey(tfBusinessListDeal.getBlId());

			tfBusinessList.setBlStatus(tfBusinessListDeal.getBldStatus());

			tfBusinessList.setBlData(serviceDate);

			tfBusinessList.setBlTime(serviceTime);

			if (tfBusinessListDeal.getBldStatus().equals("赢单")) {
				tfBusinessList.setBlMoney(tfBusinessListDeal.getBldMoney());
			}

			tfBusinessListService.updateByPrimaryKeySelective(tfBusinessList);

			r = "t";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return r;
	}

	/**
	 * 
	 * 跟踪订单--列表
	 * 
	 */
	@RequestMapping(value = "/phone/tfBusinessList_list")
	@ResponseBody
	public TfBusinessListNew tfBusinessListList(TfBusinessList tfBusinessList, HttpServletRequest request,
			HttpSession session) {

		String id = request.getParameter("id");

		tfBusinessList.setUserId(id);

		List<TfBusinessList> TfBusinessLists = tfBusinessListService.queryTempList(tfBusinessList);

		TfBusinessListNew tfBusinessListNew = new TfBusinessListNew();

		tfBusinessListNew.setId(id);

		tfBusinessListNew.setTfBusinessList(TfBusinessLists);

		return tfBusinessListNew;
	}

	/**
	 * 
	 * 跟踪订单--详情
	 * 
	 */
	@RequestMapping(value = "/phone/tfBusinessList_view")
	@ResponseBody
	public TfBusinessListViewNew tfBusinessListView(HttpServletRequest request, HttpSession session) {

		TfBusinessListViewNew tfBusinessListViewNew = new TfBusinessListViewNew();

		String id = request.getParameter("id");
		String id1 = request.getParameter("id1");
		
		TfBusinessList tfBusinessList = tfBusinessListService.selectByPrimaryKey(id1);

		TfBusinessListDeal tfBusinessListDeal = new TfBusinessListDeal();
		tfBusinessListDeal.setBlId(id1);
		List<TfBusinessListDeal> tfBusinessListDeals = tfBusinessListDealService.queryTempList(tfBusinessListDeal);

		List<TfCustomer> tfCustomers = new ArrayList<TfCustomer>();
		if (tfBusinessList.getBlCustomer() != null && tfBusinessList.getBlCustomer().length() > 0) {
			String[] s = tfBusinessList.getBlCustomer().split(";");
			for (String cid : s) {
				TfCustomer tfCustomer = new TfCustomer();
				tfCustomer = tfCustomerService.selectByPrimaryKey(cid);
				if (tfCustomer != null) {
					tfCustomers.add(tfCustomer);
				}
			}
		}

		tfBusinessListViewNew.setIdNew(id);
		tfBusinessListViewNew.setTfBusinessList(tfBusinessList);
		tfBusinessListViewNew.setTfBusinessListDeal(tfBusinessListDeals);
		tfBusinessListViewNew.setTfCustomer(tfCustomers);

		return tfBusinessListViewNew;
	}

}
