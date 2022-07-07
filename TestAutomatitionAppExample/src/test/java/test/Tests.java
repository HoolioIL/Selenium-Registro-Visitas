package test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
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
import helpers.ScreenShooter;
import helpers.WebDriverManager;

public class Tests {
	
	private WebDriver driver;
	ArrayList<String> tabs;
	private String baseUrl = "https://registrovisitas2022.herokuapp.com";
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension( 800, 600));
		//driver.manage().window().setPosition(new Point( 400,300));
		driver.navigate().to( baseUrl + "/auth/login");
		Helpers helper = new Helpers();
		helper.awaitResponse(3);
		
		//Open new Tab, why? idk
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		String openGoogle = "window.open('https://www.google.com/')";
		jsExecutor.executeScript( openGoogle );
		tabs = new ArrayList<String>(driver.getWindowHandles());
	}
	@Test
	public void loginUserInvalidData() {
		WebDriverManager.setWindowsSize(driver, "maximize");
		driver.switchTo().window(tabs.get(1)).navigate().to("https://hoolioil.github.io/fernanda-ibarra-fonoaudiologa-chimbarongo/");
		driver.switchTo().window(tabs.get(0));
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("mod@user.com", "1234567");
		Helpers helper = new Helpers();
		helper.awaitResponse(2);
		LogTests lt = new LogTests();
        lt.printInfo( "Invalid DATA:" + "probando si funciona con dos logs" );
		Assert.assertTrue(driver.findElement(By.className("card")).getText().contains("La combinación email/password no es correcta"));
		driver.quit();
	}
	@Test
	public void loginValid() {
		//WebDriverManager.setWindowsSize(driver, "fullscreen");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("mario@bros.jp", "123456789");
		DashboardPage dashboardPage = new DashboardPage(driver, baseUrl);
		dashboardPage.loadDashboard();
		driver.close();
	}
	
	
	@Test
	public void checkCardDashboardExists() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("mario@bros.jp", "123456789");
		DashboardPage dashboardPage = new DashboardPage(driver, baseUrl);
		dashboardPage.cardContents();
		driver.close();
	}	
	
	
	@Test
	public void registerIncident() {
		WebDriverManager.setWindowsSize(driver, 600, 800);
		LoginPage loginPage = new LoginPage(driver);
		IncidentsPage incidentPage = new IncidentsPage(driver);
		DashboardPage dashboardPage = new DashboardPage(driver, baseUrl);
		loginPage.login("mario@bros.jp", "123456789");
		dashboardPage.navigateToIncidents();
		incidentPage.selectCategory("Conflicto");
		driver.close();
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if ( !result.isSuccess()) {
			ScreenShooter.takeScreenshot("Error" , driver);
		}
		driver.quit();
		
	}
	

}
