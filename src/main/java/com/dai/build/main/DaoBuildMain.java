/**
 * 
 */
package com.dai.build.main;

import java.io.IOException;
import java.sql.SQLException;

import com.dai.build.util.PropertiesUtil;


/**
 * 功能说明:Dao层创建主入口
 * 
 * @title
 *
 * @author Dai
 * 
 * @Date 2016年12月3日 下午11:42:16
 *
 *
 */
public class DaoBuildMain {
	public static void main(String[] args) throws Exception {
		PropertiesUtil pu = new PropertiesUtil("daoBuildConfig");
		String tables = pu.getPropertyByName("build.table");
		String type = pu.getPropertyByName("build.type");
		String[] tableNames = tables.split(",");
		if ("create".equals(type.toLowerCase())) {
			for(String tableName:tableNames){
				createTable(tableName);
			}
		} else if ("delete".equals(type.toLowerCase())) {
			for(String tableName:tableNames){
				deleteTable(tableName);
			}
		} else {
			System.err.println("类型错误");
		}	
	}
	public static void createTable(String tableName) throws Exception{
		DaoBuild db = new DaoBuild(tableName);
		db.create();
	}
	public static void deleteTable(String tableName) throws Exception{
		DaoBuild db = new DaoBuild(tableName);
		db.delete();
	}
}
