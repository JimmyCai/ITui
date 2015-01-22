package cn.itui.webdevelop.utils;

/**
 * 分词
 * @author warrior
 *
 */
public class WordParticiple {

	/**
	 * 将word的每个字符都插入%
	 * @param word
	 * @return
	 */
	public static String participle(String word) {
		String result = "%";
//		System.out.println(condition.length());
		for (int i = 0; i < word.length(); i++) {
			result += word.toCharArray()[i] + "%";
		}
		return result;
	}
	/**
	 * 如果string为""（即查询条件为全部）,则变成"%"
	 * @param string
	 * @return
	 */
	public static String filterAll(String string) {
		if (string.equalsIgnoreCase("")) {
			return participle(string);
		}
		return string;
	}
}
