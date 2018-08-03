package com.kuaishoudan.financer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import com.kuaishoudan.financer.selenium.WebDisAgree;
import com.kuaishoudan.financer.selenium.WebSPUtil;
import com.kuaishoudan.financer.selenium.WebUtil;

import com.kuaishoudan.financer.util.DBUtil;
import com.kuaishoudan.financer.util.RandomValue;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestDisagree {
	public AndroidDriver<WebElement> driver;
	String devicename = "";
	public WebDriver webdriver;
	static KSDCase ksd = null;
	List<Employee> employes = null;
	String itename = "";
	String flow = "";
	DBUtil db = null;
	static TestDisagree tda=null;
	/**
	 * 不出合同-审批流（不同意）
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TestDisagree ct = new TestDisagree();

		ct.setUp();// 启动
		int count = ct.getCount();
		for (int i = 0; i < count; i++) {
			long startTime = System.currentTimeMillis(); // 获取开始时间
			ct.dfp(i);// 待分配app
			switch (ksd.getInit_statue()) {
			case 0:
				break;
			case 1:
				ct.webYfp();// 已分配
				break;
			case 2:
				ct.webYlr();// 已录入
				break;
			case 3:
				ct.webDksp();// 已通过
				break;
			case 4:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				break;
			case 5:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				ct.sp1();
				break;
			case 6:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				ct.sp1();
				ct.sp2();
				break;
			case 7:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				ct.sp1();
				ct.sp2();
				ct.sp3();
				break;
			case 8:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				ct.sp1();
				ct.sp2();
				ct.sp3();
				ct.sp4();
				break;
			case 9:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				ct.sp1();
				ct.sp2();
				ct.sp3();
				ct.sp4();
				ct.sp5();
				break;
			case 10:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				ct.sp1();
				ct.sp2();
				ct.sp3();
				ct.sp4();
				ct.sp5();
				ct.sp6();
				break;
			case 11:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				ct.sp1();
				ct.sp2();
				ct.sp3();
				ct.sp4();
				ct.sp5();
				ct.sp6();
				ct.sp7();
				break;
			default:
			}
			long endTime = System.currentTimeMillis(); // 获取结束时间

			System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间

		}

		ct.tearDown();

	}

	public void back() {
		AppUtil.goBack1(driver);
	}

	public void setUp() throws IOException, InterruptedException {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					webdriver = WebUtil.getDriver();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				db = new DBUtil();
				ksd = RandomValue.getRandom();

			}

		}).start();

		driver = AppUtil.getDriver();
		Thread.sleep(300);
		try {
			new WebDriverWait(driver, 2)
					.until(new ExpectedCondition<WebElement>() {
						@Override
						public WebElement apply(WebDriver d) {
							return d.findElement(By
									.id("com.kuaishoudan.financer:id/toolbar_title")); // 标题

						}
					});

		} catch (org.openqa.selenium.TimeoutException e) {
			int confirmsize=driver.findElements(By.id("com.kuaishoudan.financer:id/dialog_confirm")).size();//改权限
			if(confirmsize==1){
				driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_confirm")).click();
			}
			Thread.sleep(200);

			int acs = driver.findElements(
					By.id("com.kuaishoudan.financer:id/btn_login")).size();
			if (acs == 1) {
				AppUtil.login(driver, devicename, ksd);// 登录
			}
			try {
				new WebDriverWait(driver, 1)
						.until(new ExpectedCondition<WebElement>() {
							@Override
							public WebElement apply(WebDriver d) {
								return d.findElement(By
										.id("com.kuaishoudan.financer:id/toolbar_title"));

							}
						});

			} catch (org.openqa.selenium.TimeoutException ex) {

				Thread.sleep(2000);
				for (int i = 0; i < 4; i++) {
					int yx = driver.findElements(
							By.id("com.lbe.security.miui:id/dialog_container"))
							.size();
					if (yx == 1) {
						try {

							driver.switchTo().alert().accept();// 允许权限

						} catch (org.openqa.selenium.NoAlertPresentException ea) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
						}
					}
				}
				Thread.sleep(100);
				int	tgk = driver.findElements(
						By.id("com.kuaishoudan.financer:id/tv_guide_know"))
						.size();

				if (tgk == 1) {
					AppUtil.df(driver,
							By.id("com.kuaishoudan.financer:id/tv_guide_know"))
							.click();// 我知道了

				}
			}

		}catch (org.openqa.selenium.WebDriverException e) {
			
		}

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

	public int getCount() {
		return WebUtil.getCount();
	}

	/**
	 * 创建用户，进件，待审批
	 */
	public void dfp(int i) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if (ksd.getCommit_type() == 2) {
			ksd = AppUtil.addZjjtest(driver, webdriver, devicename, i, ksd);
		} else {
			ksd = AppUtil.addTest(driver, webdriver, devicename, 1);
		}
	}

	/**
	 * web
	 */
	public void webYfp() {
		WebUtil.login(webdriver, ksd);// 登录
		WebUtil.testDFP(webdriver, ksd);// 待分配
		WebUtil.logout(webdriver);// 登出

	}

	public void webYlr() {
		WebUtil.login(webdriver, ksd);// 登录
		WebUtil.testDFP(webdriver, ksd);// 待分配
		WebUtil.testYFP(webdriver, ksd);// 已分配
		WebUtil.logout(webdriver);// 登出

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

		}

	}

	// 请款审批同意专员
	public void sp1() {
		
		itename = ksd.getLoginname();
		if ("A".equals(flow) || "B".equals(flow)) {
			for (Employee ep : employes) {

				if (ep.getDesc().equals("客服专员")) {
					System.out.println(ep.getUsername());
					WebDisAgree.testSP1(webdriver, ep.getAccount(), ksd.getLoginname(), ksd); //不同意
					 WebDisAgree .testDisAgreeQk(driver,ksd);//重新请款
					WebSPUtil.testSP1(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
					itename = ep.getUsername();
		
					break;
				}
				if (ep.getDesc().equals("请款审核专员")) {
					System.out.println(ep.getUsername());
					WebDisAgree.testSP1(webdriver, ep.getAccount(), ksd.getLoginname(), ksd); //不同意
					 WebDisAgree .testDisAgreeQk(driver,ksd);//重新请款
					WebSPUtil.testSP1(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
					itename = ep.getUsername();
	
					break;
				}
			}
		} else {
			System.out.println(ksd.getLoginname());
			WebSPUtil.testSP1(webdriver, ksd.getLoginemail(), itename, ksd); // 请款审批同意专员

		}
	}

	//
	public void sp2() {
		
		for (int i = 0; i < employes.size(); i++) {
			Employee ep = employes.get(i);
			if (ep.getDesc().equals("数据运营")) {
				System.out.println(ep.getUsername());
				WebDisAgree.testSP2(webdriver, ep.getAccount(), itename,ksd);//不同意
 
				 appBsqht22();
				 sp11();
				 sp22();
		 
				break;
			} else if (ep.getDesc().equals("请款审核组长")) {
				System.out.println(ep.getUsername());
				WebDisAgree.testSP2(webdriver, ep.getAccount(), itename,ksd);//不同意
	 
				 appBsqht22();
				 sp11();
				 sp22();
				break;
			} 
		}

	}

	// 333
	public void sp3() {
		for (Employee ep : employes) {

			if (ep.getDesc().equals("BD经理")) {
				System.out.println(ep.getUsername());
				WebDisAgree.loginBD(driver, ep.getAccount(),ksd);
				AppUtil.login(driver, devicename, ksd);// 登录
				 appBsqht22();
				 sp11();
				 sp22();
				 sp33();
				break;
			}
			

		}
		for (Employee ep : employes) {
			if (ep.getDesc().equals("请款审核组长")) {
				System.out.println(ep.getUsername());
				WebDisAgree.testSP3(webdriver, ep.getAccount(), itename,ksd); // 请款审批同意专员
	 
				 appBsqht22();
				 sp11();
				 sp22();
				 sp33();
				break;
			} else if (ep.getDesc().equals("数据运营")) {
				System.out.println(ep.getUsername());
				WebDisAgree.testSP3(webdriver, ep.getAccount(), itename,ksd); // 请款审批同意专员
				 
				 appBsqht22();
				sp11();
				sp22();
				 sp33();
				break;
			}
		}

	}

	// 状态已放款
	public void sp4() {

		for (Employee ep : employes) {
			if (ep.getDesc().equals("财务专员")) {
				System.out.println(ep.getUsername());
				WebDisAgree.testSP4(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				appBsqht22();
				try {
					sp11();
					sp22();
					sp33();
					sp44();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}

		}

	}

	// 状态已回款
	public void sp5() {

		for (Employee ep : employes) {
			if (ep.getDesc().equals("财务专员")) {
				System.out.println(ep.getUsername());
				WebSPUtil.testSP5(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				itename = ep.getUsername();
				break;
			}
		}

	}

	// 归档--通知下发材料

	public void sp6() {

		// ksd = WebSPUtil.testSP6(webdriver, ksd); // 请款审批同意专员
		AppSPUtil.sp6App(driver,webdriver, ksd);

	}

	// 归档

	public void sp7() {
		ksd = WebDisAgree.testSP7(webdriver, ksd);
		sp6();
		ksd = WebSPUtil.testSP7(webdriver, ksd); // 请款审批同意专员

	}

	public void tearDown() throws Exception {

		driver.quit();
		webdriver.quit();
		db.closeConn1();

	}
	// 请款审批同意专员
		public void sp11() {
			itename = ksd.getLoginname();
			if ("A".equals(flow) || "B".equals(flow)) {
				for (Employee ep : employes) {

					if (ep.getDesc().equals("客服专员")) {
						System.out.println(ep.getUsername());
						WebSPUtil.testSP1(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
						itename = ep.getUsername();

						break;
					}
					if (ep.getDesc().equals("请款审核专员")) {
						System.out.println(ep.getUsername());
						WebSPUtil.testSP1(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
						itename = ep.getUsername();

						break;
					}
				}
			} else {
				System.out.println(ksd.getLoginname());
				WebSPUtil.testSP1(webdriver, ksd.getLoginemail(), itename, ksd); // 请款审批同意专员

			}
		}

		//
		public void sp22() {

			for (int i = 0; i < employes.size(); i++) {
				Employee ep = employes.get(i);
				if (ep.getDesc().equals("数据运营")) {
					System.out.println(ep.getUsername());
					switch (flow) {
					case "A":
						WebSPUtil.testSP2(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
						break;
					case "B":
						WebSPUtil.testSP2(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
						break;
					case "C":
						TestC.testSP2(webdriver, ep.getAccount(), itename, ksd);
						break;
					case "D":
						TestD.testSP2(webdriver, ep.getAccount(), itename, ksd);
						break;
					case "E":
						TestE.testSP2(webdriver, ep.getAccount(), itename, ksd);
						break;
					default:
						System.out.println("default");

					}
					itename = ep.getUsername();
					employes.remove(i);
					break;
				} else if (ep.getDesc().equals("请款审核组长")) {
					System.out.println(ep.getUsername());
					switch (flow) {
					case "A":
						WebSPUtil.testSP2(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
						break;
					case "B":
						WebSPUtil.testSP2(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
						break;
					case "C":
						TestC.testSP2(webdriver, ep.getAccount(), itename, ksd);
						break;
					case "D":
						TestD.testSP2(webdriver, ep.getAccount(), itename, ksd);
						break;
					case "E":
						TestE.testSP2(webdriver, ep.getAccount(), itename, ksd);
						break;
					default:
						System.out.println("default");

					}

					itename = ep.getUsername();
					employes.remove(i);
					break;
				} else if (ep.getDesc().equals("BD经理")) {
					System.out.println(ep.getUsername());
					AppSPUtil.loginBD(driver, ep.getAccount(), ksd);
					AppUtil.login(driver, devicename, ksd);// 登录
					itename = ep.getUsername();
					employes.remove(i);
					break;
				}
			}

		}

		// 333
		public void sp33() {
			for (Employee ep : employes) {

				if (ep.getDesc().equals("BD经理")) {
					System.out.println(ep.getUsername());
					AppSPUtil.loginBD(driver, ep.getAccount(), ksd);
					AppUtil.login(driver, devicename, ksd);// 登录
					itename = ep.getUsername();
					break;
				}
				/*
				 * if (ep.getDesc().equals("BD经理")) {
				 * System.out.println(ep.getUsername());
				 * WebSPUtil.testSP3(webdriver, ep.getAccount(), itename, ksd); //
				 * 请款审批同意专员 itename = ep.getUsername(); break; }
				 */

			}
			for (Employee ep : employes) {
				if (ep.getDesc().equals("请款审核组长")) {
					System.out.println(ep.getUsername());
					WebSPUtil.testSP3(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
					itename = ep.getUsername();
					break;
				} else if (ep.getDesc().equals("数据运营")) {
					System.out.println(ep.getUsername());
					WebSPUtil.testSP3(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
					itename = ep.getUsername();
					break;
				}
			}

		}

		// 状态已放款
		public void sp44() {
			for (Employee ep : employes) {
				if (ep.getDesc().equals("财务专员")) {
					System.out.println(ep.getUsername());
					WebSPUtil.testSP4(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
					itename = ep.getUsername();
					break;
				}
			}

		}

		
		public void appBsqht22() {
			ksd= WebDisAgree .testDisAgreeQk(driver,ksd);//重新请款
			flow = ksd.getFlow();
			switch (flow) {
			case "A":
		
				employes = UserDaoImpl.getSpNameid(ksd, 1);
				break;
			case "B":

				employes = UserDaoImpl.getSpNameid(ksd, 1);
				break;
			case "C":

				employes = UserDaoImpl.getSpNameid(ksd, 3);

				break;
			case "D":

				employes = UserDaoImpl.getSpNameid(ksd, 3);
				break;
			case "E":
	
				employes = UserDaoImpl.getSpNameid(ksd, 3);
				break;
			default:
				System.out.println("default");

			}
		 

		}

}
