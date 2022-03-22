package com.elon33.netdisc.model;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * 数据库访问模型
 * @author elon@elon33.com
 */
public class ConnDB {
	private Connection con = null;

	/**
	 * 返回数据库访问连接对象
	 */
	private final String hostname = "localhost";
	private final String username = System.getenv("MYSQL_USER");;
	private final String password = System.getenv("MYSQL_PASS");;
	public Connection getConn() {
		try {
			// 加载驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 得到连接
			con = DriverManager.getConnection("jdbc:mysql://"+hostname+":3306/hadoop?serverTimezone=UTC&user="+username+"&password="+password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (con != null)
			System.out.println("******数据库连接成功！*******");
		return con;
	}
}
