package com.apps.userinfo.service;

import com.apps.userinfo.domain.TbUserinforexVO;
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
public interface ITbUserinforexService {
	/**
	 *  分页查询
	 */
	public PageBeanVO<TbUserinforexVO> selectTbUserinforexVOPage(PageBeanVO<TbUserinforexVO> page,TbUserinforexVO tbUserinforex);
	/**
	 *   根据ID查看
	 */
	public TbUserinforexVO selectTbUserinforexVOById (TbUserinforexVO tbUserinforex);              
	
	/**
	 *  新增单条数据
	 */
	public boolean saveTbUserinforexVO (TbUserinforexVO tbUserinforex);
	
	/**
	 *  修改单条数据
	 */
	public boolean updateTbUserinforexVO(TbUserinforexVO tbUserinforex);
	
	/**
	 *  批量删除多条数据
	 */
	public boolean delTbUserinforexVO(TbUserinforexVO tbUserinforex);
}
