package cn.itui.webdevelop.controller;

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

@Controller
public class UserController {
	private static Log rRLogger = LogFactory.getLog("requestResponse");
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private UserService userService;

	@RequestMapping(value=URLConstants.LOGIN, method=RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter(USERNAME);
		String password = request.getParameter(PASSWORD);
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		String requestStr = RequestUtil.getUserBaseInfo(request) + USERNAME + ":" + username + "\t" + PASSWORD + ":" + password;
		rRLogger.info(requestStr);
		return userService.doLogin(username, password);
	}
	
	@RequestMapping(value=URLConstants.REGESTER, method=RequestMethod.POST)
	public String register(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String username = request.getParameter(USERNAME);
		String password = request.getParameter(PASSWORD);
		String requestStr = RequestUtil.getUserBaseInfo(request) + USERNAME + ":" + username + "\t" + PASSWORD + ":" + password;
		rRLogger.info(requestStr);
		String code = DigestUtils.md5DigestAsHex((username+password).getBytes());
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		int type = 1;
		return userService.doRegister(username, password,code, type);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
