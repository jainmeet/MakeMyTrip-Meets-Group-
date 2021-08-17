package makemytrip;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import fm.Excel;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class Onewaytrip extends Excel {

	public Onewaytrip(String pathWithFileName) {
		super(pathWithFileName);
		// TODO Auto-generated constructor stub
	}

	public WebDriver driver;
  @Test(dataProvider="dp")
  public void f(String no,String from,String to,String firstname,String lastname,String number,String email) throws Exception {
	  Properties prop=new Properties();
	  prop.load(new FileInputStream("src/test/resources/settings.property"));
	  Excel ra= new Excel("C:\\Users\\ABBU SUMANTH\\OneDrive\\Documents\\onewaytrip.xlsx");
	  ExtentReports report= new ExtentReports();
	  report.attachReporter(new ExtentHtmlReporter("oneway.html"));
	  ExtentTest tc1=report.createTest("Testcase1");
	  ExtentTest tc2=report.createTest("Testcase2");
	
	   driver.navigate().refresh();
	   driver.get(prop.getProperty("url"));
	   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   driver.findElement(By.xpath(prop.getProperty("clickonlogin"))).click();
	   driver.findElement(By.xpath(prop.getProperty("from"))).click();
	   
	   driver.findElement(By.xpath(prop.getProperty("source"))).sendKeys(from);
	   Thread.sleep(2000);
	   driver.findElement(By.xpath(prop.getProperty("sourceSelection"))).click();
	   driver.findElement(By.xpath(prop.getProperty("to"))).click();
	   
       driver.findElement(By.xpath(prop.getProperty("destination"))).sendKeys(to);
       Thread.sleep(2000);
       driver.findElement(By.xpath(prop.getProperty("destinationSelection"))).click();
       driver.findElement(By.xpath(prop.getProperty("traveller"))).click();
       driver.findElement(By.xpath(prop.getProperty("adult"))).click();
       
       driver.findElement(By.xpath(prop.getProperty("travellerclass"))).click();
       Thread.sleep(2000);
       driver.findElement(By.xpath(prop.getProperty("apply"))).click();
       Thread.sleep(2000);
       driver.findElement(By.xpath(prop.getProperty("Search"))).click();
       try {
       driver.findElement(By.xpath(prop.getProperty("viewdetails"))).click();
       }
       catch(Exception e) {
    	   System.out.println("from and to cant be same");
    	   ra.writeDataLogin("Sheet1", Integer.parseInt(no), 7, "from and to cant be same");
    	   tc1.fail("test case failed with error message - from and to cant be same");
    	   report.flush();
       }
       Thread.sleep(2000);
       driver.findElement(By.xpath(prop.getProperty("booknow"))).click();
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
       try {
       driver.findElement(By.xpath(prop.getProperty("review"))).click();
       }
       catch(Exception e){
    	   
       }
       Thread.sleep(2000);
       driver.findElement(By.xpath(prop.getProperty("insurance"))).click();
       Thread.sleep(2000);
       driver.findElement(By.xpath(prop.getProperty("Addadult"))).click();
       driver.findElement(By.xpath(prop.getProperty("Firstname"))).sendKeys(firstname);
       driver.findElement(By.xpath(prop.getProperty("Lastname"))).sendKeys(lastname);
       driver.findElement(By.xpath(prop.getProperty("gender"))).click();
       driver.findElement(By.xpath(prop.getProperty("mobilenumber"))).sendKeys(number);
       driver.findElement(By.xpath(prop.getProperty("emailid"))).sendKeys(email);
      
      Thread.sleep(2000);
      Actions act =  new Actions(driver);
      act.moveToElement(driver.findElement(By.xpath(prop.getProperty("doubleclick")))).click().perform();
     
      Thread.sleep(2000);
      driver.findElement(By.xpath(prop.getProperty("onewaycontinue"))).click();
      Thread.sleep(2000);
      driver.findElement(By.xpath(prop.getProperty("checkbox"))).click();
      Thread.sleep(2000);
      try {
    	  driver.findElement(By.xpath(prop.getProperty("seatselection"))).click();
      }
      catch(Exception e) {
    	  System.out.println("BookingSucess");
    	 ra.writeDataLogin("Sheet1", Integer.parseInt(no), 7, "BookingSucess");
    	  tc2.pass("test case passed and booking is successfull");
    	  report.flush();	  
      }
      
      
  }
  @DataProvider
  public Object[][] dp() {
	  Excel ex= new Excel("C:\\Users\\ABBU SUMANTH\\OneDrive\\Documents\\onewaytrip.xlsx");
   Object data[][]=new Object[2][7];
     data[0][0]=ex.readData("sheet1",0,0);
     data[0][1]=ex.readData("sheet1",0,1);
     data[0][2]=ex.readData("sheet1",0,2);
     data[0][3]=ex.readData("sheet1",0,3);
     data[0][4]=ex.readData("sheet1",0,4);
     data[0][5]=ex.readData("sheet1",0,5);
     data[0][6]=ex.readData("sheet1",0,6);
     data[1][0]=ex.readData("sheet1",1,0);
     data[1][1]=ex.readData("sheet1",1,1);
     data[1][2]=ex.readData("sheet1",1,2);
     data[1][3]=ex.readData("sheet1",1,3);
     data[1][4]=ex.readData("sheet1",1,4);
     data[1][5]=ex.readData("sheet1",1,5);
     data[1][6]=ex.readData("sheet1",1,6);
     
     return data;
     
    
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\ABBU SUMANTH\\OneDrive\\Desktop\\chromedriver\\chromedriver.exe");
		 driver=new ChromeDriver();
  }

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

}
