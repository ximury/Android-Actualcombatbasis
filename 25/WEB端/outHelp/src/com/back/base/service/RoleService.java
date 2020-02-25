package com.back.base.service;

import java.util.List;

import com.back.base.model.TRole;



public interface RoleService {

	public TRole find(String id);

	public TRole save(TRole role);

	public TRole update(TRole role);

	public List<TRole> list(TRole role);

	public TRole saveOrUpdate(TRole role);

	public int delete(String id);

	public List<TRole> selectRelationByLoginId(String loginId);
}
