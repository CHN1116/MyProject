package cn.zhjb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * jdbc������
 * @author zhang
 *
 */
public class JdbcUtil {
	private static String driver;
	private static String jdbcUrl;
	private static String uname;
	private static String password;
	static{
		//��ȡproperties�ļ�
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
	
	//��ȡconnection����
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn =  DriverManager.getConnection(jdbcUrl, uname, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//�ر�statement
	public static void closeStatement(Statement state){
		if(state != null){
			try {
				state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//�ر�connection
	public static void closeConnection(Connection conn){
		if (conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
