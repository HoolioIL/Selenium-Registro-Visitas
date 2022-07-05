package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	
	private By userField;
	private By passwordField;
	private By loginButton;
	
	public LoginPage( WebDriver driver ) {
		this.driver 	= driver;
		userField 		= By.xpath("/html/body/div/app-root/div/app-main/div/app-login/div/div/div/div/form/div/div[1]/div/div/input");
		passwordField 	= By.xpath("/html/body/div/app-root/div/app-main/div/app-login/div/div/div/div/form/div/div[3]/div/div/input");
		loginButton 	= By.xpath("/html/body/div/app-root/div/app-main/div/app-login/div/div/div/div/form/div/div[5]/button");
	}
	
	
	public void login( String user, String pass) {
		driver.findElement(userField).sendKeys( user );
		driver.findElement(passwordField).sendKeys( pass );
		driver.findElement(loginButton).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
}
