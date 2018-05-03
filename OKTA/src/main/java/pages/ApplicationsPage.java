package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import wdMethods.ProjectMethods;

public class ApplicationsPage extends ProjectMethods{
	public ApplicationsPage (RemoteWebDriver driver, ExtentTest test) {
		this.driver =driver;
		this.test=test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search']")
	public WebElement EnterApplicationName;
	public ApplicationsPage enterApplicationName() {
		type(EnterApplicationName, "Talent");
		return this;
	}
	
	@FindBy(how = How.XPATH, using = "//a[@class='app-instance-name']")
	public WebElement goToApplication;
	public ApplicationPage clickApplicationName() throws InterruptedException {
		

Thread.sleep(1000);
		click(goToApplication);
		
			
		return new ApplicationPage(driver, test);
	}
	
	
}
