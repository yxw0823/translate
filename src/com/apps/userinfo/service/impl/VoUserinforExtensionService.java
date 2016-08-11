package com.apps.userinfo.service.impl;

import com.apps.userinfo.service.IVoUserinforExtensionService;
import com.apps.userinfo.domain.VoUserinforExtensionVO;
import com.framework.core.service.impl.BaseService;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.utils.GuidUtils;
import org.springframework.stereotype.Service;
import com.framework.core.utils.StringUtils;
import java.util.List;

/**
* <p>Title: gocom用户扩展表</p>
* <p>Description: gocom用户扩展表</p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:中盈优创</p>
* @author: yanxuewen
* @version 1.0
*/
@Service
public class VoUserinforExtensionService extends BaseService implements IVoUserinforExtensionService {
	/**
	 * gocom用户扩展表 分页查询
	 * @param page 分页参数
	 * @param voUserinforExtension
	 * @return
	 */
	public PageBeanVO<VoUserinforExtensionVO> selectVoUserinforExtensionVOPage(PageBeanVO<VoUserinforExtensionVO> page,VoUserinforExtensionVO voUserinforExtension){
		StringBuffer sql=new StringBuffer();
		sql.append("select ");
			sql.append(" VoUserinforExtension.committee_no ,");
			sql.append(" VoUserinforExtension.isRank ,");
		sql.append(" VoUserinforExtension.userid ");
		sql.append(" from vo_userinfor_extension  VoUserinforExtension ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(voUserinforExtension.getUserid())) {
		sql.append(" and  VoUserinforExtension.userid = :userid ");	
		}
		if (!StringUtils.isEmpty(voUserinforExtension.getCommittee_no())) {
		sql.append(" and  VoUserinforExtension.committee_no = :committee_no ");	
		}
		if (!StringUtils.isEmpty(voUserinforExtension.getIsRank())) {
		sql.append(" and  VoUserinforExtension.isRank = :isRank ");	
		}
		return this.query4PageExt(page, sql.toString(), voUserinforExtension);
	}
	
	/**
	 * gocom用户扩展表  根据ID查看
	 */
	public VoUserinforExtensionVO selectVoUserinforExtensionVOById (VoUserinforExtensionVO voUserinforExtension){
		StringBuffer sql=new StringBuffer();
		sql.append("select ");
			sql.append(" VoUserinforExtension.userid , ");
			sql.append(" VoUserinforExtension.committee_no , ");
			sql.append(" VoUserinforExtension.isRank  ");
		sql.append(" from vo_userinfor_extension VoUserinforExtension ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(voUserinforExtension.getUserid())) {
		sql.append(" and  VoUserinforExtension.userid = :userid ");	
		}
		return (VoUserinforExtensionVO) this.queryForClass(sql.toString(), VoUserinforExtensionVO.class, voUserinforExtension);
	}              
	
	/**
	 * gocom用户扩展表 新增单条数据
	 */
	public boolean saveVoUserinforExtensionVO(VoUserinforExtensionVO voUserinforExtension){
		voUserinforExtension.setUserid(GuidUtils.getRandomGUID(true));
		StringBuffer sql=new StringBuffer();
		sql.append("insert into  vo_userinfor_extension ( ");
		sql.append(" userid , ");
		sql.append(" committee_no , ");
		sql.append(" isRank  ");
		sql.append(" ) values ( ");
		sql.append(" :userid , ");
		sql.append(" :committee_no , ");
		sql.append(" :isRank  ");
		sql.append(" )   ");
		return this.execSql(sql.toString(), voUserinforExtension)>0;	
	}
	
	/**
	 * gocom用户扩展表 修改单条数据
	 */
	public boolean updateVoUserinforExtensionVO(VoUserinforExtensionVO voUserinforExtension){
		StringBuffer sql=new StringBuffer();
		sql.append(" update vo_userinfor_extension set  ");
		sql.append(" committee_no = :committee_no , ");
		sql.append(" isRank = :isRank  ");
		sql.append(" where userid = :userid ");
		return this.execSql(sql.toString(), voUserinforExtension)>0;
	}
	
	/**
	 * gocom用户扩展表 批量删除多条数据
	 */
	public boolean delVoUserinforExtensionVO(VoUserinforExtensionVO voUserinforExtension){
		StringBuffer sql=new StringBuffer();
		sql.append(" delete from  vo_userinfor_extension where  userid = :userid  ");
		return this.batchExecSql4List(sql.toString(),extractToList("userid",voUserinforExtension.getUserid(),VoUserinforExtensionVO.class)).length>0;
		
	}
}
