package com.dai.build.entity;

/**
 * 存储字段信息
 * 
 * @author Dai
 *
 */
public class ColumnInfo {
	/**
	 * 字段名
	 */
	private String columnName;
	/**
	 * 字段各类名称
	 */
	private NameModel multipleColumnNames;
	/**
	 * 字段类型
	 */
	private String columnType;
	/**
	 * 字段各种类型
	 */
	private TypeModel multipleColumnType;
	/**
	 * 是否允许为空
	 */
	private Boolean nullable = true;
	/**
	 * 是否为主键
	 */
	private Boolean isKey = false;
	/**
	 * 备注
	 */
	private String remark;

	public ColumnInfo(String columnName, String columnType) {
		setColumnName(columnName);
		setColumnType(columnType);
		setMultipleColumnNames(new NameModel(this.columnName));
		setMultipleColumnType(new TypeModel(this.columnType));
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
		if (columnType.toUpperCase().equals("INT")) {
			columnType = "INTEGER";
		}
		this.columnType = columnType;
	}

	public Boolean getIsKey() {
		return isKey;
	}

	public void setIsKey(Boolean isKey) {
		this.isKey = isKey;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return multipleColumnNames
	 */
	public NameModel getMultipleColumnNames() {
		return multipleColumnNames;
	}

	/**
	 * @param multipleColumnNames
	 *            要设置的 multipleColumnNames
	 */
	public void setMultipleColumnNames(NameModel multipleColumnNames) {
		this.multipleColumnNames = multipleColumnNames;
	}

	/**
	 * @return multipleColumnType
	 */
	public TypeModel getMultipleColumnType() {
		return multipleColumnType;
	}

	/**
	 * @param multipleColumnType
	 *            要设置的 multipleColumnType
	 */
	public void setMultipleColumnType(TypeModel multipleColumnType) {
		this.multipleColumnType = multipleColumnType;
	}

}
