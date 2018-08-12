/**
 * 
 */
package com.dai.build.entity;

/**
 * 功能说明:
 * 
 * @title
 *
 * @author Dai
 * 
 * @Date 2016年12月3日 下午4:15:28
 *
 *
 */
public class FileInfo {
	private String name;// 文件名称
	private String packageName;// 包名
	private String path;// 所在位置
	private String fileName;// 文件全名
	private String templatePath;// 模版地址

	public FileInfo(String name, String fileName, String packageName,
			String path, String templatePath) {
		this.name = name;
		this.packageName = packageName;
		this.path = path;
		this.fileName = fileName;
		this.templatePath = templatePath;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName
	 *            要设置的 packageName
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            要设置的 path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            要设置的 fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return templatePath
	 */
	public String getTemplatePath() {
		return templatePath;
	}

	/**
	 * @param templatePath
	 *            要设置的 templatePath
	 */
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

}
