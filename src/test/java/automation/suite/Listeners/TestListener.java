package automation.suite.Listeners;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import automation.suite.Reports.ExtentManager;
import automation.suite.Reports.ExtentTestManager;
import automation.suite.intialSetup.DriverInit;
import automation.suite.test.TestBase;

public class TestListener extends TestBase implements ITestListener {
	
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		CONFIG = new Properties();
		try {
			TestBase.loadPropertyFiles();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ExtentTestManager.startTest(methodName, "Description: " + result.getMethod().getDescription());
		
	}

	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test Case has been PASSED.");
		ExtentManager.getExtentInstance().endTest(ExtentTestManager.getTest());
		ExtentManager.getExtentInstance().flush();
		
	}

	public void onTestFailure(ITestResult result) {
		String filePath = System.getProperty("user.dir") + "//test-output//ErrorScreenshots//"
				+ result.getMethod().getMethodName() + ".png";
		System.out.println("***** Error "+result.getName()+" test has failed *****");
    	String methodName=result.getName().toString().trim();
    	takeScreenShot(methodName, DriverInit.getBrowserInstance(),filePath);
    	ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
    	ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Case has been FAILED.");
    	ExtentManager.getExtentInstance().endTest(ExtentTestManager.getTest());
		ExtentManager.getExtentInstance().flush();	
		
	}
	
	public void takeScreenShot(String methodName, WebDriver driver,String filePath) {
		
   	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with test method name 
           try {
				FileUtils.copyFile(scrFile, new File(filePath));
				System.out.println("***Placed screen shot in "+filePath+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
   }
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		ExtentTestManager.startTest(methodName, "Description: " + result.getMethod().getDescription());
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Case has been Skipped.");
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		new ExtentManager().getInstance(context.getSuite().getName());
		
	}

	public void onFinish(ITestContext context) {
		ExtentManager.getExtentInstance().endTest(ExtentTestManager.getTest());
		ExtentManager.getExtentInstance().flush();	
		
	}
	
	protected String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}



	
	
	
	
	
	
	}
