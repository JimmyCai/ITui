package cn.itui.webdevelop.utils.exception;

public class SessionExceedException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6150326441972158104L;
	private static String message = "回话过期";
	
	public SessionExceedException() {
		super(message);
	}

}
