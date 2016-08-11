package com.apps.qrlogin.domain;

import java.io.Serializable;

/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:中盈优创</p>
* @author: yanxuewen
* @version 1.0
*/
public class QrcodeloginVO  implements Serializable {
	
    private String id;		//注释：  类型：VARCHAR  长度/精度:36 / 0
    private String user_name;		//注释：  类型：VARCHAR  长度/精度:100 / 0
    private String password;		//注释：  类型：VARCHAR  长度/精度:100 / 0
    private java.util.Date createtime;		//注释：  类型：DATETIME  长度/精度:19 / 0
    private String qrcode;		//注释：  类型：VARCHAR  长度/精度:36 / 0
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public java.util.Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	
}
