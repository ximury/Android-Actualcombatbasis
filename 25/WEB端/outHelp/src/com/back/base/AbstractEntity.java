package com.back.base;

import java.util.HashMap;

public abstract class AbstractEntity {
	private String menuId;
	
	private String operateColumn;
	
	public abstract HashMap<String, String> dicMap();

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getOperateColumn() {
		return operateColumn;
	}

	public void setOperateColumn(String operateColumn) {
		this.operateColumn = operateColumn;
	}
	 
	 
}
