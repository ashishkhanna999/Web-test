package automation.suite.Reports;

import java.util.HashMap;
import java.util.Map;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentTestManager {
	
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

    /**
     * Gets the test report object instance.
     *
     * @return the test
     */
    public static ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    /**
     * End test.
     */
    public static  void endTest() {
    	ExtentManager.getExtentInstance().endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    /**
     * Start test.
     *
     * @param testName the test name
     * @param desc the desc
     * @return the extent test
     */
    public static ExtentTest startTest(String testName, String desc) {
        ExtentTest test = ExtentManager.getExtentInstance().startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        
        return test;
    }
}
