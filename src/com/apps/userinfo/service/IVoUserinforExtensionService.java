package com.apps.userinfo.service;

import com.apps.userinfo.domain.VoUserinforExtensionVO;
import com.framework.core.domain.PageBeanVO;
import java.util.List;
/**
* <p>Title: gocom用户扩展表</p>
* <p>Description: gocom用户扩展表</p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:中盈优创</p>
* @author: yanxuewen
* @version 1.0
*/
public interface IVoUserinforExtensionService {
	/**
	 * gocom用户扩展表 分页查询
	 */
	public PageBeanVO<VoUserinforExtensionVO> selectVoUserinforExtensionVOPage(PageBeanVO<VoUserinforExtensionVO> page,VoUserinforExtensionVO voUserinforExtension);
	/**
	 * gocom用户扩展表  根据ID查看
	 */
	public VoUserinforExtensionVO selectVoUserinforExtensionVOById (VoUserinforExtensionVO voUserinforExtension);              
	
	/**
	 * gocom用户扩展表 新增单条数据
	 */
	public boolean saveVoUserinforExtensionVO (VoUserinforExtensionVO voUserinforExtension);
	
	/**
	 * gocom用户扩展表 修改单条数据
	 */
	public boolean updateVoUserinforExtensionVO(VoUserinforExtensionVO voUserinforExtension);
	
	/**
	 * gocom用户扩展表 批量删除多条数据
	 */
	public boolean delVoUserinforExtensionVO(VoUserinforExtensionVO voUserinforExtension);
}
