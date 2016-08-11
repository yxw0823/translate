package com.apps.sys.domain;

import java.util.List;

public class AccountVO
{
	private String account_id;// varchar(36) NOT NULL COMMENT '账户id',
	private String department_id;// varchar(36) DEFAULT NULL COMMENT
	// '部门id',
	private String role_id;// varchar(36) DEFAULT NULL COMMENT
	// '角色id',
	private String position_id;// varchar(36) DEFAULT NULL COMMENT
	// '职位id',
	private String account_name;// varchar(36) DEFAULT NULL COMMENT
	// '账号名',
	private String pass_word;// varchar(36) DEFAULT NULL COMMENT
	// '密码',
	private String user_name;// varchar(36) DEFAULT NULL COMMENT
	// '姓名',
	private String sex;// varchar(1) DEFAULT NULL COMMENT '性别',
	private String tel;// varbinary(36) DEFAULT NULL COMMENT
	// '电话',
	private String is_use;// varchar(1) DEFAULT '1' COMMENT
	// '是否启用：0停用，1启用，默认启用',
	private String create_time;// datetime DEFAULT NULL COMMENT '创建时间',
	private String department_name;
	private String position_name;
	private String role_name;
	private String esn;
	private boolean flag;
	private String clientid;
	private String old_pass_word;
	private String new_pass_word;
	private String loginstatus;
	private String user_icon;
	private String create_time_begin;
	private String create_time_end;
	private String area;
	private List<RoleVO> roles; // 当前登陆人管理的区域集合
	private String roleIds;// 一组以逗号分隔的区域id，表示当前等人的管理区域id

	public List<RoleVO> getRoles()
	{
		return roles;
	}

	public void setRoles(List<RoleVO> roles)
	{
		this.roles = roles;
	}

	public String getRoleIds()
	{
		return roleIds;
	}

	public void setRoleIds(String roleIds)
	{
		this.roleIds = roleIds;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getCreate_time_begin()
	{
		return create_time_begin;
	}

	public void setCreate_time_begin(String create_time_begin)
	{
		this.create_time_begin = create_time_begin;
	}

	public String getCreate_time_end()
	{
		return create_time_end;
	}

	public void setCreate_time_end(String create_time_end)
	{
		this.create_time_end = create_time_end;
	}

	public String getUser_icon()
	{
		return user_icon;
	}

	public void setUser_icon(String user_icon)
	{
		this.user_icon = user_icon;
	}

	public String getLoginstatus()
	{
		return loginstatus;
	}

	public void setLoginstatus(String loginstatus)
	{
		this.loginstatus = loginstatus;
	}

	public String getEsn()
	{
		return esn;
	}

	public void setEsn(String esn)
	{
		this.esn = esn;
	}

	public boolean isFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}

	public String getClientid()
	{
		return clientid;
	}

	public void setClientid(String clientid)
	{
		this.clientid = clientid;
	}

	public String getOld_pass_word()
	{
		return old_pass_word;
	}

	public void setOld_pass_word(String oldPassWord)
	{
		old_pass_word = oldPassWord;
	}

	public String getNew_pass_word()
	{
		return new_pass_word;
	}

	public void setNew_pass_word(String newPassWord)
	{
		new_pass_word = newPassWord;
	}

	private String path;

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getRole_name()
	{
		return role_name;
	}

	public void setRole_name(String role_name)
	{
		this.role_name = role_name;
	}

	public String getDepartment_name()
	{
		return department_name;
	}

	public void setDepartment_name(String department_name)
	{
		this.department_name = department_name;
	}

	public String getPosition_name()
	{
		return position_name;
	}

	public void setPosition_name(String position_name)
	{
		this.position_name = position_name;
	}

	public String getAccount_id()
	{
		return account_id;
	}

	public void setAccount_id(String account_id)
	{
		this.account_id = account_id;
	}

	public String getDepartment_id()
	{
		return department_id;
	}

	public void setDepartment_id(String department_id)
	{
		this.department_id = department_id;
	}

	public String getRole_id()
	{
		return role_id;
	}

	public void setRole_id(String role_id)
	{
		this.role_id = role_id;
	}

	public String getPosition_id()
	{
		return position_id;
	}

	public void setPosition_id(String position_id)
	{
		this.position_id = position_id;
	}

	public String getAccount_name()
	{
		return account_name;
	}

	public void setAccount_name(String account_name)
	{
		this.account_name = account_name;
	}

	public String getPass_word()
	{
		return pass_word;
	}

	public void setPass_word(String pass_word)
	{
		this.pass_word = pass_word;
	}

	public String getUser_name()
	{
		return user_name;
	}

	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	public String getIs_use()
	{
		return is_use;
	}

	public void setIs_use(String is_use)
	{
		this.is_use = is_use;
	}

	public String getCreate_time()
	{
		return create_time;
	}

	public void setCreate_time(String create_time)
	{
		this.create_time = create_time;
	}

}
