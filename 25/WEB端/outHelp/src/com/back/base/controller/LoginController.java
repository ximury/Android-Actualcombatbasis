package com.back.base.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.base.AbstractEntity;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.base.pageModel.Login;
import com.back.base.service.LoginService;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;
import com.back.base.utils.MD5Util;

@Controller
@RequestMapping("/back/login")
public class LoginController extends BaseController{
	
	@Autowired(required = true)
	private LoginService loginService;

	/**
	 * 获取列表（可以带查询条件）
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=list")
	public String list(Login login, ModelMap model,HttpServletRequest request) {
		
		PageContext page = PageContext.getContext(request,rowPerPage);//获得分页标签
		page.setPagination(true);//修改分页状态  是否分页
		
		List<Login> list = loginService.list(login);
		model.put("list", list);
		model.put("login", login);
		model.put("page", page);
		return "backpage/base/login/list";
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=addInput")
	public String addInput() {
		return "backpage/base/login/edit";
	}

	/**
	 * 新增/保存
	 * 
	 * @param login
	 * @return
	 */
	@RequestMapping(params = "cmd=saveOrUpdate")
	public String saveOrUpdate(Login login, ModelMap model) {
		if (!StringUtils.hasText(login.getId())) {
			login.setId(UUID.randomUUID().toString());
//			login.setPassword(MD5Util.md5(login.getUsername()));//MD5 加密
			login.setPassword(login.getUsername());
			
			login.setCreatetime(DateUtil.Time2String(new Date()));
			login.setEnablestate("1");// 默认启用
			login.setUpdateFlag(false);
		} else {
			login.setUpdateFlag(true);
			login.setUpdatetime(DateUtil.Time2String(new Date()));
		}
		try {
			loginService.saveOrUpdate(login);
			return "redirect:/back/login.do?cmd=list";
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
			loginService.deleteByPrimaryKeys(id);
			model.put("msg", "删除成功！");
			return IConstant.SUCCESS_PAGE;
		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}
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
		model.put("login", loginService.find(id));
		return "backpage/base/login/edit";
	}

	/**
	 * 关联用户列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "cmd=relationLoginList")
	public String relationLoginList(String roleId, Login login, ModelMap model) {
		List<Login> list = loginService.selectNoRelationByRoleId(login, roleId);
		model.put("list", list);
		model.put("login", login);
		return "backpage/base/login/reference";
	}

	/**
	 * 关联用户角色
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "cmd=relationLogin")
	public @ResponseBody
	String relationLogin(String roleIds, String loginIds, ModelMap model) {
		try {
			loginService.relationLogin(roleIds, loginIds);
			return IConstant.SUCCESS;
		} catch (Exception e) {
			return IConstant.ERROR;
		}
	}

	/**
	 * 根据角色ID查询关联用户
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(params = "cmd=selectByRoleId")
	public @ResponseBody
	List<Login> selectByRoleId(String roleId) {
		List<Login> list = loginService.selectByRoleId(roleId);
		return list;
	}

	/**
	 * 批量取消关联
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "cmd=deleteRelation")
	public String deleteRelation(String ids, ModelMap model) {
		try {
			loginService.deleteRelation(ids);
			model.put("msg", "删除成功！");
			return IConstant.SUCCESS_PAGE;
		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}
	}

	/**
	 * 添加角色页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=roleRelationInput")
	public String roleRelationInput() {
		return "backpage/base/role/role_relation";
	}

	/**
	 * 部门参照页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=departmentReferenceInput")
	public String departmentReferenceInput() {
		return "backpage/base/organization/department/reference";
	}

	/**
	 * 启用，禁用
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "cmd=statusChange")
	public @ResponseBody
	String statusChange(String id) {
		try {
			loginService.statusChange(id);
			return IConstant.SUCCESS;
		} catch (Exception e) {
			return IConstant.ERROR;
		}
	}
	
	/**
	 * 初始化密码
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "cmd=pwdChange")
	public @ResponseBody
	String pwdChange(String id) {
		try {
			Login rl = loginService.find(id);
//			loginService.pwdChange(id,MD5Util.md5(rl.getUsername()));
			loginService.pwdChange(id,rl.getUsername());
			return IConstant.SUCCESS;
		} catch (Exception e) {
			return IConstant.ERROR;
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
	public String find(String id, ModelMap model) {
		model.put("login", loginService.find(id));
		return "backpage/base/login/view";
	}
	
	
	/**
	 * 修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=passwordInput")
	public String passwordInput() {
		return "backpage/base/login/password";
	}
	
	
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=password")
	public String password(String password,String newpassword,HttpSession session,ModelMap model) {
		Login login = new Login();
//		login.setPassword(MD5Util.md5(password));
		login.setPassword(password);
		login.setUsername(((Login)session.getAttribute("login")).getUsername());
		login = loginService.login(login);
		if(null != login){
//			login.setPassword(MD5Util.md5(newpassword));
			login.setPassword(newpassword);
			loginService.update(login);
			model.put("msg","修改成功！");
		}else{
			model.put("msg","密码不正确！");
		}
		return "backpage/base/login/password";
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
