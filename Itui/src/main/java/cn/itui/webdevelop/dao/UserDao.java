package cn.itui.webdevelop.dao;

import java.util.HashMap;

public interface UserDao {
	public HashMap<String, Object> match(String email, String password);

	public int insertUser(String email, String password, String code, int type);

	public int activate(String code);

	public int updatePassword(String email, String password);

	public int getLastId();

}
