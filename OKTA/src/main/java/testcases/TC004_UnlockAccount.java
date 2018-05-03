package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wdMethods.ProjectMethods;

public class TC004_UnlockAccount extends ProjectMethods {
	

	@BeforeClass
	public void setData() {
		testCaseName = "TC004_UnlockAccount";
		testDescription = "Unlock Account (positive)";
		category="Regression";
		authors = "swarna";
		dataSheetName = "TC004_UnlockAccount";
		browserName ="chrome";
	}
	
	@Test(dataProvider="fetchData")
	public void UnlockAccount(String uname, String emailid,String emailPassword, String sanswer) throws InterruptedException {
		new LoginPage(driver, test)
		.clickNeedHelpSignInlink()
		.clickUnlockAccountLink()
		.enterUserName(uname)
		.clickSendEmailButton()
	    .unlockViaEmail(emailid, emailPassword, sanswer);
	}
}
