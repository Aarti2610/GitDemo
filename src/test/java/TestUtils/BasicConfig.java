package TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import Pageobject.Android.FormPage;
import Utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BasicConfig extends AppiumUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage form;
	
	@BeforeClass(alwaysRun=true)
	public void Configuration() throws IOException
	{
		//The below class starts the server by giving details like .js file path, port and IP address,
		//we do not need to start it manually through terminal
			
		/* service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\aarti\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                 .withIPAddress("192.168.29.65").usingPort(4723).build();

        service.start();*/ 
		//Passing the above details in method in AppiumUtil and calling the method here
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\Data.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String ipaddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String deviceName = prop.getProperty("AndroidDeviceName");
		
        service = startServer(ipaddress,Integer.parseInt(port));
				
	UiAutomator2Options options = new UiAutomator2Options();
	options.setDeviceName(prop.getProperty(deviceName));
	options.setChromedriverExecutable("C:\\Users\\aarti\\OneDrive\\Desktop\\My Workspace\\chromedriver_win32\\chromedriver.exe");
	
	//options.setCapability("browserName", "Chrome");
	//options.setApp("C:\\Users\\aarti\\OneDrive\\Desktop\\My Workspace\\Eclipse_Workspace\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
				
	options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\General-Store.apk");
				
			
	//we have a method get URL which takes data from service(AppiumDriverLocalService
	
	 //driver = new AndroidDriver(new URL("http://192.168.29.65:4723"), options);
				                                                   //local host+ port
	driver = new AndroidDriver(service.getUrl(), options);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	form = new FormPage(driver);	
	
	}
	
	
	@AfterClass(alwaysRun=true)
	public void tearDown()
	{
		//stop server
	    driver.quit();
		
	    service.stop();
		
	}
	
	
	
	
}
