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
public class VoUserinforVO  implements Serializable {
	
    private String userid;		//注释：  类型：VARCHAR  长度/精度:200 / 0
    private String shortname;		//注释：  类型：VARCHAR  长度/精度:100 / 0
    private String domain;		//注释：  类型：VARCHAR  长度/精度:100 / 0
    private String truename;		//注释：  类型：VARCHAR  长度/精度:40 / 0
    private String userpassword;		//注释：  类型：VARCHAR  长度/精度:50 / 0
    private String maindeptid;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String sourcefrom;		//注释：  类型：VARCHAR  长度/精度:50 / 0
    private String roleid;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String sex;		//注释：  类型：VARCHAR  长度/精度:4 / 0
    private String telphone;		//注释：  类型：VARCHAR  长度/精度:30 / 0
    private String bp;		//注释：  类型：VARCHAR  长度/精度:30 / 0
    private String mobile;		//注释：  类型：VARCHAR  长度/精度:15 / 0
    private String email;		//注释：  类型：VARCHAR  长度/精度:100 / 0
    private String duty;		//注释：  类型：VARCHAR  长度/精度:1000 / 0
    private String zip;		//注释：  类型：VARCHAR  长度/精度:10 / 0
    private String birthday;		//注释：  类型：VARCHAR  长度/精度:30 / 0
    private String faxnumber;		//注释：  类型：VARCHAR  长度/精度:50 / 0
    private Integer ruleid;		//注释：  类型：INT  长度/精度:10 / 0
    private String faxcode;		//注释：  类型：VARCHAR  长度/精度:50 / 0
    private String smscode;		//注释：  类型：VARCHAR  长度/精度:50 / 0
    private String voipcode;		//注释：  类型：VARCHAR  长度/精度:50 / 0
    private String isdelete;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String updateversion;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private Long updatetimestamp;		//注释：  类型：TIMESTAMP  长度/精度:19 / 0
    private String canloginpc;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String canloginmobile;		//注释：  类型：INT UNSIGNED  长度/精度:10 / 0
    private String smsserverid;		//注释：  类型：VARCHAR  长度/精度:200 / 0
    private String sign;		//注释：  类型：VARCHAR  长度/精度:300 / 0
    private String officepos;		//注释：  类型：VARCHAR  长度/精度:100 / 0
    private String needbuddyapprove;		//注释：true为需要好友验证  类型：VARCHAR  长度/精度:10 / 0
    private String isopen;		//注释：true标识公开个人资料  类型：VARCHAR  长度/精度:10 / 0
    private String cansearch;		//注释：是否允许被其他人搜索到  类型：VARCHAR  长度/精度:10 / 0
    private String isrobot;		//注释：是否为机器人  类型：SMALLINT UNSIGNED  长度/精度:5 / 0
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getMaindeptid() {
		return maindeptid;
	}
	public void setMaindeptid(String maindeptid) {
		this.maindeptid = maindeptid;
	}
	public String getSourcefrom() {
		return sourcefrom;
	}
	public void setSourcefrom(String sourcefrom) {
		this.sourcefrom = sourcefrom;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getBp() {
		return bp;
	}
	public void setBp(String bp) {
		this.bp = bp;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getFaxnumber() {
		return faxnumber;
	}
	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}
	public Integer getRuleid() {
		return ruleid;
	}
	public void setRuleid(Integer ruleid) {
		this.ruleid = ruleid;
	}
	public String getFaxcode() {
		return faxcode;
	}
	public void setFaxcode(String faxcode) {
		this.faxcode = faxcode;
	}
	public String getSmscode() {
		return smscode;
	}
	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}
	public String getVoipcode() {
		return voipcode;
	}
	public void setVoipcode(String voipcode) {
		this.voipcode = voipcode;
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
	public Long getUpdatetimestamp() {
		return updatetimestamp;
	}
	public void setUpdatetimestamp(Long updatetimestamp) {
		this.updatetimestamp = updatetimestamp;
	}
	public String getCanloginpc() {
		return canloginpc;
	}
	public void setCanloginpc(String canloginpc) {
		this.canloginpc = canloginpc;
	}
	public String getCanloginmobile() {
		return canloginmobile;
	}
	public void setCanloginmobile(String canloginmobile) {
		this.canloginmobile = canloginmobile;
	}
	public String getSmsserverid() {
		return smsserverid;
	}
	public void setSmsserverid(String smsserverid) {
		this.smsserverid = smsserverid;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getOfficepos() {
		return officepos;
	}
	public void setOfficepos(String officepos) {
		this.officepos = officepos;
	}
	public String getNeedbuddyapprove() {
		return needbuddyapprove;
	}
	public void setNeedbuddyapprove(String needbuddyapprove) {
		this.needbuddyapprove = needbuddyapprove;
	}
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	public String getCansearch() {
		return cansearch;
	}
	public void setCansearch(String cansearch) {
		this.cansearch = cansearch;
	}
	public String getIsrobot() {
		return isrobot;
	}
	public void setIsrobot(String isrobot) {
		this.isrobot = isrobot;
	}
	
}
