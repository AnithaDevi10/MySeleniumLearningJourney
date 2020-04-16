package Javacoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Nykaa {
	public static void main(String[] args) throws InterruptedException
	{
System.setProperty("webdriver.chrome.driver", "/Users/kumanananitha/eclipse-workspace/Selenium/drivers/chromedriver");
//launching chromebrowser by disabling notifications
ChromeOptions options=new ChromeOptions();
options.addArguments("--disable-notification");
ChromeDriver driver= new ChromeDriver(options);
		//to load URL
driver.get("https://www.nykaa.com/");
		//to maximise the browser
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
//2) Mouseover on Brands and Mouseover on Popular
Actions builder=new Actions(driver);
WebElement ele=driver.findElementByXPath("//a[text()='brands']");
WebElement ele1=driver.findElementByXPath("//a[text()='Popular']");

builder.moveToElement(ele).moveToElement(ele1).perform();
//3) Click L'Oreal Paris
driver.findElementByXPath("(//li[@class='brand-logo menu-links'])[5]").click();



//4) Go to the newly opened window and check the title contains L'Oreal Paris
//write window handle code
//window handle
Set<String> winSet=driver.getWindowHandles();
List<String> winList=new ArrayList<String>(winSet);
//Switching the context to the second window
driver.switchTo().window(winList.get(1));
if(driver.getTitle().contains("L'Oreal Paris"))
	System.out.println("The new window has the title");
else System.out.println("The window doesnot hav title");

//5) Click sort By and select customer top rated
 
driver.findElementByXPath("//span[text()='Sort By : ']").click();
//Customer top rated
driver.findElementByXPath("//span[text()='customer top rated']").click();

//6) Click Category and click Shampoo
Thread.sleep(5000);
driver.findElementByXPath("(//div[@class='filter-sidebar__filter-title pull-left'])[1]").click();
driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined']/span)[1]").click();
//7) check whether the Filter is applied with Shampoo
driver.findElementByXPath("(//div[@class='filter-applied']/following::li)[1]");
//8) Click on L'Oreal Paris Colour Protect Shampoo
driver.findElementByXPath("//img[@alt=\"L'Oreal Paris Colour Protect Shampoo\"]").click();
//9) GO to the new window and select size as 175ml
//now get the window handles too
Set<String> winSet1=driver.getWindowHandles();
List<String> winList1=new ArrayList<String>(winSet1);
driver.switchTo().window(winList1.get(2));
String title=driver.getTitle();
System.out.println(title);
Thread.sleep(2000);
driver.findElementByXPath("//span[text()='175ml']").click();
//driver.findElementByXPath("(//span[@class='size-pallets'])[2]").click();
Thread.sleep(2000);
//10) Print the MRP of the product
String shampooprice=driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
String price=shampooprice.replaceAll("\\D","");
int pricevalue=Integer.parseInt(price);
System.out.println(pricevalue);
Thread.sleep(2000);
//11) Click on ADD to BAG
driver.findElementByXPath("//div[@class='pdp-tile-add-to-cart-wrapper']/button").click();
Thread.sleep(2000);
//12) Go to Shopping Bag 
driver.findElementByXPath("//div[@class='AddBagIcon']").click();
Thread.sleep(2000);
//13) Print the Grand Total amount
String pr1=driver.findElementByXPath("//div[@class='value medium-strong']").getText();
//String pr2=pr1.replaceAll("\\D","");
//int prval=Integer.parseInt(pr2);
System.out.println("The grand total amount is"+pr1);
//14) Click Proceed
driver.findElementByXPath("//span[text()='Proceed']").click();

//15) Click on Continue as Guest
driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();

//16) Print the warning message (delay in shipment)
String warningmsg=driver.findElementByXPath("//div[text()='Please expect delay in shipments because of the national lockdown']").getText();
System.out.println(warningmsg);
//17) Close all windows
driver.quit();
	}
}
