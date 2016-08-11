package com.framework.core.utils;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConvertDemo {

public static String DateToStr(Date date) {
  
   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String str = format.format(date);
   return str;
}
public static Date StrToDate(String str) {
  
   SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
   Date date = null;
   try {
    date = format.parse(str);
   } catch (ParseException e) {
    e.printStackTrace();
   }
   return date;
}

public static void main(String[] args) {
  
/*   Date date = new Date();
   System.out.println("日期转字符串：" + ConvertDemo.DateToStr(date));*/
   System.out.println("字符串转日期：" + ConvertDemo.StrToDate("20061126185727"));
   Date date  = ConvertDemo.StrToDate("20061126185727");
   System.out.println("日期转字符串：" + ConvertDemo.DateToStr(date));
  
}

}