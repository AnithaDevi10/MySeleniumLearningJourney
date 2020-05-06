package Javacoding;

import java.util.ArrayList;
import java.util.List;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ajio {

	public static void main(String[] args) throws InterruptedException {
		//    1)Go to https://www.ajio.com/
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
		driver.get("https://www.ajio.com/shop/sale");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
      JavascriptExecutor js =  (JavascriptExecutor) driver;
       Actions action=new Actions(driver);
     //		2) Enter Bags in the Search field and Select Bags in Women Handbags
       driver.findElementByName("searchVal").sendKeys("Bags");
       Thread.sleep(500);
       driver.findElementByXPath("(//span[text()='Women Handbags'])[1]").click();
       Thread.sleep(1000);
//			3) Click on five grid and Select SORT BY as "What's New"
       driver.findElementByClassName("five-grid").click();
       Thread.sleep(500);
       WebElement dropdown = driver.findElementByXPath("//div[@class='filter-dropdown']//select[1]");
       Select option=new Select(dropdown);
       option.selectByVisibleText("What's New");
       Thread.sleep(1000);
//			4) Enter Price Range Min as 2000 and Max as 5000
       driver.findElementByXPath("//span[text()='price']").click();
       driver.findElementById("minPrice").sendKeys("2500");
       driver.findElementById("maxPrice").sendKeys("5000");
       driver.findElementByXPath("//button[@class='rilrtl-button ic-toparw']").click();
       Thread.sleep(500);
//			5) Click on thefive-gridproduct "Puma Ferrari LS Shoulder Bag"
       driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
       Set<String> winset=driver.getWindowHandles();
       List<String> winlist=new ArrayList <String> (winset);
       driver.switchTo().window(winlist.get(1));
       
//			6) Verify the Coupon code for the price above 2690 is applicable for your product, 
		//if applicable the get the Coupon Code and Calculate the discount price for the coupon
       String coupondesc=driver.findElementByClassName("promo-desc").getText();
      
       
       String prodprice=driver.findElementByClassName("prod-sp").getText();
       int productprice=Integer.parseInt(prodprice.replaceAll("\\D",""));
       
       String afterdiscprice=driver.findElementByXPath("//div[@class='promo-discounted-price']//span[1]").getText();
       int discountprice=Integer.parseInt(afterdiscprice.replaceAll("\\D",""));
      // if(productprice>2690)
      // {
    	   float discamt=productprice-discountprice;
       
       System.out.println("the discounted amount is " +discamt);
       //}
//			7) Check the availability of the product for pincode 560043, 
		//print the expected delivery date if it is available
driver.findElementByXPath("//span[@class='edd-pincode-msg-details edd-pincode-msg-details-pointer edd-pincode-msg-details-text-color']").click();
       driver.findElementByName("pincode").sendKeys("607807");
       driver.findElementByClassName("edd-pincode-modal-submit-btn").click();
       
       WebElement successpincode=driver.findElementByClassName("edd-message-success-pincode");
       if(successpincode!=null)
       { String deldate=driver.findElementByClassName("edd-message-success-details-highlighted").getText();}
       else {System.out.println("the product not available in pincode");}
//			8) Click on Other Informations under Product Details 
		//and Print the Customer Care address, phone and email
       driver.findElementByXPath("//div[text()='Other information']").click();
       Thread.sleep(1000);
  String addremail=driver.findElementByXPath("//span[text()='Customer Care Address']/following-sibling::span[2]").getText();
     System.out.println("the addr,email,phnumber " + addremail); 
  Thread.sleep(500);
//			9) Click on ADD TO BAG and then GO TO BAG
       WebElement addtobag=driver.findElementByClassName("btn-gold");
       js.executeScript("arguments[0].click()",addtobag);
       Thread.sleep(4000);
       wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='btn-cart']")));
       ////span[@class='ic-pdp-add-cart']/following-sibling::span
       WebElement gotobag=driver.findElementByXPath("//div[@class='btn-cart']");
     if  (gotobag.isEnabled())
    	 js.executeScript("arguments[0].click()", gotobag);
    	 Thread.sleep(2000);
    	
//			10) Check the Order Total before apply coupon
       String ordertotal=driver.findElementByXPath("//span[@class='price-value bold-font']").getText();
       int ordtotal=Integer.parseInt(ordertotal.replaceAll("\\D",""));
//			11) Enter Coup.on Code and Click Apply
       driver.findElementById("couponCodeInput").sendKeys("EPIC");
       driver.findElementByXPath("//button[text()='Apply']").click();
       Thread.sleep(1000);
//			12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary 
		//and the matches the amount calculated in Product details
       String savamt=driver.findElementByXPath("(//span[@class='price-value discount-price'])[2]").getText();
       System.out.println("the savings amount "+savamt);
       String arrOfStr = savamt.substring(4); 
System.out.println(arrOfStr);
//System.out.println(arrOfStr[1]);
       float savingamt=Float.parseFloat(arrOfStr.replaceAll("[^0-9.]",""));
       System.out.println("After reg ex applied "+savingamt);
       float savingsamt=Math.round(savingamt);
       if(savingsamt==discamt)
       {System.out.println("The discount amount matches"); }
       else
    	   System.out.println("The amount doesnot match");
       Thread.sleep(1000);
//			13) Click on Delete and Delete the item from Bag
       driver.findElementByClassName("delete-btn").click();
       Thread.sleep(500);
       driver.findElementByXPath("(//div[@class='delete-btn'])[2]").click();
//			14) Close all the browsers

	}

	}

