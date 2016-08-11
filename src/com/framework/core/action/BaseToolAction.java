package com.framework.core.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.framework.core.domain.UploadFile;
import com.framework.core.utils.Const;
import com.framework.core.utils.ImageUtils;
import com.framework.core.utils.StringUtils;

@Controller
@SuppressWarnings(
{
	"unchecked"
})
@RequestMapping(value = "/framework/core/BaseToolAction.do")
public class BaseToolAction extends BaseAction
{
	private static final long serialVersionUID = 1068590804829697704L;

	@RequestMapping(params = "method=getHostIP")
	public void getHostIP(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		System.out.println(ip);
	}

	@RequestMapping(params = "method=downLoad")
	public void downLoads(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String fileN = URLDecoder.decode(URLDecoder.decode(request.getParameter("filename"), "utf-8"), "utf-8");
			String array[] = fileN.split("/");
			String fpath = request.getParameter("fpath");
			if (fpath == null)
			{
				fpath = fileN;
			}
			String filename = array[array.length - 1];
			downLoadFile(request, response, filename, fpath);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void downLoadFile(HttpServletRequest request, HttpServletResponse response, String fileName, String fpath)
		throws IOException
	{
		response.reset();
		String contentType = "application/x-msdownload";
		response.setContentType(contentType);
		// String specialName=request.getParameter("specialName");
		// if (specialName==null) {
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(fileName.getBytes("gb2312"), "iso8859-1"));
		// }else {
		// response.setHeader("Content-disposition", "attachment; filename="
		// + new String(specialName.getBytes("gb2312"), "iso8859-1"));
		//
		// }
		java.io.BufferedInputStream bis = new java.io.BufferedInputStream(new java.io.FileInputStream(request
				.getSession().getServletContext().getRealPath(fpath)));
		java.io.BufferedOutputStream bos = new java.io.BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		try
		{
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
			{
				bos.write(buff, 0, bytesRead);
			}
		}
		catch (IOException e)
		{
			// socket reset 不用处理
		}
		finally
		{
			if (bis != null)
			{
				bis.close();
			}
			if (bos != null)
			{
				bos.close();
			}
		}
	}

	@RequestMapping(params = "method=reponseUploadPath")
	public void reponseUploadPath(HttpServletRequest request, HttpServletResponse response, String constname)
	{
		Class c = Const.class;
		String folder;
		try
		{
			Field f = c.getField(constname);
			folder = (String) f.get(c);
			List<UploadFile> list = uploadMultipleFiles(request, folder);
			String temp = "";
			String SizeInBytes = "";
			String fileName = "";
			if (list != null && !list.isEmpty())
			{
				if (list.size() == 1)
				{
					temp = list.get(0).getFilePath();
					SizeInBytes = list.get(0).getFileSize();
					fileName = list.get(0).getFileName();
				}
				else
				{
					for (UploadFile upFile : list)
					{
						temp += upFile.getFilePath() + "||";
						SizeInBytes += upFile.getFileSize() + "||";
						fileName += upFile.getFileName() + "||";
					}
					temp = temp.substring(0, temp.length() - 2);
					fileName = fileName.substring(0, fileName.length() - 2);
					SizeInBytes = SizeInBytes.substring(0, SizeInBytes.length() - 2);
				}
			}
			responseHtml(response, "{success:true,msg:'" + temp + "',SizeInBytes:'" + SizeInBytes + "',fileName:'"
					+ fileName + "' }");
		}
		catch (Exception e)
		{
			System.out.println("Const类中不存在" + constname + "变量名");
			e.printStackTrace();
		}
	}

	/**
	 * 处理一次性多文件上传 Action
	 */
	@RequestMapping(params = "method=reponseUploadPaths")
	public void reponseUploadPaths(HttpServletRequest request, HttpServletResponse response, String constname)
	{
		Class c = Const.class;
		String folder;
		try
		{
			Field f = c.getField(constname);
			folder = (String) f.get(c);
			List<UploadFile> list = uploadMultipleFiles(request, folder);
			this.responseJsonExtForm(response, list);
		}
		catch (Exception e)
		{
			System.out.println("Const类中不存在" + constname + "变量名");
			e.printStackTrace();
		}
	}

	/**
	 * 处理一次性多文件上传 Service
	 */
	public List<UploadFile> uploadMultipleFiles(HttpServletRequest request, String folder)
	{
		// 处理文件
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = new LinkedList<MultipartFile>();
		List<UploadFile> filePaths = new ArrayList<UploadFile>();
		for (Iterator it = multipartRequest.getFileNames(); it.hasNext();)
		{
			String key = (String) it.next();
			List<MultipartFile> files = multipartRequest.getFiles(key);
			for (MultipartFile file : files)
			{
				if (file.getOriginalFilename().length() > 0 && file.getOriginalFilename().lastIndexOf(".") != -1
						&& file.getSize() > 0)
				{
					fileList.add(file);
				}
			}
		}
		String serverPath = request.getRealPath("/");
		File saveFile = new File(serverPath + folder);
		if (!saveFile.exists())
		{
			saveFile.mkdirs();
		}
		String savePath = serverPath + folder + (folder.endsWith("/") ? "" : "/");
		// 保存文件
		for (MultipartFile targetFile : fileList)
		{
			try
			{
				CommonsMultipartFile cf = (CommonsMultipartFile) targetFile;
				DiskFileItem fi = (DiskFileItem) cf.getFileItem();
				File f = fi.getStoreLocation();
				UploadFile upFile = new UploadFile();
				upFile.setFileName(targetFile.getOriginalFilename().replace(",", ""));// .substring(0,targetFile.getOriginalFilename().lastIndexOf("."))
				upFile.setFileSize(targetFile.getSize() + "");
				upFile.setFolder(folder);
				String fileName = URLEncoder.encode(
						StringUtils.replaceBlank(targetFile.getOriginalFilename()).substring(0,
								targetFile.getOriginalFilename().lastIndexOf("."))).replaceAll("%", "");
				String temp_savePath = savePath
						+ fileName
						+ "_"
						+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
						+ RandomUtils.nextInt(1000)
						+ targetFile.getOriginalFilename().substring(targetFile.getOriginalFilename().lastIndexOf("."),
								targetFile.getOriginalFilename().length());
				String temp_img_small__savePath = savePath
						+ fileName
						+ "_"
						+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
						+ RandomUtils.nextInt(1000)
						+ "smallImg"
						+ targetFile.getOriginalFilename().substring(targetFile.getOriginalFilename().lastIndexOf("."),
								targetFile.getOriginalFilename().length());
				temp_savePath = temp_savePath.replaceAll("\\\\", "/");
				System.out.println("上传文件路径..................." + temp_savePath);
				FileOutputStream outFile = new FileOutputStream(temp_savePath);
				FileCopyUtils.copy(targetFile.getBytes(), outFile);
				outFile.flush();
				outFile.close();
				// 如果是图片，获取图片的宽高。
				String imgType = ImageUtils.getImageType(temp_savePath);
				if (!StringUtils.isEmpty(imgType))
				{
					upFile.setHeight(ImageUtils.getImgHeight(temp_savePath) + "");
					upFile.setWidth(ImageUtils.getImgWidth(temp_savePath) + "");
				}
				// 获取项目路径 返回list
				int end = temp_savePath.length();
				String returnPath = temp_savePath.substring(request.getRealPath("").length() + 1, end).replaceAll(
						"\\\\", "/");
				upFile.setFilePath(returnPath);
				if (Const.StoryCover.equals(folder))
				{
					// 上传封面图片，进行特殊处理
					if (!StringUtils.isEmpty(imgType))
					{
						// 图片大于1M 进行压缩
						if (targetFile.getSize() > (1048576 * 1))
						{
							float f1 = (float) 0.8;

							if (targetFile.getSize() > (1048576 * 5))
							{
								f1 = Float.valueOf(1)
										/ (Float.valueOf(targetFile.getSize()) / (Float.valueOf(1024 * 1024)));
							}
							ImageUtils.resize(new File(temp_savePath), new File(temp_img_small__savePath), 1080, f1);
							returnPath = temp_img_small__savePath.substring(request.getRealPath("").length() + 1,
									temp_img_small__savePath.length()).replaceAll("\\\\", "/");
							System.out.println("上传文件路径..................." + returnPath);
							upFile.setFilePath(returnPath);

						}
					}
				}

				filePaths.add(upFile);
			}
			catch (FileNotFoundException e)
			{
				System.out.println("创建" + savePath + "错误");
				e.printStackTrace();
			}
			catch (IOException e)
			{
				System.out.println("上传文件" + targetFile.getOriginalFilename() + "错误");
				e.printStackTrace();
			}
		}
		System.out.println("返回上传对象................" + filePaths);
		return filePaths;
	}

	/**
	 * 处理 一个或多个 单文件上传
	 */
	public List<UploadFile> uploadSingletonFiles(HttpServletRequest request)
	{
		// 处理文件
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<Map> fileList = new LinkedList<Map>();
		List<UploadFile> filePaths = new ArrayList<UploadFile>();
		for (Iterator it = multipartRequest.getFileNames(); it.hasNext();)
		{
			String key = (String) it.next();
			List<MultipartFile> files = multipartRequest.getFiles(key);
			for (MultipartFile file : files)
			{
				if (file.getOriginalFilename().length() > 0 && file.getOriginalFilename().lastIndexOf(".") != -1)
				{
					Map map = new HashMap();
					map.put("file", file);
					String fieldName = key.substring(0, key.lastIndexOf("_file"));
					map.put("beanFieldName", fieldName);
					map.put("folder", request.getParameter(fieldName + "_constname"));
					fileList.add(map);
				}
			}
		}
		String serverPath = request.getRealPath("/");
		for (Map map : fileList)
		{
			MultipartFile targetFile = (MultipartFile) map.get("file");
			String beanFieldName = (String) map.get("beanFieldName");
			String folder = (String) map.get("folder");
			File saveFile = new File(serverPath + folder);
			if (!saveFile.exists())
			{
				saveFile.mkdirs();
			}
			String savePath = serverPath + folder + (folder.endsWith("/") ? "" : "/");
			UploadFile upFile = new UploadFile();
			upFile.setFolder(folder);
			upFile.setFieldName(beanFieldName);
			upFile.setFileName(targetFile.getOriginalFilename().replace(",", ""));// .substring(0,targetFile.getOriginalFilename().lastIndexOf("."))
			upFile.setFileSize(targetFile.getSize() + "");
			String fileName = URLEncoder.encode(
					StringUtils.replaceBlank(targetFile.getOriginalFilename()).substring(0,
							targetFile.getOriginalFilename().lastIndexOf("."))).replaceAll("%", "");
			savePath = savePath
					+ fileName
					+ "_"
					+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
					+ targetFile.getOriginalFilename().substring(targetFile.getOriginalFilename().lastIndexOf("."),
							targetFile.getOriginalFilename().length());
			savePath = savePath.replaceAll("\\\\", "/");
			System.out.println("上传文件路径..................." + savePath);
			try
			{
				FileOutputStream outFile = new FileOutputStream(savePath);
				FileCopyUtils.copy(targetFile.getBytes(), outFile);
				outFile.flush();
				outFile.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("上传文件异常");
			}
			// 获取项目路径 返回list
			int end = savePath.length();
			String returnPath = savePath.substring(request.getRealPath("").length() + 1, end).replaceAll("\\\\", "/");
			upFile.setFilePath(returnPath);
			filePaths.add(upFile);
		}
		System.out.println("返回上传对象................" + filePaths);
		return filePaths;
	}

	public void extraUploadInfo(Object object, List<UploadFile> files)
	{
		try
		{
			Class cls = object.getClass();
			Field field = null;
			for (UploadFile f : files)
			{
				field = cls.getDeclaredField(f.getFieldName());
				field.setAccessible(true);
				field.set(object, f.getFilePath());
				field = cls.getDeclaredField(f.getFieldName() + "_text");
				field.setAccessible(true);
				field.set(object, f.getFileName());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("处理上传文件信息异常");
		}
	}

	/**
	 * 读取txt文件
	 * 
	 * @param txt
	 *            文件路径
	 * 
	 */
	@RequestMapping(params = "method=readTxt")
	public String readTxt(String txtPath)
	{
		String str = "";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(txtPath));
			String r = br.readLine();
			while (r != null)
			{
				str += r;
				r = br.readLine();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return str;
	}

	@RequestMapping(params = "method=selectTreeEmpty")
	public void selectTreeEmpty(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		this.responseJsonArray(response, new ArrayList());
	}

	public String parseFileVO(String names, String paths)
	{
		String[] name = names.split(",");
		String[] path = paths.split(",");
		List<UploadFile> files = new ArrayList<UploadFile>();
		UploadFile file = null;
		for (int i = 0; i < name.length; i++)
		{
			file = new UploadFile();
			file.setFileName(name[i]);
			file.setFilePath(path[i]);
			files.add(file);
		}
		return JSON.toJSONString(files);
	}

	public String getMobileJspPath(HttpServletRequest request)
	{
		String returnJsp = request.getParameter("returnJsp");
		return "/jsp/mobile/" + returnJsp;
	}
}
