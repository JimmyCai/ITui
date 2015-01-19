package cn.itui.webdevelop.utils.exception;

public class OtherException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2987953041941437629L;
	public static final String MESSAGE = "出错啦！";
	private static OtherException instance;
	
	public synchronized static OtherException getInstance() {
		if(instance == null)
			instance = new OtherException();
		return instance;
	}
	
	private OtherException() {
		super(MESSAGE);
	}
}
