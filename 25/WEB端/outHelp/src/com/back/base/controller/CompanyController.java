package com.back.base.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.back.base.AbstractEntity;
import com.back.base.model.TResource;
import com.back.base.pageModel.Company;
import com.back.base.service.CompanyService;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;

@Controller
@RequestMapping("/back/company")
public class CompanyController extends BaseController {
	@Autowired(required = true)
	private CompanyService companyService;

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "cmd=delete")
	public String delete(String id, ModelMap model) {
		try {
			companyService.delete(id);
			model.put("msg", "删除成功！");
			model.put("flag", "remove");
			return IConstant.SUCCESS_PAGE;
		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}
	}

	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=main")
	public String main() {
		return "/backpage/base/organization/main";
	}

	/**
	 * 查询对象
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=find")
	public String find(String id, ModelMap model) {
		model.put("company", companyService.find(id));
		return "backpage/base/organization/company/view";
	}

	/**
	 * 更新页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=updateInput")
	public String updateInput(String id, String flag, ModelMap model) {
		if (StringUtils.hasText(id)) {
			model.put("company", companyService.find(id));
		} else {
			model.put("company", companyService.findRoot());
		}
		if (StringUtils.hasText(flag) && flag.equals("setting")) {
			return "backpage/base/organization/company/base";
		} else {
			return "backpage/base/organization/company/edit";
		}
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=addInput")
	public String addInput() {
		return "backpage/base/organization/company/edit";
	}

	/**
	 * 设置公司基本信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "cmd=saveOrUpdate")
	public String saveOrUpdate(Company company, String setting, ModelMap model) {
		if (!StringUtils.hasText(company.getId())) {
			company.setId(UUID.randomUUID().toString());
			company.setCreatetime(DateUtil.Time2String(new Date()));
			company.setUpdateFlag(false);
		} else {
			company.setUpdateFlag(true);
			company.setUpdatetime(DateUtil.Time2String(new Date()));
		}
		try {
			companyService.saveOrUpdate(company);
			if (company.isUpdateFlag()) {
				return "redirect:/back/company.do?cmd=find&id=" + company.getId() + "&flag=update&setting="+setting;// 修改
			} else {
				return "redirect:/back/company.do?cmd=find&id=" + company.getId() + "&flag=add";// 新增
			}

		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}
	}

	@Override
	public String getOperateColumn(List<TResource> re, AbstractEntity ae) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOperateButton(List<TResource> re,String[] params) {
		// TODO Auto-generated method stub
		return null;

	}

}
