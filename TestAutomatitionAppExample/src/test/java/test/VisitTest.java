package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.VisitPage;
import helpers.Helpers;

public class VisitTest extends CommonTest{
	
	@Test(description = "Registrar visita exitosamente")
	public void registerVisit() {
		LoginPage lp = new LoginPage(driver);
		lp.login("mario@bros.jp", "123456789"); //falta optimizar
		DashboardPage dp = new DashboardPage(driver, baseUrl);
		dp.navigateToVisits();
		VisitPage visitPage = new VisitPage( driver );
		Helpers helper = new Helpers();
		String name = helper.generateRandomString(7);
		String rut = helper.generateRandomNumber(9);
		String reason = helper.generateRandomString(12);
		int sizeRegister = visitPage.addVisit(name, rut, reason);
		Assert.assertEquals(sizeRegister, 1);
	}
	

}
