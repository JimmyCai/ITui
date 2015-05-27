package cn.itui.webdevelop.model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 每访问一次页面，增加一次页面访问量
 * */
public class Stats implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -672144559396137460L;
	/**
	 * 获取以yyyyMMdd格式的日期
	 */
	public static String getDate() {
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMdd"); 
	    String date = format.format(new Date()); 
		return date;
	}
	/**
	 * 获取以HHmm24小时制格式的时间
	 */
	public static String getTime(){
		java.text.DateFormat format = new java.text.SimpleDateFormat("HHmm"); 
	    String time = format.format(new Date()); 
		return time;
	}
	/**
	 * 将Unix时间戳转化为指定格式
	 */
	public static String unixTimeStamp2Date(String unixTimeStamp,String formats){
		Long timestamp = Long.parseLong(unixTimeStamp)*1000;
		String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));
    	return date;
		}
	/**
	 * 通过正则表达式过滤字符串
	 */
	public static String stringFilter(String input,String regex){
		// Pattern pattern =
		// Pattern.compile(".attach.*./attach.|正则表达式|好的|.size.*./size.");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(sb, "");
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
