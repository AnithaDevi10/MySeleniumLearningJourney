package Javacoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMytrip {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//1) Go to https://www.makemytrip.com/	
		System.setProperty("webdriver.chrome.driver", "/Users/kumanananitha/eclipse-workspace/Selenium/drivers/chromedriver");
		//launching chromebrowser by disabling notifications
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notification");
		ChromeDriver driver= new ChromeDriver(options);
				//to load URL
		driver.get("https://www.makemytrip.com/");
				//to maximise the browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);				
	
		WebDriverWait wait = new WebDriverWait(driver, 30);
//	2) Click Hotels
		driver.findElementByXPath("//span[text()='Hotels']").click();
//	3) Enter city as Goa, and choose Goa, India
		driver.findElementByXPath("//div[contains(@class,'hsw_inputBox selectHtlCity')]").click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']")));
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys("Goa");
//choose goa
		driver.findElementByXPath("(//div[@class='flexOne']/p[contains(text(),Goa)])[1]").click();
//	4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5
	driver.findElementByXPath("//div[@aria-label='Fri May 15 2020']").click();
	driver.findElementByXPath("//span[text()='Select Checkout Date']").click();
	driver.findElementByXPath("//div[@aria-label='Tue May 19 2020']").click();
//	5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 11).
	//Click Apply Button.
	driver.findElementByXPath("//input[@id='guest']").click();
	driver.findElementByXPath("(//div[@class='addRooomDetails']//li)[2]").click();
	driver.findElementByXPath("(//div[@class='addRooomDetails']//li)[14]").click();
	driver.findElementByXPath("//button[text()='APPLY']").click();
//	6) Click Search button
    driver.findElementByXPath("//button[text()='Search']").click();
//	7) Select locality as Baga
    //as screen is blacked out
    driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
    driver.findElementByXPath("//span[@class='checkmarkOuter']/label[text()='Baga']").click();
//	8) Select 5 start in Star Category under Select Filters
    driver.findElementByXPath("//span[@class='checkmarkOuter']/label[text()='5 Star']").click();
//	9) Click on the first resulting hotel and go to the new window
    driver.findElementByXPath("//span[text()='Acron Waterfront Resort-Member ITC Hotel Group']").click();
    Set<String> winSet=driver.getWindowHandles();
    List<String> winList=new ArrayList<String>(winSet);
//Switching the context to the second window
    driver.switchTo().window(winList.get(1));
//	10) Print the Hotel Name 
       String hotelname=driver.findElementByXPath("//h1[text()='Acron Waterfront Resort-Member ITC Hotel Group']").getText();
       System.out.println(hotelname);
//	11) Click MORE OPTIONS link and Select 3Months plan and close
    driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
    driver.findElementByXPath("//tr[2]/td[6]").click();
    driver.findElementByXPath("//span[@class='close']").click();
//	12) Click on BOOK THIS NOW
    driver.findElementByXPath("//a[text()='BOOK THIS NOW']").click();
//	13) Print the Total Payable amount
     String totalpayamount=driver.findElementByXPath("//span[@id='revpg_total_payable_amt']").getText();
     System.out.println("The total payable amount"+totalpayamount);
//have to handle sweet alert
      driver.findElementByXPath("//span[@class='close']").click();
//Alert alert = driver.switchTo().alert();
//driver.findElementByXPath("//p[@class='latoBlack font22 blackText appendBottom5']");
//alert.dismiss();
//	14) Close the browser
//close all the pages
      driver.quit();


	}

}
