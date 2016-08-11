package com.framework.core.utils;

public class PostlistVO {
	private String acqins        ;//代理机构标识码BIT32               
	private String forwins       ;//发送机构标识码BIT33               
	private String srvstan       ;//系统跟踪号BIT11                   
	private String transdatetime ;//交易传输时间BIT7                  
	private String trandate      ;//交易日期 YYYY-MM-DD               
	private String cardno        ;//主账号BIT2                        
	private String tranamt       ;//交易金额BIT4                      
	private String trancode      ;//交易类型码BIT3                    
	private String mctmcc        ;//商户类型BIT18                     
	private String devid         ;//终端标识码BIT41                   
	private String mid           ;//受卡方标识码（商户代码）BIT42     
	private String adddata       ;//附加响应数据BIT44                 
	private String revins        ;//接收机构标识码BIT100              
	private String origsrvstan   ;//原始交易的系统跟踪号BIT90.2       
	private String rspcode       ;//交易返回码BIT39                   
	private String issuebrc      ;//发卡机构标识码c                   
	private String midfee        ;//商户手续费                        
	private String issfee        ;//发卡方手续费分成                  
	private String acqfee        ;//收单方手续费                      
	private String description   ;//保留                              
	private String checkflag     ;//商户小票核对标志,0:未核对;1:已核对
	private String brc           ;//商户所属机构代码                  
	private String origtranstime ;//
	public String getAcqins() {
		return acqins;
	}
	public void setAcqins(String acqins) {
		this.acqins = acqins;
	}
	public String getForwins() {
		return forwins;
	}
	public void setForwins(String forwins) {
		this.forwins = forwins;
	}
	public String getSrvstan() {
		return srvstan;
	}
	public void setSrvstan(String srvstan) {
		this.srvstan = srvstan;
	}
	public String getTransdatetime() {
		return transdatetime;
	}
	public void setTransdatetime(String transdatetime) {
		this.transdatetime = transdatetime;
	}
	public String getTrandate() {
		return trandate;
	}
	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getTranamt() {
		return tranamt;
	}
	public void setTranamt(String tranamt) {
		this.tranamt = tranamt;
	}
	public String getTrancode() {
		return trancode;
	}
	public void setTrancode(String trancode) {
		this.trancode = trancode;
	}
	public String getMctmcc() {
		return mctmcc;
	}
	public void setMctmcc(String mctmcc) {
		this.mctmcc = mctmcc;
	}
	public String getDevid() {
		return devid;
	}
	public void setDevid(String devid) {
		this.devid = devid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getAdddata() {
		return adddata;
	}
	public void setAdddata(String adddata) {
		this.adddata = adddata;
	}
	public String getRevins() {
		return revins;
	}
	public void setRevins(String revins) {
		this.revins = revins;
	}
	public String getOrigsrvstan() {
		return origsrvstan;
	}
	public void setOrigsrvstan(String origsrvstan) {
		this.origsrvstan = origsrvstan;
	}
	public String getRspcode() {
		return rspcode;
	}
	public void setRspcode(String rspcode) {
		this.rspcode = rspcode;
	}
	public String getIssuebrc() {
		return issuebrc;
	}
	public void setIssuebrc(String issuebrc) {
		this.issuebrc = issuebrc;
	}
	public String getMidfee() {
		return midfee;
	}
	public void setMidfee(String midfee) {
		this.midfee = midfee;
	}
	public String getIssfee() {
		return issfee;
	}
	public void setIssfee(String issfee) {
		this.issfee = issfee;
	}
	public String getAcqfee() {
		return acqfee;
	}
	public void setAcqfee(String acqfee) {
		this.acqfee = acqfee;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCheckflag() {
		return checkflag;
	}
	public void setCheckflag(String checkflag) {
		this.checkflag = checkflag;
	}
	public String getBrc() {
		return brc;
	}
	public void setBrc(String brc) {
		this.brc = brc;
	}
	public String getOrigtranstime() {
		return origtranstime;
	}
	public void setOrigtranstime(String origtranstime) {
		this.origtranstime = origtranstime;
	}
}
