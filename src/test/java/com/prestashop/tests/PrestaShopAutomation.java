package com.prestashop.tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class PrestaShopAutomation {
	WebDriver driver;
	

	@BeforeClass
	public void setUp() {
		System.out.println("setting up WebDriver before class");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
	}
//	@BeforeMethod
//	public void beforeTests() {
//		driver.get("http://automationpractice.com/index.php");
//	}
//	@AfterMethod
//	public void afterTests() {
//		driver.close();
//	}
	@Test
	public void wrongCredentials() {
		driver.findElement(By.linkText("Sign in")).click();		
		String email="abc@def.com";
		String password="abcdefg";
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password+Keys.ENTER);
		String message=driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		System.out.println(message);
		assertEquals(message,"Authentication failed.");
		
		
	}
	@Test
	public void invalidEmail() {
		driver.findElement(By.linkText("Sign in")).click();		
		String email="abcf";
		String password="xyz";
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password+Keys.ENTER);
		String message=driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		System.out.println(message);
		assertEquals(message,"Invalid email address.");
	}
	@Test
	public void blankEmail() {
		driver.findElement(By.linkText("Sign in")).click();		
		String password="xyz";
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password+Keys.ENTER);
		String message=driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		System.out.println(message);
		assertEquals(message,"An email address required.");
	}
	@Test
	public void blankPassword() {
		driver.findElement(By.linkText("Sign in")).click();		
		String email="abcf@gmail.com";
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email+Keys.ENTER);
		
		String message=driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		System.out.println(message);
		assertEquals(message,"Password is required.");
	}
	@AfterClass
	public void closing() {
		driver.close();
	}

}
