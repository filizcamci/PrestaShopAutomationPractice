package com.prestashop.tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrestaShopAutomationPositive {
	String email;
	String firstName;
	String lastName;
	WebDriver driver;
	Faker faker=new Faker();
	@BeforeClass
	public void setUp() {
		System.out.println("setting up WebDriver before class");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
	}


	@Test
	public void register() throws InterruptedException {
		email=faker.name().firstName()+"@gmail.com";
		String sEmail=email;
		firstName=faker.name().firstName();
		String sFirstName=firstName;
		lastName=faker.name().lastName();
		String sLastName=lastName;
		String password="sdgnm";
		String address=faker.address().streetAddress();
		String city=faker.address().city();
		String zipcode="78723";
		driver.findElement(By.linkText("Sign in")).click();	
		driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(email+Keys.ENTER);
		driver.findElement(By.xpath("//input[@value='2']")).click();
		driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password);
		Select day=new Select(driver.findElement(By.xpath("//select[@id='days']")));
		day.selectByIndex(8);
		Select month=new Select(driver.findElement(By.xpath("//select[@id='months']")));
		month.selectByIndex(8);;
		Select year=new Select(driver.findElement(By.xpath("//select[@id='years']")));
		year.selectByIndex(12);
		driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(address);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		Select state=new Select(driver.findElement(By.xpath("//select[@id='id_state']")));
		state.selectByIndex(12);
		driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys(zipcode);
		driver.findElement(By.xpath("//input[@id='phone_mobile']")).sendKeys("234-344-56-78"+Keys.ENTER);
		//driver.findElement(By.xpath("//span[.='\"Register\"']")).click();
		//Thread.sleep(3000);
		driver.findElement(By.linkText("Sign out")).click();
		driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(sEmail);
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password+Keys.ENTER);
		//System.out.println(sFirstName+" "+sLastName);
		String fl=driver.findElement(By.xpath("//div[@class='header_user_info']")).getText();
		//System.out.println(fl);
		assertEquals(sFirstName+" "+sLastName,fl);
		
	}

	 @AfterClass
	 public void closing() {
	 driver.close();
	 }
}
