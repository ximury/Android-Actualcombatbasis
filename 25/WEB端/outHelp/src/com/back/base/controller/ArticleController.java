package com.back.base.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.base.AbstractEntity;
import com.back.base.cache.DictCache;
import com.back.base.model.Article;
import com.back.base.model.ArticleColumns;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.base.service.ArticleColumnsService;
import com.back.base.service.ArticleService;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;

@Controller
@RequestMapping("/back/article")
public class ArticleController extends BaseController {
	@Autowired(required = true)
	private ArticleColumnsService articleColumnsService;
	@Autowired(required = true)
	private ArticleService articleService;

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "cmd=delete")
	public String delete(String pkId,String columnsId, ModelMap model) {
		try {
			articleService.delete(pkId);
			return "redirect:/back/article.do?cmd=list&pkId=" + pkId + "&columnsId="+columnsId;
		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}
	}

	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=main")
	public String main() {
		return "/backpage/base/cms/article/main";
	}

	@RequestMapping(params = "cmd=tree")
	public @ResponseBody
	List<ArticleColumns> tree() {
		List<ArticleColumns> list = articleColumnsService.list(new ArticleColumns());
		return list;
	}

	/**
	 * 查询对象
	 * 
	 * @param pkId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=find")
	public String find(String pkId, ModelMap model) {
		model.put("articleColumns", articleColumnsService.find(pkId));
		return "backpage/base/cms/articleColumns/view";
	}

	/**
	 * 查询对象列表
	 * 
	 * @param pkId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=list")
	public String list(Article article, ModelMap model) {
		model.put("articleColumns", articleColumnsService.find(article.getColumnsId()));
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签

		page.setPagination(true);// 修改分页状态 是否分页

		List<Article> articles = articleService.list(article);

		List<Article> list = new ArrayList<Article>();

		for (Article art : articles) {
			DictCache.dictObj(art);
			list.add(art);
		}
		model.put("list", list);
		model.put("page", page);
		return "backpage/base/cms/article/view";
	}

	/**
	 * 更新页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=updateInput")
	public String updateInput(String pkId, String flag, ModelMap model) {
		model.put("article", articleService.find(pkId));
		return "backpage/base/cms/article/edit";
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=addInput")
	public String addInput() {
		return "backpage/base/cms/article/edit";
	}

	/**
	 * 设置公司基本信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "cmd=saveOrUpdate")
	public String saveOrUpdate(Article article, ModelMap model) {
		if (!StringUtils.hasText(article.getPkId())) {
			article.setPkId(UUID.randomUUID().toString());
			article.setCreateTime(DateUtil.Time2String(new Date()));
			article.setUpdateFlag(false);
			article.setStatus("00");// 保存 状态
		} else {
			article.setUpdateFlag(true);
			article.setUpdateTime(DateUtil.Time2String(new Date()));
		}
		try {
			articleService.saveOrUpdate(article);
			return "redirect:/back/article.do?cmd=list&pkId=" + article.getPkId() + "&columnsId=" + article.getColumnsId();
		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}
	}

	@RequestMapping(params = "cmd=update")
	public @ResponseBody
	boolean update(Article article) {

		articleService.update(article);

		return true;

	}

	@Override
	public String getOperateColumn(List<TResource> re, AbstractEntity ae) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOperateButton(List<TResource> re, String[] params) {
		// TODO Auto-generated method stub
		return null;

	}

}
