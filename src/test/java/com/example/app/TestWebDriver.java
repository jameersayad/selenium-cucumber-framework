package com.example.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestWebDriver {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.wikipedia.org/");
		Thread.sleep(1000*5);
		driver.close();
		driver.quit();
	}
}
