package com.framework.wight.security;

/**
 * Title: 密码加密
 * Description: MD5加密
 * Copyright: Copyright (c) 2011
 * Company: 易象
 * @author 钱嘉
 * @version 1.0 2011-11-22
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SecurityContextUtil {
	/**
	 * MD5密码加密
	 * 
	 * @return
	 */
	public static String encodePassword(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}
	
	/**
	 * 
	 * @param type 1纯数字  2纯字母  3数字字母混合  
	 * @param length  密码长度
	 * @return 返回密码长度
	 */
	public static String createRandomPassWord(int type, int length) {
		int numSize=10;
		String codeStr="zxcvbnmlkjhgfdsaqwertyuiopZXCVBNMLKJHGFDSAQWERTYUIOP";
		String passTemp="";
		Random r=new Random();
		for (int i = 0; i <length; i++) {
			switch (type) {
			case 1:
				passTemp+=r.nextInt(numSize);//0-9
				break;
			case 2:
				passTemp+=codeStr.charAt(r.nextInt(codeStr.length()));
				break;
			case 3:
				int choose=r.nextInt(2);//0 or 1
				if (choose==0) {
					passTemp+=r.nextInt(numSize);
				}else {
					passTemp+=codeStr.charAt(r.nextInt(codeStr.length()));
				}
				break;
			}
		}
		return passTemp;
	}
}
