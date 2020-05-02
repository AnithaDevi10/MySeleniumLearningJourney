package Javacoding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
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

public class Shiksha {

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
		driver.get("https://studyabroad.shiksha.com/");
		// To maximize the browser
		driver.manage().window().maximize();
		// implicitly wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
      JavascriptExecutor js =  (JavascriptExecutor) driver;
       Actions action=new Actions(driver);

//		    1) Go to https://studyabroad.shiksha.com/
//			2) Mouse over on Colleges and click MS in Computer Science &Engg under MS Colleges
       WebElement colleges=driver.findElementByXPath("(//label[contains(text(),'Colleges')])[2]");
       WebElement MScompsci=driver.findElementByXPath("//a[text()='MS in Computer Science &Engg']");
       action.moveToElement(colleges).build().perform();
      // action.moveToElement(MScompsci).click();
       MScompsci.click();
       Thread.sleep(1000);
       
//			3) Select GRE under Exam Accepted and Score 300 & Below 
       WebElement GRE=driver.findElementByXPath("//label[@for='exam-0']//span[1]");
       js.executeScript("arguments[0].click()", GRE);
       Thread.sleep(1000);
       WebElement score300=driver.findElementByXPath("(//select[@class='score-select-field'])[1]");
       WebElement match=driver.findElementByClassName("score-select-field");
       Select select=new Select(match);
       select.selectByVisibleText("284 & below");
       Thread.sleep(1000);
//			4) Max 10 Lakhs under 1st year Total fees, USA under countries
       WebElement max10l=driver.findElementByXPath("//label/p[text()='Max 10 Lakhs']");
       max10l.click();
       Thread.sleep(1000);
       WebElement usa=driver.findElementByXPath("//a[text()='USA']/ancestor::label/span");
      // wait.until(ExpectedConditions.visibilityOfElementLocated("//a[text()='USA']/ancestor::label/span"));
       js.executeScript("arguments[0].click()", usa);
       Thread.sleep(1000);
//			5) Select Sort By: Low to high 1st year total fees
       WebElement sort=driver.findElementById("categorySorter");
       Select select1=new Select(sort);
       select1.selectByValue("fees_ASC");
       Thread.sleep(1000);
//			6) Click Add to compare of the College having least fees with Public University, Scholarship and Accomadation
        //(1)  first find the number of colleges count
       String collcount=driver.findElementByXPath("//span[@id='foundCoursesCount']").getText();
       System.out.println("the colleges count"+collcount);
       int noofcol=Integer.parseInt(collcount.replaceAll("\\D",""));
       System.out.println("The number of colleges"+noofcol);
       //(2)get the colleges having 3 conditions tick in the map.So the map will contain only list of colleges
       // having 3 conditions ticked(public university,scholarship,accomadation
       //hash map doesnot maintain insertion order
       Map<String,Float> maps=new HashMap<String,Float> ();
       for(int i=1;i<=noofcol;i++)
             { String pubuniv=driver.findElementByXPath("(//p[text()='Public university']/span)["+i+"]").getAttribute("class");
               System.out.println("the public univ tick is"+pubuniv +"and i is"+ i);
               String  scholar=driver.findElementByXPath("(//p[text()='Scholarship']/span)["+i+"]").getAttribute("class");
               System.out.println("the scholar is"+scholar+"and i is"+i);
               String accomodate=driver.findElementByXPath("(//p[text()='Accommodation']/span)["+i+"]").getAttribute("class");
               System.out.println("the accomodate is"+accomodate+"and  i is "+i);
              if(pubuniv.equalsIgnoreCase("tick-mark")&& scholar.equalsIgnoreCase("tick-mark")&& accomodate.equalsIgnoreCase("tick-mark"))
             {
    	        String annualfees= driver.findElementByXPath("(//div[@class='detail-col flLt']//p[contains(text(),'Rs')])["+i+"]").getText(); 
    	        System.out.println("the annual fees of college"+ annualfees+ " and the number is"+ i );
    	        float firstyrfees=Float.parseFloat(annualfees.replaceAll("[^0-9.]",""));
    	        maps.put(""+i,firstyrfees);
              } 
             } 
       //(3) add the firstyearfees value from map (map>value) to list,so as to apply sorting in list
    	  List <Float> sortedfees=new ArrayList<Float> ();
    	  for(Entry<String,Float> eachEntry:maps.entrySet())
    	       { sortedfees.add(eachEntry.getValue());
    	         System.out.println("printing map with college with 3 ticks");
    	         System.out.println(eachEntry.getKey()+"--->"+eachEntry.getValue());
    	        }
    	  //(4)after filling fees value in sortedfees list,apply collections.sort
    	  Collections.sort(sortedfees);
    	 Float lowestfees=sortedfees.get(0); 
    	  //(5) now take the lowest fees and add the corresponding college (add to compare())
    	 //using key which is to be got from map
    	 for (Entry <String,Float> eachEntry:maps.entrySet())
    	      { 
		      if(eachEntry.getValue().equals(lowestfees))
		         { String   college=eachEntry.getKey();
		           System.out.println("the college number is"+college);
		           //   (//p[text()='Add to compare'])[1]
    	           driver.findElementByXPath("(//p[contains(text(),'Add to compare')])["+college+"]").click();
			       break;
			       }
    	      }
       
       System.out.println("The lowest annual fees is"+lowestfees);
 	  Thread.sleep(2000);
    		   
    //			7) Select the first college under Compare with similar colleges 
       driver.findElementByXPath("(//span[@class='add-tag'])[1]").click();
       System.out.println("selected the first college to compare");
       Thread.sleep(1000);
//			8) Click on Compare College>
         driver.findElementByXPath("//strong[text()='Compare Colleges >']").click();
         System.out.println("clicked on compare colleges");
         Thread.sleep(1000);
//			9) Select When to Study as 2021
         driver.findElementByXPath("(//span[@class='common-sprite'])[2]").click();
         Thread.sleep(500);
//			11) Select Level of Study as Masters
         driver.findElementByXPath("(//span[@class='common-sprite'])[5]").click();
          Thread.sleep(500);
//			10) Select Preferred Countries as USA
         driver.findElementByXPath("(//div[contains(@class,'sp-frm selectCountryField')])[1]").click();
         Thread.sleep(500);
         wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[contains(@for,'USA')]")));
         driver.findElementByXPath("//label[contains(@for,'USA')]").click();
         Thread.sleep(500);
         wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByClassName("ok-btn")));
         
         driver.findElementByClassName("ok-btn").click();
         Thread.sleep(500);
//			12) Select Preferred Course as MS
         driver.findElementByXPath("(//div[contains(@class,'sp-frm selectField')])[1]").click();
         Thread.sleep(500);
         driver.findElementByXPath("(//li[@class='lr-row'])[2]").click();
         Thread.sleep(500);
//			13) Select Specialization as "Computer Science & Engineering"
         driver.findElementByXPath("//div[text()='All specializations']").click();
         Thread.sleep(1000);
        driver.findElementByXPath("//li[text()='Computer Science & Engineering']").click();
        
//			14) Click on Sign Up
         driver.findElementById("signup").click();
//			15) Print all the warning messages displayed on the screen for missed mandatory fields
         List <WebElement> errmessages=driver.findElementsByXPath("//div[@class='helper-text' and starts-with(text(),'Please')]");
           for( WebElement errmsg:errmessages)
        	   
            { 
        	   System.out.println("The error messages  are"+errmsg.getText());
             }
         
           //16.close the browser
         }

	}
	  
	
	
