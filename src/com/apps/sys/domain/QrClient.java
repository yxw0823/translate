package com.apps.sys.domain;

import java.io.Serializable;

public class QrClient implements Serializable {
	private String qrid;
	private String qrurl;
	private String qrimg;
	private String create_time;

	public String getQrid() {
		return qrid;
	}

	public void setQrid(String qrid) {
		this.qrid = qrid;
	}

	public String getQrurl() {
		return qrurl;
	}

	public void setQrurl(String qrurl) {
		this.qrurl = qrurl;
	}

	public String getQrimg() {
		return qrimg;
	}

	public void setQrimg(String qrimg) {
		this.qrimg = qrimg;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
