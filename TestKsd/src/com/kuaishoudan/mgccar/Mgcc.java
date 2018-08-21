package com.kuaishoudan.mgccar;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.bean.Supplier;
import com.kuaishoudan.financer.dao.SpplierDaoImpl;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.util.DBUtil;
import com.kuaishoudan.financer.util.RandomValue;

public class Mgcc {
	static AndroidDriver<WebElement> driver;
	static KSDCase ksd;
	DBUtil db = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mgcc mc = new Mgcc();
		try {
			mc.setUp();

			int count = mc.getCount();

			for (int i = 0; i < count; i++) {
				Thread.sleep(400);

				testJj(driver, ksd);// 进件

				mgcGJBXX(driver, ksd);// 基本信息
				mgcGCXX(driver, ksd);// 购车信息

				upload(driver);// 上传照片
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mc.tearDown();
	}
	public int getCount() {
		return WebUtil.getCount();
	}

	public void tearDown(){
		driver.quit();
		
		db.closeConn1();
	}
	public void setUp() throws IOException, InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {

				db = new DBUtil();
			
				ksd = RandomValue.getRandom();
			}

		}).start();
		driver = getDriver();
		Thread.sleep(300);
		try {
			new WebDriverWait(driver, 2)
					.until(new ExpectedCondition<WebElement>() {
						@Override
						public WebElement apply(WebDriver d) {
							return d.findElement(By
									.id("com.kuaishoudan.mgccar:id/tv_toolbar_title")); // 标题

						}
					});


		} catch (org.openqa.selenium.TimeoutException e) {
			int status=SpplierDaoImpl.mgccStatus(ksd.getMgccar_phone());
			if(status==1){
				mgcLogin(ksd);// 登录

			}else{
				mgcRegist();//注册
				mgcLogin(ksd);// 登录
				mgcBind();//绑定信息
			}
		}
	}

	public static AndroidDriver<WebElement> getDriver()
			throws MalformedURLException {

		String apkName = "";
		Properties properties = new Properties();
		try {
			InputStreamReader in = new InputStreamReader(
					AppUtil.class.getResourceAsStream("ksd.properties"),
					"UTF-8");
			properties.load(in);
			apkName = properties.getProperty("mgccar_Name");

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
		capabilities.setCapability("app-package", "com.kuaishoudan.mgccar");
		capabilities.setCapability("app-activity",
				"com.kuaishoudan.mgccar.personal.activity.WelcomeActivity");
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(
				new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver,
		 * new AlertListener(), new ElementListener());
		 */

		return driver;

	}
	public static void mgcBind() {
		List<Supplier> spls = SpplierDaoImpl.getSuplier(ksd);
		String splcode=spls.get(ksd.getSssh_id()).getSupplier_code();
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/bind_no")).sendKeys(splcode );//绑定账号
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/tv_next")).click();//下一步
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/dialog_custom_confirm")).click();//确定
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/tv_department")).click();//选择部门
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElements(By.id("com.kuaishoudan.mgccar:id/tv_title")).get(1).click();//金融部
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/tv_role")).click();//选择角色
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElements(By.id("com.kuaishoudan.mgccar:id/tv_title")).get(1).click();//销售经理

		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/tv_succed")).click();//下一步
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/bind_no")).sendKeys(ksd.getUsername());//请输入姓名
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/tv_succed")).click();//完成
	}
	
	public static void testJj(AndroidDriver<WebElement> driver, KSDCase ksd){
		ksd = RandomValue.getRandom();
		  DecimalFormat df2 = new DecimalFormat("#.00");
		 ksd.setCarprice( Double.parseDouble(df2.format(ksd.getCarprice())));
		 ksd.setSqdk( Double.parseDouble(df2.format(ksd.getSqdk())));
		    
		System.out.println("名称" + ksd.getUsername() + "手机"
				+ ksd.getPhone() + "身份证号" + ksd.getIdentitynum()
				+ "身份类型" + ksd.getIdentitytype() + "军官" + ksd.getJgid()
				+ "企业个人" + ksd.getQygr() + "车类型" + ksd.getCartype()
				+ "车品牌" + ksd.getCarbrand() + "车系" + ksd.getCarseries()
				+ "车价格" + ksd.getCarprice() + "贷款价格" + ksd.getSqdk()
				+ "融资期限" + ksd.getHkqs() + "\n  "
				+ ksd.getPurchase_tax() + " " + ksd.getInsurance()
				+ " " + ksd.getGps_charge() + " "
				+ ksd.getService_charge() + "," + ksd.getRegisttype()
				+ "," + ksd.getPledge());
		System.out.println(ksd.getFlow() + "," + ksd.getLoginemail()+","+ksd.getMgccar_phone());
		List<WebElement> pros=driver.findElements(By.id("com.kuaishoudan.mgccar:id/tv_produce_name"));
		pros.get(2).click();//金融产品
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/tv_add")).click();//进件
		
		
		
	}
	public static void mgcRegist() {

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/btn_registered"))
				.click();// 注册
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_phone")).sendKeys(ksd.getMgccar_phone());// 手机号
		AppUtil.df(driver,
				By.id("com.kuaishoudan.mgccar:id/btn_captcha")).click();//获取验证码
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver,
				By.id("com.kuaishoudan.mgccar:id/edit_captcha"))
				.sendKeys(SpplierDaoImpl.mgccCaptcha(ksd.getMgccar_phone()));// 验证码
		AppUtil.df(driver,
				By.id("com.kuaishoudan.mgccar:id/edit_pwd")).sendKeys(ksd.getMgccar_pwd());//密码
		
		AppUtil.df(driver,
				By.id("com.kuaishoudan.mgccar:id/btn_register")).click();//注册按钮
	}
	public static void mgcLogout() {

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/tv_main_mine_text"))
				.click();// 我的
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/tv_exit")).click();// 退出登录

		AppUtil.df(driver,
				By.id("com.kuaishoudan.mgccar:id/dialog_custom_confirm"))
				.click();// 确定
	}

	public static void mgcLogin(KSDCase ksd) {
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_account"))
				.clear();
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_account"))
				.sendKeys(ksd.getMgccar_phone());
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_password"))
				.sendKeys(ksd.getMgccar_pwd());

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/btn_login"))
				.click();
	}

	public static void mgcGJBXX(AndroidDriver<WebElement> driver, KSDCase ksd) {
/*		AppUtil.df(driver,
				By.id("com.kuaishoudan.mgccar:id/tv_toolbar_confirm")).click();*/
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_name"))
				.sendKeys(ksd.getUsername());
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_phone"))
				.sendKeys(ksd.getPhone());

		if (ksd.getIdentitytype() == 1) {
			// 点击身份证

			// 证件号码 *****
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_id_code"))
					.sendKeys(ksd.getIdentitynum());
		} else if (ksd.getIdentitytype() == 2) {
			// 点击军官证
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_id_type"))
					.click();// 点身份证
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElements(By.id("com.kuaishoudan.mgccar:id/text_select"))
					.get(1).click();// 点军官证
			// 证件号码 *****
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_id_code"))
					.sendKeys(ksd.getJgid());
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_id_address"))
				.sendKeys(ksd.getAddress()); // 地址
	}

	public static void mgcGCXX(AndroidDriver<WebElement> driver, KSDCase ksd) {

		int ran = ksd.getCartype();
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
		if (ran == 1) {
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_car_type"))
					.click();// 新车或二手车
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/check_old_car")).get(1)
					.click();
			// 二手车
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_brand"))
				.click();

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_brand"))
				.click();
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_series"))
				.click();// 车系
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_price"))
				.sendKeys("" + ksd.getCarprice());// 开票价

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_loan"))
				.sendKeys("" + ksd.getSqdk());// 预估贷款额
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_periods"))
				.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElements(By.id("com.kuaishoudan.mgccar:id/tv_title"))
				.get(ksd.getHkqs()+1).click();// 还款期数
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/tv_rate"))
				.click();// 费率
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		driver.findElements( By.id("com.kuaishoudan.mgccar:id/tv_title")).get(1)
					.click();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	driver.findElement(By.id("com.kuaishoudan.mgccar:id/edit_remark"))
				.sendKeys(ksd.getRemark());// 备注.
		int gq = ksd.getQygr();
		if (gq == 2) {
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/cb_main"))
					.click();// 企业进件代码
			AppUtil.df(driver,
					By.id("com.kuaishoudan.mgccar:id/et_company_name"))
					.sendKeys(ksd.getBusinessname());// 企业名称.
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/et_company_no"))
					.sendKeys(ksd.getBusinessid());// 营业执照.
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/btn_next")).click();// 下一步

	}
	/**
	 *进件购车信息 ( 未完成)，新
	 * 
	 */
	public static  void  mgcGCXX2(AndroidDriver<WebElement> driver, KSDCase ksd){
		


		int ran = ksd.getCartype();
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

		/*if (ran == 1) {
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_car_type"))
					.click();// 新车或二手车
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/check_old_car")).get(1)
					.click();
			// 二手车
		}*/
		//品牌车系
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_brand"))
				.click();

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_brand"))
				.click();
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_series"))
				.click();// 车系
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//开票价
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_price"))
				.sendKeys("" + ksd.getCarprice());
		//申请贷款
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_loan"))
				.sendKeys("" + ksd.getSqdk());
		
		//融资期限
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_periods"))
				.click();
	 
		driver.findElements(By.id("com.kuaishoudan.mgccar:id/text_select"))
				.get(ksd.getHkqs()).click();
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 费率
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_remark"))
				.sendKeys(ksd.getRemark());
		
		// 备注
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_remark"))
				.sendKeys(ksd.getRemark());
		
		//个人企业进件
		int gq = ksd.getQygr();
		if (gq == 2) {
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/cb_main"))
					.click();// 企业进件
			AppUtil.df(driver,
					By.id("com.kuaishoudan.mgccar:id/edit_company_name"))
					.sendKeys(ksd.getBusinessname());// 企业名称.
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/tv_company_no"))
					.sendKeys(ksd.getBusinessid());// 营业执照.
		}
		
		// 下一步
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/btn_next")).click();
		
	}
	/**
	 * app请款(未完成)
	 */
	public static  void  mgcQk(AndroidDriver<WebElement> driver, KSDCase ksd){
		//账户名
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_remark"))
		.sendKeys(ksd.getRemark());
		//账户 
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_remark"))
		.sendKeys(ksd.getRemark());
		//车价贷款额
		AppUtil.df(driver, By.id(""))
		.sendKeys(ksd.getRemark());
		//GPS费
		AppUtil.df(driver, By.id(""))
		.sendKeys("0");
	

		//保险费
		if (Double.parseDouble(ksd.getInsurance()) == 0) {
			AppUtil.df(driver, By.id("")).sendKeys("0");// 保险费
		}
		//购置税
		
		if (Double.parseDouble(ksd.getPurchase_tax()) == 0) {
			AppUtil.df(driver, By.id("")).sendKeys("0");// 购置税
		}	
		
		//上牌抵押地
		AppUtil.df(driver, By.id("")).click();	
		//备注
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_remark"))
		.sendKeys(ksd.getRemark());	
		
	}
	/**
	 * 所需材料
	 * @param driver
	 * @param ksd
	 */
	public  static void sxcl(AndroidDriver<WebElement> driver, KSDCase ksd){
		
		
	}
	/**
	 * 上传照片
	 * 
	 * @param driver
	 * @return
	 */
	public static String upload(final AndroidDriver<WebElement> driver) throws org.openqa.selenium.TimeoutException {
		String acstatue = "";
	//	int imgcount = ksd.getImgcount();
		 int imgcount=5;
		int count1 = imgcount / 10;
		int count2 = imgcount % 10;
		try {
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/iv_add"))
			.click();// 上传照片;
			for (int j = 0; j < count1; j++) {// 10

				if (j > 0) {

					AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/iv_add"))
							.click();// 上传照片;

				}

				AppUtil.df(
						driver,
						By.id("com.kuaishoudan.mgccar:id/tv_choose_img"))
						.click();
				// 从相册选择
				List<WebElement> imgs =	AppUtil.dfs(driver,
						By.id("com.kuaishoudan.mgccar:id/iv_thumb"));
				for (int i = 0; i < 10; i++) {
					if (i == 0 && j > 0) {
						for (int k = 0; k < (j * 10); k++)

							imgs.get(i).click();// 添加图片（驾驶证）

					} else if (i == 0 && j == 0) {

					} else {

						imgs.get(i).click();// 添加图片（驾驶证）
					}

					imgs.get(i).click();// 添加图片（驾驶证）
				}
			
				AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/btn_ok")).click();
				// 两种证上传——确定按钮
				Thread.sleep( 500);
				AppUtil.swipeToUp2(driver, 1000);// 向上滑动
				Thread.sleep(8500);

			}

			if (count2 == 0 && count1 > 0) {

			} else {

				if (count1 != 0) {
					AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/iv_add"))
							.click();// 上传照片;

				}
				AppUtil.df(
						driver,
						By.id("com.kuaishoudan.mgccar:id/tv_choose_img"))
						.click();
				// 从相册选择
				
				Thread.sleep(200);
				List<WebElement> imgs = AppUtil.dfs(driver,
						By.id("com.kuaishoudan.mgccar:id/iv_thumb"));
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
				} 

				//
				AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/btn_ok")).click();
				// 两种证上传——确定按钮

				Thread.sleep(count2 * 600);

			}

			driver.findElement(
					By.id("com.kuaishoudan.mgccar:id/tv_complain"))
					.click();
			// 提交

			Thread.sleep(1200);

			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.openqa.selenium.TimeoutException e) {
			// System.out.println(e);
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			
		} catch (org.openqa.selenium.WebDriverException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	
	
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/iv_toolbar_back"))
				.click();// 返回按钮
		return acstatue;
	}
}
