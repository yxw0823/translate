package com.framework.core.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class OfficeUtils {
	 
//	public static void main(String[] args) {
//		List<EmployeeInfoEntity> list=new ArrayList<EmployeeInfoEntity>();
//		for (int i = 0; i < 10; i++) {
//			EmployeeInfoEntity e=new EmployeeInfoEntity();
//			e.setEmployee_id(i+"");
//			e.setEmployee_name("name"+i);
//			list.add(e);
//		}
//		System.out.println(OfficeUtils.createExcel("D://abc//", "download", "统计信息.xls", "编号,姓名".split(","),list,"employee_id,employee_name".split(","),"总共:,1700万,这是测试的".split(",")));
//	}
	
	/**
	 * 创建Excel包含数据
	 * 数组都按顺序对应列名和字段值
	 * @param serverPath  存放目录路径：  "D:/"
	 * @param folder	  存放文件夹路径 ：download
	 * @param filename	 文件名   ：统计信息.xls
	 * @param columnNames 列名
	 * @param dataSource  数据源
	 * @param valueNames  字段名
	 * @param bottom_row_str  底部自定义行    
	 * @return saveFileName 保存的文件路径+名称
	 */
	public static synchronized String createExcel(String serverPath,String folder,String filename,String[] columnNames,List dataSource,String[] valueNames,String[] bottom_row_str){
		try {
			 serverPath = serverPath+((serverPath.endsWith("/")||serverPath.endsWith("\\")) ? "" : File.separator) ;
			 serverPath=serverPath.replaceAll("\\\\", "/").replaceAll("//", "/");
			 folder=folder+ ((folder.endsWith("/")||folder.endsWith("\\")) ? "" : File.separator);
			File fold=new File(serverPath+folder);
			if (!fold.exists()) {
				fold.mkdirs();
			}
			String saveFileName=folder+filename;
			saveFileName=saveFileName.replaceAll("\\\\", "/").replaceAll("//", "/");
			saveFileName=saveFileName.substring(0,saveFileName.lastIndexOf("."))+"_"+new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date())+saveFileName.substring(saveFileName.lastIndexOf("."),saveFileName.length());
			System.out.println("createExcel:"+serverPath+saveFileName);
			File file=new File(serverPath+saveFileName);
			// 创建可写入的excel工作簿
			WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
			// 创建可写的工作表
			WritableSheet wtSheet = writableWorkbook.createSheet("create", 0);
			for (int i=0;i<columnNames.length;i++) {
				wtSheet.addCell(new Label(i, 0, columnNames[i].trim()));
			}
			for (int j = 1; j <= dataSource.size(); j++) {
				for (int i = 0; i < valueNames.length; i++) {
					Object obj=dataSource.get(j-1);
//					Field field = obj.getClass().getDeclaredField(valueNames[i]);
					Field field =BeanUtils.getDeclaredField(obj, valueNames[i]);
					field.setAccessible(true);
					wtSheet.addCell(new Label(i, j, String.valueOf(field.get(obj)==null?"":field.get(obj))));
					field.setAccessible(false);
				}
				
			}
			if (bottom_row_str!=null) {
				for (int i = 0; i < bottom_row_str.length; i++) {
					wtSheet.addCell(new Label(i, dataSource.size()+1,bottom_row_str[i] ));
				}
			}
			writableWorkbook.write();
			writableWorkbook.close();
			return saveFileName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} 
	} 
	
	public static synchronized boolean createExcelTemplete(String serverPath,String folder,String filename,String[] columnNames){
		try {
			String savePath = serverPath+((serverPath.endsWith("/")||serverPath.endsWith("\\")) ? "" : File.separator) + folder
			+ ((folder.endsWith("/")||folder.endsWith("\\")) ? "" : File.separator);
			
			File fold=new File(savePath);
			if (!fold.exists()) {
				fold.mkdirs();
			}
			File file=new File(savePath+filename);
			// 创建可写入的excel工作簿
			WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
			// 创建可写的工作表
			WritableSheet wtSheet = writableWorkbook.createSheet("create", 0);
			for (int i=0;i<columnNames.length;i++) {
				wtSheet.addCell(new Label(i, 0, columnNames[i].trim()));
			}
			
			writableWorkbook.write();
			writableWorkbook.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	} 
	/**
	 * 解析Excel 返回List对象
	 * @param <T>
	 * @param path excel文件物理路径
	 * @param clazz 需要返回的对象
	 * @param fields 需要赋值的字段，必须和excel列的中顺序相同
	 * @return
	 */
	public static synchronized <T>List<T> parseExcel(String path,Class<T> clazz,String fields) {
		InputStream ios =null;
		Workbook writablebook=null;
		File f=null;
		try {
			List<T> list=new ArrayList<T>();
			T obj=null;
			
			try {
				ios = new BufferedInputStream(new FileInputStream(path));
				f=new File(path);
			} catch (Exception e) {
				System.out.println(path+"文件不存在");
				return null;
			}
			WorkbookSettings setting=new WorkbookSettings();
			Locale local=new Locale("zh","CN");
			setting.setLocale(local);
			setting.setEncoding("ISO-8859-1");
			writablebook = Workbook.getWorkbook(ios,setting); 
			Sheet sheetArray = writablebook.getSheet(0); 
			Cell[] cells = null;
			
			Field field=null;
			int rows=	sheetArray.getRows();
			
			for (int i = 0; i < rows; i++) {
				cells= sheetArray.getRow(i);
				obj=clazz.newInstance();
				String[] fieldstr=fields.split(",");
				for (int j = 0; j < fieldstr.length; j++) {
					if (fieldstr[j]!=null&&!fieldstr[j].trim().equals("")){
						field=clazz.getDeclaredField(fieldstr[j]);
						if(j<cells.length)
						set(field, obj, cells[j].getContents().toString());
					}
				}
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			try {
				if(writablebook!=null)
				writablebook.close();
				if(ios!=null)
				ios.close();
				if(f!=null&&f.exists())
					f.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 解析Excel为对象赋值
	 * @param field
	 * @param obj
	 * @param context
	 * @throws Exception
	 */
	private static  void  set(Field field,Object obj,String context) throws Exception{
		field.setAccessible(true);
		field.set(obj, context);
		field.setAccessible(false);
	}
}
