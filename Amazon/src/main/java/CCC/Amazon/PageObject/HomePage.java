package CCC.Amazon.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;

import CCC.Amazon.Component.Component;

public class HomePage extends Component{

	WebDriver driver=null;
	ExtentTest test;
	
	public HomePage(WebDriver driver,ExtentTest test)
	{
		super(driver,test);
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='Mobiles']")
	WebElement mobileCategory;
	
	public PriceSelectionPage selectCategory() throws Exception
	{
		clickElement(mobileCategory,"Select Category");
		return new PriceSelectionPage(driver,test);
	}
	
}
