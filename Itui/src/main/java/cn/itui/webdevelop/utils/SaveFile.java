package cn.itui.webdevelop.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.*;
import org.springframework.web.multipart.MultipartFile;

/*
 * 保存文件类
 * */
public class SaveFile {
	//保存图片
	public static void saveImage(String filename, MultipartFile image) throws IOException {
		//新建文件
		File file = new File("F:\\images\\"+ filename);//test
		FileUtils.writeByteArrayToFile(file,image.getBytes());
	}

}
