package com.kuaishoudan.financer.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kuaishoudan.financer.flow.TestA;
import com.kuaishoudan.financer.flow.TestC;
import com.kuaishoudan.financer.flow.TestD;
import com.kuaishoudan.financer.flow.TestE;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.WebUtil;

public class DBUtil {

	public static final String url = "jdbc:mysql://rm-2ze7vfrx833b858d1do.mysql.rds.aliyuncs.com:3306/zhihjf_pre?characterEncoding=utf8";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "work";// read_only
	public static final String password = "Jizhicar2014";
	public static Connection conn1 = null;
	public PreparedStatement pst = null;
	static MyDataSource ds = new MyDataSource();
	static {

	}

	public void closeConn(Connection conn, ResultSet rs, PreparedStatement stmt) {

		conn1 = conn;
		ds.addBackToPool(conn);
		// System.out.println("使用完 后将连接 放回 连接池中");
		// 释放 资源 并 不将连接 释放
		// JdbcUtils.release(rs, stmt, conn);

	}
	public void closeConn(Connection conn ) {

		conn1 = conn;
		ds.addBackToPool(conn);
		// System.out.println("使用完 后将连接 放回 连接池中");
		// 释放 资源 并 不将连接 释放
		// JdbcUtils.release(rs, stmt, conn);

	}

	public void closeConn1() {

		ds.closeCon();
	}

	public Connection openConnection1() {
		Properties prop = new Properties();
		String driver = null;
		String url = null;
		String username = null;
		String password = null;

		try {

			// System.out.println(url);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * try { Class.forName(name); String url1=getUrl(); conn =
		 * DriverManager.getConnection(url1, user, password); //
		 * System.out.println(conn); return conn; } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		return null;
	}

	public static void main(String[] args) {

		// new DBUtil().openConnection();
		getUrl();
	}

	public static Connection openConnection() {

		Connection connection = null;
		PreparedStatement stmt = null;

		ResultSet rs = null;
		try {

			connection = (Connection) ds.getConnection();

			return connection;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public static String getUrl() {
		Properties properties = new Properties();
		String baseUrl = "";
		String url = "";
		try {
			InputStreamReader in = new InputStreamReader(
					WebUtil.class.getResourceAsStream("ksd.properties"),
					"UTF-8");
			properties.load(in);

			baseUrl = properties.getProperty("webUrl");
			baseUrl = baseUrl.substring(7).split("\\.")[0];
			// System.out.println("+++++++++++"+baseUrl);
			switch (baseUrl) {
			case "test":
				url = "jdbc:mysql://rm-2ze7vfrx833b858d1do.mysql.rds.aliyuncs.com:3306/zhihjf_test?characterEncoding=utf8";
				break;
			case "test2":
				// System.out.println("B+!!!!!!!!");
				url = "jdbc:mysql://rm-2ze7vfrx833b858d1do.mysql.rds.aliyuncs.com:3306/zhihjf_test2?characterEncoding=utf8";
				break;

			default:
				System.out.println("default");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;

	}
}
