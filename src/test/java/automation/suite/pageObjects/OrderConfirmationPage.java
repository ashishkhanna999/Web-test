package automation.suite.pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {

	WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(css = "#center_column h1")
	private WebElement orderConfirmationHeader;
	
	@FindBy(css = "p[class='cheque-indent'] strong")
	private WebElement orderConfirmationText;
	
	@FindBy(css = "li[id='step_end'][class='step_current last'] ")
	private WebElement paymentTab;
	
	
	/**
	 * Gets the order confirmation header.
	 *
	 * @return the order confirmation header
	 */
	public String getOrderConfirmationHeader() {
		String strHeaderVal=orderConfirmationHeader.getText();
		return strHeaderVal;
	}
	
	
	/**
	 * Gets the order confirmation text.
	 *
	 * @return the order confirmation text
	 */
	public String getorderConfirmationText() {
		String strConfirmTxt=orderConfirmationText.getText();
		return strConfirmTxt;
	}
	
	
	/**
	 * Verify payment is last tab.
	 *
	 * @return true, if successful
	 */
	public boolean verifyPaymentIsLastTab() {
		boolean flag=false;
		flag=paymentTab.isDisplayed();
		return flag;
	}
	
}
