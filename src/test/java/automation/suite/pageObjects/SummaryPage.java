package automation.suite.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;
import automation.suite.Reports.ExtentTestManager;


public class SummaryPage {

	WebDriver driver;

	public SummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "p[class='cart_navigation clearfix'] a[title='Proceed to checkout']")
	private WebElement proceedToCheckoutSummBtn;
	
	
	/**
	 * Click proceed to checkout summary button.
	 */
	public void clickProceedToCheckoutSummBtn() {
		proceedToCheckoutSummBtn.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Proceed to checkout' button on summary screen");
	}
}
