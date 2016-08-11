package com.apps.qrlogin.service;

import com.apps.qrlogin.domain.QrcodeloginVO;
import com.framework.core.domain.PageBeanVO;
import java.util.List;
/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:中盈优创</p>
* @author: yanxuewen
* @version 1.0
*/
public interface IQrcodeloginService {
	/**
	 *  分页查询
	 */
	public PageBeanVO<QrcodeloginVO> selectQrcodeloginVOPage(PageBeanVO<QrcodeloginVO> page,QrcodeloginVO qrcodelogin);
	/**
	 *   根据ID查看
	 */
	public QrcodeloginVO selectQrcodeloginVOById (QrcodeloginVO qrcodelogin);              
	
	/**
	 *  新增单条数据
	 */
	public boolean saveQrcodeloginVO (QrcodeloginVO qrcodelogin);
	
	/**
	 *  修改单条数据
	 */
	public boolean updateQrcodeloginVO(QrcodeloginVO qrcodelogin);
	
	/**
	 *  批量删除多条数据
	 */
	public boolean delQrcodeloginVO(QrcodeloginVO qrcodelogin);
}
