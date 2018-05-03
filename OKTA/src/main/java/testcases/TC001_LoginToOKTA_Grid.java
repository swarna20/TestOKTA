package testcases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import wdMethods.ProjectMethods;

public class TC001_LoginToOKTA_Grid extends ProjectMethods{
	
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
	public void Login(String network, String uname, String upassword, String answer) {
		network = "Inpremise" ;
	
	if(network.equalsIgnoreCase("Inpremise"))	{
		new HomePage(driver, test);
		}
	else {
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
	
	

}
