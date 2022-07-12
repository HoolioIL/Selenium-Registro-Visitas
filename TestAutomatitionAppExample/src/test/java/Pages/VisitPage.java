package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import helpers.Helpers;

public class VisitPage {
		
	private WebDriver driver;
	
	private By nameField;
	private By rutField;
	private By reasonField;
	private By submitButton;
	private By showedCards;
	private By paginationButtons;
	
	public VisitPage( WebDriver driver) {
		this.driver = driver;
		this.nameField = By.xpath("//input[@formcontrolname = 'nombre']");
		this.rutField = By.xpath("//input[@formcontrolname = 'rut']");
		this.reasonField = By.xpath("//input[@formcontrolname = 'motivo']");
		this.submitButton = By.xpath("//button[@type='submit' and contains( string(), 'Guardar')]");
		this.showedCards = By.xpath("//div[contains( @class, \"card\")]/div[contains(@class,\"card-header\") and following-sibling::div[contains(@class, \"card-body\")]]");
		this.paginationButtons = By.xpath("//ul/li[contains(@class, \"page-item\")]");
		
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
	
	
	public boolean checkPagination() {
		int sizeCards = driver.findElements(showedCards).size();
		WebElement nextBtn = driver.findElement(By.xpath("//ul/li[contains(@class, \"page-item\")][last() -1]"));
		if ( sizeCards < 6) {
			if ( !nextBtn.getAttribute("class").contains("disabled")) {
				return false;
			}
		}
		else if ( sizeCards == 6 && (!nextBtn.getAttribute("class").contains("disabled") )) {
			List<WebElement> checkElementsPagination = driver.findElements(By.xpath("//div[@class=\"card\"]/div[@class=\"card-header\"]/h3"));
			String titleCard = checkElementsPagination.get( checkElementsPagination.size() - 1 ).getText();
			nextBtn.click();
			Helpers.SetExplicitWait(driver, showedCards, 10);
			if( driver.findElements(By.xpath("//h3[contains( string(), '" + titleCard + "')]")).size() != 0) {
				System.out.println("El último ingreso de la página anterior está presente en la siguiente (Información duplicada)");
				return false;
			}
		}
		return true;
	}
	
	
}
