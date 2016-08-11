package com.apps.userinfo.service.impl;

import com.apps.userinfo.service.ITbUserinforexService;
import com.apps.userinfo.domain.TbUserinforexVO;
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
public class TbUserinforexService extends BaseService implements ITbUserinforexService {
	/**
	 *  分页查询
	 * @param page 分页参数
	 * @param tbUserinforex
	 * @return
	 */
	public PageBeanVO<TbUserinforexVO> selectTbUserinforexVOPage(PageBeanVO<TbUserinforexVO> page,TbUserinforexVO tbUserinforex){
		StringBuffer sql=new StringBuffer();
		sql.append("select ");
			sql.append(" TbUserinforex.userid ,");
			sql.append(" TbUserinforex.domain ,");
			sql.append(" TbUserinforex.orderid ,");
			sql.append(" TbUserinforex.deptpath ,");
		sql.append(" TbUserinforex.departmentid ");
		sql.append(" from tb_userinforex  TbUserinforex ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(tbUserinforex.getUserid())) {
		sql.append(" and  TbUserinforex.userid = :userid ");	
		}
		if (!StringUtils.isEmpty(tbUserinforex.getDepartmentid())) {
		sql.append(" and  TbUserinforex.departmentid = :departmentid ");	
		}
		if (!StringUtils.isEmpty(tbUserinforex.getDomain())) {
		sql.append(" and  TbUserinforex.domain = :domain ");	
		}
		if (!StringUtils.isEmpty(tbUserinforex.getOrderid())) {
		sql.append(" and  TbUserinforex.orderid = :orderid ");	
		}
		if (!StringUtils.isEmpty(tbUserinforex.getDeptpath())) {
		sql.append(" and  TbUserinforex.deptpath = :deptpath ");	
		}
		return this.query4PageExt(page, sql.toString(), tbUserinforex);
	}
	
	/**
	 *   根据ID查看
	 */
	public TbUserinforexVO selectTbUserinforexVOById (TbUserinforexVO tbUserinforex){
		StringBuffer sql=new StringBuffer();
		sql.append("select ");
			sql.append(" TbUserinforex.userid , ");
			sql.append(" TbUserinforex.departmentid , ");
			sql.append(" TbUserinforex.domain , ");
			sql.append(" TbUserinforex.orderid , ");
			sql.append(" TbUserinforex.deptpath  ");
		sql.append(" from tb_userinforex TbUserinforex ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(tbUserinforex.getDepartmentid())) {
		sql.append(" and  TbUserinforex.departmentid = :departmentid ");	
		}
		return (TbUserinforexVO) this.queryForClass(sql.toString(), TbUserinforexVO.class, tbUserinforex);
	}              
	
	/**
	 *  新增单条数据
	 */
	public boolean saveTbUserinforexVO(TbUserinforexVO tbUserinforex){
		tbUserinforex.setDepartmentid(GuidUtils.getRandomGUID(true));
		StringBuffer sql=new StringBuffer();
		sql.append("insert into  tb_userinforex ( ");
		sql.append(" userid , ");
		sql.append(" departmentid , ");
		sql.append(" domain , ");
		sql.append(" orderid , ");
		sql.append(" deptpath  ");
		sql.append(" ) values ( ");
		sql.append(" :userid , ");
		sql.append(" :departmentid , ");
		sql.append(" :domain , ");
		sql.append(" :orderid , ");
		sql.append(" :deptpath  ");
		sql.append(" )   ");
		return this.execSql(sql.toString(), tbUserinforex)>0;	
	}
	
	/**
	 *  修改单条数据
	 */
	public boolean updateTbUserinforexVO(TbUserinforexVO tbUserinforex){
		StringBuffer sql=new StringBuffer();
		sql.append(" update tb_userinforex set  ");
		sql.append(" userid = :userid , ");
		sql.append(" domain = :domain , ");
		sql.append(" orderid = :orderid , ");
		sql.append(" deptpath = :deptpath  ");
		sql.append(" where departmentid = :departmentid ");
		return this.execSql(sql.toString(), tbUserinforex)>0;
	}
	
	/**
	 *  批量删除多条数据
	 */
	public boolean delTbUserinforexVO(TbUserinforexVO tbUserinforex){
		StringBuffer sql=new StringBuffer();
		sql.append(" delete from  tb_userinforex where  departmentid = :departmentid  ");
		return this.batchExecSql4List(sql.toString(),extractToList("departmentid",tbUserinforex.getDepartmentid(),TbUserinforexVO.class)).length>0;
		
	}
}
