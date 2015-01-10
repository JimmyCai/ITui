package cn.itui.webdevelop.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	
	public static void httpResponse(HttpServletResponse response, Object res) {
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(res.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
