package CCC.Amazon;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import CCC.Amazon.Component.ReadUtility;
import CCC.Amazon.PageObject.HomePage;
import CCC.Amazon.PageObject.MobileSelectionPage;   
import CCC.Amazon.PageObject.PriceSelectionPage;
import CCC.BaseTest.BaseTest;

public class MobileSelection extends BaseTest{


	ExtentTest test;

	@Test(enabled=true,dataProvider="getDataNew")
	public void mobileSelection(HashMap<String, String> map) throws Exception {

		test=extent.createTest("Select Mobile with Price "+map.get("Price")+" "+" Mobile "+map.get("Mobile"));
		
		HomePage hm=new HomePage(driver,test);
		hm.gotoURL("https://www.amazon.in/");
		PriceSelectionPage ps=hm.selectCategory();
		MobileSelectionPage mb=ps.selectPrice();
		mb.selectMobile(map.get("Price"));
		String productName=mb.getProductName();
		mb.assertValue(productName, map.get("Mobile"));
	}

	@DataProvider
	public Object[][] getData()
	{
		HashMap<String, String> map1=new HashMap<String, String>();
		map1.put("Price", "18,999");
		map1.put("Mobile", "OnePlus Nord CE 2 Lite 5G (Blue Tide, 6GB RAM, 128GB Storage)");
		HashMap<String, String> map2=new HashMap<String, String>();
		map2.put("Price", "6,999");
		map2.put("Mobile", "realme narzo 50i (Mint Green, 2GB RAM+32GB Storage) - 6.5\" inch Large Display | 5000mAh Battery");
		return new Object[][]{{map1},{map2}};
	}
	
	@DataProvider
	public Object[][] getDataNew() throws Exception
	{
		List<HashMap<String, String>> data=ReadUtility.readJsonFile("TestData", "TestData1.json");
		return new Object[][]{{data.get(0)}};
	}

	public void testMethod()
	{
		//This is a test method
	}
}
