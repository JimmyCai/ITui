package cn.itui.webdevelop.utils;

import org.springframework.web.multipart.MultipartFile;

import cn.itui.webdevelop.utils.exception.FileUploadException;

/*
 * 校验文件类
 * */

public class ValidateFile {
	
	public static int validateImage(MultipartFile image) throws Exception{
		//图片格式为jpeg和png
		if((!image.getContentType().equals("image/jpeg"))||(!image.getContentType().equals("image/png")))
			throw FileUploadException.getInstance();
		return 1;
	}

}
