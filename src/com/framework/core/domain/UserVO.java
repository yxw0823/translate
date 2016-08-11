package com.framework.core.domain;

public class UserVO {
	private String userid          ;//VARCHAR2(20)                           
	private String g_id          ;//VARCHAR2(20)                           
	private String logon_name     ;//VARCHAR2(60)                           
	private String passwd      ;//VARCHAR2(30)                           
	private String name      ;//VARCHAR2(60) Y                         
	private String mobilephone   ;//VARCHAR2(20) Y                         
	private String email         ;//VARCHAR2(60) Y                         
	private String createtime    ;//TIMESTAMP(6) Y                         
	private String u_type        ;//VARCHAR2(2)  Y                         
	private String lastlogintime ;//TIMESTAMP(6) Y                         
	private String logincount    ;//INTEGER      Y                         
	private String status        ;//VARCHAR2(2)  Y   
	private String org_id;
	private String depId;
	
	private String roles ;
	
	public String getG_id() {
		return g_id;
	}
	public void setG_id(String g_id) {
		this.g_id = g_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getU_type() {
		return u_type;
	}
	public void setU_type(String u_type) {
		this.u_type = u_type;
	}
	public String getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public String getLogincount() {
		return logincount;
	}
	public void setLogincount(String logincount) {
		this.logincount = logincount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getLogon_name() {
		return logon_name;
	}
	public void setLogon_name(String logon_name) {
		this.logon_name = logon_name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
}
