package cn.zhjb.dao;

import cn.zhjb.pojo.User;

public interface UserDao {

	User getUserInfoDao(String username, String userpwd);

}
