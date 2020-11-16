package SeleniumCoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestNaukri {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Launching Chromebrowser
		 System.setProperty("webdriver.chrome.driver",
				 "/Users/kumanananitha/eclipse-workspace/Selenium/drivers/chromedriver");
		 System.setProperty("webdriver.chrome.silentOutput", "true");
	  //To disable notifications
	 	 ChromeDriver driver;
		 ChromeOptions options= new ChromeOptions();
		 options.addArguments("--disable-notifications");
		 //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			DesiredCapabilities dc = new DesiredCapabilities();

	 options.addArguments("--incognito");
		 dc.setCapability(ChromeOptions.CAPABILITY, options);
	    driver=new ChromeDriver(options);	 
	//Maximize the browser
		 driver.manage().window().maximize();
	//Load the url
		 driver.get("https://www.naukri.com/");
	//Implicitly wait for 5 milliseconds
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


		
		 
		 Set<String> winSet = driver.getWindowHandles();
		 List<String> winLis=new ArrayList<String>(winSet);
		 driver.switchTo().window(winLis.get(1));
		 driver.manage().window().maximize();
		
		 Thread.sleep(2000);
		 
		 
		 String name1 = driver.findElementByXPath("//img[1]").getAttribute("alt");
		 System.out.println("Company name1 = "+name1);
		 Thread.sleep(1000);
		 driver.close();
		 Thread.sleep(2000);
		 Set<String> winSet1 = driver.getWindowHandles();
		 List<String> winLis1=new ArrayList<String>(winSet1);
		 driver.switchTo().window(winLis1.get(1));
		 driver.manage().window().maximize();
		
		
		
		 
		 String name2 = driver.findElementByXPath("//img[1]").getAttribute("alt");
		 System.out.println("Company name2 = "+name2);
		 driver.close();
		 Thread.sleep(2000);
		 
		 
		 Set<String> winSet2 = driver.getWindowHandles();
		 List<String> winLis2=new ArrayList<String>(winSet2);
		 driver.switchTo().window(winLis2.get(0));
		 
		 WebElement fileupload = driver.findElementById("file_upload");
		 fileupload.sendKeys("/Users/kumanananitha/Downloads/Anitha.jpeg");
		 Thread.sleep(1000);
		 String msg =driver.findElementByXPath("//div[@class='error-header-desc error']").getText();
		 System.out.println("The error message is  :" +msg);
		 driver.close();
		 
		 

	}

}
