package com.back.phone.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.back.base.AbstractEntity;
import com.back.base.controller.BaseController;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.base.utils.DateTime;
import com.back.phone.model.TfAttence;
import com.back.phone.model.TfAttenceMain;
import com.back.phone.modelNew.TfAttenceMainNew;
import com.back.phone.modelNew.TfAttenceNew;
import com.back.phone.service.TfAttenceMainService;
import com.back.phone.service.TfAttenceService;

import sun.misc.BASE64Encoder;

@Controller
public class TfAttenceController extends BaseController {

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
	private TfAttenceService tfAttenceService;

	@Autowired(required = true)
	private TfAttenceMainService tfAttenceMainService;

	/**
	 * 
	 * 考勤明细-列表初始
	 * 
	 */
	@RequestMapping(value = "/phone/tfAttence_listO")
	@ResponseBody
	public TfAttenceNew tfAttenceListO(TfAttence tfAttence, ModelMap model, HttpServletRequest request,
			HttpSession session) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页

		TfAttenceNew tt = new TfAttenceNew();

		String id = request.getParameter("id");

		tfAttence.setUserId(id);

		List<TfAttence> tfAttences = tfAttenceService.queryTempList(tfAttence);

		tt.setDate(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));

		tt.setTfAttence(tfAttences);

		return tt;

	}

	/**
	 * 
	 * 考勤-列表初始
	 * 
	 */
	@RequestMapping(value = "/phone/tfAttence_list")
	@ResponseBody
	public TfAttenceMainNew tfAttenceList(TfAttenceMain tfAttenceMain, ModelMap model, HttpServletRequest request,
			HttpSession session) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页

		String id = request.getParameter("id");
		
		String yearMonth = request.getParameter("yearMonth");
		
		tfAttenceMain.setUserId(id);
		
		tfAttenceMain.setAmData(yearMonth);

		List<TfAttenceMain> tt = tfAttenceMainService.queryTempList(tfAttenceMain);

		TfAttenceMainNew tam = new TfAttenceMainNew();

		tam.setTfAttenceMain(tt);

		return tam;

	}

	/**
	 * 
	 * 考勤-列表 翻页和查询
	 * 
	 */
	@RequestMapping(value = "/phone/tfAttence_listCheck")
	@ResponseBody
	public net.sf.json.JSONArray tfAttenceListCheck(TfAttence tfAttence, ModelMap model, HttpServletRequest request,
			HttpSession session) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页

		String id = request.getParameter("id");

		String yearMonth = request.getParameter("yearMonth");

		tfAttence.setUserId(id);

		tfAttence.setAttenceData(yearMonth);

		List<TfAttence> tfAttences = tfAttenceService.queryCheckList(tfAttence);

		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(tfAttences);

		return jsonArray;

	}

	/**
	 * 
	 * 考勤-签到签退
	 * 
	 */
	@RequestMapping(value = "/phone/tfAttence_on")
	@ResponseBody
	public String tfAttenceOn(TfAttenceMain tfAttenceMain, HttpServletRequest request, HttpSession session)
			throws ServletException, IOException {
		
		String result = "f";

		String start = "08:30:00";

		String end = "17:00:00";

		try {

			String id = request.getParameter("id");

			String date = request.getParameter("date");

			String attenceStatus = request.getParameter("attenceStatus");

			String attencePlace = request.getParameter("attencePlace");

			String serviceDate = DateTime.getCurDate_yyyy_MM_dd();

			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

			String serviceTime = formatter.format(new Date());

			if (date != null && !date.equals(serviceDate)) {
				result = "f";
				return result;
			}

			tfAttenceMain.setUserId(id);
			tfAttenceMain.setAmData(serviceDate);

			// 处理上传图片begin
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
			// 处理上传图片end
			
			tfAttenceMain = tfAttenceMainService.selectByIdDate(tfAttenceMain);

			if (attenceStatus != null && attenceStatus.equals("0")) {

				if (tfAttenceMain == null) {
					TfAttenceMain tfAttenceMain1 = new TfAttenceMain();
					tfAttenceMain1.setAmStart("1");
					tfAttenceMain1.setUserId(id);
					tfAttenceMain1.setAmData(serviceDate);
					tfAttenceMain1.setAmSplace(attencePlace);
					tfAttenceMain1.setAmStartt(serviceTime);
					tfAttenceMain1.setAmSphoto(attachmentName);

					java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm:ss");
					java.util.Calendar c1 = java.util.Calendar.getInstance();
					java.util.Calendar c2 = java.util.Calendar.getInstance();

					c1.setTime(df.parse(serviceTime));
					c2.setTime(df.parse(start));
					int r = c1.compareTo(c2);
					if (r >= 0) {
						tfAttenceMain1.setAmStartr("1");
					} else {
						tfAttenceMain1.setAmStartr("0");
					}

					String uuid = UUID.randomUUID().toString();
					tfAttenceMain1.setAmId(uuid);
					tfAttenceMainService.insert(tfAttenceMain1);
					result = "t";
				}

			}

			if (attenceStatus != null && attenceStatus.equals("1")) {

				if (tfAttenceMain != null) {

					tfAttenceMain.setAmEnd("1");
					tfAttenceMain.setAmEplace(attencePlace);
					tfAttenceMain.setAmEndt(serviceTime);
					tfAttenceMain.setAmEphoto(attachmentName);

					java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm:ss");
					java.util.Calendar c1 = java.util.Calendar.getInstance();
					java.util.Calendar c2 = java.util.Calendar.getInstance();

					c1.setTime(df.parse(serviceTime));
					c2.setTime(df.parse(end));
					int r = c1.compareTo(c2);
					if (r >= 0) {
						tfAttenceMain.setAmEndr("0");

					} else {
						tfAttenceMain.setAmEndr("1");

					}
					tfAttenceMainService.updateByPrimaryKeySelective(tfAttenceMain);

					result = "t";
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "f";
		}

		return result;

	}

	/**
	 * 
	 * 考勤-明细
	 * 
	 */
	@RequestMapping(value = "/phone/tfAttence_view")
	@ResponseBody
	public TfAttenceMainNew tfAttenceView(TfAttenceMain tfAttenceMain, ModelMap model, HttpServletRequest request,
			HttpSession session) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(false);// 修改分页状态 是否分页

		String id = request.getParameter("id");

		String date = request.getParameter("date");

		tfAttenceMain.setUserId(id);
		tfAttenceMain.setAmData(date);

		List<TfAttenceMain> tt = tfAttenceMainService.queryTempList(tfAttenceMain);

		TfAttenceMainNew tam = new TfAttenceMainNew();

		tam.setTfAttenceMain(tt);

		return tam;

	}

}
