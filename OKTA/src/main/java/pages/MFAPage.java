package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import wdMethods.ProjectMethods;

public class MFAPage extends ProjectMethods {
	
	public MFAPage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how=How.XPATH, using=" //span[@class='icon-dm']")
    public WebElement eleMFAOptionsIcon;
	public MFAPage clickMFAOptions() {
		click(eleMFAOptionsIcon);
		return this;

	}
	
	@FindBy(how=How.XPATH, using="//span[@class='icon factor-icon mfa-question-30']")
    public WebElement eleSecurityQuetionMFA;
	public MFAPage selectSecurityquestionMFA() {
		click(eleSecurityQuetionMFA);
		return this;

	}
		
	@FindBy(how=How.XPATH, using="//input[@name='answer']")
	public WebElement elesecurityAnswer;
	
	public MFAPage enterSecurityAnswer(String data) {
		type(elesecurityAnswer, data);
		return this;
	}
	
	
	@FindBy(how=How.XPATH, using="//input[@value='Verify']")
	public WebElement eleVerifyButton;
	public HomePage clickonVerifybutton() {
		click(eleVerifyButton);
		return new HomePage(driver, test);
		
	}
	
	
}
