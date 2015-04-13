package cn.itui.webdevelop.model;

/**
 * 获取课程开始时间，并分类
 * */

public class Course {
	
	public static int getDateType(Object object,Object object2){
		//今天 返回0
		if((Integer.parseInt((String) object)<=Integer.parseInt(Stats.getDate()))&&(Integer.parseInt((String) object2)>=Integer.parseInt(Stats.getDate())))
			return 0;
		//明天 返回1
		else if((Integer.parseInt(Stats.getDate())+1)==Integer.parseInt((String) object))
			return 1;
		//明天以后 返回2
		else 
		return 2;
	}

}
