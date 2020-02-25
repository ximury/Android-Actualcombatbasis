package com.back.base.service;

import java.util.List;
import java.util.Map;

import com.back.base.MapParam;
import com.back.base.model.TParty;



public interface PartyService {

	public List<TParty> tree(TParty party);
	
	public List<String> getAllParentIds(String partyId);

	public List<TParty> departmentReferenceTree(TParty party);
	
	public Map<Object,Object> getAllMap(MapParam param);
	
	public Map<Object,Object> getOtherMap(MapParam param);
	
	public String getOtherMapHtml(MapParam param,String name,String id,String selVal);
	
	public String getOtherMapNameHtml(MapParam param,String name,String id,String selVal);
	
	public List<TParty> getOther(TParty party);
	
}
