package com.framework.core.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//使用abstract目的是为了让使用者只能调用getInstance获取实例
public abstract class RegexUtil
{

	public static void main(String[] args)
	{
		RegexUtil ru = RegexUtil.getInstance();
		String regexp = "(//d+.//d+.//d+.//d+):(//d+)";
		String data = "ldap://192.168.16.152:389/";
		System.out.println(">>>" + ru.matchGroup(regexp, data).size());
	}

	public List<String> matchGroup(String regexp, String data)
	{
		Pattern p = Pattern.compile(regexp);
		Matcher matcher = p.matcher(data);
		List<String> list = null;
		while (matcher.find())
		{
			int length = matcher.groupCount();
			list = new java.util.ArrayList<String>(length);
			for (int i = 1; i < length + 1; i++)
			{
				list.add(matcher.group(i));
			}
		}
		return list;
	}

	private static RegexUtil ru = null;

	// 单太模式
	public static RegexUtil getInstance()
	{
		if (ru == null)
		{
			ru = new RegexUtil()
			{
			};
		}
		return ru;
	}
}