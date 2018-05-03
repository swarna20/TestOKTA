package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import wdMethods.ProjectMethods;

public class TC003_ChangeForgotPasswordQuestion extends ProjectMethods {
	
	@BeforeClass
	public void setData() {
		testCaseName = "TC003_ChangeForgotPasswordQuestion";
		testDescription = "Change Forgot Password Question (positive)";
		category="Regression";
		authors = "swarna";
		dataSheetName = "TC003_ChangeForgotPasswordQuestion";
		browserName ="chrome";
	}
	
	@Test(dataProvider="fetchData")
	public void changeForgotPasswordQuestion(String uname, String upassword, String answer, 
			String Squestion, String Sanswer) {
		new LoginPage(driver, test)
		.enterUserName(uname)
		.enterUserPassword(upassword)
		.clickSignIn()
	//	.clickMFAOptions()
		//.selectSecurityquestionMFA()
		//.enterSecurityAnswer(answer)
	//	.clickonVerifybutton()
		.clickUserProfile()
		.clickSettings()
		.clickEditProfile()
		.enterPassword(upassword)
		.clickVerifyButton()
		.clickPasswordQuestionEditButton()
		.selectPasswordQuestion(Squestion)
		.enterSecurityAnswer(Sanswer)
		.clickPasswordQuestionSaveButton()
		.verifyPasswordQuestion();
		
		
	}
	
	

}
