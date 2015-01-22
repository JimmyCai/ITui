package cn.itui.webdevelop.utils.exception;

public class DatabaseException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5104826000530868569L;
	public static final String MESSAGE = "数据库查询出错啦！";
	private static DatabaseException instance;
	
	public synchronized static DatabaseException getInstance() {
		if(instance == null)
			instance = new DatabaseException();
		return instance;
	}
	
	private DatabaseException() {
		super(MESSAGE);
	}

}
