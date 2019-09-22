package cn.zhjb.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBUtil {
	//声明全局变量记录jdbc参数
	private static String driver;
	private static String jdbcUrl;
	private static String uname;
	private static String password;
	//使用静态代码块，在类加载时即完成对属性文件的读取
	static{
		//读取properties文件
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		driver = bundle.getString("driver");
		jdbcUrl = bundle.getString("jdbcUrl");
		uname = bundle.getString("uname");
		password = bundle.getString("password");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//创建连接对象并返回
	public static Connection getConnection(){
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(jdbcUrl, uname, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	//关闭资源
	public static void closeAll(ResultSet rs,Statement stmt,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//封装增加删除修改的通用工具方法
	/**
	 * @param sql SQL语句
	 * @param objs	SQL语句占位符实参，如果没有参数则传入null
	 * @return 返回增删改的结果，类型为int
	 */
	public static int executeDML(String sql,Object...objs){
		// 声明jdbc变量
				Connection conn = null;
				PreparedStatement ps = null;
				int i = -1;
				try {
					// 获取连接对象
					conn = DBUtil.getConnection();
					// 开启事务管理
					conn.setAutoCommit(false);
					// 创建SQL命令对象
					ps = conn.prepareStatement(sql);
					// 给占位符赋值
					if(objs!=null){
						for(int j=0;j<objs.length;j++){
							ps.setObject(j+1,objs[j]);
						}
					}
					// 执行SQL
					i = ps.executeUpdate();
					conn.commit();
				} catch (Exception e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				} finally {
					DBUtil.closeAll(null, ps, conn);
				}
				return i;
	}
}
