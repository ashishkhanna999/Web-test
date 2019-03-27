package automation.suite.test;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import automation.suite.Reports.ExtentTestManager;
import automation.suite.Util.CommonUtil;
import automation.suite.pageObjects.AddressPage;
import automation.suite.pageObjects.CreateAccountPage;
import automation.suite.pageObjects.HomePage;
import automation.suite.pageObjects.OrderConfirmationPage;
import automation.suite.pageObjects.OrderSummaryPage;
import automation.suite.pageObjects.PaymentMethodPage;
import automation.suite.pageObjects.ProductDetailPage;
import automation.suite.pageObjects.ShippingPage;
import automation.suite.pageObjects.SignInPage;
import automation.suite.pageObjects.SummaryPage;



public class UI_Tests extends TestBase{

	

	@Test(description="Verify that the user is able to create an account through the website")
	@Parameters(value = "browser")
	public  void ui_verifyCreateCustomer() throws Exception{
		SignInPage objSignInPage= new SignInPage(driver);
		CreateAccountPage objCreateAccountPage= new CreateAccountPage(driver);
		HomePage objHomePage= new HomePage(driver);
		driver.get(CommonUtil.getValFromResource("AppURL"));
		
		objSignInPage.clkSignInBtn(driver);
		String EmailAddress= objSignInPage.enterEmailAddess(8);
		CommonUtil.setValIntoResource("emailAdressToLogin",EmailAddress);
		objSignInPage.clkcreateAccountBtn();
		objCreateAccountPage.enterCustomerFirstName(CommonUtil.getValFromResource("customerFirstname"));
		objCreateAccountPage.enterCustomerLastName(CommonUtil.getValFromResource("customerLastname"));
		objCreateAccountPage.enterPassword(CommonUtil.getValFromResource("password"));
		objCreateAccountPage.selectDOB(CommonUtil.getValFromResource("dayofBirth"),
				CommonUtil.getValFromResource("monthofBirth"), CommonUtil.getValFromResource("yearofBirth"));
		String strFirstname=objCreateAccountPage.enterFirstName(CommonUtil.getValFromResource("firstName"));
		String strLastname=objCreateAccountPage.enterLastName(CommonUtil.getValFromResource("lastName"));
		objCreateAccountPage.enterCompany(CommonUtil.getValFromResource("companyName"));
		objCreateAccountPage.enterAddress(CommonUtil.getValFromResource("address"));
		objCreateAccountPage.enterCity(CommonUtil.getValFromResource("city"));
		objCreateAccountPage.selectState(CommonUtil.getValFromResource("state"));
		objCreateAccountPage.enterPostCode(CommonUtil.getValFromResource("postcode"));
		objCreateAccountPage.enterMobilePhone(CommonUtil.getValFromResource("mobilePhone"));
		objCreateAccountPage.enterAddressAlias(CommonUtil.getValFromResource("addressAlias"));
		objCreateAccountPage.clickregisterBtn();
		Assert.assertEquals(objHomePage.getMyAccountHeaderVal(driver), CommonUtil.getValFromResource("myAccountHeaderVal"));
		ExtentTestManager.getTest().log(LogStatus.PASS, "My Account header value has been verified.");
		Assert.assertEquals(objHomePage.getLoggedInUsername(), strFirstname + " " + strLastname);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Logged In user has been verified.");
		Assert.assertTrue(objHomePage.verifySignOutBtn());	
		ExtentTestManager.getTest().log(LogStatus.PASS, "Sign out button is displayed when user is Logged into application.");
		objHomePage.clicksignOutBtn();
			
	} 
	
	
	@Test(description="Verify that the user is able to login into application with registered user.", dependsOnMethods = {"ui_verifyCreateCustomer"})
	@Parameters(value = "browser")
	public  void ui_verifyRegisteredCustomer() throws Exception{
		SignInPage objSignInPage= new SignInPage(driver);
		HomePage objHomePage= new HomePage(driver);
		
		driver.get(CommonUtil.getValFromResource("AppURL"));
		
		objSignInPage.clkSignInBtn(driver);
		String EmailAddress=CommonUtil.getValFromResource("emailAdressToLogin");
		objSignInPage.enterRgstrEmailAddress(EmailAddress);
		objSignInPage.enterPassword(CommonUtil.getValFromResource("password"));
		objSignInPage.clksubmitBtn();
		Assert.assertEquals(objHomePage.getMyAccountHeaderVal(driver), CommonUtil.getValFromResource("myAccountHeaderVal"));
		ExtentTestManager.getTest().log(LogStatus.PASS, "My Account header value has been verified.");
		Assert.assertEquals(objHomePage.getLoggedInUsername(), CommonUtil.getValFromResource("customerFirstname") + " "
				+ CommonUtil.getValFromResource("customerLastname"));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Logged In user has been verified.");
		Assert.assertTrue(objHomePage.verifySignOutBtn());	
		ExtentTestManager.getTest().log(LogStatus.PASS, "Sign out button is displayed when user is Logged into application.");
		objHomePage.clicksignOutBtn();
			
	} 
	
	
	@Test(description="Verify that the user is able to complete the order from the application.", dependsOnMethods = {"ui_verifyCreateCustomer"})
	@Parameters(value = "browser")
	public  void ui_verifyOrderDetails() throws Exception{
		SignInPage objSignInPage= new SignInPage(driver);
		HomePage objHomePage= new HomePage(driver);
		ProductDetailPage objProductDetailPage= new ProductDetailPage(driver);
		SummaryPage objSummaryPage=new SummaryPage(driver);
		AddressPage objAddressPage=new AddressPage(driver);
		ShippingPage objShippingPage=new ShippingPage(driver);
		PaymentMethodPage objPaymentMethodPage= new PaymentMethodPage(driver);
		OrderSummaryPage objOrderSummaryPage= new OrderSummaryPage(driver);
		OrderConfirmationPage objOrderConfirmationPage=new OrderConfirmationPage(driver);
		driver.get(CommonUtil.getValFromResource("AppURL"));
		
		objSignInPage.clkSignInBtn(driver);
		String EmailAddress=CommonUtil.getValFromResource("emailAdressToLogin");
		objSignInPage.enterRgstrEmailAddress(EmailAddress);
		objSignInPage.enterPassword(CommonUtil.getValFromResource("password"));
		objSignInPage.clksubmitBtn();
		objHomePage.clickWomenMenu();
		objHomePage.clickProductItem(driver);
		objHomePage.clickProductItem(driver);
		objProductDetailPage.clickAddToCartBtn();
		objProductDetailPage.clickProceedToCheckoutBtn();
		objSummaryPage.clickProceedToCheckoutSummBtn();
		objAddressPage.clickProceedToCheckoutAddBtn();
		objShippingPage.clickTermsAndConditionChkbx();
		objShippingPage.clickProceedToCheckoutShipBtn();
		objPaymentMethodPage.clickPayByBankLnk();
		objOrderSummaryPage.clickConfirmOrderBtn();	
		String strOrderHeaderVal= objOrderConfirmationPage.getOrderConfirmationHeader();
		Assert.assertEquals(strOrderHeaderVal, CommonUtil.getValFromResource("orderConfirmationHeaderVal"));
		ExtentTestManager.getTest().log(LogStatus.PASS, "'Order confirmation.' header has been verified.");
		String strOrderConfirmTxt= objOrderConfirmationPage.getorderConfirmationText();
		Assert.assertEquals(strOrderConfirmTxt, CommonUtil.getValFromResource("orderCompleteText"));
		ExtentTestManager.getTest().log(LogStatus.PASS, "'Your order on My Store is complete.' header has been verified.");
		Assert.assertTrue(objOrderConfirmationPage.verifyPaymentIsLastTab());	
		ExtentTestManager.getTest().log(LogStatus.PASS, "Last payment tab has been verified");
		objHomePage.clicksignOutBtn();
			
	} 


}
