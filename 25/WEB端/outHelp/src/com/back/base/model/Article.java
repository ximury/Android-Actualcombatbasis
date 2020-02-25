package com.back.base.model;

import java.util.HashMap;

import com.back.base.AbstractEntity;

public class Article extends AbstractEntity{
    private String pkId;

    private String title;

    private String columnsId;

    private String smallUrl;

    private String keywords;

    private Integer orders;

    private String author;

    private String remark;

    private String status;
    
    private String statusMc;

    private String createId;

    private String createTime;

    private String updateId;

    private String updateTime;

    private String content;
    
    private boolean updateFlag;
    

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId == null ? null : pkId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getColumnsId() {
        return columnsId;
    }

    public void setColumnsId(String columnsId) {
        this.columnsId = columnsId == null ? null : columnsId.trim();
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl == null ? null : smallUrl.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	public String getStatusMc() {
		return statusMc;
	}

	public void setStatusMc(String statusMc) {
		this.statusMc = statusMc;
	}

	@Override
	public HashMap<String, String> dicMap() {
		HashMap<String, String>  map = new HashMap<String, String>();
		map.put("status","ARTICLE_STATUS");//需要翻译的字段和对应的字典
		return map;		
	}


}