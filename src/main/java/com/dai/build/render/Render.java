package com.dai.build.render;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;


/**
 * @author Dai
 *
 */
public abstract class Render {
	/**
	 * 模版路径
	 */
	protected String templatePath="";
	/**
	 * 编码格式
	 */
	protected String encoding="UTF-8";
	/**
	 * 输出位置
	 */
	protected String outputPath =getClass().getResource("/").toString();
	
	protected Logger logger = LoggerFactory.getLogger(getClass());	
	/**
	 * freemarker配置
	 */
	Configuration config;
	/**
	 * 日志
	 */
	Render() throws IOException{
		config = new Configuration();
		ClassTemplateLoader ctl = new ClassTemplateLoader(this.getClass(), "/");
		TemplateLoader tl = config.getTemplateLoader();
		TemplateLoader[] loaders = new TemplateLoader[] { tl, ctl };
		MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
		config.setTemplateLoader(mtl);	
	}
	
	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) throws IOException {
		this.templatePath = templatePath;
	}
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	/**
	 * 渲染执行方法
	 * @param param 渲染参数
	 * @throws TemplateException 模版异常
	 * @throws IOException IO异常
	 */
	public abstract void run(RenderParam param) throws Exception;
	public abstract void run(RenderParam param,boolean overwrite) throws Exception;
	
	
}
