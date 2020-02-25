package com.back.base.service;

import java.util.List;

import com.back.base.model.TMenu;



public interface MenuService {

	public List<TMenu> tree(String loginId,String personId);

	public TMenu find(String id);

	public TMenu save(TMenu menu);

	public TMenu update(TMenu menu);

	public TMenu saveOrUpdate(TMenu menu);

	public int delete(String id);
}
