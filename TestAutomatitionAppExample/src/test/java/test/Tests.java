package test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.DashboardPage;
import Pages.IncidentsPage;
import Pages.LoginPage;
import helpers.Helpers;
import helpers.LogTests;

public class Tests {
	
	private WebDriver driver;
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.navigate().to("https://registrovisitas2022.herokuapp.com/auth/login");
		Helpers helper = new Helpers();
		helper.awaitResponse(3);
	}
//	@Test
//	public void loginUserInvalidData() {
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.login("mod@user.com", "1234567");
//		Helpers helper = new Helpers();
//		helper.awaitResponse(2);
//		LogTests lt = new LogTests();
//        lt.printInfo( "Invalid DATA:" + "probando si funciona con dos logs" );
//		Assert.assertTrue(driver.findElement(By.className("card")).getText().contains("La combinación email/password no es correcta"));
//		driver.close();
//	}//
//	@Test
//	public void loginValid() {
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.login("mario@bros.jp", "123456789");
//		DashboardPage dashboardPage = new DashboardPage(driver);
//		dashboardPage.loadDashboard();
//		driver.close();
//	}
	
	
//	@Test
//	public void checkCardDashboardExists() {
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.login("mario@bros.jp", "123456789");
//		DashboardPage dashboardPage = new DashboardPage(driver);
//		dashboardPage.cardContents();
//		driver.close();
//	}	
	
	
	@Test
	public void registerIncident() {
		LoginPage loginPage = new LoginPage(driver);
		IncidentsPage incidentPage = new IncidentsPage(driver);
		DashboardPage dashboardPage = new DashboardPage(driver);
		loginPage.login("mario@bros.jp", "123456789");
		dashboardPage.navigateToIncidents();
		incidentPage.selectCategory("Conflicto");
		driver.close();
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if ( !result.isSuccess()) {
			File screenshot = ( (TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File("src/test/java/screenshots/Error"+ System.currentTimeMillis() + ".png"));
			}catch ( IOException e) {
				e.printStackTrace();
			}
			driver.quit();
		}
		
	}
	

}
