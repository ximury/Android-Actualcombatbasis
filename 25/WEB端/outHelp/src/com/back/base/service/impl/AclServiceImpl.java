package com.back.base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.back.base.dao.DataDicMapper;
import com.back.base.dao.TAclMapper;
import com.back.base.dao.TMenuMapper;
import com.back.base.dao.TResourceMapper;
import com.back.base.dao.TroletaskMapper;
import com.back.base.model.DataDic;
import com.back.base.model.TAcl;
import com.back.base.model.TMenu;
import com.back.base.model.TResource;
import com.back.base.model.Troletask;
import com.back.base.service.AclService;

@Service
public class AclServiceImpl implements AclService {
	
	
	private static final Logger logger = Logger.getLogger(AclServiceImpl.class);

	@Autowired(required = true)
	private TAclMapper aclMapper;

	@Autowired(required = true)
	private TMenuMapper menuMapper;
	
	@Autowired(required = true)
	private TResourceMapper resourceMapper;	
	
	@Autowired(required = true)
	private DataDicMapper dataDicMapper;	
	
	@Autowired(required = true)
	private TroletaskMapper troletaskMapper;	
	

	public int save(TAcl tacl) {
		int count = 0;
		aclMapper.deleteByPrincipalIdAndResourceType(tacl);
		logger.info("删除授权成功！");
		String resourceids = tacl.getResourceid();
		for (String resourceid : resourceids.split(",")) {
			tacl.setResourceid(resourceid);
			tacl.setId(UUID.randomUUID().toString());
			count += aclMapper.insert(tacl);
		}
		logger.info("重新授权成功！");
		return count;
	}

	public List<TMenu> authorizationMenu(TAcl tacl) {
		List<TMenu> menus = menuMapper.selectAll();
		List<TAcl> acls = aclMapper.select(tacl);
		for (TMenu menu : menus) {
			if (menuMapper.selectByPid(menu.getId()).size() > 0) {// 父节点
				menu.setOpen(true);// 打开
			}
			for (TAcl acl : acls) {
				if (menu.getId().equals(acl.getResourceid())) {//有权限
					menu.setChecked(true);//选中
				}
			}
		}
		return menus;
	}

	public List<TResource> authorizationResource(TAcl tacl) {
		List<TResource> resources = resourceMapper.select(new TResource());
		List<TAcl> acls = aclMapper.select(tacl);
		for (TResource resource : resources) {
				resource.setOpen(true);// 打开
				if(StringUtils.hasText(resource.getType())){
					if("0".equals(resource.getType())){
						resource.setName(resource.getName()+"(列表按钮)");
					}else if("1".equals(resource.getType())){
						resource.setName(resource.getName()+"(功能按钮)");
					}
				}
			for (TAcl acl : acls) {
				if (resource.getId().equals(acl.getResourceid())) {//有权限
					resource.setChecked(true);//选中
				}
			}
		}		
		return resources;
	}
	
	public List<TResource> authorizationTaskRole(TAcl tacl){
		
		DataDic dd = new DataDic();
		dd.setDicName("代办类型");
		List<DataDic> dateDic = dataDicMapper.selectAll(dd);
		List<TResource>resources = new ArrayList<TResource>();
		Troletask tr = new Troletask();
		tr.setRoleId(tacl.getPrincipalid());
		List <Troletask> troleTask = troletaskMapper.selectAll(tr);
		
		tacl.getPrincipalid();
		
		for(DataDic rdd : dateDic){
			TResource  resource = new TResource();
			
			resource.setOpen(true);// 打开
			resource.setName(rdd.getDicValue());
			resource.setId(rdd.getBusCode()+"_"+rdd.getTypeId());			
			for (Troletask tra : troleTask) {
				if (tra.getFunId().equals(rdd.getBusCode())) {//有权限
					resource.setChecked(true);//选中
					break;
				}
			}
			resources.add(resource);
			
		}
		return resources;
	}
	
	public boolean havePermit(String principalId,String resourceId){
		TAcl query = new TAcl();
		query.setPrincipalid(principalId);
		query.setPrincipaltype("role");
		query.setResourceid(resourceId);
		query.setResourcetype("resource");
		List<TAcl> acls = aclMapper.query(query);
		if(null != acls && acls.size()>0){
			return true;
		}
		return false;
	}

}
