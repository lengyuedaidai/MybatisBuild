package com.dai.build.JDBC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 存储表结构信息
 * @author Dai
 *
 */
public class TableInfo {
		
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 注释
	 */
	private String remark;
	/**
	 * 主键列名
	 */
	private Set<String> primaryKeys = new HashSet<>();
	
	/**
	 * 添加主键
	 * @param column
	 */
	public void addPrimaryKey(String columnName){
		if(primaryKeys==null){
			primaryKeys =  new HashSet<>();
		}
		primaryKeys.add(columnName);
	}
	
	/**
	 * 字段信息
	 */
	private List<ColumnInfo> columns = new ArrayList<>();
	
	public TableInfo(String tableName){
		setTableName(tableName);
	}
	
	/**
	 * 添加单字段
	 * @param column
	 */
	public void addColumn(ColumnInfo column){
		if(columns==null){
			columns =  new ArrayList<>();
		}
		columns.add(column);
	}
	/**
	 * 添加多字段
	 * @param columns
	 */
	public void addColumns(List<ColumnInfo> columns){
		if(this.columns==null){
			this.columns =  new ArrayList<>();
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
}
