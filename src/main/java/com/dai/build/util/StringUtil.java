package com.dai.build.util;

import java.io.File;
import java.util.List;

/**
 * @author Dai
 *
 */
public class StringUtil {
	/**
	 * 转换字符串为boolean值，"NO","FALSE"转为false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean toBoolean(String str) {
		if (isBlankByTrim(str))
			return false;
		String n_str = str.trim().toUpperCase();
		switch (n_str) {
		case "YES":
			return true;
		case "NO":
			return false;
		case "TRUE":
			return true;
		case "FALSE":
			return false;
		default:
			return Boolean.parseBoolean(str);
		}
	}

	/**
	 * isEmpty(null)+"--"+isEmpty("")+"--"+isEmpty("  ")+"--"+isEmpty(" dai ")
	 * true--false--false--false 判断字符串是否为null
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null);
	}

	/**
	 * isBlank(null)+"--"+isBlank("")+"--"+isBlank("  ")+"--"+isBlank(" dai ")
	 * true--true--false--false 判断字符串是否为null或空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return (isEmpty(str)) || (str.length() == 0);
	}

	/**
	 * isBlankByTrim(null)+"--"+isBlankByTrim("")+"--"+isBlankByTrim("  "
	 * )+"--"+isBlankByTrim(" dai ") true--true--true--false
	 * 判断去空格后，字符串是否为null或空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlankByTrim(String str) {
		return (isEmpty(str)) || (str.trim().length() == 0);
	}

	/**
	 * 删除下划线，并将首字母和下划线后字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceUnderlineToUpper(String str) {
		return replaceStringToUpper(str, "_", "");
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstCharacterToUpper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 替换字符串并让它的下一个字母为大写
	 * 
	 * @param str
	 *            要转换的字符串
	 * @param regex
	 *            要被替换的字符串，此字符串后面值将大写
	 * @param replacement
	 *            替换的新字符串
	 * @return
	 */
	public static String replaceStringToUpper(String str, String regex,
			String replacement) {

		String newString = "";
		int first = 0;
		while (str.indexOf(regex) != -1) {
			first = str.indexOf(regex);
			if (first != str.length()) {
				newString = newString + str.substring(0, first) + replacement;
				str = str.substring(first + regex.length(), str.length());
				str = firstCharacterToUpper(str);
			}
		}
		newString = newString + str;
		return newString;
	}

	public static String getPackagePath(String srcPath, String pack) {
		if (isBlankByTrim(pack)) {
			return srcPath;
		}
		if (isBlankByTrim(srcPath)) {
			return pack.replaceAll("[.]", "\\\\");
		} else {
			return srcPath + File.separator + pack.replaceAll("[.]", "\\\\");
		}
	}

	public static String join(Object[] o, String flag) {
		StringBuffer str_buff = new StringBuffer();
		for (int i = 0, len = o.length; i < len; i++) {
			str_buff.append(String.valueOf(o[i]));
			if (i < len - 1)
				str_buff.append(flag);
		}
		return str_buff.toString();
	}

	public static String join(List o, String flag) {
		return join(o.toArray(), flag);
	}

	public static void main(String[] args) {
		// String a = "aa_bb_cc";
		System.out
				.println(getPackagePath(
						System.getProperty("user.dir") + File.separator
								+ "src\\main\\java",
						"com.account.gms.core.util"));
	}

}
