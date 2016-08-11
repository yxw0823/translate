package com.apps.userinfo.domain;

import java.io.Serializable;

/**
* <p>Title: gocom用户扩展表</p>
* <p>Description: gocom用户扩展表</p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:中盈优创</p>
* @author: yanxuewen
* @version 1.0
*/
public class VoUserinforExtensionVO  implements Serializable {
	
    private String userid;		//注释：用户id  类型：VARCHAR  长度/精度:200 / 0
    private String committee_no;		//注释：committee_no  类型：VARCHAR  长度/精度:200 / 0
    private Integer isRank;		//注释：是否履职排名（1是0否）  类型：INT  长度/精度:10 / 0
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCommittee_no() {
		return committee_no;
	}
	public void setCommittee_no(String committee_no) {
		this.committee_no = committee_no;
	}
	public Integer getIsRank() {
		return isRank;
	}
	public void setIsRank(Integer isRank) {
		this.isRank = isRank;
	}
	
}
