package cn.itui.webdevelop.utils.exception;

public class SessionExceedException extends Exception{
	private static String message = "回话过期";
	
	public SessionExceedException() {
		super(message);
	}

}
