package cn.itui.webdevelop.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itui.webdevelop.service.StatsService;
import cn.itui.webdevelop.utils.exception.DatabaseException;

@Controller
public class StatsController {
	private StatsService statsService;
	public static final String COOKIE_HASH_KEY = "oknrmwxigbrjvqn";

	public StatsService getStatsService() {
		return statsService;
	}

	public void setStatsService(StatsService statsService) {
		this.statsService = statsService;
	}

	@RequestMapping(URLConstants.API_INDEX)
	 public String getPVStats(HttpServletRequest request, HttpServletResponse
	 response) throws Exception{
	 return statsService.getPVStats();
	 }
	
	@RequestMapping(URLConstants.API_NEWINDEX)
	public String getIndexInfo(HttpServletRequest request,
			HttpServletResponse response) throws DatabaseException {
		return statsService.getIndexInfo();
	}

	@RequestMapping(URLConstants.API_GET_USER_INFO)
	public String getUserInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String httpUserAgent = request.getHeader("User-Agent");
		String httpAcceptLanguage = request.getHeader("Accept-Language");
		String authHashKey = DigestUtils.md5DigestAsHex((COOKIE_HASH_KEY
				+ httpUserAgent + httpAcceptLanguage).getBytes());
		System.out.println(authHashKey);
		Cookie[] cookies = request.getCookies();
		return statsService.getUserInfo(cookies, authHashKey);
	}
}
