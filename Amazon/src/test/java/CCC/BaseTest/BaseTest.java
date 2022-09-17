package CCC.BaseTest;

import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import CCC.Amazon.Component.ReadUtility;
import CCC.Amazon.Component.Reporting;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public ExtentReports extent;
	
	@BeforeTest
	public void launchBrowser() throws Exception
	{
		
		extent=Reporting.createReport();
		Properties pr=ReadUtility.readProperty("Config", "config.properties");
		String browserName=pr.getProperty("browserName");
		if(browserName.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browserName.equals("FF"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browserName.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else
		{
			throw new Exception("Driver not match");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}


	@AfterTest
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
}
