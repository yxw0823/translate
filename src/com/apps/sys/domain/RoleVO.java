package com.apps.sys.domain;

public class RoleVO implements java.io.Serializable
{
    private String role_id;// varchar(36) NOT NULL COMMENT '角色id',
    private String role_name;// varchar(50) DEFAULT NULL COMMENT '角色名',
    private String remark;// varchar(200) DEFAULT NULL COMMENT '备注',
    private String create_time;// datetime DEFAULT NULL COMMENT '创建时间',
    private String usecount;
    private String area;
    private boolean isAdmin;
    private String appID;
    private String isoBundleId;
    private String AndroidBundleId;
    // 对于县级城市来说 标识其上级市级城市名称以用于天气预报数据查询
    private String parentRoleName;
    // 移动oa下载地址
    private String oaDownLoadUrl;
    // 数字城市网站官方url
    private String szcsUrl;
    // 数字城市网站官方areaCode
    private String areaCode;
    // 扩展字段，可以根据实际情况。单独定义。本次是存放一个图片地址
    private String bgimage;

    public String getBgimage()
    {
        return bgimage;
    }

    public void setBgimage(String bgimage)
    {
        this.bgimage = bgimage;
    }

    /**
     * @return the isAdmin
     */
    public boolean isAdmin()
    {
        return isAdmin;
    }

    /**
     * @param isAdmin
     *            the isAdmin to set
     */
    public void setAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public String getUsecount()
    {
        return usecount;
    }

    public void setUsecount(String usecount)
    {
        this.usecount = usecount;
    }

    public String getRole_id()
    {
        return role_id;
    }

    public void setRole_id(String role_id)
    {
        this.role_id = role_id;
    }

    public String getRole_name()
    {
        return role_name;
    }

    public void setRole_name(String role_name)
    {
        this.role_name = role_name;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getAppID()
    {
        return appID;
    }

    public void setAppID(String appID)
    {
        this.appID = appID;
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
     * @return the isoBundleId
     */
    public String getIsoBundleId()
    {
        return isoBundleId;
    }

    /**
     * @param isoBundleId
     *            the isoBundleId to set
     */
    public void setIsoBundleId(String isoBundleId)
    {
        this.isoBundleId = isoBundleId;
    }

    /**
     * @return the androidBundleId
     */
    public String getAndroidBundleId()
    {
        return AndroidBundleId;
    }

    /**
     * @param androidBundleId
     *            the androidBundleId to set
     */
    public void setAndroidBundleId(String androidBundleId)
    {
        AndroidBundleId = androidBundleId;
    }

    public String getParentRoleName()
    {
        return parentRoleName;
    }

    public void setParentRoleName(String parentRoleName)
    {
        this.parentRoleName = parentRoleName;
    }

    public String getOaDownLoadUrl()
    {
        return oaDownLoadUrl;
    }

    public void setOaDownLoadUrl(String oaDownLoadUrl)
    {
        this.oaDownLoadUrl = oaDownLoadUrl;
    }

    /**
     * @return the szcsUrl
     */
    public String getSzcsUrl()
    {
        return szcsUrl;
    }

    /**
     * @param szcsUrl
     *            the szcsUrl to set
     */
    public void setSzcsUrl(String szcsUrl)
    {
        this.szcsUrl = szcsUrl;
    }

    /**
     * @return the areaCode
     */
    public String getAreaCode()
    {
        return areaCode;
    }

    /**
     * @param areaCode
     *            the areaCode to set
     */
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }
}
