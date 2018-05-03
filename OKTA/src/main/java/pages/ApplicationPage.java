package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


import com.relevantcodes.extentreports.ExtentTest;

import wdMethods.ProjectMethods;

public class ApplicationPage extends ProjectMethods {
	public ApplicationPage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.LINK_TEXT, using = "Import")
	public WebElement importOption;

	public ApplicationPage clickImport() {
		click(importOption);
		return this;
	}

	@FindBy(how = How.XPATH, using = "(//span[@class='more-options has-tool-tip'])[2]")
	public WebElement userIcon;

		public ApplicationPage clickUserIcon() {
		click(userIcon);
		return this;
	}

	public ApplicationPage confirmUser() throws InterruptedException {
		WebElement ele1 = driver.findElementByName("unassigned-users-table_length");
		selectDropDownUsingText(ele1, "100");
		for (int i = 2; i <= 100; i++) {
			//Thread.sleep(500);

			String userIconPath = "//table[@id='unassigned-users-table']//tr[" + i + "]//td[2]/div/div/h4";
			WebElement userType = driver.findElementByXPath(userIconPath);

			String ignoreUserPath = "//table[@id='unassigned-users-table']//tr[" + i
					+ "]//td[2]//div//div[2]//ul//li[5]//h4//span";
			WebElement ignore = driver.findElementByXPath(ignoreUserPath);

			String ignoreUserPath1 = "//table[@id='unassigned-users-table']//tr[" + i
					+ "]//td[2]//div//div[2]//ul//li[5]//h4//span";
			WebElement ignore1 = driver.findElementByXPath(ignoreUserPath1);
			
		 String userType1 = getText(userType);
			System.out.println(userType1);
			if (userType1.equalsIgnoreCase("NEW Okta user")) {
				// selectNewUserCheckbox();
				clickUserIcon();
				click(ignore1);
			}
			
			else {
				clickUserIcon();
				click(ignore);
			}
			System.out.println(i);
		}
		clickconfirmAssignment();
		clickconfirm();
		return this;

	}

	public ApplicationPage confirmUser_Last() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElementByXPath("//span[@id='unassigned-users-table_last']").click();
		Thread.sleep(1000);

		confirmUser();
		/*Thread.sleep(500);

		driver.findElementByXPath("//span[@id='unassigned-users-table_last']").click();
		WebElement ele1 = driver.findElementByName("unassigned-users-table_length");
		selectDropDownUsingText(ele1, "100");
		for (int i = 2; i <= 100; i++) {
			Thread.sleep(500);

			String userIconPath = "//table[@id='unassigned-users-table']//tr[" + i + "]//td[2]/div/div/h4";
			WebElement userType = driver.findElementByXPath(userIconPath);

			String ignoreUserPath = "//table[@id='unassigned-users-table']//tr[" + i
					+ "]//td[2]//div//div[2]//ul//li[5]//h4//span";
			WebElement ignore = driver.findElementByXPath(ignoreUserPath);

			String ignoreUserPath1 = "//table[@id='unassigned-users-table']//tr[" + i
					+ "]//td[2]//div//div[2]//ul//li[5]//h4//span";
			WebElement ignore1 = driver.findElementByXPath(ignoreUserPath1);
			
			String userIconPath1 = "(//span[@class='more-options has-tool-tip'])["+i+"]";
			WebElement userIcon = driver.findElementByXPath(userIconPath1);

			
			 * WebDriverWait mywebDriverWait = new WebDriverWait(driver, 50);
			 * mywebDriverWait.until(ExpectedConditions.elementToBeClickable(userType));
			  String userType1 = getText(userType);
			System.out.println(userType1);
			if (userType1.equalsIgnoreCase("NEW Okta user")) {
				// selectNewUserCheckbox();
				userIcon.click();
				click(ignore1);
			}
			
			 * else if(userType1.equalsIgnoreCase("EXACT Okta user match")) {
			 * clickUserIcon() ; click(ignore); }
			 
			else {
				userIcon.click();
				click(ignore);
			}
			System.out.println(i);
		}
		clickconfirmAssignment();
		clickconfirm();*/
		return this;

	}

	@FindBy(how = How.XPATH, using = "//table[@id='unassigned-users-table']//tr[2]//td[3]/input")
	public WebElement newUserCheckbox;

	public ApplicationPage selectNewUserCheckbox() {

		click(newUserCheckbox);

		return this;
	}

	@FindBy(how = How.XPATH, using = "//span[text()='Confirm Assignments']")
	public WebElement confirmAssignment;

	public ApplicationPage clickconfirmAssignment() {

		click(confirmAssignment);

		return this;
	}

	@FindBy(how = How.XPATH, using = "//input[@value='Confirm']")
	public WebElement confirm;

	public ApplicationPage clickconfirm() {

		click(confirm);

		return this;

	}
}
