package com.dai.build.render;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.dai.build.entity.FileInfo;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Dai
 *
 */
public abstract class RenderBase extends Render {
	Template t;

	public Template getT() throws IOException {
		if (t == null) {
			t = config.getTemplate(templatePath, encoding);
		}
		return t;
	}

	public void setTemplatePath(String templatePath) throws IOException {
		this.templatePath = templatePath;
		t = config.getTemplate(templatePath, encoding);
	}

	public RenderBase(RenderParam param) throws IOException {
		super(param);
	}

	public void create() throws TemplateException, IOException,
			IllegalArgumentException, IllegalAccessException {
		create(true);
	}

	public void create(boolean overwrite) throws TemplateException, IOException,
			IllegalArgumentException, IllegalAccessException {
		FileInfo fileInfo = getFileInfoByParam(param);
		String fileFullName = fileInfo.getFileName();
		String outputPath = fileInfo.getPath();
		String filePath = outputPath + File.separator + fileFullName;
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());
		if (file.exists()) {
			if (overwrite) {
				logger.warn(fileFullName + "文件已存在，覆盖原文件");
			} else {
				logger.warn(fileFullName + "文件已存在，不覆盖原文件");
				return;
			}
		} else {
			  //判断目标文件所在的目录是否存在
	        if(!file.getParentFile().exists()) {
	            //如果目标文件所在的目录不存在，则创建父目录
	        	logger.warn("目标文件所在目录不存在，创建"+file.getParentFile().getAbsolutePath());
	            if(!file.getParentFile().mkdirs()) {
	            	logger.error("创建目标文件目录:"+file.getParentFile().getAbsolutePath()+"失败！");
	                return;
	            }
	            file.createNewFile();
	        }else{
				file.createNewFile();
	        }

		}
		HashMap<String, Object> model = param.toHashMap();
		FileWriter fw = new FileWriter(file);
		setTemplatePath(fileInfo.getTemplatePath());
		getT().process(model, fw);
		fw.flush();
		fw.close();
	}

	public void delete() {
		FileInfo fileInfo = getFileInfoByParam(param);
		String fileFullName = fileInfo.getFileName();
		String outputPath = fileInfo.getPath();
		String filePath = outputPath + File.separator + fileFullName;
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());
		if (file.exists()) {
			logger.warn(fileFullName + "文件存在，进行删除");
			file.delete();
		}
	}

	public abstract FileInfo getFileInfoByParam(RenderParam param);
}
