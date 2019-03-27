package automation.suite.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

import automation.suite.Reports.ExtentTestManager;

public class CreateAccountPage {

	WebDriver driver;

	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "customer_firstname")
	private WebElement cutmrfirstNameInputBox;

	@FindBy(id = "customer_lastname")
	private WebElement cutmrlastNameInputBox;

	@FindBy(id = "email")
	private WebElement emailInputBox;

	@FindBy(id = "passwd")
	private WebElement passwordInputBox;

	@FindBy(id = "days")
	private WebElement daysDrpdwm;

	@FindBy(id = "months")
	private WebElement monthsDrpdwm;

	@FindBy(id = "years")
	private WebElement yearsDrpdwm;

	@FindBy(id = "firstname")
	private WebElement firstNameInputBox;

	@FindBy(id = "lastname")
	private WebElement lastNameInputBox;

	@FindBy(id = "company")
	private WebElement companyInputBox;

	@FindBy(id = "address1")
	private WebElement addressInputBox;

	@FindBy(id = "city")
	private WebElement cityInputBox;

	@FindBy(id = "id_state")
	private WebElement stateDrpdwm;

	@FindBy(id = "postcode")
	private WebElement postcodeInputBox;

	@FindBy(id = "id_country")
	private WebElement countryDrpdwm;

	@FindBy(id = "phone_mobile")
	private WebElement mobileInputBox;

	@FindBy(id = "alias")
	private WebElement aliasInputBox;

	@FindBy(id = "submitAccount")
	private WebElement registerBtn;

	/**
	 * Enter customer first name.
	 *
	 * @param FirstName the first name
	 * @return the string
	 */
	public String enterCustomerFirstName(String FirstName) {
		cutmrfirstNameInputBox.sendKeys(FirstName);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter customer first name as: " +FirstName);
		return FirstName;
	}

	/**
	 * Enter customer last name.
	 *
	 * @param LastName the last name
	 * @return the string
	 */
	public String enterCustomerLastName(String LastName) {
		cutmrlastNameInputBox.sendKeys(LastName);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter customer last name as: " +LastName);
		return LastName;
	}

	/**
	 * Enter email address.
	 *
	 * @param EmailAddress the email address
	 */
	public void enterEmailAddress(String EmailAddress) {
		emailInputBox.sendKeys(EmailAddress);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter emailaddress as: " +EmailAddress);
	}

	/**
	 * Enter password.
	 *
	 * @param Password the password
	 */
	public void enterPassword(String Password) {
		passwordInputBox.sendKeys(Password);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter password as: " +Password);
	}

	/**
	 * Select DOB.
	 *
	 * @param Days the days
	 * @param Month the month
	 * @param Year the year
	 */
	public void selectDOB(String Days, String Month, String Year) {
		Select selectDay = new Select(daysDrpdwm);
		Select selectMonth = new Select(monthsDrpdwm);
		Select selectYear = new Select(yearsDrpdwm);
		selectDay.selectByValue(Days);
		selectMonth.selectByValue(Month);
		selectYear.selectByValue(Year);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Select date of birth as: " +Days+ "-"+Month+"-"+Year);

	}

	/**
	 * Enter first name.
	 *
	 * @param FirstName the first name
	 * @return the string
	 */
	public String enterFirstName(String FirstName) {
		firstNameInputBox.sendKeys(FirstName);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter firstname as: " +FirstName);
		return FirstName;
	}

	/**
	 * Enter last name.
	 *
	 * @param LastName the last name
	 * @return the string
	 */
	public String enterLastName(String LastName) {
		lastNameInputBox.sendKeys(LastName);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter lastName as: " +LastName);
		return LastName;
	}

	/**
	 * Enter company.
	 *
	 * @param CompanyName the company name
	 */
	public void enterCompany(String CompanyName) {
		companyInputBox.sendKeys(CompanyName);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter company Name as: " +CompanyName);
	}

	/**
	 * Enter address.
	 *
	 * @param Address the address
	 */
	public void enterAddress(String Address) {
		addressInputBox.sendKeys(Address);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter address as: " +Address);
	}

	/**
	 * Enter city.
	 *
	 * @param City the city
	 */
	public void enterCity(String City) {
		cityInputBox.sendKeys(City);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter city as: " +City);
	}

	/**
	 * Select state.
	 *
	 * @param State the state
	 */
	public void selectState(String State) {
		Select selectState = new Select(stateDrpdwm);
		selectState.selectByVisibleText(State);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Select state as: " +State);
	}

	/**
	 * Enter post code.
	 *
	 * @param Postcode the postcode
	 */
	public void enterPostCode(String Postcode) {
		postcodeInputBox.sendKeys(Postcode);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter postcode as: " +Postcode);
	}

	/**
	 * Select country.
	 *
	 * @param Country the country
	 */
	public void selectCountry(String Country) {
		Select selectCountry = new Select(countryDrpdwm);
		selectCountry.selectByValue(Country);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Select Country as: " +Country);
	}

	/**
	 * Enter mobile phone.
	 *
	 * @param MobilePhone the mobile phone
	 */
	public void enterMobilePhone(String MobilePhone) {
		mobileInputBox.sendKeys(MobilePhone);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter MobilePhone as: " +MobilePhone);
	}

	/**
	 * Enter address alias.
	 *
	 * @param Alias the alias
	 */
	public void enterAddressAlias(String Alias) {
		aliasInputBox.clear();
		aliasInputBox.sendKeys(Alias);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Enter Address alias as: " +Alias);
	}

	/**
	 * Click register button.
	 */
	public void clickregisterBtn() {
		registerBtn.click();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Click on 'Register' button");
	}

}
