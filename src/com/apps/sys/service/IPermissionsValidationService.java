package com.apps.sys.service;

public interface IPermissionsValidationService {
	public String getPermissions(String role_id);
	public String getPermissionsByMenu(String role_id,String menu_id);
}
