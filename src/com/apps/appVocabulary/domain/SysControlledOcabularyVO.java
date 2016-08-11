package com.apps.appVocabulary.domain;

import java.io.Serializable;

/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:易象</p>
* @author: yanxuewen
* @version 1.0
*/
public class SysControlledOcabularyVO  implements Serializable {
	
    private String ocabulary_id;		//注释：主键  类型：VARCHAR  长度/精度:37 / 0
    private String operatetype_id;		//注释：词表内容项ID  类型：VARCHAR  长度/精度:20 / 0
    private String content;		//注释：词表内容项名称  类型：VARCHAR  长度/精度:100 / 0
    private String coding;		//注释：编码  类型：VARCHAR  长度/精度:20 / 0
    private String state;		//注释：state  类型：VARCHAR  长度/精度:2 / 0
    private String pid;		//注释：父ID  类型：VARCHAR  长度/精度:36 / 0
    private String Spread1;		//注释：  类型：VARCHAR  长度/精度:50 / 0
    private String Spread2;		//注释：  类型：VARCHAR  长度/精度:50 / 0
    private String Spread3;		//注释：  类型：VARCHAR  长度/精度:50 / 0
    private String Spread4;		//注释：  类型：VARCHAR  长度/精度:50 / 0
    private String Spread5;		//注释：  类型：VARCHAR  长度/精度:50 / 0
	
	
	public String getOcabulary_id() {
		return ocabulary_id;
	}
	public void setOcabulary_id(String ocabulary_id) {
		this.ocabulary_id = ocabulary_id;
	}
	public String getOperatetype_id() {
		return operatetype_id;
	}
	public void setOperatetype_id(String operatetype_id) {
		this.operatetype_id = operatetype_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCoding() {
		return coding;
	}
	public void setCoding(String coding) {
		this.coding = coding;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getSpread1() {
		return Spread1;
	}
	public void setSpread1(String Spread1) {
		this.Spread1 = Spread1;
	}
	public String getSpread2() {
		return Spread2;
	}
	public void setSpread2(String Spread2) {
		this.Spread2 = Spread2;
	}
	public String getSpread3() {
		return Spread3;
	}
	public void setSpread3(String Spread3) {
		this.Spread3 = Spread3;
	}
	public String getSpread4() {
		return Spread4;
	}
	public void setSpread4(String Spread4) {
		this.Spread4 = Spread4;
	}
	public String getSpread5() {
		return Spread5;
	}
	public void setSpread5(String Spread5) {
		this.Spread5 = Spread5;
	}
	
}
