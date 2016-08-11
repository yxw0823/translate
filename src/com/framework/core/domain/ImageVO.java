package com.framework.core.domain;

public class ImageVO
{
	private String width; // 宽度
	private String height;// 高度
	private String localHref;// 本地路径
	private String netHref;// 网络路径
	private String size;// 图片大小
	private String type;// 图片格式

	public String getWidth()
	{
		return width;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}

	public String getHeight()
	{
		return height;
	}

	public void setHeight(String height)
	{
		this.height = height;
	}

	public String getLocalHref()
	{
		return localHref;
	}

	public void setLocalHref(String localHref)
	{
		this.localHref = localHref;
	}

	public String getNetHref()
	{
		return netHref;
	}

	public void setNetHref(String netHref)
	{
		this.netHref = netHref;
	}

	public String getSize()
	{
		return size;
	}

	public void setSize(String size)
	{
		this.size = size;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
