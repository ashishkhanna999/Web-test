package automation.suite.pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import automation.suite.Reports.ExtentTestManager;


public class OrderSummaryPage {

	WebDriver driver;
	WebDriverWait wait;

	public OrderSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#cart_navigation button")
	private WebElement confirmOrderBtn;
	
	
	/**
	 * Click confirm order button.
	 */
	public void clickConfirmOrderBtn() {
		confirmOrderBtn.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'I confirm my order' button");
	}
}
