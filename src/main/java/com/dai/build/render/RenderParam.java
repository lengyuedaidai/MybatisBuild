package com.dai.build.render;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.dai.build.JDBC.ColumnInfo;
import com.dai.build.JDBC.TableInfo;
import com.dai.build.util.StringUtil;

/**
 * @author Dai
 * 渲染参数
 *
 */
public class RenderParam {
	private String srcPath="E:/";
	private String packageDao="com.team.vehicle.web.dao";
	private String packageModel="com.team.vehicle.web.model";
	private String packageService="com.team.vehicle.web.model";
	private String packageServiceIpml="com.team.vehicle.web.model";
	private String packageDaoPath="E:/com/team/vehicle/web/dao";
	private String packageModelPath="E:/com/team/vehicle/web/model";
	private String packageServicePath="E:/com/team/vehicle/web/model";
	private String packageServiceIpmlPath="E:/com/team/vehicle/web/model";
	private HashMap<String,Object> table = new HashMap();
	private String SelectParamClass ="com.team.vehicle.web.model.SelectParam";
	
	private String mapperName="CsMapper";
	private String serviceName="CsService";
	private String serviceImplName="CsServiceImpl";
	private String ModelName="Cs";
	
	public HashMap<String, Object> toHashMap() throws IllegalArgumentException, IllegalAccessException{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			hm.put(field.getName(),field.get(this));
		}
		return hm;
	} 
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
		setPackageDaoPath(srcPath+packageDao.replace(".", "/"));
		setPackageModelPath(srcPath+packageModel.replace(".", "/"));
		setPackageServicePath(srcPath+packageService.replace(".", "/"));
		setPackageServiceIpmlPath(srcPath+packageServiceIpml.replace(".", "/"));
	}
	public void setPackageDao(String packageDao) {
		this.packageDao = packageDao;
		setPackageDaoPath(srcPath+packageDao.replace(".", "/"));
	}
	public void setPackageModel(String packageModel) {
		this.packageModel = packageModel;
		setPackageModelPath(srcPath+packageModel.replace(".", "/"));
	}
	public void setPackageService(String packageService) {
		this.packageService = packageService;
		setPackageServicePath(srcPath+packageService.replace(".", "/"));
	}
	public void setPackageServiceIpml(String packageServiceIpml) {
		this.packageServiceIpml = packageServiceIpml;
		setPackageServiceIpmlPath(srcPath+packageServiceIpml.replace(".", "/"));
	}

	public void setTable(TableInfo tableInfo) {
		List<ColumnInfo> columnsList = tableInfo.getColumns();
		String tableName = tableInfo.getTableName().toLowerCase();
		String modelName = StringUtil.replaceUnderlineToUpper(tableName);
		this.table.put("tableName", tableName);
		this.table.put("modelName", modelName);
		setFileName(modelName);
		String pkName = tableInfo.getPrimaryKey()==null?"":tableInfo.getPrimaryKey().toLowerCase();
		List<HashMap<String, Object>> columns = new ArrayList<>();
		HashMap<String, Object> column;
		for (ColumnInfo columnInfo : columnsList) {
			column = new HashMap<>();
			String name = columnInfo.getColumnName().toLowerCase();
			column.put("columnName", name);
			column.put("modelName", StringUtil.replaceUnderlineToUpper(name));
			column.put("columnType", columnInfo.getColumnType());
			column.put("modelType", getTypeBySQLType(columnInfo.getColumnType()));
			column.put("remark", columnInfo.getRemark());
			if(name.equals(pkName)){
				this.table.put("primaryKeyColumn", column);
			}
			columns.add(column);
		}
		this.table.put("columns", columns);
	}
	
	public HashMap<String, Object> getTable() {
		return table;
	}
	public String getTypeBySQLType(String Sqltype) {
		if(!StringUtil.isBlankByTrim(Sqltype)){
			switch (Sqltype.toUpperCase()) {
				case "INT":
					return "java.lang.Integer";
				case "BIGINT":
					return "java.lang.Integer";
				case "DOUBLE":
					return "java.lang.Double";
				case "FLOAT":
					return "java.lang.Float";
				case "VARCHAR":
					return "java.lang.String";
				case "DATE":
					return "java.util.Date";	
				default:
					return "java.lang.String";
			}
		}
		return null;
	}
	
	
	private void setFileName(String name){
		setMapperName(name+"Mapper");
		setServiceName(name+"Service");
		setServiceImplName(name+"ServiceImpl");
		setModelName(name);
	}

	public String getMapperName() {
		return mapperName;
	}
	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceImplName() {
		return serviceImplName;
	}
	public void setServiceImplName(String serviceImplName) {
		this.serviceImplName = serviceImplName;
	}
	public String getModelName() {
		return ModelName;
	}
	public void setModelName(String modelName) {
		ModelName = modelName;
	}
	public String getPackageDaoPath() {
		return packageDaoPath;
	}
	private void setPackageDaoPath(String packageDaoPath) {
		this.packageDaoPath = packageDaoPath;
	}
	public String getPackageModelPath() {
		return packageModelPath;
	}
	private void setPackageModelPath(String packageModelPath) {
		this.packageModelPath = packageModelPath;
	}
	public String getPackageServicePath() {
		return packageServicePath;
	}
	private void setPackageServicePath(String packageServicePath) {
		this.packageServicePath = packageServicePath;
	}
	public String getPackageServiceIpmlPath() {
		return packageServiceIpmlPath;
	}
	private void setPackageServiceIpmlPath(String packageServiceIpmlPath) {
		this.packageServiceIpmlPath = packageServiceIpmlPath;
	}
	public String getSrcPath() {
		return srcPath;
	}
	public String getPackageDao() {
		return packageDao;
	}
	public String getPackageModel() {
		return packageModel;
	}
	public String getPackageService() {
		return packageService;
	}
	public String getPackageServiceIpml() {
		return packageServiceIpml;
	}
	public String getSelectParamClass() {
		return SelectParamClass;
	}
	public void setSelectParamClass(String selectParamClass) {
		SelectParamClass = selectParamClass;
	}
	
}
