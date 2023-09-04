package com.vtiger.commom;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class CommonActions {
	
	
	public WebDriver driver;
	public WebDriverWait wait;
	public ExtentTest loggerExtentTest;
	
	public CommonActions(WebDriver driver, ExtentTest loggerExtentTest) 
	{
		this.driver= driver;		
		this.loggerExtentTest= loggerExtentTest;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	
	public void enterValue(WebElement elm, String value, String msg) 
	{
		try {
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.clear();
		elm.sendKeys(value);
		loggerExtentTest.pass(msg);
		}
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
			loggerExtentTest.fail("Step failed due to error = "+ e.getMessage() + "<span class='label end-time'><a href='"+getScreenshot()+"'Screenshot</a></span>");
		}
		
	}
	
	public void clickElement(WebElement elm, String msg) 
	{
		try 
		{
			wait.until(ExpectedConditions.elementToBeClickable(elm));
			elm.click();
			loggerExtentTest.pass(msg);
			
		}
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
			loggerExtentTest.fail("Step failed due to error = "+ e.getMessage() + "<span class='label end-time'><a href='"+ getScreenshot() + "'Screenshot</a></span>");
		}
			
	}
	
	public void elementExist(WebElement elm, String msg) 
	{
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(elm));
			elm.isDisplayed();
			loggerExtentTest.pass(msg);
		
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			loggerExtentTest.fail("Step failed due to error = "+ e.getMessage() + "<span class='label end-time'><a href='"+getScreenshot()+"'Screenshot</a></span>");
		}
			
	}
	
	
	public void getTextValidate(WebElement elm, String expe, String msg) 
	{
		try 
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		
		      if(expe.equals(elm.getText()))
		         {
		             loggerExtentTest.pass(msg);
		         }
		
		      else 
		         {
		             loggerExtentTest.fail("Comparison Failed expected data was " + expe+ "and found" + elm.getText());
		         }
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			loggerExtentTest.fail("Step failed due to error = "+ e.getMessage()+ "<span class='label end-time'><a href=' "+ getScreenshot() +"'Screenshot</a></span>");
	    }	
			
    }
	
	
	public String getScreenshot() 
	{
		DateFormat format = new SimpleDateFormat("yy_MM_dd-hh_mm");
		Date date = new Date();
		String string = format.format(date);
		TakesScreenshot scrShoT = ((TakesScreenshot)driver);
		
		File scrFile = scrShoT.getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir")+ "/src/test/java/com/vtiger/reports/screenshots/image"+ string +".png";
		File desFile = new File(path);
		
		try 
		{
			FileUtils.copyFile(scrFile, desFile);
	    }
		catch (Exception e) {
			e.printStackTrace();
		} 
		
		return path;
	}
}
	
	
	

