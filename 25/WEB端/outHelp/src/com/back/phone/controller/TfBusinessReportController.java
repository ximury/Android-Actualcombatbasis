package com.back.phone.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.back.base.AbstractEntity;
import com.back.base.controller.BaseController;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.base.utils.DateTime;
import com.back.phone.model.TfBusinessReport;
import com.back.phone.model.TfCustomer;
import com.back.phone.model.TfLoss;
import com.back.phone.modelNew.TfBusinessReportNew;
import com.back.phone.service.TfBusinessReportService;
import com.back.phone.service.TfCustomerService;
import com.back.phone.service.TfLossService;

@Controller
public class TfBusinessReportController extends BaseController {

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
	private TfBusinessReportService tfBusinessReportService;
	
	@Autowired(required = true)
	private TfCustomerService tfCustomerService;
	
	@Autowired(required = true)
	private TfLossService tfLossService;

	/**
	 * 
	 * 业务列表
	 * 
	 */
	@RequestMapping(value = "/phone/tfBusinessReport_list")
	@ResponseBody
	public TfBusinessReportNew tfBusinessReportList(TfBusinessReport tfBusinessReport, ModelMap model,
			HttpServletRequest request, HttpSession session) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页
		
		String id = request.getParameter("id");
		
		tfBusinessReport.setUserId(id);

		TfBusinessReportNew trn = new TfBusinessReportNew();

		List<TfBusinessReport> tfBusinessReports = tfBusinessReportService.queryTempList(tfBusinessReport);

		trn.setTfBusinessReport(tfBusinessReports);

		trn.setUserIId(tfBusinessReport.getUserId());

		return trn;

	}

	/**
	 * 
	 * 业务详情
	 * 
	 */
	@RequestMapping(value = "/phone/tfBusinessReport_view")
	@ResponseBody
	public TfBusinessReport tfBusinessReportView(TfBusinessReport tfBusinessReport, HttpServletRequest request,
			HttpSession session) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页

		String id = request.getParameter("iid");

		tfBusinessReport = tfBusinessReportService.selectByPrimaryKey(id);

		return tfBusinessReport;

	}

	/**
	 * 
	 * 业务新增
	 * 
	 */
	@RequestMapping(value = "/phone/tfBusinessReport_add")
	@ResponseBody
	public String tfBusinessReportAdd(TfBusinessReport tfBusinessReport, HttpServletRequest request,
			HttpSession session) {

		String r = "f";
		
		DecimalFormat df = new DecimalFormat("######0.00");// 保存显示用保留二位

		String id = request.getParameter("id");

		String serviceDate = DateTime.getCurDate_yyyy_MM_dd();

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

		String serviceTime = formatter.format(new Date());

		if (tfBusinessReport.getBrStatus() == null || tfBusinessReport.getBrStatus().equals("")) {
			return r;
		}
		
		// 处理上传图片begin
		String attachmentName = "";// 文件名
		try {
			MultipartFile attachmentFile = ((MultipartHttpServletRequest) request).getFile("filename");
			String uploadDir = request.getSession().getServletContext().getRealPath("/upload");

			String fileName = System.currentTimeMillis() + "";
			InputStream stream = attachmentFile.getInputStream();
			String attachmentOriginalName = attachmentFile.getOriginalFilename();// 文件源名
			String fileFormat = attachmentOriginalName.substring(attachmentOriginalName.lastIndexOf(".") + 1,
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
			return r;
		}
		tfBusinessReport.setBrPhoto(attachmentName);
		// 处理上传图片end
		
		tfBusinessReport.setUserId(id);

		tfBusinessReport.setBrTime(serviceTime);

		tfBusinessReport.setBrData(serviceDate);

		if (tfBusinessReport.getBrStatus().equals("业务拜访")) {
			
			tfBusinessReportService.insert(tfBusinessReport);
			
			r = "t";

		} else if (tfBusinessReport.getBrStatus().equals("订单业绩")) {
			
			String uuCid = "";
			String uuid1 = UUID.randomUUID().toString();
			String uuid2 = UUID.randomUUID().toString();
			
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
			
			if(customerName1!=null && customerName1.length()>0){
				TfCustomer tfCustomer1 = new TfCustomer();
				tfCustomer1.setUserId(id);
				tfCustomer1.setCustomerName(customerName1);
				tfCustomer1.setCustomerComplete("1");
				tfCustomer1.setCustomerAllmoney("0.00");
				tfCustomer1.setCustomerSpare1("1");// 合作次数
				tfCustomer1.setCustomerSpare4(serviceDate);//日期
				
				if(customerPhone1!=null && customerPhone1.length()>0){
					tfCustomer1.setCustomerPhone(customerPhone1);
				}
				if(customerCompany1!=null && customerCompany1.length()>0){
					tfCustomer1.setCustomerCompany(customerCompany1);					
				}
				if(customerAddress1!=null && customerAddress1.length()>0){
					tfCustomer1.setCustomerAddress(customerAddress1);
				}
				if(customerRemark1!=null && customerRemark1.length()>0){
					tfCustomer1.setCustomerRemark(customerRemark1);
				}

				if(customerId1!=null && customerId1.length()>0){
					TfCustomer tfCustomerNew = tfCustomerService.selectByPrimaryKey(customerId1);
					tfCustomerNew.setCustomerSpare1(tfCustomerNew.getCustomerSpare1()+1);
					tfCustomerService.updateByPrimaryKeySelective(tfCustomerNew);
					uuCid = uuCid+customerId1+";";
				}else{
					tfCustomer1.setCustomerSpare1("1");// 合作次数
					tfCustomer1.setCustomerId(uuid1);
					tfCustomerService.insert(tfCustomer1);
					uuCid = uuCid+uuid1+";";
				}
				
			}
			
			if(customerName2!=null && customerName2.length()>0){
				TfCustomer tfCustomer2 = new TfCustomer();
				tfCustomer2.setUserId(id);
				tfCustomer2.setCustomerName(customerName2);
				tfCustomer2.setCustomerComplete("1");
				tfCustomer2.setCustomerAllmoney("0.00");
				tfCustomer2.setCustomerSpare1("1");// 合作次数
				tfCustomer2.setCustomerSpare4(serviceDate);//日期
				
				
				if(customerPhone2!=null && customerPhone2.length()>0){
					tfCustomer2.setCustomerPhone(customerPhone2);
				}
				if(customerCompany2!=null && customerCompany2.length()>0){
					tfCustomer2.setCustomerCompany(customerCompany2);					
				}
				if(customerAddress2!=null && customerAddress2.length()>0){
					tfCustomer2.setCustomerAddress(customerAddress2);
				}
				if(customerRemark2!=null && customerRemark2.length()>0){
					tfCustomer2.setCustomerRemark(customerRemark2);
				}
				
				if(customerId2!=null && customerId2.length()>0){
					TfCustomer tfCustomerNew = tfCustomerService.selectByPrimaryKey(customerId2);
					tfCustomerNew.setCustomerSpare1(tfCustomerNew.getCustomerSpare1()+1);
					tfCustomerService.updateByPrimaryKeySelective(tfCustomerNew);
					uuCid = uuCid+customerId2+";";
				}else{
					tfCustomer2.setCustomerSpare1("1");// 合作次数
					tfCustomer2.setCustomerId(uuid2);
					tfCustomerService.insert(tfCustomer2);
					uuCid = uuCid+uuid2+";";
				}
				
				uuCid = uuCid+uuid2+";";
			}
			
			if(uuCid.length()>0){
				uuCid = uuCid.substring(0, uuCid.length()-1);				
				tfBusinessReport.setBrSpare1(uuCid);
			}
			
			String uuLid = "";
			String uuLid1 = UUID.randomUUID().toString();
			String uuLid2 = UUID.randomUUID().toString();
			String uuLid3 = UUID.randomUUID().toString();
			
			String lossName1 = request.getParameter("lossName1");
			String lossName2 = request.getParameter("lossName2");
			String lossName3 = request.getParameter("lossName3");
			
			String lossMoney1 = request.getParameter("lossMoney1");
			String lossMoney2 = request.getParameter("lossMoney2");
			String lossMoney3 = request.getParameter("lossMoney3");
			
			String lossSpare11 = request.getParameter("lossSpare11");
			String lossSpare12 = request.getParameter("lossSpare12");
			String lossSpare13 = request.getParameter("lossSpare13");
			
			if(lossName1!=null && lossName1.length()>0 && lossMoney1!=null && lossMoney1.length()>0){
				TfLoss tfLoss1 = new TfLoss();
				tfLoss1.setLossId(uuLid1);
				tfLoss1.setLossName(lossName1);
				tfLoss1.setLossMoney(df.format(lossMoney1));
				tfLoss1.setLossStatus("1");//业务
				tfLoss1.setLossSpare2(serviceDate);
				
				if(lossSpare11!=null && lossSpare11.length()>0){
					tfLoss1.setLossSpare1(lossSpare11);
				}
				tfLossService.insert(tfLoss1);
				
				uuLid = uuLid + uuLid1 +";";
			}
			
			if(lossName2!=null && lossName2.length()>0 && lossMoney2!=null && lossMoney2.length()>0){
				TfLoss tfLoss2 = new TfLoss();
				tfLoss2.setLossId(uuLid2);
				tfLoss2.setLossName(lossName2);
				tfLoss2.setLossMoney(df.format(lossMoney2));
				tfLoss2.setLossStatus("1");//业务
				tfLoss2.setLossSpare2(serviceDate);
				if(lossSpare12!=null && lossSpare12.length()>0){
					tfLoss2.setLossSpare1(lossSpare12);
				}
				tfLossService.insert(tfLoss2);
				
				uuLid = uuLid + uuLid2 +";";
			}
			
			if(lossName3!=null && lossName3.length()>0 && lossMoney3!=null && lossMoney3.length()>0){
				TfLoss tfLoss3 = new TfLoss();
				tfLoss3.setLossId(uuLid3);
				tfLoss3.setLossName(lossName3);
				tfLoss3.setLossMoney(df.format(lossMoney3));
				tfLoss3.setLossStatus("1");//业务
				tfLoss3.setLossSpare2(serviceDate);
				if(lossSpare13!=null && lossSpare13.length()>0){
					tfLoss3.setLossSpare1(lossSpare13);
				}
				tfLossService.insert(tfLoss3);
				
				uuLid = uuLid + uuLid3 +";";
			}
			
			if(uuLid.length()>0){
				uuLid = uuLid.substring(0, uuLid.length()-1);				
				tfBusinessReport.setBrSpare2(uuLid);
			}
			
			tfBusinessReportService.insert(tfBusinessReport);
			
			
			
			r = "t";
		}

		return r;
	}
	
	
	
	
	/**
	 * 
	 * 业务新增 --new
	 * 
	 */
	@RequestMapping(value = "/phone/tfBusinessReport_addNew")
	@ResponseBody
	public String tfBusinessReportAddNew(TfBusinessReport tfBusinessReport, HttpServletRequest request,
			HttpSession session) {

		String r = "f";

		String id = request.getParameter("id");

		String serviceDate = DateTime.getCurDate_yyyy_MM_dd();

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

		String serviceTime = formatter.format(new Date());

		if (tfBusinessReport.getBrStatus() == null || tfBusinessReport.getBrStatus().equals("")) {
			return r;
		}
		
		// 处理上传图片begin
		String attachmentName = "";// 文件名
		try {
			MultipartFile attachmentFile = ((MultipartHttpServletRequest) request).getFile("filename");
			String uploadDir = request.getSession().getServletContext().getRealPath("/upload");

			String fileName = System.currentTimeMillis() + "";
			InputStream stream = attachmentFile.getInputStream();
			String attachmentOriginalName = attachmentFile.getOriginalFilename();// 文件源名
			String fileFormat = attachmentOriginalName.substring(attachmentOriginalName.lastIndexOf(".") + 1,
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
		tfBusinessReport.setBrPhoto(attachmentName);
		// 处理上传图片end
		
		tfBusinessReport.setUserId(id);

		tfBusinessReport.setBrTime(serviceTime);

		tfBusinessReport.setBrData(serviceDate);

		if (tfBusinessReport.getBrStatus().equals("业务拜访")) {
			
			tfBusinessReportService.insert(tfBusinessReport);
			
			r = "t";

		} else if (tfBusinessReport.getBrStatus().equals("订单业绩")) {
			
			ArrayList<Map> customerList = new ArrayList<Map>(); 
			
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
			
			if(customerName1!=null && customerName1.length()>0){
				
				Map<String, String> map = new HashMap<String, String>();
				
				map.put("customerId", customerId1);
				map.put("customerName", customerName1);
				map.put("customerPhone", customerPhone1);
				map.put("customerCompany", customerCompany1);
				map.put("customerAddress", customerAddress1);
				map.put("customerRemark", customerRemark1);
				
				customerList.add(map);
			}
			
			if(customerName2!=null && customerName2.length()>0){
				
				Map<String, String> map = new HashMap<String, String>();
				
				map.put("customerId", customerId2);
				map.put("customerName", customerName2);
				map.put("customerPhone", customerPhone2);
				map.put("customerCompany", customerCompany2);
				map.put("customerAddress", customerAddress2);
				map.put("customerRemark", customerRemark2);
				
				customerList.add(map);
				
			}
			
			ArrayList<Map> lossList = new ArrayList<Map>(); 
			
			String lossName1 = request.getParameter("lossName1");
			String lossName2 = request.getParameter("lossName2");
			String lossName3 = request.getParameter("lossName3");
			
			String lossMoney1 = request.getParameter("lossMoney1");
			String lossMoney2 = request.getParameter("lossMoney2");
			String lossMoney3 = request.getParameter("lossMoney3");
			
			String lossSpare11 = request.getParameter("lossSpare11");
			String lossSpare12 = request.getParameter("lossSpare12");
			String lossSpare13 = request.getParameter("lossSpare13");
			
			if(lossName1!=null && lossName1.length()>0 && lossMoney1!=null && lossMoney1.length()>0){
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", lossName1);
				map.put("lossMoney", lossMoney1);
				map.put("lossSpare1", lossSpare11);
				
				lossList.add(map);
			}
			
			if(lossName2!=null && lossName2.length()>0 && lossMoney2!=null && lossMoney2.length()>0){
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", lossName2);
				map.put("lossMoney", lossMoney2);
				map.put("lossSpare1", lossSpare12);
				
				lossList.add(map);
			}
			
			if(lossName3!=null && lossName3.length()>0 && lossMoney3!=null && lossMoney3.length()>0){
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", lossName3);
				map.put("lossMoney", lossMoney3);
				map.put("lossSpare1", lossSpare13);
				
				lossList.add(map);
			}
			
			tfBusinessReportService.insertNew(id,tfBusinessReport, customerList, lossList);
			
			r = "t";
		}

		return r;
	}

}
