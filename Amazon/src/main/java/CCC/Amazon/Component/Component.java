package CCC.Amazon.Component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Component {

	WebDriver driver=null;
	ExtentTest test=null;

	public Component(WebDriver driver,ExtentTest test)
	{
		this.driver=driver;
		this.test=test;
	}

	public void gotoURL(String url) throws Exception
	{
		try {
			driver.get(url);
			Reporting.passTestStep(driver,test, "Go to URL => "+url);
		}
		catch(Exception e)
		{
			Reporting.failTestStep(driver,test, "Failed go to URL => "+url);
		}
	}

	public void clickElement(WebElement element, String name) throws Exception
	{
		try {
			element.click();
			Reporting.passTestStep(driver,test, "Clicked on => "+name);
		}
		catch(Exception e)
		{
			Reporting.failTestStep(driver,test,"Failed go to Click on => "+name);
		}
	}

	public void assertValue(String actual,String expected) throws Exception
	{
		try {
			Assert.assertEquals(actual, expected);
			Reporting.passTestStep(driver,test, "Value Matched expected=> "+expected+ " and actual is "+actual);
		}
		catch(Exception e)
		{
			Reporting.failTestStep(driver,test, "Value don't matched expected=> "+expected+ " and actual is "+actual);
		}
	}

}
