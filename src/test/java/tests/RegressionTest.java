package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.Dashboardpage;
import pages.LoginPage;
import utils.TestBase;
import utils.TestData;

public class RegressionTest extends TestBase {
	
	@Test(enabled = false )
	@Parameters({"username","password"})
	public void ValidateUsertest(@Optional("admin@yourstore.com") String uname, @Optional("admin") String pass) {
		// login Application -loginPage
		LoginPage loginObj = new LoginPage(driver);
		loginObj.LoginAppliation(uname,pass);
		test.log(Status.INFO, "Login appliation");
		// validate user - DashboardPage
		Dashboardpage dashboardObj = new Dashboardpage(driver);
		Assert.assertEquals("John Smith", dashboardObj.getUserName());
		test.log(Status.PASS, "Expected name is John Smith and the actual name is "+dashboardObj.getUserName());
		// logout - DashboardPage
		dashboardObj.clickLogoutLink();
		test.log(Status.INFO, "Logout appliation");
	}
	

	@Test(dataProvider = "excelData", dataProviderClass = TestData.class)	
	public void ValidateLoginTest(String uname, String pass) {
		// login Application -loginPage
		LoginPage loginObj = new LoginPage(driver);
		loginObj.LoginAppliation(uname,pass);
		test.log(Status.INFO, "Login appliation");
		// validate user - DashboardPage
		Dashboardpage dashboardObj = new Dashboardpage(driver);
		Assert.assertEquals("John Smith", dashboardObj.getUserName());
		test.log(Status.PASS, "Expected name is John Smith and the actual name is "+dashboardObj.getUserName());
		// logout - DashboardPage
		dashboardObj.clickLogoutLink();
		test.log(Status.INFO, "Logout appliation");
	}

	
}
