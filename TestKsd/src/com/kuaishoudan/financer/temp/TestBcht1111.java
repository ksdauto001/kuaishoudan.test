package com.kuaishoudan.financer.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.bean.Employee;
import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.flow.TestA;
import com.kuaishoudan.financer.flow.TestB;
import com.kuaishoudan.financer.flow.TestC;
import com.kuaishoudan.financer.flow.TestD;
import com.kuaishoudan.financer.flow.TestE;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebSPUtil;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.util.DBUtil;
import com.kuaishoudan.financer.util.RandomValue;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class TestBcht1111 {
	public AppiumDriver<AndroidElement> driver;
	String devicename = "";
	public WebDriver webdriver;
	static KSDCase ksd = null;
	List<Employee> employes = null;
	String itename = "";
	String flow="";
	/**
	 * 1.21 0.31 不出合同-审批流
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TestBcht1111 ct = new TestBcht1111();

		System.out.println("***@");

		ct.setUp2();// web启动
		ct.setUp();// app启动

		for (int i = 0; i < 1; i++) {
			ct.dfp();// 待分配app

			ct.webDksp();// 已录

			ct.appBsqht();// App不申请合同-申请请款
			ct.sp1();
			ct.sp2();
		/*	ct.sp3();
			ct.sp4();*/
 
			/*ct.sp5();
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
		  flow = ksd.getFlow();
		switch (flow) {
		case "A":
			ksd = TestA.testBCSQQK(driver, webdriver, ksd, devicename);
			employes = UserDaoImpl.getSpNameid(ksd, 1);
			break;
		case "B":
			ksd = TestB.testBCSQQK(driver, webdriver, ksd, devicename);
			employes = UserDaoImpl.getSpNameid(ksd, 1);
			break;
		case "C":
			ksd = TestC.testBCSQQK(driver, webdriver, ksd, devicename);
			employes = UserDaoImpl.getSpNameid(ksd, 3);
			
			break;
		case "D":
			ksd = TestD.testBCSQQK(driver, webdriver, ksd, devicename);
			employes = UserDaoImpl.getSpNameid(ksd, 3);
			break;
		case "E":
			ksd = TestE.testBCSQQK(driver, webdriver, ksd, devicename);
			employes = UserDaoImpl.getSpNameid(ksd, 3);
			break;
		default:
			System.out.println("default");
			break;
		}

	}

	// 请款审批同意专员
	public void sp1() {
		itename = ksd.getLoginname();
		if("A".equals(flow)||"B".equals(flow)){
		for (Employee ep : employes) {
			if (ep.getDesc().equals("客服专员")) {

				WebSPUtil.testSP1(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				itename = ep.getUsername();
				break;
			}
			if (ep.getDesc().equals("请款审核专员")) {

				WebSPUtil.testSP1(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				itename = ep.getUsername();
				break;
			}
		}
		}else{
			WebSPUtil.testSP1(webdriver, ksd.getLoginemail(), itename, ksd); // 请款审批同意专员
			
		}
	}

	//
	public void sp2() {
 
		for (Employee ep : employes) {

			if (ep.getDesc().equals("数据运营")) {
				switch (flow) {
				case "A":
				WebSPUtil.testSP2(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员

				case "B":
				WebSPUtil.testSP2(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员

				case "C":
				TestC.testSP2(driver,ep.getAccount(), itename, ksd);
			
				break;
				case "D":
				TestD.testSP2(driver,ep.getAccount(), itename, ksd);
				
					break;
				case "E":
				TestE.testSP2(driver, ep.getAccount(), itename, ksd);
				break;
				default:
					System.out.println("default");
					break;				
				}
				itename = ep.getUsername();
				break;
			}
		}
		 
	}

	// 333
	public void sp3() {
		for (Employee ep : employes) {

			if (ep.getDesc().equals("BD经理")) {
				AppSPUtil.loginBD(driver, ep.getAccount(), ksd);
				AppUtil.login(driver, devicename, ksd);// 登录
				itename = ep.getUsername();
				break;
			}

		}
		for (Employee ep : employes) {
			if (ep.getDesc().equals("请款审核组长")) {

				WebSPUtil.testSP3(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				itename = ep.getUsername();
				break;
			}
		}

	}

	public void sp4() {
		for (Employee ep : employes) {
			if (ep.getDesc().equals("财务专员")) {

				WebSPUtil.testSP4(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				itename = ep.getUsername();
				break;
			}
		}

	}

	// 状态已放款
	public void sp5() {
		for (Employee ep : employes) {
			if (ep.getDesc().equals("财务专员")) {

				WebSPUtil.testSP5(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				itename = ep.getUsername();
				break;
			}
		}

	}

	// 归档--通知下发材料

	public void sp6() {

		// ksd = WebSPUtil.testSP6(webdriver, ksd); // 请款审批同意专员
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
