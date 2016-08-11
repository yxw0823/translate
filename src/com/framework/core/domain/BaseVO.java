/**
 * 
 */
package com.framework.core.domain;


/**
 * @author GuoJingFu
 *
 */
public class BaseVO {
	private String key;
	private String value;
	private String spacedim;//空间维
	private String timedim;//时间维
	private String remark;//其它
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSpacedim() {
		return spacedim;
	}
	public void setSpacedim(String spacedim) {
		this.spacedim = spacedim;
	}
	public String getTimedim() {
		return timedim;
	}
	public void setTimedim(String timedim) {
		this.timedim = timedim;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
