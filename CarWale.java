package Javacoding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarWale {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		    1) Go to https://www.carwale.com/
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
		driver.get("https://www.carwale.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 30);
      JavascriptExecutor js =  (JavascriptExecutor) driver;
      
//			2) Click on Used
      driver.findElementByXPath("//li[@data-tabs='usedCars']").click();
//			3) Select the City as Chennai
      driver.findElementById("usedCarsList").sendKeys("Chennai",Keys.ENTER);
      Thread.sleep(500);
//			4) Select budget min (8L) and max(12L) and Click Search
      driver.findElementById("budgetBtn").click();
      driver.findElementById("minInput").sendKeys("8",Keys.ENTER);
      driver.findElementById("maxInput").sendKeys("12",Keys.ENTER);
      driver.findElementById("btnFindCar").click();
      Thread.sleep(1000);
      driver.findElementByXPath("//input[@id='placesQuery']").sendKeys("Chennai,Tamil Nadu",Keys.ENTER);
      driver.findElementByXPath("//span[@id='closeLocIcon']").click();
//			5) Select Cars with Photos under Only Show Cars With
      Thread.sleep(1000);
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Cars with Photos']")));
      driver.findElementByXPath("//span[text()='Cars with Photos']").click();
//			6) Select Manufacturer as "Hyundai" --> Creta
      Thread.sleep(500);
      WebElement Hyundai=driver.findElementByXPath("(//li[@data-manufacture-en='Hyundai']/span)[1]");
      js.executeScript("arguments[0].click()",Hyundai);
//wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//li[@data-manufacture-en='Hyundai']/span)[1]")));      
//driver.findElementByXPath("(//li[@data-manufacture-en='Hyundai']/span)[1]").click();
      WebElement Creta=driver.findElementByXPath("//span[text()='Creta']");
      js.executeScript("arguments[0].click()",Creta);
//driver.findElementByXPath("//span[text()='Creta']").click();
//			7) Select Fuel Type as Petrol
      WebElement fueltype=driver.findElementByXPath("//h3[text()[normalize-space()='Fuel Type']]");
      js.executeScript("arguments[0].click()",fueltype);
//driver.findElementByXPath("//h3[text()[normalize-space()='Fuel Type']]").click();
      WebElement petrol=driver.findElementByXPath("//span[text()='Petrol']");
      js.executeScript("arguments[0].click()",petrol);
//driver.findElementByXPath("//span[text()='Petrol']").click();
//			8) Select Best Match as "KM: Low to High"
WebElement match= driver.findElementById("sort");
Select select=new Select(match);
select.selectByValue("2");

//			9) Validate the Cars are listed with KMs Low to High
//LInked Hash Map to which all the data will be stored
//(//span[@class='slkms vehicle-data__item'])[2]
List<WebElement> noofcars=driver.findElementsByXPath("//span[contains(@class,'slkms vehicle')]");
        Map<Integer, String> kmmap = new LinkedHashMap<Integer,String>();
       // List<WebElement> carname=driver.findElementsByXPath("(//span[@data-carname-id='carname'])["+i+"]");
       //list which is used to store the kms got from the list
        List<Integer> vehicleKM=new ArrayList<Integer>();       
//List<WebElement> distance=driver.findElementsByXPath("(//span[@class='slkms vehicle-data__item'])["+i+"]");
 for(int i=0;i< noofcars.size();i++)
 { 
	 String rawKM=noofcars.get(i).getText(); 
	int intKM = Integer.parseInt(rawKM.replaceAll("\\D", ""));
	vehicleKM.add(intKM);
kmmap.put(intKM,rawKM );
   }
//Create copy of the Integer List, sort and compare two lists

 List<Integer> sortedKM=new ArrayList<Integer> (vehicleKM);
 Collections.sort(sortedKM);
 if(vehicleKM.equals(sortedKM))
	 System.out.println("Huyandi car kms are sorted");
 else System.out.println("Huyandi car kms are not sorted");

//			10) Add the least KM ran car to Wishlist
 Integer lessKM=sortedKM.get(0);
 String lessKMcar="";
 for(Entry<Integer,String> eachEntry:kmmap.entrySet())
 { 
	if(eachEntry.getKey().equals(lessKM))
	{ lessKMcar=eachEntry.getValue();  }
 
 }
 System.out.println("Less km utilised by Creta Car"+lessKMcar);
 Thread.sleep(500);
 WebElement lesskmwishlist=driver.findElementByXPath("//span[text()='"+lessKMcar+"']/ancestor::div[@class='card-detail-block']/preceding-sibling::div//span[contains(@class,'shortlist-icon')]");
		//span[text()='8,000 km']/ancestor::div[@class='card-detail-block']/preceding-sibling::div//span[contains(@class,'shortlist-icon')]		 
js.executeScript("arguments[0].click();",lesskmwishlist );
 //			11) Go to Wishlist and Click on More Details
Thread.sleep(500);
driver.findElementByXPath("//li[@data-action='ShortList&CompareWindow_Click']").click();
Thread.sleep(500);
driver.findElementByLinkText("More details »").click();
//			12) Print all the details under Overview in the same way as displayed in application
Set<String> winset=driver.getWindowHandles();
List<String> winlist=new ArrayList<String> (winset);
driver.switchTo().window(winlist.get(1));
List <WebElement> cardesc=driver.findElementsByXPath("//div[@id='overview']//div[@class='equal-width text-light-grey']");
List <WebElement> carspec=driver.findElementsByXPath("//div[@id='overview']//div[@class='equal-width dark-text']");
Map<String,String> cardetails= new LinkedHashMap<String,String> ();
System.out.println("description size"+cardesc.size());
System.out.println("specification size"+carspec.size());
for(int i=0;i<cardesc.size();i++)
{ String desc=cardesc.get(i).getText();
   String spec=carspec.get(i).getText();
   cardetails.put(desc,spec);
   }
for( Entry<String,String> eachEntry:cardetails.entrySet())
{
	System.out.println(eachEntry.getKey()+"---->"+eachEntry.getValue());
}
//div[@class='equal-width dark-text']

//			13) Close the browser.
driver.quit();
	}

}
