package com.apps.userinfo.service.impl;

import com.apps.userinfo.service.IVoDepartmentService;
import com.apps.userinfo.domain.VoDepartmentVO;
import com.framework.core.service.impl.BaseService;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.utils.GuidUtils;
import org.springframework.stereotype.Service;
import com.framework.core.utils.StringUtils;
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
public class VoDepartmentService extends BaseService implements IVoDepartmentService {
	/**
	 *  分页查询
	 * @param page 分页参数
	 * @param voDepartment
	 * @return
	 */
	public PageBeanVO<VoDepartmentVO> selectVoDepartmentVOPage(PageBeanVO<VoDepartmentVO> page,VoDepartmentVO voDepartment){
		StringBuffer sql=new StringBuffer();
		sql.append("select ");
			sql.append(" VoDepartment.departname ,");
			sql.append(" VoDepartment.domain ,");
			sql.append(" VoDepartment.pathid ,");
			sql.append(" VoDepartment.ordernumber ,");
			sql.append(" VoDepartment.parentid ,");
			sql.append(" VoDepartment.departfrom ,");
			sql.append(" VoDepartment.usercount ,");
			sql.append(" VoDepartment.ruleid ,");
			sql.append(" VoDepartment.isdelete ,");
			sql.append(" VoDepartment.updateversion ,");
			sql.append(" VoDepartment.smsserverid ,");
			sql.append(" VoDepartment.fullpath ,");
		sql.append(" VoDepartment.departmentid ");
		sql.append(" from vo_department  VoDepartment ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(voDepartment.getDepartmentid())) {
		sql.append(" and  VoDepartment.departmentid = :departmentid ");	
		}
		if (!StringUtils.isEmpty(voDepartment.getDepartname())) {
		sql.append(" and  VoDepartment.departname = :departname ");	
		}
		if (!StringUtils.isEmpty(voDepartment.getDomain())) {
		sql.append(" and  VoDepartment.domain = :domain ");	
		}
		if (!StringUtils.isEmpty(voDepartment.getPathid())) {
		sql.append(" and  VoDepartment.pathid = :pathid ");	
		}
		if (!StringUtils.isEmpty(voDepartment.getOrdernumber())) {
		sql.append(" and  VoDepartment.ordernumber = :ordernumber ");	
		}
		if (!StringUtils.isEmpty(voDepartment.getParentid())) {
		sql.append(" and  VoDepartment.parentid = :parentid ");	
		}
		if (!StringUtils.isEmpty(voDepartment.getDepartfrom())) {
		sql.append(" and  VoDepartment.departfrom = :departfrom ");	
		}
		if (!StringUtils.isEmpty(voDepartment.getUsercount())) {
		sql.append(" and  VoDepartment.usercount = :usercount ");	
		}
		if (!StringUtils.isEmpty(voDepartment.getRuleid())) {
		sql.append(" and  VoDepartment.ruleid = :ruleid ");	
		}
		if (!StringUtils.isEmpty(voDepartment.getIsdelete())) {
		sql.append(" and  VoDepartment.isdelete = :isdelete ");	
		}
		if (!StringUtils.isEmpty(voDepartment.getUpdateversion())) {
		sql.append(" and  VoDepartment.updateversion = :updateversion ");	
		}
		if (!StringUtils.isEmpty(voDepartment.getSmsserverid())) {
		sql.append(" and  VoDepartment.smsserverid = :smsserverid ");	
		}
		if (!StringUtils.isEmpty(voDepartment.getFullpath())) {
		sql.append(" and  VoDepartment.fullpath = :fullpath ");	
		}
		return this.query4PageExt(page, sql.toString(), voDepartment);
	}
	
	/**
	 *   根据ID查看
	 */
	public VoDepartmentVO selectVoDepartmentVOById (VoDepartmentVO voDepartment){
		StringBuffer sql=new StringBuffer();
		sql.append("select ");
			sql.append(" VoDepartment.departmentid , ");
			sql.append(" VoDepartment.departname , ");
			sql.append(" VoDepartment.domain , ");
			sql.append(" VoDepartment.pathid , ");
			sql.append(" VoDepartment.ordernumber , ");
			sql.append(" VoDepartment.parentid , ");
			sql.append(" VoDepartment.departfrom , ");
			sql.append(" VoDepartment.usercount , ");
			sql.append(" VoDepartment.ruleid , ");
			sql.append(" VoDepartment.isdelete , ");
			sql.append(" VoDepartment.updateversion , ");
			sql.append(" VoDepartment.smsserverid , ");
			sql.append(" VoDepartment.fullpath  ");
		sql.append(" from vo_department VoDepartment ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(voDepartment.getDepartmentid())) {
		sql.append(" and  VoDepartment.departmentid = :departmentid ");	
		}
		return (VoDepartmentVO) this.queryForClass(sql.toString(), VoDepartmentVO.class, voDepartment);
	}              
	
	/**
	 *  新增单条数据
	 */
	public boolean saveVoDepartmentVO(VoDepartmentVO voDepartment){
		voDepartment.setDepartmentid(GuidUtils.getRandomGUID(true));
		StringBuffer sql=new StringBuffer();
		sql.append("insert into  vo_department ( ");
		sql.append(" departmentid , ");
		sql.append(" departname , ");
		sql.append(" domain , ");
		sql.append(" pathid , ");
		sql.append(" ordernumber , ");
		sql.append(" parentid , ");
		sql.append(" departfrom , ");
		sql.append(" usercount , ");
		sql.append(" ruleid , ");
		sql.append(" isdelete , ");
		sql.append(" updateversion , ");
		sql.append(" smsserverid , ");
		sql.append(" fullpath  ");
		sql.append(" ) values ( ");
		sql.append(" :departmentid , ");
		sql.append(" :departname , ");
		sql.append(" :domain , ");
		sql.append(" :pathid , ");
		sql.append(" :ordernumber , ");
		sql.append(" :parentid , ");
		sql.append(" :departfrom , ");
		sql.append(" :usercount , ");
		sql.append(" :ruleid , ");
		sql.append(" :isdelete , ");
		sql.append(" :updateversion , ");
		sql.append(" :smsserverid , ");
		sql.append(" :fullpath  ");
		sql.append(" )   ");
		return this.execSql(sql.toString(), voDepartment)>0;	
	}
	
	/**
	 *  修改单条数据
	 */
	public boolean updateVoDepartmentVO(VoDepartmentVO voDepartment){
		StringBuffer sql=new StringBuffer();
		sql.append(" update vo_department set  ");
		sql.append(" departname = :departname , ");
		sql.append(" domain = :domain , ");
		sql.append(" pathid = :pathid , ");
		sql.append(" ordernumber = :ordernumber , ");
		sql.append(" parentid = :parentid , ");
		sql.append(" departfrom = :departfrom , ");
		sql.append(" usercount = :usercount , ");
		sql.append(" ruleid = :ruleid , ");
		sql.append(" isdelete = :isdelete , ");
		sql.append(" updateversion = :updateversion , ");
		sql.append(" smsserverid = :smsserverid , ");
		sql.append(" fullpath = :fullpath  ");
		sql.append(" where departmentid = :departmentid ");
		return this.execSql(sql.toString(), voDepartment)>0;
	}
	
	/**
	 *  批量删除多条数据
	 */
	public boolean delVoDepartmentVO(VoDepartmentVO voDepartment){
		StringBuffer sql=new StringBuffer();
		sql.append(" delete from  vo_department where  departmentid = :departmentid  ");
		return this.batchExecSql4List(sql.toString(),extractToList("departmentid",voDepartment.getDepartmentid(),VoDepartmentVO.class)).length>0;
		
	}
}
