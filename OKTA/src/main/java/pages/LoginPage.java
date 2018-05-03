/**
 * 
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import wdMethods.ProjectMethods;

/**
 * @author swarnalatha.valluru
 *
 */
public class LoginPage extends ProjectMethods {

	public LoginPage(RemoteWebDriver driver1, ExtentTest test) {
		System.out.println(driver1);
		this.driver = driver1;
		System.out.println(driver.getCurrentUrl());
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Enter User name for OKTA Login
	 */

@FindBy(how = How.XPATH, using = "//input[@id='okta-signin-username']") 
	private WebElement eleUserName;

	public LoginPage enterUserName(String data) {
		System.out.println(driver.getCurrentUrl());
		
		type(eleUserName, data);
		return this;
	}

	/**
	 * Enter User Password for OKTA Login
	 */

	@FindBy(how = How.ID, using = "okta-signin-password")
	private WebElement eleUserPassword;

	public LoginPage enterUserPassword(String data) {
		type(eleUserPassword, data);
		return this;
	}

	/**
	 * Clcik on OKTA SignIn button
	 */

	@FindBy(how = How.ID, using = "okta-signin-submit")
	private WebElement eleSignIn;

	public MFAPage clickSignIn() {
		click(eleSignIn);
		return new MFAPage(driver, test);
	}

	
	public HomePage clickSignIn1() {
		click(eleSignIn);
		return new HomePage(driver, test);
	}

	@FindBy(how = How.LINK_TEXT, using = "Need help signing in?")
	private WebElement eleNeedHelpSignIn;

	public LoginPage clickNeedHelpSignInlink() {
		click(eleNeedHelpSignIn);
		return this;
	}
	
	@FindBy(how = How.LINK_TEXT, using = "Reset/Forgot password?")
	private WebElement eleReset_Forgotpassword  ;

	public ResetPasswordPage clickReset_ForgotpasswordLink() {
		click(eleReset_Forgotpassword);
		return new ResetPasswordPage(driver, test);
	}
	
	@FindBy(how = How.LINK_TEXT, using = "Unlock account?")
	private WebElement eleUnlockAccount  ;

	public UnlockAccountPage clickUnlockAccountLink() {
		click(eleUnlockAccount);
		return new UnlockAccountPage(driver, test);
	}

}
