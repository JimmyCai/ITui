package cn.itui.webdevelop.utils.recommend;

import java.util.HashMap;
import java.util.List;

public class MajorRecommendResult {
	private List<HashMap<String, Object>> majors;
	private int similiarCount;
	private int nearCount;
	private int correlateCount;
	private int transdisciplinaryCount;
	
	public MajorRecommendResult(List<HashMap<String, Object>> majors, int similiarCount, int nearCount, int correlateCount, int transdisciplinaryCount) {
		this.majors = majors;
		this.similiarCount = similiarCount;
		this.nearCount = nearCount;
		this.correlateCount = correlateCount;
		this.transdisciplinaryCount = transdisciplinaryCount;
	}

	public List<HashMap<String, Object>> getMajors() {
		return majors;
	}

	public void setMajors(List<HashMap<String, Object>> majors) {
		this.majors = majors;
	}

	public int getSimiliarCount() {
		return similiarCount;
	}

	public void setSimiliarCount(int similiarCount) {
		this.similiarCount = similiarCount;
	}

	public int getNearCount() {
		return nearCount;
	}

	public void setNearCount(int nearCount) {
		this.nearCount = nearCount;
	}

	public int getCorrelateCount() {
		return correlateCount;
	}

	public void setCorrelateCount(int correlateCount) {
		this.correlateCount = correlateCount;
	}

	public int getTransdisciplinaryCount() {
		return transdisciplinaryCount;
	}

	public void setTransdisciplinaryCount(int transdisciplinaryCount) {
		this.transdisciplinaryCount = transdisciplinaryCount;
	}
	
	
}
