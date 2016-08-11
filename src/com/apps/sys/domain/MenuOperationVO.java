package com.apps.sys.domain;

public class MenuOperationVO {
	private String menu_operation_id 		  ;//	varchar(36) NOT NULL COMMENT '菜单操作id',
	private String menu_id 								;//	varchar(36) DEFAULT NULL COMMENT '菜单id',
	private String menu_operation_name 	  ;//	varchar(50) DEFAULT NULL COMMENT '菜单操作名',
	private String is_use 								;//	varchar(1) DEFAULT '1' COMMENT '是否启用：0停用，1启用，默认启用',
	private String create_time 						;//	datetime DEFAULT NULL COMMENT '创建时间',
	private String operation_code;			//varchar(36) 菜单操作代码
	public String getOperation_code() {
		return operation_code;
	}
	public void setOperation_code(String operation_code) {
		this.operation_code = operation_code;
	}
	public String getMenu_operation_id() {
		return menu_operation_id;
	}
	public void setMenu_operation_id(String menu_operation_id) {
		this.menu_operation_id = menu_operation_id;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_operation_name() {
		return menu_operation_name;
	}
	public void setMenu_operation_name(String menu_operation_name) {
		this.menu_operation_name = menu_operation_name;
	}
	public String getIs_use() {
		return is_use;
	}
	public void setIs_use(String is_use) {
		this.is_use = is_use;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	
}
