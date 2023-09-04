package com.vtiger.stepdefinitions;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.checkerframework.checker.i18nformatter.qual.I18nFormat;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Network.UserAgent;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	     public static WebDriver driver;
         public static Properties prop;
         public static  Map<String,Map<String, String>> td;
         
         
         public static ExtentHtmlReporter extentHtmlReporter;
         public static ExtentReports extentReports;
         public static ExtentTest loggerExtentTest;
         public static String TCName= "";
         
    
    public void initiation() throws Exception 
    {
    if(prop==null)
    prop = readProperties();
    if(td==null)
    td=	getData(System.getProperty("user.dir") + "/src/test/resources/TestData/Data_3.xlsx","Sheet1");
    System.out.println(td);	
    if (extentReports==null)
    createExtentReport();
    }
         
	
	
	public void launchApp() 
	{
		if(prop.getProperty("Browser").equals("chrome")) 
		{
		WebDriverManager.chromedriver().setup();  // No need of system.set property, chrom.exe file
		driver = new ChromeDriver();
		}
		
		else if(prop.getProperty("Browser").equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
//		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://localhost:100");
		driver.manage().window().maximize();
		int time=Integer.parseInt(prop.getProperty("GlobalTimeout"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
			
	}
	
	@AfterSuite
	public void closeApp()
	{
		driver.quit();
	}
	
	public void createExtentReport() 
	{
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("hh-mm_dd-MM-YY");
		String fileName = format.format(date);
		
		
		extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/src/test/java/com/vtiger/reports/ExtentReport"+ fileName + ".html");
		//Create object of ExtentReport
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
		extentReports.setSystemInfo("HostName", "AutomationTestHub");
		extentReports.setSystemInfo("Environment", "Test");
		extentReports.setSystemInfo("UserName", "Rajesh U");
		extentHtmlReporter.config().setDocumentTitle("Title of the report comes here");
		extentHtmlReporter.config().setReportName("Name of the report comes here");
		extentHtmlReporter.config().setTheme(Theme.STANDARD);
			
	}
	
	
public Map<String,Map<String, String>> getData (String file, String sheet) throws Exception
	{
	Fillo fillo=new Fillo();
	Connection connection = fillo.getConnection(file);
	String strQuery="Select * from " + sheet;
	Recordset recordset = connection.executeQuery(strQuery);
	List<String> collist = recordset.getFieldNames();
	
	Map<String,Map<String, String>> data = new LinkedHashMap<String,Map<String,String>>();
	while(recordset.next())
	{
		Map<String,String> rowdata = new LinkedHashMap<String,String>();
		for (int i=0; i<collist.size(); i++ ) 
		{
			String fieldsname = collist.get(i);
			String colvalue = recordset.getField(fieldsname);
			rowdata.put(fieldsname, colvalue);
		}
	data.put(recordset.getField(collist.get(0)), rowdata);
		System.out.println();
	}
	 
	recordset.close();
	connection.close();
	
	return data;
	}
	
 public Properties readProperties() 
	{
		Properties prop = null;
		try 
		{
		       prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/Config/Setting.properties");
		prop.load(fis);		}
		catch (Exception e) 
		{
			 e.getMessage();
		}
		return prop;
	}

}
