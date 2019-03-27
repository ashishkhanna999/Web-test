package automation.suite.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;
import automation.suite.Reports.ExtentTestManager;


public class AddressPage {

	WebDriver driver;

	public AddressPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "p[class='cart_navigation clearfix'] button")
	private WebElement proceedToCheckoutAddBtn;
	
	
	/**
	 * Click proceed to checkout add button.
	 */
	public void clickProceedToCheckoutAddBtn() {
		proceedToCheckoutAddBtn.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Proceed to checkout' button on address screen");
	}
}
