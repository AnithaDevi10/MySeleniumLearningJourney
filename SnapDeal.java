package Javacoding;

import java.util.ArrayList;
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

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
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
		driver.get("https://www.snapdeal.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 30);
      JavascriptExecutor js =  (JavascriptExecutor) driver;
Actions action=new Actions(driver);
//		    1) Go to https://www.snapdeal.com/
//			‎2) Mouse over on Toys, Kids' Fashion & more and click on Toys
      WebElement toys=driver.findElementByXPath("//span[text()=\"Toys, Kids' Fashion & more\"]");
      action.moveToElement(toys).build().perform();
     // Thread.sleep(1000);
      driver.findElementByXPath("//span[text()='Educational Toys']").click();
//			3) Click Educational Toys in Toys & Games
//			‎4) Click the Customer Rating 4 star and Up 
      driver.findElementByXPath("//label[@for='avgRating-4.0']").click();
//			5) Click the offer as 40-50
      Thread.sleep(1000);
  wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[@for='discount-40%20-%2050']")));
  driver.findElementByXPath("//label[@for='discount-40%20-%2050']").click();
    //  driver.findElementByXPath("(//input[@checked='checked']/following-sibling::label)[1]").click();
//			6) Check the availability for the pincode
     // driver.findElementById("pincode-edit").click();
      driver.findElementByXPath("//input[@placeholder='Enter your pincode']").sendKeys("600043",Keys.TAB);
     // driver.findElementByClassName("pincode-check").click();
      driver.findElementByXPath("//button[text()='Check']").click();
//			7) Click the Quick View of the first product 
      Thread.sleep(500);
     // wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//div[contains(@class,'center quick-view-bar')])[1]")));
     // driver.findElementByXPath("(//img[@class='product-image wooble'])[1]").click();
 //WebElement quickv=driver.findElementByXPath("(//div[contains(@class,'center quick-view-bar')])[1]");
//		8) Click on View Details

      WebElement quickv=driver.findElementByXPath("(//div[contains(text(),'Quick View')])[1]");
      js.executeScript("arguments[0].click()",quickv);
// action.moveToElement(quickv).build().perform();
// action.moveToElement(quickv).click();
      // driver.findElementByLinkText("view details").click();
    Thread.sleep(500);
    wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[contains(@class,'btn btn-theme-secondary')])[1]")));   
       driver.findElementByXPath("(//a[contains(@class,'btn btn-theme-secondary')])[1]").click();
//			8) Click on View Details
//			9) Capture the Price of the Product and Delivery Charge
       String pr=driver.findElementByClassName("payBlkBig").getText();
       System.out.println(pr);
       String pri=pr.replaceAll("\\D","");
       int price=Integer.parseInt(pri);
       System.out.println(price);
       String del=driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
       System.out.println("the delivery chr"+del);
       String del1=del.replaceAll("\\D","");
       int delchg=Integer.parseInt(del1);
       System.out.println("the cleansed del ch"+delchg);
       int total=price+delchg;
       System.out.println("the price of toy"+total);
driver.findElementByXPath("//span[text()='add to cart']").click();
//			10) Validate the You Pay amount matches the sum of (price+deliver charge)
       
//			11) Search for Sanitizer
driver.findElementById("inputValEnter").sendKeys("Sanitizer",Keys.ENTER);
//			12) Click on Product "BioAyurveda Neem Power Hand Sanitizer"
driver.findElementByXPath("//p[@title='BioAyurveda Neem Power  Hand Sanitizer 500 mL Pack of 1']").click();
//			13) Capture the Price and Delivery Charge
Set<String> win=driver.getWindowHandles();
ArrayList<String> winlist=new ArrayList <String>(win);
driver.switchTo().window(winlist.get(1));
String sa_pr=driver.findElementByClassName("payBlkBig").getText();
String sa_pr1=sa_pr.replaceAll("\\D","");
int sa_price=Integer.parseInt(sa_pr1);
System.out.println("The sanitizer price"+sa_price);
String sa_dc=driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
String sa_dc1=sa_dc.replaceAll("\\D","");
int sa_delch=Integer.parseInt(sa_dc1);
System.out.println("the del ch of sanitizer"+sa_dc1);
int sa_total=sa_price+sa_delch;
System.out.println("the sanitizer total"+sa_total);
//			14) Click on Add to Cart
Thread.sleep(1000);
driver.findElementByXPath("(//span[text()='ADD TO CART'])[1]").click();
//			15) Click on Cart 
Thread.sleep(2000);
wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByClassName("cartTextSpan")));
driver.findElementByClassName("cartTextSpan").click();
//			16) Validate the Proceed to Pay matches the total amount of both the products
int grandtotal=total+sa_total;
System.out.println("The total value of cart is"+grandtotal);
String button=driver.findElementByXPath("//input[@type='button']").getAttribute("value");
System.out.println(button);
String button1=button.replaceAll("\\D","");
int totalamt=Integer.parseInt(button1);
System.out.println("Proceed to pay match value"+totalamt);
if(totalamt==grandtotal)
	System.out.println("The total matches");
else System.out.println("the total didnot match");
//			17) Close all the windows
//driver.close();

driver.quit();		

	}

}
