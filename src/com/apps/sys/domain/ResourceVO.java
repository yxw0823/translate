package com.apps.sys.domain;

public class ResourceVO
{
	private String resource_id;// varchar(36) not null comment '资源编号',
	private String role_id;// varchar(36) comment '角色编号',
	private String resource_type;// varchar(1) comment 1,菜单，菜单功能'2,配置
	private String create_time;// datetime comment '创建时间',
	private String resource_type_id;// varchar(36) comment '资源类型编号',
	private String chkDisabled;
	private String comment_id;

	public String getComment_id()
	{
		return comment_id;
	}

	public void setComment_id(String comment_id)
	{
		this.comment_id = comment_id;
	}

	public String getChkDisabled()
	{
		return chkDisabled;
	}

	public void setChkDisabled(String chkDisabled)
	{
		this.chkDisabled = chkDisabled;
	}

	public String getResource_id()
	{
		return resource_id;
	}

	public void setResource_id(String resource_id)
	{
		this.resource_id = resource_id;
	}

	public String getRole_id()
	{
		return role_id;
	}

	public void setRole_id(String role_id)
	{
		this.role_id = role_id;
	}

	public String getResource_type()
	{
		return resource_type;
	}

	public void setResource_type(String resource_type)
	{
		this.resource_type = resource_type;
	}

	public String getCreate_time()
	{
		return create_time;
	}

	public void setCreate_time(String create_time)
	{
		this.create_time = create_time;
	}

	public String getResource_type_id()
	{
		return resource_type_id;
	}

	public void setResource_type_id(String resource_type_id)
	{
		this.resource_type_id = resource_type_id;
	}

}
