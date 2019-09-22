package cn.zhjb.service.impl;

import cn.zhjb.dao.UserDao;
import cn.zhjb.dao.impl.UserDaoImpl;
import cn.zhjb.pojo.User;
import cn.zhjb.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();
	@Override
	public User getUserInfoService(String username, String userpwd) {
		User user = userDao.getUserInfoDao(username,userpwd);
		return user;
	}
	
}
