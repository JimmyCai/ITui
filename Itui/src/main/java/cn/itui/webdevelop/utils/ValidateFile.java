package cn.itui.webdevelop.utils;

import org.springframework.web.multipart.MultipartFile;

import cn.itui.webdevelop.utils.exception.FileUploadException;

/*
 * 校验文件类
 * */

public class ValidateFile {
	
	public static int validateImage(MultipartFile image) throws Exception{
		System.out.println(image.getContentType());
		//图片格式为jpeg、png、jpeg
		if((!image.getContentType().equals("image/jpeg"))&&(!image.getContentType().equals("image/png"))&&(!image.getContentType().equals("image/jpg")))
			throw FileUploadException.getInstance();
		return 1;
	}

}
