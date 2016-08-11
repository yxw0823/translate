package com.framework.core.domain;

import java.io.Serializable;

public class CodesSqlVO implements Serializable{

	private static final long serialVersionUID = -7334372689288332073L;
	private Integer id;
	private String sqlname;
	private String sqlmsg;
	private String sqltype;
	private String remark;


    public CodesSqlVO(Integer id, String sqlname, String sqlmsg,
			String sqltype, String remark) {
		super();
		this.id = id;
		this.sqlname = sqlname;
		this.sqlmsg = sqlmsg;
		this.sqltype = sqltype;
		this.remark = remark;
	}


	public CodesSqlVO() {}
    
  	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSqlname() {
		return sqlname;
	}
	public void setSqlname(String sqlname) {
		this.sqlname = sqlname;
	}
	public String getSqlmsg() {
		return sqlmsg;
	}
	public void setSqlmsg(String sqlmsg) {
		this.sqlmsg = sqlmsg;
	}
	public String getSqltype() {
		return sqltype;
	}
	public void setSqltype(String sqltype) {
		this.sqltype = sqltype;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
