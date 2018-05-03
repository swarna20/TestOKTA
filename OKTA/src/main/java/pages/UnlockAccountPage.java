package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;

import wdMethods.ProjectMethods;

public class UnlockAccountPage extends ProjectMethods {

	public UnlockAccountPage(RemoteWebDriver driver, ExtentTest test) {

		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@name='username']")
	public WebElement eleUserName;

	public UnlockAccountPage enterUserName(String Uname) {
		type(eleUserName, Uname);
		return this;
	}

	@FindBy(how = How.LINK_TEXT, using = "Send Email")
	public WebElement eleSendEmailButton;

	public UnlockAccountPage clickSendEmailButton() {
		click(eleSendEmailButton);

		return this;

	}

	public void unlockViaEmail(String emailid, String emailPassword, String sanswer) throws InterruptedException {

		driver.get("http://gmail.com");
		Thread.sleep(2000);

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
			if (text.contains("Unlock Account")) {
				click(Elemail);
				break;
			}
		}
		driver.findElementByXPath("//a[starts-with(@href, 'https://wileyqa.oktapreview.com')]").click();
		switchToWindow(1);

		driver.findElementByXPath("//input[@name='answer']").sendKeys(sanswer);
		driver.findElementByXPath(" //input[@value='Unlock Account']").click();
		String text = driver.findElementByXPath("//h2[contains(text(), 'unlocked')]").getText();
		System.out.println(text);

	}

}
