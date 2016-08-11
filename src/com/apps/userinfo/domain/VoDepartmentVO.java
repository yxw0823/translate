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
public class VoDepartmentVO  implements Serializable {
	
    private String departmentid;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String departname;		//注释：  类型：VARCHAR  长度/精度:100 / 0
    private String domain;		//注释：  类型：VARCHAR  长度/精度:50 / 0
    private String pathid;		//注释：  类型：VARCHAR  长度/精度:100 / 0
    private String ordernumber;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String parentid;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String departfrom;		//注释：  类型：VARCHAR  长度/精度:50 / 0
    private Integer usercount;		//注释：  类型：INT  长度/精度:10 / 0
    private String ruleid;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String isdelete;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String updateversion;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String smsserverid;		//注释：  类型：VARCHAR  长度/精度:200 / 0
    private String fullpath;		//注释：部门的完整路径  类型：TEXT  长度/精度:65535 / 0
	
	
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getPathid() {
		return pathid;
	}
	public void setPathid(String pathid) {
		this.pathid = pathid;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getDepartfrom() {
		return departfrom;
	}
	public void setDepartfrom(String departfrom) {
		this.departfrom = departfrom;
	}
	public Integer getUsercount() {
		return usercount;
	}
	public void setUsercount(Integer usercount) {
		this.usercount = usercount;
	}
	public String getRuleid() {
		return ruleid;
	}
	public void setRuleid(String ruleid) {
		this.ruleid = ruleid;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	public String getUpdateversion() {
		return updateversion;
	}
	public void setUpdateversion(String updateversion) {
		this.updateversion = updateversion;
	}
	public String getSmsserverid() {
		return smsserverid;
	}
	public void setSmsserverid(String smsserverid) {
		this.smsserverid = smsserverid;
	}
	public String getFullpath() {
		return fullpath;
	}
	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
	}
	
}
