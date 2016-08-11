package com.apps.sys.domain;

public class PositionVO {
	private String position_id 			;//	varchar(36) NOT NULL COMMENT '职位id',
	private String position_name 		;//	varchar(36) DEFAULT NULL COMMENT '职位名',
	private String is_use 					;//	varchar(1) DEFAULT '1' COMMENT '是否启用：0停用，1启用，默认启用',
	private String org_cd 					;//	varchar(36) DEFAULT NULL COMMENT '机构代号',
	private String parent_id 	;//	varchar(36) DEFAULT NULL COMMENT '父节点id',
	private String create_time 			;//	datetime DEFAULT NULL COMMENT '创建时间',
	private String remark 			;//	varchar(200) DEFAULT NULL COMMENT '备注',
	private String order_by;		//int (11,0) 排序 
	
	private String department_id;
	private String relational_id;
	private String supperCode;		//	上级代号
	private String checked;
	
	
	public String getOrder_by() {
		return order_by;
	}
	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getSupperCode() {
		return supperCode;
	}
	public void setSupperCode(String supperCode) {
		this.supperCode = supperCode;
	}
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPosition_id() {
		return position_id;
	}
	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	public String getIs_use() {
		return is_use;
	}
	public void setIs_use(String is_use) {
		this.is_use = is_use;
	}
	public String getOrg_cd() {
		return org_cd;
	}
	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	  
	  
}
