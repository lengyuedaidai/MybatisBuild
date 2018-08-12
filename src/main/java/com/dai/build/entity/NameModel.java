package com.dai.build.entity;

import com.dai.build.util.StringUtil;

public class NameModel {
	private String dbName;// 数据库字段名称或表名
	private String modelName;// 去除下划线，并将下划线后第一个字母大写的java变量名
	private String modelNameFirstUp;// 第一个字母大写的java变量名
	private String dbNameUpper;// 原始名称的全大写
	private String dbNameLower;// 原始名称的全小写
	private String modelNameUpper;// java变量名全大写
	private String modelNameLower;// java变量名全小写

	public NameModel(String dbName) {
		this.dbName = dbName;
		this.dbNameUpper = this.dbName.toUpperCase();
		this.dbNameLower = this.dbName.toLowerCase();
		this.modelName = StringUtil.replaceUnderlineToUpper(this.dbNameLower);
		this.modelNameFirstUp = StringUtil
				.firstCharacterToUpper(this.dbNameLower);
		this.modelNameUpper = this.modelName.toUpperCase();
		this.modelNameLower = this.modelName.toLowerCase();
	}

	/**
	 * @return dbName
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * @param dbName
	 *            要设置的 dbName
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * @return modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName
	 *            要设置的 modelName
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return modelNameFirstUp
	 */
	public String getModelNameFirstUp() {
		return modelNameFirstUp;
	}

	/**
	 * @param modelNameFirstUp
	 *            要设置的 modelNameFirstUp
	 */
	public void setModelNameFirstUp(String modelNameFirstUp) {
		this.modelNameFirstUp = modelNameFirstUp;
	}

	/**
	 * @return dbNameUpper
	 */
	public String getDbNameUpper() {
		return dbNameUpper;
	}

	/**
	 * @param dbNameUpper
	 *            要设置的 dbNameUpper
	 */
	public void setDbNameUpper(String dbNameUpper) {
		this.dbNameUpper = dbNameUpper;
	}

	/**
	 * @return dbNameLower
	 */
	public String getDbNameLower() {
		return dbNameLower;
	}

	/**
	 * @param dbNameLower
	 *            要设置的 dbNameLower
	 */
	public void setDbNameLower(String dbNameLower) {
		this.dbNameLower = dbNameLower;
	}

	/**
	 * @return modelNameUpper
	 */
	public String getModelNameUpper() {
		return modelNameUpper;
	}

	/**
	 * @param modelNameUpper
	 *            要设置的 modelNameUpper
	 */
	public void setModelNameUpper(String modelNameUpper) {
		this.modelNameUpper = modelNameUpper;
	}

	/**
	 * @return modelNameLower
	 */
	public String getModelNameLower() {
		return modelNameLower;
	}

	/**
	 * @param modelNameLower
	 *            要设置的 modelNameLower
	 */
	public void setModelNameLower(String modelNameLower) {
		this.modelNameLower = modelNameLower;
	}

}
