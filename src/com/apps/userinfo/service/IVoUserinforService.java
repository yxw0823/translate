package com.apps.userinfo.service;

import com.apps.userinfo.domain.VoUserinforVO;
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
public interface IVoUserinforService {
	/**
	 *  分页查询
	 */
	public PageBeanVO<VoUserinforVO> selectVoUserinforVOPage(PageBeanVO<VoUserinforVO> page,VoUserinforVO voUserinfor);
	/**
	 *   根据ID查看
	 */
	public VoUserinforVO selectVoUserinforVOById (VoUserinforVO voUserinfor);              
	
	/**
	 *  新增单条数据
	 */
	public boolean saveVoUserinforVO (VoUserinforVO voUserinfor);
	/**
	 * 登陆
	 * @param voUserinfor
	 * @return
	 */
	public VoUserinforVO login(VoUserinforVO voUserinfor);
	/**
	 *  修改单条数据
	 */
	public boolean updateVoUserinforVO(VoUserinforVO voUserinfor);
	
	/**
	 *  批量删除多条数据
	 */
	public boolean delVoUserinforVO(VoUserinforVO voUserinfor);
}
