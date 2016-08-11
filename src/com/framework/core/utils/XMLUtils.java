package com.framework.core.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @描述：
 * @包名：com.framework.core.utils
 * @类名：XMLUtils
 * @日期：2014-4-24
 * @版权：Copyright ® 烽火星空. All right reserved.
 * @作者：Administrator
 */
public class XMLUtils
{
    /**
     * 将xml类型的字符串转换成document对象进行操作
     * 
     * @param xml
     * @return
     */
    public static Document parseStringToXml(String xml)
    {
        try
        {
            return DocumentHelper.parseText(xml);
        }
        catch (DocumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取xml文件
     * 
     * @param filepath
     * @return
     */
    public static Document parseFileToXml(String filepath)
    {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try
        {
            doc = saxReader.read(new File(filepath));
        }
        catch (DocumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 获取webapp 相对路径下的xml文件
     * 
     * @param filename
     * @return
     */
    public static Document parseFIOToXml(String filename)
    {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try
        {
            InputStream in = XMLUtils.class.getResourceAsStream("xml/" + filename);
            doc = saxReader.read(in);
        }
        catch (DocumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 根据xpath 获取指定的元素
     * 
     * @param xpath
     * @param doc
     * @return
     */
    public static Element getElementByXpath(String xpath, Document doc)
    {
        List<Element> parameterList = doc.selectNodes(xpath);
        if (parameterList != null && parameterList.size() > 0)
        {
            return (Element) parameterList.get(0);
        }
        return null;
    }

    /**
     * 获取指定id的xml元素(单个xml中id唯一的情况下使用)
     * 
     * @param id
     * @param doc
     * @return
     */
    public static Element getElementById(String id, Document doc)
    {
        return getElementByXpath("//*[@id='" + id + "']", doc);
    }

    /**
     * 在指定id的元素后面插入元素
     * 
     * @param pid
     * @param newele
     * @param doc
     * @return
     */
    public static Document insertElement(String pid, Element newele, Document doc)
    {
        return insertElement(pid, newele, doc, 1);
    }

    /**
     * 在指定id的元素后面或前面插入元素
     * 
     * @param pid
     * @param newele
     * @param doc
     * @param i 为1表示在坐标元素之后----- 为0 则在坐标元素之前
     * @return
     */
    public static Document insertElement(String pid, Element newele, Document doc, int i)
    {
        Element element = getElementById(pid, doc);// 坐标元素
        List<Element> list = element.getParent().content();// 获取坐标元素父元素下的所有元素
        // list.indexOf(valueEle)+1, +1 表示在坐标元素之后； 不+1，则在坐标元素之前
        list.add(list.indexOf(element) + i, newele);
        return doc;
    }

    /**
     * 删除文档doc的指定路径下的所有子节点（包含元素，属性等） <br/> 如果路径相同一并删除
     * 
     * @param doc 文档对象
     * @param xpath 指定元素的路径 根据路径可删除元素、属性
     * @return 删除成功时返回true，否则false
     */
    public static boolean deleteNodes(Document doc, String xpath)
    {
        boolean flag = true;
        try
        {
            List<Node> nlist = doc.selectNodes(xpath);
            for (Node node : nlist)
            {
                node.detach();
            }
        }
        catch (Exception e)
        {
            flag = false;
        }
        return flag;
    }

    /**
     * 删除一个父元素下所有的子节点（包含元素，属性等）
     * 
     * @param element 父元素
     * @return 删除成功时返回true，否则false
     */
    public static boolean deleteChildren(Element element)
    {
        boolean flag = true;
        try
        {
            List<Node> nlist = element.elements();
            for (Node node : nlist)
            {
                node.detach();
            }
        }
        catch (Exception e)
        {
            flag = false;
        }
        return flag;
    }

    /**
     * 删除指定的元素
     * 
     * @param document
     * @param ele
     * @return
     */
    public static boolean deleteElement(Element ele)
    {
        List<Element> list = ele.getParent().content();
        list.remove(list.indexOf(ele));
        return true;
    }

    /**
     * 保存xml
     * 
     * @param filepath
     * @param document
     * @return 操作标识符
     */
    public static boolean saveDocument(String filepath, Document document)
    {
        try
        {
            XMLWriter writer = new XMLWriter(new FileWriter(new File(filepath)));
            writer.write(document);
            writer.close();
            return true;
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 测试
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        Document document = parseFileToXml("D:\\readxml.xml");
        Element ment = getElementByXpath("//p", document);
        System.out
                .println("***********************************" + ment.asXML() + "***********************************");
        /*
         * try {
         * 
         * XMLWriter writer = new XMLWriter(new FileWriter(new
         * File("F:\\readxml.xml"))); writer.write(document); writer.close(); }
         * catch (IOException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); }
         */
        String content =
                "<p>&nbsp; 记者从昨天召开的出租汽车&ldquo;经营行为规范整治提升年&rdquo;活动动员大会上了解到，我市将从5月1日起开展为期1年的规范整治提升活动，以有效遏制目前我市出租汽车行业存在的拒载、拼客、议价多收费等违法经营行为。</p><p>";
        content = content.replaceAll("&nbsp;", "#");
        content = content.replaceAll(">#{2,}", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        System.out.println(content);
    }
}