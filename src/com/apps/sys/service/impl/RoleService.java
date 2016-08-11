package com.apps.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.sys.domain.AccountVO;
import com.apps.sys.domain.MenuTableVO;
import com.apps.sys.domain.ResourceVO;
import com.apps.sys.domain.RoleVO;
import com.apps.sys.service.IRoleService;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.service.impl.BaseService;
import com.framework.core.utils.GuidUtils;
import com.framework.core.utils.StringUtils;

@Service
public class RoleService extends BaseService implements IRoleService
{
    /**
     * 创建sql语句
     * 
     * @author 黄登亮
     * @param RoleVO
     * @return sql语句
     */
    public String createSQL(RoleVO roleVO)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("select role_id, role_name, remark, create_time    , area,  appID, ");
        if (roleVO.getArea() != null)
        {
            sql.append(" ( select count(0) from sys_account_manage a  where a.role_id = role.role_id  and ( select SUBSTRING_INDEX(GROUP_CONCAT(area), ',', -1)   from m_user_info m where m.account_id=a.account_id)=:area  ) usecount ");
        }
        else
        {
            sql.append(" ( select count(0) from sys_account_manage a  where a.role_id = role.role_id   ) usecount ");
        }
        sql.append("  from sys_role_manage role                             ");
        sql.append(" where 1 = 1                                          ");
        if (!StringUtils.isEmpty(roleVO) && !StringUtils.isEmpty(roleVO.getRole_id()))
        {
            sql.append(" and role_id =:role_id ");
        }
        if (!StringUtils.isEmpty(roleVO) && !StringUtils.isEmpty(roleVO.getUsecount()))
        {
            sql.append(" and appID ='role' ");
        }
        else
        {
            sql.append(" and appID !='role' ");
        }
        if (!StringUtils.isEmpty(roleVO) && !StringUtils.isEmpty(roleVO.getRole_name()))
        {
            sql.append(" and role_name like '" + StringUtils.encodeLike(roleVO.getRole_name(), 3) + "' ");
        }
        if (!roleVO.isAdmin() && !"1".equals(roleVO.getRole_id()))
        {
            sql.append(" and role_id!='-1' and role_id!='1'		");
        }
        return sql.toString();
    }

    /**
     * 创建sql语句
     * 
     * @author 黄登亮
     * @param RoleVO
     * @return sql语句
     */
    public String createSQLById(RoleVO roleVO)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("select role_id, role_name, remark, create_time , area,  appID,bgimage, ");
        if (roleVO.getArea() != null)
        {
            sql.append(" ( select count(0) from sys_account_manage a  where a.role_id = role.role_id  and ( select SUBSTRING_INDEX(GROUP_CONCAT(area), ',', -1)   from m_user_info m where m.account_id=a.account_id)=:area  ) usecount ");
        }
        else
        {
            sql.append(" ( select count(0) from sys_account_manage a  where a.role_id = role.role_id   ) usecount ");
        }
        sql.append("  from sys_role_manage role                             ");
        sql.append(" where 1 = 1                                          ");
        if (!StringUtils.isEmpty(roleVO) && !StringUtils.isEmpty(roleVO.getRole_id()))
        {
            sql.append(" and role_id =:role_id ");
        }
        if (!StringUtils.isEmpty(roleVO) && !StringUtils.isEmpty(roleVO.getAppID()))
        {
            if ("appID".equals(roleVO.getAppID()))
            {
                sql.append(" and appID !='role' ");
            }
            else
            {
                sql.append(" and appID =:appID ");
            }
        }
        if (!StringUtils.isEmpty(roleVO) && !StringUtils.isEmpty(roleVO.getRole_name()))
        {
            sql.append(" and role_name like '" + StringUtils.encodeLike(roleVO.getRole_name(), 3) + "' ");
        }
        if (!roleVO.isAdmin() && !"1".equals(roleVO.getRole_id()))
        {
            sql.append(" and role_id!='-1' and role_id!='1'     ");
        }
        return sql.toString();
    }

    /**
     * 角色分页查询
     * 
     * @param page
     * @param article
     * @return
     */
    public PageBeanVO<RoleVO> selectRolePage(PageBeanVO<RoleVO> page, RoleVO roleVO)
    {
        return this.query4PageExt(page, this.createSQL(roleVO), roleVO);
    }

    /**
     * 根据id查询角色
     * 
     * @author 黄登亮
     * @param RoleVO
     */
    public RoleVO selectRoleById(RoleVO roleVO)
    {
        Object o = this.queryForClass(this.createSQLById(roleVO), RoleVO.class, roleVO);
        if (o instanceof RoleVO)
        {
            return (RoleVO) o;
        }
        return new RoleVO();
    }

    /**
     * 保存角色
     * 
     * @author 黄登亮
     * @param RoleVO
     * @return 是否添加成功：true 是，false 否
     */
    public boolean saveRole(RoleVO roleVO)
    {
        StringBuffer sql = new StringBuffer();
        roleVO.setRole_id(GuidUtils.getRandomGUID(true));
        sql.append(" insert into sys_role_manage                          ");
        sql.append("   (role_id, role_name, remark, create_time,area,bgimage,appID)          ");
        sql.append(" values ( :role_id, :role_name, :remark, now(),:area,:bgimage,:appID)    	  ");
        return this.execSql(sql.toString(), roleVO) > 0;
    }

    /**
     * 修改角色
     * 
     * @author 黄登亮
     * @param RoleVO
     * @return 是否修改成功：true 是，false 否
     */
    public boolean updateRole(RoleVO roleVO)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" update sys_role_manage                               ");
        sql.append("    set role_name = :role_name, remark = :remark ,appID=:appID  ,area=:area,bgimage=:bgimage   ");
        sql.append("  where role_id = :role_id                            ");
        return this.execSql(sql.toString(), roleVO) > 0;
    }

    /**
     * 根据menu_id删除角色
     * 
     * @author 黄登亮
     * @param menu_id
     *            如果是多个，则使用','分隔
     * @return 是否删除成功：true 是，false 否
     */
    public boolean delRole(RoleVO roleVO)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" delete from sys_role_manage		");
        sql.append("   where role_id = :role_id			");
        return this.execSql(sql.toString(), roleVO) > 0;
        // return this.batchExecSql4List(sql.toString(),
        // extractToList("role_id",
        // roleVO.getRole_id(), RoleVO.class)).length > 0;
    }

    /**
     * 保存角色权限
     * 
     * @author 黄登亮
     * @param ResourceVO
     * @return 是否添加成功：true 是，false 否
     */
    public boolean saveRoleConfig(ResourceVO resourceVO)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("	delete from sys_resource_table where role_id= :role_id	");
        // 先删除然后重新保存权限
        this.execSql(sql.toString(), resourceVO);
        // 拼接list
        List<ResourceVO> lsVo = new ArrayList<ResourceVO>();
        if (!StringUtils.isEmpty(resourceVO.getResource_type_id()))
        {
            String[] resource_type_id = resourceVO.getResource_type_id().split(",");
            for (int i = 1; i < resource_type_id.length; i++)
            {
                ResourceVO rVo = new ResourceVO();
                rVo.setResource_id(GuidUtils.getRandomGUID(true));
                rVo.setResource_type_id(resource_type_id[i]);
                rVo.setRole_id(resourceVO.getRole_id());
                lsVo.add(rVo);
            }
        }
        sql.setLength(0);
        sql.append("	insert into sys_resource_table																								");
        sql.append("	  (resource_id, role_id, resource_type, create_time, resource_type_id)        ");
        sql.append("	values                                                                        ");
        sql.append("	  (:resource_id, :role_id, :resource_type, now(), :resource_type_id)   ");
        return this.batchExecSql4List(sql.toString(), lsVo).length > 0;
    }

    public List<RoleVO> selectRoleList(String area)
    {
        String sql = " select * from sys_role_manage where 1=1 ";
        sql += " and role_id!='-1' and role_id!='1' and appID !='role' ";
        if (!StringUtils.isEmpty(area))
        {
            sql += "and area='" + area + "'";
        }
        return this.query(sql, RoleVO.class);
    }

    public List<RoleVO> selectRole(String roleId)
    {
        String sql = "SELECT ro.* from sys_role_manage  ro  where ro.role_id in (SELECT resource_type_id from sys_resource_comment where role_id ='"
                + roleId + "')";
        return this.query(sql, RoleVO.class);
    }

    public List<RoleVO> selectRoleList()
    {
        String sql = " select * from sys_role_manage where 1=1 ";
        sql += " and appID ='role' ";
        return this.query(sql, RoleVO.class);
    }

    public List<RoleVO> selectColumnList()
    {
        String sql = " select * from sys_role_manage where 1=1 ";
        sql += " and appID !='role' ";
        return this.query(sql, RoleVO.class);
    }

    public Integer selectRoleUserCount(RoleVO r)
    {
        String sql = "select count(0) from sys_account_manage where role_id = '" + r.getRole_id() + "' and is_use = 1 ";
        return (Integer) this.queryForColumn(sql, Integer.class);
    }

    public Integer selectRoleProductCount(RoleVO r)
    {
        String sql = "select count(0) from m_product_classify where product_role_id = '" + r.getRole_id() + "'   ";
        return (Integer) this.queryForColumn(sql, Integer.class);
    }

    public Integer selectRoleMobileCount(RoleVO r)
    {
        String sql = "select count(0) from mobile_menu_role where role_id = '" + r.getRole_id() + "'  ";
        return (Integer) this.queryForColumn(sql, Integer.class);
    }

    public void updateRoleBatch(List<AccountVO> accountVO)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" update sys_account_manage                               ");
        sql.append("    set role_id = :role_id     ");
        sql.append("  where account_id = :account_id                            ");
        this.batchExecSql4List(sql.toString(), accountVO);
    }

    /*
     * 根据appid查询新增加的角色的数据
     * 
     * @see
     * com.apps.sys.service.IRoleService#selectRoleByAppId(com.apps.sys.domain
     * .RoleVO)
     */
    public RoleVO selectRoleByAppId(RoleVO roleVO)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" select  * from sys_role_manage where   ");
        sql.append(" appID ='").append(roleVO.getAppID()).append("'");
        Object o = this.queryForClassDefault(sql.toString(), roleVO);
        if (o instanceof RoleVO)
        {
            return (RoleVO) o;
        }
        else
        {
            return new RoleVO();
        }
    }

    public String selectMaxColumnId()
    {
        StringBuffer sql = new StringBuffer();
        sql.append("select max(a.columnId) as columnId from appColumnInfo a");
        return (String) this.queryForColumn(sql.toString(), null);
    }

    public List<MenuTableVO> selectAllMenuTable()
    {
        StringBuffer sql = new StringBuffer();
        sql.append("select a.* from sys_menu_table a ");
        // sql.append("LEFT JOIN appColumnInfo b on
        // a.parent_menu_id=b.menu_id");
        return this.query(sql.toString(), MenuTableVO.class, null);
    }

    public RoleVO selectRoleByName(RoleVO roleVO)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" select  * from sys_role_manage where   ");
        sql.append(" role_name ='").append(roleVO.getRole_name()).append("'");
        Object o = this.queryForClassDefault(sql.toString(), roleVO);
        if (o instanceof RoleVO)
        {
            return (RoleVO) o;
        }
        else
        {
            return new RoleVO();
        }
    }

    public boolean delColumnInfo(RoleVO ro)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" delete from appColumnInfo		");
        sql.append("   where appID = :appID			");
        return this.execSql(sql.toString(), ro) > 0;
    }

    public List<RoleVO> selectHomePageInfo()
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT r.* from m_home_page h LEFT JOIN sys_role_manage r  on r.role_id = h.area_id  GROUP BY area_id ");
        return this.query(sql.toString(), RoleVO.class);
    }
}
