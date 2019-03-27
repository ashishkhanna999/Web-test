package automation.suite.Util;



import java.io.IOException;
import java.net.URISyntaxException;
import automation.suite.test.TestBase;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.RandomStringUtils;


public class CommonUtil {

		
	/**
	 * Function will return the value corresponding to the key passed
	 * 
	 * @param key
	 *            the key
	 * @return the value from resource file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getValFromResource(String key) throws IOException {		
		return TestBase.CONFIG.getProperty(key).trim();
	}
	
	
	/**
	 * Sets the val into resource.
	 *
	 * @param key the key
	 * @param value the value
	 * @throws Exception the exception
	 */
	public static void setValIntoResource(String key, String value) throws Exception {	
		try{
		String absoultePath=System.getProperty("user.dir") + "//src//test//resources//TestData.properties";
		PropertiesConfiguration config = new PropertiesConfiguration(absoultePath);
		config.setProperty(key, value);
		config.save();
		TestBase.loadPropertyFiles();
		} 
		catch (URISyntaxException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Generate random string.
	 *
	 * @param noOfchar the no ofchar
	 * @return the string
	 */
	public static String generateRandomString(int noOfchar){		
		String randomString = RandomStringUtils.randomAlphabetic(noOfchar);
		return randomString;
	}
	

	
	
	
}
