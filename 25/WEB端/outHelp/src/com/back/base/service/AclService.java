package com.back.base.service;

import java.util.List;

import com.back.base.model.TAcl;
import com.back.base.model.TMenu;
import com.back.base.model.TResource;


public interface AclService {

	public int save(TAcl tacl);

	public List<TMenu> authorizationMenu(TAcl tacl);

	public List<TResource> authorizationResource(TAcl tacl);
	
	
	public List<TResource> authorizationTaskRole(TAcl tacl);
	
	public boolean havePermit(String principalId,String resourceId);
}
