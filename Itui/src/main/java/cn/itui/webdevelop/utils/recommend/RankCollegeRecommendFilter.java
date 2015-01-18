package cn.itui.webdevelop.utils.recommend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.itui.webdevelop.model.College;

public class RankCollegeRecommendFilter implements CollegeRecommendFilter{
	private static final int RECOMMENDCOUNT = 4;

	public List<HashMap<String, Object>> recommendCollege(
			List<College> candidates, int rank) {
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
		HashMap<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("name", curCollege.getName());
		tmpMap.put("rank", curCollege.getRank());
		StringBuilder typeBuilder = new StringBuilder();
		if(curCollege.getIs211() == 1)
			typeBuilder.append("211/");
		if(curCollege.getIs985() == 1)
			typeBuilder.append("985/");
		if(curCollege.getIs34() == 1)
			typeBuilder.append("34");
		String type = null;
		if(typeBuilder.length() > 0) {
			if(typeBuilder.charAt(typeBuilder.length() - 1) == '/')
				type = typeBuilder.substring(0, typeBuilder.length() - 1);
			else
				type = typeBuilder.toString();
		}
		else
			type="null";
		tmpMap.put("level", type);
		resultMaps.add(tmpMap);
	}

}
