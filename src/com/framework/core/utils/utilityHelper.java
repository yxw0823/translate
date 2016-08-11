package com.framework.core.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class utilityHelper
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		// TODO Auto-generated method stub

		/*
		 * String strAbsPath = request.getServletPath();
		 * out.println(strAbsPath.substring(strAbsPath.lastIndexOf("/")+1));
		 */
	}

	public static String getFileName(HttpServletRequest request)
	{

		String path = request.getServletPath();
		int i = path.lastIndexOf("/");
		return path.substring(i);
	}

	public static BufferedImage write2D(String content, int imgWidth, int imgHeight) throws WriterException
	{

		// QRCode qrcode = new QRCode();

		BitMatrix byteMatrix;
		int BLACK = 0xff000000;
		int WHITE = 0xFFFFFFFF;

		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();

		hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 文字编码。

		byteMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, imgWidth, imgHeight, hints);
		// new BitMatrix().setRegion(left, top, width, height)

		int width = byteMatrix.getWidth();
		int height = byteMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				image.setRGB(x, y, byteMatrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 
	 * 裁剪
	 * 
	 * @param image
	 *            原图
	 * @param x
	 *            起点坐标x
	 * @param y
	 *            起点坐标y
	 * @param destWidth
	 *            裁剪后宽度
	 * @param destHeight
	 *            裁剪后高度
	 * @return
	 */
	public static BufferedImage cut(BufferedImage image, int x, int y, int destWidth, int destHeight)
	{

		// 裁剪图像
		ImageFilter cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
		Image destImage = Toolkit.getDefaultToolkit().createImage(
				new FilteredImageSource(image.getSource(), cropFilter));

		// 将destImage转换为bufferedImage
		BufferedImage dstBufferedImage = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D biContext = dstBufferedImage.createGraphics();
		biContext.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		biContext.drawImage(destImage, 0, 0, null);

		return dstBufferedImage;
	}

	public static boolean isPCWEB(HttpServletRequest request)
	{

		boolean b = false;
		String ua = request.getHeader("user-agent");
		String os = null;
		String str = null;
		if (ua.contains(";"))
		{
			str = ua.split(";")[0];
			if (str.contains("("))
			{
				os = str.split("\\(")[1].trim();
				if (os.contains("compatible") || os.contains("Windows"))
				{
					b = true;
				}
			}
		}
		return b;
	}

}
