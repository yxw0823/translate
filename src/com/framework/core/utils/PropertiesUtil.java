package com.framework.core.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class PropertiesUtil extends HttpServlet
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 根据key读取value
    public static String readValue(String filePath, String key, HttpServletRequest request)
    {
        filePath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes") + "/" + filePath;
        Properties props = new Properties();
        try
        {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            return props.getProperty(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // 根据key读取value
    public static String readValue(String filePath, String key)
    {
        filePath = PropertiesUtil.class.getResource("/").getPath() + "/" + filePath;
        Properties props = new Properties();
        try
        {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            return props.getProperty(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // 读取properties的全部信息
    public static void readProperties(String filePath)
    {
        filePath = Class.class.getClass().getResource("/").getPath() + "/" + filePath;
        Properties props = new Properties();
        try
        {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements())
            {
                String key = (String) en.nextElement();
                String Property = props.getProperty(key);
                System.out.println(key + Property);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 写入properties信息
    public static void writeProperties(String filePath, String parameterName, String parameterValue)
    {
        filePath = Class.class.getClass().getResource("/").getPath() + "/" + filePath;
        Properties prop = new Properties();
        try
        {
            InputStream fis = new FileInputStream(filePath);
            // 从输入流中读取属性列表（键和元素对）
            prop.load(fis);
            // 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(filePath);
            prop.setProperty(parameterName, parameterValue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            prop.store(fos, "Update '" + parameterName + "' value");
        }
        catch (IOException e)
        {
            System.err.println("Visit " + filePath + " for updating " + parameterName + " value error");
        }
    }
}
