package com.apps.sys.domain;

public class DepartmentPositionVO {
	private String relational_id    ;//    varchar(36) not null,
	private String department_id    ;//    varchar(36) comment '部门id',
	private String position_id      ;//    varchar(36) comment '职位id',
	public String getRelational_id() {
		return relational_id;
	}
	public void setRelational_id(String relational_id) {
		this.relational_id = relational_id;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getPosition_id() {
		return position_id;
	}
	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}
	
}
