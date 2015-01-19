package cn.itui.webdevelop.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserLoginInfo {
	public static final String USERNAME = "userName";
	public static final String LOGINDATE = "loginDate";
	public static final String LOGINIP = "loginIp";
	public static final String LEAVEDATE ="leaveDate";
	
	private String userName;
	private Date loginDate;
	private String loginIp;
	private Date leaveDate;
	
	public UserLoginInfo(String userName, Date loginDate, String loginIp) {
		this.userName = userName;
		this.loginDate = loginDate;
		this.loginIp = loginIp;
	}
	
	public static UserLoginInfo extractUserLoginInfoFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(USERNAME);
		Date loginDate = (Date) session.getAttribute(LOGINDATE);
		String loginIp = (String) session.getAttribute(LOGINIP);
		return new UserLoginInfo(userName, loginDate, loginIp);
	}
	
	public String toLoggerString() {
		StringBuilder loggerBuilder = new StringBuilder();
		loggerBuilder.append(" USER NAME:");
		loggerBuilder.append(userName);
		loggerBuilder.append(" LOGINDATE:");
		loggerBuilder.append(loginDate.toString());
		loggerBuilder.append(" LOGIN IP:");
		loggerBuilder.append(loginIp);
		return loggerBuilder.toString();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
}
