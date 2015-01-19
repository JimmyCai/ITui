package cn.itui.webdevelop.utils.recommend;

import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.model.College;
import cn.itui.webdevelop.utils.exception.DatabaseException;

public interface CollegeRecommendFilter {
	public List<HashMap<String, Object>> recommendCollege(List<College> candidates, int rank) throws DatabaseException;
}
