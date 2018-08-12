package com.dai.build.entity;

import java.util.Date;

import com.dai.build.util.StringUtil;



public class TypeModel {
	private String dbType;// 数据库类型
	private String modelType;// 对应java类型
	private String modelSimpleType;// java类简短的名称

	public TypeModel(String dbType) {
		this.dbType = dbType;
		this.modelType = getModelTypeByDbType(dbType);
		this.modelSimpleType = getModelSimpleTypeByDbType(dbType);
	}

	/**
	 * 
	 * 功能说明:通过数据库类型找到对应的java类型
	 * 
	 * @title
	 * @param dbType
	 * @return
	 */
	public String getModelTypeByDbType(String dbType) {
		if (!StringUtil.isBlankByTrim(dbType)) {
			switch (dbType.toUpperCase()) {
			case "INT":
				return Integer.class.getName();
			case "BIGINT":
				return Long.class.getName();
			case "DOUBLE":
				return Double.class.getName();
			case "FLOAT":
				return Float.class.getName();
			case "VARCHAR":
				return String.class.getName();
			case "DATE":
			case "TIMESTAMP":
			case "TIME":
				return Date.class.getName();
			default:
				return String.class.getName();
			}
		}
		return null;
	}

	/**
	 * 
	 * 功能说明:通过数据库类型找到对应的java类型简短的名称
	 * 
	 * @title
	 * @param dbType
	 * @return
	 */
	public String getModelSimpleTypeByDbType(String dbType) {
		if (!StringUtil.isBlankByTrim(dbType)) {
			switch (dbType.toUpperCase()) {
			case "INT":
				return Integer.class.getSimpleName();
			case "BIGINT":
				return Long.class.getSimpleName();
			case "DOUBLE":
				return Double.class.getSimpleName();
			case "FLOAT":
				return Float.class.getSimpleName();
			case "VARCHAR":
				return String.class.getSimpleName();
			case "DATE":
			case "TIMESTAMP":
			case "TIME":
				return Date.class.getSimpleName();
			default:
				return String.class.getSimpleName();
			}
		}
		return null;
	}

	/**
	 * @return dbType
	 */
	public String getDbType() {
		return dbType;
	}

	/**
	 * @param dbType
	 *            要设置的 dbType
	 */
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	/**
	 * @return modelType
	 */
	public String getModelType() {
		return modelType;
	}

	/**
	 * @param modelType
	 *            要设置的 modelType
	 */
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	/**
	 * @return modelSimpleType
	 */
	public String getModelSimpleType() {
		return modelSimpleType;
	}

	/**
	 * @param modelSimpleType
	 *            要设置的 modelSimpleType
	 */
	public void setModelSimpleType(String modelSimpleType) {
		this.modelSimpleType = modelSimpleType;
	}

}
