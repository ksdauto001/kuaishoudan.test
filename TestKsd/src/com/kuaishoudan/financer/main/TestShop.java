package com.kuaishoudan.financer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.bean.ShopBeanCase;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppShopUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebShop;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.util.DBUtil;
import com.kuaishoudan.financer.util.RandomValue;

public class TestShop {

	/**
	 * 创建商户
	 * @param args
	 */

	public AndroidDriver<WebElement> driver;
	public String devicename = "";
	public WebDriver webdriver;
	KSDCase ksd = null;
	DBUtil db = null;
	ShopBeanCase shopBeanCase = new ShopBeanCase();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		TestShop ct = new TestShop();
		ct.setUp();// app启动
		int count = ct.getCount();

		for (int i = 0; i < count; i++) {

			ct.appCreateShop();
			ct.webCreateShop();
		}

		ct.tearDown();

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

			}

		}).start();
		driver = AppUtil.getDriver();

	}

	public void tearDown() throws Exception {
		driver.quit();
		webdriver.quit();
	}

	public int getCount() {
		return WebUtil.getCount();
	}

	// 创建商户，备案信息
	public void appCreateShop() {
		try {
			ksd = RandomValue.getRandom();
			shopBeanCase = RandomValue.getShop();
			ksd.setSssh(shopBeanCase.getShopname());
			shopBeanCase = AppShopUtil.createShop(driver, shopBeanCase,
					devicename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("##===" + shopBeanCase.getStatue());
	}

	// 审批
	public void webCreateShop() {
		try {
			WebUtil.login(webdriver, ksd);// 登录
			WebShop.test2(webdriver, ksd);
			WebUtil.logout(webdriver);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("##===" + shopBeanCase.getStatue());
	}

	public void goback() {
		AppUtil.goBack0(driver);
	}

}
