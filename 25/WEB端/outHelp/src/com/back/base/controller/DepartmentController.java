package com.back.base.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.base.AbstractEntity;
import com.back.base.model.TPerson;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.base.pageModel.Department;
import com.back.base.service.DepartmentService;
import com.back.base.service.PersonService;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;

@Controller
@RequestMapping("/back/department")
public class DepartmentController extends BaseController{
	@Autowired(required = true)
	private DepartmentService departmentService;
	
	@Autowired(required = true)
	private PersonService personService;

	/**
	 * 获取列表（可以带查询条件）
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	@RequestMapping(params = "cmd=list")
	public @ResponseBody
	List<Department> list(Department department) {
		List<Department> list = departmentService.list(department);
		return list;
	}

	/**
	 * 新增/更新
	 * 
	 * @param department
	 * @return
	 */
	@RequestMapping(params = "cmd=saveOrUpdate")
	public String saveOrUpdate(Department department, ModelMap model) {
		if (!StringUtils.hasText(department.getId())) {
			department.setId(UUID.randomUUID().toString());
			department.setCreatetime(DateUtil.Time2String(new Date()));
			department.setUpdateFlag(false);
		} else {
			department.setUpdateFlag(true);
			department.setUpdatetime(DateUtil.Time2String(new Date()));
		}
		try {
			departmentService.saveOrUpdate(department);
			if (department.isUpdateFlag()) {
				return "redirect:/back/department.do?cmd=find&id="+department.getId()+"&flag=update";//修改
			} else {
				return "redirect:/back/department.do?cmd=find&id="+department.getId()+"&flag=add";//新增
			}
		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "cmd=delete")
	public String delete(String id, ModelMap model) {
		try {
			departmentService.delete(id);
			model.put("msg", "删除成功！");
			model.put("flag", "remove");
			return IConstant.SUCCESS_PAGE;
		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}
	}

	/**
	 * 查询对象
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=find")
	public String find(String id, ModelMap model,HttpServletRequest request) {
		TPerson person = new TPerson();
		person.setDepartmentid(id);
		List<TPerson> list = personService.list(person);
		PageContext page = PageContext.getContext(request,rowPerPage);//获得分页标签
		page.setPagination(true);//修改分页状态  是否分页
		model.put("list", list);
		model.put("page", page);
		model.put("department", departmentService.find(id));
		return "backpage/base/organization/department/view";
	}

	/**
	 * 更新页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=updateInput")
	public String updateInput(String id, ModelMap model) {
		model.put("department", departmentService.find(id));
		return "backpage/base/organization/department/edit";
	}
	
	
	/**
	 * 新增页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=addInput")
	public String addInput() {
		return "backpage/base/organization/department/edit";
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
