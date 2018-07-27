package com.kuaishoudan.financer.flow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebOrgan;
import com.kuaishoudan.financer.selenium.WebSPUtil;
import com.kuaishoudan.financer.selenium.WebUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class TestA {
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
		TestA ct = new TestA();

		System.out.println("***@");

		ct.setUp2();// web启动
		ct.setUp();// app启动

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(new File(
					System.getProperty("user.dir") + "/0615.txt"), true));

			for (int i = 0; i < 1; i++) {
				ksd = ct.dfp();// 待分配app

				ct.webDksp();// 已录

				ct.appBsqht();// App不申请合同-申请请款

				int flag = UserDaoImpl.getRisk_type(ksd);
				if (flag == 1) {
					// System.out.println("\n"+"1"+ksd.getUsername());
					writer.write("1" + ksd.getUsername() + "\n");
				} else {
					System.out.println("1" + ksd.getUsername()
							+ "=================" + flag);
					writer.write("1" + ksd.getUsername() + "================="
							+ flag + "\n");
				}
				// ct.sp1();
				// ct.sp2();
				// ct.sp3();
				// ct.sp4();
				// ct.sp5();
				// ct.sp6();
				// ct.sp7();

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			ex.printStackTrace();

		} finally {
			try {
				writer.flush();
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	public KSDCase dfp() {
		ksd = AppUtil.addTest(driver, webdriver, devicename, 1);
		return ksd;
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

		ksd = testBCSQQK(driver, webdriver, ksd, devicename);

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
			WebSPUtil.testSP2(webdriver, email, itename, ksd); // 请款审批同意专员

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
				AppSPUtil.loginBD(driver, email, ksd);
				AppUtil.login(driver, devicename, ksd);// 登录

				Thread.sleep(1000);
				Map<String, String> map2 = AppSPUtil.getSPname(driver, ksd);// 从app获取审批人名字
				String itename2 = map2.get("prename");
				String email2 = WebSPUtil.nameToemail(map2.get("name"));
				WebSPUtil.testSP3(webdriver, email2, itename2, ksd); // 请款审批同意专员
			} else {
				String itename = map.get("prename");
				String email = WebSPUtil.nameToemail(map.get("name"));
				WebSPUtil.testSP3(webdriver, email, itename, ksd); // 请款审批同意专员
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

	// 不出合同申请请款
	public static KSDCase testBCSQQK(AppiumDriver<AndroidElement> driver,
			WebDriver webdriver, KSDCase ksd, String devicename) {

		int aa = 0, countImg = 0;
		List<Integer> list2 = ksd.getImgtypes();
		List<Integer> list3 = UserDaoImpl.getOMaterial(ksd, 2);
		countImg = list3.size();

		if (countImg == 0) {
			List<Integer> list4 = UserDaoImpl.getOMaterial2(ksd, 2);
			list2.add(list4.get(0));
			countImg = 1;
		} else {
			list2.addAll(list3);
		}
		ksd.setImgtypes(list2);
		System.out.println("$$$" + countImg);
		ksd.setImgcount(countImg);

		if (ksd.getCommit_type() == 2) {
			AppUtil.df(driver,
					By.id("com.kuaishoudan.financer:id/text_product")).click();// 常规产品列表
		} else {

			AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/text_name"))
					.click();// 首页列表
		}

		AppUtil.df(driver,
				By.id("com.kuaishoudan.financer:id/tv_not_apply_compact"))
				.click();// 不出合同
		int gxs = AppUtil.dfs(driver,
				By.id("com.kuaishoudan.financer:id/check_group")).size();// 勾选数
		// System.out.println("gxs" + gxs);
		List<WebElement> noGPS = AppUtil.dfs(driver,
				By.id("com.kuaishoudan.financer:id/check_group"));
		AppUtil.dfBy(driver, noGPS.get(gxs - 1)).click();// 不安装 选择GPS安装方式

		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/toolbar_submit"))
				.click();// 提交

		AppUtil.df(driver,
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 是按钮

		/*
		 * 申请请款445整数进位
		 */

		int acounts = ksd.getSssh_account();
		int acount_c = acounts / 3;
		int acount_y = acounts % 3;
		if (acount_c == 0) {

		} else {
			for (int j = 0; j < acount_c; j++)
				AppUtil.swipeToUp0(driver, 1000);// 向上滑动

		}
		List<WebElement> accouts = AppUtil.dfs(driver,
				By.id("com.kuaishoudan.financer:id/text_request_pay_name"));
		AppUtil.dfBy(driver, accouts.get(acount_y)).click();// dian账号名

		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.click();// xia下标

		AppUtil.df(driver,
				By.id("com.kuaishoudan.financer:id/et_chekuan_chejia"))
				.sendKeys(ksd.getVin());// 车架号
		List<WebElement> ssds = AppUtil.dfs(driver,
				By.id("com.kuaishoudan.financer:id/text_content"));
		AppUtil.dfBy(driver, ssds.get(1)).sendKeys("0");// 购置税
		AppUtil.dfBy(driver, ssds.get(2)).sendKeys("0");// 保险费
		// ssds.get(2).sendKeys("0") ;// 保险费

		AppUtil.df(driver,
				By.id("com.kuaishoudan.financer:id/ll_chekuan_shangpaidiya"))
				.click();// 上牌抵押地
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/options3"))
				.click();// 城市
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		/*
		 * driver.swipe(width * 2 / 3, height - 80, width * 2 / 3, height - 280,
		 * 800);
		 */
		/*
		 * TouchAction action1=new
		 * TouchAction(driver).press(PointOption.point(width*2/3, height-80))
		 * .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		 * .moveTo(PointOption.point(width*2/3, height -280)).release();
		 * action1.perform();
		 */
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/btnSubmit"))
				.click();// 城市确定

		AppUtil.df(driver,
				By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang"))
				.click();// 上牌方

		List<WebElement> spf = AppUtil.dfs(driver,
				By.id("com.kuaishoudan.financer:id/text_select"));
		AppUtil.dfBy(driver, spf.get(ksd.getRegisttype() - 1)).click();

		AppUtil.df(driver,
				By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang"))
				.click();// 抵押方

		List<WebElement> dyf = AppUtil.dfs(driver,
				By.id("com.kuaishoudan.financer:id/text_select"));
		AppUtil.dfBy(driver, dyf.get(ksd.getPledge() - 1)).click();

		AppUtil.df(driver,
				By.id("com.kuaishoudan.financer:id/text_request_other_pay"))
				.click();// 第三方收款

		List<WebElement> yes1 = AppUtil.dfs(driver,
				By.id("com.kuaishoudan.financer:id/text_select"));
		AppUtil.dfBy(driver, yes1.get(1)).click();// 是

		// driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_kouchuxiang")).sendKeys(""+ksd.getDeduction());//扣除款项
		AppUtil.swipeToUp(driver, 1000);// 向上滑动

		AppUtil.uploadQk(driver, ksd.getImgcount());
		// RequestPayout requestPyout = ksd.getRequestpayout();
		/*
		 * try {
		 * 
		 * // AppUtil.testFd(driver, devicename,requestPyout); //
		 * AppUtil.testDy(driver,devicename, requestPyout); //
		 * AppUtil.testZx(driver,devicename, requestPyout); } catch
		 * (InterruptedException e1) { // TODO Auto-generated catch block //
		 * e1.printStackTrace(); } catch (IOException e1) { // TODO
		 * Auto-generated catch block // e1.printStackTrace(); }
		 */

		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_toolbar_confirm")).click();// 确定
		try {
			driver.findElement(By.id("com.kuaishoudan.financer:id/tv_confirm"))
					.click();// 申请请款确定
		} catch (org.openqa.selenium.NoSuchElementException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			for (int j = 0; j < 5; j++) {
				// System.out.println("@@@@@@@@@@@");
				try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				int ss = driver
						.findElements(
								By.id("com.kuaishoudan.financer:id/tv_toolbar_confirm"))
						.size();
				if (ss == 1) {

					AppUtil.df(
							driver,
							By.id("com.kuaishoudan.financer:id/tv_toolbar_confirm"))
							.click();

				} else {

					break;
				}

			}
			AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/tv_confirm"))
			.click();// 申请请款确定
		}

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_countdown"))
				.click();// 倒计时确认
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回

		/*
		 * String actualstatue = AppSPUtil.getActstatue(driver);
		 * ksd.setStatue(actualstatue); Assert.assertEquals(ksd.getStatue(),
		 * "已请款");
		 */

		Assert.assertEquals(UserDaoImpl.getFinanstatue_id(ksd),
				UserDaoImpl.getstatus_id("已请款"));

		Assert.assertEquals(UserDaoImpl.getRisk_type(ksd), 1);

		return ksd;
	}

	public static void contentToTxt(String filePath, String content) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					filePath), true));
			writer.write("\n" + content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
