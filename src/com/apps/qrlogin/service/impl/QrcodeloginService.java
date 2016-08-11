package com.apps.qrlogin.service.impl;

import com.apps.qrlogin.service.IQrcodeloginService;
import com.apps.qrlogin.domain.QrcodeloginVO;
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
public class QrcodeloginService extends BaseService implements IQrcodeloginService {
	/**
	 *  分页查询
	 * @param page 分页参数
	 * @param qrcodelogin
	 * @return
	 */
	public PageBeanVO<QrcodeloginVO> selectQrcodeloginVOPage(PageBeanVO<QrcodeloginVO> page,QrcodeloginVO qrcodelogin){
		StringBuffer sql=new StringBuffer();
		sql.append("select ");
			sql.append(" Qrcodelogin.user_name ,");
			sql.append(" Qrcodelogin.password ,");
			sql.append(" date_format(Qrcodelogin.createtime,'%Y-%m-%d') createtime , ");
			sql.append(" Qrcodelogin.qrcode ,");
		sql.append(" Qrcodelogin.id ");
		sql.append(" from m_qrcodelogin  Qrcodelogin ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(qrcodelogin.getId())) {
		sql.append(" and  Qrcodelogin.id = :id ");	
		}
		if (!StringUtils.isEmpty(qrcodelogin.getUser_name())) {
		sql.append(" and  Qrcodelogin.user_name = :user_name ");	
		}
		if (!StringUtils.isEmpty(qrcodelogin.getPassword())) {
		sql.append(" and  Qrcodelogin.password = :password ");	
		}
		if (!StringUtils.isEmpty(qrcodelogin.getCreatetime())) {
		sql.append(" and  Qrcodelogin.createtime = :createtime ");	
		}
		if (!StringUtils.isEmpty(qrcodelogin.getQrcode())) {
		sql.append(" and  Qrcodelogin.qrcode = :qrcode ");	
		}
		return this.query4PageExt(page, sql.toString(), qrcodelogin);
	}
	
	/**
	 *   根据ID查看
	 */
	public QrcodeloginVO selectQrcodeloginVOById (QrcodeloginVO qrcodelogin){
		StringBuffer sql=new StringBuffer();
		sql.append("select ");
			sql.append(" Qrcodelogin.id , ");
			sql.append(" Qrcodelogin.user_name , ");
			sql.append(" Qrcodelogin.password , ");
			sql.append(" date_format(Qrcodelogin.createtime,'%Y-%m-%d') createtime , ");
			sql.append(" Qrcodelogin.qrcode  ");
		sql.append(" from m_qrcodelogin Qrcodelogin ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(qrcodelogin.getId())) {
		sql.append(" and  Qrcodelogin.id = :id ");	
		}
		if (!StringUtils.isEmpty(qrcodelogin.getQrcode())) {
			sql.append(" and  Qrcodelogin.qrcode = :qrcode ");	
			}
		return (QrcodeloginVO) this.queryForClass(sql.toString(), QrcodeloginVO.class, qrcodelogin);
	}              
	
	/**
	 *  新增单条数据
	 */
	public boolean saveQrcodeloginVO(QrcodeloginVO qrcodelogin){
		qrcodelogin.setId(GuidUtils.getRandomGUID(true));
		StringBuffer sql=new StringBuffer();
		sql.append("insert into  m_qrcodelogin ( ");
		sql.append(" id , ");
		sql.append(" user_name , ");
		sql.append(" password , ");
		sql.append(" createtime , ");
		sql.append(" qrcode  ");
		sql.append(" ) values ( ");
		sql.append(" :id , ");
		sql.append(" :user_name , ");
		sql.append(" :password , ");
		sql.append(" now() , ");
		sql.append(" :qrcode  ");
		sql.append(" )   ");
		return this.execSql(sql.toString(), qrcodelogin)>0;	
	}
	
	/**
	 *  修改单条数据
	 */
	public boolean updateQrcodeloginVO(QrcodeloginVO qrcodelogin){
		StringBuffer sql=new StringBuffer();
		sql.append(" update m_qrcodelogin set  ");
		sql.append(" user_name = :user_name , ");
		sql.append(" password = :password , ");
		sql.append(" qrcode = :qrcode  ");
		sql.append(" where qrcode = :qrcode ");
		return this.execSql(sql.toString(), qrcodelogin)>0;
	}
	
	/**
	 *  批量删除多条数据
	 */
	public boolean delQrcodeloginVO(QrcodeloginVO qrcodelogin){
		StringBuffer sql=new StringBuffer();
		sql.append(" delete from  m_qrcodelogin where  id = :id  ");
		return this.batchExecSql4List(sql.toString(),extractToList("id",qrcodelogin.getId(),QrcodeloginVO.class)).length>0;
		
	}
}
