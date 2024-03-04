package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;


public class AndroidActions extends AppiumUtils{
	

		public AndroidDriver driver;
		
		public AndroidActions(AndroidDriver driver)
		{
			this.driver = driver;
		}
		
		//longPress Gesture Code
		public void longClick(WebElement ele)
		{
			((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", 
					ImmutableMap.of("elementId",((RemoteWebElement) ele).getId(),"duration",2000));
		}
		
		public void scrollToEndAction()
		//Scroll Gesture Code
		{
	    boolean canScrollMore;
	    do {
		canScrollMore = (boolean)((JavascriptExecutor)driver).executeScript("mobile: scrollGesture", 
					ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 200,
						    "direction", "down",
	    			    "percent", 3.0));
	    }
	    while(canScrollMore);
		}
		
		//passing the direction argument as the code will work for both left and right
		public void swipeAction(WebElement firstImage, String direction)
		{
			//Swipe
			   ((JavascriptExecutor)driver).executeScript("mobile:swipeGesture",ImmutableMap.of
					   ("elementId",((RemoteWebElement)firstImage).getId(),
			   "direction",direction,
			   "percent", 0.75));
		}
		
		public void scrollToText(String text)
		{
			driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))")).click();
		}
		
		
	
		
		
		
		
	}

	
	
