package com.shubham.selenium.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shubham.selenium.config.TestBase;
import com.shubham.selenium.config.TestNGConstant;

public class DemoTest extends TestBase {

	@DataProvider(name = "getData")
	public static Object[][] getData() throws InterruptedException {

		File file = new File(TestNGConstant.dataProvider);
		BufferedReader br;
		Object[][] testData = null;

		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			int lineNo = 0;
			while ((line = br.readLine()) != null) {
				lineNo++;
			}
			br.close();

			testData = new String[lineNo][5];
			br = new BufferedReader(new FileReader(file));
			int lineIndex = 0;
			while ((line = br.readLine()) != null) {

				String[] values = line.split("\\|");

				testData[lineIndex][0] = values[0];
				testData[lineIndex][1] = values[1];
				testData[lineIndex][2] = values[2];
				testData[lineIndex][3] = values[3];
				testData[lineIndex][4] = values[4];
				lineIndex++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testData;
	}

	@Test(dataProvider = "getData", testName = "testDemo")
	public void testDemo(String name, String id, String pin, String mobile, String otp)
			throws IOException, InterruptedException {
		driver.navigate().to(TestNGConstant.baseURL + "/registerForm.htm");
		Thread.sleep(2000);
		driver.findElement(By.id("optInBtn")).click();

		WebElement uname = driver.findElement(By.id("uname"));
		uname.sendKeys(name);

		WebElement uid = driver.findElement(By.id("uid"));
		uid.sendKeys(id);

		WebElement upin = driver.findElement(By.id("upin"));
		upin.sendKeys(pin);

		WebElement mobileNumber = driver.findElement(By.id("mobileNumber"));
		mobileNumber.sendKeys(mobile);

		List<WebElement> usex = driver.findElements(By.xpath("//*[@id='usex']//option"));
		for (int i = 0; i < usex.size(); i++) {
			String optionName = usex.get(i).getText();
			if (optionName.equals("Male")) {
				usex.get(i).click();

			}
		}

		List<WebElement> uCountry = driver.findElements(By.xpath("//*[@id='uCountry']//option"));
		for (int i = 0; i < uCountry.size(); i++) {
			String optionName = uCountry.get(i).getText();
			if (optionName.equals("USA")) {
				uCountry.get(i).click();

			}
		}

		driver.findElement(By.id("getOtpBttn")).click();
		Thread.sleep(5000);
		Alert alert = driver.switchTo().alert();
		alert.accept();

		Thread.sleep(5000);

		driver.findElement(By.id("uploadPhoto"))
				.sendKeys("D:\\workspace\\SeleniumTestNG\\src\\test\\java\\com\\shubham\\selenium\\test\\photo.jpg");

		WebElement uotp = driver.findElement(By.id("uotp"));
		uotp.sendKeys(otp);

		driver.findElement(By.id("submitbtn")).click();

		Assert.assertTrue(true);
	}

	@DataProvider(name = "getDataNegative")
	public static Object[][] getDataNegative() throws InterruptedException {

		File file = new File(TestNGConstant.dataProvider);
		BufferedReader br;
		Object[][] testData = null;

		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			int lineNo = 0;
			while ((line = br.readLine()) != null) {
				lineNo++;
			}
			br.close();

			testData = new String[lineNo][5];
			br = new BufferedReader(new FileReader(file));
			int lineIndex = 0;
			while ((line = br.readLine()) != null) {

				String[] values = line.split("\\|");

				testData[lineIndex][0] = values[0];
				testData[lineIndex][1] = values[1];
				testData[lineIndex][2] = values[2];
				testData[lineIndex][3] = values[3];
				testData[lineIndex][4] = values[4];
				lineIndex++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testData;
	}

	@Test(dataProvider = "getDataNegative", testName = "getDataNegative")
	public void testDemoNegative(String name, String id, String pin, String mobile, String otp)
			throws IOException, InterruptedException {
		driver.navigate().to(TestNGConstant.baseURL + "/registerForm.htm");
		Thread.sleep(2000);
		driver.findElement(By.id("optInBtn")).click();

		WebElement uname = driver.findElement(By.id("uname"));
		uname.sendKeys(name);

		WebElement uid = driver.findElement(By.id("uid"));
		uid.sendKeys(id);

		WebElement upin = driver.findElement(By.id("upin"));
		upin.sendKeys(pin);

		WebElement mobileNumber = driver.findElement(By.id("mobileNumber"));
		mobileNumber.sendKeys(mobile);

		List<WebElement> usex = driver.findElements(By.xpath("//*[@id='usex']//option"));
		for (int i = 0; i < usex.size(); i++) {
			String optionName = usex.get(i).getText();
			if (optionName.equals("Male")) {
				usex.get(i).click();

			}
		}

		List<WebElement> uCountry = driver.findElements(By.xpath("//*[@id='uCountry']//option"));
		for (int i = 0; i < uCountry.size(); i++) {
			String optionName = uCountry.get(i).getText();
			if (optionName.equals("USA")) {
				uCountry.get(i).click();

			}
		}

		driver.findElement(By.id("getOtpBttn")).click();

		Thread.sleep(5000);

		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();

		boolean status = (alertText.length()) > 0;

		Assert.assertTrue(status);
	}

	@BeforeMethod
	public void beforeTest() {
		initialization();
	}

	@AfterMethod
	public void after() {
		afterMethod();
	}

}
