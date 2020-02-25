package com.back.base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.back.base.MapParam;
import com.back.base.dao.TPartyMapper;
import com.back.base.model.TParty;
import com.back.base.service.PartyService;

@Service
public class PartyServiceImpl implements PartyService {

	@Autowired(required = true)
	TPartyMapper partyMapper;

	public List<TParty> tree(TParty party) {
		List<TParty> tpartys = new ArrayList<TParty>();
		tpartys = partyMapper.query(party);
		for(TParty tparty : tpartys){
			tparty.setOpen(true);
		}
		return tpartys;
	}
	
	/**
	 * 递归查询出所有的父节点的id
	 */
	public List<String> getAllParentIds(String partyId){
		List<String> partyIds = new ArrayList<String>();
		if(StringUtils.hasText(partyId)){
			partyIds.add(partyId);
			while(null != partyMapper.queryPid(partyId)){
				partyId = partyMapper.queryPid(partyId);
				partyIds.add(partyId);
			}
		}
		return partyIds;
	}

	public List<TParty> departmentReferenceTree(TParty party) {
		List<TParty> tpartys = new ArrayList<TParty>();
		tpartys = partyMapper.query(party);
		for(TParty tparty : tpartys){
			if(tparty.getDiscriminate().equals("company")){
				tparty.setChkDisabled(false);
			}
			tparty.setOpen(true);
		}
		return tpartys;
	}

	public Map<Object, Object> getAllMap(MapParam param) {
		// TODO Auto-generated method stub
		return partyMapper.getAllMap(param);
	}

	public Map<Object, Object> getAllMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Object, Object> getOtherMap(MapParam param) {
		// TODO Auto-generated method stub
		return partyMapper.getOtherMap(param);
	}

	public String getOtherMapHtml(MapParam param,String name,String id,String selVal) {
		// TODO Auto-generated method stub
		Map<Object, Object> map = partyMapper.getOtherMap(param);
		List <TParty> partys = partyMapper.getOther(new TParty());
		
		String str = "<select name='"+name+"' id='"+id+"'   class=\"validate[required,length[0,40]]\" >";
		
		 str += "<option value=''>--查看所有--</option> ";
		for (TParty tpar :partys) {
			
			
			   str += "<option value='"+tpar.getId()+"'  ";
			   
			   if(tpar.getId().equals(selVal)){
				   
				   str +="selected";
			   }
			   
			   str += 	   ">"+tpar.getName()+"</option>";
		}
		str += "</select>";
		
		return str;
	}
	
	public String getOtherMapNameHtml(MapParam param,String name,String id,String selVal) {
		// TODO Auto-generated method stub
		Map<Object, Object> map = partyMapper.getOtherMap(param);
		List <TParty> partys = partyMapper.getOther(new TParty());
		
		String str = "<select name='"+name+"' id='"+id+"'   class=\"validate[required,length[0,40]]\" >";
		
		 str += "<option value=''>--查看所有--</option> ";
		for (TParty tpar :partys) {
			
			
			   str += "<option value='"+tpar.getName()+"'  ";
			   
			   if(tpar.getName().equals(selVal)){
				   
				   str +="selected";
			   }
			   
			   str += 	   ">"+tpar.getName()+"</option>";
		}
		str += "</select>";
		
		return str;
	}

	public List<TParty> getOther(TParty party) {
		// TODO Auto-generated method stub
		return partyMapper.getOther(party);
	}
	
	
}
