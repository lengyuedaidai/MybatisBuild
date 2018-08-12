package com.dai.build.render;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.dai.build.entity.FileInfo;
import com.dai.build.entity.TableInfo;


/**
 * @author Dai 渲染参数
 *
 */
public class RenderParam {
	/*
	 * private FileInfo modelClassFile; private FileInfo mapperClassFile;
	 * private FileInfo mapperXMLFile; private FileInfo serviceClassFile;
	 * private FileInfo serviceImplClassFile;
	 */
	private TableInfo table;
	private Map<String, FileInfo> fileInfoMap = new HashMap<String, FileInfo>();

	public FileInfo getFileInfo(String name) {
		if (fileInfoMap.containsKey(name)) {
			return fileInfoMap.get(name);
		} else {
			return null;
		}
	}

	public void addFileInfo(String name, FileInfo fileInfo) {
		fileInfoMap.put(name, fileInfo);
	}

	public HashMap<String, Object> toHashMap()
			throws IllegalArgumentException, IllegalAccessException {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			hm.put(field.getName(), field.get(this));
		}
		return hm;
	}

	/**
	 * @return table
	 */
	public TableInfo getTable() {
		return table;
	}

	/**
	 * @param table
	 *            要设置的 table
	 */
	public void setTable(TableInfo table) {
		this.table = table;
	}

}
