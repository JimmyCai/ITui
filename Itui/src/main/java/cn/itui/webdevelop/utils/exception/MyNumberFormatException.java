package cn.itui.webdevelop.utils.exception;

public class MyNumberFormatException extends NumberFormatException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5469485631569326050L;
	private String message;
	
	public MyNumberFormatException() {
		super();
		this.message = "出现非法字符！";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
