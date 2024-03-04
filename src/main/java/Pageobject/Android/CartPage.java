package Pageobject.Android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{

	AndroidDriver driver;
	
	public CartPage(AndroidDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']")
	private List<WebElement> productPrice;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsButton;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement acceptButton;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement checkbox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;

	
	public double totalPrice()
	{
		int count = productPrice.size();
		double sum =0;
		for(int i= 0 ;i<count ;i++)
		{
			String pricestring = productPrice.get(i).getText();
			Double price = formattedSumAmount(pricestring);
			sum = sum+price;
			
		}
		return sum;
		
	}

	public double totalPriceDisplayed()
	{
		return formattedSumAmount(totalAmount.getText());
	}
	
	public void termsAndConditions() throws InterruptedException
	{
		longClick(termsButton);
		Thread.sleep(2000);
		acceptButton.click();
	}
	
	public void submitOrder()
	{
		checkbox.click();
		proceed.click();
	}
	
}
