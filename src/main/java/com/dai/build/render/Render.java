package com.dai.build.render;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * @author Dai
 *
 */
public abstract class Render {
	protected String templatePath;
	/**
	 * 编码格式
	 */
	protected String encoding = "UTF-8";

	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * freemarker配置
	 */
	Configuration config;
	public RenderParam param;

	public Render(RenderParam param) {
		this.param = param;
		config = new Configuration();
		ClassTemplateLoader ctl = new ClassTemplateLoader(this.getClass(), "/");
		System.out.println(this.getClass().getResource("/"));
		TemplateLoader tl = config.getTemplateLoader();
		TemplateLoader[] loaders = new TemplateLoader[] { tl, ctl };
		MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
		config.setTemplateLoader(mtl);
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * 渲染执行方法
	 * 
	 * @param param
	 *            渲染参数
	 * @throws TemplateException
	 *             模版异常
	 * @throws IOException
	 *             IO异常
	 */
	public abstract void create() throws Exception;

	public abstract void create(boolean overwrite) throws Exception;

	public abstract void delete();

}
