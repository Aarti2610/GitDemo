package Pageobject.Android;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalog extends AndroidActions{

AndroidDriver driver;
	
	public ProductCatalog(AndroidDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//we are using list as  there are multiple items with same xpath
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;
	
	public void addItemsToCartByIndex(int index)
	{
		addToCart.get(index).click();
	}
	
	public CartPage cartButton() throws InterruptedException
	{
		cartButton.click();
		Thread.sleep(2000);
		return new CartPage(driver);
	}
}
