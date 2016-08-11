package com.apps.sys.domain;

import java.io.Serializable;

public class HotWord implements Serializable {
	private String hot_id;//编号
	private String hot_name;//热词
	private String hot_rows;//查询到的数量
	private String hot_time;//查询时间
	private String hot_source;//来源  1产品  2资讯
	private String hot_count;
	
	public String getHot_count() {
		return hot_count;
	}
	public void setHot_count(String hot_count) {
		this.hot_count = hot_count;
	}
	public String getHot_id() {
		return hot_id;
	}
	public void setHot_id(String hot_id) {
		this.hot_id = hot_id;
	}
	public String getHot_name() {
		return hot_name;
	}
	public void setHot_name(String hot_name) {
		this.hot_name = hot_name;
	}
	public String getHot_rows() {
		return hot_rows;
	}
	public void setHot_rows(String hot_rows) {
		this.hot_rows = hot_rows;
	}
	public String getHot_time() {
		return hot_time;
	}
	public void setHot_time(String hot_time) {
		this.hot_time = hot_time;
	}
	public String getHot_source() {
		return hot_source;
	}
	public void setHot_source(String hot_source) {
		this.hot_source = hot_source;
	}
	
	
}
