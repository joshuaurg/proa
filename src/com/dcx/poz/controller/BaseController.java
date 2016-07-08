package com.dcx.poz.controller;

import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.dcx.poz.util.QiniuUtil;

public class BaseController{
	
	/**
	 * 上传文件到七牛
	 * @param request
	 * @param fileName
	 * @param extension
	 * @param imagePrefix
	 * @param mediaType : 1图片 2音频 3视频
	 * @return
	 */
	protected String qiniuUpload(HttpServletRequest request,String fileName,String extension,String prefix,Integer mediaType){
		MultipartFile  multiFile = null;
    	String url = null;
    	try {
    		if(request instanceof MultipartHttpServletRequest){
    			MultipartHttpServletRequest mur = (MultipartHttpServletRequest) request;
    			multiFile = mur.getFile(fileName);
    			if(!multiFile.isEmpty()){
    				byte[] bytes = multiFile.getBytes();
    				JSONObject resJson = QiniuUtil.qiniuUploadWithOps(bytes,extension,mediaType, prefix);
    				url = resJson.getString("key");
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

}
