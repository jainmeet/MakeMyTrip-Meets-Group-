package login;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import login.Excel;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;


public class RoundTest extends Excel {
	
	public RoundTest(String pathWithFileName) {
		super(pathWithFileName);
		
	}

	public WebDriver driver;
	
  @Test(dataProvider = "dp")
  public void f(String no,String un, String pwd, String source, String destination) throws Exception {
	  
	  driver.manage().window().maximize();                //For maximizing window
	  
	  
	  Properties prop=new Properties();
	  prop.load(new FileInputStream("src/test/resources/settings.property"));  //xpath from setting property file
	  Excel ra= new Excel("C:\\Users\\pasad\\OneDrive\\Documents\\roundtrip.xlsx");
	 
	  //generating testng reports 
	  
	  ExtentReports roundreport= new ExtentReports();
	  roundreport.attachReporter(new ExtentHtmlReporter("roundway.html"));
	  ExtentTest t1=roundreport.createTest("TestC1");
	  ExtentTest t2=roundreport.createTest("TestC2");
	  
	  
	   driver.get(prop.getProperty("url"));
	   driver.findElement(By.xpath(prop.getProperty("Loginviaemail"))).click();            //for email part
	   driver.findElement(By.id(prop.getProperty("email"))).sendKeys(un);
	   
	   driver.findElement(By.xpath(prop.getProperty("continue"))).click();               
	   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   
	   driver.findElement(By.id(prop.getProperty("password"))).sendKeys(pwd);
	   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    
       driver.findElement(By.xpath(prop.getProperty("Login"))).click();              //for login session for first time
       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       
       driver.findElement(By.xpath(prop.getProperty("flight"))).click();             //searching for flights while going on that tab
       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       
       driver.findElement(By.xpath(prop.getProperty("roundtrip"))).click();         //selecting ways from different trip
       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       
       driver.findElement(By.xpath(prop.getProperty("from"))).click();             
       Thread.sleep(2000);
       
       driver.findElement(By.xpath(prop.getProperty("source"))).sendKeys(source);      //selecting city from source 
       Thread.sleep(2000);
        
       driver.findElement(By.xpath(prop.getProperty("sourceSelection"))).click();             
       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       
       driver.findElement(By.xpath(prop.getProperty("to"))).click();
       Thread.sleep(2000);
       
       driver.findElement(By.xpath(prop.getProperty("destination"))).sendKeys(destination);     //selecting destination of city part
       Thread.sleep(2000);
       
       driver.findElement(By.xpath(prop.getProperty("destinationSelection"))).click();
       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       
       driver.findElement(By.xpath(prop.getProperty("depart"))).click();
       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       
       driver.findElement(By.xpath(prop.getProperty("departdate"))).click();                  //calendar date for departure
       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       
       
       driver.findElement(By.xpath(prop.getProperty("returndate"))).click();                 //return date 
       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        
       
      driver.findElement(By.xpath(prop.getProperty("traveller"))).click();               //choosing no. of adults,children,infants and traveller class 
      driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       
      
      driver.findElement(By.xpath(prop.getProperty("adult"))).click();
      driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       
      driver.findElement(By.xpath(prop.getProperty("children"))).click();
      driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       
      driver.findElement(By.xpath(prop.getProperty("infants"))).click();
      driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       
      driver.findElement(By.xpath(prop.getProperty("travellerclass"))).click();   //choosing from different traveller class
      driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       
      driver.findElement(By.xpath(prop.getProperty("apply"))).click();
      driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
      
      driver.findElement(By.xpath(prop.getProperty("Search"))).click();              //Searching flights for given conditions
      Thread.sleep(2000);
      
      //using try-catch for search condition and t1 and t2 cannot be same
      
      
     try {
    	 driver.findElement(By.xpath(prop.getProperty("okaygotit"))).click();              
         Thread.sleep(2000);
     }
     catch (Exception e) {
    	 System.out.println("from and to cannot be same");
    	 ra.writeDataLogin("Sheet1", Integer.parseInt(no), 5, "from and to cant be same");
    	 
    	 t2.fail("Booking is unsuccessfull and failed");
         roundreport.flush();
         
    	 
     }
      
      //Searching for flights and fares
      driver.findElement(By.xpath(prop.getProperty("onward"))).click();              //Searching flights for onward journey
      Thread.sleep(2000);
      
      driver.findElement(By.xpath(prop.getProperty("return"))).click();              //Searching flights for return journey
      Thread.sleep(2000);
      
      driver.findElement(By.xpath(prop.getProperty("goahead"))).click();              //Searching fares for given conditions
      Thread.sleep(2000);
      
      driver.findElement(By.xpath(prop.getProperty("view"))).click();              
      Thread.sleep(1000);
      
      //Getting window handles for complete booking part
      
      String pid=driver.getWindowHandle();
      Set<String> allWin=driver.getWindowHandles();
     
      for (String id : allWin) {
   		if (!id.equals(pid)) {
   		System.out.println("Window id is: " + id);
   		driver.switchTo().window(id);
   		Thread.sleep(1000);
   		//driver.close();
   		} else {
   		System.out.println("Window id is: " + id);
   		}
   		}
      
      
      
      driver.findElement(By.xpath(prop.getProperty("insurance"))).click();              //travel insurance policy
      Thread.sleep(1000);
     
      //driver.findElement(By.xpath(prop.getProperty("insurance"))).click();
      //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      
      //traveller details are added and continued
      
      driver.findElement(By.xpath(prop.getProperty("Addadult"))).click();                 
      driver.findElement(By.xpath(prop.getProperty("Firstname"))).sendKeys("Raksha");
      driver.findElement(By.xpath(prop.getProperty("Lastname"))).sendKeys("Pasad");
      driver.findElement(By.xpath(prop.getProperty("gender"))).click();
      
    
      //driver.findElement(By.xpath("/html/body/div/div/div[2]/div[3]/div/div/div[1]/form[1]/div[4]/div/div[3]/div[1]/label/span[1]/span")).click();
     // driver.findElement(By.xpath("//*[@id=\"Email\"]/div/input")).click();
     Thread.sleep(2000);
     Actions act =  new Actions(driver);
     act.moveToElement(driver.findElement(By.xpath(prop.getProperty("doubleclick")))).click().perform();
     // driver.findElement(By.xpath("/html/body/div/div/div[2]/div[3]/div/div/div[1]/form[1]/div[6]/button")).click();
     Thread.sleep(2000);
     
     driver.findElement(By.xpath(prop.getProperty("conform"))).click();              
     Thread.sleep(1000);
     
     driver.findElement(By.xpath(prop.getProperty("okay"))).click();              //Conforming the details for seat booking and payment
     Thread.sleep(1000);
   
     ra.writeDataLogin("Sheet1", Integer.parseInt(no), 5, "from and to are same");
     t1.pass("Booking is successfull");
     roundreport.flush();
     
     
  }
  
  
   
  @DataProvider
  public Object[][] dp() throws Exception {
	  Excel ex= new Excel("C:\\Users\\pasad\\OneDrive\\Documents\\roundtrip.xlsx");               //providing data from excel sheet
   Object data[][]=new Object[2][5];
   data[0][0]=ex.readData("sheet1",0,0);
   data[0][1]=ex.readData("sheet1",0,1);
   data[0][2]=ex.readData("sheet1",0,2);
   data[0][3]=ex.readData("sheet1",0,3);
   data[0][4]=ex.readData("sheet1",0,4);
   data[1][0]=ex.readData("sheet1",1,0);
   data[1][1]=ex.readData("sheet1",1,1);
   data[1][2]=ex.readData("sheet1",1,2);
   data[1][3]=ex.readData("sheet1",1,3);
   data[1][4]=ex.readData("sheet1",1,4);
  
     
     return data;
     
    
  }
  
  @BeforeMethod
  public void beforeMethod() {
	 
		  System.setProperty("webdriver.chrome.driver","C:\\Users\\pasad\\Downloads\\chromedriver_win32\\chromedriver.exe");
			 driver=new ChromeDriver();
  }

  @AfterMethod
  public void afterMethod() {
	 
        driver.close();
  }

}

  
  

