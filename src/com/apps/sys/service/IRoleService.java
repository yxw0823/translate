package com.apps.sys.service;

import java.util.List;

import com.apps.sys.domain.AccountVO;
import com.apps.sys.domain.MenuTableVO;
import com.apps.sys.domain.ResourceVO;
import com.apps.sys.domain.RoleVO;
import com.framework.core.domain.PageBeanVO;

public interface IRoleService
{
	public static final String DEFAULT_AREA = "全局角色";

	public PageBeanVO<RoleVO> selectRolePage(PageBeanVO<RoleVO> page, RoleVO RoleVO);

	public boolean saveRole(RoleVO RoleVO);

	public boolean updateRole(RoleVO RoleVO);

	public boolean delRole(RoleVO RoleVO);

	public RoleVO selectRoleById(RoleVO RoleVO);

	public List<RoleVO> selectRole(String roleId);

	public boolean saveRoleConfig(ResourceVO resourceVO);

	public List<RoleVO> selectRoleList(String createid);

	public List<RoleVO> selectRoleList();

	public List<RoleVO> selectColumnList();

	public List<RoleVO> selectHomePageInfo();

	public Integer selectRoleUserCount(RoleVO r);

	public Integer selectRoleProductCount(RoleVO r);

	public Integer selectRoleMobileCount(RoleVO r);

	public void updateRoleBatch(List<AccountVO> accountVO);

	public RoleVO selectRoleByAppId(RoleVO roleVO);

	public String selectMaxColumnId();

	public List<MenuTableVO> selectAllMenuTable();

	public RoleVO selectRoleByName(RoleVO roleVO);

	public boolean delColumnInfo(RoleVO ro);
}
