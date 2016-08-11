package com.framework.core.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import sun.misc.BASE64Encoder;

@Component
@SuppressWarnings("unchecked")
public class HttpClientMapUtils
{
	public static final String METHOD_POST_STRING = "POST";
	public static final String METHOD_GET_STRING = "GET";
	public static final String METHOD_PUT_STRING = "PUT";
	public static final String METHOD_DELETE_STRING = "DELETE";
	public static final String CONTENT_TYPE_TEXT_XML = "text/xml;charset=UTF-8";
	public static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=UTF-8";
	public static final String CONTENT_TYPE_TEXT_FORM = "application/x-www-form-urlencoded";

	/***************************************************************************
	 * @param requestURL
	 *            http请求地址
	 * @param parameters
	 *            发送的参数
	 * @param contentType
	 *            消息内容类型
	 * @param method
	 *            请求方式
	 * @throws Exception
	 **************************************************************************/
	public static String sendRequest(String requestURL, String parameters, String contentType, String method)
		throws Exception
	{
		URL url = new URL(requestURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestProperty("Content-Length", String.valueOf(parameters.length()));
		if (StringUtils.isEmpty(contentType))
		{
			contentType = HttpClientMapUtils.CONTENT_TYPE_TEXT_FORM;
		}
		httpConn.setRequestProperty("Content-Type", contentType);
		httpConn
				.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36");
		httpConn.setRequestMethod(method);
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream o = httpConn.getOutputStream();
		o.write(parameters.getBytes("utf-8"));
		o.close();
		InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "utf-8");
		BufferedReader bufr = new BufferedReader(isr);// 缓冲
		// BufferedReader bufr = new BufferedReader(new
		// InputStreamReader(new
		// FileInputStream("D:\\demo.txt")));可以综合到一句。
		/*
		 * int ch =0; ch = bufr.read(); System.out.println((char)ch);
		 */
		String line = null;
		StringBuffer content = new StringBuffer();
		while ((line = bufr.readLine()) != null)
		{
			content.append(line);
		}
		// System.out.println(content.toString());
		// HttpServletResponse response = getResponse();
		return content.toString();
	}

	/***************************************************************************
	 * @param requestURL
	 *            http请求地址 解析GPS实际地址
	 * @param parameters
	 *            发送的参数
	 * @param contentType
	 *            消息内容类型
	 * @param method
	 *            请求方式
	 * @throws Exception
	 **************************************************************************/
	public static String sendGpsspgRequest(String requestURL, String parameters, String contentType, String method)
		throws Exception
	{
		URL url = new URL(requestURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestProperty("Content-Length", String.valueOf(parameters.length()));
		if (StringUtils.isEmpty(contentType))
		{
			contentType = HttpClientMapUtils.CONTENT_TYPE_TEXT_FORM;
		}
		httpConn.setRequestProperty("Content-Type", contentType);
		httpConn.setRequestProperty("Host", "www.gpsspg.com");
		httpConn.setRequestProperty("Referer", "http://www.gpsspg.com/apps/maps/baidu_140722.htm");
		httpConn
				.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125 Safari/537.36");
		httpConn.setRequestMethod(method);
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream o = httpConn.getOutputStream();
		o.write(parameters.getBytes("utf-8"));
		o.close();
		InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "utf-8");
		BufferedReader bufr = new BufferedReader(isr);// 缓冲
		// BufferedReader bufr = new BufferedReader(new
		// InputStreamReader(new
		// FileInputStream("D:\\demo.txt")));可以综合到一句。
		/*
		 * int ch =0; ch = bufr.read(); System.out.println((char)ch);
		 */
		String line = null;
		StringBuffer content = new StringBuffer();
		while ((line = bufr.readLine()) != null)
		{
			content.append(line);
		}
		// System.out.println(content.toString());
		// HttpServletResponse response = getResponse();
		return content.toString();
	}

	/***************************************************************************
	 * @param requestURL
	 *            http请求地址 获取新浪短地址
	 * @param parameters
	 *            发送的参数
	 * @param contentType
	 *            消息内容类型
	 * @param method
	 *            请求方式
	 * @throws Exception
	 **************************************************************************/
	public static String sendShortUrlGeneratorRequest(String requestURL, String parameters, String contentType,
			String method) throws Exception
	{
		URL url = new URL(requestURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestProperty("Content-Length", String.valueOf(parameters.length()));
		if (StringUtils.isEmpty(contentType))
		{
			contentType = HttpClientMapUtils.CONTENT_TYPE_TEXT_FORM;
		}
		httpConn.setRequestProperty("Content-Type", contentType);
		httpConn.setRequestProperty("Host", "www.waqiang.com");
		httpConn.setRequestProperty("Cookie", "PHPSESSID=cb1qvq8mgfmjams6j9j2bofs81");
		httpConn
				.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
		httpConn.setRequestMethod(method);
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream o = httpConn.getOutputStream();
		o.write(parameters.getBytes("utf-8"));
		o.close();
		InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "utf-8");
		BufferedReader bufr = new BufferedReader(isr);// 缓冲
		// BufferedReader bufr = new BufferedReader(new
		// InputStreamReader(new
		// FileInputStream("D:\\demo.txt")));可以综合到一句。
		/*
		 * int ch =0; ch = bufr.read(); System.out.println((char)ch);
		 */
		String line = null;
		StringBuffer content = new StringBuffer();
		while ((line = bufr.readLine()) != null)
		{
			content.append(line);
		}
		// System.out.println(content.toString());
		// HttpServletResponse response = getResponse();
		return content.toString();
	}

	public static String getMapUrl(String str)
	{
		if (StringUtils.isEmpty(str))
		{
			return "";
		}
		String[] strs = str.split(",");
		String lat = "";
		String lng = "";
		if (strs.length == 2)
		{
			lat = strs[0];
			lng = strs[1];
			return "http://www.gpsspg.com/ajax/maps_get.aspx?lat=" + lat + "&lng=" + lng + "&type=0";
		}
		return "";
	}

	public static String send(String str)
	{
		try
		{
			return sendGpsspgRequest(getMapUrl(str), "", "application/x-www-form-urlencoded",
					HttpClientMapUtils.METHOD_POST_STRING);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
		}
		return "";
	}

	public static void main(String[] args) throws Exception
	{
		long timeStamp = System.currentTimeMillis() / 1000;
		System.out.println(timeStamp);
		String randomNum = "rnd" + ((int) (Math.random() * 1000000) + 1000);
		// 先写死，这两个参数是要后期传入的。
		String paramKeys = "5CDAFCB3";
		String paramId = "d0029";
		StringBuffer parameters = new StringBuffer();
		List<Map.Entry<String, String>> mappingList = null;
		Map<String, String> map = new TreeMap<String, String>();
		map.put("timeStamp", String.valueOf(timeStamp));
		map.put("randomNum", String.valueOf(randomNum));
		map.put("paramKeys", String.valueOf(paramKeys));
		map.put("paramId", String.valueOf(paramId));
		parameters.append("timeStamp=").append(String.valueOf(timeStamp)).append("&").append("randomNum=").append(
				String.valueOf(randomNum)).append("&").append("paramId=").append(paramId).append("&myEncrypt=");
		// 通过ArrayList构造函数把map.entrySet()转换成list
		mappingList = new ArrayList<Map.Entry<String, String>>(map.entrySet());
		// 通过比较器实现比较排序
		Collections.sort(mappingList, new Comparator<Map.Entry<String, String>>()
		{
			public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2)
			{
				// return mapping1.getValue().compareTo(mapping2.getValue());
				return mapping2.getValue().compareTo(mapping1.getValue());
				// return map2.getValue() - map1.getValue();// 降序排列
			}
		});
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> mapping : mappingList)
		{
			// System.out.println(mapping.getKey() + ":" + mapping.getValue());
			sb.append(mapping.getValue());
		}
		System.out.println(sb.toString());
		String sha1 = Encrypt(sb.toString(), "SHA-1");
		System.out.println(sha1);
		parameters.append(sha1);
		parameters.append("&clientWidth=&clientHeight=");
		sendRequest("http://xhpfm.open.zhongguowangshi.com/open/startad", parameters.toString(),
				HttpClientMapUtils.CONTENT_TYPE_TEXT_FORM, HttpClientMapUtils.METHOD_POST_STRING);
	}

	public static String Encrypt(String strSrc, String encName)
	{
		// parameter strSrc is a string will be encrypted,
		// parameter encName is the algorithm name will be used.
		// encName dafault to "MD5"
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		try
		{
			if (encName == null || encName.equals(""))
			{
				encName = "MD5";
			}
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		}
		catch (NoSuchAlgorithmException e)
		{
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] bts)
	{
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++)
		{
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1)
			{
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static String getSHA(String vars) throws Exception
	{
		MessageDigest md5 = MessageDigest.getInstance("SHA-1");
		md5.update(vars.getBytes("UTF-8"));
		byte[] digesta = md5.digest();
		BASE64Encoder base64 = new BASE64Encoder();
		return base64.encode(digesta);
	}

	public static String bytetoString(byte[] digest)
	{
		String str = "";
		String tempStr = "";
		for (int i = 1; i < digest.length; i++)
		{
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1)
			{
				str = str + "0" + tempStr;
			}
			else
			{
				str = str + tempStr;
			}
		}
		return str.toLowerCase();
	}
}
