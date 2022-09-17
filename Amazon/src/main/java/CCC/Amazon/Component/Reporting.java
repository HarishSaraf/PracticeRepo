package CCC.Amazon.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class Reporting {
	
	public static String reportPath=null;
	public static String concate=".";
	
	public static String initializeReportPath()
	{
		reportPath=System.getProperty("user.dir")+"/TestReport/TestReport_"+getCurrentDateTimeNow();
		return reportPath;
	}

	public static ExtentReports createReport() throws IOException
	{
		File fil=new File(initializeReportPath());
		ExtentSparkReporter report = new ExtentSparkReporter(fil);
		report.loadXMLConfig(System.getProperty("user.dir")+"//Config//extent-config.xml");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extent;
	}

	public static void failTestStep(WebDriver driver,ExtentTest test,String message) throws Exception
	{
		Properties pr=ReadUtility.readProperty("Config", "config.properties");
		if(pr.getProperty("takeOnlyFailedScreenshot").equals("true") || pr.getProperty("takeBothPassAndFailedScreenshot").equals("true"))
		{
			String path=reportPath+"/FailedScreenshot/FailedScreenshot_"+getCurrentDateTimeNow();
			//String path=concate+"./TestReport/FailedScreenshot/FailedScreenshot_"+getCurrentDateTimeNow();
			takeScreenshot(driver, path); 
			test.log(Status.FAIL, message,MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		else
			test.log(Status.FAIL, message);

	}

	public static void passTestStep(WebDriver driver,ExtentTest test,String message) throws Exception
	{
		Properties pr=ReadUtility.readProperty("Config", "config.properties");
		if(pr.getProperty("takeBothPassAndFailedScreenshot").equals("true"))
		{
			String path=reportPath+"/PassedScreenshot/PassedScreenshot_"+getCurrentDateTimeNow();
			//String path=concate+"./TestReport/PassedScreenshot/PassedScreenshot_"+getCurrentDateTimeNow();
			takeScreenshot(driver, path);
			test.log(Status.PASS, message,MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		else
			test.log(Status.PASS, message);
	}

	public static void takeScreenshot(WebDriver driver, String path) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(path));
	}

	public static String getCurrentDateTimeNow()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
		LocalDateTime now = LocalDateTime.now();    
		return dtf.format(now);
	}
}
