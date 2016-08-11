package com.apps.userinfo.service.impl;

import com.apps.userinfo.service.IVoUserinforService;
import com.apps.userinfo.domain.VoUserinforVO;
import com.framework.core.service.impl.BaseService;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.utils.GuidUtils;

import org.springframework.stereotype.Service;

import com.framework.core.utils.StringUtils;
import com.framework.wight.security.SecurityContextUtil;

import java.util.List;

/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:中盈优创</p>
* @author: yanxuewen
* @version 1.0
*/
@Service
public class VoUserinforService extends BaseService implements IVoUserinforService {
	/**
	 *  分页查询
	 * @param page 分页参数
	 * @param voUserinfor
	 * @return
	 */
	public PageBeanVO<VoUserinforVO> selectVoUserinforVOPage(PageBeanVO<VoUserinforVO> page,VoUserinforVO voUserinfor){
		StringBuffer sql=new StringBuffer();
		sql.append("select ");
			sql.append(" VoUserinfor.shortname ,");
			sql.append(" VoUserinfor.domain ,");
			sql.append(" VoUserinfor.truename ,");
			sql.append(" VoUserinfor.userpassword ,");
			sql.append(" VoUserinfor.maindeptid ,");
			sql.append(" VoUserinfor.sourcefrom ,");
			sql.append(" VoUserinfor.roleid ,");
			sql.append(" VoUserinfor.sex ,");
			sql.append(" VoUserinfor.telphone ,");
			sql.append(" VoUserinfor.bp ,");
			sql.append(" VoUserinfor.mobile ,");
			sql.append(" VoUserinfor.email ,");
			sql.append(" VoUserinfor.duty ,");
			sql.append(" VoUserinfor.zip ,");
			sql.append(" VoUserinfor.birthday ,");
			sql.append(" VoUserinfor.faxnumber ,");
			sql.append(" VoUserinfor.ruleid ,");
			sql.append(" VoUserinfor.faxcode ,");
			sql.append(" VoUserinfor.smscode ,");
			sql.append(" VoUserinfor.voipcode ,");
			sql.append(" VoUserinfor.isdelete ,");
			sql.append(" VoUserinfor.updateversion ,");
			sql.append(" VoUserinfor.updatetimestamp ,");
			sql.append(" VoUserinfor.canloginpc ,");
			sql.append(" VoUserinfor.canloginmobile ,");
			sql.append(" VoUserinfor.smsserverid ,");
			sql.append(" VoUserinfor.sign ,");
			sql.append(" VoUserinfor.officepos ,");
			sql.append(" VoUserinfor.needbuddyapprove ,");
			sql.append(" VoUserinfor.isopen ,");
			sql.append(" VoUserinfor.cansearch ,");
			sql.append(" VoUserinfor.isrobot ,");
		sql.append(" VoUserinfor.userid ");
		sql.append(" from vo_userinfor  VoUserinfor ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(voUserinfor.getUserid())) {
		sql.append(" and  VoUserinfor.userid = :userid ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getShortname())) {
		sql.append(" and  VoUserinfor.shortname = :shortname ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getDomain())) {
		sql.append(" and  VoUserinfor.domain = :domain ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getTruename())) {
		sql.append(" and  VoUserinfor.truename = :truename ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getUserpassword())) {
		sql.append(" and  VoUserinfor.userpassword = :userpassword ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getMaindeptid())) {
		sql.append(" and  VoUserinfor.maindeptid = :maindeptid ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getSourcefrom())) {
		sql.append(" and  VoUserinfor.sourcefrom = :sourcefrom ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getRoleid())) {
		sql.append(" and  VoUserinfor.roleid = :roleid ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getSex())) {
		sql.append(" and  VoUserinfor.sex = :sex ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getTelphone())) {
		sql.append(" and  VoUserinfor.telphone = :telphone ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getBp())) {
		sql.append(" and  VoUserinfor.bp = :bp ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getMobile())) {
		sql.append(" and  VoUserinfor.mobile = :mobile ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getEmail())) {
		sql.append(" and  VoUserinfor.email = :email ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getDuty())) {
		sql.append(" and  VoUserinfor.duty = :duty ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getZip())) {
		sql.append(" and  VoUserinfor.zip = :zip ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getBirthday())) {
		sql.append(" and  VoUserinfor.birthday = :birthday ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getFaxnumber())) {
		sql.append(" and  VoUserinfor.faxnumber = :faxnumber ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getRuleid())) {
		sql.append(" and  VoUserinfor.ruleid = :ruleid ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getFaxcode())) {
		sql.append(" and  VoUserinfor.faxcode = :faxcode ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getSmscode())) {
		sql.append(" and  VoUserinfor.smscode = :smscode ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getVoipcode())) {
		sql.append(" and  VoUserinfor.voipcode = :voipcode ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getIsdelete())) {
		sql.append(" and  VoUserinfor.isdelete = :isdelete ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getUpdateversion())) {
		sql.append(" and  VoUserinfor.updateversion = :updateversion ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getUpdatetimestamp())) {
		sql.append(" and  VoUserinfor.updatetimestamp = :updatetimestamp ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getCanloginpc())) {
		sql.append(" and  VoUserinfor.canloginpc = :canloginpc ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getCanloginmobile())) {
		sql.append(" and  VoUserinfor.canloginmobile = :canloginmobile ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getSmsserverid())) {
		sql.append(" and  VoUserinfor.smsserverid = :smsserverid ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getSign())) {
		sql.append(" and  VoUserinfor.sign = :sign ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getOfficepos())) {
		sql.append(" and  VoUserinfor.officepos = :officepos ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getNeedbuddyapprove())) {
		sql.append(" and  VoUserinfor.needbuddyapprove = :needbuddyapprove ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getIsopen())) {
		sql.append(" and  VoUserinfor.isopen = :isopen ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getCansearch())) {
		sql.append(" and  VoUserinfor.cansearch = :cansearch ");	
		}
		if (!StringUtils.isEmpty(voUserinfor.getIsrobot())) {
		sql.append(" and  VoUserinfor.isrobot = :isrobot ");	
		}
		return this.query4PageExt(page, sql.toString(), voUserinfor);
	}
	
	/**
	 *   根据ID查看
	 */
	public VoUserinforVO selectVoUserinforVOById (VoUserinforVO voUserinfor){
		StringBuffer sql=new StringBuffer();
		sql.append("select ");
			sql.append(" VoUserinfor.userid , ");
			sql.append(" VoUserinfor.shortname , ");
			sql.append(" VoUserinfor.domain , ");
			sql.append(" VoUserinfor.truename , ");
			sql.append(" VoUserinfor.userpassword , ");
			sql.append(" VoUserinfor.maindeptid , ");
			sql.append(" VoUserinfor.sourcefrom , ");
			sql.append(" VoUserinfor.roleid , ");
			sql.append(" VoUserinfor.sex , ");
			sql.append(" VoUserinfor.telphone , ");
			sql.append(" VoUserinfor.bp , ");
			sql.append(" VoUserinfor.mobile , ");
			sql.append(" VoUserinfor.email , ");
			sql.append(" VoUserinfor.duty , ");
			sql.append(" VoUserinfor.zip , ");
			sql.append(" VoUserinfor.birthday , ");
			sql.append(" VoUserinfor.faxnumber , ");
			sql.append(" VoUserinfor.ruleid , ");
			sql.append(" VoUserinfor.faxcode , ");
			sql.append(" VoUserinfor.smscode , ");
			sql.append(" VoUserinfor.voipcode , ");
			sql.append(" VoUserinfor.isdelete , ");
			sql.append(" VoUserinfor.updateversion , ");
			sql.append(" VoUserinfor.updatetimestamp , ");
			sql.append(" VoUserinfor.canloginpc , ");
			sql.append(" VoUserinfor.canloginmobile , ");
			sql.append(" VoUserinfor.smsserverid , ");
			sql.append(" VoUserinfor.sign , ");
			sql.append(" VoUserinfor.officepos , ");
			sql.append(" VoUserinfor.needbuddyapprove , ");
			sql.append(" VoUserinfor.isopen , ");
			sql.append(" VoUserinfor.cansearch , ");
			sql.append(" VoUserinfor.isrobot  ");
		sql.append(" from vo_userinfor VoUserinfor ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(voUserinfor.getUserid())) {
		sql.append(" and  VoUserinfor.userid = :userid ");	
		}
		return (VoUserinforVO) this.queryForClass(sql.toString(), VoUserinforVO.class, voUserinfor);
	}              
	
	/**
	 *  新增单条数据
	 */
	public boolean saveVoUserinforVO(VoUserinforVO voUserinfor){
		voUserinfor.setUserid(GuidUtils.getRandomGUID(true));
		StringBuffer sql=new StringBuffer();
		sql.append("insert into  vo_userinfor ( ");
		sql.append(" userid , ");
		sql.append(" shortname , ");
		sql.append(" domain , ");
		sql.append(" truename , ");
		sql.append(" userpassword , ");
		sql.append(" maindeptid , ");
		sql.append(" sourcefrom , ");
		sql.append(" roleid , ");
		sql.append(" sex , ");
		sql.append(" telphone , ");
		sql.append(" bp , ");
		sql.append(" mobile , ");
		sql.append(" email , ");
		sql.append(" duty , ");
		sql.append(" zip , ");
		sql.append(" birthday , ");
		sql.append(" faxnumber , ");
		sql.append(" ruleid , ");
		sql.append(" faxcode , ");
		sql.append(" smscode , ");
		sql.append(" voipcode , ");
		sql.append(" isdelete , ");
		sql.append(" updateversion , ");
		sql.append(" updatetimestamp , ");
		sql.append(" canloginpc , ");
		sql.append(" canloginmobile , ");
		sql.append(" smsserverid , ");
		sql.append(" sign , ");
		sql.append(" officepos , ");
		sql.append(" needbuddyapprove , ");
		sql.append(" isopen , ");
		sql.append(" cansearch , ");
		sql.append(" isrobot  ");
		sql.append(" ) values ( ");
		sql.append(" :userid , ");
		sql.append(" :shortname , ");
		sql.append(" :domain , ");
		sql.append(" :truename , ");
		sql.append(" :userpassword , ");
		sql.append(" :maindeptid , ");
		sql.append(" :sourcefrom , ");
		sql.append(" :roleid , ");
		sql.append(" :sex , ");
		sql.append(" :telphone , ");
		sql.append(" :bp , ");
		sql.append(" :mobile , ");
		sql.append(" :email , ");
		sql.append(" :duty , ");
		sql.append(" :zip , ");
		sql.append(" :birthday , ");
		sql.append(" :faxnumber , ");
		sql.append(" :ruleid , ");
		sql.append(" :faxcode , ");
		sql.append(" :smscode , ");
		sql.append(" :voipcode , ");
		sql.append(" :isdelete , ");
		sql.append(" :updateversion , ");
		sql.append(" :updatetimestamp , ");
		sql.append(" :canloginpc , ");
		sql.append(" :canloginmobile , ");
		sql.append(" :smsserverid , ");
		sql.append(" :sign , ");
		sql.append(" :officepos , ");
		sql.append(" :needbuddyapprove , ");
		sql.append(" :isopen , ");
		sql.append(" :cansearch , ");
		sql.append(" :isrobot  ");
		sql.append(" )   ");
		return this.execSql(sql.toString(), voUserinfor)>0;	
	}
	
	/**
	 *  修改单条数据
	 */
	public boolean updateVoUserinforVO(VoUserinforVO voUserinfor){
		StringBuffer sql=new StringBuffer();
		sql.append(" update vo_userinfor set  ");
		sql.append(" shortname = :shortname , ");
		sql.append(" domain = :domain , ");
		sql.append(" truename = :truename , ");
		sql.append(" userpassword = :userpassword , ");
		sql.append(" maindeptid = :maindeptid , ");
		sql.append(" sourcefrom = :sourcefrom , ");
		sql.append(" roleid = :roleid , ");
		sql.append(" sex = :sex , ");
		sql.append(" telphone = :telphone , ");
		sql.append(" bp = :bp , ");
		sql.append(" mobile = :mobile , ");
		sql.append(" email = :email , ");
		sql.append(" duty = :duty , ");
		sql.append(" zip = :zip , ");
		sql.append(" birthday = :birthday , ");
		sql.append(" faxnumber = :faxnumber , ");
		sql.append(" ruleid = :ruleid , ");
		sql.append(" faxcode = :faxcode , ");
		sql.append(" smscode = :smscode , ");
		sql.append(" voipcode = :voipcode , ");
		sql.append(" isdelete = :isdelete , ");
		sql.append(" updateversion = :updateversion , ");
		sql.append(" updatetimestamp = :updatetimestamp , ");
		sql.append(" canloginpc = :canloginpc , ");
		sql.append(" canloginmobile = :canloginmobile , ");
		sql.append(" smsserverid = :smsserverid , ");
		sql.append(" sign = :sign , ");
		sql.append(" officepos = :officepos , ");
		sql.append(" needbuddyapprove = :needbuddyapprove , ");
		sql.append(" isopen = :isopen , ");
		sql.append(" cansearch = :cansearch , ");
		sql.append(" isrobot = :isrobot  ");
		sql.append(" where userid = :userid ");
		return this.execSql(sql.toString(), voUserinfor)>0;
	}
	
	/**
	 *  批量删除多条数据
	 */
	public boolean delVoUserinforVO(VoUserinforVO voUserinfor){
		StringBuffer sql=new StringBuffer();
		sql.append(" delete from  vo_userinfor where  userid = :userid  ");
		return this.batchExecSql4List(sql.toString(),extractToList("userid",voUserinfor.getUserid(),VoUserinforVO.class)).length>0;
		
	}

	public VoUserinforVO login(VoUserinforVO voUserinfor) {
		voUserinfor.setUserpassword(SecurityContextUtil.encodePassword(voUserinfor.getUserpassword()));
		StringBuffer sql=new StringBuffer();
		sql.append("select ");
			sql.append(" VoUserinfor.userid , ");
			sql.append(" VoUserinfor.shortname , ");
			sql.append(" VoUserinfor.domain , ");
			sql.append(" VoUserinfor.truename , ");
			sql.append(" VoUserinfor.userpassword , ");
			sql.append(" VoUserinfor.maindeptid , ");
			sql.append(" VoUserinfor.sourcefrom , ");
			sql.append(" VoUserinfor.roleid , ");
			sql.append(" VoUserinfor.sex , ");
			sql.append(" VoUserinfor.telphone , ");
			sql.append(" VoUserinfor.bp , ");
			sql.append(" VoUserinfor.mobile , ");
			sql.append(" VoUserinfor.email , ");
			sql.append(" VoUserinfor.duty , ");
			sql.append(" VoUserinfor.zip , ");
			sql.append(" VoUserinfor.birthday , ");
			sql.append(" VoUserinfor.faxnumber , ");
			sql.append(" VoUserinfor.ruleid , ");
			sql.append(" VoUserinfor.faxcode , ");
			sql.append(" VoUserinfor.smscode , ");
			sql.append(" VoUserinfor.voipcode , ");
			sql.append(" VoUserinfor.isdelete , ");
			sql.append(" VoUserinfor.updateversion , ");
			sql.append(" VoUserinfor.updatetimestamp , ");
			sql.append(" VoUserinfor.canloginpc , ");
			sql.append(" VoUserinfor.canloginmobile , ");
			sql.append(" VoUserinfor.smsserverid , ");
			sql.append(" VoUserinfor.sign , ");
			sql.append(" VoUserinfor.officepos , ");
			sql.append(" VoUserinfor.needbuddyapprove , ");
			sql.append(" VoUserinfor.isopen , ");
			sql.append(" VoUserinfor.cansearch , ");
			sql.append(" VoUserinfor.isrobot  ");
			sql.append(" from vo_userinfor VoUserinfor ");
			sql.append(" where 1=1 ");
			sql.append(" and  VoUserinfor.shortname = :shortname ");	
			//sql.append(" and  VoUserinfor.userpassword = :userpassword ");	
		try {
			return (VoUserinforVO) this.queryForClass(sql.toString(), VoUserinforVO.class, voUserinfor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
