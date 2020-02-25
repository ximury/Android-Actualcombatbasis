package com.back.base.pageModel;

import java.util.HashMap;
import java.util.Map;

public class Menu {
	
    private String id;

    private String text;

    private String state;
    
    private Map<String,Object> attributes = new HashMap<String,Object>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
    
    
    
    
    
 
}