package Utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

//This class is created to use methods which are common to both android and IOS 
public abstract class AppiumUtils {
	
  public AppiumDriverLocalService service;
	
	public double formattedSumAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
	
	/*public List<HashMap<String,String>> getJsonData(String JsonFilepath)
	{
		//System.getProperty("user.dir")+"src\\test\\java\\frameworkpackage\\TestData\\TestData.json
		String jsonContent = FileUtils.readFileToString(new File(JsonFilepath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String,String>>>() {

		});
				
	}*/
	
	
	public void waitForElementToAppear(WebElement ele,AppiumDriver driver)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
	
	}
	
	
	//passing IP address and port in parameters
	public AppiumDriverLocalService  startServer(String ipAddress, int port)
	{
		// service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\aarti\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
               //  .withIPAddress("192.168.29.65").usingPort(4723).build();
		 service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\aarti\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
         .withIPAddress(ipAddress).usingPort(port).build();
        service.start();
        return service;
	}
	
	//screenshot code
	public String getScreenshotpath(String testCaseName, AppiumDriver driver) throws IOException
	{
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile =System.getProperty("user.dir")+"//reports"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
				
				
	}
	
}
