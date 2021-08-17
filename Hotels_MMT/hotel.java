package login;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;



public class hotel  {
	WebDriver driver;
	Properties prop;
	
	  @Test(dataProvider = "dp")
	 public void f(String city , String fname , String lname , String email , String phone) throws Exception, IOException {
	
     ExtentReports report= new ExtentReports();
	 report.attachReporter(new ExtentHtmlReporter("hotel.html"));
		  ExtentTest tc1=report.createTest("Testcase1");
		  ExtentTest tc2=report.createTest("Testcase2");
		  
		  HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("D:\\mmthotel.xls"));
		  HSSFSheet sh= wb.getSheet("Sheet1");
		 	  
	 prop = new Properties();
	 prop.load(new FileInputStream("src/test/resources/settings.property"));
	 driver.get(prop.getProperty("url"));
	 driver.findElement(By.xpath(prop.getProperty("popup"))).click(); //popup
	 driver.findElement(By.xpath(prop.getProperty("hotel"))).click(); //hotels
	 driver.findElement(By.xpath(prop.getProperty("clickcity"))).click(); //click city
	 Thread.sleep(3000);
	 driver.findElement(By.xpath(prop.getProperty("entercity"))).sendKeys(city); //enter city
	 Thread.sleep(3000);
	 driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	 driver.findElement(By.xpath(prop.getProperty("ok"))).click();
	 driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	 driver.findElement(By.id(prop.getProperty("checkin"))).click();
	 driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
	 driver.findElement(By.cssSelector(prop.getProperty("checkindate"))).click();
	 driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
	 driver.findElement(By.cssSelector(prop.getProperty("checkout"))).click();
	 driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
	 driver.findElement(By.id(prop.getProperty("guest"))).click();	
	 driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
	 driver.findElement(By.xpath(prop.getProperty("room"))).click();
	 driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
	 driver.findElement(By.id(prop.getProperty("search"))).click(); //search
	 driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
	 driver.findElement(By.xpath(prop.getProperty("hotels"))).click(); //hotel opening
	 Thread.sleep(5000);

	
	 String pid = driver.getWindowHandle();
	 System.out.println(pid);
	 Set<String> allWin = driver.getWindowHandles();

	for (String id : allWin) {
	if (!id.equals(pid)) {
	System.out.println("Window id is: " + id);
	driver.switchTo().window(id);
	Thread.sleep(1000);
	} else {
	System.out.println("Window id is: " + id);
	 }
	}
	
	driver.findElement(By.xpath(prop.getProperty("booking"))).click(); //booking
	driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	Thread.sleep(5000);
	WebElement E= driver.findElement(By.className("frmSelect"));
	Select frmSelect= new Select(E);
	frmSelect.selectByVisibleText("Ms");
	
	driver.findElement(By.id("fName")).sendKeys(fname);
	driver.findElement(By.id("lName")).sendKeys(lname);
	driver.findElement(By.id("email")).sendKeys(email);
	driver.findElement(By.id("mNo")).sendKeys(phone);
	Thread.sleep(3000);
		
	try { 
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("paynow"))).click();
		sh.getRow(1).createCell(5).setCellValue("passed");
		wb.write(new FileOutputStream("D:\\mmthotel.xls"));
    	
		}
    catch(Exception e){ 
    Thread.sleep(3000);
	
    driver.findElement(By.xpath("//div[contains(@class,'payOptions appendBottom20')]//li[2]")).click();
    driver.findElement(By.xpath(prop.getProperty("paynow"))).click();
    sh.getRow(1).createCell(5).setCellValue("passed");
	wb.write(new FileOutputStream("D:\\mmthotel.xls"));
	sh.getRow(1).createCell(6).setCellValue("No Error");
	wb.write(new FileOutputStream("D:\\mmthotel.xls"));
	
      		}
	
	
	

	try {
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='dt__payment__sprite payment__icon__upi']")).isDisplayed()) ;
		sh.getRow(2).createCell(5).setCellValue("Failed");
		wb.write(new FileOutputStream("D:\\mmthotel.xls"));
	}
	catch(Exception e)
	{
		
		System.out.println("Error is :Please enter guest's first name");
		sh.getRow(2).createCell(5).setCellValue("Failed");
		wb.write(new FileOutputStream("D:\\mmthotel.xls"));
		sh.getRow(2).createCell(6).setCellValue("Error is :Please enter guest's first name");
		wb.write(new FileOutputStream("D:\\mmthotel.xls"));
	}
	
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='dt__payment__sprite payment__icon__upi']")).isDisplayed()) ;
	
	
	tc1.pass("testcasepassed");
	tc2.fail("testcasefailed");
	
	   report.flush();
  } 
 
	  
	  
	  @BeforeMethod
	  public void beforeMethod() {
			System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe" );
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			
	  }
	  

	  @AfterMethod
	  public void afterMethod() {
		// driver.close();
	  }
	  
	  @DataProvider
		public Object[] dp() throws Exception {
			  HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("D:\\mmthotel.xls"));
			  HSSFSheet sh= wb.getSheet("Sheet1");
			 	  
			 Object data[][] = new Object[2][5];
		     data[0][0] = sh.getRow(1).getCell(0).toString();
		     data[0][1] = sh.getRow(1).getCell(1).toString();
		     data[0][2] = sh.getRow(1).getCell(2).toString();
		     data[0][3] = sh.getRow(1).getCell(3).toString();
		     data[0][4] = sh.getRow(1).getCell(4).toString();
		     data[1][0] = sh.getRow(2).getCell(0).toString();
		     data[1][1] = sh.getRow(2).getCell(1).toString();
		     data[1][2] = sh.getRow(2).getCell(2).toString();
		     data[1][3] = sh.getRow(2).getCell(3).toString();
		     data[1][4] = sh.getRow(2).getCell(4).toString();
		     
		    
			  
			return data;

			
		}
	  	  
}
	














	


