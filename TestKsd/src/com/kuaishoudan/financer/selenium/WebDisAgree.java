package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.util.RandomValue;

public class WebDisAgree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new WebDisAgree().test1();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test1() throws MalformedURLException {
		WebDriver webdriver = WebUtil.getDriver();
		KSDCase ksd = RandomValue.getRandom();
		WebDisAgree.testSP1(webdriver, "liqj@jizhicar.com", "刘浩亮", ksd); // 请款审批同意专员
	}

	// 请款审批同意专员

	public static boolean testSP1(WebDriver driver, String email,
			String itename, KSDCase ksd) {
		// String username="niun@jizhicar.com";
		boolean flag = false;

		if (email.equals(ksd.getLoginemail()) || email.equals("")) {
			WebSPUtil.login2(driver, ksd.getLoginemail(), ksd.getPwd());
			WebUtil.df(driver,By.linkText("客户")).click();
			WebUtil.df(driver,By.linkText("请款管理")).click();
			WebSPUtil.clickItemorder(driver, itename);
 

			WebUtil.df(driver,By.linkText("不同意")).click();
		 
			WebUtil.df(driver,By.name("remark"))
					.sendKeys(
							"自2017年市行政审批局成立以来，我市持续加大简政放权力度。 截至目前， 行政审批许可事项由2017年的212项减少为117项，削减率近50%。2017年4月， 市行政审批局正式成立。一年来，该局以构建审批标准化为统领，以破解行政审批工作服务全局遇到的难点问题为导向，立足深化 ‘放管服’改革成效，为全市经济发展提供了更高效的审批、更优质的服务。加大简政放权力度。市行政审批局不断深化行政审批制度改革");
 
			driver.findElement(By.id("confirm_sub_t")).click();

		} else {
			WebSPUtil.login2(driver, email, ksd.getSp_password());
			WebSPUtil.clickItem(driver, itename);
			 


			WebUtil.df(driver,By.linkText("不同意")).click();
		 
			driver.findElement(
					By.xpath("//div[@class='question_container']/div/div/div"))
					.click();
	 
			WebUtil.df(driver,By.name("remark"))
					.sendKeys(
							"自2017年市行政审批局成立以来，我市持续加大简政放权力度。 截至目前， 行政审批许可事项由2017年的212项减少为117项，削减率近50%。2017年4月， 市行政审批局正式成立。一年来，该局以构建审批标准化为统领，以破解行政审批工作服务全局遇到的难点问题为导向，立足深化 ‘放管服’改革成效，为全市经济发展提供了更高效的审批、更优质的服务。加大简政放权力度。市行政审批局不断深化行政审批制度改革");
	 
			WebUtil.df(driver,By.id("disagree_sub")).click();// 确认
 
			WebUtil.df(driver,By.linkText("确定")).click();// 确认
			flag = true;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		WebUtil.logout(driver);
		return flag;
	}

	// 请款审批同意数据运营

	public static boolean testSP2(WebDriver driver, String email, String itename ,KSDCase ksd) {
		// String username = "huangsx@jizhicar.com";
		boolean flag = false;
		WebSPUtil.login2(driver, email,ksd.getSp_password());

		WebSPUtil.clickItem(driver, itename);
	 
 

		WebUtil.df(driver,By.linkText("不同意")).click();

 
		WebUtil.df(driver,By.name("remark"))
				.sendKeys(
						"自2017年市行政审批局成立以来，我市持续加大简政放权力度。 截至目前， 行政审批许可事项由2017年的212项减少为117项，削减率近50%。2017年4月， 市行政审批局正式成立。一年来，该局以构建审批标准化为统领，以破解行政审批工作服务全局遇到的难点问题为导向，立足深化 ‘放管服’改革成效，为全市经济发展提供了更高效的审批、更优质的服务。加大简政放权力度。市行政审批局不断深化行政审批制度改革"
								+ "自2017年市行政审批局成立以来，我市持续加大简政放权力度。 截至目前， 行政审批许可事项由2017年的212项减少为117项，削减率近50%。2017年4月， 市行政审批局正式成立。一年来，该局以构建审批标准化为统领，以破解行政审批工作服务全局遇到的难点问题为导向，立足深化 ‘放管服’改革成效，为全市经济发展提供了更高效的审批、更优质的服务。加大简政放权力度。市行政审批局不断深化行政审批制度改革");
 
		WebUtil.df(driver,By.id("sendBtn")).click();// 确认

		flag = true;
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
		return flag;
	}

	// 请款审批审核组长

	public static boolean testSP3(WebDriver driver, String email, String itename,KSDCase ksd) {
		// String username = "xiny@jizhicar.com";
		boolean flag = false;
		WebSPUtil.login2(driver, email, ksd.getSp_password());

		WebSPUtil.clickItem(driver, itename);
  
		WebUtil.df(driver,By.linkText("不同意")).click();
 
		WebUtil.df(driver,
				By.xpath("//div[@class='question_container']/div/div/div"))
				.click();
	 
		WebUtil.df(driver,By.id("remark"))
				.sendKeys(
						"自2017年市行政审批局成立以来，我市持续加大简政放权力度。 截至目前， 行政审批许可事项由2017年的212项减少为117项，削减率近50%。2017年4月， 市行政审批局正式成立。一年来，该局以构建审批标准化为统领，以破解行政审批工作服务全局遇到的难点问题为导向，立足深化 “放管服”改革成效，为全市经济发展提供了更高效的审批、更优质的服务。加大简政放权力度。市行政审批局不断深化行政审批制度改革");
		WebUtil.df(driver,By.id("disagree_sub")).click();// 确认
		flag = true;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.df(driver,By.linkText("确定")).click();//
		WebUtil.logout(driver);
		return flag;
	}

	// 财务专员 审批

	public static boolean testSP4(WebDriver driver, String email,
			String itename, KSDCase ksd) {
		// / String username = "sheny@jizhicar.com";
		boolean flag = false;
		WebSPUtil.login2(driver, email, ksd.getSp_password());
		WebUtil.df(driver, By.linkText("商户")).click();
		WebUtil.df(driver, By.linkText("放款管理")).click();
	//	WebSPUtil.clickItem(driver, itename);
		int height = driver.manage().window().getSize().height;
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height * 2 + 500) + ")"); // 向下滑动
		WebShop.clickShop(driver, ksd);

 
		Select userSelect = new Select(
				driver.findElement(By.id("orderby_type")));
		userSelect.selectByVisibleText("按贷款时间倒序排列");
 
		driver.findElement(By.linkText("筛选")).click();
		 

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height * 2 + 200) + ")"); // 向下滑动

		driver.findElement(By.linkText("不同意")).click();
 
		driver.findElement(By.name("remark"))
				.sendKeys(
						"自2017年市行政审批局成立以来，我市持续加大简政放权力度。 截至目前， 行政审批许可事项由2017年的212项减少为117项，削减率近50%。2017年4月， 市行政审批局正式成立。一年来，该局以构建审批标准化为统领，以破解行政审批工作服务全局遇到的难点问题为导向，立足深化 “放管服”改革成效，为全市经济发展提供了更高效的审批、更优质的服务。加大简政放权力度。市行政审批局不断深化行政审批制度改革");
 

		driver.findElement(By.linkText("确认")).click();

		driver.findElement(By.linkText("确定")).click();
		// driver.findElement(By.className("cancel")).click();// 稍后再说

		flag = true;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
		return flag;
	}

	// 财务专员 审批-已放款

	public static boolean testSP5(WebDriver driver, String email,
			String itename, KSDCase ksd) {
		// String username = "sheny@jizhicar.com";
		boolean flag = false;
		WebSPUtil.login2(driver, email, ksd.getSp_password());
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("回款管理")).click();
		driver.findElement(By.linkText("待回款")).click();
		WebSPUtil.clickItemorder(driver, ksd.getLoginname());
		int height = driver.manage().window().getSize().height;
		// System.out.println("height" + height);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height * 2 + 200) + ")"); // 向下滑动
	 
		/*
		 * driver.findElements(
		 * By.xpath("//div[@class='requestpayout_detail_btn_box']/a/div"))
		 * .get(0).click();// 确认回款
		 */// driver.findElement(By.xpath("//div[@class='requestpayout_detail_btn_box']/a/div")).click();//确认回款
		driver.findElement(By.linkText("确认回款")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// driver.findElement(By.linkText("确认回款")).click();//===
		driver.findElement(By.className("confirm")).click();
		flag = true;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
		return flag;
	}

	public static KSDCase testSP7(WebDriver driver, KSDCase ksd) {
		// String username = "liuhl@jizhicar.com";
		boolean flag = false;
	 
		WebSPUtil.login2(driver, ksd.getLoginemail(), ksd.getPwd());
		WebUtil.df(driver,By.linkText("客户")).click();

		WebUtil.df(driver,By.xpath("//ul[@class='slide_nav_bar']/li[6]/a"))
				.click();// 归档管理

		WebUtil.df(driver,By.id("start_loan_time")).click();
		WebUtil.df(driver,By.className("jedateok")).click();
		WebUtil.df(driver, By.linkText("筛选")).click();
		
		WebSPUtil.clickItemorder(driver, ksd.getLoginname());
		WebUtil.df(driver,By.linkText("不同意")).click();
	 
		WebUtil.df(driver,By.name("remark"))
				.sendKeys(
						"自2017年市行政审批局成立以来，我市持续加大简政放权力度。 截至目前， 行政审批许可事项由2017年的212项减少为117项，削减率近50%。2017年4月， 市行政审批局正式成立。一年来，该局以构建审批标准化为统领，以破解行政审批工作服务全局遇到的难点问题为导向，立足深化 “放管服”改革成效，为全市经济发展提供了更高效的审批、更优质的服务。加大简政放权力度。市行政审批局不断深化行政审批制度改革");// 归档确认
 
		WebUtil.df(driver,By.linkText("确认")).click();// 归档确认

		flag = true;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ksd;
	}

	public static KSDCase testDisAgreeQk(AndroidDriver<WebElement> driver,
			KSDCase ksd) {
		// driver.findElement(By.id("com.kuaishoudan.financer:id/text_name")).click();//
 
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/text_name"))
				.click();
	 
 
		int count = ksd.getImgtypes().size() - ksd.getImgcount();
	//	int count=5;
	//	System.out.println("count" + count);
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		AppUtil.swipeToUpQk(driver, 1000, count  );// 向上滑动ksd.getImgtypes().size()
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/tv_record"))
				.click();
 
		AppUtil.df(driver,
				By.id("com.kuaishoudan.financer:id/text_request_pay_name"))
				.click();
 
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/toolbar_submit"))
				.click();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		try {
			AppUtil.df(driver,
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AppUtil.df(driver,
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();
		}
	 

		AppUtil.goBack1(driver);
		return ksd;

	}

	// BD经理登录审批
	public static boolean loginBD(AndroidDriver<WebElement> driver,
			String username,KSDCase ksd) {
		boolean flag = false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
	 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String titletext = AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/toolbar_title"))
				.getText().trim();// 标题文本

		if ("贷款详情".equals(titletext)) {
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回
		}
		AppUtil.logout(driver);// 退出登录
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/edit_account"))
				.clear();
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/edit_password"))
				.clear();
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/edit_account"))
				.sendKeys(username);
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/edit_password"))
				.sendKeys(ksd.getSp_password());
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/btn_login"))
				.click();
	 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/toolbar_menu"))
				.click();// 菜单

		driver.findElements(
				By.id("com.kuaishoudan.financer:id/design_menu_item_text"))
				.get(7).click();// 消息
	 
		driver.findElements(By.id("com.kuaishoudan.financer:id/message_body"))
				.get(0).click();// 点进同意贷款详情
		 
		AppUtil.df(driver,
				By.id("com.kuaishoudan.financer:id/btn_check_disagree"))
				.click();// bu同意按钮
 
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/edit_reason"))
				.sendKeys(
						"自2017年市行政审批局成立以来，我市持续加大简政放权力度。 截至目前， 行政审批许可事项由2017年的212项减少为117项，削减率近50%。2017年4月， 市行政审批局正式成立。一年来，该局以构建审批标准化为统领，以破解行政审批工作服务全局遇到的难点问题为导向，立足深化 “放管服”改革成效，为全市经济发展提供了更高效的审批、更优质的服务。加大简政放权力度。市行政审批局不断深化行政审批制度改革");
 
		AppUtil.df(driver,By.id("com.kuaishoudan.financer:id/toolbar_finish"))
				.click();// 确定按钮
 
		AppUtil.df(driver,
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 提醒----确定按钮

		AppUtil.logout(driver);// 退出登录
		flag = true;
		return flag;

	}
}
