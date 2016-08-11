package com.apps.sys.domain;

import java.io.Serializable;

public class DepAccTreeVO implements Serializable {
	private String muid;
	private String id;
	private String pid;
	private String text;
	private String nodetype;
	private String esn;
	private String message_id;
	private boolean checked;
	private String clientid;
	
	 
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public String getMuid() {
		return muid;
	}
	public void setMuid(String muid) {
		this.muid = muid;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getEsn() {
		return esn;
	}
	public void setEsn(String esn) {
		this.esn = esn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getNodetype() {
		return nodetype;
	}
	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}
	
}
