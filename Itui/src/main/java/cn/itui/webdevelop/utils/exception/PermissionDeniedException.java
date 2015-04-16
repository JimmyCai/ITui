package cn.itui.webdevelop.utils.exception;

public class PermissionDeniedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2793683178683472432L;
	public static final String MESSAGE = "您没有权限进行此操作，请联系管理员！";
	private static PermissionDeniedException instance;
	
	public synchronized static PermissionDeniedException getInstance() {
		if(instance == null)
			instance = new PermissionDeniedException();
		return instance;
	}
	
	private PermissionDeniedException() {
		super(MESSAGE);
	}

}
