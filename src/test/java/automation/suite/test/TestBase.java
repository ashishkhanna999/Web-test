package automation.suite.test;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import automation.suite.intialSetup.DriverInit;


public class TestBase extends DriverInit {

	public WebDriver driver;
	public static Properties CONFIG;
	public static FileInputStream fsConfig;
	public static FileInputStream fsAPI;
	public static FileInputStream fsData;
	public static FileInputStream fsDB;

	public WebDriver getDriver() {
		return driver;
	}

	@Parameters("browser")
	@BeforeClass
	public void setupTest(@Optional String browser) throws Exception {
		
		if (browser != null){
			WebDriver ldriver = getDriverVal(browser);
			this.driver = ldriver;
			this.driver.manage().window().maximize();
			this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			this.driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
			this.driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
		}	
	}
	
	@Parameters("browser")
	@AfterClass
	public void TearDown(@Optional String browser) {
		try {
			if (browser != null){
			this.driver.close();
			this.driver.quit();
			}
		} catch (Exception e) {
		}
	}
	
	
	/**
	 * Load property files.
	 *
	 * @throws Exception the exception
	 */
	public static void loadPropertyFiles() throws Exception{
		fsConfig = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//Configuration.properties");
				fsData= new FileInputStream(
						System.getProperty("user.dir") + "//src//test//resources//TestData.properties");
				fsAPI= new FileInputStream(
						System.getProperty("user.dir") + "//src//test//resources//API.properties");
				CONFIG.load(fsConfig);
				CONFIG.load(fsData);
				CONFIG.load(fsAPI);				
	}


}
