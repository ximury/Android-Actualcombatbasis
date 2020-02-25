package com.back.phone.controller;
import com.back.base.page.PageContext;
import com.jspsmart.upload.SmartUpload;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;  

@Controller
public class UploadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {  
  
	
	
	@RequestMapping(value = "/phone/testABC")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		
		System.out.println("开始了！！！！！！！！！！！阿门");
		
        response.setContentType("text/html,charset=UTF-8");  
  
        SmartUpload smartUpload = new SmartUpload();  
  
        try {  
            smartUpload.initialize(this.getServletConfig(), request, response);  
            smartUpload.upload();  
            com.jspsmart.upload.File smartFile = smartUpload.getFiles().getFile(0);  
            if (!smartFile.isMissing()) {  
                String saveFileName = "/upload/" + smartFile.getFileName();  
                smartFile.saveAs(saveFileName, smartUpload.SAVE_PHYSICAL);  
                response.getWriter().print("ok:" + saveFileName + ", msg:" + smartUpload.getRequest().getParameter("msg"));  
            } else {  
                response.getWriter().print("missing...");  
            }  
        } catch (Exception e) {  
            response.getWriter().print(e);  
        }  
  
    }  
  
}  