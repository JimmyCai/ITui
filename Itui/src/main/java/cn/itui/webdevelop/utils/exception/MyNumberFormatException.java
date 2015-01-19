package cn.itui.webdevelop.utils.exception;

public class MyNumberFormatException extends NumberFormatException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5469485631569326050L;
	private static final String MESSAGE = "出现非法字符！";
	private static MyNumberFormatException instance;
	
	public synchronized static MyNumberFormatException getInstance() {
		if(instance == null)
			instance = new MyNumberFormatException();
		return instance;
	}
	
	private MyNumberFormatException() {
		super(MESSAGE);
	}
}
