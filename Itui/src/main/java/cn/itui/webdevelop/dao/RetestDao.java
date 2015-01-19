package cn.itui.webdevelop.dao;

import cn.itui.webdevelop.model.Retest;

public interface RetestDao {
	public Retest findRetestById(int majorId) throws Exception;
}
