package SeleniumCoding;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestHonda {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//1) Go to https://www.honda2wheelersindia.com/
		//--------------------------------------------
		
		System.setProperty("webdriver.chrome.driver", 
				"/Users/kumanananitha/eclipse-workspace/Selenium/drivers/chromedriver");
		/*ChromeOptions options  = new ChromeOptions();
	    
	    options.addArguments("--disable-popup-blocking");
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(ChromeOptions.CAPABILITY, options);*/
	   // ChromeDriver  driver = new ChromeDriver(capabilities);
		
		ChromeDriver driver = new ChromeDriver();
		
		
		driver.get("https://www.honda2wheelersindia.com/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//close pop up
		//----------
		
		driver.findElementByXPath("//button[@class='close']").click();
		
		
		//2) Click on scooters and click dio
		//-----------------------------------
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("link_Scooter")));
		
		driver.findElementById("link_Scooter").click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='owl-wrapper-outer']//a[contains(@href,'dio')]")));
		
		driver.findElementByXPath("//div[@class='owl-wrapper-outer']//a[contains(@href,'dio')]").click();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		//3) Click on Specifications and mouseover on ENGINE
		//------------------------------------------------
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='Specifications']")));
		
		Actions action = new Actions(driver);
		
		action.moveToElement(driver.findElementByXPath("//a[text()='Specifications']")).click().build().perform();
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='ENGINE']")));
		
		
		
		action.moveToElement(driver.findElementByXPath("//a[text()='ENGINE']")).build().perform();
		
		//4) Get Displacement value
		//----------------------
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='engine part-2 axx']//span[text()='Displacement']/following-sibling::span")));
		
		String strDisplacement_Dio = driver.findElementByXPath("//div[@class='engine part-2 axx']//span[text()='Displacement']/following-sibling::span").getText();
		
		String[] arr = strDisplacement_Dio.split("cc");
		
		strDisplacement_Dio = arr[0];
		
		double dblDisplacement_Dio = Double.parseDouble(strDisplacement_Dio.trim());
		
		//5) Go to Scooters and click Activa 125
		//-----------------------------------------
		
		driver.findElementById("link_Scooter").click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='owl-wrapper-outer']//a[contains(@href,'activa125')]")));
		
		driver.findElementByXPath("//div[@class='owl-wrapper-outer']//a[contains(@href,'activa125')]").click();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//6) Click on Specifications and mouseover on ENGINE
		//--------------------------------------------------
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='Specifications']")));
		
		action.moveToElement(driver.findElementByXPath("//a[text()='Specifications']")).click().build().perform();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='ENGINE']")));
		
		action = new Actions(driver);
		
		action.moveToElement(driver.findElementByXPath("//a[text()='ENGINE']")).build().perform();
		
		
		//7) Get Displacement value
		//-------------------------
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='engine part-4 axx']//span[text()='Displacement']/following-sibling::span")));
		
		String strDisplacement_activa125 = driver.findElementByXPath("//div[@class='engine part-4 axx']//span[text()='Displacement']/following-sibling::span").getText();
		arr = strDisplacement_activa125.split("cc");
		
		strDisplacement_activa125 = arr[0];
		
		double dblDisplacementActiva125 = Double.parseDouble(strDisplacement_activa125.trim());
		
		
		//8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		//--------------------------------------------------------------------------------------------------
		
		if(dblDisplacement_Dio>dblDisplacementActiva125)
		{
			System.out.println("Dio has better Displacement");
		}
		else
		{
			System.out.println("Activa125 has better Displacement");
		}
		
		
		//9) Click FAQ from Menu 
		//---------------------
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//ul[@class='nav navbar-nav']//a[text()='FAQ']")));
		
		driver.findElementByXPath("//ul[@class='nav navbar-nav']//a[text()='FAQ']").click();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//10) Click Activa 125 BS-VI under Browse By Product
		//-----------------------------------------------------------
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[text()='Activa 125 BS-VI']")));
		
		driver.findElementByXPath("//a[text()='Activa 125 BS-VI']").click();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		//11) Click  Vehicle Price 
		//----------------------
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//ul[contains(@class,'nav nav-pills tabb-design')]//a[contains(text(),'Vehicle Price')]")));
		
		action.moveToElement(driver.findElementByXPath("//ul[contains(@class,'nav nav-pills tabb-design')]//a[contains(text(),'Vehicle Price')]")).click().build().perform();
		
		
		//12) Make sure Activa 125 BS-VI selected and click submit
		//-----------------------------------------------------------
		
		
		WebElement ele = driver.findElementById("ModelID6");
		
		Select slt = new Select(ele);
		
		String selectedValue = slt.getFirstSelectedOption().getText();
		
		if(selectedValue.contains("Activa 125 BS"))
		{
			System.out.println("Activa 125 BS-VI is selected");
		}
		
		action.moveToElement(driver.findElementById("submit6")).click().build().perform();
		
		
		//13) click the price link
		//-------------------------
		
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[contains(text(),'Click here to know the price of')]")));
		
		driver.findElementByXPath("//a[contains(text(),'Click here to know the price of')]").click();
		
		
		//14)  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
		//------------------------------------------------------------------------------
		
		Set<String> windowHandles = driver.getWindowHandles();
		
		List<String> lstwindowHandles = new ArrayList(windowHandles);
		
		if(lstwindowHandles.size()==2)
		{
			
			driver.switchTo().window(lstwindowHandles.get(1));
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		
		
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementById("StateID")));
		
		WebElement ele_StateDropDown = driver.findElementById("StateID");
		
		Select slt_StateDropDown = new Select(ele_StateDropDown);
		
		slt_StateDropDown.selectByVisibleText("Tamil Nadu");
		
		WebElement ele_CityeDropDown = driver.findElementById("CityID");
		
		Select slt_CityeDropDown = new Select(ele_CityeDropDown);
		
		slt_CityeDropDown.selectByVisibleText("Chennai");
		
		
		
		//15) Click Search
		//----------------
		
		driver.findElementByXPath("//button[text()='Search']").click();
		
		
		//16) Print all the 3 models and their prices
		//-----------------------------------------
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//table[@id='gvshow']//td[2]/following-sibling::td")));
		
		Map<String,String> map_Model_Price = new LinkedHashMap();
		
		String strModel="";
		String strPrice = "";
		
		
		for(int i=1;i<=3;i++)
		{
			strModel = driver.findElementByXPath("//table[@id='gvshow']//tr["+i+"]//td[contains(text(),'ACTIVA')]").getText();
			strPrice = driver.findElementByXPath("//table[@id='gvshow']//tr["+i+"]//td[contains(text(),'ACTIVA')]/following-sibling::td").getText();
			
			map_Model_Price.put(strModel, strPrice);
			
		}
		
		System.out.println("|Model----------Price|");
		
		for(Entry<String,String> each:map_Model_Price.entrySet())
		{
			System.out.print("|"+each.getKey()+"----------"+each.getValue());
			System.out.println();
		}
		
		driver.quit();
		

	}

}
