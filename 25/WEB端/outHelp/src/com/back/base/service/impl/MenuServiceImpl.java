package com.back.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.dao.TAclMapper;
import com.back.base.dao.TLoginRoleMapper;
import com.back.base.dao.TMenuMapper;
import com.back.base.dao.TPersonMapper;
import com.back.base.model.TMenu;
import com.back.base.service.MenuService;
import com.back.base.service.PartyService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired(required = true)
	private TMenuMapper menuMapper;
	
	@Autowired(required = true)
	private TAclMapper aclMapper;	
	
	@Autowired(required = true)
	private TLoginRoleMapper loginRoleMapper;
	
	@Autowired(required = true)
	private TPersonMapper personMapper;
	
	@Autowired(required = true)
	private PartyService partyService;

	public List<TMenu> tree(String loginId,String personId) {
		List<TMenu> menus = menuMapper.selectAll();
		if("admin".equals(loginId)){
			return menus;//超级管理员返回所有菜单权限
		}
		List<TMenu> authorizationMenus = new ArrayList<TMenu>();
		
		/**TAcl tacl = new TAcl();
		tacl.setPrincipalid(loginId);
		tacl.setPrincipaltype("login");
		tacl.setResourcetype("menu");
		List<TAcl> acls = aclMapper.query(tacl);//用户权限
		*/
		
		List<String> principalIds = new ArrayList<String>();
		
		//principalIds.add(loginId);//用户主体
		
		principalIds.addAll(loginRoleMapper.queryRoleIdByLoginId(loginId));//角色主体
		
		//principalIds.addAll(partyService.getAllParentIds(personMapper.selectByPrimaryKey(personId).getPositionid()));//机构主体
		
		List<String> resourceIds = aclMapper.selectResourceIdByPrincipalIds(principalIds);//返回拥有的权限集合
		
		for(TMenu menu : menus){
			for(String resourceId : resourceIds){
				if(menu.getId().equals(resourceId)){
					authorizationMenus.add(menu);
				}
			}
		}
		return authorizationMenus;
	}	
	
	public TMenu find(String id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	public TMenu save(TMenu menu) {
		menuMapper.insert(menu);
		return menu;
	}

	public TMenu update(TMenu menu) {
		menuMapper.updateByPrimaryKeySelective(menu);
		return menu;
	}

	public TMenu saveOrUpdate(TMenu menu) {
		if(menu.isUpdateFlag()){
			update(menu);
		}else{
			save(menu);
		}
		return menu;
	}

	public int delete(String id) {
		int count = menuMapper.deleteByPrimaryKey(id);
		return count;
	}

}
