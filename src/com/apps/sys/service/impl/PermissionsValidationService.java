package com.apps.sys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.sys.domain.MenuVO;
import com.apps.sys.service.IPermissionsValidationService;
import com.framework.core.service.impl.BaseService;
import com.framework.core.utils.StringUtils;

/**
 * 权限验证
 * @author Administrator
 *
 */
@Service
public class PermissionsValidationService extends BaseService implements IPermissionsValidationService{
	
	/**
	 * 返回当前角色用户的菜单操作权限
	 * @author 黄登亮
	 * @return
	 */
	public String getPermissions(String role_id){
		StringBuffer sql = new StringBuffer();
		sql.append("	select group_concat(r.resource_type_id) resource_id                  			 ");
		sql.append("	  from sys_resource_table r                                                      ");
		sql.append("	  left join sys_menu_operation mp on mp.operation_code = r.resource_type_id      ");
//		if(!StringUtils.isEmpty(menu_url)){
//			sql.append("	 ,(select menu_id                                                            ");
//			sql.append("	                                        from sys_menu_table                  ");
//			sql.append("	                                       where menu_url like '%");
//			sql.append(menu_url);
//			sql.append("%') m																			 ");
//			sql.append("		 where r.role_id = '");
//			sql.append(role_id);
//			sql.append("'	   and length(r.resource_type_id) <= 35                                      ");
//			sql.append("	   and mp.menu_id = m.menu_id                                                ");
//			sql.append("	 group by mp.menu_id                                                         ");
//		}else{
			sql.append("		 where r.role_id = '");
			sql.append(role_id);
			sql.append("'	   and length(r.resource_type_id) <= 35                                      ");
//			sql.append("	 group by mp.menu_id                                                         ");
//		}
		
		List<MenuVO> lsMenu = this.query(sql.toString(), MenuVO.class);
		return (lsMenu.size()>0)?lsMenu.get(0).getResource_id()+",":"";
	}
	/**
	 * 返回当前角色用户的菜单操作权限
	 * @author 黄登亮
	 * @return
	 */
	public String getPermissionsByMenu(String role_id,String menu_id){
		StringBuffer sql = new StringBuffer();
		sql.append("		SELECT              ");
		sql.append(" 	group_concat(o.menu_operation_name) ");
		sql.append(" FROM  ");                               
		sql.append(" 	sys_resource_table r, ");
		sql.append(" 	sys_menu_operation o   ");
		sql.append(" WHERE     ");
		sql.append(" 	r.resource_type_id = o.operation_code    ");
		sql.append(" AND r.role_id = '"+role_id+"'");
		sql.append(" AND length(r.resource_type_id) <= 35              ");
		sql.append(" AND r.resource_type_id IN (SELECT o.operation_code FROM sys_menu_operation o WHERE o.menu_id ='"+menu_id+"')");
		
		return (String)this.queryForColumn(sql.toString(), String.class);
	}
}
