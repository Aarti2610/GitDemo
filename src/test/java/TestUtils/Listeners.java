package TestUtils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.IListeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.AppiumUtils;
import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener{
	
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ExtentTest test;
	AppiumDriver driver;
	//need to add the details in testNG file as well
	//this method will run before every test in any class
	public void onTestStart(ITestResult result)
	{
		 test = extent.createTest(result.getMethod().getMethodName());
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test is passed");
	}

	public void onTestFailure(ITestResult result)
	{
		test.fail(result.getThrowable());
		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		
		try {
			test.addScreenCaptureFromPath(getScreenshotpath(result.getMethod().getMethodName(),driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}

}
