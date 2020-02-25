package com.back.base.dao;

import java.util.HashMap;
import java.util.List;

import com.back.base.MapParam;
import com.back.base.model.TParty;


public interface TPartyMapper extends BaseMapper<TParty>{

	List<TParty> queryByPid(String id);

	List<TParty> query(TParty party);

	String queryPid(String id);
	
	HashMap<Object,Object> getAllMap(MapParam param);
	
	HashMap<Object,Object> getOtherMap(MapParam param);
	
	List<TParty> getOther(TParty party);
	
	
}