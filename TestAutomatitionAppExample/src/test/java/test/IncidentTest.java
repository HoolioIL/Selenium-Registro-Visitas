package test;

import org.testng.annotations.Test;

import Pages.DashboardPage;
import Pages.IncidentsPage;
import Pages.LoginPage;
import helpers.WebDriverManager;

public class IncidentTest extends CommonTest {
	
	@Test(description="Registrar Incidente", enabled=true)
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


}
