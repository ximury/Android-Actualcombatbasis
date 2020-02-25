package com.back.base.service.impl;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.dao.TCompanyMapper;
import com.back.base.dao.TPartyMapper;
import com.back.base.model.TCompany;
import com.back.base.model.TParty;
import com.back.base.pageModel.Company;
import com.back.base.service.CompanyService;



@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	private static final Logger logger = Logger.getLogger(CompanyServiceImpl.class);
	
	@Autowired(required = true)
	private TCompanyMapper companyMapper;
	@Autowired(required = true)
	private TPartyMapper partyMapper;

	public Company save(Company company) {
		TParty tparty = new TParty();
		TCompany tcompany = new TCompany();
		
		BeanUtils.copyProperties(company, tparty);
		BeanUtils.copyProperties(company, tcompany);
		
		tparty.setDiscriminate("company");
		partyMapper.insertSelective(tparty);
		companyMapper.insertSelective(tcompany);
		logger.info(TCompany.class.getName()+"新增成功！");
		return company;
	}


	
	public List<Company> list(Company company) {
		return companyMapper.query(company);
	}


	
	public Company find(String id) {
		return companyMapper.find(id);
	}
	
	
	public Company findRoot() {
		return companyMapper.findRoot();
	}
		


	
	public Company update(Company company) {
		TParty tparty = new TParty();
		TCompany tcompany = new TCompany();
		BeanUtils.copyProperties(company, tparty);
		BeanUtils.copyProperties(company, tcompany);
		companyMapper.updateByPrimaryKeySelective(tcompany);
		partyMapper.updateByPrimaryKeySelective(tparty);
		logger.info(TCompany.class.getName()+"更新成功！");
		return company;
	}

	
	public Company saveOrUpdate(Company company) {
		if(company.isUpdateFlag()){
			update(company);
		}else{
			save(company);
		}
		return company;
	}

	public int delete(String id) {
		int count = companyMapper.deleteByPrimaryKey(id);
		count += partyMapper.deleteByPrimaryKey(id);
		logger.info(TCompany.class.getName()+"删除成功！");
		return count;
	}
	

}
