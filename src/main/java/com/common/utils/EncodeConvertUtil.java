package com.common.utils;

/**
 * @author zzx
 * @tiem 2017年8月25日
 * 用来不同编码之间的字符串转换格式
 */
public class EncodeConvertUtil {
	
	/**
	 * 将字符串转为unicode编码
	 * @param str 被转换的字符串
	 * @return
	 * @author zzx
	 * 2017年8月25日
	 */
	public static String str2unicode(String str){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {  
	        // 取出每一个字符  
	        char c = str.charAt(i);  
	        // 转换为unicode  
	        sb.append("\\u" + Integer.toHexString(c));  //toHexString 以十六进制的无符号整数形式返回一个整数参数的字符串表示形式。
	    }  
		return sb.toString();
	}
	
	/**
	 * 将unicode转为字符串
	 * @param unicode
	 * @return
	 * 2017年8月25日
	 * @author zzx
	 */
	public static String unicode2str(String unicode){
		StringBuffer sb = new StringBuffer();
		String[] hex = unicode.split("\\\\u");  
		for (int i = 1; i < hex.length; i++) {
			// 转换出每一个代码点  
	        int data = Integer.parseInt(hex[i], 16);  
	        // 追加成string  
	        sb.append((char) data);  
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(EncodeConvertUtil.str2unicode("你好"));
		// 阿拉伯字符串
		System.out.println(EncodeConvertUtil.unicode2str("\\u0646\\u0635\\u064a\\u0628\\u0639\\u0631\\u0628\\u0641\\u064a\\u062f\\u064a\\u0648"));
	}

}
