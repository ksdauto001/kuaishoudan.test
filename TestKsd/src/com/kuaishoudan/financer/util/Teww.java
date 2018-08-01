package com.kuaishoudan.financer.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.util.CaseUtil;
import com.kuaishoudan.financer.util.JdbcUtils;
import com.kuaishoudan.financer.util.MyDataSource;
import com.kuaishoudan.financer.util.RandomValue;
import com.mysql.jdbc.Connection;

public class Teww {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	String a="asd";
		String b="weee";

	//	Assert.assertEquals(a, b);
		System.out.println("ffffff");
		List<Integer> ll=new ArrayList<Integer>();
		ll.add(1);
		ll.add(2);
		ll.remove(0);
		for(int i=0;i<ll.size();i++){
			System.out.println(ll.get(i));
		}*/
		/*WebDriver driver = null;
		try {
			driver = WebUtil.getDriver();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String baseUrl = "";
		long startTime = System.currentTimeMillis();    //获取开始时间

		Properties properties = new Properties();
		try {
        	InputStreamReader in=new InputStreamReader(WebUtil.class.getResourceAsStream("ksd.properties"), "UTF-8");
        	properties.load(in);
        	baseUrl = properties.getProperty("webUrl");

           // System.out.println("z");
        	driver.get(baseUrl);
		//    	Thread.sleep(500);
       } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();    //获取结束时间

		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
*/
	/*	KSDCase ksd=RandomValue.getRandom();
		ksd.setIdentitytype(1);
		ksd.setIdentitynum("350721198605282570");
		ksd.setVin("014W4NI3VTJ6LZK9H");
		ksd.setUsername("夹亚");
		ksd.setSssh("郊区1");
		ksd.setProduct("奇瑞徽银-简易贷及常规产品一区");
		ksd.setCartype(0);
		Map<String, String> actual = UserDaoImpl.getAdvance(ksd);
		Map<String, String> expect = CaseUtil.getAdvance(ksd);
		Assert.assertEquals(actual, expect);*/
	 
	//	ff();
	
		String[] flows={"A","B","C","D","E"};
		for(int i=0;i<10;i++){
			int ftype = (int) (Math.random() * 5);
			System.out.println(flows[ftype]);
		}
	}

	public static void ff() {

		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement stmt = null;
		MyDataSource ds = null;
		ResultSet rs = null;
		try {
			ds = new MyDataSource();
			// 从连接池 中 取得 连接
			for(int i=0;i<6;i++){
			connection = (Connection) ds.getConnection();
			System.out.println("@@"+connection);
			ds.addBackToPool(connection);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 最终 将 连接 放回到 连接池中
		//	ds.addBackToPool(connection);
			System.out.println("使用完 后将连接 放回 连接池中");
			// 释放 资源 并 不将连接 释放
///		JdbcUtils.release(rs, stmt, connection);
			ds.closeCon();
		}
	

	}
}
