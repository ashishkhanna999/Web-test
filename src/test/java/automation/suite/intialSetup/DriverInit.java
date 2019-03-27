package automation.suite.intialSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInit {

	private WebDriver driver;
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

	
	/**
	 * Driver initialse.
	 *
	 * @param browser the browser
	 * @return the web driver
	 */
	private WebDriver DriverInitialse(String browser) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();

				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			setWebDriver(driver);

		} catch (Exception e) {
			System.err.println("Exception in starting driver: " + e.getMessage());
		}
		return driver;
	}


	/**
	 * Gets the driver val.
	 *
	 * @param browser the browser
	 * @return the driver val
	 * @throws Exception the exception
	 */
	public WebDriver getDriverVal(String browser) throws Exception {		
		DriverInitialse(browser);
        return dr.get();
    }

	/**
	 * Gets the browser instance.
	 *
	 * @return the browser instance
	 */
	public static WebDriver getBrowserInstance() {
		return dr.get();
	}

	/**
	 * Sets the web driver.
	 *
	 * @param driver the new web driver
	 */
	public void setWebDriver(WebDriver driver) {
		dr.set(driver);
	}

}
