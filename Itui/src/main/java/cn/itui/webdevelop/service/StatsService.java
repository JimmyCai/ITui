package cn.itui.webdevelop.service;

import javax.servlet.http.Cookie;

import cn.itui.webdevelop.utils.exception.DatabaseException;

public interface StatsService {
	public String getPVStats() throws Exception;

	public String getIndexInfo() throws DatabaseException;

	public String getUserInfo(Cookie[] cookies, String authHashKey) throws Exception;
}
