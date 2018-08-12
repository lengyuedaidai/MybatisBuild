package com.dai.build.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 配置文件读取通用方法类
 * 
 * @author ZYB
 *
 */
public class PropertiesUtil {
	private Properties Props = new Properties();

	/**
	 * 构造方法
	 * 
	 * @param propertiesName
	 *            配置文件名称
	 */
	public PropertiesUtil(String propertiesName) {
		InputStream is = PropertiesUtil.class
				.getResourceAsStream("/" + propertiesName + ".properties");
		try {
			Props.load(new InputStreamReader(is, "UTF-8"));
			System.out.println(propertiesName + "配置文件读取成功");
		} catch (IOException e) {
			System.out.println(propertiesName + "配置文件读取失败,请检查你的配置文件是否存在！");
			e.printStackTrace();
		}
	}

	public String getPropertyByName(String name) {
		return Props.getProperty(name);
	}

}
