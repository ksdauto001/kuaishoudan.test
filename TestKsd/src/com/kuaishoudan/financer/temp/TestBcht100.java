package com.kuaishoudan.financer.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebSPUtil;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.util.RandomValue;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class TestBcht100 {
	public AppiumDriver<AndroidElement> driver;
	String devicename = "";
	public WebDriver webdriver;
	static KSDCase ksd = null;

	/**
	 * 不出合同-审批流
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TestBcht100 ct = new TestBcht100();
		 ksd = RandomValue.getRandom();
		 ksd.setSssh("测试");
		System.out.println("***@");
		ct.setUp2();// web启动
		ct.setUp();// app启动
	
		for (int i = 0; i < 20; i++) {
		/*	ct.dfp();// 待分配app

			ct.webDksp();// 已录

			ct.appBsqht();// App不申请合同-申请请款
			ct.sp1();
			ct.sp2();
			ct.sp3();*/
			ct.sp4();
			ct.back();
		/*	ct.sp5();
			ct.sp6();
			ct.sp7();*/

		}

		ct.tearDown();
		// ct.back()
	}

	public void back() {
		AppUtil.goBack1(driver);
	}

	public void setUp() throws IOException, InterruptedException {
		driver = AppUtil.getDriver();

		

	}

	// web
	public void setUp2() throws IOException, InterruptedException {
		webdriver = WebUtil.getDriver();

	}

	public void loginWeb(String username) {
	

		WebUtil.login(webdriver, ksd);// 登录
	}

	public void logoutWeb() {
 

		WebUtil.logout(webdriver);// 登出
	}

	/**
	 * 创建用户，进件，待审批
	 */
	public void dfp() {
		ksd = AppUtil.addTest(driver, webdriver, devicename, 1);

	}

	/**
	 * web
	 */
	public void webDksp() {
		WebUtil.login(webdriver, ksd);// 登录
		WebUtil.testDFP(webdriver, ksd);// 待分配
		WebUtil.testYFP(webdriver, ksd);// 已分配
		WebUtil.testYLR(webdriver, ksd);// 已录入
		WebUtil.logout(webdriver);// 登出

	}

	// App不申请合同
	public void appBsqht() {

		ksd = AppSPUtil.testBCSQQK(driver, webdriver, ksd, devicename);

	}



	// 申请请款
	public void appSqqk() {
		AppSPUtil.testHTSQQK(driver, webdriver, ksd, devicename);// 请款
		// System.out.println(AppUtil.getStatue(driver));
		// AppUtil.look_status(driver);//查看进度

	}

	// 请款审批同意专员
	public void sp1() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP1(webdriver, email, itename, ksd); // 请款审批同意专员
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//
	public void sp2() {
		try {
			Map<String, String> map = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP2(webdriver, email, itename,ksd); // 请款审批同意专员

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 333
	public void sp3() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字

			if (map.size() == 1) {
				// bd操作

				String email = WebSPUtil.nameToemail(map.get("name"));
				AppSPUtil.loginBD(driver, email,ksd);
				AppUtil.login(driver, devicename, ksd);// 登录

				Thread.sleep(1000);
				Map<String, String> map2 = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字
				String itename2 = map2.get("prename");
				String email2 = WebSPUtil.nameToemail(map2.get("name"));
				WebSPUtil.testSP3(webdriver, email2, itename2,ksd); // 请款审批同意专员
			} else {
				String itename = map.get("prename");
				String email = WebSPUtil.nameToemail(map.get("name"));
				WebSPUtil.testSP3(webdriver, email, itename,ksd); // 请款审批同意专员
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sp4() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP4(webdriver, email, itename, ksd); // 请款审批同意专员
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 状态已放款
	public void sp5() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP5(webdriver, email, itename, ksd); // 请款审批同意专员
			AppUtil.goBack1(driver);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 归档--通知下发材料

	public void sp6() {

		ksd = WebSPUtil.testSP6(webdriver, ksd); // 请款审批同意专员
		AppSPUtil.sp6App(driver, ksd);

	}

	// 归档

	public void sp7() {

		ksd = WebSPUtil.testSP7(webdriver, ksd); // 请款审批同意专员

	}

	public void tearDown() throws Exception {

		driver.quit();
		webdriver.quit();
	}
}
