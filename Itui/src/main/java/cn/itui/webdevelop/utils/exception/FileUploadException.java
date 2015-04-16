package cn.itui.webdevelop.utils.exception;

public class FileUploadException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3337707338769171950L;
	public static final String MESSAGE = "文件上传失败，请校验上传文件格式及大小";
	private static FileUploadException instance;
	
	public synchronized static FileUploadException getInstance() {
		if(instance == null)
			instance = new FileUploadException();
		return instance;
	}
	
	private FileUploadException() {
		super(MESSAGE);
	}

}
