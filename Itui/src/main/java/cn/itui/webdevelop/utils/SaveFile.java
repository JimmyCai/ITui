package cn.itui.webdevelop.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.*;
import org.springframework.web.multipart.MultipartFile;

/*
 * 保存文件类
 * */
public class SaveFile {
	private static String TEACHER_LOGO_PATH;


	public static String getTEACHER_LOGO_PATH() {
		return TEACHER_LOGO_PATH;
	}


	public static void setTEACHER_LOGO_PATH(String tEACHER_LOGO_PATH) {
		TEACHER_LOGO_PATH = tEACHER_LOGO_PATH;
	}


	//保存图片
	public static void saveImage(String filename, MultipartFile image) throws IOException {
		//新建文件
		File file = new File(TEACHER_LOGO_PATH+ filename);
		FileUtils.writeByteArrayToFile(file,image.getBytes());
	}

}
