package com.apps.userinfo.service;

import com.apps.userinfo.domain.VoDepartmentVO;
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
public interface IVoDepartmentService {
	/**
	 *  分页查询
	 */
	public PageBeanVO<VoDepartmentVO> selectVoDepartmentVOPage(PageBeanVO<VoDepartmentVO> page,VoDepartmentVO voDepartment);
	/**
	 *   根据ID查看
	 */
	public VoDepartmentVO selectVoDepartmentVOById (VoDepartmentVO voDepartment);              
	
	/**
	 *  新增单条数据
	 */
	public boolean saveVoDepartmentVO (VoDepartmentVO voDepartment);
	
	/**
	 *  修改单条数据
	 */
	public boolean updateVoDepartmentVO(VoDepartmentVO voDepartment);
	
	/**
	 *  批量删除多条数据
	 */
	public boolean delVoDepartmentVO(VoDepartmentVO voDepartment);
}
