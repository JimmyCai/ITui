package cn.itui.webdevelop.service;

import cn.itui.webdevelop.utils.exception.DatabaseException;

public interface StatsService {
	public String getPVStats() throws Exception;

	public String getIndexInfo() throws DatabaseException;
}
