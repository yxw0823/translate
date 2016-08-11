package com.framework.core.domain;

import java.io.Serializable;
import java.util.List;

public class MenuPanelItem implements Serializable {
	private String id;
	private String text;
	private String iconCls;
	private boolean leaf;
	private boolean expanded;
	private String url;
	private List<MenuPanelItem> children;
	
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public List<MenuPanelItem> getChildren() {
		return children;
	}
	public void setChildren(List<MenuPanelItem> children) {
		this.children = children;
	}
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
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	 
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
