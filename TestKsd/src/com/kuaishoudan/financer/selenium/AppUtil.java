package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import sun.print.resources.serviceui;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.bean.RequestPayout;
import com.kuaishoudan.financer.dao.UserDaoImpl;
 
import com.kuaishoudan.financer.main.TestUser;
import com.kuaishoudan.financer.util.CaseUtil;
import com.kuaishoudan.financer.util.IdCardGenerator;
import com.kuaishoudan.financer.util.RandomValue;

public class AppUtil {

	public static AndroidDriver<WebElement> getDriver()
			throws MalformedURLException {

		String apkName = "";
		Properties properties = new Properties();
		try {
			InputStreamReader in = new InputStreamReader(
					AppUtil.class.getResourceAsStream("ksd.properties"),
					"UTF-8");
			properties.load(in);
			apkName = properties.getProperty("appName");

			System.out.println("+++++++++++" + apkName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// set up appium
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, apkName);// financerfinalVersionjiagusign.apk
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("automationName", "uiautomator2");
		capabilities.setCapability("newCommandTimeout", 4000);
		capabilities.setCapability("device", "Android");
		capabilities.setCapability("platformName", "Android");
		// 虚拟机
		// capabilities.setCapability("deviceName", "Android Emulator");
		// 真机
		capabilities.setCapability("deviceName", "Android");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(CapabilityType.VERSION, "4.4");

		// support Chinese
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);

		// capabilities.setCapability("autoLaunch", false);

		capabilities.setCapability("noSign", true);
		capabilities.setCapability("noReset", true);

		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("app-package", "com.kuaishoudan.financer");
		capabilities.setCapability("app-activity",
				"com.kuaishoudan.financer.activity.act.WelcomeActivity");
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), capabilities);
		
	/*	   driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver,
		   new AlertListener(), new ElementListener());*/
		 

		return driver;

	}

	public static WebElement df(AppiumDriver driver, final By dr) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		return wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(dr);
			}
		});
		// By.xpath("//android.widget.RelativeLayout[@index='2']")
	}

	public static List<WebElement> dfs(AppiumDriver driver, final By dr) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		return (List<WebElement>) wait
				.until(new ExpectedCondition<List<WebElement>>() {
					@Override
					public List<WebElement> apply(WebDriver d) {
						return d.findElements(dr);
					}
				});
	}

	public static WebElement dfBy(WebDriver driver, final WebElement w) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		return wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return w;
			}
		});
		// By.xpath("//android.widget.RelativeLayout[@index='2']")
	}

	static Duration duration = Duration.ofSeconds(1);

	// 向上滑动
	public static void swipeToUp0(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);

		/*
		 * driver.swipe(width / 2, height*7/8 , width / 2, height *3/16,
		 * during);
		 */
		// wait for page loading12801321

		TouchAction action1 = new TouchAction(driver)
				.press(PointOption.point(width / 2, height * 7 / 8))
				.waitAction(WaitOptions.waitOptions(duration))
				.moveTo(PointOption.point(width / 2, height * 3 / 16))
				.release();
		action1.perform();

	}

	// 向上滑动
	public static void swipeToUpJj(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		// for (int i = 0; i < 2; i++)
		/*
		 * driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4,
		 * during);
		 */
		// wait for page loading12801321'
		TouchAction action1 = new TouchAction(driver)
				.press(PointOption.point(width / 2, height * 3 / 4))
				.waitAction(WaitOptions.waitOptions(duration))
				.moveTo(PointOption.point(width / 2, height / 4)).release();
		action1.perform();
	}

	// 向上滑动
	public static void swipeToUp(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		/*
		 * for (int i = 0; i < 2; i++) driver.swipe(width / 2, height * 3 / 4,
		 * width / 2, height / 4, during);
		 */
		// wait for page loading12801321
		for (int i = 0; i < 2; i++) {
			TouchAction action1 = new TouchAction(driver)
					.press(PointOption.point(width / 2, height * 3 / 4))
					.waitAction(WaitOptions.waitOptions(duration))
					.moveTo(PointOption.point(width / 2, height / 4)).release();
			action1.perform();
		}
	}

	// 向上滑动
	public static void swipeToUp2(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		for (int i = 0; i < 9; i++)
		/*
		 * driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4,
		 * during);
		 */
		{
			TouchAction action1 = new TouchAction(driver)
					.press(PointOption.point(width / 2, height * 3 / 4))
					.waitAction(WaitOptions.waitOptions(duration))
					.moveTo(PointOption.point(width / 2, height / 4)).release();
			action1.perform();
		}

	}

	// 向上滑动
	public static void swipeToUp3(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		for (int i = 0; i < 1; i++)
	
		{
			TouchAction action1 = new TouchAction(driver)
					.press(PointOption.point(width / 2, height * 3 / 4))
					.waitAction(WaitOptions.waitOptions(duration))
					.moveTo(PointOption.point(width / 2, height / 4)).release();
			action1.perform();
		}

	}

	// 向上滑动
	public static void swipeToUpQk(AppiumDriver driver, int during, int count) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		for (int i = 0; i < (count+3 ); i++)
		/*
		 * driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4,
		 * during);
		 */
		{
			TouchAction action1 = new TouchAction(driver)
					.press(PointOption.point(width / 2, height * 7 / 8))
					.waitAction(WaitOptions.waitOptions(duration))
					.moveTo(PointOption.point(width / 2, height *11/ 16)).release();
			action1.perform();
		}

	}

	// 向下滑动
	public static void swipeToDown(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		// for (int i = 0; i < 2; i++)
		/*
		 * driver.swipe(width / 2, height * 3 / 4, width / 2, height - 20,
		 * during);
		 */
		// wait for page loading12801321
		TouchAction action1 = new TouchAction(driver)
				.press(PointOption.point(width / 2, height / 4))
				.waitAction(WaitOptions.waitOptions(duration))
				.moveTo(PointOption.point(width / 2, height * 3 / 4)).release();
		action1.perform();
	}

	/**
	 * 创建用户
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */

	public static boolean createUser(AndroidDriver<WebElement> driver,
			String devicename, int k, KSDCase ksd) {
		boolean flag = false;

		try {
			new WebDriverWait(driver, 6).until(
					new ExpectedCondition<WebElement>() {
						@Override
						public WebElement apply(WebDriver d) {
							return d.findElement(By
									.id("com.kuaishoudan.financer:id/toolbar_custom_img"));

						}
					}).click();// +号
			df(driver, By.id("com.kuaishoudan.financer:id/menu_manual_input"))
					.click(); // 手动输入
		} catch (org.openqa.selenium.TimeoutException e) {
			df(driver, By.id("com.kuaishoudan.financer:id/ll_input")).click();// 手动输入

		}
		try {

			df(driver, By.id("com.kuaishoudan.financer:id/edit_name"))
					.sendKeys(ksd.getUsername());// 名字

			df(driver, By.id("com.kuaishoudan.financer:id/edit_phone"))
					.sendKeys(ksd.getPhone()); // 手机

			df(driver, By.id("com.kuaishoudan.financer:id/text_id_type"))
					.click();// 点击身份证

			if (ksd.getIdentitytype() == 1) {
				// 点击身份证
				dfs(driver, By.id("com.kuaishoudan.financer:id/text_select"))
						.get(1).click();
				// 证件号码 *****
				df(driver, By.id("com.kuaishoudan.financer:id/edit_id_code"))
						.sendKeys(ksd.getIdentitynum());
			} else if (ksd.getIdentitytype() == 2) {
				// 点击军官证

				dfs(driver, By.id("com.kuaishoudan.financer:id/text_select"))
						.get(2).click();
				// 证件号码 *****
				df(driver, By.id("com.kuaishoudan.financer:id/edit_id_code"))
						.sendKeys(ksd.getJgid());
			}

			df(driver, By.id("com.kuaishoudan.financer:id/edit_id_address"))
					.sendKeys(ksd.getAddress()); // 地址
			df(driver, By.id("com.kuaishoudan.financer:id/toolbar_confirm"))
					.click();// 确认

			df(driver,
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click(); // 马上进件
			flag = true;

		} catch (org.openqa.selenium.NoSuchElementException ex) {

			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * 个人贷款
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static KSDCase addGr(AndroidDriver<WebElement> driver,
			WebDriver webdriver, String devicename, int k, KSDCase ksd) {

		String actualstatue = "";

		boolean flag = false;
		boolean cx = false;

		try {

			df(
					driver,
					By.id("com.kuaishoudan.financer:id/btn_select_order_type_individual"))
					.click();
			// 去申请
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			// TODO Auto-generated catch block
			System.out.println(ex);
			flag = true;
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();
		}catch (org.openqa.selenium.WebDriverException ex) {
			
		}
		if (!flag) {
			try {
				int ran = ksd.getCartype();
				// ------------
				if (ran == 1) {
					df(driver,
							By.id("com.kuaishoudan.financer:id/check_old_car"))
							.click();
					// 二手车
				} else {
					df(driver,
							By.id("com.kuaishoudan.financer:id/layout_new_car"))
							.click();
					// 新车
				}

				df(driver, By.id("com.kuaishoudan.financer:id/text_supplier"))
						.click();
				// 所属商户
				// List<WebElement> suppliers = dfs(driver,
				// By.id("com.kuaishoudan.financer:id/tv_name"));

				WebElement supplier = dfBy(
						driver,
						dfs(driver,
								By.id("com.kuaishoudan.financer:id/tv_name"))
								.get(ksd.getSssh_id()));
				ksd.setSssh(supplier.getText());
				supplier.click();// 所属商户列表

				df(driver, By.id("com.kuaishoudan.financer:id/text_brand"))
						.click();
				// 品牌车系

				try {

					List<WebElement> clpps = dfs(
							driver,
							By.id("com.kuaishoudan.financer:id/item_brand_item"));// 车辆品牌（奥迪）
					/*
					 * for (int i = 0; i < clpps.size(); i++) { String brand =
					 * clpps .get(i) .findElement(
					 * By.id("com.kuaishoudan.financer:id/text_brand"))
					 * .getText();
					 * 
					 * if (ksd.getCarbrand().equals(brand)) {
					 * clpps.get(i).click(); break; }
					 * 
					 * }
					 */
					WebElement Cbrand = dfBy(
							driver,
							clpps.get(0)
									.findElement(
											By.id("com.kuaishoudan.financer:id/text_brand")));
					ksd.setCarbrand(Cbrand.getText());
					Cbrand.click();// 车辆品牌点
					cx = true;
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
				try {
					if (cx) {
						List<WebElement> seriess = dfs(
								driver,
								By.id("com.kuaishoudan.financer:id/text_series"));// 车辆品牌（奥迪）
						/*
						 * for (int i = 0; i < seriess.size(); i++) { String
						 * series = seriess.get(i).getText(); if
						 * (ksd.getCarseries().equals(series)) {
						 * seriess.get(i).click();// 车辆型号 break; }
						 * 
						 * }
						 */
						WebElement cseries = dfBy(driver, seriess.get(0));
						ksd.setCarseries(cseries.getText());
						cseries.click();
					}
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				df(driver, By.id("com.kuaishoudan.financer:id/edit_price"))
						.sendKeys("" + ksd.getCarprice());// 车辆价格

				df(driver, By.id("com.kuaishoudan.financer:id/edit_loan"))
						.sendKeys("" + ksd.getSqdk());// 申请贷款

				df(driver, By.id("com.kuaishoudan.financer:id/text_periods"))
						.click();

				// 还款期数 / 融资期限

				dfs(driver, By.id("com.kuaishoudan.financer:id/text_select"))
						.get(ksd.getHkqs()).click();// 还款期数周期 /融资期限

				df(driver, By.id("com.kuaishoudan.financer:id/text_product"))
						.click(); // 金融产品

				try {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					List<WebElement> producs = dfs(driver,
							By.id("com.kuaishoudan.financer:id/text_product"));

					for (int i = 0; i < producs.size(); i++) {

						if (!producs.get(i).getText().contains("平安租赁")) {
							if (i == 0) {
								df(
										driver,
										By.id("com.kuaishoudan.financer:id/text_product"))
										.click();// 第一个产品

							} else {
								dfBy(
										driver,
										dfs(
												driver,
												By.id("com.kuaishoudan.financer:id/text_product"))
												.get(i)).click();// 产品
							}
							break;

						}
					}

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}catch (org.openqa.selenium.WebDriverException e) {
						// TODO Auto-generated catch block
					//	e.printStackTrace();
					df(
							driver,
							By.id("com.kuaishoudan.financer:id/text_product"))
							.click();// 第一个产品
				}
				// _________

				try {
					Thread.sleep(100);

					AppUtil.swipeToUpJj(driver, 1000);// 向上滑动

					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (org.openqa.selenium.WebDriverException e) {
					AppUtil.swipeToUpJj(driver, 1000);// 向上滑动
				}

				df(driver, By.id("com.kuaishoudan.financer:id/text_feilv"))
						.click();// 费率

				try {

					// List<WebElement> rates = dfs(driver,
					// By.id("com.kuaishoudan.financer:id/text_select"));

					// dfBy(driver,rates.get(0)).click();// 费率选项
					df(driver, By.id("com.kuaishoudan.financer:id/text_select"))
							.click();
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

			
				WebElement prodname = df(driver,
						By.id("com.kuaishoudan.financer:id/text_product"));

				ksd.setProduct(prodname.getText());
				WebElement rate = df(driver,
						By.id("com.kuaishoudan.financer:id/text_feilv"));

				ksd.setRate(rate.getText());
				
				df(driver, By.id("com.kuaishoudan.financer:id/edit_remark"))
				.sendKeys(ksd.getRemark());// 备注
				if(ksd.getInit_statue()==100){
					WebUtil.login(webdriver, ksd);// 登录
					 WebOrgan.getImge1(webdriver, ksd);//创建供应商图片

					WebUtil.logout(webdriver);
					
				}
				df(driver, By.id("com.kuaishoudan.financer:id/toolbar_next"))
						.click();// 下一步

				if (ran == 1) { // 二手车
					int havesystem = UserDaoImpl.gethave_system(ksd
							.getProduct().trim().split("-")[0]);// 产品名称查是否有常规甩单
					System.out.println(ksd.getProduct().trim().split("-")[0]
							+ "," + havesystem);
					if (havesystem == 0) {
						// 订单常规
						df(
								driver,
								By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
								.click();
					}
				}

				int aa = 0, countImg = 0;

				List<Integer> list2 = new ArrayList<Integer>();

				List<Integer> list3 = UserDaoImpl.getOMaterial(ksd, 1);
				countImg = list3.size();

				if (countImg == 0) {
					List<Integer> list4 = UserDaoImpl.getOMaterial2(ksd, 1);
					countImg = 1;

					try {
						list2.add(list4.get(0));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					list2.addAll(list3);
				}
				ksd.setImgtypes(list2);
				System.out.println(list2.size() + "$$$" + countImg);

				ksd.setImgcount(countImg);

				df(driver, By.id("com.kuaishoudan.financer:id/btn_add"))
						.click();
				// 上传照片

			} catch (org.openqa.selenium.NoSuchElementException ex) {
				ex.printStackTrace();

			} catch (org.openqa.selenium.WebDriverException e) {

				e.printStackTrace();

			} catch (java.lang.IndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

			actualstatue = upload(driver, ksd);

		}
		return ksd;
	}

	/**
	 * 企业贷款
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static KSDCase addQy(AndroidDriver<WebElement> driver,
			WebDriver webdriver, String devicename, int k, KSDCase ksd) {

		String actualstatue = "";
		boolean flag = false;
		boolean cx = false;

		try {
			df(
					driver,
					By.id("com.kuaishoudan.financer:id/btn_select_order_type_company"))
					.click();
			// 去申请
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			// TODO Auto-generated catch block
			System.out.println(ex);
			flag = true;
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();
		}catch (org.openqa.selenium.WebDriverException ex) {
			
		}
		if (!flag) {
			try {

				df(driver,
						By.id("com.kuaishoudan.financer:id/edit_company_name"))
						.sendKeys(ksd.getBusinessname());// 企业名称
				df(
						driver,
						By.id("com.kuaishoudan.financer:id/edit_company_business_license"))
						.sendKeys(ksd.getBusinessid());
				// 营业执照号

				int ran = ksd.getCartype();
				// ------------
				if (ran == 1) {
					df(driver,
							By.id("com.kuaishoudan.financer:id/check_old_car"))
							.click();// 二手车
				} else {
					df(driver,
							By.id("com.kuaishoudan.financer:id/layout_new_car"))
							.click();// 新车
				}
				// ___________

				df(driver, By.id("com.kuaishoudan.financer:id/text_supplier"))
						.click();// 所属商户

				// List<WebElement> suppliers = ;

				WebElement supplier = dfBy(
						driver,
						dfs(driver,
								By.id("com.kuaishoudan.financer:id/tv_name"))
								.get(ksd.getSssh_id()));
				ksd.setSssh(supplier.getText());
				supplier.click();// 所属商户列表

				df(driver, By.id("com.kuaishoudan.financer:id/text_brand"))
						.click();

				try {
					List<WebElement> clpps = dfs(
							driver,
							By.id("com.kuaishoudan.financer:id/item_brand_item"));// 车辆品牌（奥迪）
					/*
					 * for (int i = 0; i < clpps.size(); i++) { String brand =
					 * clpps .get(i) .findElement(
					 * By.id("com.kuaishoudan.financer:id/text_brand"))
					 * .getText();
					 * 
					 * if (ksd.getCarbrand().equals(brand)) {
					 * clpps.get(i).click(); break; }
					 * 
					 * }
					 */
					WebElement Cbrand = dfBy(
							driver,
							clpps.get(0)
									.findElement(
											By.id("com.kuaishoudan.financer:id/text_brand")));
					ksd.setCarbrand(Cbrand.getText());
					Cbrand.click();// 车辆品牌点
					cx = true;
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				try {
					if (cx) {

						List<WebElement> seriess = dfs(
								driver,
								By.id("com.kuaishoudan.financer:id/text_series"));// 车辆品牌（奥迪）
						/*
						 * for (int i = 0; i < seriess.size(); i++) { String
						 * series = seriess.get(i).getText(); if
						 * (ksd.getCarseries().equals(series)) {
						 * seriess.get(i).click();// 车辆型号 break; }
						 * 
						 * }
						 */
						WebElement cseries = dfBy(driver, seriess.get(0));
						ksd.setCarseries(cseries.getText());
						cseries.click();
					}
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
				df(driver, By.id("com.kuaishoudan.financer:id/edit_price"))
						.sendKeys("" + ksd.getCarprice());// 车辆价格

				df(driver, By.id("com.kuaishoudan.financer:id/edit_loan"))
						.sendKeys("" + ksd.getSqdk());// 申请贷款

				df(driver, By.id("com.kuaishoudan.financer:id/text_periods"))
						.click();// 还款期数 / 融资期限
				dfs(driver, By.id("com.kuaishoudan.financer:id/text_select"))
						.get(ksd.getHkqs()).click(); // 还款期数周期 /融资期限
				// _________

				try {
					Thread.sleep(100);

					AppUtil.swipeToUpJj(driver, 1000);// 向上滑动

					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (org.openqa.selenium.WebDriverException e) {
					AppUtil.swipeToUpJj(driver, 1000);// 向上滑动
				}

				df(driver, By.id("com.kuaishoudan.financer:id/text_product"))
						.click(); // 金融产品

				try {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					List<WebElement> producs = dfs(driver,
							By.id("com.kuaishoudan.financer:id/text_product"));

					for (int i = 0; i < producs.size(); i++) {

						if (!producs.get(i).getText().contains("平安租赁")) {
							if (i == 0) {
								df(
										driver,
										By.id("com.kuaishoudan.financer:id/text_product"))
										.click();// 第一个产品

							} else {
								dfBy(
										driver,
										dfs(
												driver,
												By.id("com.kuaishoudan.financer:id/text_product"))
												.get(i)).click();// 产品
							}
							break;

						}

					}

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}catch (org.openqa.selenium.WebDriverException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				df(
						driver,
						By.id("com.kuaishoudan.financer:id/text_product"))
						.click();// 第一个产品
				}

				try {

					df(driver, By.id("com.kuaishoudan.financer:id/text_feilv"))
							.click();// 费率
					// List<WebElement> rates = dfs(driver,
					// By.id("com.kuaishoudan.financer:id/text_select"));

					// dfBy(driver,rates.get(0)).click();// 费率选项
					df(driver, By.id("com.kuaishoudan.financer:id/text_select"))
							.click();

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}


				WebElement prodname = df(driver,
						By.id("com.kuaishoudan.financer:id/text_product"));
				ksd.setProduct(prodname.getText());
				WebElement rate = df(driver,
						By.id("com.kuaishoudan.financer:id/text_feilv"));
				ksd.setRate(rate.getText());


				df(driver, By.id("com.kuaishoudan.financer:id/edit_remark"))
						.sendKeys(ksd.getRemark());// 备注
				if(ksd.getInit_statue()==100){
				WebUtil.login(webdriver, ksd);// 登录
				 WebOrgan.getImge1(webdriver, ksd);//创建供应商图片

				WebUtil.logout(webdriver);
				}
				df(driver, By.id("com.kuaishoudan.financer:id/toolbar_next"))
						.click();// 下一步

				if (ran == 1) { // 二手车
					int havesystem = UserDaoImpl.gethave_system(ksd
							.getProduct().trim().split("-")[0]);// 产品名称查是否有常规甩单

					System.out.println(ksd.getProduct().trim().split("-")[0]
							+ "," + havesystem);

					if (havesystem == 0) {
						df(
								driver,
								By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
								.click();// 订单常规
					}
				}

				List<Integer> list2 = new ArrayList<Integer>();
				int aa = 0, countImg = 0;

				List<Integer> list3 = UserDaoImpl.getOMaterial(ksd, 1);
				countImg = list3.size();

				if (countImg == 0) {
					List<Integer> list4 = UserDaoImpl.getOMaterial2(ksd, 1);
					countImg = 1;

					try {
						list2.add(list4.get(0));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					list2.addAll(list3);
				}
				ksd.setImgtypes(list2);
				System.out.println(list2.size() + "$$$" + countImg);

				ksd.setImgcount(countImg);
				df(driver, By.id("com.kuaishoudan.financer:id/btn_add"))
						.click();
				// 上传照片
			} catch (org.openqa.selenium.NoSuchElementException ex) {

				ex.printStackTrace();

			} catch (org.openqa.selenium.WebDriverException e) {

				e.printStackTrace();

			} catch (java.lang.IndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

			actualstatue = upload(driver, ksd);

		}
		return ksd;
	}

	public static KSDCase addTest(AndroidDriver<WebElement> driver,
			WebDriver webdriver, String devicename, int i) {

		KSDCase ksd = RandomValue.getRandom();

		System.out.println("名称" + ksd.getUsername() + "手机" + ksd.getPhone()
				+ "身份证号" + ksd.getIdentitynum() + "身份类型"
				+ ksd.getIdentitytype() + "军官" + ksd.getJgid() + "企业个人"
				+ ksd.getQygr() + "车类型" + ksd.getCartype() + "车品牌"
				+ ksd.getCarbrand() + "车系" + ksd.getCarseries() + "车价格"
				+ ksd.getCarprice() + "贷款价格" + ksd.getSqdk() + "融资期限"
				+ ksd.getHkqs() + "\n  " + ksd.getPurchase_tax() + " "
				+ ksd.getInsurance() + " " + ksd.getGps_charge() + " "
				+ ksd.getService_charge() + "," + ksd.getRegisttype() + ","
				+ ksd.getPledge());
		System.out.println(ksd.getFlow() + "," + ksd.getLoginemail());

		int gq = ksd.getQygr();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = createUser(driver, devicename, i, ksd);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> expect = CaseUtil.getCustomer(ksd);
		Map<String, String> actual = UserDaoImpl.getCustomer(ksd);
		Assert.assertEquals(actual, expect);
		if (flag) {

			if (gq == 2) {// 企业贷款
				ksd = addQy(driver, webdriver, devicename, i, ksd);
			} else {// 个人贷款
				ksd = addGr(driver, webdriver, devicename, i, ksd);
				//
			}

			
			 // String statue = AppSPUtil.getActstatue(driver);
			 // Assert.assertEquals(statue, "待分配");
			 
			Assert.assertEquals(UserDaoImpl.getFinanStatue_id(ksd),
					UserDaoImpl.getstatus_id("待分配"));

		}

		return ksd;

	}

	// 再次进件
	public static int zcjj(AndroidDriver<WebElement> driver, KSDCase ksd) {

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		int a = UserDaoImpl.getUser_Count(ksd);
		System.out.println("a"+a);
		if (a == 1) {
			
			df(driver, By.id("com.kuaishoudan.financer:id/text_name")).click();// 首页列表
			df(driver, By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
					.click();

			df(
					driver,
					By.id("com.kuaishoudan.financer:id/text_customer_algin_jinjian"))
					.click(); // 大于1次进件

		}else {
			df(driver, By.id("com.kuaishoudan.financer:id/text_name")).click();// 首页列表
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			df(driver, By.id("com.kuaishoudan.financer:id/btn_add_loan"))
					.click();// 第3次进件3

		}

		return a;
	}
	// 再次进件
		public static int zcjj2(AndroidDriver<WebElement> driver, KSDCase ksd) {

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			int a = UserDaoImpl.getUser_Count(ksd);
			System.out.println("a"+a);
			if (a == 2&&(ksd.getInit_statue()<4)) {
				
				df(driver, By.id("com.kuaishoudan.financer:id/text_name")).click();// 首页列表
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				df(driver, By.id("com.kuaishoudan.financer:id/btn_add_loan"))
						.click();// 第3次进件3

			}else {
			
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				df(driver, By.id("com.kuaishoudan.financer:id/btn_add_loan"))
						.click();// 第3次进件3

			}

			return a;
		}
	/**
	 * 上传照片
	 * 
	 * @param driver
	 * @return
	 */
	public static String upload(final AndroidDriver<WebElement> driver,
			KSDCase ksd) throws org.openqa.selenium.TimeoutException {
		String acstatue = "";
		int imgcount = ksd.getImgcount();
		// imgcount=1;
		int count1 = imgcount / 20;
		int count2 = imgcount % 20;
		try {

			for (int j = 0; j < count1; j++) {// 20

				if (j > 0) {

					df(driver, By.id("com.kuaishoudan.financer:id/btn_add"))
							.click();// 上传照片;

				}

				df(
						driver,
						By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
						.click();
				// 从相册选择
				List<WebElement> imgs = dfs(driver,
						By.id("com.kuaishoudan.financer:id/iv_thumb"));
				for (int i = 0; i < 12; i++) {
					if (i == 0 && j > 0) {
						for (int k = 0; k < (j * 20); k++)

							imgs.get(i).click();// 添加图片（驾驶证）

					} else if (i == 0 && j == 0) {

					} else {

						imgs.get(i).click();// 添加图片（驾驶证）
					}

					imgs.get(i).click();// 添加图片（驾驶证）
				}
				AppUtil.swipeToUp3(driver, 800);// 向上滑动
				Thread.sleep(200);
				List<WebElement> ivts = dfs(driver,
						By.id("com.kuaishoudan.financer:id/iv_check"));

				List<WebElement> imgsss = dfs(driver,
						By.id("com.kuaishoudan.financer:id/iv_thumb"));
				System.out.println(ivts.size() + ",," + imgsss.size());
				int ccc = imgsss.size() - ivts.size();
				int m = 0;
				for (int i = 0; i < ivts.size(); i++) {
					if (m == 8) {
						break;
					}
					if (ivts.get(i).getAttribute("selected").equals("false")) {
						imgsss.get(i + ccc).click();
						// 添加图片（驾驶证）

						imgsss.get(i + ccc).click();// 添加图片（驾驶证）
						m++;

					}
				}
				df(driver, By.id("com.kuaishoudan.financer:id/btn_ok")).click();
				// 两种证上传——确定按钮

				AppUtil.swipeToUp2(driver, 1000);// 向上滑动
				Thread.sleep(8500);

			}

			if (count2 == 0 && count1 > 0) {

			} else {

				if (count1 != 0) {
					df(driver, By.id("com.kuaishoudan.financer:id/btn_add"))
							.click();// 上传照片;

				}
				df(
						driver,
						By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
						.click();
				// 从相册选择
				
				Thread.sleep(200);
				List<WebElement> imgs = dfs(driver,
						By.id("com.kuaishoudan.financer:id/iv_thumb"));
				if (count2 == 0 && count1 == 0) {

					imgs.get(0).click();// 添加图片（驾驶证）
					// 添加图片（驾驶证）
				} else if (count2 > 0 && count2 <= 12) {

					for (int i = 0; i < count2; i++) {
						if (i == 0) {
							for (int k = 0; k < (count1 * 20); k++)

								imgs.get(i).click();// 添加图片（驾驶证）

						} else {

							imgs.get(i).click();// 添加图片（驾驶证）
						}

						imgs.get(i).click();// 添加图片（驾驶证）
					}
				} else if (count2 > 12 && count2 < 21) {

					for (int i = 0; i < 12; i++) {
						if (i != 0)

							imgs.get(i).click();// 添加图片（驾驶证）

						imgs.get(i).click();// 添加图片（驾驶证）
					}
					AppUtil.swipeToUp3(driver, 800);// 向上滑动
					Thread.sleep(200);
					List<WebElement> ivts = dfs(driver,
							By.id("com.kuaishoudan.financer:id/iv_check"));

					List<WebElement> imgsss = dfs(driver,
							By.id("com.kuaishoudan.financer:id/iv_thumb"));
					// System.out.println(ivts.size()+",,"+imgsss.size());
					int ccc = imgsss.size() - ivts.size();
					int n = 0;
					for (int i = 0; i < ivts.size(); i++) {
						if (n == (count2 - 12)) {
							break;
						}
						if (ivts.get(i).getAttribute("selected")
								.equals("false")) {

							imgsss.get(i + ccc).click();
							// 添加图片（驾驶证）

							imgsss.get(i + ccc).click();// 添加图片（驾驶证）
							n++;
						}
					}

				}

				//
				df(driver, By.id("com.kuaishoudan.financer:id/btn_ok")).click();
				// 两种证上传——确定按钮

				Thread.sleep(count2 * 400);

			}
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_confirm"))
					.click();
			// 上传完照片-确认按钮
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();
			// 提醒确定是

			Thread.sleep(1200);

			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.openqa.selenium.TimeoutException e) {
			// System.out.println(e);
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			// df(driver,By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
			// System.out.println(ex);
			/*
			 * String title=
			 * driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title"
			 * )).getText().trim(); if (title.equals("拍摄资料")) {
			 */
			for (int j = 0; j < 5; j++) {
				// System.out.println("@@@@@@@@@@@");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	int usestatue = UserDaoImpl.getFinanStatue_id(ksd);

			  	if (usestatue == 1) {
			/*	int ss= driver.findElements(By.id("com.kuaishoudan.financer:id/toolbar_confirm")).size();
				if (ss==1) {*/
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_confirm"))
							.click();
					// 上传完照片-确认按钮
					int size = driver
							.findElements(
									By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
							.size();
					// System.out.println("size"+size);
					if (size == 1) {
						driver.findElement(
								By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
								.click();
						// 提醒确定是
					}

				} else {

					break;
				}
				// }
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (org.openqa.selenium.WebDriverException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		int countR = driver.findElements(
				By.className("android.widget.RelativeLayout")).size();
		// System.out.println("--==="+countR);
		if (countR == 8) {

			df(driver, By.id("com.kuaishoudan.financer:id/tv_guide_know"))
					.click();// 我知道了
			df(driver, By.id("com.kuaishoudan.financer:id/tv_guide_know"))
					.click();// 我知道了

		}
		df(driver, By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回按钮
		return acstatue;
	}

	public static KSDCase addZjjtest(AndroidDriver<WebElement> driver,
			WebDriver webdriver, String devicename, int i, KSDCase ksd) {
		int gq = 0;
	//	System.out.println(i);
		if (i == 0 ) {
			ksd = RandomValue.getKSD(driver);
			ksd = RandomValue.getRandom(driver, ksd);
			AppUtil.zcjj(driver, ksd);
		} else if(i==1&&(ksd.getInit_statue()<4)){
//s			ksd = RandomValue.getKSD(driver);
			ksd = RandomValue.getRandom(driver, ksd);
			AppUtil.zcjj2(driver, ksd);
		}else {
			ksd = RandomValue.getRandom(driver, ksd);
			df(driver, By.id("com.kuaishoudan.financer:id/btn_add_loan"))
					.click();// 第3次进件qi
		}
		System.out.println("名称" + ksd.getUsername() + "手机" + ksd.getPhone()
				+ "身份证号" + ksd.getIdentitynum() + "身份类型"
				+ ksd.getIdentitytype() + "军官" + ksd.getJgid() + "企业个人"
				+ ksd.getQygr() + "车类型" + ksd.getCartype() + "车品牌"
				+ ksd.getCarbrand() + "车系" + ksd.getCarseries() + "车价格"
				+ ksd.getCarprice() + "贷款价格" + ksd.getSqdk() + "融资期限"
				+ ksd.getHkqs());
		gq = ksd.getQygr();
		if (gq == 2) {// 企业贷款
			ksd = addQy(driver, webdriver, devicename, i, ksd);
		} else {// 个人贷款
			ksd = addGr(driver, webdriver, devicename, i, ksd);
		}
		
		Assert.assertEquals(UserDaoImpl.getFinanStatue_id(ksd),
				UserDaoImpl.getstatus_id("待分配"));
		return ksd;
	}

	public static void login(AndroidDriver<WebElement> driver,
			String devicename, KSDCase ksd) {

		try {

			df(driver, By.id("com.kuaishoudan.financer:id/edit_account"))
					.clear();

			df(driver, By.id("com.kuaishoudan.financer:id/edit_account"))
					.sendKeys(ksd.getLoginemail());

			df(driver, By.id("com.kuaishoudan.financer:id/edit_password"))
					.sendKeys(ksd.getPwd());

			df(driver, By.id("com.kuaishoudan.financer:id/btn_login")).click();
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 登出
	public static void logout(AndroidDriver<WebElement> driver) {

		df(driver, By.id("com.kuaishoudan.financer:id/toolbar_menu")).click();// 菜单

		df(driver, By.id("com.kuaishoudan.financer:id/header_img")).click();// 头像

		df(driver, By.id("com.kuaishoudan.financer:id/account_logout")).click();// 退出登录

		df(driver, By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 确定)
	}

	public static boolean ElementExist(AndroidDriver<WebElement> driver,
			By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			return false;
		}

	}

	/**
	 * 已请款-----返回查看状态
	 * 
	 * @param driver
	 */
	public static String getStatue(AndroidDriver<WebElement> driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		String statue = AppSPUtil.getActstatue(driver);// 状态值
		return statue;

	}

	/**
	 * (未使用) 查看进度
	 * 
	 * @param driver
	 */
	public static void look_status(AndroidDriver<WebElement> driver) {
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		// driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
		/*
		 * driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		 * .get(0).click();// 第一个客户
		 * driver.manage().timeouts().implicitlyWait(28, TimeUnit.SECONDS);
		 * driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/toolbar_loan_status")) .click();//
		 * 流程。。。 driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		 * driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/text_customer_look_status"))
		 * .click();// 查看进度
		 */

	}

	/**
	 * 两次返回
	 * 
	 * @param driver
	 */
	public static void goBack1(AndroidDriver<WebElement> driver) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回按钮
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回按钮
	 
	}

	/**
	 * 一次返回
	 * 
	 * @param driver
	 */
	public static void goBack0(AndroidDriver<WebElement> driver) {
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回按钮
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getIndexname(AndroidDriver<WebElement> driver) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = dfBy(
				driver,
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_name")).get(0))
				.getText().trim();
		return name;
	}

	/**
	 * 上传照片
	 * 
	 * @param driver
	 * @return
	 */
	public static String uploadQk(AndroidDriver<WebElement> driver,
			int imgcount) {
		String acstatue = "";
		//imgcount=15;
		//System.out.println(imgcount);
		int count1 = imgcount / 10;
		int count2 = imgcount % 10;
		try {

			for (int j = 0; j < count1; j++) {// 10

				df(driver, By.id("com.kuaishoudan.financer:id/btn_add"))
						.click();// 上传照片

				df(
						driver,
						By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
						.click();// 从相册选择
				List<WebElement> imgs = dfs(driver,
						By.id("com.kuaishoudan.financer:id/iv_thumb"));
				for (int i = 0; i < 10; i++) {
					if (i == 0 && j > 0) {
						for (int k = 0; k < (j * 10); k++)
							imgs.get(i).click();// 添加图片（驾驶证）
					} else if (i == 0 && j == 0) {

					} else {
						imgs.get(i).click();// 添加图片（驾驶证）第一次
					}
					imgs.get(i).click();// 添加图片（驾驶证）
				}

				df(driver, By.id("com.kuaishoudan.financer:id/btn_ok")).click();// 两种证上传——确定按钮
				Thread.sleep( 500);
				AppUtil.swipeToUp2(driver, 1000);// 向上滑动
				Thread.sleep(7500);

			}

			if (count2 == 0 && count1 > 0) {

			} else {

				df(driver, By.id("com.kuaishoudan.financer:id/btn_add"))
						.click();// 上传照片

				df(
						driver,
						By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
						.click();// 从相册选择
				Thread.sleep(200);
				List<WebElement> imgs = dfs(driver,
						By.id("com.kuaishoudan.financer:id/iv_thumb"));
				if (count2 == 0 && count1 == 0) {

					imgs.get(0).click();// 添加图片（驾驶证）
				} else if (count2 > 0 && count2 < 11) {

					for (int i = 0; i < count2; i++) {
						if (i == 0) {
							for (int k = 0; k < (count1 * 10); k++)
								imgs.get(i).click();// 添加图片（驾驶证）
						} else {
							imgs.get(i).click();// 添加图片（驾驶证）第一次
						}
						imgs.get(i).click();// 添加图片（驾驶证）
					}
				}

				//

				df(driver, By.id("com.kuaishoudan.financer:id/btn_ok")).click();// 两种证上传——确定按钮
				
				Thread.sleep(500 + count2 * 500);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (org.openqa.selenium.NoSuchElementException ex) {
			
		}catch (org.openqa.selenium.WebDriverException e) {
			e.printStackTrace();

		}

		return acstatue;
	}

	// 返点费用支出
	public static void testFd(AndroidDriver<WebElement> driver,
			String devicename, RequestPayout RequestPyout)
			throws InterruptedException, IOException {

		AppUtil.swipeToUp3(driver, 1000);// 向上滑动
		Thread.sleep(500);

		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.get(0).click();
		AppUtil.swipeToUp(driver, 1000);// 向上滑动
		// 车款融资额返点

		Thread.sleep(500);

		List<WebElement> fds = driver.findElements(By
				.id("com.kuaishoudan.financer:id/text_content"));
		fds.get(0).sendKeys(RequestPyout.getFinancing_back_point());

		// GPS返点

		fds.get(1).sendKeys(RequestPyout.getGps_back_point());

		// 保险返点

		fds.get(2).sendKeys(RequestPyout.getInsurance_back_point());

		// 服务费返点

		fds.get(3).sendKeys(RequestPyout.getService_back_point());

		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.get(0).click();
	}

	// 新车抵押费用支出

	public static void testDy(AndroidDriver<WebElement> driver,
			String devicename, RequestPayout RequestPyout)
			throws InterruptedException, IOException {

		AppUtil.swipeToUp3(driver, 1000);// 向上滑动
		Thread.sleep(500);
	List<WebElement> ivts=	driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show"));
		System.out.println(ivts.size());
	ivts.get(1).click();
		AppUtil.swipeToUp(driver, 1000);// 向上滑动
		Thread.sleep(500);

		// 抵押费
	 
	List<WebElement> dys=	driver.findElements(By.id("com.kuaishoudan.financer:id/text_content"));
				dys.get(0).sendKeys(RequestPyout.getMortgage_free() );
 
		// 解押费
 
		dys.get(1).sendKeys(RequestPyout.getSign_free() );
 
		// 上牌抵押地
		df(driver,
				By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaidiya"))
				.click();
		df(driver,By.id("com.kuaishoudan.financer:id/options3"))
				.click();// 城市
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
 
		/*TouchAction action1 = new TouchAction(driver)
				.press(PointOption.point(width * 2 / 3, height - 80))
				.waitAction(WaitOptions.waitOptions(duration))
				.moveTo(PointOption.point(width * 2 / 3, height - 280))
				.release();
		action1.perform();*/

		df(driver, By.id("com.kuaishoudan.financer:id/btnSubmit")).click();// 城市确定
 

		// 上牌方
		df(driver,
				By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang"))
				.click();
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(RequestPyout.getRegistration_party()).click();

		// 抵押方
		df(driver,
				By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang"))
				.click();
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(RequestPyout.getRegistration_party()).click();
		Thread.sleep(500);
//		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show"))
		ivts.get(1).click();

	}

	// 新车杂项费用支出

	public static void testZx(AndroidDriver<WebElement> driver,
			String devicename, RequestPayout RequestPyout)
			throws InterruptedException, IOException {
		Thread.sleep(300);
		AppUtil.swipeToUp3(driver, 1000);// 向上滑动
		Thread.sleep(500);
		List<WebElement> ivts=	driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show"))
		;System.out.println(ivts.size());
		ivts.get(2).click();
		AppUtil.swipeToUp(driver, 1000);// 向上滑动
		// GPS安装费

		Thread.sleep(500);
		List<WebElement> zxs = driver.findElements(By
				.id("com.kuaishoudan.financer:id/text_content"));
		zxs.get(0).sendKeys(RequestPyout.getGps_installation());

		// 前置利息

		zxs.get(1).sendKeys(RequestPyout.getInterest_on_pre());

		// 退款

		zxs.get(2).sendKeys(RequestPyout.getRefund());

		// 车价贷款(返款)

		zxs.get(3).sendKeys(RequestPyout.getThe_car_loan());
		Thread.sleep(500);
		//driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show"))
		ivts.get(2).click();

	}

}
