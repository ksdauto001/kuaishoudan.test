package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.bean.RequestPayout;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.util.IdCardGenerator;
import com.kuaishoudan.financer.util.RandomValue;

public class AppSPUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 申请合同**
	public static KSDCase testSQHT(AppiumDriver<AndroidElement> driver,
			KSDCase ksd) {
		String actualstatue = "";
		driver.manage().timeouts().implicitlyWait(115, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 首页列表
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * driver.findElements(By.id("com.kuaishoudan.financer:id/text_product"))
		 * .get(0).click();// 常规产品列表 }
		 */
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_apply_compact")).click();// 申请合同
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		int gxs = driver.findElements(
				By.id("com.kuaishoudan.financer:id/check_group")).size();// 勾选数
		System.out.println("gxs" + gxs);
		driver.findElements(By.id("com.kuaishoudan.financer:id/check_group"))
				.get(gxs - 1).click();// 不安装 选择GPS安装方式
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_remark"))
				.sendKeys("备注");
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		AppUtil.swipeToUp(driver, 1000);
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add"))
				.click();// 添加照片
		upload(driver);

		// getActstatue(driver);// 状态值
		String statue = getActstatue(driver);
		ksd.setStatue(statue);
		List<Integer> list2 = ksd.getImgtypes();
		if (ksd.getCartype() == 0) {
			list2.add(1049);
		} else {
			list2.add(1048);
		}
		list2.add(1050);
		list2.add(1051);
		list2.add(1052);
		ksd.setImgtypes(list2);
		return ksd;
	}

	// (申请合同)-申请请款
	public static KSDCase testHTSQQK(AppiumDriver<AndroidElement> driver,
			WebDriver webdriver, KSDCase ksd, String devicename) {
		String actualstatue = "";
		driver.manage().timeouts().implicitlyWait(115, TimeUnit.SECONDS);

		WebUtil.login(webdriver, ksd);// 登录
		List<Integer> list = WebOrgan.getImge2(webdriver, ksd);
		List<Integer> list2 = ksd.getImgtypes();

		WebUtil.logout(webdriver);
		int aa = 0, countImg = 0;
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < 9) {
				List<Integer> list3 = UserDaoImpl.getImgType(list.get(i) + 7,
						list);
				list2.addAll(list3);
				aa = list3.size();
				countImg = aa + countImg;
			}
		}

		if (countImg == 0) {
			for (Integer type : list) {
				if (type > 99) {
					list2.add(type);
					break;
				}
			}
		}
		ksd.setImgtypes(list2);
		System.out.println(list2.size() + "$$$" + countImg);
		ksd.setImgcount(countImg);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/btn_apply_request")).click();// 申请请款
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_request_pay_name"))
				.click();
		driver.findElement(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.click();// xia
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/ll_chekuan_shangpaidiya"))
				.click();// 上牌抵押地
		driver.findElement(By.id("com.kuaishoudan.financer:id/options3"))
				.click();// 城市
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		/*
		 * driver.swipe(width * 2 / 3, height - 80, width * 2 / 3, height - 280,
		 * 800);
		 */
		TouchAction action1 = new TouchAction(driver)
				.press(PointOption.point(width * 2 / 3, height - 80))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(width * 2 / 3, height - 280))
				.release();
		action1.perform();
		driver.findElement(By.id("com.kuaishoudan.financer:id/btnSubmit"))
				.click();// 城市确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang"))
				.click();// 上牌方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(ksd.getRegisttype() - 1).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang"))
				.click();// 抵押方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(ksd.getPledge() - 1).click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		try {

			driver.findElement(
					By.id("com.kuaishoudan.financer:id/tv_chekuan_kouchuxiang"))
					.sendKeys("" + ksd.getDeduction());// 扣除款项

			Thread.sleep(500);
			AppUtil.swipeToUp(driver, 1000);// 向上滑动
			Thread.sleep(500);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		AppUtil.uploadQk(driver, ksd.getImgcount());

		// RequestPayout requestPyout = ksd.getRequestpayout();
		/*
		 * try {
		 * 
		 * AppUtil.testFd(driver, devicename,requestPyout);
		 * 
		 * // AppUtil.testDy(driver,devicename, requestPyout);
		 * 
		 * // AppUtil.testZx(driver,devicename, requestPyout); } catch
		 * (InterruptedException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } catch (IOException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_toolbar_confirm"))
				.click();// 确定
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_confirm"))
				.click();// 申请请款确定

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_countdown"))
				.click();// 信息确认按钮com.kuaishoudan.financer:id/rl_countdown
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actualstatue = AppUtil.getStatue(driver);
		ksd.setStatue(actualstatue);

		return ksd;
	}

	// 不出合同申请请款
	public static KSDCase testBCSQQK(AppiumDriver<AndroidElement> driver,
			WebDriver webdriver, KSDCase ksd, String devicename) {
		driver.manage().timeouts().implicitlyWait(115, TimeUnit.SECONDS);
		int aa = 0, countImg = 0;
		/*
		 * WebUtil.login(webdriver, ksd );// 登录 List<Integer> list =
		 * WebOrgan.getImge2(webdriver, ksd); WebUtil.logout(webdriver);
		 * List<Integer> list2=ksd.getImgtypes();
		 * 
		 * Collections.sort(list); for(int i=0;i<list.size();i++){
		 * if(list.get(i)<9){ List<Integer>
		 * list3=UserDaoImpl.getImgType(list.get(i)+7,list);
		 * list2.addAll(list3); aa=list3.size(); countImg=aa+countImg; } }
		 * if(countImg==0){ for(Integer type:list){ if(type>99){
		 * list2.add(type);break; } } }
		 */
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
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_not_apply_compact"))
				.click();// 不出合同
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		int gxs = driver.findElements(
				By.id("com.kuaishoudan.financer:id/check_group")).size();// 勾选数
		System.out.println("gxs" + gxs);
		driver.findElements(By.id("com.kuaishoudan.financer:id/check_group"))
				.get(gxs - 1).click();// 不安装 选择GPS安装方式

		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_submit"))
				.click();// 提交
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 是按钮

		/*
		 * driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/btn_apply_request")).click();//
		 * 申请请款445整数进位
		 */
		driver.manage().timeouts().implicitlyWait(125, TimeUnit.SECONDS);
		int acounts = ksd.getSssh_account();
		int acount_c = acounts / 3;
		int acount_y = acounts % 3;
		if (acount_c == 0) {

		} else {
			try {
				Thread.sleep(2000);
				for (int j = 0; j < acount_c; j++)
					AppUtil.swipeToUp0(driver, 1000);// 向上滑动
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElements(
				By.id("com.kuaishoudan.financer:id/text_request_pay_name"))
				.get(acount_y).click();// dian账号名
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.click();// xia下标
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/et_chekuan_chejia"))
				.sendKeys(ksd.getVin());// 车架号

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		List<AndroidElement> ssds = driver.findElements(By
				.id("com.kuaishoudan.financer:id/text_content"));
		ssds.get(1).sendKeys("0.02");// 购置税
		ssds.get(2).sendKeys("0");// 保险费
		// ssds.get(2).sendKeys("0") ;// 保险费
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.findElement(
				By.id("com.kuaishoudan.financer:id/ll_chekuan_shangpaidiya"))
				.click();// 上牌抵押地
		driver.findElement(By.id("com.kuaishoudan.financer:id/options3"))
				.click();// 城市
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		/*
		 * driver.swipe(width * 2 / 3, height - 80, width * 2 / 3, height - 280,
		 * 800);
		 */
		TouchAction action1 = new TouchAction(driver)
				.press(PointOption.point(width * 2 / 3, height - 80))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(width * 2 / 3, height - 280))
				.release();
		action1.perform();
		driver.findElement(By.id("com.kuaishoudan.financer:id/btnSubmit"))
				.click();// 城市确定
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);

		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang"))
				.click();// 上牌方
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(ksd.getRegisttype() - 1).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang"))
				.click();// 抵押方
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(ksd.getPledge() - 1).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		/*
		 * driver.findElement(By.id(
		 * "com.kuaishoudan.financer:id/text_request_other_pay"
		 * )).click();//第三方收款 driver.manage().timeouts().implicitlyWait(15,
		 * TimeUnit.SECONDS);
		 * driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"
		 * )).get(1).click();//是 driver.manage().timeouts().implicitlyWait(15,
		 * TimeUnit.SECONDS);
		 */
		try {
			// driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_kouchuxiang")).sendKeys(""+ksd.getDeduction());//扣除款项
			Thread.sleep(500);
			AppUtil.swipeToUp(driver, 1000);// 向上滑动
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		AppUtil.uploadQk(driver, ksd.getImgcount());

		RequestPayout requestPyout = ksd.getRequestpayout();
		try {

			AppUtil.testFd(driver, devicename, requestPyout);
			// AppUtil.testDy(driver,devicename, requestPyout);
			// AppUtil.testZx(driver,devicename, requestPyout);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_toolbar_confirm"))
				.click();// 确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_confirm"))
				.click();// 申请请款确定

		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_countdown"))
				.click();// 倒计时确认

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
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		String actualstatue = getActstatue(driver);
		ksd.setStatue(actualstatue);
		return ksd;
	}

	/**
	 * 上传照片 申请合同
	 * 
	 * @param driver
	 * @return
	 */
	public static String upload(AppiumDriver<AndroidElement> driver) {
		String acstatue = "";
		try {
			/*
			 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 * driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add"))
			 * .click();// 上传照片
			 */
			Thread.sleep(500);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
					.click();// 从相册选择
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(0).click();// 添加图片（身份证）
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(1).click();// 添加图片（驾驶证）
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(1).click();// 添加图片（驾驶证）
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(2).click();// 添加图片（驾驶证）
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(2).click();// 添加图片（驾驶证）
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(3).click();// 添加图片（驾驶证）
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(3).click();// 添加图片（驾驶证）

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//

			Thread.sleep(2000);
			driver.findElement(By.id("com.kuaishoudan.financer:id/btn_ok"))
					.click();// 两种证上传——确定按钮
			AppUtil.swipeToUp(driver, 1000);// 向上滑动
			Thread.sleep(15000);
			driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_submit"))
					.click();// 上传完-提交按钮
			driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);
			Thread.sleep(1000);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();// 提醒确定是
			Thread.sleep(3000);
			// driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
			// driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
			// driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮

			/*
			 * acstatue = driver .findElement(
			 * By.id("com.kuaishoudan.financer:id/item_status"))
			 * .getText().trim();
			 */

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (org.openqa.selenium.WebDriverException e) {
			System.out.println(e);
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();// 是
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 从客户页面返回
		}

		try {

			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回按钮

		} catch (org.openqa.selenium.WebDriverException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return acstatue;
	}

	// BD经理登录审批
	public static boolean loginBD(AppiumDriver<AndroidElement> driver,
			String username, KSDCase ksd) {
		boolean flag = false;

		/*
		 * String titletext = driver
		 * .findElement(By.id("com.kuaishoudan.financer:id/toolbar_title"))
		 * .getText().trim();// 标题文本
		 * 
		 * if ("贷款详情".equals(titletext)) { driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回 }
		 */
		AppUtil.logout(driver);// 退出登录
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/edit_account"))
				.clear();
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/edit_password"))
				.clear();

		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/edit_account"))
				.sendKeys(username);
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/edit_password"))
				.sendKeys(ksd.getSp_password());// "!123456"

		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/btn_login"))
				.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/toolbar_menu"))
				.click();// 菜单

		driver.findElements(
				By.id("com.kuaishoudan.financer:id/design_menu_item_text"))
				.get(7).click();// 消息

		driver.findElements(By.id("com.kuaishoudan.financer:id/message_body"))
				.get(0).click();// 点进同意贷款详情

		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/btn_check_agree"))
				.click();// 同意按钮

		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/edit_reason"))
				.sendKeys("同意");

		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/toolbar_finish"))
				.click();// 确定按钮

		AppUtil.df(driver,
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 提醒----确定按钮

		AppUtil.logout(driver);// 退出登录
		flag = true;
		return flag;

	}

	public static Map<String, String> getSPname(
			AppiumDriver<AndroidElement> driver, KSDCase ksd)
			throws InterruptedException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		Thread.sleep(1500);
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		String spname = "";
		String title = driver.findElement(
				By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
		if ("查看状态".equals(title)) {
			AppUtil.swipeToDown(driver, 1000);// 向下滑动
			// System.out.println("下-----------" + title);

		} else {

			driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
					.get(0).click();// 首页列表

			driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
					.click();// 状态审批流程
			Thread.sleep(500);
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/text_customer_look_status"))
					.click();// 查看进度
			// AppUtil.swipeToDown(driver, 1000);// 向下滑动
		}
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		List<AndroidElement> statueitems = driver.findElements(By
				.id("com.kuaishoudan.financer:id/ll_status"));
		for (int i = 0; i < statueitems.size(); i++) {
			String statue = statueitems
					.get(i)
					.findElement(
							By.id("com.kuaishoudan.financer:id/item_status"))
					.getText();
			// System.out.println("@@" + statueitems.size());
			if ("正在处理".equals(statue)) {
				int fsize = statueitems.get(i)
						.findElements(By.className("android.widget.TextView"))
						.size();

				if (fsize == 1) {
					map.put("name", ksd.getLoginemail());
					map.put("prename", ksd.getLoginname());
					break;
				}
				String name = statueitems
						.get(i)
						.findElement(
								By.id("com.kuaishoudan.financer:id/item_name"))
						.getText();
				String[] strs = name.split("-");
				if (strs[0].contains("BD")) {
					// ///////////////////////////////

					spname = strs[1];
					System.out.println("BD经理处理" + spname);
					map.put("name", spname);
					break;
				} else {
					String prename = statueitems
							.get(i + 1)
							.findElement(
									By.id("com.kuaishoudan.financer:id/item_name"))
							.getText();
					String[] strspre = prename.split("-");
					spname = strs[1] + "," + strspre[1];
					// System.out.println("正在处理" + spname);
					map.put("name", strs[1]);
					map.put("prename", strspre[1]);
					break;
				}
			} else if ("放款审批/已放款".equals(statue)) {
				String name = statueitems
						.get(i)
						.findElement(
								By.id("com.kuaishoudan.financer:id/item_name"))
						.getText();
				String[] strs = name.split("-");
				spname = strs[1] + "," + ksd.getLoginname();// spname = strs[1]
															// + "," + "刘浩亮";
				map.put("name", strs[1]);
				map.put("prename", ksd.getLoginname());
			} else if ("回款结果/已回款".equals(statue)) {
				String name = statueitems
						.get(i)
						.findElement(
								By.id("com.kuaishoudan.financer:id/item_name"))
						.getText();
				String[] strs = name.split("-");
				spname = strs[1] + "," + ksd.getLoginname();
				map.put("name", strs[1]);
				map.put("prename", ksd.getLoginname());
			}
		}
		// return spname;
		return map;
	}

	// 状态实际值
	public static String getActstatue(AppiumDriver<AndroidElement> driver) {
		/*
		 * String titletext =AppUtil.df(driver,
		 * By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();// 标题文本
		 * 
		 * if ("贷款详情".equals(titletext)) { driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回 }
		 */
		/*
		 * try { Thread.sleep(200); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * AppUtil.swipeToDown(driver, 500);
		 */
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement actu1 = AppUtil.df(driver,
				By.id("com.kuaishoudan.financer:id/tv_lable"));
		String actu = actu1.getText();
		if (actu.equals("新店加急")) {

			actu = AppUtil
					.dfs(driver, By.id("com.kuaishoudan.financer:id/tv_lable"))
					.get(1).getText().trim();
		}
		return actu;

	}

	public static void sp6App(AppiumDriver<AndroidElement> driver, KSDCase ksd) {

		if (ksd.getCommit_type() == 2) {
			AppUtil.df(driver,
					By.id("com.kuaishoudan.financer:id/text_product")).click();// 常规产品列表
		} else {

			AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/text_name"))
					.click();// 首页列表
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/btn_archive"))
				.click();// 归档
		// driver.findElements(By.id("com.kuaishoudan.financer:id/check_group")).get(0).click();//当面交付
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/tv_select_type"))
				.click();// 材料类型

		driver.findElements(By.id("com.kuaishoudan.financer:id/tv_title"))
				.get(2).click();//
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/cb_all")).click();
		/*
		 * for(int i=0;i<ksd.getImgcount();i++){
		 * driver.findElements(By.id("com.kuaishoudan.financer:id/cb_check"
		 * )).get(i).click(); }
		 */
		//
		int countImg = 0;
		List<Integer> list2 = ksd.getImgtypes();
		List<Integer> list3 = UserDaoImpl.getOMaterial(ksd, 3);
		countImg = list3.size();

		list2.addAll(list3);

		List<Integer> list4 = UserDaoImpl.getOMaterial2(ksd, 3);
		list2.addAll(list4);

		// countImg=list3.size()+list4.size();
		countImg = 1;
		ksd.setImgcount(countImg);
		ksd.setImgtypes(list2);

		AppUtil.swipeToUp(driver, 1000);

		AppUtil.uploadQk(driver, ksd.getImgcount());
		new WebDriverWait(driver, 120).until(
				new ExpectedCondition<WebElement>() {
					@Override
					public WebElement apply(WebDriver d) {
						return d.findElement(By
								.id("com.kuaishoudan.financer:id/tv_toolbar_confirm"));

					}
				}).click(); // 提交

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回

	}

}
