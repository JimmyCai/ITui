package cn.itui.webdevelop.service;

public interface UserService {

	public String doLogin(String username, String password);
	public String doRegister(String username, String password, String code, int type);
}
