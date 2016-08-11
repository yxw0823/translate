package com.framework.core.utils;

public class SQLUtils {
	
	public static StringBuffer sqlConnect(StringBuffer sql) {
		if (sql.toString().toLowerCase().indexOf("where") > -1) {
			sql.append(" where ");
		} else {
			sql.append(" and ");
		}
		return sql;
	}
	
}
