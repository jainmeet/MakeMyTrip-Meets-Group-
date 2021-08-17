package makemytrip;

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
import java.util.Properties;
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

public class Login extends Excel{
	
	public Login(String pathWithFileName) {
		super(pathWithFileName);
		// TODO Auto-generated constructor stub
	}

	public WebDriver driver;
  @Test(dataProvider="dp")
  public void f(String no,String un,String pwd) throws Exception {
	  ExtentReports report= new ExtentReports();
	  report.attachReporter(new ExtentHtmlReporter("login.html"));
	  ExtentTest tc1=report.createTest("Testcase1");
	  ExtentTest tc2=report.createTest("Testcase2");
	  ExtentTest tc3=report.createTest("Testcase3");
	  ExtentTest tc4=report.createTest("Testcase4");
	  
	  Excel ra=new Excel("C:\\\\Users\\\\ABBU SUMANTH\\\\OneDrive\\\\Documents\\\\mmt.xlsx");
	  Properties prop=new Properties();
	  prop.load(new FileInputStream("src/test/resources/settings.property"));
	  
	   driver.navigate().refresh();
	   driver.get(prop.getProperty("url"));
	   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   driver.findElement(By.xpath(prop.getProperty("Loginviaemail"))).click();
	   driver.findElement(By.id(prop.getProperty("email"))).sendKeys(un);
	try {
	   driver.findElement(By.xpath(prop.getProperty("continue"))).click();
	   }
	   catch(Exception e) {
		  
		   System.out.println("please enter a valid username");
		   ra.writeDataLogin("Sheet1", Integer.parseInt(no), 3, "please enter a valid username");
		   tc2.fail("Testcasefailed with an error please enter valid username");
		   tc3.fail("Testcasefailed with an error please enter valid username");
		   tc4.fail("Testcasefailed with an error please enter valid username");
		   report.flush();
		 
	   }
	  
	   driver.findElement(By.id(prop.getProperty("password"))).sendKeys(pwd);
	   try {
		   driver.findElement(By.xpath(prop.getProperty("Login"))).click();
		 
	   }
	   catch(Exception e){
		 
		   System.out.println("please enter a valid password");
		   ra.writeDataLogin("Sheet1", Integer.parseInt(no), 3, "please enter a valid password");
		   
		
	   }
	   
	  
	   ra.writeDataLogin("Sheet1", Integer.parseInt(no), 3, "Log in sucess");
	 
	   tc1.pass("testcasepassed");
	   report.flush();
	   
	  
  }
  @DataProvider
  public Object[][] dp() {
	  Excel ex= new Excel("C:\\Users\\ABBU SUMANTH\\OneDrive\\Documents\\mmt.xlsx");
   Object data[][]=new Object[4][3];
     data[0][0]=ex.readData("sheet1",0,0);
     data[0][1]=ex.readData("sheet1",0,1);
     data[0][2]=ex.readData("sheet1",0,2);
     data[1][0]=ex.readData("sheet1",1,0);
     data[1][1]=ex.readData("sheet1",1,1);
     data[1][2]=ex.readData("sheet1",1,2);
     data[2][0]=ex.readData("sheet1",2,0);
     data[2][1]=ex.readData("sheet1",2,1);
     data[2][2]=ex.readData("sheet1",2,2);
     data[3][0]=ex.readData("sheet1",3,0);
     data[3][1]=ex.readData("sheet1",3,1);
     data[3][2]=ex.readData("sheet1",3,2);
     
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
