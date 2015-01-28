package cn.itui.webdevelop.service.impl;

import java.util.HashMap;

import cn.itui.webdevelop.dao.UserDao;
import cn.itui.webdevelop.dao.UserInfoDao;
import cn.itui.webdevelop.service.UserService;
import cn.itui.webdevelop.utils.ResponseUtil;

public class UserServiceImpl implements UserService{
	private UserDao userDao;
	private UserInfoDao userInfoDao;

	public String doLogin(String username, String password) {
		HashMap<String, Object> usermap= userDao.match(username, password);
		if (usermap.get("code") != null){
			String logo = userInfoDao.getUserLogo((Integer)usermap.get("id"));
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("login", "true");
			map.put("logo", logo);
			map.put("username", username);
			return ResponseUtil.wrapNormalReturn(map);
		}else {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("login", "false");
			map.put("msg", "用户名或密码错误");
			return ResponseUtil.wrapNormalReturn(map);
		}
	}

	public String doRegister(String username, String password, String code, int type) {
		int id = userDao.insertUser(username, password, code, type);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id > 0){
			map.put("regester", "true");
			map.put("msg", "注册成功！请验证邮箱");
		}else {
			map.put("regester", "false");
			map.put("msg", "用户名已存在");			
		}
		return ResponseUtil.wrapNormalReturn(map);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

}
