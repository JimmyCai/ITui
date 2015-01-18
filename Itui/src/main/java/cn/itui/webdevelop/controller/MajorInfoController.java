package cn.itui.webdevelop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itui.webdevelop.service.MajorInfoService;
import cn.itui.webdevelop.utils.EnDeCode;
import cn.itui.webdevelop.utils.exception.SessionExceedException;

/**
 * 管理major info的控制器
 * @author jimmycai
 *
 */
@Controller
public class MajorInfoController {
	private MajorInfoService majorInfoService;
	
	@RequestMapping(value=URLConstants.GETMAJORINFO)
	public String getMajorInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int random = getDecodeRandomNumber(request);
		int majorId = decodeId(request, random);
		String retJson = majorInfoService.getMajorInfo(majorId, random);
		return retJson;
	}
	
	/**
	 * 从httprequest中获取当前session的random number
	 * @param request
	 * @return
	 * @throws SessionExceedException
	 */
	private int getDecodeRandomNumber(HttpServletRequest request) throws SessionExceedException {
		if(request.getSession().getAttribute(EnDeCode.SESSION_STRING) == null) {
			throw new SessionExceedException();
		}
		int random = (Integer) request.getSession().getAttribute(EnDeCode.SESSION_STRING);
		return random;
	}
	
	/**
	 * 对id进行解密
	 * @param request
	 * @param random
	 * @return
	 */
	private int decodeId(HttpServletRequest request, int random) {
		String code = request.getParameter("majorId");
		return EnDeCode.decodeId(code, random);
	}

	public MajorInfoService getMajorInfoService() {
		return majorInfoService;
	}

	public void setMajorInfoService(MajorInfoService majorInfoService) {
		this.majorInfoService = majorInfoService;
	}

}
