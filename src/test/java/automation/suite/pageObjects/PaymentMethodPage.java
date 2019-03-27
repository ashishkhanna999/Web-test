package automation.suite.pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;
import automation.suite.Reports.ExtentTestManager;


public class PaymentMethodPage {

	WebDriver driver;

	public PaymentMethodPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(className = "bankwire")
	private WebElement payByBankLnk;
	
	
	/**
	 * Click pay by bank link.
	 */
	public void clickPayByBankLnk() {
		payByBankLnk.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Pay by bank wire' link");
	}
}
