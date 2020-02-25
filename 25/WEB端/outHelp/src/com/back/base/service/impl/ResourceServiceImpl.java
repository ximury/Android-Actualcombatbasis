package com.back.base.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.dao.TMenuMapper;
import com.back.base.dao.TResourceMapper;
import com.back.base.model.TMenu;
import com.back.base.model.TResource;
import com.back.base.service.ResourceService;
import com.back.base.utils.DateUtil;

@Service
public class ResourceServiceImpl implements ResourceService {

	private static final Logger logger = Logger.getLogger(ResourceServiceImpl.class);

	@Autowired(required = true)
	private TMenuMapper menuMapper;

	@Autowired(required = true)
	private TResourceMapper resourceMapper;

	public TResource find(String id) {
		return resourceMapper.selectByPrimaryKey(id);
	}

	public List<TResource> list(TResource resource) {
		return resourceMapper.query(resource);
	}

	public TResource saveOrUpdate(TResource resource) {
		if (resource.isUpdateFlag()) {
			resourceMapper.updateByPrimaryKeySelective(resource);
		} else {
			TResource parentResource = resourceMapper.queryRoot(resource.getMenuid());// 父节点
			if (null == parentResource) {// 无父节点，先插入父节点
				TMenu menu = menuMapper.selectByPrimaryKey(resource.getMenuid());
				parentResource = new TResource();
				parentResource.setId(UUID.randomUUID().toString());
				parentResource.setMenuid(resource.getMenuid());
				parentResource.setName(menu.getName());
				parentResource.setCreatetime(DateUtil.Time2String(new Date()));
				resourceMapper.insertSelective(parentResource);
				resource.setPid(parentResource.getId());
			} else {
				resource.setPid(parentResource.getId());
				resourceMapper.insertSelective(resource);
			}
		}
		return resource;
	}

	public int delete(String id) {
		String pid = resourceMapper.selectByPrimaryKey(id).getPid();
		int count = resourceMapper.deleteByPrimaryKey(id);
		if (resourceMapper.selectSiblingsCount(id) == 0) {// 没有同级节点，删除父节点
			count += resourceMapper.deleteByPrimaryKey(pid);
		}
		logger.info(TResource.class.getName() + "数据删除成功！");
		return count;
	}

}
