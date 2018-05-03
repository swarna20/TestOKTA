package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wdMethods.ProjectMethods;

public class TC005_ForgotPassword extends ProjectMethods {
	

	@BeforeClass
	public void setData() {
		testCaseName = "TC005_ForgotPassword";
		testDescription = "ForgotPassword (positive)";
		category="Regression";
		authors = "swarna";
		dataSheetName = "TC005_ForgotPassword";
		browserName ="chrome";
	}
	
	@Test(dataProvider="fetchData")
	public void ForgotPassword(String uname, String emailid,String emailPassword, String sanswer, String newPassword) throws InterruptedException  {
		new LoginPage(driver, test)
		.clickNeedHelpSignInlink()
		.clickReset_ForgotpasswordLink()
		.enterUserName(uname)
		.clickResetViaEmailButton()
		.passwordResetViaEmail(emailid, emailPassword,  sanswer, newPassword);
	}
}
