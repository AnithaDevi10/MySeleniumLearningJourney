package Javacoding;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Honda {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
//		1) Go to https://www.honda2wheelersindia.com/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "/Users/kumanananitha/eclipse-workspace/Selenium/drivers/chromedriver");
		// disable the notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		// Launching Chrome Browser
		ChromeDriver driver = new ChromeDriver(options);
		// To Load the url
		driver.get("https://www.honda2wheelersindia.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.findElementByXPath("//button[@class='close']").click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
      JavascriptExecutor js =  (JavascriptExecutor) driver;
      		//js.executeScript("window.scrollBy(0, 250)");
//			2) Click on scooters and click dio
      driver.findElementByXPath("//a[@id='link_Scooter']").click();
      Thread.sleep(2000);
      driver.findElementByXPath("//img[@src='/assets/images/thumb/dioBS6-icon.png']").click();
//			3) Click on Specifications and mouseover on ENGINE
      driver.findElementByLinkText("Specifications").click();
      driver.findElementByLinkText("ENGINE").click();
      //			4) Get Displacement value
      Thread.sleep(2000);
      String dispval_Dio=driver.findElementByXPath("//span[text()='109.51cc']").getText();
    String   dispval=dispval_Dio.replaceAll("cc","");
      System.out.println(dispval);
      double dio=Double.parseDouble(dispval);
      System.out.println(dio);
//			5) Go to Scooters and click Activa 125
      driver.findElementByXPath("//a[@id='link_Scooter']").click();
      driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']").click();
//			6) Click on Specifications and mouseover on ENGINE
      driver.findElementByLinkText("Specifications").click();
      driver.findElementByLinkText("ENGINE").click();
//			7) Get Displacement value
      String dispval_Act125=driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText();
      dispval_Act125=dispval_Act125.replaceAll("cc","");
      
      double Act125=Double.parseDouble(dispval_Act125);
      System.out.println(Act125);
//			8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
      if(dio>Act125)
      {System.out.println("Dio has good displacement");}
      {System.out.println("Activa 125 has good displacement");}      
      
//			9) Click FAQ from Menu 
      driver.findElementByXPath("//a[text()='FAQ']").click();
//			10) Click Activa 125 BS-VI under Browse By Product
      driver.findElementByLinkText("Activa 125 BS-VI").click();
//			11) Click  Vehicle Price 
      driver.findElementByXPath("//a[@href='#6a']/i").click();
//			12) Make sure Activa 125 BS-VI selected and click submit
      String Act125VI=driver.findElementById("ModelID6").getText();
      if(Act125VI.equalsIgnoreCase("Activa 125 BS-VI"))
    	  System.out.println("Activa 125 BS-VI is selected");
      driver.findElementById("submit6").click();
//			13) click the price link
      driver.findElementByXPath("//table[@id='tblPriceMasterFilters']//a[1]").click();
    //Move Control to new window
      Set<String> win = driver.getWindowHandles();
      ArrayList<String> winlis = new ArrayList<String>(win);
      driver.switchTo().window(winlis.get(1));

//			14)  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
      Select state = new Select(driver.findElementById("StateID"));
      state.selectByVisibleText("Tamil Nadu");
      Select city = new Select(driver.findElementById("CityID"));
      city.selectByVisibleText("Chennai");
      

//			15) Click Search
      driver.findElementByXPath("//button[text()='Search']").click();
//			16) Print all the 3 models and their prices
      Map<String,String> map_Model_Price = new LinkedHashMap();
		
		String strModel="";
		String strPrice="";
		
		
		for(int i=1;i<=3;i++)
		{
			strModel = driver.findElementByXPath("//table[@id='gvshow']//tr["+i+"]//td[contains(text(),'ACTIVA')]").getText();
			strPrice = driver.findElementByXPath("//table[@id='gvshow']//tr["+i+"]//td[contains(text(),'ACTIVA')]/following-sibling::td").getText();
			
			map_Model_Price.put(strModel, strPrice);
			
		}
		
		System.out.println("|Model----------Price|");
		
		for(Entry<String,String> each:map_Model_Price.entrySet())
		{
			System.out.print("|"+each.getKey()+"----------"+each.getValue());
			System.out.println();
		}
		
		driver.quit();
	
      
      
//			17) Close the Browser
	}

}
