package automation.suite.API;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.relevantcodes.extentreports.LogStatus;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import automation.suite.Reports.ExtentTestManager;
import automation.suite.Util.CommonUtil;



public class API_Verification {
	
	
	/**
	 * Function will return the response from the API.
	 *
	 * @param request the request
	 * @return the response
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String getResponse(String request) throws ClientProtocolException, IOException{
		String apiOutput=null;
		DefaultHttpClient httpClient = new DefaultHttpClient();
	    HttpGet getRequest = new HttpGet(request);
		HttpResponse response = httpClient.execute(getRequest);
        HttpEntity httpEntity = response.getEntity();
        apiOutput = EntityUtils.toString(httpEntity);
		return apiOutput;		
	}
	
/**
	 * Post request.
	 *
	 * @param reqJSON the request JSON
	 * @param APIURI the apiuri
	 * @return the string
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws JSONException the JSON exception
	 */
	public String postRequest(String reqJSON,String APIURI) throws ClientProtocolException, IOException, JSONException {
		
		String apiOutput=null;
		DefaultHttpClient httpClient = new DefaultHttpClient();        
        HttpPost postRequest = new HttpPost(APIURI); 
        StringEntity params =new StringEntity(reqJSON);
        postRequest.setEntity(params);
        HttpResponse response = httpClient.execute(postRequest);
        HttpEntity httpEntity = response.getEntity();
        apiOutput = EntityUtils.toString(httpEntity);
		return apiOutput;
	}
	
	
	/**
	 * Put request.
	 *
	 * @param reqJSON the request JSON
	 * @param APIURI the apiuri
	 * @return the string
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws JSONException the JSON exception
	 */
	public int putRequest(String reqJSON,String APIURI) throws ClientProtocolException, IOException, JSONException {
	   DefaultHttpClient httpClient = new DefaultHttpClient();        
       HttpPut putRequest = new HttpPut(APIURI); 
       StringEntity params =new StringEntity(reqJSON);
       putRequest.setEntity(params);
       HttpResponse response = httpClient.execute(putRequest);
       int statuscode =response.getStatusLine().getStatusCode();
	   return statuscode;
	}
	
	/**
	 * Delete request.
	 * @param APIURI the apiuri
	 * @return the string
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws JSONException the JSON exception
	 */
	public int deleteRequest(String APIURI) throws ClientProtocolException, IOException, JSONException {
	   DefaultHttpClient httpClient = new DefaultHttpClient();        
       HttpDelete deleteRequest = new HttpDelete(APIURI); 
       HttpResponse response = httpClient.execute(deleteRequest);
       int statuscode =response.getStatusLine().getStatusCode();
	   return statuscode;
	}
	
	
	/**
	 * Verify json schema.
	 *
	 * @return true, if successful
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public boolean verifyJsonSchema() throws ClientProtocolException, IOException{
		boolean flag=false;
		try{
			String response=getResponse(CommonUtil.getValFromResource("baseUri")+"employees");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Calling a get enpoint: " +CommonUtil.getValFromResource("baseUri")+"employees");
			final File initialFile = new File(System.getProperty("user.dir") + "//src//test//resources//Schema.json");
			final InputStream targetStream = new FileInputStream(initialFile);
	        JSONObject jsonSchema = new JSONObject(new JSONTokener(targetStream));
	        //JSONObject jsonSubject = new JSONObject(new JSONTokener(API_Verification.class.getResourceAsStream("{\"id\" : \"73804\",\"employee_name\" : \"Vaidehi\",\"employee_salary\" : \"73804\",\"employee_age\" : \"28\",\"profile_image\" : \"\"}")));
	        JSONArray obj_JSONArray = new JSONArray(response);
	        for (int i=obj_JSONArray.length()-1; i>=0;i--){	
	        	JSONObject jsonSubject=obj_JSONArray.getJSONObject(i);
	        	 Schema schema = SchemaLoader.load(jsonSchema);
	        	 schema.validate(jsonSubject);
			}
	        flag=true;
			
		}
		catch(Exception e){
			flag=false;
			System.out.println(e.getMessage());
			
		}
		return flag;		
		
	}
	
	/**
	 * Creates the new employee.
	 *
	 * @param noOfchar the no ofchar
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean createNewEmployee(int noOfchar) throws Exception {
		boolean flag=false;
		String randomVal=CommonUtil.generateRandomString(noOfchar);
		String emplyeename="test_gk_"+randomVal;
		CommonUtil.setValIntoResource("newEmployee",emplyeename);
		String reqJSON = "{\"name\":\" "+emplyeename+" \",\"salary\":\"5000\",\"age\":\"30\"}";		   
	    String postURI = CommonUtil.getValFromResource("baseUri")+"create";
	    ExtentTestManager.getTest().log(LogStatus.INFO, "Calling a Post enpoint: " +postURI);
	    ExtentTestManager.getTest().log(LogStatus.INFO, "Calling a Post enpoint with body: " +reqJSON);
	    postRequest(reqJSON,postURI);	 
	    String response=getResponse(CommonUtil.getValFromResource("baseUri")+"employees");   
	    JSONArray obj_JSONArray = new JSONArray(response);
	    for (int i=obj_JSONArray.length()-1; i>=0;i--){	
	    	String expEmpVal=obj_JSONArray.getJSONObject(i).getString("employee_name");
			 if(expEmpVal.trim().equals(emplyeename.trim())==true){ 
				 flag=true;
				 break;			 
			 }
		}
	  return flag;
	 }
	
	
	
	/**
	 * Verify new created employee.
	 *
	 * @param EmployeeName the employee name
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean verifyNewCreatedEmployee(String EmployeeName) throws Exception {
		boolean flag=false;
		String response=getResponse(CommonUtil.getValFromResource("baseUri")+"employees");
	    ExtentTestManager.getTest().log(LogStatus.INFO, "Calling a Get enpoint: " +CommonUtil.getValFromResource("baseUri")+"employees");
	    ExtentTestManager.getTest().log(LogStatus.INFO, "Calling a Get enpoint to verify response of employee: " +CommonUtil.getValFromResource("newEmployee"));
	    JSONArray obj_JSONArray = new JSONArray(response);
	    for (int i=obj_JSONArray.length()-1; i>=0;i--){	
	    	String expEmpVal=obj_JSONArray.getJSONObject(i).getString("employee_name");
			 if(expEmpVal.trim().equals(EmployeeName.trim())==true){ 			 
				 String strSalary= obj_JSONArray.getJSONObject(i).getString("employee_salary");
				 String strAge= obj_JSONArray.getJSONObject(i).getString("employee_age");
				 CommonUtil.setValIntoResource("employeeID",obj_JSONArray.getJSONObject(i).getString("id"));
				 if(strSalary.equals("5000") && strAge.equals("30") ){
					 flag=true; 
				 }
				 break;			 
			 }
		}
	  return flag;
	 }
	
	
	/**
	 * Update newly created employee.
	 *
	 * @param EmployeeID the employee ID
	 * @param noOfchar the no ofchar
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean updateNewlyCreatedEmployee(String EmployeeID, int noOfchar) throws Exception {
		boolean flag=false;
		String randomVal=CommonUtil.generateRandomString(noOfchar);
		String emplyeename="test_gk_"+randomVal;
		String reqJSON = "{\"name\":\" "+emplyeename+" \",\"salary\":\"5000\",\"age\":\"30\"}";
		int statuscode=putRequest(reqJSON,CommonUtil.getValFromResource("baseUri")+"update/" +EmployeeID);
	    ExtentTestManager.getTest().log(LogStatus.INFO, "Calling a update enpoint: " +CommonUtil.getValFromResource("baseUri")+"update/" +EmployeeID);
	    ExtentTestManager.getTest().log(LogStatus.INFO, "Calling a update enpoint with body: " +reqJSON);
	    if(statuscode==200){
	    	flag=true;
	    }
	  return flag;
	 }

	
	/**
	 * Delete newly created employee.
	 *
	 * @param EmployeeID the employee ID
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean deleteNewlyCreatedEmployee(String EmployeeID) throws Exception {
		boolean flag=false;
		int statuscode=deleteRequest(CommonUtil.getValFromResource("baseUri")+"delete/" +EmployeeID);
	    ExtentTestManager.getTest().log(LogStatus.INFO, "Calling a delete enpoint: " +CommonUtil.getValFromResource("baseUri")+"delete/" +EmployeeID);
	    if(statuscode==200){
	    	flag=true;
	    }
	  return flag;
	 }
	
	
	
	
	



}



