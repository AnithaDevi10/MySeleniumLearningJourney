package Javacoding;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HpPaviliondispop {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//1) Go to https://store.hp.com/in-en/
		//to disable the rendering message becoz of new chrome installation
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		System.setProperty("webdriver.chrome.driver", "/Users/kumanananitha/eclipse-workspace/Selenium/drivers/chromedriver");
		//launching chromebrowser by disabling notifications
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notification");
		//to disable all types of popups coming up when running the code
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
//merge is to combine disable notifications and unexpected popups up
		options.merge(cap);
	
		ChromeDriver driver= new ChromeDriver(options);
				//to load URL
		driver.get("https://store.hp.com/in-en/");
				//to maximise the browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		Thread.sleep(3000);
		
	//	2) Mouse over on Laptops menu and click on Pavilion
		Actions builder=new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("(//span[text()='Laptops'])[1]")).perform();
		
		driver.findElementByXPath("(//span[text()='Pavilion'])[1]").click();
		
//3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0, 250)");
		
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		js.executeScript("window.scrollBy(0, 280)");
		
		//Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Intel Core i7']/parent::a/input")));
		//driver.findElementByXPath("//input[@class='product-filter-checkbox'])[2]").click();
		//driver.findElementByXPath("//a/span[text()='Intel Core i7']").click();
		driver.findElementByXPath("//span[text()='Intel Core i7']/parent::a/input").click();
		
//4) Hard Drive Capacity -->More than 1TB
Thread.sleep(3000);
driver.findElementByXPath("//a/span[text()='More than 1 TB']").click();
//5) Select Sort By: Price: Low to High
Thread.sleep(3000);
//js.executeScript("window.scrollBy(0, 600)");

Select dropdown=new Select(driver.findElementByXPath("(//select[@id='sorter'])[1]"));
dropdown.selectByValue("price_asc");
//6) Print the First resulting Product Name and Price
Thread.sleep(3000);
String productname = driver.findElementByXPath("(//a[@class='product-item-link'])[1]").getText();
System.out.println("The product name is"+productname);
String price=driver.findElementByXPath("//span[@id='product-price-9580']/span").getText();
System.out.println("The product price is"+price);

//7) Click on Add to Cart
driver.findElementByXPath("//button[@data-url='https://store.hp.com/in-en/default/hp-pavilion-15-cs3008tx-8lx78pa.html']/span").click();
//close the popup (Don't miss out)
		driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();

//8) Click on Shopping Cart icon --> Click on View and Edit Cart
driver.findElementByXPath("//a[@title='Shopping Cart']").click();
driver.findElementByXPath("//a/span[text()='View and edit cart']").click();

//9) Check the Shipping Option --> Check availability at Pincode
//driver.findElementByXPath("//strong[text()='Shipping Option']").click();
//driver.findElementByXPath("//div/input[@placeholder='Pincode']").click();
driver.findElementById("standard_delivery").click();
driver.findElementByXPath("//div/input[@placeholder='Pincode']").sendKeys("600128");
driver.findElementByXPath("//button[text()='check']").click();
//u have to inspect the element of instock
wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@class='available']"))).click();
System.out.println("The delivery of status is :: "+driver.findElementByXPath("//span[@class='available']").getText());

//10) Verify the order Total against the product price
String subtotal = driver.findElementByXPath("//span[@data-th='Subtotal']").getText();
String st=subtotal.replaceAll("\\D","");
//int st1=Integer.parseInt(st);
String ordertotal = driver.findElementByXPath("//td[@data-th='Order Total']/strong").getText();
String ot=subtotal.replaceAll("\\D","");
//int ot1=Integer.parseInt(ot);

//11) Proceed to Checkout if Order Total and Product Price matches
if(st.equals(ot))	
{
	System.out.println("The order total and product price matches");
	//Thread.sleep(3000);
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@id='sendIsCAC'])[1]")));
	//js.executeScript("window.scrollBy(0, 300)");
	//driver.findElement(By.xpath("(//button[@id='sendIsCAC'])[1]")).click();
	driver.findElementByXPath("(//button/span[text()='Proceed to Checkout'])[1]").click();
}
else System.out.println("The product price doesnot match");

//12) Click on Place Order
driver.findElementByXPath("//button/span[text()='Place Order']").click();

//13) Capture the Error message and Print
String errormsg=driver.findElementByXPath("//div/span[text()='Please specify a payment method.']").getText();
System.out.println("The error message is"+errormsg);
//14) Close Browser
driver.close();
	}
}
