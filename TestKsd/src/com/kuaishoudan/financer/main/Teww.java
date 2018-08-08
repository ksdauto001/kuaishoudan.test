package com.kuaishoudan.financer.main;

import io.appium.java_client.android.AndroidElement;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
		//ff();
		/* final Timer timer = new Timer();
		    TimerTask task = new TimerTask() {
		        private int count;

		        @Override
		        public void run() {
		         
		            this.count++;
		            System.out.println(count);
		          
		      //      System.out.println("Hello !!");  
	        		 
	        		System.out.println("Hello !!");  
	    			 
		        }
		    };
		    timer.schedule(task, 0,300);// 1秒一次	    
		    System.out.println(32);
		    System.out.println(92);
		    fa();*/
	/*	double a=0;
		String b="0";
		if(a==Double.parseDouble(b)){
			System.out.println("相等");
		}*/
	/*	KSDCase ksd=new KSDCase();
		ksd.setSqdk(24.368);
		ksd.setPurchase_tax("1.1");
		ksd.setInsurance("2.2");
		ksd.setDeduction(2.5);ksd.setVin("11111111111111111110");
		CaseUtil.getAdvance(ksd);*/
		DecimalFormat decimalFormat = new DecimalFormat(
				"###################.#######");
		double dew=22.727*10000 ;
 
		 BigDecimal dsd=new BigDecimal( dew );
 
		double d=dsd.setScale(0, RoundingMode.HALF_UP).doubleValue() ;

 
		double car_loan_charge=227270;
		KSDCase ksd=new KSDCase ();
		ksd.setPurchase_tax("3987.39");
		ksd.setInsurance("4074.77");
		ksd.setDeduction( 55.54 );
		System.out.println(ksd.getDeduction());
		 BigDecimal fff=new BigDecimal( ksd.getDeduction() );
		 double deduction= fff.setScale(2, RoundingMode.HALF_UP).doubleValue();
		 double toalcharge=car_loan_charge+Double.parseDouble(ksd.getPurchase_tax())+Double.parseDouble(ksd.getInsurance())- deduction;
		/* BigDecimal toalchargedecimal=new BigDecimal( toalcharge );
		 double toalcharge2= toalchargedecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();*/
		 System.out.println(decimalFormat.format(toalcharge));
	}

	public static void fa(){
		System.out.println("322221");
		 /*final Timer timer = new Timer();
		    TimerTask task = new TimerTask() {
		        private int count;

		        @Override
		        public void run() {
		         
		            this.count++;
		            System.out.println(count);
		          
		      //      System.out.println("Hello !!");  
	        		List<AndroidElement>	yscs= driver.findElements(By.className("android.widget.TextView"));
	        		for(int i=0;i<yscs.size();i++){
						System.out.println(yscs.size()+"!@#"+yscs.get(i).getText());
					
					}
	        		System.out.println("Hello !!");  
	    			int a=yscs.size()-1;
					System.out.println(a+"@@"+yscs.get(a).getText());
					if(yscs.get(a).getText().equals("已上传")){
						
						
					      System.out.println("定时器停止了");
					      timer.cancel();// 停止定时器
					}
		        }
		    };
		    timer.schedule(task, 0,300);// 1秒一次
*/	}
	public static void ff() {

		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement stmt = null;
		MyDataSource ds = null;
		ResultSet rs = null;
		try {
			ds = new MyDataSource();
			// 从连接池 中 取得 连接
			connection = (Connection) ds.getConnection();
			System.out.println("@@"+connection);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 最终 将 连接 放回到 连接池中
		//	ds.addBackToPool(connection);
			System.out.println("使用完 后将连接 放回 连接池中");
			// 释放 资源 并 不将连接 释放
			JdbcUtils.release(rs, stmt, connection);
		}

	}
}
