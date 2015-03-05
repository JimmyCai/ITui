package cn.itui.webdevelop.service;

import java.util.HashMap;

public interface UserService {
	
	public String doLogin(String email, String password) throws Exception;
	public String doRegister(String email, String password, String code, int type) throws Exception;
	public String activate(String code) throws Exception;
	public boolean sendMail(String to, String subject, String vmFile, HashMap<String, Object> model) throws Exception;
	public String resetPassword(String email, String password) throws Exception;
	public String resendEmail(String email) throws Exception;
	public String sendResetEmail(String email, String password)throws Exception;
}
