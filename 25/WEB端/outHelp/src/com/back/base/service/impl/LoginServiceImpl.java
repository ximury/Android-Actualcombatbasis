package com.back.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.back.base.dao.TAclMapper;
import com.back.base.dao.TLoginMapper;
import com.back.base.dao.TLoginRoleMapper;
import com.back.base.dao.TPersonMapper;
import com.back.base.dao.TResourceMapper;
import com.back.base.model.TAcl;
import com.back.base.model.TLogin;
import com.back.base.model.TLoginRoleKey;
import com.back.base.model.TPerson;
import com.back.base.model.TResource;
import com.back.base.pageModel.Login;
import com.back.base.service.LoginService;
import com.back.base.utils.DateUtil;

@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);

	@Autowired(required = true)
	private TLoginMapper loginMapper;

	@Autowired(required = true)
	private TLoginRoleMapper loginRoleMapper;

	@Autowired(required = true)
	private TPersonMapper personMapper;

	@Autowired(required = true)
	private TResourceMapper resourceMapper;

	@Autowired(required = true)
	private TAclMapper aclMapper;

	public void save(Login login) {
		TLogin tlogin = new TLogin();
		TPerson tperson = new TPerson();
		BeanUtils.copyProperties(login, tlogin);
		BeanUtils.copyProperties(login, tperson);
		tperson.setDepartmentid(login.getDepartmentId());
		personMapper.insertSelective(tperson);
		loginMapper.insertSelective(tlogin);
		logger.info(TLogin.class.getName() + "新增成功！");
	}

	public List<Login> list(Login login) {
		return loginMapper.query(login);
	}

	public Login find(String id) {
		return loginMapper.selectById(id);
	}

	public void update(Login login) {
		TLogin tlogin = new TLogin();
		TPerson tperson = new TPerson();
		BeanUtils.copyProperties(login, tlogin);
		if (!login.isLoginFlag()) {
			BeanUtils.copyProperties(login, tperson);
			tperson.setDepartmentid(login.getDepartmentId()); 
			personMapper.updateByPrimaryKeySelective(tperson);
		}
		loginMapper.updateByPrimaryKeySelective(tlogin);
		logger.info(TLogin.class.getName() + "更新成功！");
	}

	public void deleteByPrimaryKeys(String ids) {
		loginMapper.deleteByPrimaryKeys(ids.split(","));
		logger.info(TLogin.class.getName() + "批量删除成功！");
	}

	public Login login(Login login) {
		return loginMapper.find(login);
	}

	public void relationLogin(String roleIds, String loginIds) {
		loginRoleMapper.deleteByLoginIds(loginIds.split(","));
		logger.info(TLogin.class.getName() + "删除关联成功！");
		for (String loginId : loginIds.split(",")) {
			TLoginRoleKey loginRole = new TLoginRoleKey();
			loginRole.setLoginid(loginId);
			for (String roleId : roleIds.split(",")) {
				loginRole.setRoleid(roleId);
				loginRoleMapper.insert(loginRole);
			}
		}
		logger.info(TLogin.class.getName() + "关联成功！");
	}

	public List<Login> selectByRoleId(String roleId) {
		return loginMapper.selectByRoleId(roleId);
	}

	public List<Login> selectNoRelationByRoleId(Login login, String roleId) {
		List<Login> allLogin = list(login);
		List<Login> relationLogin = selectByRoleId(roleId);
		if (allLogin.removeAll(relationLogin)) {
			return allLogin;
		}
		return allLogin;
	}

	public void deleteRelation(String ids) {
		loginRoleMapper.deleteByLoginIds(ids.split(","));
	}

	public Login saveOrUpdate(Login login) {
		if (login.isUpdateFlag()) {
			update(login);
		} else {
			save(login);
		}
		return login;
	}

	public void statusChange(String id) {
		TLogin tlogin = loginMapper.selectByPrimaryKey(id);
		if (tlogin.getEnablestate().equals("1")) {
			tlogin.setEnablestate("0");// 禁用
		} else if (tlogin.getEnablestate().equals("0")) {
			tlogin.setEnablestate("1");// 启用
			tlogin.setEnabletime(DateUtil.Time2String(new Date()));
		}
		loginMapper.updateByPrimaryKeySelective(tlogin);

		logger.info(TLogin.class.getName() + "更新成功！");
	}
	
	public void pwdChange(String id,String pwd) {
		TLogin tlogin = loginMapper.selectByPrimaryKey(id);
		tlogin.setPassword(pwd);
		loginMapper.updateByPrimaryKeySelective(tlogin);

		logger.info(TLogin.class.getName() + "更新成功！");
	}

	/**
	 * 根据roleId获取资源权限列表
	 * 
	 * @param roleId
	 * @return
	 */
	public Map resourceList(String roleId) {
		
		Map<String, List> resourceMap = new HashMap<String, List>();
		
		List<TResource> allReource = resourceMapper.selectByPrincipalid(roleId);
		for(TResource tr :allReource){
			
			if(resourceMap.get(tr.getMenuid())==null){
				List childRs = new ArrayList();
				List addButton = new ArrayList();
				List trButton = new ArrayList();
				if(tr.getType().equals("1")){
					addButton.add(tr);
				}else if(tr.getType().equals("0")){
					trButton.add(tr);
				}
				childRs.add(addButton);
				childRs.add(trButton);
				resourceMap.put(tr.getMenuid(), childRs);
			}else{
				List childRs =  new ArrayList();
				List addButton = (List) resourceMap.get(tr.getMenuid()).get(0);
				List trButton = (List) resourceMap.get(tr.getMenuid()).get(1);
				if(tr.getType().equals("1")){
					addButton.add(tr);
				}else if(tr.getType().equals("0")){
					trButton.add(tr);
				}
				childRs.add(addButton);
				childRs.add(trButton);
				resourceMap.remove(tr.getMenuid());
				resourceMap.put(tr.getMenuid(), childRs);
			}
		}
		return resourceMap;
	}
}
