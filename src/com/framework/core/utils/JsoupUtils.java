package com.framework.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class JsoupUtils
{
	protected static Logger logger = LoggerFactory.getLogger("JsoupUtils");

	public static String getText(Element menelementt, String cssQuery, int i)
	{
		Elements ems = menelementt.select(cssQuery);
		if (ems != null && ems.size() > i)
		{
			return ems.get(i).text();
		}
		return null;
	}

	public static Elements getElements(Document doc, String cssQuery)
	{
		Elements ems = doc.select(cssQuery);
		return ems;
	}

	/**
	 * 传入一组 Selector选择器概述
	 * 
	 * @param element
	 * @param cssQuery
	 *            如:attr,image,img,src:text,title,div.title a,0
	 * @return List<Map<String, String>>
	 */
	public static List<Map<String, String>> getObject(Elements element, String cssQuery)
	{
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		for (Element emt : element)
		{
			Map<String, String> map = new HashMap<String, String>();
			list.add(setObjectMap(map, cssQuery, emt));
		}
		return list;
	}

	/**
	 * 到网页抓取一个结果集。
	 * 
	 * @param resultSets
	 * @param cssQuery
	 *            过滤结果集
	 * @param Url
	 *            抓取网页地址
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static List<Map<String, String>> getObject(String resultSets, String cssQuery, String Url, String parameters)
	{

		try
		{
			String html = HttpClientUtil.sendRequest2Html(Url, parameters, "text/html;charset=UTF-8",
					HttpClientMapUtilsTest.METHOD_GET_STRING);
			Document emt = Jsoup.parse(html);
			return getObject(emt.select(resultSets), cssQuery);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<Map<String, String>> getAllObject(Elements pageEmt, String cssQuery,
			List<Map<String, String>> list, String cssQuerys, String baseurl)
	{
		try
		{
			for (Element emt : pageEmt)
			{
				String html = HttpClientUtil.sendRequest2Html(baseurl + emt.attr("href"), "",
						"text/html;charset=UTF-8", HttpClientMapUtilsTest.METHOD_GET_STRING);
				Document doc = Jsoup.parse(html);
				Elements div = getElements(doc, cssQuery);
				list.addAll(getObject(div, cssQuerys));

			}
			return list;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static Map<String, String> setObjectMap(Map<String, String> map, String cssQuerys, Element emt)
	{
		String[] cssQuery = cssQuerys.split("::");
		for (String query : cssQuery)
		{
			String[] querys = query.split(",");

			if (querys.length < 4)
			{

				logger.error("表达式不正确，正确的应该是以逗号分隔的“::”为一组的字符串特殊表达式如:attr,image,img,src:text,title,div.title a,0");
				String error = "";
				for (String str : querys)
				{
					error += str + ",";
				}
				logger.error("错误位置" + error.substring(0, error.length()));
				return null;
			}

			String method = querys[0];
			// 取属性
			String value = "";
			if (method.equals("attr"))
			{
				value = emt.select(querys[2]).attr(querys[3]);

			}
			// 取值
			if (method.equals("text"))
			{
				value = getText(emt, querys[2], Integer.valueOf(querys[3]));

			}
			if (querys.length == 6)
			{
				if (!Regexp.isHardRegexpValidate(querys[5], "\\$\\d{1,2}"))
				{
					logger.error(querys[5] + "校验不通过，正确的应该是“$”开头数字结尾,正确的写法为:$1,$2,$33等..");

					logger.error("错误位置大致在：" + query + "附近!");
					return null;
				}
				Integer i = Integer.valueOf((String) Regexp.getSoftRegexpArray(querys[5], "\\$(\\d{1,2})").get(1));
				value = String.valueOf(Regexp.getSoftRegexpArray(value, querys[4]).get(i));

			}
			map.put(querys[1], value);
		}
		return map;
	}

	public static Map<String, String> getObject(Map<String, String> map, String cssQuerys, String Url, String parameters)
	{

		try
		{
			String html = HttpClientUtil.sendRequest2Html(Url, "", "text/html;charset=UTF-8",
					HttpClientMapUtilsTest.METHOD_GET_STRING);
			Document emt = Jsoup.parse(html);
			map = setObjectMap(map, cssQuerys, emt);
			return map;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public static void main(String[] args)
	{
		String cssQuery = "attr,href,div.title a,href::attr,image,img,src::text,title,div.title a,0::text,ticket,span[data-keynum~=.*_boughtcount],0::text,grade,span.grade,0::text,attention,span[data-keynum~=.*_clickedtimes],0::text,grade,span.grade,0::text,grade,span.grade,0::text,type,div.ui_text p:not(.ui_summary),0::text,language,div.ui_text p:not(.ui_summary),1::text,duration,div.ui_text p:not(.ui_summary),2::text,director,div.ui_text p:not(.ui_summary),3::text,protagonist,div.ui_text p:not(.ui_summary),4::";
		try
		{
			Map<String, Object> detailMap = new HashMap<String, Object>();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();

			cssQuery = "text,title,div#appTitleText,0::text,synopsis,div#detailTab_content div.mod_bd,0::text,director,div.intro_text span,0::text,duration,div.intro_text span,4::text,release_time,div.intro_text span,6,.*：(.*)$,$1";
			Map<String, String> map = new HashMap<String, String>();
			getObject(map, cssQuery, "http://m.gewara.com/touch/pictureDetail.xhtml?movieId=237577756&tag=movie", "");
			cssQuery = "text,short_remark,div.ui_summary_big,0::attr,banner,div.Hm_video img,style,background:url\\((.*)\\) center,$1::attr,cover_image,div.ui_pic img,src";
			getObject(map, cssQuery, "http://m.gewara.com/touch/detail.xhtml?movieId=237577756&tag=movie", "");
			detailMap.put("detail", map);
			getObject("ul.clear li", "attr,image,img,src",
					"http://m.gewara.com/touch/pictureDetail.xhtml?movieId=237577756&tag=movie", "");
			detailMap.put("images", getObject("ul.clear li", "attr,image,img,src", "237577756&tag=movie", ""));
			System.out.println(JSON.toJSONString(detailMap));
			/*
			 * Elements div = getElements(doc, "div.ui_media");
			 * list.addAll(getObject(div, cssQuery)); Elements divPage =
			 * getElements(doc, "div#page a:not(.on):not(.next)");
			 * getAllObject(divPage, "div.ui_media", list, cssQuery,
			 * "http://www.gewara.com");
			 * 
			 * for (Element emt : divPage) { html =
			 * HttpClientUtil.sendRequest2Html("http://www.gewara.com" +
			 * emt.attr("href"), "", "text/html;charset=UTF-8",
			 * HttpClientMapUtilsTest.METHOD_GET_STRING); doc =
			 * Jsoup.parse(html); div = getElements(doc, "div.ui_media");
			 * list.addAll(getObject(div, cssQuery)); } //
			 * System.out.println(div.toString());
			 * 
			 * for (Element src : div) { Map<String, String> map = new HashMap<String,
			 * String>(); map.put("image", src.select("img").attr("src"));
			 * map.put("title", src.select("div.title a ").text());
			 * map.put("attention",
			 * src.select("span[data-keynum~=.*_clickedtimes]").text());
			 * map.put("ticket",
			 * src.select("span[data-keynum~=.*_boughtcount]").text());
			 * map.put("grade", src.select("span.grade").text().replace(" ",
			 * "")); map.put("type", src.select("div.ui_text
			 * p:not(.ui_summary)").get(0).text()); map.put("language",
			 * src.select("div.ui_text p:not(.ui_summary)").get(1).text());
			 * map.put("duration", src.select("div.ui_text
			 * p:not(.ui_summary)").get(2).text()); map.put("director",
			 * src.select("div.ui_text p:not(.ui_summary)").get(3).text());
			 * map.put("protagonist", src.select("div.ui_text
			 * p:not(.ui_summary)").get(4).text()); list.add(map); } //
			 * JSONArray.toArray(JSONArray.fromObject(list), MMovieVO.class);
			 * System.out.println(JSON.toJSONString(list));
			 */
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
