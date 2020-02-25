package com.back.phone.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.util.Base64;

public class Base64Util {
	
	protected void getPhono(String name,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String file1 = req.getParameter("file");
		if (file1 != null) {
			byte[] b = Base64.decodeFast(file1);
			String filepath = req.getSession().getServletContext().getRealPath("/files");
			File file = new File(filepath);
			if (!file.exists())
				file.mkdirs();
			FileOutputStream fos = new FileOutputStream(
					file.getPath() + "/phono" + name + ".jpg");
			System.out.println(file.getPath());
			fos.write(b);
			fos.flush();
			fos.close();
		}
	}

}
