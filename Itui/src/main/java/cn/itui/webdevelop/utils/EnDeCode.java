package cn.itui.webdevelop.utils;

import java.util.Random;

import cn.itui.webdevelop.utils.exception.ParameterErrorException;

/**
 * 加密解密类
 * @author jimmycai
 *
 */
public class EnDeCode {
	private static final int RANDOM_RANGE = 8000;	
	private static Random random = new Random();
	
	/**
	 * 对id进行解密, paraCode为要解密的参数名
	 * @param paraCode为要解码的code
	 * @return
	 * @throws NumberFormatException 
	 * @throws ParameterErrorException 
	 */
	public static int decodePara(String paraCode) throws NumberFormatException, ParameterErrorException{
		if(paraCode.length() < 4)
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		int decodeId = 0;
		String randomStr = paraCode.substring(0, 4);
		String code = paraCode.substring(4);
		decodeId = decodeId(code, randomStr);
		return decodeId;
	}
	
	/**
	 * 对id进行加密
	 * @param id
	 * @return
	 */
	public static String encodePara(int id) {
		int randomInt = randomNumber() + 1000;
		String codeStr = encodeId(id, randomInt);
		return randomInt + codeStr;
	}

	/**
	 * 对id进行简单的加密
	 * @param id
	 * @param randomInt
	 * @return
	 */
	private static String encodeId(int id, int randomInt) {		
		return id+randomInt+"";
	}
	
	/**
	 * 对id进行对应的解密
	 * @param code
	 * @param randomStr
	 * @return
	 * @throws NumberFormatException
	 */
	private static int decodeId(String code, String randomStr) throws NumberFormatException{
		int randomInt = Integer.parseInt(randomStr);
		int codeInt = Integer.parseInt(code);
		return codeInt - randomInt;
	}
	
	public static int randomNumber() {
		return random.nextInt(RANDOM_RANGE);
	}

}
