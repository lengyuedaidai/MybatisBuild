package com.dai.build.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.dai.build.JDBC.ColumnInfo;
import com.dai.build.JDBC.TableInfo;
import com.dai.build.JDBC.TableUtil;
import com.dai.build.render.Render;
import com.dai.build.render.RenderMapperClass;
import com.dai.build.render.RenderParam;

import freemarker.template.TemplateException;;

public class tableInfoTest {
	@Test
	public void test() throws IOException, TemplateException, ClassNotFoundException, SQLException {
		TableUtil ti = TableUtil.getInstance();
		TableInfo table = ti.getTableByName("CHANNEL");
		List<ColumnInfo> columns = table.getColumns();
		System.err.println("表名："+table.getTableName());
		for (ColumnInfo column : columns) {
			System.err.println("字段:"+column.getColumnName()+"--"+column.getColumnType()+"--"+column.getRemark());
		}
		Set<String> pks = table.getPrimaryKeys();
		for (String pk : pks) {
			System.err.println("主键:"+pk);
		}
		
	}

}
