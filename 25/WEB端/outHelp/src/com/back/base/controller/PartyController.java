package com.back.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.base.AbstractEntity;
import com.back.base.model.TParty;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.base.service.PartyService;


@Controller
@RequestMapping("/back/party")
public class PartyController extends BaseController{
	
	@Autowired(required = true)
	private PartyService partyService;
	

	@RequestMapping(params="cmd=tree")
	public @ResponseBody List<TParty> tree(){
		PageContext page = PageContext.getContext(request,rowPerPage);//获得分页标签
		
		page.setPagination(false);//修改分页状态  是否分页
		List<TParty> list = partyService.tree(new TParty());
		return list;
	}
	
	/**
	 * 获取列表（可以带查询条件）
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=list")
	public String list(TParty party, ModelMap model) {
		List<TParty> list = partyService.tree(party);
		model.put("list", list);
		model.put("party", party);
		return "backpage/base/authorize/party_list";
	}
	
	
	/**
	 * 部门参照树
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=departmentReferenceTree")
	public @ResponseBody List<TParty> departmentReferenceTree(TParty party, ModelMap model) {
		List<TParty> list = partyService.departmentReferenceTree(party);
		return list;
	}

	@Override
	public String getOperateColumn(List<TResource> re, AbstractEntity ae) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOperateButton(List<TResource> re,String[] params) {
		// TODO Auto-generated method stub
		return null;
	}	
}
