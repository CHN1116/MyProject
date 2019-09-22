package cn.zhjb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import cn.zhjb.dao.UserDao;
import cn.zhjb.pojo.User;
import cn.zhjb.util.DBUtil;


public class UserDaoImpl implements UserDao {

	@Override
	public User getUserInfoDao(String username, String userpwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		System.out.println(username+" "+userpwd);
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from t_user where username=? and userpwd=?";
			//String sql = "select * from t_user";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, userpwd);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User();
				user.setUserName(rs.getString("username"));
				user.setUserpwd(rs.getString("userpwd"));
				user.setUserId(rs.getInt("userid"));
				user.setUserAge(rs.getInt("userage"));
				user.setUserSex(rs.getString("usersex"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, ps, conn);
			
		}
		
		System.out.println(user);		
		return user;
	}

}
