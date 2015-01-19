package cn.itui.webdevelop.utils;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/**
 * 返回json给前端
 * @author jimmycai
 *
 */
public class ResponseUtil {
	
	/**
	 * 正常的返回给前端
	 * @param response
	 * @param res
	 */
	public static void httpResponse(HttpServletResponse response, Object res) {
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(res.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发生异常时返回给前端
	 * @param response
	 * @param exception
	 */
	public static void httpResponseException(HttpServletResponse response, Exception exception) {
		try{
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			HashMap<String, Object>	responseJsonMap = new HashMap<String, Object>();
			responseJsonMap.put("status", -1);
			responseJsonMap.put("errMessage", exception.getMessage());
			Gson gson = new Gson();
			String jsonString = gson.toJson(responseJsonMap);
			response.getWriter().write(jsonString);
			response.getWriter().flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
