package com.dai.build.JDBC;

/**
 * 存储字段信息
 * @author Dai
 *
 */
public class ColumnInfo {
	/**
	 * 字段名
	 */
	private String columnName;
	/**
	 * 字段类型
	 */
	private String columnType;
	/**
	 * 是否为空
	 */
	private boolean nullable;
	/**
	 * 是否为主键
	 */
	private boolean isKey;
	/**
	 * 备注
	 */
	private String remark;
	
	public ColumnInfo(String columnName,String columnType){
		setColumnName(columnName);
		setColumnType(columnType);
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public boolean isKey() {
		return isKey;
	}
	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}
	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
