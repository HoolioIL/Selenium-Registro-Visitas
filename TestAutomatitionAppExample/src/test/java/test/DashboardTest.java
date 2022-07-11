package test;

import org.testng.annotations.Test;

import Pages.DashboardPage;
import Pages.LoginPage;

@Test(groups = {"DashboardGroup"})
public class DashboardTest extends CommonTest{
	
	
	@Test(description="Cards dashboard (vista inicio) cargan correctamente")//groups = { "DashboardGroup" }
	public void checkCardDashboardExists() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("mario@bros.jp", "123456789");
		DashboardPage dashboardPage = new DashboardPage(driver, baseUrl);
		dashboardPage.cardContents();
		driver.close();
	}	
	
}
