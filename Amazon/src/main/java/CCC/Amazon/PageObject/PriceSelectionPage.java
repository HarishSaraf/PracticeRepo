package CCC.Amazon.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import CCC.Amazon.Component.Component;

public class PriceSelectionPage extends Component{

	WebDriver driver=null;
	ExtentTest test=null;
	
	public PriceSelectionPage(WebDriver driver,ExtentTest test) {
		// TODO Auto-generated constructor stub
		super(driver,test);
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='Starting ₹6,999']")
	WebElement selectPrice6999;
	
	public MobileSelectionPage selectPrice() throws Exception
	{
		clickElement(selectPrice6999,"Select Price 6999");
		return new MobileSelectionPage(driver,test);
	}

}
