package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DashboardPage {
	
	private WebDriver driver;
	private By cardResidents;
	private By cardUnities;
	private By cardIncidents;
	private By cardVisits;
	private By incidentsButton;
	private String url;
		
	public DashboardPage( WebDriver driver, String url) {
		this.driver = driver;
		cardResidents = By.xpath("/html/body/div/app-root/div/app-main/div/div/app-dashboard/section[1]/div/div/div[1]/div");
		cardUnities	  = By.xpath("/html/body/div/app-root/div/app-main/div/div/app-dashboard/section[1]/div/div/div[2]/div");
		cardIncidents = By.xpath("/html/body/div/app-root/div/app-main/div/div/app-dashboard/section[1]/div/div/div[3]/div");
		cardVisits 	  = By.xpath("/html/body/div/app-root/div/app-main/div/div/app-dashboard/section[1]/div/div/div[4]/div");
		incidentsButton = By.xpath("/html/body/div/app-root/div/app-main/div/div/app-dashboard/section[1]/div/div/div[3]/div/a");
		this.url = url + "/dashboard";
		
	}
	
	public void cardContents() {
		if ( driver.findElements(cardResidents ).size() == 0 ) {
			throw new AssertionError("card residents not found");
		}
		if ( driver.findElements(cardUnities ).size() == 0 ) {
			throw new AssertionError("card unities not found");
		}
		if ( driver.findElements(cardIncidents ).size() == 0 ) {
			throw new AssertionError("card incident not found");
		}
		if ( driver.findElements(cardVisits ).size() == 0 ) {
			throw new AssertionError("card visits not found");
		}
	}
	
	
	public void loadDashboard() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(cardResidents));
		Assert.assertEquals( url, driver.getCurrentUrl() );
	}
	
	public void navigateToIncidents() {
		driver.findElement(incidentsButton).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
}
