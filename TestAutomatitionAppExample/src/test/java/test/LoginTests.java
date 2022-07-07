package test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.DashboardPage;
import Pages.LoginPage;
import helpers.Helpers;
import helpers.LogTests;
import helpers.WebDriverManager;

public class LoginTests extends CommonTests{
	
	@Test(description="Ingreso de datos no válidos en formulario de login")
	public void loginUserInvalidData() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		String openGoogle = "window.open('')";
		jsExecutor.executeScript( openGoogle );
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		
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

	@Test(description="Login con credenciales válidas")
	public void loginValid() {
		//WebDriverManager.setWindowsSize(driver, "fullscreen");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("mario@bros.jp", "123456789");
		DashboardPage dashboardPage = new DashboardPage(driver, baseUrl);
		dashboardPage.loadDashboard();
		driver.close();
	}

}
