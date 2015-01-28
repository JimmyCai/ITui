package cn.itui.webdevelop.dao.impl;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.UserInfoDao;

public class UserInfoDaoImpl implements UserInfoDao {
	private SqlSession sqlSession;

	public String getUserLogo(int id) {		
		return sqlSession.selectOne("cn.itui.webdevelop.dao.UserInfoDao.getUserLogo",id);
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}
