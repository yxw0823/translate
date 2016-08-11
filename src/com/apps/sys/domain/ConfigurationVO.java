package com.apps.sys.domain;

public class ConfigurationVO {

	private String configuration_id   ;//  varchar(36) not null comment '配置id',
	private String configuration_value;//  varchar(200) comment '配置值',
	private String create_time        ;//  datetime comment '创建时间',
	private String configuration_code;	// varchar(36) 配置代码
	private String checked;				//
	private String role_id;				
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getConfiguration_code() {
		return configuration_code;
	}
	public void setConfiguration_code(String configuration_code) {
		this.configuration_code = configuration_code;
	}
	public String getConfiguration_id() {
		return configuration_id;
	}
	public void setConfiguration_id(String configuration_id) {
		this.configuration_id = configuration_id;
	}
	public String getConfiguration_value() {
		return configuration_value;
	}
	public void setConfiguration_value(String configuration_value) {
		this.configuration_value = configuration_value;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
}
