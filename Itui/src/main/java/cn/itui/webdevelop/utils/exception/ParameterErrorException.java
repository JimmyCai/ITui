package cn.itui.webdevelop.utils.exception;

public class ParameterErrorException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3540697282103270636L;
	public static final String ABSENCE_MESSAGE = "缺少参数";
	public static final String ERROR_MESSAGE = "参数错误";
	public static final String ERROR_PASSWORD = "密码错误";
	private static ParameterErrorException absenceInstance;
	private static ParameterErrorException errorInstance;
	private static ParameterErrorException errorPasswordInstance;
	
	public synchronized static ParameterErrorException getInstance(String message) {
		if(message.equals(ABSENCE_MESSAGE)) {
			if(absenceInstance == null)
				absenceInstance = new ParameterErrorException(message);
			return absenceInstance;
		}else if (message.equalsIgnoreCase(ERROR_PASSWORD)){
			if (errorPasswordInstance == null)
				errorPasswordInstance = new ParameterErrorException(message);
			return errorPasswordInstance;			
		}else {
			if(errorInstance == null)
				errorInstance = new ParameterErrorException(message);
			return errorInstance;
		}
	}
	
	private ParameterErrorException(String message) {
		super(message);
	}

}
