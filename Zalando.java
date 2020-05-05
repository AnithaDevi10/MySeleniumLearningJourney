package Javacoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zalando {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//			1) Go to https://www.zalando.com/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "/Users/kumanananitha/eclipse-workspace/Selenium/drivers/chromedriver");
		// disable the notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
	    // Launching Chrome Browser
		ChromeDriver driver = new ChromeDriver(options);
		// To Load the url
		driver.get("https://www.zalando.com/");
		// To maximize the browser
		 try 
		    { 
		    	System.out.println("alert  present");
				
		    	Alert alert=driver.switchTo().alert();
			      String alerttext=alert.getText();
			      System.out.println("The alert message is"+alerttext);
			      alert.accept();
			    
		        //return true; 
		    }   // try 
		    catch (NoAlertPresentException Ex) 
		    { 
		    	System.out.println("Alert not present");
		       // return false; 
		    }   // catch 


		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 30);
      JavascriptExecutor js =  (JavascriptExecutor) driver;
      Actions action=new Actions(driver);
      
		//Thread.sleep(2000);
	   		
		
		
		
		//WebDriverWait wait = new WebDriverWait(driver, 300 /*timeout in seconds*/);
		/*if(wait.until(ExpectedConditions.alertIsPresent())!=null)
		    {
			System.out.println("alert  present");
		Alert alert=driver.switchTo().alert();
	      String alerttext=alert.getText();
	      System.out.println("The alert message is"+alerttext);
	      alert.accept();
	      }
	      
		else
		    System.out.println("alert not present");
		*/
//			2) Get the Alert text and print it
      Thread.sleep(500);
//			3) Close the Alert box and click on Zalando.uk
      driver.findElementByXPath("//a[text()='Zalando.uk']").click();
      Thread.sleep(500);
//			4) Click Women--> Clothing and click Coat 
      driver.findElementByXPath("(//span[contains(@class,'z-text z-navicat-header_genderText')])[1]").click();
      driver.findElementByXPath("(//span[text()='Clothing'])[1]").click();
      Thread.sleep(500);
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[@href='/womens-clothing-coats/'])[2]")));
      driver.findElementByXPath("(//a[@href='/womens-clothing-coats/'])[2]").click();
      Thread.sleep(1000);
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@class='uc-btn uc-btn-primary']")));
   //   driver.findElementByXPath("//button[contains(text(),'That’s OK')]").click();
      driver.findElementByXPath("//button[@class='uc-btn uc-btn-primary']").click();

//			5) Choose Material as cotton (100%) and Length as thigh-length
      driver.findElementByXPath("//span[@data-label='Material']//span[1]").click();
      driver.findElementByXPath("//span[text()='cotton (100%)']").click();
      driver.findElementByXPath("//button[text()='Save']").click();
      Thread.sleep(1000);
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@data-label='Length']//span[1]")));
      driver.findElementByXPath("//span[@data-label='Length']//span[1]").click();
      driver.findElementByXPath("//span[text()='thigh-length']").click();
      driver.findElementByXPath("//button[text()='Save']").click();
//			6) Click on Q/S designed by MANTEL - Parka coat
      Thread.sleep(1000);
      wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//img[@alt='MANTEL - Parka - navy']")));
      driver.findElementByXPath("//img[@alt='MANTEL - Parka - navy']").click();
      Thread.sleep(1000);
//			7) Check the availability for Color as Olive and Size as 'M'
      driver.findElementByXPath("(//img[@alt='olive'])[2]").click();
      driver.findElementByXPath("//button[@id='picker-trigger']//span[1]").click();
      driver.findElementByXPath("(//li[@role='option']//div)[5]").click();
  String availability = driver.findElementByXPath("//h2[contains(@class,'A95iT1 pDVUjz')]").getText();
  System.out.println("Avaialability of stock"+availability);
//			8) If the previous preference is not available, check  availability for Color Navy and Size 'M'
  if(availability.equalsIgnoreCase("Out of stock"))
  {
  driver.findElementByXPath("(//img[@alt='navy'])[2]").click();
  driver.findElementByXPath("//button[@id='picker-trigger']//span[1]").click();
  driver.findElementByXPath("(//li[@role='option']//div)[5]").click();
  String free=driver.findElementByXPath("(//span[text()='Free'])[1]").getText();
  System.out.println("The standard  delivery is"+free);
//	9) Add to bag only if Standard Delivery is free

        if(free.equalsIgnoreCase("Free"))
        {   driver.findElementByXPath("//span[text()='Add to bag']").click(); 
//		10) Mouse over on Your Bag and Click on "Go to Bag"
WebElement bag=driver.findElementByXPath("//a[@href='/cart/']");
action.moveToElement(bag).build().perform();
      driver.findElementByXPath("//div[text()='Go to bag']").click();
//		11) Capture the Estimated Deliver Date and print

      String estddate=driver.findElementByXPath("(//span[contains(@class,'z-2-text z-2-text-body')])[1]").getText();
      System.out.println("The estimated delivery date and time is"+estddate);
        }
  }
  
//			12) Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print
  WebElement freedelret=driver.findElementByXPath("(//a[@name='“headbanner.about.us\"'])[1]");
  action.moveToElement(freedelret).build().perform();
  String tooltiptxt= driver.findElementByXPath("(//span[@class='z-navicat-header-uspBar_message-split_styled'])[2]").getAttribute("title");
  System.out.println("The tooltip text is"+tooltiptxt);
//			13) Click on FREE DELIVERY & RETURNS
  freedelret.click();
//			14) Click on Start chat in the Start chat and go to the new window 
  Thread.sleep(3000);

//	14) Click on Start chat in the Start chat and go to the new window
if (driver.findElementByXPath("//span[text()='Start chat']").isEnabled() == true) {
	driver.findElementByXPath("//span[text()='Start chat']").click();
} else {
	System.out.println("The chat box not enabled");
}
	Set<String> windowHandles = driver.getWindowHandles();
	
	List<String> lstwindowHandles = new ArrayList(windowHandles);
	
	driver.switchTo().window(lstwindowHandles.get(1));
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//  wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[contains(text(),'Start chat')]")));
//  driver.findElementByXPath("//span[contains(text(),'Start chat')]").click();
//  Set<String> winset=driver.getWindowHandles();
//  List<String> winlist=new ArrayList<String> (winset);
//  driver.switchTo().window(winlist.get(1));
////	15) Enter you first name and a dummy email and click Start Chat

  driver.findElementById("prechat_customer_name_id").sendKeys("Anitha",Keys.ENTER);
  driver.findElementById("prechat_customer_email_id").sendKeys("anitha@anitha.com");
  driver.findElementById("prechat_submit").click();
  Thread.sleep(3000);
//	16) Type Hi, click Send and print thr reply message and close the chat window.

  driver.findElementById("liveAgentChatTextArea").sendKeys("Hi");
	driver.findElementByXPath("//button[text()='Send']").click();
Thread.sleep(500);
  String replymssg=driver.findElementByXPath("(//span[@class='messageText'])[4]").getText();
  System.out.println("the reply message is"+replymssg);
  

	}

}
