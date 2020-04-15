package Javacoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Myntra {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
System.setProperty("webdriver.chrome.driver", "/Users/kumanananitha/eclipse-workspace/Selenium/drivers/chromedriver");
				//launching chromebrowser
ChromeOptions options=new ChromeOptions();
options.addArguments("--disable-notification");
ChromeDriver driver= new ChromeDriver(options);
				//to load URL
driver.get("https://www.myntra.com/");
				//to maximise the browser
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
//1.Mouse over on WOMEN(Actions-->moveToElement)
WebElement mouseHover = driver.findElementByXPath("//a[text()='Women']");
Actions builder=new Actions(driver);
builder.moveToElement(mouseHover).perform();
//2.click jackets and coats
 driver.findElementByXPath("//a[text()='Jackets & Coats']").click();
 
 //3.Find the total count of item (top) -> getText -> String

 String str = driver.findElementByClassName("title-count").getText();
 
 String text = str.replaceAll("\\D","") ;
int totalcount=Integer.parseInt(text) ;
//4.Validate the sum of categories count matches
String str1=driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
String text1 = str1.replaceAll("\\D","") ;

int Jacketcount=Integer.parseInt(text1);

String str2=driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
String text2 = str2.replaceAll("\\D","") ;
int Coatcount=Integer.parseInt(text2);

int Totalclothcount= Jacketcount+Coatcount;

if(totalcount==Totalclothcount)
	System.out.println("the count matches");
else
	System.out.println("the count doesnot match");
//6. Check Coats

WebElement radiobut=driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]");
radiobut.click();
//System.out.println(radiobut.isSelected());
//7.click brandmore
driver.findElementByClassName("brand-more").click();
//8.type mango and click checkbox
driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("MANGO");
//label[@class=' common-customCheckbox']/div
//click checkbox
driver.findElementByXPath("//label[@class=' common-customCheckbox']/div").click();
//9.close the popup
driver.findElementByXPath("//div[@class='FilterDirectory-titleBar']/span").click();
//10.Confirm all the Coats are of brand MANGO
//findElements (brand) -> List<WebElement> 
//foreach -> getText of each brand 
//compare > if(condition
Thread.sleep(10000);
List<WebElement> brandList=driver.findElementsByClassName("product-brand");
int counter=0;
for(WebElement eachBrand:brandList)
{ 

String brand=eachBrand.getText();
//System.out.println("brands name"+brand);
	counter++;
}
System.out.println(counter);

//11) Sort by Better Discount
Thread.sleep(5000);
WebDriverWait wait = new WebDriverWait(driver,30);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sort-sortBy")));
Actions action2=new Actions(driver);
action2.moveToElement(driver.findElementByClassName("sort-sortBy")).perform();
driver.findElementByXPath("//label[text()='Better Discount']").click();
//12)Find the price of first displayed item
//findElements (price) -> List<WebElement> 
//get(0) -> WebElement -> getText -> String -> int
Thread.sleep(5000);
List<WebElement> pricelist = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
String  price=pricelist.get(0).getText().replaceAll("\\D", "");
System.out.println("The price of first displayed item "+price);
Thread.sleep(5000);
//13.13) Mouse over on size of the first item
Actions action1 = new Actions(driver);
action1. moveToElement(driver.findElementByXPath("(//div[@class='product-productMetaInfo'])[1]")).perform();
//action1. moveToElement(driver.findElementByXPath("(//h4[@class='product-sizes'])[1]")).perform();


//14) Click on WishList Now
//driver.findElement(By.xpath("//span[text()='wishlist now']")).click();

Thread.sleep(5000);
driver.findElementByXPath("(//div[@class='product-actions product-prelaunchActions']/span)[1]").click();

driver.close();
}

}
