package com.dai.build.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 存储表结构信息
 * 
 * @author Dai
 *
 */
public class TableInfo {

	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 表名
	 */
	private NameModel multipleTableNames;
	/**
	 * 注释
	 */
	private String remark;
	/**
	 * 主键列名
	 */
	private Set<String> primaryKeys = new HashSet<>();

	/**
	 * 字段信息
	 */
	private List<ColumnInfo> columns = new ArrayList<>();

	public TableInfo(String tableName) {
		setTableName(tableName);
		setMultipleTableNames(new NameModel(tableName));
	}

	/**
	 * 添加主键
	 * 
	 * @param column
	 */
	public void addPrimaryKey(String columnName) {
		if (primaryKeys == null) {
			primaryKeys = new HashSet<>();
		}
		primaryKeys.add(columnName);
	}

	/**
	 * 添加主键
	 * 
	 * @param column
	 */
	public void addPrimaryKeys(Set<String> primaryKeys) {
		if (this.primaryKeys == null) {
			this.primaryKeys = new HashSet<>();
		}
		this.primaryKeys.addAll(primaryKeys);
	}

	/**
	 * 添加单字段
	 * 
	 * @param column
	 */
	public void addColumn(ColumnInfo column) {
		if (this.columns == null) {
			this.columns = new ArrayList<>();
		}
		this.columns.add(column);
	}

	/**
	 * 添加多字段
	 * 
	 * @param columns
	 */
	public void addColumns(List<ColumnInfo> columns) {
		if (this.columns == null) {
			this.columns = new ArrayList<>();
		}
		this.columns.addAll(columns);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ColumnInfo> getColumns() {
		return columns;
	}

	public Set<String> getPrimaryKeys() {
		return primaryKeys;
	}

	public String getPrimaryKey() {
		String pk = null;
		for (String primaryKey : primaryKeys) {
			pk = primaryKey;
		}
		return pk;
	}

	/**
	 * @return multipleTableNames
	 */
	public NameModel getMultipleTableNames() {
		return multipleTableNames;
	}

	/**
	 * @param multipleTableNames
	 *            要设置的 multipleTableNames
	 */
	public void setMultipleTableNames(NameModel multipleTableNames) {
		this.multipleTableNames = multipleTableNames;
	}

}
