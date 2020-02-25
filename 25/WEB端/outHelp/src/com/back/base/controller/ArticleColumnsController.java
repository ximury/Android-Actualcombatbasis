package com.back.base.controller;

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
import com.back.base.model.ArticleColumns;
import com.back.base.model.TResource;
import com.back.base.service.ArticleColumnsService;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;

@Controller
@RequestMapping("/back/articleColumns")
public class ArticleColumnsController extends BaseController {
	@Autowired(required = true)
	private ArticleColumnsService articleColumnsService;

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "cmd=delete")
	public String delete(String pkId, ModelMap model) {
		try {
			articleColumnsService.delete(pkId);
			model.put("msg", "删除成功！");
			model.put("flag", "remove");
			return IConstant.SUCCESS_PAGE;
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
		return "/backpage/base/cms/articleColumns/main";
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
	 * 更新页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "cmd=updateInput")
	public String updateInput(String pkId, String flag, ModelMap model) {
		model.put("articleColumns", articleColumnsService.find(pkId));
		return "backpage/base/cms/articleColumns/edit";
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cmd=addInput")
	public String addInput() {
		return "backpage/base/cms/articleColumns/edit";
	}

	/**
	 * 设置公司基本信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "cmd=saveOrUpdate")
	public String saveOrUpdate(ArticleColumns articleColumns, String setting, ModelMap model) {
		if (!StringUtils.hasText(articleColumns.getPkId())) {
			articleColumns.setPkId(UUID.randomUUID().toString());
			articleColumns.setCreateTime(DateUtil.Time2String(new Date()));
			articleColumns.setUpdateFlag(false);
			articleColumns.setStatus("01");//启动 状态
		} else {
			articleColumns.setUpdateFlag(true);
			articleColumns.setUpdateTime(DateUtil.Time2String(new Date()));
		}
		try {
			articleColumnsService.saveOrUpdate(articleColumns);
			if (articleColumns.isUpdateFlag()) {
				return "redirect:/back/articleColumns.do?cmd=find&pkId=" + articleColumns.getPkId() + "&flag=update";// 修改
			} else {
				return "redirect:/back/articleColumns.do?cmd=find&pkId=" + articleColumns.getPkId() + "&flag=add";// 新增
			}

		} catch (Exception e) {
			model.put("msg", e.getMessage());
			return IConstant.ERROR_PAGE;
		}
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
