package Pageobject.Android;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import Utils.AndroidActions;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{

	AndroidDriver driver;
	
	public FormPage(AndroidDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement namefield;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement countrySelection;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement submitButton;
	
	
	public void setNameField(String name)
	{
		namefield.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender)
	{
		if(gender.contains("female"))
				{
			      femaleOption.click();
				}
		else
			maleOption.click();
	}
	
	public void setCountryName(String countryname)
	{
	   countrySelection.click();
	   scrollToText(countryname);
	  // driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryname+"']")).click();
	}
	
	//creating the object of ProductCatalog class in below method as it is last method to run in FormPage class and 
	//will navigate to ProductCatalog class after clicking the button
	
	/*public void submitForm()
	{
		submitButton.click();
	}*/
	
	public ProductCatalog submitForm()
	{
		submitButton.click();
		return new ProductCatalog(driver);
	}
	
	public void setActivity()
	{
		//Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
       /// driver.startActivity(activity);
        driver.executeScript("mobile: startActivity", ImmutableMap.of("intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
	}
	
	
}
