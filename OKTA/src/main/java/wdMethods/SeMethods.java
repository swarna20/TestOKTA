/*
 * 
 */
package wdMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Reporter;

// TODO: Auto-generated Javadoc
/**
 * The Class SeMethods.
 */
public class SeMethods extends Reporter implements WdMethods{

	/** The driver. */
	public RemoteWebDriver driver;
	
	/** The s hub port. */
	public String sUrl,sHubUrl,sHubPort;
	
	/** The prop. */
	public Properties prop;
	
	/**
	 * Instantiates a new se methods.
	 */
	public SeMethods() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./resources/config.properties")));
			sHubUrl = prop.getProperty("HUB");
			sHubPort = prop.getProperty("PORT");
			sUrl = prop.getProperty("URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#startApp(java.lang.String, boolean)
	 */
	public void startApp(String browser, boolean bRemote) {
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WINDOWS);
			// this is for grid run
			if(bRemote)
				try {
					driver = new RemoteWebDriver(new URL("http://"+sHubUrl+":"+sHubPort+"/wd/hub"), dc);
					
				} catch (MalformedURLException e) {
				}
			else{ // this is for local run
				if(browser.equalsIgnoreCase("chrome")){
					System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
					driver = new ChromeDriver();
				}else {
					System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
					driver = new FirefoxDriver();
				}
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(sUrl);
			reportStep("The browser: "+browser+" launched successfully", "PASS");
		} catch (WebDriverException e) {			
			reportStep("The browser: "+browser+" could not be launched", "FAIL");
		}
	}

	/**
	 * Start app.
	 *
	 * @param browser the browser
	 */
	public void startApp(String browser) {
		startApp(browser, false);
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#locateElement(java.lang.String, java.lang.String)
	 */
	public WebElement locateElement(String locator, String locValue) {
		try {
			switch(locator) {
			case("id"): return driver.findElementById(locValue);
			case("link"): return driver.findElementByLinkText(locValue);
			case("xpath"):return driver.findElementByXPath(locValue);
			case("name"): return driver.findElementByName(locValue);
			case("class"): return driver.findElementByClassName(locValue);
			case("tag"):return driver.findElementByTagName(locValue);
			}
		} catch (NoSuchElementException e) {
			reportStep("The element with locator "+locator+" not found.","FAIL");
			throw new RuntimeException();
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while finding "+locator+" with the value "+locValue, "FAIL");
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#locateElement(java.lang.String)
	 */
	public WebElement locateElement(String locValue) {
		return driver.findElementById(locValue);
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#type(org.openqa.selenium.WebElement, java.lang.String)
	 */
	public void type(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			reportStep("The data: "+data+" entered successfully in the field :"+ele, "PASS");
		} catch (InvalidElementStateException e) {
			reportStep("The data: "+data+" could not be entered in the field :"+ele,"FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while entering "+data+" in the field :"+ele, "FAIL");
		}
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#click(org.openqa.selenium.WebElement)
	 */
	public void click(WebElement ele) {
		String text = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));			
			text = ele.getText();
			ele.click();
			reportStep("The element "+text+" is clicked", "PASS");
		} catch (InvalidElementStateException e) {
			reportStep("The element: "+text+" could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while clicking in the field :", "FAIL");
			e.printStackTrace();
		 
	    } catch (RuntimeException e) {
              e.printStackTrace();
	    } 
	}

	/**
	 * Click with no snap.
	 *
	 * @param ele the ele
	 */
	public void clickWithNoSnap(WebElement ele) {
		String text = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));	
			text = ele.getText();
			ele.click();			
			reportStep("The element :"+text+"  is clicked.", "PASS",false);
		} catch (InvalidElementStateException e) {
			reportStep("The element: "+text+" could not be clicked", "FAIL",false);
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while clicking in the field :","FAIL",false);
		} 
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#getText(org.openqa.selenium.WebElement)
	 */
	public String getText(WebElement ele) {	
		String bReturn = "";
		try {
			bReturn = ele.getText();
		} catch (WebDriverException e) {
			reportStep("The element: "+ele+" could not be found.", "FAIL");
		}
		return bReturn;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {		
		String bReturn = "";
		try {
			bReturn =  driver.getTitle();
		} catch (WebDriverException e) {
			reportStep("Unknown Exception Occured While fetching Title", "FAIL");
		} 
		return bReturn;
	}

	/**
	 * Gets the attribute.
	 *
	 * @param ele the ele
	 * @param attribute the attribute
	 * @return the attribute
	 */
	public String getAttribute(WebElement ele, String attribute) {		
		String bReturn = "";
		try {
			bReturn=  ele.getAttribute(attribute);
		} catch (WebDriverException e) {
			reportStep("The element: "+ele+" could not be found.", "FAIL");
		} 
		return bReturn;
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#selectDropDownUsingText(org.openqa.selenium.WebElement, java.lang.String)
	 */
	public void selectDropDownUsingText(WebElement ele, String value) {
		try {
			new Select(ele).selectByVisibleText(value);
			reportStep("The dropdown is selected with text "+value,"PASS");
		} catch (WebDriverException e) {
			reportStep("The element: "+ele+" could not be found.", "FAIL");
		}

	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#selectDropDownUsingIndex(org.openqa.selenium.WebElement, int)
	 */
	public void selectDropDownUsingIndex(WebElement ele, int index) {
		try {
			new Select(ele).selectByIndex(index);
			reportStep("The dropdown is selected with index "+index,"PASS");
		} catch (WebDriverException e) {
			reportStep("The element: "+ele+" could not be found.", "FAIL");
		} 

	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#verifyTitle(java.lang.String)
	 */
	public boolean verifyTitle(String title) {
		boolean bReturn =false;
		try {
			if(getTitle().equals(title)) {
				reportStep("The title of the page matches with the value :"+title,"PASS");
				bReturn= true;
			}else {
				reportStep("The title of the page:"+driver.getTitle()+" did not match with the value :"+title, "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		} 
		return bReturn;
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#verifyExactText(org.openqa.selenium.WebElement, java.lang.String)
	 */
	public void verifyExactText(WebElement ele, String expectedText) {
		try {
			if(getText(ele).equals(expectedText)) {
				reportStep("The text: "+getText(ele)+" matches with the value :"+expectedText,"PASS");
			}else {
				reportStep("The text "+getText(ele)+" doesn't matches the actual "+expectedText,"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Text", "FAIL");
		} 

	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#verifyPartialText(org.openqa.selenium.WebElement, java.lang.String)
	 */
	public void verifyPartialText(WebElement ele, String expectedText) {
		try {
			if(getText(ele).contains(expectedText)) {
				reportStep("The expected text contains the actual "+expectedText,"PASS");
			}else {
				reportStep("The expected text doesn't contain the actual "+expectedText,"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Text", "FAIL");
		} 
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#verifyExactAttribute(org.openqa.selenium.WebElement, java.lang.String, java.lang.String)
	 */
	public void verifyExactAttribute(WebElement ele, String attribute, String value) {
		try {
			if(getAttribute(ele, attribute).equals(value)) {
				reportStep("The expected attribute :"+attribute+" value matches the actual "+value,"PASS");
			}else {
				reportStep("The expected attribute :"+attribute+" value does not matches the actual "+value,"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Attribute Text", "FAIL");
		} 

	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#verifyPartialAttribute(org.openqa.selenium.WebElement, java.lang.String, java.lang.String)
	 */
	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		try {
			if(getAttribute(ele, attribute).contains(value)) {
				reportStep("The expected attribute :"+attribute+" value contains the actual "+value,"PASS");
			}else {
				reportStep("The expected attribute :"+attribute+" value does not contains the actual "+value,"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Attribute Text", "FAIL");
		}
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#verifySelected(org.openqa.selenium.WebElement)
	 */
	public void verifySelected(WebElement ele) {
		try {
			if(ele.isSelected()) {
				reportStep("The element "+ele+" is selected","PASS");
			} else {
				reportStep("The element "+ele+" is not selected","FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "FAIL");
		}
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#verifyDisplayed(org.openqa.selenium.WebElement)
	 */
	public void verifyDisplayed(WebElement ele) {
		try {
			if(ele.isDisplayed()) {
				reportStep("The element "+ele+" is visible","PASS");
			} else {
				reportStep("The element "+ele+" is not visible","FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "FAIL");
		} 
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#switchToWindow(int)
	 */
	public void switchToWindow(int index) {
		try {
			Set<String> allWindowHandles = driver.getWindowHandles();
			List<String> allHandles = new ArrayList<>();
			allHandles.addAll(allWindowHandles);
			driver.switchTo().window(allHandles.get(index));
		} catch (NoSuchWindowException e) {
			reportStep("The driver could not move to the given window by index "+index,"PASS");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "FAIL");
		}
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#switchToFrame(org.openqa.selenium.WebElement)
	 */
	public void switchToFrame(WebElement ele) {
		try {
			driver.switchTo().frame(ele);
			reportStep("switch In to the Frame "+ele,"PASS");
		} catch (NoSuchFrameException e) {
			reportStep("WebDriverException : "+e.getMessage(), "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "FAIL");
		} 
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#acceptAlert()
	 */
	public void acceptAlert() {
		String text = "";	
				
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.accept();
			reportStep("The alert "+text+" is accepted.","PASS");
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.","FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "FAIL");
		}  
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#dismissAlert()
	 */
	public void dismissAlert() {
		String text = "";		
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			alert.dismiss();
			reportStep("The alert "+text+" is dismissed.","PASS");
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.","FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "FAIL");
		} 

	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#getAlertText()
	 */
	public String getAlertText() {
		String text = "";		
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.","FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "FAIL");
		} 
		return text;
	}

	/* (non-Javadoc)
	 * @see utils.Reporter#takeSnap()
	 */
	public long takeSnap(){
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#closeBrowser()
	 */
	public void closeBrowser() {
		try {
			driver.close();
			reportStep("The browser is closed","PASS");
		} catch (Exception e) {
			reportStep("The browser could not be closed","FAIL");
		}
	}

	/* (non-Javadoc)
	 * @see wdMethods.WdMethods#closeAllBrowsers()
	 */
	public void closeAllBrowsers() {
		try {
			driver.quit();
			reportStep("The opened browsers are closed","PASS");
		} catch (Exception e) {
			reportStep("Unexpected error occured in Browser","FAIL");
		}
	}




}
