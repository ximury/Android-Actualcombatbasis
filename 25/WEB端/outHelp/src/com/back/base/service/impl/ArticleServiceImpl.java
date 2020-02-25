package com.back.base.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.base.dao.ArticleMapper;
import com.back.base.model.Article;
import com.back.base.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	private static final Logger logger = Logger.getLogger(ArticleColumnsServiceImpl.class);

	@Autowired(required = true)
	private ArticleMapper articleMapper;

	public Article find(String id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	public Article save(Article article) {
		articleMapper.insert(article);
		logger.info(Article.class.getName() + "数据插入成功！");
		return article;
	}

	public Article update(Article article) {
		articleMapper.updateByPrimaryKeySelective(article);
		logger.info(Article.class.getName() + "数据更新成功！");
		return article;
	}

	public List<Article> list(Article article) {
		return articleMapper.query(article);
	}

	public Article saveOrUpdate(Article article) {
		if (article.isUpdateFlag()) {
			update(article);
		} else {
			save(article);
		}
		return article;
	}

	public int delete(String id) {
		int count = articleMapper.deleteByPrimaryKey(id);
		logger.info(Article.class.getName() + "数据删除成功！");
		return count;
	}

}
