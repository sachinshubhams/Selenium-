package com.shubham.selenium.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
	public WebDriver driver;

	public void initialization() {
		// WebDriverManager.chromedriver().version("83.0.4103.39").setup();
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless");
		// options.addArguments("start-maximized");
		/*
		 * options.addArguments("enable-automation");
		 * options.addArguments("--no-sandbox"); options.addArguments("--incognito");
		 * options.addArguments("--disable-infobars");
		 * options.addArguments("--disable-dev-shm-usage");
		 * options.addArguments("--disable-browser-side-navigation");
		 * options.addArguments("--disable-gpu");
		 */
		options.addArguments("--ignore-ssl-errors=yes");
		options.addArguments("--ignore-certificate-errors");
		System.setProperty(TestNGConstant.cromeDriver, TestNGConstant.cromeDriverPath);
		driver = new ChromeDriver(options);
		// driver.manage().window().maximize();
	}

	public void afterMethod() {
		driver.quit();
	}

}
