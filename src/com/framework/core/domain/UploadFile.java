package com.framework.core.domain;

import java.io.Serializable;

public class UploadFile implements Serializable
{
	private String fileName;
	private String filePath;
	private String fileSize;
	private String fieldName;
	private String folder;
	private String height;
	private String width;

	public String getFolder()
	{
		return folder;
	}

	public void setFolder(String folder)
	{
		this.folder = folder;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public String getFileSize()
	{
		return fileSize;
	}

	public void setFileSize(String fileSize)
	{
		this.fileSize = fileSize;
	}

	@Override
	public String toString()
	{
		return "UploadFile [fileName=" + fileName + ", filePath=" + filePath + ", fileSize=" + fileSize
				+ ", fieldName=" + fieldName + ", folder=" + folder + "]";
	}

	public String getHeight()
	{
		return height;
	}

	public void setHeight(String height)
	{
		this.height = height;
	}

	public String getWidth()
	{
		return width;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}
}
