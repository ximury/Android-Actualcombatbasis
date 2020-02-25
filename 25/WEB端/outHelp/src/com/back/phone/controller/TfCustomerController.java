package com.back.phone.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

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
import com.back.phone.model.TfBusinessList;
import com.back.phone.model.TfBusinessReport;
import com.back.phone.model.TfCustomer;
import com.back.phone.modelNew.TfCustomerNew;
import com.back.phone.service.TfBusinessListService;
import com.back.phone.service.TfBusinessReportService;
import com.back.phone.service.TfCustomerService;

@Controller
public class TfCustomerController extends BaseController{

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
	private TfCustomerService tfCustomerService;
	
	@Autowired(required = true)
	private TfBusinessReportService tfBusinessReportService;
	
	@Autowired(required = true)
	private TfBusinessListService tfBusinessListService;
	
	/**
	 * 
	 * 客户列表
	 * 
	 */
	@RequestMapping(value = "/phone/tfCustomer_list")
	@ResponseBody
	public TfCustomerNew tfCustomerList(TfCustomer tfCustomer, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页
		
		String id = request.getParameter("id");
		
		String type = request.getParameter("type");
		
		tfCustomer.setUserId(id);
		
		tfCustomer.setCustomerComplete("0");//完成
		
		if(type!=null && type.equals("未合作")){
			
			tfCustomer.setCustomerSpare1("0");
		}
		if(type!=null && type.equals("初次合作")){
			
			tfCustomer.setCustomerSpare1("1");
		}
		if(type!=null && type.equals("多次合作")){
			
			tfCustomer.setCustomerSpare1("999");
		}
		
		List<TfCustomer> tfCustomers =  tfCustomerService.queryTempList(tfCustomer);
		
		TfCustomerNew tcn = new TfCustomerNew();
		
		tcn.setTfCustomerList(tfCustomers);
		
		return tcn;
	}
	
	/**
	 * 
	 * 客户资料
	 * 
	 */
	@RequestMapping(value = "/phone/tfCustomer_view")
	@ResponseBody
	public TfCustomerNew tfCustomerView(TfCustomer tfCustomer, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页
		
		String cid = request.getParameter("cid");
		
		tfCustomer = tfCustomerService.selectByPrimaryKey(cid);
		
		List<TfBusinessList> tfBusinessLists = tfBusinessListService.queryTempListByCstmId(cid);
		
		List<TfBusinessReport> tfBusinessReports = tfBusinessReportService.queryTempListByCstmId(cid);
		
		TfCustomerNew tcn = new TfCustomerNew();
		
		tcn.setTfCustomer(tfCustomer);
		
		tcn.setTfBusinessListList(tfBusinessLists);
		
		tcn.setTfBusinessReportList(tfBusinessReports);		
		
		return tcn;
	}
	
	
	/**
	 * 
	 * 客户资料-添加/更新
	 * 
	 */
	@RequestMapping(value = "/phone/tfCustomer_save")
	@ResponseBody
	public String tfCustomerSave(TfCustomer tfCustomer, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		
		String r = "f";
		
		String serviceDate = DateTime.getCurDate_yyyy_MM_dd();
		
		String id = request.getParameter("id");
		
		System.out.println(id);
		
		String photo = request.getParameter("photo");
		
		if(photo!=null && photo.equals("y")){
			
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
			tfCustomer.setCustomerPhoto(attachmentName);
			// 处理上传图片end
			
		}
		
		
		
		if(tfCustomer.getCustomerId()!=null && tfCustomer.getCustomerId().length()>0){
			
			tfCustomer.setCustomerComplete("0");
			
			tfCustomerService.updateByPrimaryKeySelective(tfCustomer);
				
			r = "t";
			
		}else{
			
			tfCustomer.setUserId(id);//用户id
			tfCustomer.setCustomerComplete("0");
			tfCustomer.setCustomerAllmoney("0.00");
			tfCustomer.setCustomerSpare1("0");// 合作次数
			tfCustomer.setCustomerSpare2("1");// 客户等级
			tfCustomer.setCustomerSpare4(serviceDate);//日期
			
			tfCustomerService.insert(tfCustomer);
			
			r = "t";
		}
		
		return r;
	}
	
	
}
