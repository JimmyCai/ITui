package cn.itui.webdevelop.utils.recommend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.model.College;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.exception.DatabaseException;

/**
 * 根据college的rank进行刷选
 * @author jimmycai
 *
 */
public class RankCollegeRecommendFilter implements CollegeRecommendFilter{
	private static final int RECOMMENDCOUNT = 4;

	public List<HashMap<String, Object>> recommendCollege(
			List<College> candidates, int rank) throws DatabaseException {
		if(candidates == null) {
			throw DatabaseException.getInstance();
		}
		boolean beginAdd = false;
		int beginLoc = 0;
		List<HashMap<String, Object>> resultMaps = new ArrayList<HashMap<String,Object>>();
		for(int i = 0; i < candidates.size(); i++) {
			int curRank = candidates.get(i).getRank();
			if(beginAdd)
				addOne(resultMaps, candidates.get(i));
			else if(curRank == rank || Math.abs(curRank - rank) <= 2) {
				beginAdd = true;
				beginLoc = i;
				addOne(resultMaps, candidates.get(i));
			}
			if(resultMaps.size() >= RECOMMENDCOUNT)
				return resultMaps;
		}
		if(resultMaps.size() < RECOMMENDCOUNT) {
			for(int i = beginLoc - 1; i >=0; i--) {
				addOne(resultMaps, candidates.get(i));
				if(resultMaps.size() >= RECOMMENDCOUNT)
					return resultMaps;
			}
		}
		return resultMaps;
	}
	
	private void addOne(List<HashMap<String, Object>> resultMaps, College curCollege) {
		if(resultMaps == null || curCollege == null)
			return;
		HashMap<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("collegeId", EnDeCode.encodePara(curCollege.getId()));
		tmpMap.put("name", curCollege.getName());
		tmpMap.put("rank", curCollege.getRank());
		String type = curCollege.getTypeString();
		tmpMap.put("level", type);
		resultMaps.add(tmpMap);
	}

}
