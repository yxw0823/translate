package com.apps.sys.domain;

/**
 * 菜单项实体
 * 
 * @author Administrator
 * 
 */
public class MenuTableVO
{
	private String menu_id;
	private String menu_name;
	private String menu_url;
	private String menu_img;
	private String is_use;
	private String parent_menu_id;
	private String parent_menu_name;
	private String create_time;
	private String menu_code;
	private String index;
	private String newsColCateID;
	private String colTypeID;
	private String serviceColCateID;
	private String parentColID;
	private String columnIntro;
	private String flag;
	private String offLineTime;
	private String iscoding;
	private String isColumn; // 是否为栏目菜单（0，为系统菜单，1为栏目菜单）
	private String columnImg; // 存储栏目图片路径
	private String imgSort; // 图片类别，“1”大图，“2”中图，‘3’小图
	private String listShow; // 列表图片类别，“1”大图，“2”中图，‘3’小图 展示
	private String menubar;

	public String getListShow()
	{
		return listShow;
	}

	public void setListShow(String listShow)
	{
		this.listShow = listShow;
	}

	public String getColumnImg()
	{
		return columnImg;
	}

	public void setColumnImg(String columnImg)
	{
		this.columnImg = columnImg;
	}

	public String getImgSort()
	{
		return imgSort;
	}

	public void setImgSort(String imgSort)
	{
		imgSort = imgSort;
	}

	public String getIsColumn()
	{
		return isColumn;
	}

	public void setIsColumn(String isColumn)
	{
		this.isColumn = isColumn;
	}

	/**
	 * @return the iscoding
	 */
	public String getIscoding()
	{
		return iscoding;
	}

	/**
	 * @param iscoding
	 *            the iscoding to set
	 */
	public void setIscoding(String iscoding)
	{
		this.iscoding = iscoding;
	}

	public String getOffLineTime()
	{
		return offLineTime;
	}

	public void setOffLineTime(String offLineTime)
	{
		this.offLineTime = offLineTime;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	public String getColumnIntro()
	{
		return columnIntro;
	}

	public void setColumnIntro(String columnIntro)
	{
		this.columnIntro = columnIntro;
	}

	public String getNewsColCateID()
	{
		return newsColCateID;
	}

	public void setNewsColCateID(String newsColCateID)
	{
		this.newsColCateID = newsColCateID;
	}

	public String getColTypeID()
	{
		return colTypeID;
	}

	public void setColTypeID(String colTypeID)
	{
		this.colTypeID = colTypeID;
	}

	public String getParentColID()
	{
		return parentColID;
	}

	public void setParentColID(String parentColID)
	{
		this.parentColID = parentColID;
	}

	public String getParent_menu_name()
	{
		return parent_menu_name;
	}

	public void setParent_menu_name(String parent_menu_name)
	{
		this.parent_menu_name = parent_menu_name;
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

	public String getMenu_code()
	{
		return menu_code;
	}

	public void setMenu_code(String menu_code)
	{
		this.menu_code = menu_code;
	}

	public String getIndex()
	{
		return index;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}

	/**
	 * @return the serviceColCateID
	 */
	public String getServiceColCateID()
	{
		return serviceColCateID;
	}

	/**
	 * @param serviceColCateID
	 *            the serviceColCateID to set
	 */
	public void setServiceColCateID(String serviceColCateID)
	{
		this.serviceColCateID = serviceColCateID;
	}

	public String getMenubar()
	{
		return menubar;
	}

	public void setMenubar(String menubar)
	{
		this.menubar = menubar;
	}
}
