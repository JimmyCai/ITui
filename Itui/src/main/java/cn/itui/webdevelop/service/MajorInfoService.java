package cn.itui.webdevelop.service;

public interface MajorInfoService {
	public String getMajorInfo(String userCode, String majorIdStr) throws Exception;
	public String getRetestInfo(int majorId) throws Exception;
	
	/*
	 * add
	 * */
	public String getMajorRank(String majorIdStr) throws Exception;

}
