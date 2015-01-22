package cn.itui.webdevelop.utils.exception;

public class ParameterAbsenceException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3540697282103270636L;
	private static final String MESSAGE = "缺少参数";
	
	public ParameterAbsenceException() {
		super(MESSAGE);
	}
}
