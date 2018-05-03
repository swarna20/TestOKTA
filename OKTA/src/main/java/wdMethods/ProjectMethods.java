/*
 * 
 */
package wdMethods;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import utils.DataInputProvider;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectMethods.
 */
public class ProjectMethods extends SeMethods{
	
	/** The browser name. */
	public String browserName;
	
	/** The data sheet name. */
	public String dataSheetName;
	
	/**
	 * Before suite.
	 */
	@BeforeSuite
	public void beforeSuite(){
		startResult();
	}

	/**
	 * Before test.
	 */
	@BeforeTest
	public void beforeTest(){
	}
	
	/**
	 * Before method.
	 */
	@BeforeMethod
	public void beforeMethod(){
		test = startTestCase(testCaseName, testDescription);
		test.assignCategory(category);
		test.assignAuthor(authors);
		startApp(browserName);		
	}
		
	/**
	 * After suite.
	 */
	@AfterSuite
	public void afterSuite(){
		endResult();
	}

	/**
	 * After test.
	 */
	@AfterTest
	public void afterTest(){
	}
	
	/**
	 * After method.
	 */
	@AfterMethod
	public void afterMethod(){
		endTestcase();
		closeAllBrowsers();
		
	}
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	@DataProvider(name="fetchData")
	public  Object[][] getData(){
		return DataInputProvider.getSheet(dataSheetName);		
	}	
	
}
