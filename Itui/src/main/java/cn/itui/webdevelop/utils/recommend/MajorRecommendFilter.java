package cn.itui.webdevelop.utils.recommend;

import java.util.HashMap;
import java.util.List;

public interface MajorRecommendFilter {
	public MajorRecommendResult recommendMajorFilter(List<HashMap<String, Object>> candidates, String code, int majorId) throws Exception;
	public MajorRecommendResult recommendMajorFilter(MajorRecommendResult recommendMajors, List<HashMap<String, Object>> candidateMajors, int collegeId, int majorId, String code) throws Exception;
	public List<HashMap<String, Object>> recommendMajorFilter(List<HashMap<String, Object>> candidates, double rate)  throws Exception;
}
