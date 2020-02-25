package com.back.base.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.dao.TRoleMapper;
import com.back.base.model.TRole;
import com.back.base.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Autowired(required = true)
	private TRoleMapper roleMapper;

	public TRole find(String id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public TRole save(TRole role) {
		roleMapper.insert(role);
		logger.info(TRole.class.getName() + "数据插入成功！");
		return role;
	}

	public TRole update(TRole role) {
		roleMapper.updateByPrimaryKeySelective(role);
		logger.info(TRole.class.getName() + "数据更新成功！");
		return role;
	}

	public List<TRole> list(TRole role) {
		return roleMapper.query(role);
	}

	public TRole saveOrUpdate(TRole role) {
		if (role.isUpdateFlag()) {
			update(role);
		} else {
			save(role);
		}
		return role;
	}

	public int delete(String id) {
		int count = roleMapper.deleteByPrimaryKey(id);
		logger.info(TRole.class.getName() + "数据删除成功！");
		return count;
	}

	public List<TRole> selectRelationByLoginId(String loginId) {

		List<TRole> roles = roleMapper.query(new TRole());
		List<TRole> selectedRoles = roleMapper.selectByLoginId(loginId);
		
		for(TRole role : roles){
			role.setOpen(true);
			for(TRole selectedRole : selectedRoles){
				if(role.getId().equals(selectedRole.getId())){
					role.setChecked(true);
				}
			}
		}
		return roles;
	}

}
