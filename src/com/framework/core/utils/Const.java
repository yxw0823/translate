package com.framework.core.utils;

import java.io.File;

import org.springframework.context.ApplicationContext;

public class Const
{
	private static String pathName = "jdbc.properties";
	// 方言
	public static final String DIALECT = FileUtils.getPropertiesContext("DIALECT", pathName);
	// 数据库
	public static final String BASE = FileUtils.getPropertiesContext("BASE", pathName);
	// 工程目录
	public static final String Directory = "D:/MyEclipse8.5/workspace/BaseVersion";
	public static final String PERMISION = "permission";
	public static final String PERMISION_BTN = "permissionBtn";
	public static final String SESSION_USER = "session_user";
	public static final String SESSION_GUEST_USER = "session_guest_user";// 外宾
	public static final String SESSION_ROLE = "session_role";// 外宾
	public static final String SESSION_APPSTOREID = "appStoreID";// 外宾
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static ApplicationContext WEB_APP_CONTEXT = null; // 该值会在web容器启动时由WebAppContextListener初始化
	// view/BASE TABLE/SYSTEM VIEW
	// 员工图像上传途径
	public static final String USER_PHOTO = "upload" + File.separator + "userpic";
	public static final String myphoto = "myphoto";
	public static final String importPoints = "importPoints";
	public static final String QUESTION_EXPORT = "questionExport";
	
	public static final String walkPointsPhoto = "walkPointsPhoto";
	public static final String TEMP = "temp";
	public static final String TAG = "tag";
	public static final String VIDEO = "video";
	public static final String DOC = "doc";
	public static final String ProductClassify = "productType";
	public static final String ProductClassifySort = "productSortType";
	public static final String InfoUpload = "InfoUpload";
	public static final String StoryCover = "StoryCover";
	public static final String APP = "exmobiApp";
	public static final String QRCODE = "QRCODE";
	public static final String VIDEOPIC = "videopic";
	public static final String NETIMAGE = "NETIMAGE";
	public static final String SESSION_ROLES = "session_users";
}
