package Login;

import java.io.FileInputStream;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class FlightBooking extends Excel {
	public FlightBooking(String pathWithFileName) {
		super(pathWithFileName);
		// TODO Auto-generated constructor stub
	}

	WebDriver driver;
	Properties prop;
	@Test(dataProvider = "dp")

	public void f(String loc,String desti,String loc2,String desti2,String FirstName,String LastName,String Num,String Email) throws Exception, IOException {
		ExtentReports report= new ExtentReports();
		report.attachReporter(new ExtentHtmlReporter("FlightBooking.html"));
		ExtentTest tc1=report.createTest("Testcase1");
		ExtentTest tc2=report.createTest("Testcase2");

		Excel ex= new Excel("C:\\Users\\jainm\\Downloads\\MeetMakeMyTrip.xlsx");

		prop = new Properties();

		prop.load(new FileInputStream("src/test/resources/settings.property"));
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		//This will Enter The To and From 
		driver.findElement(By.xpath(prop.getProperty("popup"))).click();//click on the loginblue tab
		driver.findElement(By.cssSelector(prop.getProperty("multicity"))).click();//click on the multicity tab
		driver.findElement(By.id(prop.getProperty("location1"))).sendKeys(loc);
		Thread.sleep(2000);
		driver.findElement(By.id(prop.getProperty("checkbox1"))).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(prop.getProperty("destination1"))).sendKeys(desti);
		Thread.sleep(2000);
		driver.findElement(By.id(prop.getProperty("checkbox2"))).click();
		driver.findElement(By.xpath(prop.getProperty("date12"))).click();

		//This will Enter The Second To and From 
		driver.findElement(By.id(prop.getProperty("location2"))).sendKeys(loc2);
		driver.findElement(By.id(prop.getProperty("checkbox3"))).click();
		driver.findElement(By.cssSelector(prop.getProperty("destination2"))).sendKeys(desti2);
		Thread.sleep(2000);
		driver.findElement(By.id(prop.getProperty("checkbox4"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("date"))).click();
		Thread.sleep(5000);
		

		//This Will Find If To and From Loc are same then will send error

		try {
			Assert.assertTrue(driver.findElement(By.xpath("//span[@class='redText errorMsgText']")).isDisplayed()) ;
			driver.findElement(By.xpath(prop.getProperty("viewfligth"))).click();


		}
		catch(Exception e) {
			System.out.println("Print The eeror in the SHeet" +e.getMessage());
			ex.writeDataLogin("Sheet1", Integer.parseInt(loc),9, "please enter a valid password");
		}

		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("bookfligth"))).click();
		//This is Site handdeling part
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
		Thread.sleep(10000);
		
		

		//This is to Enter The Passenger Details 
		driver.findElement(By.xpath(prop.getProperty("click1"))).click();
		driver.findElement(By.xpath(prop.getProperty("firstname"))).sendKeys(FirstName);
		driver.findElement(By.xpath(prop.getProperty("lastname"))).sendKeys(LastName);
		driver.findElement(By.xpath(prop.getProperty("click2"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("num"))).sendKeys(Num);
		driver.findElement(By.xpath(prop.getProperty("email"))).sendKeys(Email);
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("click3"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("click4"))).click();
		Thread.sleep(2000);
		//This The Next Page For Selecting The Seat 
		driver.findElement(By.xpath(prop.getProperty("popup1"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("click5"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("seat1"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("nextbutton"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("seat2"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("skipbutton"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("conbutton"))).click();

		tc1.pass("testcasepassed");
		tc2.fail("testcasefailed");


		report.flush();



	}

	@BeforeMethod
	public void beforeMethod() {

		System.setProperty("webdriver.chrome.driver","C:\\Users\\jainm\\OneDrive\\Desktop\\eclipse\\sele\\chromedriver.exe" );
		driver=new ChromeDriver();

	}


	@AfterMethod
	public void afterMethod() {
		  driver.close();
	}

	@DataProvider
	public Object[] dp() throws Exception {
		//XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("C:\\Users\\jainm\\Downloads\\MeetMakeMyTrip.xlsx"));
		//XSSFSheet sh = wb.getSheet("Sheet1");
		Excel ex= new Excel("C:\\Users\\jainm\\Downloads\\MeetMakeMyTrip.xlsx");


		Object data[][] = new Object[2][8];
		data[0][0] = ex.readData("Sheet1",0,0);
		data[0][1] = ex.readData("Sheet1",0,1);
		data[0][2] = ex.readData("Sheet1",0,2);
		data[0][3] = ex.readData("Sheet1",0,3);
		data[0][4] = ex.readData("Sheet1",0,4);
		data[0][5] = ex.readData("Sheet1",0,5);
		data[0][6] = ex.readData("Sheet1",0,6);
		data[0][7] = ex.readData("Sheet1",0,7);
		
		data[1][0] = ex.readData("Sheet1",1,0); 
		data[1][1]=ex.readData("Sheet1",1,1);
		data[1][2] = ex.readData("Sheet1",1,2);
		data[1][3]= ex.readData("Sheet1",1,3);
		data[1][4] = ex.readData("Sheet1",1,4);
		data[1][5] = ex.readData("Sheet1",1,5);
		data[1][6] = ex.readData("Sheet1",1,6);
		data[1][7] = ex.readData("Sheet1",1,7);




		return data;

	}
}
