package cn.itui.webdevelop.utils;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import cn.itui.webdevelop.utils.exception.ParameterErrorException;
import cn.itui.webdevelop.utils.exception.SessionExceedException;

/**
 * 加密解密类
 * @author jimmycai
 *
 */
public class EnDeCode {
	private static final int RANDOM_RANGE = 30;
	public static final String SESSION_STRING = "AHAHA";//放在session里的一个随机数
	
	
	/**
	 * 对id进行解密, paraName为要解密的参数名
	 * @param request
	 * @param random
	 * @return
	 * @throws ParameterErrorException 
	 * @throws SessionExceedException 
	 */
	public static int decodePara(HttpServletRequest request, String paraName) throws ParameterErrorException, SessionExceedException {
		String code = request.getParameter(paraName);
		if(code == null) 
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		int decodeId = 0;
		try {
			int random = getDecodeRandomNumber(request, false);
//			int random = 0;
			decodeId = decodeId(code, random);
		} catch (NumberFormatException e) {
			throw ParameterErrorException.getInstance(ParameterErrorException.ERROR_MESSAGE);
		}
		return decodeId;
	}
	
	/**
	 * 对id进行加密
	 * @param request
	 * @param id
	 * @param setRandomNum:在session中没有随机数时是否要在session中注入随机数，在搜索时为true
	 * @return
	 * @throws SessionExceedException
	 */
	public static String encodePara(HttpServletRequest request, int id, boolean setRandomNum) throws SessionExceedException {
		int random = getDecodeRandomNumber(request, setRandomNum);
		return encodeId(id, random);
	}
	
	/**
	 * 从httprequest中获取当前session的random number
	 * @param request
	 * @return
	 * @throws SessionExceedException
	 */
	private static int getDecodeRandomNumber(HttpServletRequest request, boolean setRandomNum) throws SessionExceedException {
		if(setRandomNum) {
			if(request.getSession().getAttribute(EnDeCode.SESSION_STRING) == null) {
				request.getSession().setAttribute(EnDeCode.SESSION_STRING, randomNumber());
			}
		}
		else {
			if(request.getSession().getAttribute(EnDeCode.SESSION_STRING) == null) {
				throw SessionExceedException.getInstance();
			}
		}

		int random = (Integer) request.getSession().getAttribute(SESSION_STRING);
		return random;
	}	

	/**
	 * 对id进行简单的加密
	 * @param id
	 * @param randomInt
	 * @return
	 */
	private static String encodeId(int id, int randomInt) {		
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
	private static int decodeId(String code, int randomInt) throws NumberFormatException{
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
