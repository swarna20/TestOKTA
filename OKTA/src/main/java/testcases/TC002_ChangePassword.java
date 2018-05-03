package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import wdMethods.ProjectMethods;

public class TC002_ChangePassword extends ProjectMethods{
	
	@BeforeClass
	public void setData() {
		testCaseName = "TC002_ChangePassword";
		testDescription = "Change Password (positive)";
		category="Regression";
		authors = "swarna";
		dataSheetName = "TC002_ChangePassword";
		browserName ="chrome";
	}
	
	@Test(dataProvider="fetchData")
	public void ChangePassword(String network, String uname, String upassword, String answer, String newPassword) {
		if(network.equalsIgnoreCase("Inpremise"))	{
			new HomePage(driver, test)
			.clickUserProfile()
			.clickSettings()
			.enterOldPassword(upassword)
			.enterNewPassword(newPassword)
			.enterConfirmPassword(newPassword)
			.clickChangePasswordbutton()
			.verifyPasswordChangeMessage();
		}
		else {
		new LoginPage(driver, test)
		.enterUserName(uname)
		.enterUserPassword(upassword)
		.clickSignIn()
		.clickMFAOptions()
		.selectSecurityquestionMFA()
		.enterSecurityAnswer(answer)
		.clickonVerifybutton()
		.clickUserProfile()
		.clickSettings()
		.enterOldPassword(upassword)
		.enterNewPassword(newPassword)
		.enterConfirmPassword(newPassword)
		.clickChangePasswordbutton()
		.verifyPasswordChangeMessage();
		
		}
		
	}
	
	

}
