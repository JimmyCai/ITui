package cn.itui.webdevelop.dao;

import java.util.HashMap;

import cn.itui.webdevelop.model.MajorInfo;

public interface MajorInfoDao {
	public MajorInfo getMajorInfoById(int id);
	public HashMap<String, Object> findMajorAllInfoByMajorId(int majorId);
}
