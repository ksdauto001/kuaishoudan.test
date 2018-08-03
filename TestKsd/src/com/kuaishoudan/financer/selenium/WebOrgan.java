package com.kuaishoudan.financer.selenium;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.util.RandomValue;

public class WebOrgan {

	/**
	 * 供应商机构管理
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			WebDriver driver = WebUtil.getDriver();
			KSDCase ksd = RandomValue.getRandom();
			ksd.setProduct("平安银行-那家店");// qita22-其他22产品1
			ksd.setCartype(1);
		/*	WebUtil.login(driver, ksd);// 登录
			List<Integer> list = getImge1(driver, ksd);
			WebUtil.logout(driver);*/
			WebUtil.login(driver, ksd);// 登录
			WebOrgan.getImge3(driver, ksd);//创建供应商归档图片
			WebUtil.logout(driver);
			Thread.sleep(25000);
			driver.quit();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void test1(WebDriver driver, KSDCase ksd)
			throws MalformedURLException, InterruptedException {
		Thread.sleep(200);
		WebUtil.df(driver, By.linkText("供应商")).click();
		String sss = ksd.getProduct().split("-")[0];

		driver.navigate().refresh();
		Thread.sleep(200);

		// List<WebElement> mask
		// =driver.findElements(By.className("org_mask"));//org_mask
		List<WebElement> pages = driver.findElements(By
				.xpath("//ul[@class='page_list']/li"));

		loop: for (int j = pages.size() - 2; j > 2; j--) {

			swipeTodown(driver);
			driver.findElement(
					By.xpath("//ul[@class='page_list']/li[" + j + "]")).click();
			List<WebElement> ws = driver.findElements(By.className("org_name"));

			for (int i = 0; i < ws.size(); i++) {

				// System.out.println("ws" + ws.get(i).getText());
				if (ws.get(i).getText().equals(sss)) {
				
					Actions action = new Actions(driver);

					action.moveToElement(ws.get(i)).perform();
					Thread.sleep(2000);

					List<WebElement> mask = driver.findElements(By
							.className("org_mask"));
					// System.out.println("@===" + mask.size());
					// mask.get(i).click();
		 
					mask.get(i).findElement(By.tagName("a")).click();
					break loop;
				}

			}
			Thread.sleep(500);

		}
		Thread.sleep(1000);

	}

	public static List<Integer> testJjzl(WebDriver driver, KSDCase ksd)
			throws InterruptedException {
		Thread.sleep(200);

		WebElement titos = driver.findElement(By
				.xpath("//ul[@class='tab_list inline_block']/li[2]"));// 进件资料
		titos.click();

		WebUtil.df(driver,By.id("editOrg_jump")).click();// 编辑
		// swipeTodown(driver);


		/*
		 * List<WebElement> checkalls=
		 * driver.findElements(By.className("check_all")) ;//全选
		 * checkalls.get(3).click();
		 */
		int type = driver.findElements(
				By.xpath("//ul[@class='tab_list inline_block']/li")).size();
		// System.out.println("size"+type);
		if (type == 2 && ksd.getCartype() == 1) {
			driver.findElement(
					By.xpath("//ul[@class='tab_list inline_block']/li[2]"))
					.click();
		}
		List<Integer> list1 = RandomValue.getImg1();
		List<WebElement> ws = driver.findElements(By.tagName("label"));
		for (int k = 0; k < ws.size(); k++) {
			String labels = ws.get(k).getAttribute("for");
			String classes = ws.get(k).getAttribute("class");
			if ((labels != null)) {
				if (classes.equals("checked")) {

					ws.get(k).click();
				}

			}
		}
		Thread.sleep(1000);
		for (int i = 0; i < 6; i++) {
			driver.findElements(By.className("must_nosend")).get(i).click();// 非必填
		}
		Thread.sleep(1000);
		for (Integer musttype : list1) {
			if (musttype < 9) {
				driver.findElements(By.className("must_send")).get(musttype)
						.click();// 必填
			}
		}

		// driver.findElements(By.className("check_all")).get(11).click();
		Thread.sleep(1000);
		for (int k = 0; k < ws.size(); k++) {
			String labels = ws.get(k).getAttribute("for");
			// System.out.println(labels);

			for (Integer imgtype : list1) {

				if ((labels != null) && labels.equals("" + imgtype)) {
					ws.get(k).click();// 选项

				}
			}
		}
		Thread.sleep(500);

		WebUtil.df(driver,By.linkText("保存")).click();// 保存
		Thread.sleep(500);
		WebUtil.df(driver,By.linkText("确定")).click();// 确定

		return list1;

	}

	public static List<Integer> testQkzl(WebDriver driver, KSDCase ksd)
			throws InterruptedException {
		Thread.sleep(500);

		WebElement titos = driver.findElement(By
				.xpath("//ul[@class='tab_list inline_block']/li[3]"));// 请款资料
		titos.click();

		driver.findElement(By.id("editOrg_jump")).click();// 编辑
		// swipeTodown(driver);


		/*
		 * List<WebElement> checkalls=
		 * driver.findElements(By.className("check_all")) ;//全选
		 * checkalls.get(3).click();
		 */
		int type = driver.findElements(
				By.xpath("//ul[@class='tab_list inline_block']/li")).size();
		// System.out.println("size"+type);
		if (type == 2 && ksd.getCartype() == 1) {
			driver.findElement(
					By.xpath("//ul[@class='tab_list inline_block']/li[2]"))
					.click();
		}
		List<Integer> list1 = RandomValue.getImg2();
		List<WebElement> ws = driver.findElements(By.tagName("label"));
		for (int k = 0; k < ws.size(); k++) {
			String labels = ws.get(k).getAttribute("for");
			String classes = ws.get(k).getAttribute("class");
			if ((labels != null)) {
				// System.out.println("=@@"+labels);
				if (classes.equals("checked")) {

					ws.get(k).click();
				}

			}
		}
		Thread.sleep(1000);
		for (int i = 0; i <= 6; i++) {
			driver.findElements(By.className("must_nosend")).get(i).click();// 非必填
		}
		Thread.sleep(1000);
		for (Integer musttype : list1) {
			if (musttype < 9) {
				driver.findElements(By.className("must_send")).get(musttype)
						.click();// 必填
			}
		}
		// driver.findElements(By.className("must_send")).get(1).click();//必填
		Thread.sleep(500);
		for (int k = 0; k < ws.size(); k++) {
			String labels = ws.get(k).getAttribute("for");
			// System.out.println(labels);

			for (Integer imgtype : list1) {

				if ((labels != null) && labels.equals("" + imgtype)) {
					ws.get(k).click();// 选项

				}
			}
		}
		WebUtil.df(driver,By.linkText("保存")).click();// 保存
		Thread.sleep(1000);
		WebUtil.df(driver,By.linkText("确定")).click();// 确定
		return list1;

	}

	public static List<Integer> testQdzl(WebDriver driver, KSDCase ksd)
			throws InterruptedException {

		Thread.sleep(500);
		WebElement titos = driver.findElement(By
				.xpath("//ul[@class='tab_list inline_block']/li[4]"));// 归档资料
		titos.click();
		Thread.sleep(200);
		driver.findElement(By.id("editOrg_jump")).click();// 编辑
		// swipeTodown(driver);

		/*
		 * List<WebElement> checkalls=
		 * driver.findElements(By.className("check_all")) ;//全选
		 * checkalls.get(3).click();
		 */
		int type = driver.findElements(
				By.xpath("//ul[@class='tab_list inline_block']/li")).size();
		// System.out.println("size"+type);
		if (type == 2 && ksd.getCartype() == 1) {
			driver.findElement(
					By.xpath("//ul[@class='tab_list inline_block']/li[2]"))
					.click();
		}
		List<Integer> list1 = RandomValue.getImg3();
		List<WebElement> ws = driver.findElements(By.tagName("label"));
		for (int k = 0; k < ws.size(); k++) {
			String labels = ws.get(k).getAttribute("for");
			String classes = ws.get(k).getAttribute("class");
			if ((labels != null)) {
				// System.out.println("=@@"+labels);
				if (classes.equals("checked")) {

					ws.get(k).click();
				}

			}
		}
		Thread.sleep(500);
		for (int i = 0; i < 6; i++) {
			driver.findElements(By.className("must_nosend")).get(i).click();// 非必填
		}
		Thread.sleep(500);
		for (Integer musttype : list1) {
			if (musttype < 9) {
				driver.findElements(By.className("must_send")).get(musttype)
						.click();// 必填
			}
		}
		Thread.sleep(500);
		for (int k = 0; k < ws.size(); k++) {
			String labels = ws.get(k).getAttribute("for");
			// System.out.println(labels);

			for (Integer imgtype : list1) {

				if ((labels != null) && labels.equals("" + imgtype)) {
					ws.get(k).click();// 选项

				}
			}
		}
		WebUtil.df(driver,By.linkText("保存")).click();// 保存
		Thread.sleep(1000);
		WebUtil.df(driver,By.linkText("确定")).click();// 确定

		return list1;
	}

	public static void swipeTodown(WebDriver driver) {
		try {
		Thread.sleep(300);
		((JavascriptExecutor) driver)
				.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // 向下滑动
	
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 进件资料-上传图片
	 */
	public static List<Integer> getImge1(WebDriver driver, KSDCase ksd) {
		List<Integer> list = null;
		try {

			test1(driver, ksd);
			list = testJjzl(driver, ksd);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 请款资料-上传图片
	 */
	public static List<Integer> getImge2(WebDriver driver, KSDCase ksd) {
		List<Integer> list = null;
		try {

			test1(driver, ksd);
			list = testQkzl(driver, ksd);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 归档资料-上传图片
	 */
	public static List<Integer> getImge3(WebDriver driver, KSDCase ksd) {
		List<Integer> list = null;
		try {

			test1(driver, ksd);
			list = testQdzl(driver, ksd);
			return list;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
