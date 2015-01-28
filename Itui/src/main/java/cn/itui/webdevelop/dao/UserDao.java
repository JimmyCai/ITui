package cn.itui.webdevelop.dao;

import java.util.HashMap;

public interface UserDao {
	public HashMap<String, Object> match(String username, String password);

	public int insertUser(String username, String password, String code, int type);
}
