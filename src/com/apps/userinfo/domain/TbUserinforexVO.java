package com.apps.userinfo.domain;

import java.io.Serializable;

/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:中盈优创</p>
* @author: yanxuewen
* @version 1.0
*/
public class TbUserinforexVO  implements Serializable {
	
    private String userid;		//注释：  类型：VARCHAR  长度/精度:200 / 0
    private String departmentid;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String domain;		//注释：  类型：VARCHAR  长度/精度:100 / 0
    private String orderid;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String deptpath;		//注释：  类型：VARCHAR  长度/精度:100 / 0
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getDeptpath() {
		return deptpath;
	}
	public void setDeptpath(String deptpath) {
		this.deptpath = deptpath;
	}
	
}
