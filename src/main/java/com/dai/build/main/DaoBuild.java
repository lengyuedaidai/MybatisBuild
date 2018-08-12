/**
 * 
 */
package com.dai.build.main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dai.build.JDBC.TableUtil;
import com.dai.build.entity.DaoNames;
import com.dai.build.entity.FileInfo;
import com.dai.build.entity.TableInfo;
import com.dai.build.render.RenderBase;
import com.dai.build.render.RenderMapperClass;
import com.dai.build.render.RenderMapperXML;
import com.dai.build.render.RenderModelClass;
import com.dai.build.render.RenderParam;
import com.dai.build.render.RenderServiceClass;
import com.dai.build.render.RenderServiceImplClass;
import com.dai.build.util.PropertiesUtil;
import com.dai.build.util.StringUtil;

import freemarker.template.TemplateException;

/**
 * 功能说明:Dao层创建类
 * 
 * @title
 *
 * @author Dai
 * 
 * @Date 2016年12月3日 下午11:42:16
 *
 *
 */
public class DaoBuild {
	private PropertiesUtil properties;

	private String srcPath;

	private String modelClassPackageName;
	private String modelClassPath;
	private String modelClassTemplatePath;

	private String mapperClassPackageName;
	private String mapperClassPath;
	private String mapperClassTemplatePath;

	private String mapperXMLPackageName;
	private String mapperXMLPath;
	private String mapperXMLTemplatePath;

	private String serviceClassPackageName;
	private String serviceClassPath;
	private String serviceClassTemplatePath;

	private String serviceImplClassPackageName;
	private String serviceImplClassPath;
	private String serviceImplClassTemplatePath;

	private FileInfo modelClassFile;
	private FileInfo mapperClassFile;
	private FileInfo mapperXMLFile;
	private FileInfo serviceClassFile;
	private FileInfo serviceImplClassFile;

	private RenderParam rp = new RenderParam();
	private TableInfo table;

	private boolean overwrite = true;

	private List<RenderBase> buildList = new ArrayList<>();

	public DaoBuild(String tableName) throws ClassNotFoundException, SQLException, IOException {
		if (properties == null) {
			properties = new PropertiesUtil("daoBuildConfig");
		}
		String projectPath = properties.getPropertyByName("target.project");
		if(projectPath.indexOf(":\\")>-1){
			this.srcPath = projectPath;
		}else{
			this.srcPath = System.getProperty("user.dir") + File.separator + projectPath;
		}
		TableUtil ti = TableUtil.getInstance();
		this.table = ti.getTableByName(tableName);
		rp.setTable(table);
		DaoNames dn = new DaoNames(tableName);
		String userType = properties.getPropertyByName("use.type");
		if (userType == null) {
			userType = "model,mapper,mapperXML,service,serviceImpl";
		}
		String[] userTypeArr = userType.split(",");
		for (String type : userTypeArr) {
			switch (type) {
			case "model":
				this.modelClassPackageName = properties.getPropertyByName("model.package");
				this.modelClassPath = StringUtil.getPackagePath(srcPath, modelClassPackageName);
				this.modelClassTemplatePath = properties.getPropertyByName("model.template");

				modelClassFile = new FileInfo(dn.getModelClassName(), dn.getModelClassFile(), modelClassPackageName,
						modelClassPath, modelClassTemplatePath);
				rp.addFileInfo("modelClassFile", modelClassFile);
				buildList.add(new RenderModelClass(rp));
				break;
			case "mapper":
				this.mapperClassPackageName = properties.getPropertyByName("mapper.package");
				this.mapperClassPath = StringUtil.getPackagePath(srcPath, mapperClassPackageName);
				this.mapperClassTemplatePath = properties.getPropertyByName("mapper.template");

				mapperClassFile = new FileInfo(dn.getMapperClassName(), dn.getMapperClassFile(), mapperClassPackageName,
						mapperClassPath, mapperClassTemplatePath);
				rp.addFileInfo("mapperClassFile", mapperClassFile);
				buildList.add(new RenderMapperClass(rp));
				break;
			case "mapperXML":
				this.mapperXMLPackageName = properties.getPropertyByName("mapperXML.package");
				this.mapperXMLPath = StringUtil.getPackagePath(srcPath, mapperXMLPackageName);
				this.mapperXMLTemplatePath = properties.getPropertyByName("mapperXML.template");
				mapperXMLFile = new FileInfo(dn.getMapperXMLName(), dn.getMapperXMLFile(), mapperXMLPackageName,
						mapperXMLPath, mapperXMLTemplatePath);
				rp.addFileInfo("mapperXMLFile", mapperXMLFile);
				buildList.add(new RenderMapperXML(rp));
				break;
			case "service":
				this.serviceClassPackageName = properties.getPropertyByName("service.package");
				this.serviceClassPath = StringUtil.getPackagePath(srcPath, serviceClassPackageName);
				this.serviceClassTemplatePath = properties.getPropertyByName("service.template");
				serviceClassFile = new FileInfo(dn.getServiceClassName(), dn.getServiceClassFile(),
						serviceClassPackageName, serviceClassPath, serviceClassTemplatePath);
				rp.addFileInfo("serviceClassFile", serviceClassFile);
				buildList.add(new RenderServiceClass(rp));
				break;
			case "serviceImpl":
				this.serviceImplClassPackageName = properties.getPropertyByName("serviceImpl.package");
				this.serviceImplClassPath = StringUtil.getPackagePath(srcPath, serviceImplClassPackageName);
				this.serviceImplClassTemplatePath = properties.getPropertyByName("serviceImpl.template");
				serviceImplClassFile = new FileInfo(dn.getServiceImplClassName(), dn.getServiceImplClassFile(),
						serviceImplClassPackageName, serviceImplClassPath, serviceImplClassTemplatePath);
				rp.addFileInfo("serviceImplClassFile", serviceImplClassFile);
				buildList.add(new RenderServiceImplClass(rp));
				break;
			}
		}
		overwrite = StringUtil.toBoolean(properties.getPropertyByName("build.overwrite"));

	}

	public void addFileInfo(String name, FileInfo fileInfo) {
		rp.addFileInfo(name, fileInfo);
	}

	public void addRender(RenderBase render) {
		buildList.add(render);
	}

	public void create() throws IllegalArgumentException, IllegalAccessException, TemplateException, IOException {
		for (RenderBase renderBase : buildList) {
			renderBase.create(overwrite);
		}
	}

	public void delete() throws IllegalArgumentException, IllegalAccessException, TemplateException, IOException {
		for (RenderBase renderBase : buildList) {
			renderBase.delete();
		}
	}
}
