package com.apps.guestInfo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.guestInfo.domain.MGuestInfoVO;
import com.apps.guestInfo.service.IMGuestInfoService;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.service.impl.BaseService;
import com.framework.core.utils.GuidUtils;
import com.framework.core.utils.StringUtils;
import com.framework.wight.security.SecurityContextUtil;

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
@Service
public class MGuestInfoService extends BaseService implements IMGuestInfoService
{
    /**
     * 创建sql语句
     * 
     * @author 颜学文
     * @param String
     * @return sql语句
     */
    public String createSQL(MGuestInfoVO mGuestInfo)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" MGuestInfo.guest_name ,");
        sql.append(" MGuestInfo.area_name ,");
        sql.append(" MGuestInfo.login_name ,");
        sql.append(" MGuestInfo.long_password ,");
        sql.append(" MGuestInfo.area_id ,");
        sql.append(" MGuestInfo.manager_id ,");
        sql.append(" MGuestInfo.manager_name ,");
        sql.append(" date_format(MGuestInfo.creation_time,'%Y-%m-%d') creation_time , ");
        sql.append(" MGuestInfo.creation_people_id ,");
        sql.append(" date_format(MGuestInfo.update_time,'%Y-%m-%d') update_time , ");
        sql.append(" MGuestInfo.update_people_id ,");
        sql.append(" MGuestInfo.guest_esn ,");
        sql.append(" MGuestInfo.guest_clientId ,");
        sql.append(" MGuestInfo.guest_Imsi ,");
        sql.append(" MGuestInfo.Spread1 ,");
        sql.append(" MGuestInfo.Spread2 ,");
        sql.append(" MGuestInfo.Spread3 ,");
        sql.append(" MGuestInfo.Spread4 ,");
        sql.append(" MGuestInfo.Spread5 ,");
        sql.append(" MGuestInfo.id ");
        sql.append(" from m_guest_info  MGuestInfo ");
        sql.append(" where 1=1 ");
        if (!StringUtils.isEmpty(mGuestInfo.getId()))
        {
            sql.append(" and  MGuestInfo.id = :id ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getGuest_name()))
        {
            mGuestInfo.setGuest_name(StringUtils.encodeLike(mGuestInfo.getGuest_name(), 3));
            sql.append(" and  MGuestInfo.guest_name like  :guest_name ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getArea_name()))
        {
            mGuestInfo.setArea_name(StringUtils.encodeLike(mGuestInfo.getArea_name(), 3));
            sql.append(" and  MGuestInfo.area_name like  :area_name ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getLogin_name()))
        {
            sql.append(" and  MGuestInfo.login_name = :login_name ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getLong_password()))
        {
            sql.append(" and  MGuestInfo.long_password = :long_password ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getArea_id()))
        {
            sql.append(" and  MGuestInfo.area_id = :area_id ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getManager_id()))
        {
            sql.append(" and  MGuestInfo.manager_id = :manager_id ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getManager_name()))
        {
            mGuestInfo.setManager_name(StringUtils.encodeLike(mGuestInfo.getManager_name(), 3));
            sql.append(" and  MGuestInfo.manager_name like  :manager_name ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getCreation_time()))
        {
            sql.append(" and  MGuestInfo.creation_time = :creation_time ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getCreation_people_id()))
        {
            sql.append(" and  MGuestInfo.creation_people_id = :creation_people_id ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getUpdate_time()))
        {
            sql.append(" and  MGuestInfo.update_time = :update_time ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getUpdate_people_id()))
        {
            sql.append(" and  MGuestInfo.update_people_id = :update_people_id ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getGuest_esn()))
        {
            mGuestInfo.setGuest_esn(StringUtils.encodeLike(mGuestInfo.getGuest_esn(), 3));
            sql.append(" and  MGuestInfo.guest_esn like  :guest_esn ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getGuest_clientId()))
        {
            sql.append(" and  MGuestInfo.guest_clientId = :guest_clientId ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getGuest_Imsi()))
        {
            mGuestInfo.setGuest_Imsi(StringUtils.encodeLike(mGuestInfo.getGuest_Imsi(), 3));
            sql.append(" and  MGuestInfo.guest_Imsi like  :guest_Imsi ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getSpread1()))
        {
            sql.append(" and  MGuestInfo.Spread1 = :Spread1 ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getSpread2()))
        {
            sql.append(" and  MGuestInfo.Spread2 = :Spread2 ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getSpread3()))
        {
            sql.append(" and  MGuestInfo.Spread3 = :Spread3 ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getSpread4()))
        {
            sql.append(" and  MGuestInfo.Spread4 = :Spread4 ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getSpread5()))
        {
            sql.append(" and  MGuestInfo.Spread5 = :Spread5 ");
        }
        return sql.toString();
    }

    /**
     * 分页查询
     * 
     * @param page 分页参数
     * @param mGuestInfo
     * @return
     */
    public PageBeanVO<MGuestInfoVO> selectMGuestInfoVOPage(PageBeanVO<MGuestInfoVO> page, MGuestInfoVO mGuestInfo)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" MGuestInfo.guest_name ,");
        sql.append(" MGuestInfo.area_name ,");
        sql.append(" MGuestInfo.login_name ,");
        sql.append(" MGuestInfo.long_password ,");
        sql.append(" MGuestInfo.area_id ,");
        sql.append(" MGuestInfo.manager_id ,");
        sql.append(" MGuestInfo.manager_name ,");
        sql.append(" date_format(MGuestInfo.creation_time,'%Y-%m-%d') creation_time , ");
        sql.append(" MGuestInfo.creation_people_id ,");
        sql.append(" date_format(MGuestInfo.update_time,'%Y-%m-%d') update_time , ");
        sql.append(" MGuestInfo.update_people_id ,");
        sql.append(" MGuestInfo.guest_esn ,");
        sql.append(" MGuestInfo.guest_clientId ,");
        sql.append(" MGuestInfo.guest_Imsi ,");
        sql.append(" MGuestInfo.Spread1 ,");
        sql.append(" MGuestInfo.Spread2 ,");
        sql.append(" MGuestInfo.Spread3 ,");
        sql.append(" MGuestInfo.Spread4 ,");
        sql.append(" MGuestInfo.Spread5 ,");
        sql.append(" MGuestInfo.id ");
        sql.append(" from m_guest_info  MGuestInfo ");
        sql.append(" where 1=1 ");
        if (!StringUtils.isEmpty(mGuestInfo.getId()))
        {
            sql.append(" and  MGuestInfo.id = :id ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getGuest_name()))
        {
            mGuestInfo.setGuest_name(StringUtils.encodeLike(mGuestInfo.getGuest_name(), 3));
            sql.append(" and  MGuestInfo.guest_name like  :guest_name ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getArea_name()))
        {
            mGuestInfo.setArea_name(StringUtils.encodeLike(mGuestInfo.getArea_name(), 3));
            sql.append(" and  MGuestInfo.area_name like  :area_name ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getLogin_name()))
        {
            sql.append(" and  MGuestInfo.login_name = :login_name ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getLong_password()))
        {
            sql.append(" and  MGuestInfo.long_password = :long_password ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getArea_id()))
        {
            sql.append(" and  MGuestInfo.area_id = :area_id ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getManager_id()))
        {
            sql.append(" and  MGuestInfo.manager_id = :manager_id ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getManager_name()))
        {
            mGuestInfo.setManager_name(StringUtils.encodeLike(mGuestInfo.getManager_name(), 3));
            sql.append(" and  MGuestInfo.manager_name like  :manager_name ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getCreation_time()))
        {
            sql.append(" and  MGuestInfo.creation_time = :creation_time ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getCreation_people_id()))
        {
            sql.append(" and  MGuestInfo.creation_people_id = :creation_people_id ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getUpdate_time()))
        {
            sql.append(" and  MGuestInfo.update_time = :update_time ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getUpdate_people_id()))
        {
            sql.append(" and  MGuestInfo.update_people_id = :update_people_id ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getGuest_esn()))
        {
            mGuestInfo.setGuest_esn(StringUtils.encodeLike(mGuestInfo.getGuest_esn(), 3));
            sql.append(" and  MGuestInfo.guest_esn like  :guest_esn ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getGuest_clientId()))
        {
            sql.append(" and  MGuestInfo.guest_clientId = :guest_clientId ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getGuest_Imsi()))
        {
            mGuestInfo.setGuest_Imsi(StringUtils.encodeLike(mGuestInfo.getGuest_Imsi(), 3));
            sql.append(" and  MGuestInfo.guest_Imsi like  :guest_Imsi ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getSpread1()))
        {
            sql.append(" and  MGuestInfo.Spread1 = :Spread1 ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getSpread2()))
        {
            sql.append(" and  MGuestInfo.Spread2 = :Spread2 ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getSpread3()))
        {
            sql.append(" and  MGuestInfo.Spread3 = :Spread3 ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getSpread4()))
        {
            sql.append(" and  MGuestInfo.Spread4 = :Spread4 ");
        }
        if (!StringUtils.isEmpty(mGuestInfo.getSpread5()))
        {
            sql.append(" and  MGuestInfo.Spread5 = :Spread5 ");
        }
        return this.query4PageExt(page, sql.toString(), mGuestInfo);
    }

    /**
     * 根据ID查看
     */
    public MGuestInfoVO selectMGuestInfoVOById(MGuestInfoVO mGuestInfo)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" MGuestInfo.id , ");
        sql.append(" MGuestInfo.guest_name , ");
        sql.append(" MGuestInfo.area_name , ");
        sql.append(" MGuestInfo.login_name , ");
        sql.append(" MGuestInfo.long_password , ");
        sql.append(" MGuestInfo.area_id , ");
        sql.append(" MGuestInfo.manager_id , ");
        sql.append(" MGuestInfo.manager_name , ");
        sql.append(" date_format(MGuestInfo.creation_time,'%Y-%m-%d') creation_time , ");
        sql.append(" MGuestInfo.creation_people_id , ");
        sql.append(" date_format(MGuestInfo.update_time,'%Y-%m-%d') update_time , ");
        sql.append(" MGuestInfo.update_people_id , ");
        sql.append(" MGuestInfo.guest_esn , ");
        sql.append(" MGuestInfo.guest_clientId , ");
        sql.append(" MGuestInfo.guest_Imsi , ");
        sql.append(" MGuestInfo.Spread1 , ");
        sql.append(" MGuestInfo.Spread2 , ");
        sql.append(" MGuestInfo.Spread3 , ");
        sql.append(" MGuestInfo.Spread4 , ");
        sql.append(" MGuestInfo.Spread5  ");
        sql.append(" from m_guest_info MGuestInfo ");
        sql.append(" where 1=1 ");
        if (!StringUtils.isEmpty(mGuestInfo.getId()))
        {
            sql.append(" and  MGuestInfo.id = :id ");
        }
        return (MGuestInfoVO) this.queryForClass(sql.toString(), MGuestInfoVO.class, mGuestInfo);
    }

    /**
     * 新增单条数据
     */
    public boolean saveMGuestInfoVO(MGuestInfoVO mGuestInfo)
    {
        mGuestInfo.setId(GuidUtils.getRandomGUID(true));
        mGuestInfo.setLong_password(SecurityContextUtil.encodePassword(mGuestInfo.getLong_password()));
        StringBuffer sql = new StringBuffer();
        sql.append("insert into  m_guest_info ( ");
        sql.append(" id , ");
        sql.append(" guest_name , ");
        sql.append(" area_name , ");
        sql.append(" login_name , ");
        sql.append(" long_password , ");
        sql.append(" area_id , ");
        sql.append(" manager_id , ");
        sql.append(" manager_name , ");
        sql.append(" creation_time , ");
        sql.append(" creation_people_id , ");
        sql.append(" update_time , ");
        sql.append(" update_people_id , ");
        sql.append(" guest_esn , ");
        sql.append(" guest_clientId , ");
        sql.append(" guest_Imsi , ");
        sql.append(" Spread1 , ");
        sql.append(" Spread2 , ");
        sql.append(" Spread3 , ");
        sql.append(" Spread4 , ");
        sql.append(" Spread5  ");
        sql.append(" ) values ( ");
        sql.append(" :id , ");
        sql.append(" :guest_name , ");
        sql.append(" :area_name , ");
        sql.append(" :login_name , ");
        sql.append(" :long_password , ");
        sql.append(" :area_id , ");
        sql.append(" :manager_id , ");
        sql.append(" :manager_name , ");
        sql.append(" :creation_time , ");
        sql.append(" :creation_people_id , ");
        sql.append(" :update_time , ");
        sql.append(" :update_people_id , ");
        sql.append(" :guest_esn , ");
        sql.append(" :guest_clientId , ");
        sql.append(" :guest_Imsi , ");
        sql.append(" :Spread1 , ");
        sql.append(" :Spread2 , ");
        sql.append(" :Spread3 , ");
        sql.append(" :Spread4 , ");
        sql.append(" :Spread5  ");
        sql.append(" )   ");
        return this.execSql(sql.toString(), mGuestInfo) > 0;
    }

    /**
     * 修改单条数据
     */
    public boolean updateMGuestInfoVO(MGuestInfoVO mGuestInfo)
    {
        StringBuffer sql = new StringBuffer();
        mGuestInfo.setLong_password(SecurityContextUtil.encodePassword(mGuestInfo.getLong_password()));
        sql.append(" update m_guest_info set  ");
        sql.append(" guest_name = :guest_name , ");
        sql.append(" area_name = :area_name , ");
        sql.append(" login_name = :login_name , ");
        sql.append(" long_password = :long_password , ");
        sql.append(" area_id = :area_id , ");
        sql.append(" manager_id = :manager_id , ");
        sql.append(" manager_name = :manager_name , ");
        sql.append(" creation_time = :creation_time , ");
        sql.append(" creation_people_id = :creation_people_id , ");
        sql.append(" update_time = :update_time , ");
        sql.append(" update_people_id = :update_people_id , ");
        sql.append(" guest_esn = :guest_esn , ");
        sql.append(" guest_clientId = :guest_clientId , ");
        sql.append(" guest_Imsi = :guest_Imsi , ");
        sql.append(" Spread1 = :Spread1 , ");
        sql.append(" Spread2 = :Spread2 , ");
        sql.append(" Spread3 = :Spread3 , ");
        sql.append(" Spread4 = :Spread4 , ");
        sql.append(" Spread5 = :Spread5  ");
        sql.append(" where id = :id ");
        return this.execSql(sql.toString(), mGuestInfo) > 0;
    }

    /**
     * 批量删除多条数据
     */
    public boolean delMGuestInfoVO(MGuestInfoVO mGuestInfo)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" delete from  m_guest_info where  id = :id  ");
        return this.batchExecSql4List(sql.toString(), extractToList("id", mGuestInfo.getId(), MGuestInfoVO.class)).length > 0;
    }

    /**
     * 验证外宾登陆
     */
    public List<MGuestInfoVO> checkPassword(MGuestInfoVO guestInfo)
    {
        guestInfo.setLong_password(SecurityContextUtil.encodePassword(guestInfo.getLong_password()));
        if ("testuser".equals(guestInfo.getLogin_name()))
        {
            guestInfo.setArea_id("admin");
        }
        StringBuffer sql = new StringBuffer(this.createSQL(guestInfo));
        boolean flag = false;
        boolean isMobile = false;
        if (!StringUtils.isEmpty(guestInfo.getGuest_esn()))
        {
            isMobile = true;
            int result =
                    Integer.parseInt(String.valueOf(this.queryForColumn(
                            "select count(0) from m_guest_info where guest_esn = '" + guestInfo.getGuest_esn()
                                    + "' and guest_clientId='" + guestInfo.getGuest_clientId() + "'", String.class)));
            if (result == 0)
            {// 为0 说明esn号不存在 为第一次手机登录 绑定esn号
                flag = true;
            }
        }
        System.out.println(sql.toString());
        List<MGuestInfoVO> MGuestInfoVOs = this.query(sql.toString(), MGuestInfoVO.class, guestInfo);
        // 默认不需要更新
        if (!MGuestInfoVOs.isEmpty())
            MGuestInfoVOs.get(0).setFlag(true);
        return MGuestInfoVOs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.apps.guestInfo.service.IMGuestInfoService#checkLongName(com.apps.
     * guestInfo.domain.MGuestInfoVO)
     */
    public int checkLongName(MGuestInfoVO guestInfo)
    {
        int result =
                Integer.parseInt(String.valueOf(this.queryForColumn(
                        "select count(0) from m_guest_info where guest_esn = '" + guestInfo.getGuest_esn()
                                + "' and area_id='" + guestInfo.getArea_id() + "' and guest_clientId='"
                                + guestInfo.getGuest_clientId() + "' and login_name='" + guestInfo.getLogin_name()
                                + "'", String.class)));
        return result;
    }
}
