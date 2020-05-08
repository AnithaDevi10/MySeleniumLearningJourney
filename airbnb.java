package Javacoding;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class airbnb {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		    1) Go to https://www.airbnb.co.in/
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
		driver.get("https://www.airbnb.co.in/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
      JavascriptExecutor js =  (JavascriptExecutor) driver;
       Actions action=new Actions(driver);
       Thread.sleep(1000);
       //close the cookie button
   driver.findElementByXPath("//button[@title='OK']").click();
//			2) Type Coorg in location and Select Coorg, Karnataka
       driver.findElementById("bigsearch-query-attached-query").sendKeys("Coorg");
       driver.findElementByXPath("//div[text()='Coorg, Karnataka']").click();
//			3) Select the Start Date as June 1st and End Date as June 5th
   WebElement dates= driver.findElementByXPath("//div[text()='Add dates']");
   dates.click();
   Thread.sleep(1000);
   dates.click();
   
       driver.findElementByXPath("(//table[@class='_cvkwaj']/following::table)[2]/tbody[1]/tr[1]/td[2]/div[1]/div[1]").click();
      driver.findElementByXPath("(//table[@class='_cvkwaj']/following::table)[2]/tbody[1]/tr[1]/td[6]/div[1]/div[1]").click();
       //			4) Select guests as 6 adults, 3 child and Click Search
       driver.findElementByXPath("//div[text()='Add guests']").click();
       WebElement adults=driver.findElementByXPath("(//button[@aria-label='increase value'])[1]");
       adults.click();
       adults.click();
       adults.click();
       adults.click();
       adults.click();
       adults.click();
       WebElement children=driver.findElementByXPath("(//button[@aria-label='increase value'])[2]");
       children.click();
       children.click();
       children.click();
       driver.findElementByXPath("//span[@class='_m9v25n']").click();
//			5) Click Cancellation flexibility and enable the filter and Save
       driver.findElementByXPath("(//span[text()='Cancellation flexibility'])[1]").click();
       driver.findElementByClassName("_6ndphef").click();
       driver.findElementById("filter-panel-save-button").click();
       Thread.sleep(500);
//			6) Select Type of Place as Entire Place and Save
       driver.findElementByXPath("(//span[text()='Type of place'])[1]").click();
       driver.findElementByXPath("(//span[@data-checkbox='true'])[1]").click();
       driver.findElementById("filter-panel-save-button").click();
       Thread.sleep(500);
//			7) Set Min price as 3000 and  max price as 5000
       driver.findElementByXPath("//div[@id='menuItemButton-price_range']//button[1]").click();
       Thread.sleep(500);
      WebElement minprice= driver.findElementByXPath("//input[@id='price_filter_min']");
      minprice.click();
      Thread.sleep(1000);
    //  minprice.sendKeys(Keys.CONTROL, Keys.chord("a")); //select all text in textbox
    //  minprice.sendKeys(Keys.BACK_SPACE); //delete it
       minprice.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),"3000");
       Thread.sleep(500);
       
       WebElement maxprice=driver.findElementByXPath("//input[@id='price_filter_max']");
       maxprice.click();
       Thread.sleep(1000);
     //  maxprice.sendKeys(Keys.CONTROL, Keys.chord("a")); //select all text in textbox
    //   maxprice.sendKeys(Keys.BACK_SPACE); //delete it
       
       maxprice.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),"5000");
       Thread.sleep(1000);
       driver.findElementById("filter-panel-save-button").click();
       Thread.sleep(1000);
//			8) Click More Filters and set 3 Bedrooms and 3 Bathrooms
       driver.findElementByXPath("(//span[text()='More filters'])[1]").click();
       WebElement bedrooms=driver.findElementByXPath("(//div[@id='filterItem-stepper-min_bedrooms-0']//button)[2]");
       Thread.sleep(500);
       bedrooms.click();
       bedrooms.click();
       bedrooms.click();
       WebElement bathrooms=driver.findElementByXPath("//div[@id='filterItem-stepper-min_bathrooms-0']//button[2]");
       Thread.sleep(500);
       bathrooms.click();
       bathrooms.click();
       bathrooms.click();
//			9) Check the Amenities with Kitchen, Facilities with Free parking on premisses, Property as House and Host Language as English
//			    and click on Stays only when stays available
       Thread.sleep(1000);
       //kitchen
       driver.findElementByXPath("//input[@name='Kitchen']/following-sibling::span[1]").click();
       //facilities with free parking
       Thread.sleep(500);
       driver.findElementByXPath("//input[@name='Free parking on premises']/following-sibling::span[1]").click();
       //property as house
       Thread.sleep(500);
       driver.findElementByXPath("//input[@name='House']/following-sibling::span[1]").click();
       //click on stays as
       Thread.sleep(500);
       driver.findElementByXPath("//div[@class='_6s3rid']/following-sibling::button[1]").click();
       Thread.sleep(3000);
//			10) Click Prahari Nivas, the complete house
       driver.findElementByClassName("_15tommw").click();
//			11) Click on "Show all * amenities"
       Set<String> winset=driver.getWindowHandles();
       List<String> winlist=new ArrayList<String> (winset);
       driver.switchTo().window(winlist.get(1));
       Thread.sleep(1000);
       WebElement showamenities= driver.findElementByXPath("(//div[@class='_1p3joamp']//button)[1]");
       wait.until(ExpectedConditions.elementToBeClickable(showamenities));
       
     js.executeScript("arguments[0].click()",showamenities);
 	Thread.sleep(2000);
       
//			12) Print all the Not included amenities
       List<WebElement> not_amenities=driver.findElementsByXPath("(//div[@class='_ylytgbo']//del)");
       int i=not_amenities.size();
       System.out.println("the total num of non amenities are "+ i);
       for(WebElement eachna:not_amenities)
       {System.out.println("Non amenties are "+ eachna.getText());}
driver.findElementByXPath("(//button[@aria-label='Close'])[2]//span").click();
       Thread.sleep(2000);
     //  (//div[@class='_ylytgbo']//del)[5]
//			13) Verify the Check-in date, Check-out date and Guests
//			14) Read all the Sleeping arrangements and Print
       List<WebElement> sleep_arrange=driver.findElementsByXPath("(//div[@class='_1p3joamp']/following-sibling::div)");
	int j=sleep_arrange.size();
		System.out.println("The total num of sleep arrangements are "+ j);
		driver.findElementByXPath("//div[contains(text(),'Bed')]/ancestor::div//div[@class='_1mlprnc']").click(); 
		driver.findElementByXPath("(//div[contains(text(),'Bed')]/ancestor::div//div[@class='_1mlprnc'])[2]").click();
	//for printing sleeping arrangements	
		for(int k=0;k<sleep_arrange.size();k++)
	       {Thread.sleep(500);
	       //for first side click
	       driver.findElementByXPath("//div[contains(text(),'Bed')]/ancestor::div//div[@class='_1mlprnc']").click(); 
			//for second side click
	       driver.findElementByXPath("(//div[contains(text(),'Bed')]/ancestor::div//div[@class='_1mlprnc'])[2]").click();
			//but the glitch it will be clicked in every iteration of loop
       System.out.println("Sleeping arrangements are "+ sleep_arrange.get(k).getText());
	       }
//	     //
/*//   	List<WebElement> eleBedrooms = driver.findElementsByXPath("//div[contains(text(),'Bedroom')]");
//   	List<WebElement> eleBeds = driver.findElementsByXPath("//div[contains(text(),'bed')]");
//   	Map<String,String> map=new LinkedHashMap<String,String>();
//   	Thread.sleep(1000);
//   	WebElement eleNext = driver.findElementByXPath("//div[@data-plugin-in-point-id='SLEEPING_ARRANGEMENT_DEFAULT']//*[contains(@style,'right')]//button");
//   	int j=0;
//   	try
//   	{
//   		while(j==0)
//   		{
//   		  if(eleNext.isDisplayed())
//   		   {
//   		      for(int k=0;i<eleBedrooms.size();i++)
//   		      {
//   			     if(eleBedrooms.get(k).getText()!="" && eleBeds.get(k).getText()!="")
//   			     {
//   			    map.put(eleBedrooms.get(k).getText(),eleBeds.get(k).getText());
//   			     }
//   		       }
//   			eleNext.click();
//   			Thread.sleep(1000);
//   		    }//if
//   	    }//while
//   	}
//   	catch(Exception e)
//   	{
//   		for(int l=0;i<eleBedrooms.size();i++)
//   		{
//   			map.put(eleBedrooms.get(l).getText(),eleBeds.get(l).getText());
//   		}
//   	}
//   	for(Entry<String,String> eachEntry:map.entrySet())
//   	{
//   		System.out.println(eachEntry.getKey()+"-->"+eachEntry.getValue());
//   	}
*/
       //15) Close all the browsers

	}

}
