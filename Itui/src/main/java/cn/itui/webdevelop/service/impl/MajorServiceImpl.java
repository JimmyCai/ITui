package cn.itui.webdevelop.service.impl;

import cn.itui.webdevelop.dao.MajorDao;
import cn.itui.webdevelop.service.MajorService;
import cn.itui.webdevelop.utils.WordParticiple;

public class MajorServiceImpl implements MajorService {
	private MajorDao majorDao;
	
	public String searchMajorsList(String condition, String category,
			String subject, String major_type, String college_type, String area) {

		condition = WordParticiple.participle(condition);
		//全部
		category = WordParticiple.filterAll(category);
		subject = WordParticiple.filterAll(subject);
		major_type = WordParticiple.filterAll(major_type);
		college_type = WordParticiple.filterAll(college_type);
		area = WordParticiple.filterAll(area);
		
		int type = 1;
		if (major_type.startsWith("专")){
			type = 2;
		}
		
		int is985=0;
		int is211=0;
		int is34 = 0;
		if (college_type.startsWith("9")){
			//985
			is985=1;
		}else if (college_type.startsWith("3")){
			//34
			is34=1;
		}else if (college_type.startsWith("2")){
			//211
			is211=1;
		}
		return null;
	}

	public MajorDao getMajorDao() {
		return majorDao;
	}

	public void setMajorDao(MajorDao majorDao) {
		this.majorDao = majorDao;
	}

}
