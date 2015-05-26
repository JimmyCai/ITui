package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itui.webdevelop.service.StatsService;
import cn.itui.webdevelop.utils.exception.DatabaseException;

@Controller
public class StatsController {
	private StatsService statsService;
	
	public StatsService getStatsService() {
		return statsService;
	}

	public void setStatsService(StatsService statsService) {
		this.statsService = statsService;
	}
	
	@RequestMapping(URLConstants.API_INDEX)
//	public String getPVStats(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		return statsService.getPVStats();
//	}
	public String getIndexInfo(HttpServletRequest request,HttpServletResponse response) throws DatabaseException{
		return statsService.getIndexInfo();
	}

}
