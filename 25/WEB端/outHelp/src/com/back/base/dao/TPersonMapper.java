package com.back.base.dao;

import java.util.List;

import com.back.base.model.TPerson;


public interface TPersonMapper extends BaseMapper<TPerson>{

	List<TPerson> query(TPerson person);


}