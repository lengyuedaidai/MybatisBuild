package com.dai.build.render;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import freemarker.template.Template;
import freemarker.template.TemplateException;



/**
 * @author Dai
 *
 */
public class RenderMapperClass extends Render {
	protected String templatePath ="com/dai/build/template/mapperClass.flt";	
	protected String outputPath ="E:/";	
	Template t;
	public Template getT() throws IOException {
		if(t==null){
			t = config.getTemplate(templatePath, encoding);
		}
		return t;
	}
	public void setTemplatePath(String templatePath) throws IOException {
		this.templatePath = templatePath;
		t = config.getTemplate(templatePath, encoding);
	}
	/**
	 * 文件名后缀
	 */
	protected static final String suffix =".java";	
	public RenderMapperClass() throws IOException {
		super();
	}
	public void run(RenderParam param) throws TemplateException, IOException, IllegalArgumentException, IllegalAccessException {
		run(param,true);
	}
	public void run(RenderParam param, boolean overwrite) throws TemplateException, IOException, IllegalArgumentException, IllegalAccessException {
		String fileFullName = param.getMapperName() + suffix;
		String filePath = outputPath+File.separator+fileFullName;
		File file = new File(filePath);
		if(file.exists()){
			if(overwrite){
				logger.warn(fileFullName+"文件已存在，覆盖原文件");
			}else{
				logger.warn(fileFullName+"文件已存在，不覆盖原文件");
				return;
			}
		}else{
			file.createNewFile();
		}
		HashMap<String, Object> model = param.toHashMap();
		FileWriter fw = new FileWriter(file);
		getT().process(model, fw);
		fw.flush();
		fw.close();
	}
	
}
