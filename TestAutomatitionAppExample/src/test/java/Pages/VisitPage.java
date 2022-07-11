package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import helpers.Helpers;

public class VisitPage {
		
	private WebDriver driver;
	
	private By nameField;
	private By rutField;
	private By reasonField;
	private By submitButton;
//	private By cardText;
	
	public VisitPage( WebDriver driver) {
		this.driver = driver;
		this.nameField = By.xpath("//input[@formcontrolname = 'nombre']");
		this.rutField = By.xpath("//input[@formcontrolname = 'rut']");
		this.reasonField = By.xpath("//input[@formcontrolname = 'motivo']");
		this.submitButton = By.xpath("//button[@type='submit' and contains( string(), 'Guardar')]");
//		this.cardText = By.xpath("//div[contains(@class, 'card-body')]");
		
	}
	
	
	public int addVisit(String name, String rut, String reason ) {
		driver.findElement(nameField).sendKeys(name);
		driver.findElement(rutField).sendKeys(rut);
		driver.findElement(reasonField).sendKeys(reason);
		driver.findElement(submitButton).click();
		Helpers.SetExplicitWait(driver, By.className("swal2-modal"), 10);
		return driver.findElements(By.xpath("//div[contains(@class, 'card-body') and contains(string(),'"+name+"') and contains(string(),'" +rut +"')and contains(string(),'"+reason+"')]"))
				.size();
	}
	
	
	
}
