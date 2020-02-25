package com.back.base.service;

import java.util.List;

import com.back.base.model.TPosition;
import com.back.base.pageModel.Position;


public interface PositionService {
	
	public Position save(Position position);

	public List<TPosition> list(TPosition position);

	public int delete(String ids);

	public Position find(String id);

	public Position update(Position position);

	public Position saveOrUpdate(Position position);
	

}
