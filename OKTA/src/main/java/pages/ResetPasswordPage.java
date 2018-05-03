package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import wdMethods.ProjectMethods;

public class ResetPasswordPage extends ProjectMethods{
	public ResetPasswordPage(RemoteWebDriver driver, ExtentTest test) {

		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//input[@name='username']")
	public WebElement eleUserName;

	public ResetPasswordPage enterUserName(String Uname) {
		type(eleUserName, Uname);
		return this;
	}
	
	
	@FindBy(how = How.LINK_TEXT, using = "Reset via Email")
	public WebElement ResetViaEmail;

	public ResetPasswordPage clickResetViaEmailButton() {
		click(ResetViaEmail);
		return this;
	}
	
	public void passwordResetViaEmail(String emailid, String emailPassword, String sanswer, String newPassword) throws InterruptedException {

		driver.get("http://gmail.com");
		Thread.sleep(50000);

		driver.findElementById("identifierId").sendKeys(emailid);
		driver.findElementByXPath("//span[text()='Next']").click();
		Thread.sleep(1000);

		driver.findElementByName("password").sendKeys(emailPassword);
		driver.findElementByXPath("//span[text()='Next']").click();
		Thread.sleep(1000);
		// driver.findElementByXPath("//div[@data-tooltip='Refresh']").click();
		for (int i = 1; i <= 25; i++) {
			String Path = "//table[@id=\":34\"]//tr[" + i + "]//td[6]//span//b";
			Thread.sleep(1000);
			WebElement Elemail = driver.findElementByXPath(Path);
			Thread.sleep(1000);
			String text = Elemail.getText();
			if (text.contains("Account password reset")) {
				click(Elemail);
				break;
			}
		}
		driver.findElementByXPath("//a[starts-with(@href, 'https://wileyqa.oktapreview.com')]").click();
		switchToWindow(1);

		driver.findElementByXPath("//input[@name='answer']").sendKeys(sanswer);
		driver.findElementByXPath(" //input[@value='Reset Password']").click();
		driver.findElementByXPath("//input[@name='newPassword']").sendKeys(newPassword);
		driver.findElementByXPath("//input[@name='confirmPassword']").sendKeys(newPassword);
		driver.findElementByXPath(" //input[@value='Reset Password']").click();
        String URL = driver.getCurrentUrl();
       
        if(URL.contains("UserHome")) {
        	System.out.println("Password reset successfully");
        }
        
        else {
        	System.out.println("Password reset successfully");
      
        }
		System.out.println(URL);

	}

}



