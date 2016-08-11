package com.framework.core.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ParmsVO<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2424016134352723919L;
	private Long id;   
	private String parmstr; 
	private Map<T,T> parmMap;
	private List<T> parmList;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParmstr() {
		return parmstr;
	}
	public void setParmstr(String parmstr) {
		this.parmstr = parmstr;
	}
	public Map<T, T> getParmMap() {
		return parmMap;
	}
	public void setParmMap(Map<T, T> parmMap) {
		this.parmMap = parmMap;
	}
	public List<T> getParmList() {
		return parmList;
	}
	public void setParmList(List<T> parmList) {
		this.parmList = parmList;
	}
}
