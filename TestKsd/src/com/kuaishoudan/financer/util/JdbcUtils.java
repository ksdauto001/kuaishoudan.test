package com.kuaishoudan.financer.util;

 

	import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import com.kuaishoudan.financer.selenium.WebUtil;

	/**
	 * 提供数据库连接池 和数据库连接
	 * 方法为静态的 通过类型访问
	 * @author huhongda
	 * 
	 */
public class JdbcUtils {

	/*
	 * private static String driverName ="com.mysql.jdbc.Driver"; private static
	 * String url=
	 * "jdbc:mysql://rm-2ze7vfrx833b858d1do.mysql.rds.aliyuncs.com:3306/zhihjf_test2?characterEncoding=utf8"
	 * ; private static String userName = "work"; private static String userPwd
	 * ="Jizhicar2014";
	 */
	public static String url2 = null;
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "work";
	public static final String password = "Jizhicar2014";
	// 读取配置文件中的内容
	// 静态方法块
	static {
		Properties properties = new Properties();
		String baseUrl = "";

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
				url2 = "jdbc:mysql://rm-2ze7vfrx833b858d1do.mysql.rds.aliyuncs.com:3306/zhihjf_test?characterEncoding=utf8";
				break;
			case "test2":
				// System.out.println("B+!!!!!!!!");
				url2 = "jdbc:mysql://rm-2ze7vfrx833b858d1do.mysql.rds.aliyuncs.com:3306/zhihjf_test2?characterEncoding=utf8";
				break;
			case "pre":
				// System.out.println("B+!!!!!!!!");
				url2 = "jdbc:mysql://rm-2ze7vfrx833b858d1do.mysql.rds.aliyuncs.com:3306/zhihjf_pre?characterEncoding=utf8";
				break;

			default:
				System.out.println("default");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 建立连接
	public static Connection getConnection() {
		try {
			// 注册驱动
			loadDriver();
			// System.out.println("创建连接成功");
			// 建立连接 并返回
			// Connection conn = DriverManager.getConnection(url, userName,
			// userPwd);
			Connection conn = DriverManager.getConnection(url2, user, password);
			// System.out.println(conn);
			return conn;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	// 注册驱动
	public static void loadDriver() {
		try {
			// Class.forName(driverName);
			Class.forName(name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 释放资源
	public static void release(ResultSet rs, Statement stat, Connection con) {
		// 存在连接或结果集的时候 释放
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			rs = null;
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			stat = null;
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			con = null;
		}
	}
}

