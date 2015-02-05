package cn.itui.webdevelop.service.impl;

import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import cn.itui.webdevelop.dao.UserDao;
import cn.itui.webdevelop.dao.UserInfoDao;
import cn.itui.webdevelop.service.UserService;
import cn.itui.webdevelop.utils.ResponseUtil;

public class UserServiceImpl implements UserService {

	public static final String SUBJECT="爱推帐号激活";
	private UserDao userDao;
	private UserInfoDao userInfoDao;
	public static String HTMLTEXT = "www.itui.cn/activate";
	private JavaMailSender javaMailSender;
	private String systemEmail;

	public String doLogin(String email, String password) throws Exception{
		HashMap<String, Object> usermap = userDao.match(email, password);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (usermap != null) {
			if ((Integer) usermap.get("active") != 0) {
				String logo = userInfoDao.getUserLogo((Integer) usermap
						.get("id"));
				map.put("login", "success");
				map.put("logo", logo);
				map.put("email", email);
			} else {
				map.put("login", "failure");
				map.put("msg", "请登录您的邮箱激活帐号");

			}
		} else {
			map.put("login", "failure");
			map.put("msg", "用户名或密码错误");
		}

		return ResponseUtil.wrapNormalReturn(map);
	}

	/**
	 * 该方法未完成，一个是插入用户默认头像未完成，即没有完成处理失败情况 二是没有处理发送邮件失败情况
	 * 
	 * @throws Exception
	 */
	public String doRegister(String email, String password, String code,
			int type) throws Exception {
		HashMap<String, Object> matchmap = userDao.match(email, null);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (matchmap == null) {
			int id = userDao.insertUser(email, password, code, type);
			System.out.println(id);
			if (id > 0) {
				// 应该异步调用，否则将出现事务处理的回滚
				id = userInfoDao.insertUserInfo_logo(id, "defaultuserlogo.png");
				if (id <= 0) {
					// 后台插入用户信息失败
					map.put("register", "false");
					// 回滚
				}
				HTMLTEXT += "?code=" + code;
				boolean bool = sendMail(email, SUBJECT, HTMLTEXT);
				if (!bool) {
					// 发送邮件失败
				}
				if (id > 0 && bool) {
					map.put("register", "true");
					map.put("msg", "注册成功！请验证邮箱");
				}
			}
		} else {
			map.put("register", "false");
			map.put("msg", "用户名已存在");
		}
		return ResponseUtil.wrapNormalReturn(map);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public String activate(String code) throws Exception{
		int id = userDao.activate(code);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id > 0) {
			map.put("msg", "成功激活帐号，可以登录啦");
		} else {
			map.put("msg", "帐号激活失败");
		}
		return ResponseUtil.wrapNormalReturn(map);
	}

	public boolean sendMail(String to, String subject, String htmlText)
			throws Exception {
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg);

			msgHelper.setFrom(systemEmail);
			msgHelper.setTo(to);
			msgHelper.setSubject(subject);
			msgHelper.setText(htmlText, true);

			javaMailSender.send(msg);
			return true;
		} catch (MessagingException e) {
			throw new Exception("Fail to send mail", e);
		}
	}


	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public String resetPassword(String email, String password) throws Exception{

		int id = userDao.updatePassword(email, password);
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (id > 0) {
			result.put("reset", "success");
			result.put("msg", "密码重置成功，请牢记哦");
		}else {
			//抛出异常
			result.put("reset", "failure");
			result.put("msg", "密码重置失败T^T");
		}
		return ResponseUtil.wrapNormalReturn(result);
	}

	public String resendEmail(String email) throws Exception {
		HashMap<String, Object> match = userDao.match(email, null);
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (match != null){
			if (sendMail(email, SUBJECT, HTMLTEXT+"?code="+match.get("code"))){
				result.put("resendEmail", "success");
				result.put("msg", "激活邮件已发送");
			}else {
				result.put("resendEmail", "failure");
				result.put("msg", "未能发送激活邮件");
			};
		}else {
			result.put("resendEmail", "failure");
			result.put("msg", "用户名不存在");
		}
		return ResponseUtil.wrapNormalReturn(result);
	}

}
