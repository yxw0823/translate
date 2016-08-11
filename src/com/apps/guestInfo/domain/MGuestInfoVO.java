package com.apps.guestInfo.domain;

import java.io.Serializable;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company:易象
 * </p>
 * 
 * @author: yanxuewen
 * @version 1.0
 */
public class MGuestInfoVO implements Serializable
{
    private String id; // 注释： 类型：VARCHAR 长度/精度:32 / 0
    private String guest_name; // 注释： 类型：VARCHAR 长度/精度:50 / 0
    private String area_name; // 注释： 类型：VARCHAR 长度/精度:50 / 0
    private String login_name; // 注释： 类型：VARCHAR 长度/精度:50 / 0
    private String long_password; // 注释： 类型：VARCHAR 长度/精度:50 / 0
    private String area_id; // 注释： 类型：VARCHAR 长度/精度:50 / 0
    private String manager_id; // 注释： 类型：VARCHAR 长度/精度:32 / 0
    private String manager_name; // 注释： 类型：VARCHAR 长度/精度:50 / 0
    private String creation_time; // 注释： 类型：DATETIME 长度/精度:19 / 0
    private String creation_people_id; // 注释： 类型：VARCHAR 长度/精度:32 / 0
    private String update_time; // 注释： 类型：DATETIME 长度/精度:19 / 0
    private String update_people_id; // 注释： 类型：VARCHAR 长度/精度:32 / 0
    private String guest_esn; // 注释： 类型：VARCHAR 长度/精度:50 / 0
    private String guest_clientId; // 注释： 类型：VARCHAR 长度/精度:50 / 0
    private String guest_Imsi; // 注释： 类型：VARCHAR 长度/精度:50 / 0
    private String Spread1; // 注释：手机操作系统 类型：VARCHAR 长度/精度:50 / 0
    private String Spread2; // 注释： 注册用户类别 1为通过手机验证用户 0 为未通过手机验证用户 类型：VARCHAR //
                            // 长度/精度:50 / 0
    private String Spread3; // 注释： 类型：VARCHAR 长度/精度:50 / 0
    private String Spread4; // 注释： 类型：VARCHAR 长度/精度:50 / 0
    private String Spread5; // 注释： 类型：VARCHAR 长度/精度:50 / 0
    private boolean flag;// 是否写入esn

    /**
     * @return the flag
     */
    public boolean getFlag()
    {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getGuest_name()
    {
        return guest_name;
    }

    public void setGuest_name(String guest_name)
    {
        this.guest_name = guest_name;
    }

    public String getArea_name()
    {
        return area_name;
    }

    public void setArea_name(String area_name)
    {
        this.area_name = area_name;
    }

    public String getLogin_name()
    {
        return login_name;
    }

    public void setLogin_name(String login_name)
    {
        this.login_name = login_name;
    }

    public String getLong_password()
    {
        return long_password;
    }

    public void setLong_password(String long_password)
    {
        this.long_password = long_password;
    }

    public String getArea_id()
    {
        return area_id;
    }

    public void setArea_id(String area_id)
    {
        this.area_id = area_id;
    }

    public String getManager_id()
    {
        return manager_id;
    }

    public void setManager_id(String manager_id)
    {
        this.manager_id = manager_id;
    }

    public String getManager_name()
    {
        return manager_name;
    }

    public void setManager_name(String manager_name)
    {
        this.manager_name = manager_name;
    }

    public String getCreation_time()
    {
        return creation_time;
    }

    public void setCreation_time(String creation_time)
    {
        this.creation_time = creation_time;
    }

    public String getCreation_people_id()
    {
        return creation_people_id;
    }

    public void setCreation_people_id(String creation_people_id)
    {
        this.creation_people_id = creation_people_id;
    }

    public String getUpdate_time()
    {
        return update_time;
    }

    public void setUpdate_time(String update_time)
    {
        this.update_time = update_time;
    }

    public String getUpdate_people_id()
    {
        return update_people_id;
    }

    public void setUpdate_people_id(String update_people_id)
    {
        this.update_people_id = update_people_id;
    }

    public String getGuest_esn()
    {
        return guest_esn;
    }

    public void setGuest_esn(String guest_esn)
    {
        this.guest_esn = guest_esn;
    }

    public String getGuest_clientId()
    {
        return guest_clientId;
    }

    public void setGuest_clientId(String guest_clientId)
    {
        this.guest_clientId = guest_clientId;
    }

    public String getGuest_Imsi()
    {
        return guest_Imsi;
    }

    public void setGuest_Imsi(String guest_Imsi)
    {
        this.guest_Imsi = guest_Imsi;
    }

    public String getSpread1()
    {
        return Spread1;
    }

    public void setSpread1(String Spread1)
    {
        this.Spread1 = Spread1;
    }

    public String getSpread2()
    {
        return Spread2;
    }

    public void setSpread2(String Spread2)
    {
        this.Spread2 = Spread2;
    }

    public String getSpread3()
    {
        return Spread3;
    }

    public void setSpread3(String Spread3)
    {
        this.Spread3 = Spread3;
    }

    public String getSpread4()
    {
        return Spread4;
    }

    public void setSpread4(String Spread4)
    {
        this.Spread4 = Spread4;
    }

    public String getSpread5()
    {
        return Spread5;
    }

    public void setSpread5(String Spread5)
    {
        this.Spread5 = Spread5;
    }
}
