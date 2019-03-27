package automation.suite.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;
import automation.suite.Reports.ExtentTestManager;


public class ShippingPage {

	WebDriver driver;

	public ShippingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "button[name='processCarrier']")
	private WebElement proceedToCheckoutShipBtn;
	
	@FindBy(id = "cgv")
	private WebElement termsChkbx;
	

	/**
	 * Click proceed to checkout ship button.
	 */
	public void clickProceedToCheckoutShipBtn() {
		proceedToCheckoutShipBtn.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Proceed to checkout' button on shipping screen");
	}
	
	/**
	 * Click terms and condition checkbox.
	 */
	public void clickTermsAndConditionChkbx() {
		termsChkbx.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Terms and Condition' checkbox on shipping screen");
	}
}
