package test;

import org.testng.annotations.Test;

import Pages.DashboardPage;
import Pages.LoginPage;

public class DashboardTests extends CommonTests{
	
	@Test(description="Cards dashboard (vista inicio) cargan correctamente", enabled=true)
	public void checkCardDashboardExists() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("mario@bros.jp", "123456789");
		DashboardPage dashboardPage = new DashboardPage(driver, baseUrl);
		dashboardPage.cardContents();
		driver.close();
	}	

}
