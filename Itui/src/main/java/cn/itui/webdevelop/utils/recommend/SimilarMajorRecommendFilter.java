package cn.itui.webdevelop.utils.recommend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.itui.webdevelop.model.MajorInfo;

public class SimilarMajorRecommendFilter implements MajorRecommendFilter{
	private static final Logger logger = Logger.getLogger(SimilarMajorRecommendFilter.class);
	public static final int MAJORCOUNT = 8;

	public MajorRecommendResult recommendMajorFilter(List<HashMap<String, Object>> candidates, String code, int majorId) {
		int simiCount = 0;
		int nearCount = 0;
		int corrCount = 0;
		int tranCount = 0;
		HashMap<String, ArrayList<HashMap<String, Object>>> codeDataMaps = new HashMap<String, ArrayList<HashMap<String,Object>>>();
		for(HashMap<String, Object> curData : candidates) {
			if((Integer)curData.get("id") != majorId){
				String curCode = (String)curData.get("code");
				if(codeDataMaps.containsKey(curCode)) {
					codeDataMaps.get(curCode).add(curData);
				}
				else {
					ArrayList<HashMap<String, Object>> tmpDatas = new ArrayList<HashMap<String,Object>>();
					tmpDatas.add(curData);
					codeDataMaps.put(curCode, tmpDatas);
				}
			}
		}
		ArrayList<HashMap<String, Object>> retMajors = new ArrayList<HashMap<String,Object>>();
		if(codeDataMaps.containsKey(code)) {
			addArrays(retMajors, codeDataMaps.get(code),0);
			simiCount = retMajors.size();
			codeDataMaps.remove(code);
		}
		if(retMajors.size() < MAJORCOUNT) {
			HashMap<String, ArrayList<HashMap<String, Object>>> tmpCodeDataMaps = (HashMap<String, ArrayList<HashMap<String, Object>>>) codeDataMaps.clone();
			Set<Entry<String, ArrayList<HashMap<String, Object>>>> codeDataSets = tmpCodeDataMaps.entrySet();
			for(Entry<String, ArrayList<HashMap<String, Object>>> curEntry : codeDataSets) {
				if(curEntry.getKey().substring(0, 4).equals(code.substring(0, 4))) {
					addArrays(retMajors, curEntry.getValue(),1);
					codeDataMaps.remove(curEntry.getKey());
				}
				if(retMajors.size() >= MAJORCOUNT) {
					nearCount = MAJORCOUNT - simiCount;
					return new MajorRecommendResult(retMajors, simiCount, nearCount, corrCount, tranCount);
				}
			}
			if(retMajors.size() < MAJORCOUNT) {
				nearCount = retMajors.size() - simiCount;
				tmpCodeDataMaps = (HashMap<String, ArrayList<HashMap<String, Object>>>) codeDataMaps.clone();
				codeDataSets = tmpCodeDataMaps.entrySet();
				for(Entry<String, ArrayList<HashMap<String, Object>>> curEntry : codeDataSets) {
					if(curEntry.getKey().substring(0, 2).equals(code.substring(0, 2))) {
						addArrays(retMajors, curEntry.getValue(),2);
						codeDataMaps.remove(curEntry.getKey());
					}
					if(retMajors.size() >= MAJORCOUNT) {
						corrCount = MAJORCOUNT - simiCount - nearCount;
						return new MajorRecommendResult(retMajors, simiCount, nearCount, corrCount, tranCount);
					}
				}
			}
		}
		return new MajorRecommendResult(retMajors, simiCount, nearCount, corrCount, tranCount);
	}
	
	private void addArrays(ArrayList<HashMap<String, Object>> retMajors, ArrayList<HashMap<String, Object>> toAdd, int color) {
		for(HashMap<String, Object> curMap : toAdd) {
			if(retMajors.size() >= MAJORCOUNT)
				return;
			addOneElement(retMajors, curMap, color);
		}
	}
	
	private void addOneElement(List<HashMap<String, Object>> retMajors, HashMap<String, Object> toAdd, int color) {
		HashMap<String, Object> majorCurInfo = new HashMap<String, Object>();
		majorCurInfo.put("majorName", toAdd.get("name"));
		majorCurInfo.put("schoolName", toAdd.get("school"));
		majorCurInfo.put("color", color);
		double rate = (Double) toAdd.get("rate");
		majorCurInfo.put("value", MajorInfo.translateRate(rate));
		System.out.println("code:" + toAdd.get("code"));
		retMajors.add(majorCurInfo);
	}

	public MajorRecommendResult recommendMajorFilter(
			MajorRecommendResult recommendMajors,
			List<HashMap<String, Object>> candidateMajors, int collegeId, int majorId,
			String code) {
		try{
			int preCode = Integer.parseInt(code.substring(0, 2));
			int I = 2;
			while(recommendMajors.getMajors().size() <= MAJORCOUNT) {
				for(HashMap<String, Object> curMap : candidateMajors) {
					if((Integer)curMap.get("id") != majorId) {
						try{
							String curCode = (String)curMap.get("code");
							int curPreCode = Integer.parseInt(curCode.substring(0, 2));
							if(Math.abs(curPreCode - preCode) < I) {
								addOneElement(recommendMajors.getMajors(), curMap, 3);
							}
							if(recommendMajors.getMajors().size() >= MAJORCOUNT)
								return recommendMajors;
						}catch(Exception e) {
							continue;
						}
					}
				}
			I++;
			}
		}catch(Exception e) {
			logger.error("pre-code not interger.");
		}
		return recommendMajors;
	}

}
