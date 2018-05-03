/**
 * 
 */
package pages;

import java.util.Scanner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import wdMethods.ProjectMethods;

/**
 * @author swarnalatha.valluru
 *
 */
public class HomePage extends ProjectMethods {
	public HomePage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	String SQuestion;

	@FindBy(how = How.XPATH, using = "//span[@class='icon icon-user-light']")
	public WebElement eleUserProfile;

	public HomePage clickUserProfile() {
		click(eleUserProfile);
		return this;
	}

	@FindBy(how = How.XPATH, using = "//p[text()='Settings']")
	public WebElement eleSettings;

	public HomePage clickSettings() {
		click(eleSettings);
		return this;
	}
	
	@FindBy(how = How.XPATH, using="//a[@id='edit-profile-link']")
	public WebElement eleEditProfile;

	public HomePage clickEditProfile() {
		click(eleEditProfile);
		return this;
	}
	
	@FindBy(how = How.ID, using="creds.password")
	public WebElement elePassword;

	public HomePage enterPassword(String data) {
		type(elePassword, data);
		return this;
	}

	@FindBy(how = How.ID, using="creds.button.submit")
	public WebElement eleVerify;

	public HomePage clickVerifyButton() {
		click(eleVerify);
		return this;
	}
	@FindBy(how = How.XPATH, using = "//input[@id='change_password.oldPassword']")
	public WebElement eleOldPassword;

	public HomePage enterOldPassword(String data) {
		driver.switchTo().frame("settings-frame");
		type(eleOldPassword, data);

		return this;
	}

	@FindBy(how = How.XPATH, using = "//input[@name='newPassword']")
	public WebElement eleNewPassword;

	public HomePage enterNewPassword(String data) {
		type(eleNewPassword, data);
		return this;
	}

	@FindBy(how = How.XPATH, using = "//input[@name='verifyPassword']")
	public WebElement eleConfirmPassword;

	public HomePage enterConfirmPassword(String data) {
		type(eleConfirmPassword, data);
		return this;
	}

	@FindBy(how = How.XPATH, using = "//input[@value='Change Password']")
	public WebElement eleChangePasswordbutton;

	public HomePage clickChangePasswordbutton() {
		click(eleChangePasswordbutton);
		return this;
	}

	@FindBy(how = How.XPATH, using = "(//ul[@class='bullets'])[2]")
	public WebElement elePasswordChangeMessage;

	public HomePage verifyPasswordChangeMessage() {
		String text = getText(elePasswordChangeMessage);
		System.out.println(text);
		return this;
	}

	@FindBy(how = How.ID, using = "forgot_pwd.edit_link")
	public WebElement eleForgotPasswordQuestionEdit;

	public HomePage clickPasswordQuestionEditButton() {
		driver.switchTo().frame("settings-frame");
		click(eleForgotPasswordQuestionEdit);
		return this;
	}

	@FindBy(how = How.XPATH, using = "//select[@id='forgot_pwd.securityQuestion']")
	public WebElement eleForgotPasswordQuestion;

	public HomePage selectPasswordQuestion(String data) {
		selectDropDownUsingText(eleForgotPasswordQuestion, data);
		SQuestion = data;
		return this;
	}

	@FindBy(how = How.XPATH, using = "//input[@id='forgot_pwd.securityAnswer']")
	public WebElement eleSecurityAnswer;

	public HomePage enterSecurityAnswer(String data) {
		if (data.length() < 4) {
			System.out.println(
					"The security question answer must be at least 4 characters in length. " + "Please enter answer");
			Scanner scan = new Scanner(System.in);
			data = scan.nextLine();
			scan.close();
		}
		type(eleSecurityAnswer, data);
		return this;
	}

	@FindBy(how = How.XPATH, using = "//input[@id='forgot_pwd.button.submit']")
	public WebElement eleForgotPasswordQuestionSave;

	public HomePage clickPasswordQuestionSaveButton() {
		click(eleForgotPasswordQuestionSave);
		return this;
	}

	@FindBy(how = How.XPATH, using = "//dd[@id='forgot_pwd.read_only.securityQuestion']")
	public WebElement elePasswordQuestion;

	public HomePage verifyPasswordQuestion() {
		if (SQuestion.equalsIgnoreCase(getText(elePasswordQuestion))) {
			System.out.println("Sequrity Question updated successfully");
		}

		else {
			readPasswordQuestionError();
		}
		return this;
	}

	@FindBy(how = How.ID, using = "forgot_pwd.securityAnswer.error")
	public WebElement eleForgotPasswordQuestionError;

	public void readPasswordQuestionError() {

		System.out.println("Error Message is:" + getText(eleForgotPasswordQuestionError));
	}

	@FindBy(how = How.LINK_TEXT, using = "Admin")
	public WebElement adminbutton;

	public AdminConsole clickAdminButton() {
		click(adminbutton);
		return new AdminConsole(driver, test);
	}

}
