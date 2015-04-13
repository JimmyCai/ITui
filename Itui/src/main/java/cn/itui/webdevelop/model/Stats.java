package cn.itui.webdevelop.model;

import java.util.Date;

/**
 * 每访问一次页面，增加一次页面访问量
 * */
public class Stats {
	/*
	 * 获取以yyyyMMdd格式的日期
	 * */
	public static String getDate() {
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMdd"); 
	    String date = format.format(new Date()); 
		return date;
	}
	/*
	 * 获取以HHmm24小时制格式的时间
	 * */
	public static String getTime(){
		java.text.DateFormat format = new java.text.SimpleDateFormat("HHmm"); 
	    String time = format.format(new Date()); 
		return time;
	}

}
