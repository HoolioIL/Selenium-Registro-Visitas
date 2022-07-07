package test;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import helpers.Helpers;
import helpers.ScreenShooter;

public class CommonTests {
	protected WebDriver driver;
//	protected ArrayList<String> tabs;
	protected String baseUrl = "https://registrovisitas2022.herokuapp.com";
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		
//		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.addArguments("--headless");
		
		//SIN ENTORNO GRÁFICO ( SIN ABRIR EL BROWSER ) 
//		driver = new ChromeDriver( chromeOptions );
		
		//AL NO PASARLE NINGUN PARAMETRO TIENE EL COMPORTAMIENTO POR DEFECTO ( ABRE EL BROWSER ) 
		driver = new ChromeDriver( );
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension( 800, 600));
		//driver.manage().window().setPosition(new Point( 400,300));
		driver.navigate().to( baseUrl + "/auth/login");
		Helpers helper = new Helpers();
		helper.awaitResponse(3);
		
		//Open new Tab, why? idk
//		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
//		String openGoogle = "window.open('https://www.google.com/')";
//		jsExecutor.executeScript( openGoogle );
//		tabs = new ArrayList<String>(driver.getWindowHandles());
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		System.out.println("El test: " + result.getMethod().getDescription() + "resulto: " + result.getStatus() );
		if ( !result.isSuccess()) {
			ScreenShooter.takeScreenshot("Error" , driver);
		}
		driver.quit();
		
	}

}
