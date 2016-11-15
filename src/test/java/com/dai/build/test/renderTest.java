package com.dai.build.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Test;

import com.dai.build.JDBC.TableInfo;
import com.dai.build.JDBC.TableUtil;
import com.dai.build.render.Render;
import com.dai.build.render.RenderMapperClass;
import com.dai.build.render.RenderMapperXML;
import com.dai.build.render.RenderParam;

import freemarker.template.TemplateException;;

public class renderTest {
	@Test
	public void test() throws Exception {
		Render r = new RenderMapperXML();
		RenderParam rp = new RenderParam();
		TableUtil ti = TableUtil.getInstance();
		TableInfo table = ti.getTableByName("CHANNEL");
		rp.setTable(table);
		rp.setSrcPath("G:\\ForestarWork\\com.fs.build\\src\\");
		rp.setPackageDao("com.dai.build.test");
		HashMap<String, Object> a = rp.toHashMap();
		System.err.println("aa");
		/*String packageSelf="com.team.vehicle.web.dao";
		String packageModel="com.team.vehicle.web.model";
		String fileName="CsMapper";
		String modelName="Cs";
		String pkType="Long";
		rp.setFileName(fileName);
		rp.setModelName(modelName);
		rp.setPackageModel(packageModel);
		rp.setPackageSelf(packageSelf);
		rp.setPkType(pkType);*/
		r.run(rp);
	}
	public static void main(String[] args) throws Exception {
		renderTest r= new renderTest();
		r.test();
	}

}
