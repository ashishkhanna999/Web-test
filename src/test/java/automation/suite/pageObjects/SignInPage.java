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
import automation.suite.Util.CommonUtil;


public class SignInPage {

	WebDriver driver;
	WebDriverWait wait;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By signInBtn = By.className("login");
	
	@FindBy(id = "email_create")
	private WebElement emailAddressIntbx;

	@FindBy(id = "SubmitCreate")
	private WebElement createAccountBtn;
	
	@FindBy(id = "email")
	private WebElement rgstrEmailAddressIntbx;
	
	@FindBy(id = "passwd")
	private WebElement passwordIntbx;
	
	@FindBy(id = "SubmitLogin")
	private WebElement submitBtn;
	

	
	/**
	 * Click sign in button.
	 *
	 * @param Driver the driver
	 */
	public void clkSignInBtn(WebDriver Driver) {
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(signInBtn)).isDisplayed();
		Driver.findElement(signInBtn).click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Sign In' button");
	}
	
	/**
	 * Click create account button.
	 */
	public void clkcreateAccountBtn() {
		createAccountBtn.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Create an account' button");
	}
	
	/**
	 * Enter email address.
	 *
	 * @param noOfchar the no ofchar
	 * @return the string
	 */
	public String enterEmailAddess(int noOfchar) {
		String strRndm= CommonUtil.generateRandomString(noOfchar);
		String Email=strRndm+"@gk.com";
		emailAddressIntbx.sendKeys(Email);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter Emailaddress as: " +strRndm+"@gk.com");
		return Email;
	}
	
	
	
	/**
	 * Enter register email address.
	 *
	 * @param Email the email
	 */
	public void enterRgstrEmailAddress(String Email) {
		rgstrEmailAddressIntbx.sendKeys(Email);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter emailaddress as: " +Email);
	}
	
	/**
	 * Enter password.
	 *
	 * @param password the password
	 */
	public void enterPassword(String password) {
		passwordIntbx.sendKeys(password);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter password as: " +password);
	}
	
	/**
	 * Click submit button.
	 */
	public void clksubmitBtn() {
		submitBtn.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Submit' button");
	}
}
