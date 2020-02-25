package com.back.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.back.base.page.PageContext;

public class PageLoaderGrid {
	 /**
     * 总页数
     */
    protected long totalPages;
    /**
     * 显示第几页
     */
    protected long curPage;
    /**
     * 总记录数
     */
    protected long totalRecords;
	//protected List<Map<String,Object>> dataRows = new ArrayList<Map<String,Object>>();
    protected List dataRows = new ArrayList();
	//protected List<Object> newDataRows = new ArrayList<Object>();
    public PageLoaderGrid(PageContext page,List rs){
    	this.setCurPage(page.getCurrentPage());
    	this.setTotalPages(page.getTotalPages());
    	this.setTotalRecords(page.getTotalRows());
    	this.setDataRows(rs);
    }
    
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
	public long getCurPage() {
		return curPage;
	}
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	/*public List<Map<String, Object>> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<Map<String, Object>> dataRows) {
		this.dataRows = dataRows;
	}*/
	/*public List<Object> getNewDataRows() {
		return newDataRows;
	}
	public void setNewDataRows(List<Object> newDataRows) {
		this.newDataRows = newDataRows;
	}*/
	public List getDataRows() {
		return dataRows;
	}
	public void setDataRows(List dataRows) {
		this.dataRows = dataRows;
	}
	
}
