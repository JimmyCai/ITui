package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.UserDao;

public class UserDaoImpl implements UserDao {
	
	private SqlSession sqlSession;

	public HashMap<String, Object> match(String username, String password) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		return sqlSession.selectOne("cn.itui.webdevelop.dao.UserDao.match", map);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insertUser(String username, String password, String code, int type) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("id",10000000);
		parameter.put("email", username);
		parameter.put("password", password);
		parameter.put("code", code);
		parameter.put("type", type);		
		return sqlSession.insert("cn.itui.webdevelop.dao.UserDao.insertUser", parameter);
	}
}
