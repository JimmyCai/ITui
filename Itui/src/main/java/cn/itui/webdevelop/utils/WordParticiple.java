package cn.itui.webdevelop.utils;

public class WordParticiple {

	public static String participle(String word) {
		String result = "%";
//		System.out.println(condition.length());
		for (int i = 0; i < word.length(); i++) {
			result += word.toCharArray()[i] + "%";
		}
		return result;
	}
	
	public static String filterAll(String string) {
		if (string.equalsIgnoreCase("")) {
			return participle(string);
		}
		return string;
	}
}
