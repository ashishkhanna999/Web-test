package automation.suite.pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;
import automation.suite.Reports.ExtentTestManager;


public class ProductDetailPage {

	WebDriver driver;

	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "[id='add_to_cart'] button")
	private WebElement addToCartBtn;
	
	@FindBy(css = "div[id='layer_cart'] a")
	private WebElement proceedToCheckoutBtn;
	
	/**
	 * Click add to cart button.
	 */
	public void clickAddToCartBtn() {
		addToCartBtn.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Add to cart' button");
	}
	
	/**
	 * Click proceed to checkout button.
	 */
	public void clickProceedToCheckoutBtn() {
		proceedToCheckoutBtn.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Proceed to checkout' button");
	}
}
