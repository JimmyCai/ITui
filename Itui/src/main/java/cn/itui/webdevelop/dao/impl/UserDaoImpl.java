package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.util.DigestUtils;

import cn.itui.webdevelop.dao.UserDao;
import cn.itui.webdevelop.model.User;

public class UserDaoImpl implements UserDao {
	
	private SqlSession sqlSession;
	private User user;

	public HashMap<String, Object> match(String email, String password) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		return sqlSession.selectOne("cn.itui.webdevelop.dao.UserDao.match", map);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insertUser(String email, String password, String code, int type) {
		user.setEmail(email);
		user.setCode(code);
		user.setPassword(password);
		user.setType(type);
		sqlSession.insert("cn.itui.webdevelop.dao.UserDao.insertUser", user);
		return user.getId();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int activate(String code) {
		return sqlSession.update("cn.itui.webdevelop.dao.UserDao.updateActive", code);
	}

	public int updatePassword(String email, String password) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("email", email);
		parameter.put("password", password);
		parameter.put("code", DigestUtils.md5DigestAsHex((email+password).getBytes()));
		return sqlSession.update("cn.itui.webdevelop.dao.UserDao.updatePassword", parameter);
	}
}
