package cn.itui.webdevelop.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itui.webdevelop.dao.StatsDao;

public class StatsDaoImpl implements StatsDao {
	private SqlSession sqlSession;

	public void refreshStats(String date, int randomNum) {
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("date", date);
		parameterMap.put("randomNum", randomNum);
	    sqlSession.update("cn.itui.webdevelop.dao.StatsDao.refreshStats", parameterMap);	
	}

	public List<HashMap<String, Object>> getTodayStatsInfo(String date) {
		return sqlSession.selectList("cn.itui.webdevelop.dao.StatsDao.getTodayStatsInfo",date);
	}

	public int getUserNum() {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.StatsDao.getUserNum");
	}

	public String getLastRecordDate() {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.StatsDao.getLastRecordDate");
	}

	public void creatNewRecord(String date) {
		sqlSession.insert("cn.itui.webdevelop.dao.StatsDao.creatNewRecord", date);
	}

	public int getTotalPV() {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.StatsDao.getTotalPV");
	}
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<HashMap<String, Object>> getPersonInfo() {
		return sqlSession.selectList("cn.itui.webdevelop.dao.StatsDao.getPersonInfo");
	}

	public List<HashMap<String, Object>> getNewsInfo() {
		return sqlSession.selectList("cn.itui.webdevelop.dao.StatsDao.getNewsInfo");
	}

	public List<HashMap<String, Object>> getTopicInfo() {
		return sqlSession.selectList("cn.itui.webdevelop.dao.StatsDao.getTopicInfo");
	}

	public HashMap<String, Object> getUserEduInfo(long userId) {
		return sqlSession.selectOne("cn.itui.webdevelop.dao.StatsDao.getUserSchool",userId);
	}

	public HashMap<String, Object> getUserInfo(String userName, int userId,
			String password) {
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("userName", userName);
		parameterMap.put("userId", userId);
		parameterMap.put("password", password);
	    return sqlSession.selectOne("cn.itui.webdevelop.dao.StatsDao.getUserInfo", parameterMap);
	}

}