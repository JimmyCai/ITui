package cn.itui.webdevelop.dao;

public interface UserInfoDao {
	public String getUserLogo(int id);

	public int insertUserInfo_logo(int id, String string, long regTime);
}
