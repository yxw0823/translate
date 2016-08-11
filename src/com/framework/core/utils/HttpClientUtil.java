package com.framework.core.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

import com.framework.core.exception.BaseException;

public class HttpClientUtil
{
	public static final String METHOD_POST_STRING = "POST";
	public static final String METHOD_GET_STRING = "GET";
	public static final String METHOD_PUT_STRING = "PUT";
	public static final String METHOD_DELETE_STRING = "DELETE";
	public static final String CONTENT_TYPE_TEXT_XML = "text/xml;charset=UTF-8";
	public static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=UTF-8";
	public static final String CONTENT_TYPE_TEXT_FORM = "application/x-www-form-urlencoded";

	class Bank
	{

		private int sum;

		public void add(String content)
		{
			System.out.println(content);

		}

	}

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
	public static void sendRequest(final String requestURL, final String parameters, final String contentType,
			final String method) throws Exception
	{
		Thread t = new Thread(new Runnable()
		{
			private String content;

			public void setContent(String content)
			{
				this.content = content;
			}

			public String getContent()
			{
				return this.content;
			}

			public void run()
			{
				try
				{

					URL url = new URL(requestURL);
					HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
					httpConn.setRequestProperty("Content-Length", String.valueOf(parameters.length()));
					httpConn.setRequestProperty("Content-Type", contentType);
					httpConn.setRequestMethod(method);
					httpConn.setDoOutput(true);
					httpConn.setDoInput(true);
					OutputStream o = httpConn.getOutputStream();
					o.write(parameters.getBytes("utf-8"));
					o.close();
					InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "utf-8");
					BufferedReader bufr = new BufferedReader(isr);// 缓冲
					String line = null;
					StringBuffer content = new StringBuffer();
					while ((line = bufr.readLine()) != null)
					{
						content.append(line);
					}
					setContent(content.toString());
				}
				catch (Exception e)
				{
					// throw new BaseException(e.getMessage());
				}
			}
		});

		t.start();
		System.out.println(t.getStackTrace()[0].getMethodName());
	}

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
	public static String sendRequest2Html(final String requestURL, final String parameters, final String contentType,
			final String method) throws Exception
	{

		try
		{

			URL url = new URL(requestURL);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestProperty("Content-Length", String.valueOf(parameters.length()));
			httpConn.setRequestProperty("Content-Type", contentType);
			httpConn.setRequestMethod(method);
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			if (!StringUtils.isEmpty(parameters))
			{
				OutputStream o = httpConn.getOutputStream();
				o.write(parameters.getBytes("utf-8"));
				o.close();
			}

			InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "utf-8");
			BufferedReader bufr = new BufferedReader(isr);// 缓冲
			String line = null;
			StringBuffer content = new StringBuffer();
			while ((line = bufr.readLine()) != null)
			{
				content.append(line);
			}
			return content.toString();
		}
		catch (Exception e)
		{
			// throw new BaseException(e.getMessage());
		}
		return null;
	}

	/***************************************************************************
	 * @param requestURL
	 *            http请求地址
	 * 
	 * @param method
	 *            请求方式
	 * @throws Exception
	 **************************************************************************/
	public static byte[] getFileBytes(String requestURL, String method) throws Exception
	{

		try
		{

			// new一个URL对象
			URL url = new URL(requestURL);
			// 打开链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式为"GET"
			conn.setRequestMethod(method);
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(inStream);
			return data;
		}
		catch (Exception e)
		{
			throw new BaseException(e.getMessage());
		}
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception
	{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1)
		{
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}

	public void uploadFile(final File file, final String url)
	{
		new Thread(new Runnable()
		{
			public void run()
			{

				if (!file.exists())
				{
					return;
				}
				PostMethod postMethod = new PostMethod(url);
				try
				{
					// FilePart：用来上传文件的类
					FilePart fp = new FilePart("filedata", file);
					Part[] parts =
					{
						fp
					};

					// 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
					MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
					postMethod.setRequestEntity(mre);
					HttpClient client = new HttpClient();
					client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
					int status = client.executeMethod(postMethod);
					if (status == HttpStatus.SC_OK)
					{
						System.out.println(postMethod.getResponseBodyAsString());
					}
					else
					{
						System.out.println("fail");
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					// 释放连接
					postMethod.releaseConnection();
				}
			}
		}).start();
	}

	public static void main(String[] args)
	{
		try
		{
			String html = HttpClientUtil.sendRequest2Html("http://www.sc.xinhuanet.com/news.htm", "<xml/>",
					HttpClientUtil.CONTENT_TYPE_TEXT_XML, HttpClientUtil.METHOD_POST_STRING);
			// String html =
			// sendRequest2Html("http://m.gewara.com/touch/pictureDetail.xhtml?movieId=3781674&tag=movie",
			// "", "text/html;charset=UTF-8",
			// HttpClientMapUtilsTest.METHOD_GET_STRING);
			// org.jsoup.nodes.Document doc = Jsoup.parse(html,
			// "http://m.gewara.com/");
			// System.out.println(doc.toString());
			/*
			 * byte[] date = getFileBytes(
			 * "http://dl2.iteye.com/upload/attachment/0069/6480/b0f439f1-1dec-363b-85e2-aee9298bbbf4.png",
			 * "GET"); FileUtils.createFile("d:/1.png", date);
			 * System.out.println(ImageUtils.getImageType(date) +
			 * String.valueOf((Math.round((Double.valueOf(date.length) / 1024.0) *
			 * 100) / 100.00)));
			 */
			/*
			 * List<Map<String, String>> list = new ArrayList<Map<String,
			 * String>>();
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
			 * p:not(.ui_summary)").get(4).text()); list.add(map); }
			 * 
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
