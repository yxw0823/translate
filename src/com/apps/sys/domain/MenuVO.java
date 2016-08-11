package com.apps.sys.domain;

import java.io.Serializable;

public class MenuVO implements Serializable
{
	private String menu_id;// varchar(36) NOT NULL COMMENT '菜单id',
	private String menu_name;// varchar(36) DEFAULT NULL COMMENT '菜单名',
	private String menu_url;// varchar(500) DEFAULT NULL COMMENT '菜单url',
	private String menu_img;// varchar(500) DEFAULT NULL COMMENT '菜单图片',
	private String is_use;// varchar(1) DEFAULT '1' COMMENT
	// '是否启用：0停用，1启用，默认启用',
	private String parent_menu_id;// varchar(36) DEFAULT NULL COMMENT '父节点id',
	private String create_time;// datetime DEFAULT NULL COMMENT '创建时间',
	private String menu_code;// varchar(36) DEFAULT NULL COMMENT '菜单代码',
	private String resource; // 返回的复选框
	private String menu_operation_name; // 菜单操作名
	private String operation_code; // 菜单操作代码
	private String resource_id; // 资源id
	private String resource_type;
	private String columnId;
	private String appID;
	private String checked;
	private String checkedOld;
	private String index;
	private String chkDisabled;
	private String isColumn; // 是否为栏目菜单（0，为系统菜单，1为栏目菜单）
	private String comment_id;
	private boolean isHidden;

	public String getComment_id()
	{
		return comment_id;
	}

	public void setComment_id(String comment_id)
	{
		this.comment_id = comment_id;
	}

	public String getIsColumn()
	{
		return isColumn;
	}

	public void setIsColumn(String isColumn)
	{
		this.isColumn = isColumn;
	}

	public String getChkDisabled()
	{
		return chkDisabled;
	}

	public void setChkDisabled(String chkDisabled)
	{
		this.chkDisabled = chkDisabled;
	}

	public String getIndex()
	{
		return index;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}

	public String getChecked()
	{
		return checked;
	}

	public void setChecked(String checked)
	{
		this.checked = checked;
	}

	public String getResource_type()
	{
		return resource_type;
	}

	public void setResource_type(String resource_type)
	{
		this.resource_type = resource_type;
	}

	private String supperCode; // 上级代号

	public String getSupperCode()
	{
		return supperCode;
	}

	public void setSupperCode(String supperCode)
	{
		this.supperCode = supperCode;
	}

	public String getResource_id()
	{
		return resource_id;
	}

	public void setResource_id(String resource_id)
	{
		this.resource_id = resource_id;
	}

	public String getMenu_operation_name()
	{
		return menu_operation_name;
	}

	public void setMenu_operation_name(String menu_operation_name)
	{
		this.menu_operation_name = menu_operation_name;
	}

	public String getOperation_code()
	{
		return operation_code;
	}

	public void setOperation_code(String operation_code)
	{
		this.operation_code = operation_code;
	}

	public String getResource()
	{
		return resource;
	}

	public void setResource(String resource)
	{
		this.resource = resource;
	}

	public String getMenu_code()
	{
		return menu_code;
	}

	public void setMenu_code(String menu_code)
	{
		this.menu_code = menu_code;
	}

	public String getMenu_id()
	{
		return menu_id;
	}

	public void setMenu_id(String menu_id)
	{
		this.menu_id = menu_id;
	}

	public String getMenu_name()
	{
		return menu_name;
	}

	public void setMenu_name(String menu_name)
	{
		this.menu_name = menu_name;
	}

	public String getMenu_url()
	{
		return menu_url;
	}

	public void setMenu_url(String menu_url)
	{
		this.menu_url = menu_url;
	}

	public String getMenu_img()
	{
		return menu_img;
	}

	public void setMenu_img(String menu_img)
	{
		this.menu_img = menu_img;
	}

	public String getIs_use()
	{
		return is_use;
	}

	public void setIs_use(String is_use)
	{
		this.is_use = is_use;
	}

	public String getParent_menu_id()
	{
		return parent_menu_id;
	}

	public void setParent_menu_id(String parent_menu_id)
	{
		this.parent_menu_id = parent_menu_id;
	}

	public String getCreate_time()
	{
		return create_time;
	}

	public void setCreate_time(String create_time)
	{
		this.create_time = create_time;
	}

	/**
	 * @return the columnId
	 */
	public String getColumnId()
	{
		return columnId;
	}

	/**
	 * @param columnId
	 *            the columnId to set
	 */
	public void setColumnId(String columnId)
	{
		this.columnId = columnId;
	}

	/**
	 * @return the appID
	 */
	public String getAppID()
	{
		return appID;
	}

	/**
	 * @param appID
	 *            the appID to set
	 */
	public void setAppID(String appID)
	{
		this.appID = appID;
	}

	public boolean getIsHidden()
	{
		return isHidden;
	}

	public void setIsHidden(boolean isHidden)
	{
		this.isHidden = isHidden;
	}

	public String getCheckedOld()
	{
		return checkedOld;
	}

	public void setCheckedOld(String checkedOld)
	{
		this.checkedOld = checkedOld;
	}
}
