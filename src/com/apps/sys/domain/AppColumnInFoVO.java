package com.apps.sys.domain;

public class AppColumnInFoVO
{
    private String appColumnInfoId;
    /**
     * 应用ID,党政客户端应用唯一标识，由总社统一编制下发，如：d0006
     */
    private String appID;
    /**
     * 栏目ID,客户端栏目ID，要求唯一，且不可重用。
     * 多级栏目结构：一级栏目：001-999；二级栏目：001001-001999；三级栏目：001001001-001001999
     */
    private String columnId;
    /**
     * 栏目中文名称
     */
    private String columnName;
    /**
     * 栏目状态，取值：1为“上线栏目”、0为“下线栏目”
     */
    private String columnState;
    /**
     * 栏目说明
     */
    private String columnIntro;
    /**
     * 栏目上线时间
     */
    private String columnOnLineTime;
    /**
     * 栏目下线时间
     */
    private String columnOffLineTime;
    private String newsColCateID;
    private String colTypeID;
    private String serviceColCateID;
    private String parentColID;

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

    private String menu_id;

    public String getMenu_id()
    {
        return menu_id;
    }

    public void setMenu_id(String menu_id)
    {
        this.menu_id = menu_id;
    }

    public String getAppID()
    {
        return appID;
    }

    public void setAppID(String appID)
    {
        this.appID = appID;
    }

    public String getColumnId()
    {
        return columnId;
    }

    public void setColumnId(String columnId)
    {
        this.columnId = columnId;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public void setColumnName(String columnName)
    {
        this.columnName = columnName;
    }

    public String getColumnState()
    {
        return columnState;
    }

    public void setColumnState(String columnState)
    {
        this.columnState = columnState;
    }

    public String getColumnIntro()
    {
        return columnIntro;
    }

    public void setColumnIntro(String columnIntro)
    {
        this.columnIntro = columnIntro;
    }

    public String getColumnOnLineTime()
    {
        return columnOnLineTime;
    }

    public void setColumnOnLineTime(String columnOnLineTime)
    {
        this.columnOnLineTime = columnOnLineTime;
    }

    public String getColumnOffLineTime()
    {
        return columnOffLineTime;
    }

    public void setColumnOffLineTime(String columnOffLineTime)
    {
        this.columnOffLineTime = columnOffLineTime;
    }

    public String getAppColumnInfoId()
    {
        return appColumnInfoId;
    }

    public void setAppColumnInfoId(String appColumnInfoId)
    {
        this.appColumnInfoId = appColumnInfoId;
    }

    /**
     * @return the serviceColCateID
     */
    public String getServiceColCateID()
    {
        return serviceColCateID;
    }

    /**
     * @param serviceColCateID the serviceColCateID to set
     */
    public void setServiceColCateID(String serviceColCateID)
    {
        this.serviceColCateID = serviceColCateID;
    }
}
