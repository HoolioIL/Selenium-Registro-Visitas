package Pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import helpers.Helpers;

public class IncidentsPage {
	
	private WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//button[@type='submit']")
	private WebElement saveButtonElement;
	
	private By categoryDrop;
	private By commentsField;
	//private By saveButton;
	private By incidentTable;
	
	public IncidentsPage( WebDriver driver) {
		this.driver = driver;
//		categoryDrop = RelativeLocator.with(By.tagName("select"));
		categoryDrop = By.xpath("//*[@formcontrolname = 'categoria']");
		commentsField = By.xpath("/html/body/div/app-root/div/app-main/div/div/app-incidencias/div/div[1]/div/div/form/div[1]/div[2]/textarea");
		//saveButton = By.xpath("//button[@type='submit']");
		incidentTable = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]");
		
		PageFactory.initElements( driver, this );
	}
	
	
	public void selectCategory( String option ) {
		WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds(10) );
		wait.until(ExpectedConditions.presenceOfElementLocated(categoryDrop));
		Select selectCategory = new Select( driver.findElement(categoryDrop));
		selectCategory.selectByVisibleText(option);
		String message = "Este es un comentario desde selenium incidencia";
		driver.findElement(commentsField).sendKeys( message );
		
		//USING PAGE OBJECT
//		driver.findElement(saveButton).click();
		
		//USING PAGE FACTORY
		saveButtonElement.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-modal")));
		driver.findElement(incidentTable).getText().contains( message );
	}

}
