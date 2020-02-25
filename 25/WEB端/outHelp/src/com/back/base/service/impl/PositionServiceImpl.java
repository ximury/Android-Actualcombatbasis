package com.back.base.service.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back.base.dao.TPartyMapper;
import com.back.base.dao.TPositionMapper;
import com.back.base.model.TParty;
import com.back.base.model.TPosition;
import com.back.base.pageModel.Position;
import com.back.base.service.PositionService;


@Service
public class PositionServiceImpl implements PositionService {

	private static final Logger logger = Logger.getLogger(PositionServiceImpl.class);

	@Autowired(required = true)
	private TPositionMapper positionMapper;
	
	@Autowired(required = true)
	private TPartyMapper partyMapper;
	
	
	public Position save(Position position) {
		TParty tparty = new TParty();
		TPosition tposition = new TPosition();
		
		BeanUtils.copyProperties(position, tparty);
		BeanUtils.copyProperties(position, tposition);
		
		tparty.setDiscriminate("position");
		partyMapper.insertSelective(tparty);
		positionMapper.insertSelective(tposition);
		logger.info(TPosition.class.getName() + "新增成功！");
		return position;
	}

	
	public List<TPosition> list(TPosition position) {
		return positionMapper.selectByCondtion(position);
	}

	
	public int delete(String id) {
		int count = positionMapper.deleteByPrimaryKey(id);
		count += partyMapper.deleteByPrimaryKey(id);
		logger.info(TPosition.class.getName() + "删除成功！");
		return count;
	}

	
	public Position find(String id) {
		return positionMapper.find(id);
	}

	
	public Position update(Position position) {
		
		TParty tparty = new TParty();
		TPosition tposition = new TPosition();
		BeanUtils.copyProperties(position, tparty);
		BeanUtils.copyProperties(position, tposition);
		positionMapper.updateByPrimaryKeySelective(tposition);
		partyMapper.updateByPrimaryKeySelective(tparty);
		logger.info(TPosition.class.getName() + "更新成功！");
		return position;			
	}
	

	
	public Position saveOrUpdate(Position position) {
		if(position.isUpdateFlag()){
			update(position);
		}else{
			save(position);
		}
		return position;
	}		
	

}
