package SeleniumCoding;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class TestAzure {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		   1) Go to https://azure.microsoft.com/en-in/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", 
				"/Users/kumanananitha/eclipse-workspace/Selenium/drivers/chromedriver");
		// disable the notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		// Launching Chrome Browser
		ChromeDriver driver = new ChromeDriver(options);
		// To Load the url
		driver.get("https://azure.microsoft.com/en-in/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
      JavascriptExecutor js =  (JavascriptExecutor) driver;
       Actions action=new Actions(driver);
			
//			2) Click on Pricing
       driver.findElementById("navigation-pricing").click();
       Thread.sleep(1000);
//			3) Click on Pricing Calculator
       //driver.findElementByLinkText("Pricing calculator").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Azure pricing calculator']")));
		driver.findElementByXPath("//span[text()='Azure pricing calculator']").click();

       Thread.sleep(3000);
//			4) Click on Containers
       WebElement containers=driver.findElementByXPath("//button[@data-event-property='containers']");
       wait.until(ExpectedConditions.elementToBeClickable(containers));
       
       js.executeScript("arguments[0].click()",containers);
    	
       
//			5) Select Container Instances
       driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();
//			6) Click on Container Instance Added View
       driver.findElementById("new-module-loc").click();
//			7) Select Region as "South India"
   WebElement region=driver.findElementByXPath("(//select[@name='region'])[1]");
   Select dropdown=new Select(region);
   dropdown.selectByVisibleText("South India");
//			8) Set the Duration as 180000 seconds
   WebElement duration=driver.findElementByXPath("(//input[@aria-label='Seconds'])[1]");
   duration.click();
   duration.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END),"180000");
   //keys.chord(Keys.CONTROL,"a"),"seonds value"

       
       // //input[@aria-label='Seconds']
       
//			9) Select the Memory as 4GB
   WebElement memory=driver.findElementByXPath("(//select[@class='select'])[3]");
   Select memlist=new Select(memory);
   memlist.selectByVisibleText("4 GB");
//			10) Enable SHOW DEV/TEST PRICING
   driver.findElementById("devtest-toggler").click();
//			11) Select Indian Rupee  as currency
   WebElement currency=driver.findElementByXPath("//select[@class='select currency-dropdown']");
   Select currencylist=new Select(currency);
   currencylist.selectByVisibleText("Indian Rupee (₹)");
   
//			12) Print the Estimated monthly price
   ////section[@id='azure-calculator']/div[1]/div[2]/div[2]/div[1]/div[1]/section[1]/div[6]/div[1]/div[2]/div[2]/span[1]/span[1]
   String estmthprice=driver.findElementByXPath("(//div[@class='column large-3 text-right total']//span[@class='numeric'])[2]").getText();
System.out.println(estmthprice);
   //			13) Click on Export to download the estimate as excel
driver.findElementByXPath("//button[text()='Export']").click();
//			14) Verify the downloded file in the local folder
File f = new File("Macintosh HD/Users/kumanananitha/Downloads/ExportedEstimate(*).xlsx");
if(f.exists() ) { 
    // do something
	System.out.println("File exists");
} 
else { System.out.println("file do not exist"); }
//			15) Navigate to Example Scenarios and Select CI/CD for Containers
Thread.sleep(2000);
//WebElement example_scenary=driver.findElementByLinkText("Example Scenarios");
WebElement example_scenary=driver.findElementByXPath("//a[@href='solution-architectures-picker-panel']");

wait.until(ExpectedConditions.elementToBeClickable(example_scenary));
//example_scenary.click();
js.executeScript("arguments[0].click()",example_scenary);
Thread.sleep(1000);
WebElement CICDcontain=driver.findElementByXPath("//span[text()='CI/CD for Containers']");
wait.until(ExpectedConditions.elementToBeClickable(CICDcontain));
//CICDcontain.click();
js.executeScript("arguments[0].click()",CICDcontain);

//			16) Click Add to Estimate
WebElement addtoest=driver.findElementByXPath("//button[text()='Add to estimate']");
wait.until(ExpectedConditions.elementToBeClickable(addtoest));
//addtoest.click();
js.executeScript("arguments[0].click()",addtoest);

Thread.sleep(3000);
//			17) Change the Currency as Indian Rupee
WebElement currlist=driver.findElementByXPath("//select[@class='select currency-dropdown']");
Select clist=new Select(currlist);
clist.selectByVisibleText("Indian Rupee (₹)");
//			18) Enable SHOW DEV/TEST PRICING
driver.findElementById("devtest-toggler").click();
Thread.sleep(500);
//print the estimate
String estimated=driver.findElementByXPath("(//div[@class='column large-3 text-right total']//span[@class='numeric'])[2]").getText();
System.out.println(estimated);
//			19) Export the Estimate
driver.findElementByXPath("//button[text()='Export']").click();
//			20) Verify the downloded file in the local folder
File f1 = new File("Macintosh HD/Users/kumanananitha/Downloads/ExportedEstimate(*).xlsx");
if(f1.exists() ) { 
    // do something
	System.out.println("File exists");
} 
else { System.out.println("file do not exist"); }

		}

}
