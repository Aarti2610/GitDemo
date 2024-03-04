package frameworkpackage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.net.MalformedURLException;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pageobject.Android.CartPage;
import Pageobject.Android.ProductCatalog;
import TestUtils.BasicConfig;



public class MyClass extends BasicConfig{

	@Test(dataProvider="getData", groups="Regression")
	public void AppiumTest(String name, String gender, String country) throws MalformedURLException, InterruptedException
	{
		
		//FormPage form = new FormPage(driver); we can add this in Basetest calss directly
		form.setNameField(name);
		form.setGender(gender);
		form.setCountryName(country);
		ProductCatalog productcatlog = form.submitForm();
		
		//ProductCatalog productcatlog = new ProductCatalog(driver);
		productcatlog.addItemsToCartByIndex(0);
		productcatlog.addItemsToCartByIndex(0);
		
		Thread.sleep(2000);
		
		CartPage cart = productcatlog.cartButton();
	    double totalSum = cart.totalPrice();
		
		double totalprice = cart.totalPriceDisplayed();
		
		AssertJUnit.assertEquals(totalSum, totalprice);
		
		cart.termsAndConditions();
		Thread.sleep(2000);
	
		cart.submitOrder();
		Thread.sleep(6000);

	}
	
	@BeforeMethod(alwaysRun=true)
	public void preSetup()
	{
		form.setActivity();
	}
	@DataProvider
	public Object[][] getData()
	{
		return new Object [][] {{"Prakash Bhagat","male","India"},{"Aarti Bhagat","female","Argentina"}};
	}
}
