package com.dai.build.render;

import java.io.IOException;

import com.dai.build.entity.FileInfo;



/**
 * @author Dai
 *
 */
public class RenderServiceImplClass extends RenderBase {

	/**
	 * @throws IOException
	 */
	public RenderServiceImplClass(RenderParam param) throws IOException {
		super(param);
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * com.account.gms.test.build.render.RenderBase#getFileInfoByParam(com.tpt.
	 * vehicle.test.build.render.RenderParam)
	 */
	@Override
	public FileInfo getFileInfoByParam(RenderParam param) {
		return param.getFileInfo("serviceImplClassFile");
	}

}
