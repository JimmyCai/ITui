package cn.itui.webdevelop.utils.exception;

public class SessionExceedException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6150326441972158104L;
	private static String message = "回话过期";
	private static SessionExceedException instance;
	
	public synchronized static SessionExceedException getInstance() {
		if(instance == null)
			instance = new SessionExceedException();
		return instance;
	}
	
	private SessionExceedException() {
		super(message);
	}

}
