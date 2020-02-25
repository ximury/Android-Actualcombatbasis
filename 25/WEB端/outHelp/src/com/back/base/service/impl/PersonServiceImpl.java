package com.back.base.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.dao.TPersonMapper;
import com.back.base.model.TCompany;
import com.back.base.model.TPerson;
import com.back.base.service.PersonService;


@Service
public class PersonServiceImpl implements PersonService {
	
	private static final Logger logger = Logger.getLogger(PersonServiceImpl.class);
	
	@Autowired(required = true)
	private TPersonMapper personMapper;
	
	public void save(TPerson person) {
		personMapper.insert(person);
	}

	
	public List<TPerson> list(TPerson person) {
		return personMapper.query(person);
	}

	
	public TPerson find(String id) {
		return personMapper.selectByPrimaryKey(id);
	}

	
	public void update(TPerson person) {
		personMapper.updateByPrimaryKey(person);
	}

	
	public int deleteByPrimaryKey(String id) {
		int count = personMapper.deleteByPrimaryKey(id);
		logger.info(TCompany.class.getName()+"删除成功！");
		return count;
	}


	public TPerson saveOrUpdate(TPerson person) {
		if(person.isUpdateFlag()){
			update(person);
		}else{
			save(person);
		}
		return person;
	}

}
