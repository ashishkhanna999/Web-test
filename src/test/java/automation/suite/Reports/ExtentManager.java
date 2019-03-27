package automation.suite.Reports;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	public static ExtentReports extent;
	int iCounter = 0;
	public static ITestContext context;

	/**
	 * Gets the single instance of ExtentManager.
	 *
	 * @param SuiteName the suite name
	 * @return single instance of ExtentManager
	 */
	public ExtentReports getInstance(String SuiteName) {

		if (extent == null) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
			String upDate = sdf.format(date);
			String resultFile = System.getProperty("user.dir") + "//test-output//Extent_AutomationReport//" + upDate
					+ "_.html";
			extent = new ExtentReports(resultFile, true);
		}
		return extent;
	}

	/**
	 * Gets the extent instance.
	 *
	 * @return the extent instance
	 */
	public static ExtentReports getExtentInstance() {
		return extent;
	}

}