package com.kuaishoudan.financer.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebSPUtil;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.util.CaseUtil;
import com.kuaishoudan.financer.util.RandomValue;

/**
 * 不出合同1
 * 
 * @author Administrator
 * 
 */
public class Test200 {

	public AppiumDriver<AndroidElement> driver = null;
	String devicename = "";
	public WebDriver webdriver = null;
	KSDCase ksd = null;

	@BeforeTest
	public void setUp() throws Exception {
		webdriver = WebUtil.getDriver();
		driver = AppUtil.getDriver();
	
		ksd = RandomValue.getRandom();
		System.out.println("名称" + ksd.getUsername() + "手机" + ksd.getPhone()
				+ "身份证号" + ksd.getIdentitynum() + "身份类型"
				+ ksd.getIdentitytype() + "军官" + ksd.getJgid() + "企业个人"
				+ ksd.getQygr() + "车类型" + ksd.getCartype() + "车品牌"
				+ ksd.getCarbrand() + "车系" + ksd.getCarseries() + "车价格"
				+ ksd.getCarprice() + "贷款价格" + ksd.getSqdk() + "融资期限"
				+ ksd.getHkqs() + "\n  " + ksd.getPurchase_tax() + "\n "
				+ ksd.getInsurance() + " \n" + ksd.getGps_charge() + "\n "
				+ ksd.getService_charge() + "," + ksd.getRegisttype() + ","
				+ ksd.getPledge());
		ksd.setSssh("谢全额鹅鹅鹅");
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
		webdriver.quit();
	}

	

	@Test(priority = 12, invocationCount = 1, threadPoolSize = 1)
	public void test12() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver,ksd);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP4(webdriver, email, itename,ksd); // 请款审批同意专员
			AppUtil.goBack1(driver);//
			String statue = AppSPUtil.getActstatue(driver);
			Assert.assertEquals(statue, "已放款");
			Assert.assertEquals(UserDaoImpl.getFinanstatue_id(ksd),
					UserDaoImpl.getstatus_id("已放款"));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
