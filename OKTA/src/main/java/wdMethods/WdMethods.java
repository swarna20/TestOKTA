/*
 * 
 */
package wdMethods;

import java.net.MalformedURLException;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

// TODO: Auto-generated Javadoc
/**
 * The Interface WdMethods.
 */
public interface WdMethods {
		
	
		/**
		 * This method will launch the chrome browser and 
		 * maximise the browser and set the wait for 30 seconds 
		 * and load the url.
		 *
		 * @author Babu - TestLeaf
		 * @param browser - This will load the specified browser
		 * @param bReturns the b returns
		 * @throws MalformedURLException the malformed URL exception
		 */
		public void startApp(String browser, boolean bReturns) throws MalformedURLException ;

		/**
		 * This method will locate the element using any given locator.
		 *
		 * @author Babu - TestLeaf
		 * @param locator  - The locator by which the element to be found
		 * @param locValue - The locator value by which the element to be found
		 * @return the web element
		 * @throws NoSuchElementException the no such element exception
		 */
		public WebElement locateElement(String locator, String locValue) ;	
		
		/**
		 * This method will locate the element using id.
		 *
		 * @author Babu - TestLeaf
		 * @param locValue - The locator value by which the element to be found
		 * @return the web element
		 * @throws NoSuchElementException the no such element exception
		 */
		public WebElement locateElement(String locValue) ;	
		
		/**
		 * This method will enter the value in the given text field .
		 *
		 * @author Babu - TestLeaf
		 * @param ele   - The Webelement (text field) in which the data to be entered
		 * @param data  - The data to be sent to the webelement
		 * @throws ElementNotVisibleException 	 *
		 */
		public void type(WebElement ele, String data) ;
		
		/**
		 * This method will click the element and take snap.
		 *
		 * @author Babu - TestLeaf
		 * @param ele   - The Webelement (button/link/element) to be clicked
		 */
		public void click(WebElement ele);

		/**
		 * This method will get the text of the element.
		 *
		 * @author Babu - TestLeaf
		 * @param ele   - The Webelement (button/link/element) in which text to be retrieved
		 * @return the text
		 */
		public String getText(WebElement ele);

		/**
		 * This method will select the drop down visible text.
		 *
		 * @author Babu - TestLeaf
		 * @param ele   - The Webelement (dropdown) to be selected
		 * @param value The value to be selected (visibletext) from the dropdown
		 */
		public void selectDropDownUsingText(WebElement ele, String value) ;
		
		/**
		 * This method will select the drop down using index.
		 *
		 * @author Babu - TestLeaf
		 * @param ele   - The Webelement (dropdown) to be selected
		 * @param index The index to be selected from the dropdown
		 */
		public void selectDropDownUsingIndex(WebElement ele, int index) ;

		/**
		 * This method will verify browser actual title with expected.
		 *
		 * @author Babu - TestLeaf
		 * @param expectedTitle the expected title
		 * @return true, if successful
		 */
		public boolean verifyTitle(String expectedTitle);
		
		/**
		 * This method will verify exact given text with actual text on the given element.
		 *
		 * @author Babu - TestLeaf
		 * @param ele   - The Webelement in which the text to be need to be verified
		 * @param expectedText  - The expected text to be verified
		 */
		public void verifyExactText(WebElement ele, String expectedText);
		
		/**
		 * This method will verify given text contains actual text on the given element.
		 *
		 * @author Babu - TestLeaf
		 * @param ele   - The Webelement in which the text to be need to be verified
		 * @param expectedText  - The expected text to be verified
		 */
		public void verifyPartialText(WebElement ele, String expectedText);

		/**
		 * This method will verify exact given attribute's value with actual value on the given element.
		 *
		 * @author Babu - TestLeaf
		 * @param ele   - The Webelement in which the attribute value to be need to be verified
		 * @param attribute  - The attribute to be checked (like value, href etc)
		 * @param value  - The value of the attribute
		 */
		public void verifyExactAttribute(WebElement ele, String attribute, String value);
		
		/**
		 * This method will verify partial given attribute's value with actual value on the given element.
		 *
		 * @author Babu - TestLeaf
		 * @param ele   - The Webelement in which the attribute value to be need to be verified
		 * @param attribute  - The attribute to be checked (like value, href etc)
		 * @param value  - The value of the attribute
		 */
		public void verifyPartialAttribute(WebElement ele, String attribute, String value);
	
		/**
		 * This method will verify if the element (Radio button, Checkbox)  is selected.
		 *
		 * @author Babu - TestLeaf
		 * @param ele   - The Webelement (Radio button, Checkbox) to be verified
		 */
		public void verifySelected(WebElement ele);
		
		/**
		 * This method will verify if the element is visible in the DOM.
		 *
		 * @author Babu - TestLeaf
		 * @param ele   - The Webelement to be checked
		 */
		public void verifyDisplayed(WebElement ele);
		
		/**
		 * This method will switch to the Window of interest.
		 *
		 * @author Babu - TestLeaf
		 * @param index The window index to be switched to. 0 -> first window
		 */
		public void switchToWindow(int index);
		
		/**
		 * This method will switch to the specific frame.
		 *
		 * @author Babu - TestLeaf
		 * @param ele   - The Webelement (frame) to be switched
		 */
		public void switchToFrame(WebElement ele);
		
		/**
		 * This method will accept the alert opened.
		 *
		 * @author Babu - TestLeaf
		 */
		public void acceptAlert();
		
		/**
		 * This method will dismiss the alert opened.
		 *
		 * @author Babu - TestLeaf
		 */
		public void dismissAlert();
		
		/**
		 * This method will return the text of the alert.
		 *
		 * @author Babu - TestLeaf
		 * @return the alert text
		 */
		public String getAlertText();
		
		/**
		 * This method will take snapshot of the browser.
		 *
		 * @author Babu - TestLeaf
		 * @return the long
		 */
		public long takeSnap();
			
		/**
		 * This method will close the active browser.
		 *
		 * @author Babu - TestLeaf
		 */
		public void closeBrowser();		
		
		/**
		 * This method will close all the browsers.
		 *
		 * @author Babu - TestLeaf
		 */
		public void closeAllBrowsers();

}



