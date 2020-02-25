package com.back.base.service;

import java.util.List;

import com.back.base.model.ArticleColumns;



public interface ArticleColumnsService {

	public ArticleColumns find(String id);

	public ArticleColumns save(ArticleColumns role);

	public ArticleColumns update(ArticleColumns role);

	public List<ArticleColumns> list(ArticleColumns role);

	public ArticleColumns saveOrUpdate(ArticleColumns role);

	public int delete(String id);

}
