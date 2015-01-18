package cn.itui.webdevelop.utils;

import java.util.Random;

/**
 * 加密解密类
 * @author jimmycai
 *
 */
public class EnDeCode {
	private static final int RANDOM_RANGE = 30;
	public static final String SESSION_STRING = "AHAHA";//放在session里的一个随机数
	/**
	 * 对id进行简单的加密
	 * @param id
	 * @param randomInt
	 * @return
	 */
	public static String encodeId(int id, int randomInt) {		
		System.out.println(randomInt);
		char[] idChars = ("" + id).toCharArray();
		char[] targetChars = new char[idChars.length];
		for(int i = 0; i < idChars.length; i++) {
			targetChars[i] = (char) (idChars[i] + randomInt);
		}
		return new String(targetChars);
	}
	
	/**
	 * 对id进行对应的解密
	 * @param code
	 * @param randomInt
	 * @return
	 * @throws NumberFormatException
	 */
	public static int decodeId(String code, int randomInt) throws NumberFormatException{
		char[] targetChars = new char[code.length()];
		for(int i = 0; i < targetChars.length; i++) {
			targetChars[i] = (char) (code.charAt(i) - randomInt);
		}
		String tmpStr = new String(targetChars);
		return Integer.parseInt(tmpStr);
	}
	
	public static int randomNumber() {
		Random random = new Random();
		return random.nextInt(RANDOM_RANGE);
	}

}
