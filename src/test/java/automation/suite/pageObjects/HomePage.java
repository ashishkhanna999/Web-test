package automation.suite.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import automation.suite.Reports.ExtentTestManager;


public class HomePage {

	WebDriver driver;
	WebDriverWait wait;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By myAccountHeader = By.cssSelector("div[id='center_column'] h1[class='page-heading']");

	@FindBy(className = "logout")
	private WebElement signOutBtn;
	
	@FindBy(className = "account")
	private WebElement usernameLabel;
	
	@FindBy(css = "a[title='Women']")
	private WebElement womenMenu;
	
	By productItem = By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li");
	
	
	
	/**
	 * Gets the my account header value.
	 *
	 * @param Driver the driver
	 * @return the my account header val
	 */
	public String getMyAccountHeaderVal(WebDriver Driver){
		String strHeaderVal=null;
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountHeader)).isDisplayed();
		strHeaderVal= Driver.findElement(myAccountHeader).getText();
		return strHeaderVal;
	}
	
	/**
	 * Verify sign out button.
	 *
	 * @return true, if successful
	 */
	public boolean verifySignOutBtn(){
		boolean flag=false;
		flag=signOutBtn.isDisplayed();
		return flag;
	}
	
	/**
	 * Gets the logged in username.
	 *
	 * @return the logged in username
	 */
	public String getLoggedInUsername(){
		String strUsername=null;
		strUsername=usernameLabel.getText();
		return strUsername;
	}
	
	/**
	 * Click sign out button.
	 */
	public void clicksignOutBtn() {
		signOutBtn.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Signout' button");
	}
	
	/**
	 * Click women menu bar.
	 */
	public void clickWomenMenu() {
		womenMenu.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Women' menu");
	}
	
	/**
	 * Click the product item.
	 *
	 * @param Driver the driver
	 */
	public void clickProductItem(WebDriver Driver) {
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(productItem)).isDisplayed();
		Driver.findElement(productItem).click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Faded Short Sleeve T-shirts' product");
	}
}
