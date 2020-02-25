package com.back.phone.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.back.base.pageModel.Login;
import com.back.base.service.LoginService;
import com.back.phone.dao.TfBusinessListMapper;


@Controller
public class LoginOnController extends BaseController {

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
	private LoginService loginService;

	@Autowired(required = true)
	private TfBusinessListMapper tfBusinessListMapper;

	/**
	 * 
	 * 测试
	 * 
	 */
	@RequestMapping(value = "/phone/test")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MultipartFile attachmentFile = ((MultipartHttpServletRequest) request).getFile("filename");
		String uploadDir = request.getSession().getServletContext().getRealPath("/upload");

		String fileName = System.currentTimeMillis() + "";
		InputStream stream = attachmentFile.getInputStream();
		String attachmentOriginalName = attachmentFile.getOriginalFilename();// 文件源名
		String fileFormat = attachmentOriginalName.substring(attachmentOriginalName.lastIndexOf(".") + 1,
				attachmentOriginalName.length());
		String attachmentName = fileName + "." + fileFormat;// 服务器文件名

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

		return attachmentName;

	}

	/**
	 * 
	 * 测试
	 * 
	 */
	@RequestMapping(value = "/phone/test1")
	@ResponseBody
	public String test1(MultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页

		String message = "t";

		String path = request.getSession().getServletContext().getRealPath("upload");

		String fileName = file.getOriginalFilename();

		File targetFile = new File(path, fileName);

		if (!targetFile.exists()) {

			targetFile.mkdirs();

		}

		file.transferTo(targetFile);

		return message;

	}

	/**
	 * 
	 * 登录
	 * 
	 */
	@RequestMapping(value = "/phone/login_on")
	@ResponseBody
	public Login loginOn(Login login, ModelMap model) {

		login = loginService.login(login);
		if (null != login) {
			return login;
		} else {
			Login l1 = new Login();
			return l1;
		}

	}

}
