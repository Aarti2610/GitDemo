package frameworkpackage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestUtils.BasicConfig;

import org.openqa.selenium.By;
import org.testng.Assert;
import io.appium.java_client.AppiumBy;

public class ErrorValidation extends BasicConfig{

	
	@Test(groups = "Smoke")
	public void ErrorCheck() throws InterruptedException
	{
		
		/*ProductCatalog productcatlog = form.submitForm();
		WebElement error = driver.findElement(By.xpath("//android.widget.Toast[1]"));
		util.waitForElementToAppear(error);
		String Toastmessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		AssertJUnit.assertEquals(Toastmessage, "Please enter your name");*/
		Thread.sleep(3000);
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//to get the pop-up message/toast message, index 1 is given to read first toast message
		String Toastmessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		Assert.assertEquals(Toastmessage, "Please enter your name");

		
	}
	
	@Test(groups ="Regression")
	public void PositiveFlow() throws InterruptedException
	{
		/*form.setNameField("Aaru Bhagat");
		form.setGender("female");
		form.setCountryName("Argentina");
		ProductCatalog productcatlog = form.submitForm();
		//Assert.assertTrue(driver.findElements(By.xpath("//android.widget.Toast[1]")).size()<1);*/
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Aarti");
		Thread.sleep(2000);
		//code to hide the open keyboard
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))")).click();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(2000);	
	}
	
	@BeforeMethod(alwaysRun=true)
	public void preSetup()
	{
		form.setActivity();
	}
	
	

		
}
