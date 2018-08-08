package com.kuaishoudan.financer.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.events.api.general.ElementEventListener;

public class ElementListener implements
ElementEventListener {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

		System.out.println("控件:" + splitElement(arg0) + "数值已改变");
	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1,
			CharSequence[] arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

		System.out.println("点击:" +splitElement(arg0));

	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

		System.out.println("准备改变控件:" +splitElement(arg0)+"数值");

	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1,
			CharSequence[] arg2) {
		// TODO Auto-generated method stub
		System.out.println("控件:" + splitElement(arg0) + "数值已改变");

	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		System.out.println("准备点击:"+splitElement(arg0));

	}
	 //获取操作的控件字符串
	  private String splitElement(WebElement element) {  
	        String str = element.toString().split("-> ")[1];  
	        return str.substring(0, str.length() - 1);  
	    }  


}
