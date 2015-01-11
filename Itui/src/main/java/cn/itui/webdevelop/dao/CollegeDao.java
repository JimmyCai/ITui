package cn.itui.webdevelop.dao;

import java.util.HashMap;


public interface CollegeDao {
	public HashMap<String, Object> findLogoAndRankByMajorId(int id);
}
