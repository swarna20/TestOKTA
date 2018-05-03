package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wdMethods.ProjectMethods;

public class TC001_LoginToOKTA extends ProjectMethods{
	
	@BeforeClass
	public void setData() {
		testCaseName = "TC001_LoginToOKTA";
		testDescription = "Login (positive)";
		category="smoke";
		authors = "swarna";
		dataSheetName = "TC001_Login";
		browserName ="chrome";
	}
	
	@Test(dataProvider="fetchData")
	public void Login(String uname, String upassword, String answer) {
		new LoginPage(driver, test)
		.enterUserName(uname)
		.enterUserPassword(upassword)
		.clickSignIn()
		.clickMFAOptions()
		.selectSecurityquestionMFA()
	.enterSecurityAnswer(answer)
	.clickonVerifybutton();
		
	}
	
	

}
