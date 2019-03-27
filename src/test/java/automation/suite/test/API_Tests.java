package automation.suite.test;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import automation.suite.API.API_Verification;
import automation.suite.Reports.ExtentTestManager;
import automation.suite.Util.CommonUtil;

public class API_Tests extends TestBase{
	
	@Test(description="Verify that user is able to verify the Json schema for Endpoint employees")
	@Parameters(value = "browser")
	public  void api_verifyJsonSchema() throws Exception{
		boolean flag;
		API_Verification objAPI_Verification= new API_Verification();
		flag= objAPI_Verification.verifyJsonSchema();
		Assert.assertTrue(flag);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Json Schema has been verified for 'Get Employees' end point.");	
			
	}

	@Test(description="Verify that using create enpoint user is able to add new employee and verify it.")
	@Parameters(value = "browser")
	public  void api_creatandVerifyNewEmployee() throws Exception{
		boolean flag;
		API_Verification objAPI_Verification= new API_Verification();
		flag= objAPI_Verification.createNewEmployee(5);
		Assert.assertTrue(flag);	
		ExtentTestManager.getTest().log(LogStatus.PASS, "A new employee has been added in 'Create endpoint' and verified in 'Get' response");
	}
	
	
	@Test(description="Verify the response of newly created employee.", dependsOnMethods = {"api_creatandVerifyNewEmployee"})
	@Parameters(value = "browser")
	public  void api_verifyNewlyAddedEmployee() throws Exception{
		boolean flag;
		API_Verification objAPI_Verification= new API_Verification();
		flag= objAPI_Verification.verifyNewCreatedEmployee(CommonUtil.getValFromResource("newEmployee"));
		Assert.assertTrue(flag);	
		ExtentTestManager.getTest().log(LogStatus.PASS, "Newly added employee has been verified.");	
	}
	
	
	@Test(description="Verify that user is able to call update endpoint successfully", dependsOnMethods = {"api_verifyNewlyAddedEmployee"})
	@Parameters(value = "browser")
	public  void api_updateNewlyAddedEmployee() throws Exception{
		boolean flag;
		API_Verification objAPI_Verification= new API_Verification();
		flag= objAPI_Verification.updateNewlyCreatedEmployee((CommonUtil.getValFromResource("employeeID")), 5);
		Assert.assertTrue(flag);	
		ExtentTestManager.getTest().log(LogStatus.PASS, "Newly added employee has been updated Successfully.");	
	}
	
	@Test(description="Verify that user is able to call delete endpoint successfully", dependsOnMethods = {"api_updateNewlyAddedEmployee"})
	@Parameters(value = "browser")
	public  void api_deleteNewlyAddedEmployee() throws Exception{
		boolean flag;
		API_Verification objAPI_Verification= new API_Verification();
		flag= objAPI_Verification.deleteNewlyCreatedEmployee((CommonUtil.getValFromResource("employeeID")));
		Assert.assertTrue(flag);	
		ExtentTestManager.getTest().log(LogStatus.PASS, "Newly added employee has been deleted Successfully.");	
	}
	

}
