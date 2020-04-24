package Javacoding;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pepperfry {

	public static void main(String[] args) throws InterruptedException, IOException {
		//1) Go to https://www.pepperfry.com/
			
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
		driver.get("https://www.pepperfry.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		Actions action=new Actions(driver);
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js= (JavascriptExecutor) driver;

		WebDriverWait wait=new WebDriverWait(driver,30);
//			2) Mouseover on Furniture and click Office Chairs under Chairs
		//Thread.sleep(2000);
	//wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[@class='popup-close'])[5]"))).click();	
//driver.findElementByXPath("(//a[@class='popup-close'])[5]").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='level-top'])[1]")));
		action.moveToElement(driver.findElementByXPath("(//a[@class='level-top'])[1]")).build().perform();
		System.out.println("Pointed to Furniture");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Office Chairs")));
		driver.findElementByLinkText("Office Chairs").click();
		System.out.println("Office Chairs link clicked");
				
		/*
		 * WebElement furniture=driver.findElementByXPath("(//a[@class='level-top'])[1]");
		WebElement offchairs=driver.findElementByLinkText("Office Chairs");
		builder.moveToElement(furniture).perform();
		builder.moveToElement(offchairs).click();
	*/
 		//3) click Executive Chairs
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Executive Chairs']")));
		action.moveToElement(driver.findElementByXPath("//h5[text()='Executive Chairs']")).click();
		System.out.println("executive chairs link clicked");

		
//			4) Change the minimum Height as 50 in under Dimensions
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@class='clipFilterDimensionHeightValue'])[1]")));
		driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]").clear();
		driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]").sendKeys("50",Keys.ENTER);
		System.out.println("Minium Height filter set to 50");
			
		//			5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-productname='Poise Executive Chair in Black Colour']")));
				
		driver.findElementByXPath("//a[@data-productname='Poise Executive Chair in Black Colour']").click();
		System.out.println("Poise Executive Chair in Black Colour is added to Wishlist");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='regPopUp']//a[@class='popup-close']")));
		driver.findElement(By.xpath("//div[@id='regPopUp']//a[@class='popup-close']")).click();
		
//			6) Mouseover on Homeware and Click Pressure Cookers under Cookware
		//Thread.sleep(2000);
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='menu_wrapper']//a[text()='Homeware']")));
		//WebElement homeware=driver.findElementByXPath("(//a[text()='Homeware'])[1]");
		action.moveToElement(driver.findElementByXPath("//div[@id='menu_wrapper']//a[text()='Homeware']")).perform();
		//System.out.println();
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Pressure Cookers']")));
		//WebElement pressurecook=driver.findElementByLinkText("Pressure Cookers");
		driver.findElementByXPath("//a[text()='Pressure Cookers']").click();
		System.out.println("Pressure Cookers clicked");
		
//			7) Select Prestige as Brand
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='brandsnamePrestige']")));
		
		driver.findElementByXPath("//label[@for='brandsnamePrestige']").click();
		System.out.println("Brand Filter applied as Prestige");

//			8) Select Capacity as 1-3 Ltr
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='capacity_db1_Ltr_-_3_Ltr']")));
		driver.findElementByXPath("//label[@for='capacity_db1_Ltr_-_3_Ltr']").click();
		System.out.println("Capacity filter applied as 1Ltr - 3Ltr");

//			9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
		Thread.sleep(1000);
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']")));		
		driver.findElementByXPath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']").click();
		System.out.println("Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr added to Wishlist");

//			10) Verify the number of items in Wishlist
		////div[@class='wishlist_bar']//span[1]
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='wishlist_bar']//span[1]")));	
	String numitems=driver.findElementByXPath("//div[@class='wishlist_bar']//span[1]").getText();
	System.out.println("The  number of items in wishlist"+numitems);
//			11) Navigate to Wishlist
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'pf-icon pf-icon-heart')]")));
	driver.findElementByXPath("//a[contains(@class,'pf-icon pf-icon-heart')]").click();	
System.out.println("navigating to wishlist");
//			12) Move Pressure Cooker only to Cart from Wishlist
	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='addtocart_icon'])[2]")));	
	WebElement cooker=driver.findElementByXPath("(//a[@class='addtocart_icon'])[2]");
	js.executeScript("arguments[0].click()",cooker);
	System.out.println("Move Pressure Cooker only to Cart from Wishlist");
//			13) Check for the availability for Pincode 600128
	wait.until(ExpectedConditions.elementToBeClickable(By.className("srvc_pin_text")));
	driver.findElementByClassName("srvc_pin_text").sendKeys("600128");
	driver.findElementByClassName("check_available").click();
	System.out.println("Check for the availability for Pincode 600128");
//			14) Click Proceed to Pay Securely
	Thread.sleep(500);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Proceed to pay securely')]")));
	driver.findElementByXPath("//a[contains(text(),'Proceed to pay securely')]").click();
//			15 Click Proceed to Pay
	driver.findElementByClassName("btn_green_big").click();
//			16) Capture the screenshot of the item under Order Item
	//li[@role='option']//figure[1] parent based attribute xpath for the figure
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='ORDER SUMMARY']")));
	
	driver.findElementByXPath("//span[text()='ORDER SUMMARY']").click();
//js.executeScript("arguments[0].click()", osumm);

	//WebElement screenShotEle = driver.findElement(By.xpath("//div[@class='slick-track']//li"));
	WebElement screenShotEle = driver.findElementByTagName("figure");
	File src = screenShotEle.getScreenshotAs(OutputType.FILE);
	File dest = new File("./screenshot/img.png");
	FileUtils.copyFile(src, dest);
	System.out.println("Element Screenshot captured under screenshot folder successfully. File Name : img.png");
//17) Close the browser	
    driver.close();
	}

}
