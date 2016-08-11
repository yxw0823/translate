package com.framework.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class DateUtils
{
	private static String defDtPtn = "yyyy-MM-dd HH:mm:ss";// 缺省日期格式

	/**
	 * 计算给定时间至今的天数
	 * 
	 * @since 1.1
	 * @param date
	 *            给定的时间
	 * @return 给定时间至今的天数
	 */
	public static long date2day(String date)
	{
		long day = 0;
		DateFormat df = DateFormat.getDateInstance();
		try
		{
			long old = df.parse(date).getTime();
			long now = new java.util.Date().getTime();
			long secs = now - old;
			day = secs / (1000 * 24 * 60 * 60);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * 格式化给定时间后day天的时间
	 * 
	 * @since 1.0
	 * @param date
	 *            需要格式化的时间
	 * @param day
	 *            增加的天数
	 * @return 给定时间的后day天的格式化字符串(如：2008-11-22)
	 */
	public static String formatDate(java.util.Date date, Integer day)
	{
		String str = "";
		str = new Date(date.getTime() + day * 24 * 60 * 60).toString();
		return str;
	}

	/**
	 * 格式化给定时间
	 * 
	 * @param date
	 *            需要格式化的时间
	 * @return 给定时间的格式化字符串(如：2008-11-22)
	 */
	public static String formatDate(java.util.Date date)
	{
		return new Date(date.getTime()).toString();
	}

	/**
	 * 得到当前年
	 * 
	 * @since 1.0
	 * @return 返回当前年(YYYY)
	 */
	public static int getYear()
	{
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 得到当前月
	 * 
	 * @since 1.0
	 * @return 返回当前月(1~12)
	 */
	public static int getMonth()
	{
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到当前日
	 * 
	 * @since 1.0
	 * @return 返回当前日(1~31)
	 */
	public static int getDay()
	{
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 将字符串按指定格式解析成日期对象
	 * 
	 * @since 1.1
	 * @param dateStr
	 *            需要进行转换的日期字符串
	 * @param pattern
	 *            日期字符串的格式
	 * @return "yyyy-MM-dd HH:mm:ss"形式的日期对象
	 */
	public static java.util.Date parseDate(String dateStr, String pattern)
	{
		SimpleDateFormat DATEFORMAT = new SimpleDateFormat(defDtPtn);
		DATEFORMAT.applyPattern(pattern);
		java.util.Date ret = null;
		try
		{
			ret = DATEFORMAT.parse(dateStr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		DATEFORMAT.applyPattern(defDtPtn);
		return ret;
	}

	/**
	 * 计算详细年龄
	 * 
	 * @since 1.1
	 * @param birthdayStr
	 *            出生日期 格式"YYYY-MM-DD"
	 * @return 指定日期至今天的年龄
	 */
	public static String countAge(String birthdayStr)
	{
		return birthdayStr;
	}

	/**
	 * 得到指定年月的天数
	 * 
	 * @since 1.1
	 * @param month
	 *            指定月份
	 * @param year
	 *            指定的年份
	 * @return 天数
	 */
	public static int getDayOfMonth(int month, int year)
	{
		int ret = 0;
		boolean flag = false;
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
		{
			flag = true;
		}
		switch (month)
		{
			case 1:
				ret = 31;
				break;
			case 2:
				if (flag)
				{
					ret = 29;
				}
				else
				{
					ret = 28;
				}
				break;
			case 3:
				ret = 31;
				break;
			case 4:
				ret = 30;
				break;
			case 5:
				ret = 31;
				break;
			case 6:
				ret = 30;
				break;
			case 7:
				ret = 31;
				break;
			case 8:
				ret = 31;
				break;
			case 9:
				ret = 30;
				break;
			case 10:
				ret = 31;
				break;
			case 11:
				ret = 30;
				break;
			case 12:
				ret = 31;
				break;
			default:
				break;
		}
		return ret;
	}

	/**
	 * 计算某天是星期几
	 * 
	 * @since 1.1
	 * @param p_date
	 *            日期字符串
	 * @return 星期
	 */
	public static int whatDayIsSpecifyDate(String p_date)
	{
		// 2002-2-22 is friday5
		long differenceDays = 0L;
		long m = 0L;
		differenceDays = signDaysBetweenTowDate(p_date, "2002-2-22");
		m = (differenceDays % 7);
		m = m + 5;
		m = m > 7 ? m - 7 : m;
		return Integer.parseInt(m + "");
	}

	/**
	 * 计算两日期间相差天数.
	 * 
	 * @since 1.1
	 * @param d1
	 *            日期字符串
	 * @param d2
	 *            日期字符串
	 * @return long 天数
	 */
	public static long signDaysBetweenTowDate(String d1, String d2)
	{
		java.sql.Date dd1 = null;
		java.sql.Date dd2 = null;
		long result = -1l;
		try
		{
			dd1 = java.sql.Date.valueOf(d1);
			dd2 = java.sql.Date.valueOf(d2);
			result = signDaysBetweenTowDate(dd1, dd2);
		}
		catch (Exception ex)
		{
			result = -1;
		}
		return result;
	}

	/**
	 * 计算两日期间相差天数.
	 * 
	 * @since 1.1
	 * @param d1
	 *            开始日期 日期型
	 * @param d2
	 *            结束日期 日期型
	 * @return long 天数
	 */
	public static long signDaysBetweenTowDate(java.sql.Date d1, java.sql.Date d2)
	{
		return (d1.getTime() - d2.getTime()) / (3600 * 24 * 1000);
	}

	public int getDaysBetween(java.util.Calendar d1, java.util.Calendar d2)
	{
		if (d1.after(d2))
		{ // swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(java.util.Calendar.DAY_OF_YEAR) - d1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = d2.get(java.util.Calendar.YEAR);
		if (d1.get(java.util.Calendar.YEAR) != y2)
		{
			d1 = (java.util.Calendar) d1.clone();
			do
			{
				days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				d1.add(java.util.Calendar.YEAR, 1);
			}
			while (d1.get(java.util.Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 计算2个日期之间的相隔天数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int getWorkingDay(java.util.Calendar d1, java.util.Calendar d2)
	{
		int result = -1;
		if (d1.after(d2))
		{ // swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int charge_start_date = 0;// 开始日期的日期偏移量
		int charge_end_date = 0;// 结束日期的日期偏移量
		// 日期不在同一个日期内
		int stmp;
		int etmp;
		stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
		etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);
		if (stmp != 0 && stmp != 6)
		{// 开始日期为星期六和星期日时偏移量为0
			charge_start_date = stmp - 1;
		}
		if (etmp != 0 && etmp != 6)
		{// 结束日期为星期六和星期日时偏移量为0
			charge_end_date = etmp - 1;
		}
		// }
		result = (getDaysBetween(this.getNextMonday(d1), this.getNextMonday(d2)) / 7) * 5 + charge_start_date
				- charge_end_date;
		// System.out.println("charge_start_date>" + charge_start_date);
		// System.out.println("charge_end_date>" + charge_end_date);
		// System.out.println("between day is-->" + betweendays);
		return result;
	}

	public String getChineseWeek(Calendar date)
	{
		final String dayNames[] =
		{
			"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
		};
		int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
		// System.out.println(dayNames[dayOfWeek - 1]);
		return dayNames[dayOfWeek - 1];
	}

	/**
	 * 获得日期的下一个星期一的日期
	 * 
	 * @param date
	 * @return
	 */
	public Calendar getNextMonday(Calendar date)
	{
		Calendar result = null;
		result = date;
		do
		{
			result = (Calendar) result.clone();
			result.add(Calendar.DATE, 1);
		}
		while (result.get(Calendar.DAY_OF_WEEK) != 2);
		return result;
	}

	/**
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int getHolidays(Calendar d1, Calendar d2)
	{
		return this.getDaysBetween(d1, d2) - this.getWorkingDay(d1, d2);
	}

	// ============================================================//
	public static Date date = null;
	public static DateFormat dateFormat = null;
	public static Calendar calendar = null;

	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期：YYYY-MM-DD 格式
	 * @return Date
	 */
	public static Date parseDate(String dateStr)
	{
		return parseDate(dateStr, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：格式化输出日期
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return 返回字符型日期
	 */
	public static String format(Date date, String format)
	{
		String result = "";
		try
		{
			if (date != null)
			{
				dateFormat = new SimpleDateFormat(format);
				result = dateFormat.format(date);
			}
		}
		catch (Exception e)
		{
		}
		return result;
	}

	/**
	 * 功能描述：
	 * 
	 * @param date
	 *            Date 日期
	 * @return
	 */
	public static String format(Date date)
	{
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：返回年份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回年份
	 */
	public static int getYear(Date date)
	{
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 功能描述：返回月份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date)
	{
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date)
	{
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date)
	{
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date)
	{
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date)
	{
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date)
	{
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 功能描述：返回字符型日期
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期 yyyy/MM/dd 格式
	 */
	public static String getDate(Date date)
	{
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：返回字符型时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型时间 HH:mm:ss 格式
	 */
	public static String getTime(Date date)
	{
		return format(date, "HH:mm:ss");
	}

	/**
	 * 功能描述：返回字符型日期时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
	 */
	public static String getDateTime(Date date)
	{
		return format(date, "yyyy/MM/dd HH:mm:ss:SSS");
	}

	/**
	 * 功能描述：日期相加
	 * 
	 * @param date
	 *            Date 日期
	 * @param day
	 *            int 天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day)
	{
		calendar = Calendar.getInstance();
		long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 * 功能描述：日期相减
	 * 
	 * @param date
	 *            Date 日期
	 * @param date1
	 *            Date 日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date date, Date date1)
	{
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 功能描述：取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String yyyy-MM-dd 格式
	 */
	public static String getMonthBegin(String strdate)
	{
		date = parseDate(strdate);
		return format(date, "yyyy-MM") + "-01";
	}

	/**
	 * 功能描述：取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public static String getMonthEnd(String strdate)
	{
		date = parseDate(getMonthBegin(strdate));
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}

	/**
	 * 功能描述：以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return String 日期字符串
	 */
	public static String formatDateByFormat(Date date, String format)
	{
		String result = "";
		if (date != null)
		{
			try
			{
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 将使用的毫秒数转化为可读的字符串, 如1天1小时1分1秒. <BR>
	 * <code>assertEquals("1天1小时1分1秒", DateUtil.timeToString(90061000));</code>
	 * 
	 * @param msUsed
	 *            使用的毫秒数.
	 * @return 可读的字符串, 如1天1小时1分1秒.
	 */
	public static String timeToString(long msUsed)
	{
		// TODO 用移位运算提高性能.
		if (msUsed < 0)
		{
			return String.valueOf(msUsed);
		}
		if (msUsed < 1000)
		{
			return String.valueOf(msUsed) + "毫秒";
		}
		// 长于1秒的过程，毫秒不计
		msUsed /= 1000;
		if (msUsed < 60)
		{
			return String.valueOf(msUsed) + "秒";
		}
		if (msUsed < 3600)
		{
			long nMinute = msUsed / 60;
			long nSecond = msUsed % 60;
			return String.valueOf(nMinute) + "分";
			// return String.valueOf(nMinute) + "分" + String.valueOf(nSecond) +
			// "秒";
		}
		// 3600 * 24 = 86400
		if (msUsed < 86400)
		{
			long nHour = msUsed / 3600;
			long nMinute = (msUsed - nHour * 3600) / 60;
			long nSecond = (msUsed - nHour * 3600) % 60;
			// return String.valueOf(nHour) + "小时" + String.valueOf(nMinute) +
			// "分" + String.valueOf(nSecond) + "秒";
			return String.valueOf(nHour) + "小时";
		}
		long nDay = msUsed / 86400;
		long nHour = (msUsed - nDay * 86400) / 3600;
		long nMinute = (msUsed - nDay * 86400 - nHour * 3600) / 60;
		long nSecond = (msUsed - nDay * 86400 - nHour * 3600) % 60;
		return String.valueOf(nDay) + "天";
		// return String.valueOf(nDay) + "天" + String.valueOf(nHour) + "小时" +
		// String.valueOf(nMinute) + "分"+ String.valueOf(nSecond) + "秒";
	}

	public static void main(String[] args)
	{
		// System.out.println(d.toString());
		// System.out.println(formatDate(d).toString());
		// System.out.println(getMonthBegin(formatDate(d).toString()));
		// System.out.println(getMonthBegin("2008/07/19"));
		System.out.println(DateUtils.format(new Date(), "yyyyMMddHHmmss"));
		UUID.randomUUID();
	}

	public static String currentTime()
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		return dateString;
	};

	public static Date stringToDate(String value, String dateFormat2)
	{
		return parseDate(value, dateFormat2);
	}

	public static Date getNextDay(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}
}
