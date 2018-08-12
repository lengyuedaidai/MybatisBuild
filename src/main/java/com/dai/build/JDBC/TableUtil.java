package com.dai.build.JDBC;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dai.build.entity.ColumnInfo;
import com.dai.build.entity.TableInfo;
import com.dai.build.util.PropertiesUtil;


/**
 * 获取表结构信息类
 * 
 * @author Dai
 *
 */
public class TableUtil {
	private PropertiesUtil properties;
	private String driver;
	private String url;
	private String user;
	private String password;
	private Connection c;
	private Set<String> tableNames = new HashSet<String>();
	private HashMap<String, TableInfo> tables = new HashMap<String, TableInfo>();
	private HashMap<String, Set<String>> tablesPrimaryKeys = new HashMap<String, Set<String>>();
	private HashMap<String, List<ColumnInfo>> tablesColumns = new HashMap<String, List<ColumnInfo>>();
	private static TableUtil db;

	private TableUtil() throws ClassNotFoundException, SQLException {
		if (properties == null) {
			properties = new PropertiesUtil("daoBuildConfig");
		}
		driver = properties.getPropertyByName("jdbc.driver");
		url = properties.getPropertyByName("jdbc.url");
		user = properties.getPropertyByName("jdbc.username");
		password = properties.getPropertyByName("jdbc.password");
		Class.forName(driver);
		c = DriverManager.getConnection(url, user, password);
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return 数据库连接
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection()
			throws SQLException, ClassNotFoundException {
		if (c.isClosed()) {
			c = DriverManager.getConnection(url, user, password);
		}
		return c;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void closeConnection() throws SQLException, ClassNotFoundException {
		c.close();
	}

	/**
	 * 获取表名集合
	 * 
	 * @return 表名集合
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Set<String> getTableNames()
			throws SQLException, ClassNotFoundException {
		return getTableNames(false);
	}

	/**
	 * 获取表名集合
	 * 
	 * @param refresh
	 *            是否从数据库更新最新信息
	 * @return 表名集合
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Set<String> getTableNames(boolean refresh)
			throws SQLException, ClassNotFoundException {
		if (tableNames.size() < 1 || refresh) {
			DatabaseMetaData meta = getConnection().getMetaData();
			ResultSet rs = meta.getTables(null, null, null,
					new String[] { "TABLE" });
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME").toUpperCase();
				tableNames.add(tableName);
			}
			rs.close();
		}
		return tableNames;
	}

	/**
	 * 获取全部表信息
	 * 
	 * @return 表信息集合
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public HashMap<String, TableInfo> getTables()
			throws SQLException, ClassNotFoundException {
		return getTables(false);
	}

	/**
	 * 获取全部表信息
	 * 
	 * @param refresh
	 *            是否从数据库更新最新信息
	 * @return 表信息集合
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public HashMap<String, TableInfo> getTables(boolean refresh)
			throws SQLException, ClassNotFoundException {
		if (!refresh && tables.size() > 0) {
			return tables;
		}
		Set<String> tableNames = getTableNames(refresh);
		for (String tableName : tableNames) {
			if (!tables.containsKey(tableName)) {
				tables.put(tableName, getTableByName(tableName));
			}
		}
		return tables;
	}

	/**
	 * 通过表名获取表信息
	 * 
	 * @param tableName
	 *            表名
	 * @return 表信息
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public TableInfo getTableByName(String tableName)
			throws SQLException, ClassNotFoundException {
		if (tableName != null) {
			tableName = tableName.toUpperCase();
		} else {
			return null;
		}
		if (!isExistByTableName(tableName)) {
			return null;
		} else if (tables.containsKey(tableName)) {
			return tables.get(tableName);
		}
		TableInfo table = new TableInfo(tableName);
		table.addPrimaryKeys(getPrimaryKeysByName(tableName));
		table.addColumns(getColumnsByName(tableName));
		DatabaseMetaData meta = getConnection().getMetaData();
		ResultSet rs_table = meta.getTables(null, null, tableName,
				new String[] { "TABLE" });
		while (rs_table.next()) {
			String tableRemark = rs_table.getString("REMARKS");
			table.setRemark(tableRemark);
		}
		tables.put(tableName, table);
		rs_table.close();
		return table;
	}

	/**
	 * 通过表名获取表主键信息
	 * 
	 * @param tableName
	 *            表名
	 * @return 表信息
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Set<String> getPrimaryKeysByName(String tableName)
			throws SQLException, ClassNotFoundException {
		if (tableName != null) {
			tableName = tableName.toUpperCase();
		} else {
			return null;
		}
		if (!isExistByTableName(tableName)) {
			return null;
		} else if (tablesPrimaryKeys.containsKey(tableName)) {
			return tablesPrimaryKeys.get(tableName);
		}
		Set<String> primaryKeys = new HashSet<String>();
		DatabaseMetaData meta = getConnection().getMetaData();
		ResultSet pk_rs = meta.getPrimaryKeys(null, null, tableName);
		while (pk_rs.next()) {
			primaryKeys.add(pk_rs.getString("COLUMN_NAME"));
		}
		tablesPrimaryKeys.put(tableName, primaryKeys);
		return primaryKeys;
	}

	/**
	 * 通过表名获取字段信息
	 * 
	 * @param tableName
	 *            表名
	 * @return 字段信息列表
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<ColumnInfo> getColumnsByName(String tableName)
			throws SQLException, ClassNotFoundException {
		if (tableName != null) {
			tableName = tableName.toUpperCase();
		} else {
			return null;
		}
		if (!isExistByTableName(tableName)) {
			return null;
		} else if (tablesColumns.containsKey(tableName)) {
			return tablesColumns.get(tableName);
		}
		Set<String> primaryKeys = getPrimaryKeysByName(tableName);
		List<ColumnInfo> columns = new ArrayList<>();
		DatabaseMetaData meta = getConnection().getMetaData();
		ResultSet rs = meta.getColumns(null, null, tableName, null);
		ColumnInfo ci;
		while (rs.next()) {
			String columnName = rs.getString("COLUMN_NAME");
			String columnType = rs.getString("TYPE_NAME");
			ci = new ColumnInfo(columnName, columnType);
			String remark = rs.getString("REMARKS");
			ci.setRemark(remark);
			if (primaryKeys.contains(columnName)) {
				ci.setIsKey(true);
				ci.setNullable(false);
			}
			columns.add(ci);
		}
		tablesColumns.put(tableName, columns);
		rs.close();
		return columns;
	}

	public boolean isExistByTableName(String tableName)
			throws ClassNotFoundException, SQLException {
		tableName = tableName == null ? null : tableName.toUpperCase();
		Set<String> tableNames = getTableNames();
		return tableNames.contains(tableName);
	}

	/**
	 * 获取单例
	 * 
	 * @return 实例
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static TableUtil getInstance()
			throws ClassNotFoundException, SQLException {
		if (db == null) {
			db = new TableUtil();
		}
		return db;
	}

}
