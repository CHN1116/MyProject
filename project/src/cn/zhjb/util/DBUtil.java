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
	//����ȫ�ֱ�����¼jdbc����
	private static String driver;
	private static String jdbcUrl;
	private static String uname;
	private static String password;
	//ʹ�þ�̬����飬�������ʱ����ɶ������ļ��Ķ�ȡ
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
	//�������Ӷ��󲢷���
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
	//�ر���Դ
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
	//��װ����ɾ���޸ĵ�ͨ�ù��߷���
	/**
	 * @param sql SQL���
	 * @param objs	SQL���ռλ��ʵ�Σ����û�в�������null
	 * @return ������ɾ�ĵĽ��������Ϊint
	 */
	public static int executeDML(String sql,Object...objs){
		// ����jdbc����
				Connection conn = null;
				PreparedStatement ps = null;
				int i = -1;
				try {
					// ��ȡ���Ӷ���
					conn = DBUtil.getConnection();
					// �����������
					conn.setAutoCommit(false);
					// ����SQL�������
					ps = conn.prepareStatement(sql);
					// ��ռλ����ֵ
					if(objs!=null){
						for(int j=0;j<objs.length;j++){
							ps.setObject(j+1,objs[j]);
						}
					}
					// ִ��SQL
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
