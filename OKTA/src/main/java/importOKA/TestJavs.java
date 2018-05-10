package importOKA;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wdMethods.ProjectMethods;

public class TC006_DeleteFromLast extends ProjectMethods {

	@BeforeClass
	public void setData() {
		testCaseName = "TC001_LoginToOKTA2";
		testDescription = "Login (positive)";
		category = "smoke";
		authors = "swarna";
		dataSheetName = "TC001_LoginToOKTA2";
		browserName = "chrome";
	}

	@Test(dataProvider = "fetchData" )
	public void Login(String uname, String upassword, String answer) throws InterruptedException {
		new LoginPage(driver, test).enterUserName(uname).enterUserPassword(upassword).clickSignIn1()
		//.clickMFAOptions()
		// .selectSecurityquestionMFA()
		// .enterSecurityAnswer(answer)
		// .clickonVerifybutton()
		.clickAdminButton().clickAplications().enterApplicationName().clickApplicationName().clickImport()
		.confirmUser_Last();
				
		

	}

	

}
