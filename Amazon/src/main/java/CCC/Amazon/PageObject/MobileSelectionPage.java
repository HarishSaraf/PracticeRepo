package CCC.Amazon.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import CCC.Amazon.Component.Component;

public class MobileSelectionPage extends Component{

	WebDriver driver=null;
	
	public MobileSelectionPage(WebDriver driver,ExtentTest test) {
		// TODO Auto-generated constructor stub
		super(driver,test);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@id='productTitle']")
	WebElement productName;
	
	@FindBy(xpath="//span[@id='productTitle']")
	List<WebElement> productNameList;
	
	public void selectMobile(String price) throws Exception
	{
		WebElement element=driver.findElement(By.xpath("//span[text()='"+price+"']"));
		clickElement(element,"Price");
	}
	
	public String getProductName()
	{
		return productName.getText().trim();
	}
	
}
