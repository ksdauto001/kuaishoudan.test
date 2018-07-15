package com.kuaishoudan.financer.temp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/*import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.main.TestUser;
 
import com.kuaishoudan.financer.util.IdCardGenerator;

public class WebUtil2 {

	*//**
	 * @param args
	 *//*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String username = "liuhl@jizhicar.com";
		WebUtil2 wt = new WebUtil2();
		WebDriver driver = null;
		try {
			driver = wt.getDriver();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//wt.login(driver, username);
	}

	public static WebDriver getDriver() throws MalformedURLException {

		DesiredCapabilities desiredCapabilities = DesiredCapabilities.htmlUnit();
		//DesiredCapabilities desiredCapabilities = DesiredCapabilities.htmlUnitWithJs();
		desiredCapabilities.setCapability("loadImages",false);
		
		HtmlUnitDriver driver = new HtmlUnitDriver(desiredCapabilities);
		driver.get("http://test.kuaishoudan.cn");	
		
		System.out.println(driver.getTitle());
		 
		driver.close();
		driver.quit();
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		 * 
		 * 
		driver.manage().window().maximize();//
		String baseUrl = "";
		Properties properties = new Properties();
		try {
        	InputStreamReader in=new InputStreamReader(WebUtil.class.getResourceAsStream("ksd.properties"), "UTF-8");
        	properties.load(in);
        	baseUrl = properties.getProperty("webUrl");

            System.out.println("+++++++++++"+baseUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		return driver;

	}

	public static WebDriver getDriver2() throws MalformedURLException {

		System.setProperty("phantomjs.binary.path",System.getProperty("user.dir")+"\\phantomjs.exe");// set phantomjs exe path
		
		DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
		capabilities.setCapability("loadImages",false);
		capabilities.setCapability("takesScreenshot",false);
		
		capabilities.setCapability("phantomjs.page.settings.resourceTimeout", 120000);
		capabilities.setCapability("phantomjs.page.settings.loadImages", false);
		capabilities.setCapability("takesScreenshot", false);
		capabilities.setCapability("phantomjs.page.settings.disk-cache", true);
	//	capabilities.setCapability("phantomjs.page.settings.userAgent", "faking it");
		PhantomJSDriver driver = new PhantomJSDriver(capabilities);
		driver.get("http://test.kuaishoudan.cn");
 
		System.out.println(driver.getTitle());
		driver.close();
		driver.quit();

		return driver;
	}
}*/
