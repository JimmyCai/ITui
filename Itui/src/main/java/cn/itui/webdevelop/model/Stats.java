package cn.itui.webdevelop.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
	
	public static String bbcode2Text(String inputString){
		Map<String,String> bbMap = new HashMap<String , String>();

        bbMap.put("(\r\n|\r|\n|\n\r)", "");
        bbMap.put("\\[b\\](.+?)\\[/b\\]", "$1");
        bbMap.put("\\[i\\](.+?)\\[/i\\]", "$1");
        bbMap.put("\\[\\*\\](.+?)\\[/\\*\\]", "$1");
        bbMap.put("\\[code\\](.+?)\\[/code\\]", "$1");
        bbMap.put("\\[attach\\]", "");
        bbMap.put("\\[/attach\\]","");
        bbMap.put("\\[list\\]", "");
        bbMap.put("\\[/list\\]", "");
        bbMap.put("\\[quote\\]", "");
        bbMap.put("\\[/quote\\]", "");
        bbMap.put("\\[u\\](.+?)\\[/u\\]", "$1");
        bbMap.put("\\[h1\\](.+?)\\[/h1\\]", "$1");
        bbMap.put("\\[h2\\](.+?)\\[/h2\\]", "$1");
        bbMap.put("\\[h3\\](.+?)\\[/h3\\]", "$1");
        bbMap.put("\\[h4\\](.+?)\\[/h4\\]", "$1");
        bbMap.put("\\[h5\\](.+?)\\[/h5\\]", "$1");
        bbMap.put("\\[h6\\](.+?)\\[/h6\\]", "$1");
        bbMap.put("\\[p\\](.+?)\\[/p\\]", "$1");
        bbMap.put("\\[p=(.+?),(.+?)\\](.+?)\\[/p\\]", "$3");
        bbMap.put("\\[center\\](.+?)\\[/center\\]", "$1");
        bbMap.put("\\[align=(.+?)\\](.+?)\\[/align\\]", "$2");
        bbMap.put("\\[color=(.+?)\\](.+?)\\[/color\\]", "$2");
        bbMap.put("\\[size\\](.+?)\\[/size\\]", "");
        bbMap.put("\\[img\\](.+?)\\[/img\\]", "$1");
        bbMap.put("\\[img=(.+?),(.+?)\\](.+?)\\[/img\\]", "<img width='$1' height='$2' src='$3' />");
        bbMap.put("\\[email\\](.+?)\\[/email\\]", "$1");
        bbMap.put("\\[email=(.+?)\\](.+?)\\[/email\\]", "$2");
        bbMap.put("\\[url\\](.+?)\\[/url\\]", "$1");
        bbMap.put("\\[url=(.+?)\\](.+?)\\[/url\\]", "$2");
        bbMap.put("\\[video\\](.+?)\\[/video\\]", "<video src='$1' />");

        for (Map.Entry entry: bbMap.entrySet()) {
            inputString = inputString.replaceAll(entry.getKey().toString(), entry.getValue().toString());
        }
        return inputString;
	}
}
