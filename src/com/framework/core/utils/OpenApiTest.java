package com.framework.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class OpenApiTest
{

	public static void main(String[] args)
	{

		// test
		String url = "http://61.147.115.200:9081/api/server/open-api";// "http://192.168.1.110:8080/kjoa/server/open-api";

		String code = "TODO-TASK-LIST";
		Map data = new HashMap<String, Object>();
		data.put("USERID", 11410);
		data.put("APPTYPE", 0);
		data.put("START_INDEX", 0);
		data.put("RECORD_COUNT", 20);
		// data.put("DEPTID", 1);

		Map reqdata = new HashMap<String, Object>();
		reqdata.put("KEY", "jsddkj");
		reqdata.put("CODE", code);
		reqdata.put("DTYPE", 0);
		reqdata.put("SIGN", null);
		reqdata.put("TOKEN", "");
		String json = null;
		try
		{
			reqdata.put("DATA", JSONObject.fromObject(data).toString());
			json = JSONObject.fromObject(reqdata).toString();
		}
		catch (Exception err)
		{

		}
		System.out.println(json);
		String respdata = OpenApiTest.executeHttpPost(url, json);
		System.out.println(respdata);
	}

	public static String executeHttpPost(String strurl, String reqdata)
	{
		URL url = null;
		HttpURLConnection connection = null;
		String respdata = null;
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(reqdata);
			byte[] buf = baos.toByteArray();

			url = new URL(strurl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Charset", "utf-8");
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);

			DataOutputStream dop = new DataOutputStream(connection.getOutputStream());
			dop.write(buf);
			dop.flush();
			dop.close();

			DataInputStream ins = new DataInputStream(connection.getInputStream());
			if (ins != null)
			{
				byte[] rData = readStream(ins);
				respdata = new String(rData, 0, rData.length, "utf-8");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}
		finally
		{
			if (connection != null)
			{
				connection.disconnect();
			}
		}
		return respdata;
	}

	public static byte[] readStream(InputStream inStream) throws Exception
	{
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1)
		{
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}
}
