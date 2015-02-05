package cn.itui.webdevelop.utils.exception;

public class NotLoginException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9170486918608380987L;
	public static final String MESSAGE = "请先登录！";
	
	private static NotLoginException instance;
	
	public synchronized static NotLoginException getInstance() {
		if(instance == null)
			instance = new NotLoginException();
		return instance;
	}
	
	private NotLoginException() {
		super(MESSAGE);
	}
	
}
