package cn.itui.webdevelop.dao;

import cn.itui.webdevelop.model.Retest;

public interface RetestDao {
	public Retest findRetestByMajorId(int majorId) throws Exception;
}
