/**
 * 
 */
package com.dai.build.entity;

import com.dai.build.util.StringUtil;

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
public class DaoNames {
	private String modelClassName;
	private String modelClassFile;
	private String mapperClassName;
	private String mapperClassFile;
	private String mapperXMLName;
	private String mapperXMLFile;
	private String serviceClassName;
	private String serviceClassFile;
	private String serviceImplClassName;
	private String serviceImplClassFile;

	public DaoNames(String tableName) {
		if (StringUtil.isBlankByTrim(tableName))
			return;
		String name = StringUtil
				.replaceUnderlineToUpper(tableName.toLowerCase());
		name = StringUtil.firstCharacterToUpper(name);
		this.modelClassName = name;
		this.modelClassFile = name + ".java";
		this.mapperClassName = name + "Mapper";
		this.mapperClassFile = name + "Mapper.java";
		this.mapperXMLName = name + "Mapper";
		this.mapperXMLFile = name + "Mapper.xml";
		this.serviceClassName = name + "Service";
		this.serviceClassFile = name + "Service.java";
		this.serviceImplClassName = name + "ServiceImpl";
		this.serviceImplClassFile = name + "ServiceImpl.java";
	}

	/**
	 * @return modelClassName
	 */
	public String getModelClassName() {
		return modelClassName;
	}

	/**
	 * @param modelClassName
	 *            要设置的 modelClassName
	 */
	public void setModelClassName(String modelClassName) {
		this.modelClassName = modelClassName;
	}

	/**
	 * @return modelClassFile
	 */
	public String getModelClassFile() {
		return modelClassFile;
	}

	/**
	 * @param modelClassFile
	 *            要设置的 modelClassFile
	 */
	public void setModelClassFile(String modelClassFile) {
		this.modelClassFile = modelClassFile;
	}

	/**
	 * @return mapperClassName
	 */
	public String getMapperClassName() {
		return mapperClassName;
	}

	/**
	 * @param mapperClassName
	 *            要设置的 mapperClassName
	 */
	public void setMapperClassName(String mapperClassName) {
		this.mapperClassName = mapperClassName;
	}

	/**
	 * @return mapperClassFile
	 */
	public String getMapperClassFile() {
		return mapperClassFile;
	}

	/**
	 * @param mapperClassFile
	 *            要设置的 mapperClassFile
	 */
	public void setMapperClassFile(String mapperClassFile) {
		this.mapperClassFile = mapperClassFile;
	}

	/**
	 * @return mapperXMLName
	 */
	public String getMapperXMLName() {
		return mapperXMLName;
	}

	/**
	 * @param mapperXMLName
	 *            要设置的 mapperXMLName
	 */
	public void setMapperXMLName(String mapperXMLName) {
		this.mapperXMLName = mapperXMLName;
	}

	/**
	 * @return mapperXMLFile
	 */
	public String getMapperXMLFile() {
		return mapperXMLFile;
	}

	/**
	 * @param mapperXMLFile
	 *            要设置的 mapperXMLFile
	 */
	public void setMapperXMLFile(String mapperXMLFile) {
		this.mapperXMLFile = mapperXMLFile;
	}

	/**
	 * @return serviceClassName
	 */
	public String getServiceClassName() {
		return serviceClassName;
	}

	/**
	 * @param serviceClassName
	 *            要设置的 serviceClassName
	 */
	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}

	/**
	 * @return serviceClassFile
	 */
	public String getServiceClassFile() {
		return serviceClassFile;
	}

	/**
	 * @param serviceClassFile
	 *            要设置的 serviceClassFile
	 */
	public void setServiceClassFile(String serviceClassFile) {
		this.serviceClassFile = serviceClassFile;
	}

	/**
	 * @return serviceImplClassName
	 */
	public String getServiceImplClassName() {
		return serviceImplClassName;
	}

	/**
	 * @param serviceImplClassName
	 *            要设置的 serviceImplClassName
	 */
	public void setServiceImplClassName(String serviceImplClassName) {
		this.serviceImplClassName = serviceImplClassName;
	}

	/**
	 * @return serviceImplClassFile
	 */
	public String getServiceImplClassFile() {
		return serviceImplClassFile;
	}

	/**
	 * @param serviceImplClassFile
	 *            要设置的 serviceImplClassFile
	 */
	public void setServiceImplClassFile(String serviceImplClassFile) {
		this.serviceImplClassFile = serviceImplClassFile;
	}

}
