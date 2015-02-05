package cn.itui.webdevelop.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.itui.webdevelop.service.UserService;
import cn.itui.webdevelop.utils.RequestUtil;
import cn.itui.webdevelop.utils.ResponseUtil;
import cn.itui.webdevelop.utils.exception.ParameterErrorException;


@Controller
public class UserController {
	private static Log rRLogger = LogFactory.getLog("requestResponse");
	private static final String PASSWORD = "password";
	private UserService userService;
	private static final String EMAIL="email";
	private static final String CODE = "code";
	private static final String CHECK_ADDRESS="check_address";
	private static final String CHECK_KEY="check_key";

	@RequestMapping(value=URLConstants.LOGIN, method=RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter(EMAIL);
		String password = request.getParameter(PASSWORD);
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		String requestStr = RequestUtil.getUserBaseInfo(request) + EMAIL + ":" + email + "\t" + PASSWORD + ":" + password;
		rRLogger.info(requestStr);
		return userService.doLogin(email, password);
	}
	
	@RequestMapping(value=URLConstants.REGISTER, method=RequestMethod.POST)
	public String register(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String email = request.getParameter(EMAIL);
		String password = request.getParameter(PASSWORD);
		String requestStr = RequestUtil.getUserBaseInfo(request) + EMAIL + ":" + email + "\t" + PASSWORD + ":" + password;
		rRLogger.info(requestStr);
		String code = DigestUtils.md5DigestAsHex((email+password).getBytes());
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		int type = 1;
		return userService.doRegister(email, password,code, type);
	}
	
	@RequestMapping(value=URLConstants.ACTIVATE)
	public String acitvate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String code = request.getParameter(CODE);
		return userService.activate(code);
	}
	
	@RequestMapping(value=URLConstants.RESET_PASSWORD)
	public String resetPassword(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String email = request.getParameter(EMAIL);
		if (email == null){
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		}
		String password = request.getParameter(PASSWORD);
		if (password == null){
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		}
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		String check_address = request.getParameter(CHECK_ADDRESS);
		if (check_address == null){
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		}
		
		String check_key = request.getParameter(CHECK_KEY);
		if (check_key == null){
			throw ParameterErrorException.getInstance(ParameterErrorException.ABSENCE_MESSAGE);
		}

		if (Touclick.check(check_key,check_address, null, null,null)){
		
			return userService.resetPassword(email, password);
		}else {
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("msg", "验证码错误");
			return ResponseUtil.wrapNormalReturn(result);
		}
	}
	
	@RequestMapping(value=URLConstants.RESEND_EMAIL)
	public String resendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter(EMAIL);
		return userService.resendEmail(email);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
