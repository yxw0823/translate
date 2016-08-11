package com.apps.sys.domain;

public class DepartmentVO {
	  private String department_id 		;//	varchar(36) NOT NULL COMMENT '部门id',
	  private String department_name 	;//	varchar(36) DEFAULT NULL COMMENT '部门名称',
	  private String parent_id 	;//	varchar(36) DEFAULT NULL COMMENT '父节点id',
	  private String is_use 			;//	varchar(1) DEFAULT '1' COMMENT '是否启用：0停用，1启用，默认启用',
	  private String remark 			;//	varchar(200) DEFAULT NULL COMMENT '备注',
	  private String org_cd 			;//	varchar(36) DEFAULT NULL COMMENT '机构代号',
	  private String create_time 		;//	datetime DEFAULT NULL COMMENT '创建时间',
	  private String order_by;		//int (11,0) 排序 
	  private String supperCode;		//	上级代号
	  
	  
	public String getSupperCode() {
		return supperCode;
	}
	public void setSupperCode(String supperCode) {
		this.supperCode = supperCode;
	}
	public String getOrder_by() {
		return order_by;
	}
	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getIs_use() {
		return is_use;
	}
	public void setIs_use(String is_use) {
		this.is_use = is_use;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrg_cd() {
		return org_cd;
	}
	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	  
}
