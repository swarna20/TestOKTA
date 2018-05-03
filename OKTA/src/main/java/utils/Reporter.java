/*
 * 
 */
package utils;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporter.
 */
public abstract class Reporter {
	
	/** The test. */
	public ExtentTest test;
	
	/** The extent. */
	public static ExtentReports extent;
	
	/** The authors. */
	public String testCaseName, testDescription, category, authors;

	
	/**
	 * Report step.
	 *
	 * @param desc the desc
	 * @param status the status
	 * @param bSnap the b snap
	 */
	public void reportStep(String desc, String status, boolean bSnap) {

		if(bSnap && !status.equalsIgnoreCase("INFO")){
			long snapNumber = 100000l;
			
			try {
				snapNumber= takeSnap();
			} catch (Exception e) {
				e.printStackTrace();
			}
			desc = desc+test.
					addScreenCapture("./../reports/images/"+snapNumber+".jpg");
		}
		
		// Write if it is successful or failure or information
		if(status.equalsIgnoreCase("PASS")){
			test.log(LogStatus.PASS, desc);
		}else if(status.equalsIgnoreCase("FAIL")){
			test.log(LogStatus.FAIL, desc);
			throw new RuntimeException("FAILED");
		}else if(status.equalsIgnoreCase("WARN")){
			test.log(LogStatus.WARNING, desc);
		}else if(status.equalsIgnoreCase("INFO")){
			test.log(LogStatus.INFO, desc);
		}
	
	}
	
	/**
	 * Report step.
	 *
	 * @param desc the desc
	 * @param status the status
	 */
	public void reportStep(String desc, String status) {
		reportStep(desc, status, true);
	}


	/**
	 * Take snap.
	 *
	 * @return the long
	 */
	public abstract long takeSnap();
	

	/**
	 * Start result.
	 *
	 * @return the extent reports
	 */
	public ExtentReports startResult(){
		extent = new ExtentReports("./reports/result.html", false);
		extent.loadConfig(new File("./resources/extent-config.xml"));
		return extent;
	}

	/**
	 * Start test case.
	 *
	 * @param testCaseName the test case name
	 * @param testDescription the test description
	 * @return the extent test
	 */
	public ExtentTest startTestCase(String testCaseName, String testDescription){
		test = extent.startTest(testCaseName, testDescription);
		return test;
	}

	/**
	 * End result.
	 */
	public void endResult(){		
		extent.flush();
	}

	/**
	 * End testcase.
	 */
	public void endTestcase(){
		extent.endTest(test);
	}

	
	
}