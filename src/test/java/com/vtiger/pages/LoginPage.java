package com.vtiger.pages;

import java.util.concurrent.locks.Condition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.vtiger.commom.CommonActions;

public class LoginPage {
	
	private WebDriver driver;
    public CommonActions ca;
   
 
    public LoginPage(WebDriver driver, ExtentTest loggerExtentTest) 
    {
    	this.driver = driver;
		PageFactory.initElements(driver,this);	
		ca = new CommonActions(driver, loggerExtentTest);
    }

	
    @FindBy(name="user_name")
    WebElement tb_username;
    
    @FindBy(name="user_password")
    WebElement tb_password;
    
    @FindBy(name="Login")
    WebElement btn_login;
    
    @FindBy(xpath ="//*[contains(text(),'You must specify a valid username and password.')]")
    WebElement msg_error;
    
    
    
	public void login(String userid, String pwd)
	{
		System.out.println("Login Started");
		ca.enterValue(tb_username, userid, userid + "has been enterd into username field");
		ca.enterValue(tb_password, pwd, "password has been entered into pwd field");
		ca.clickElement(btn_login, "login button clicked");
		System.out.println("Login Successful");
		
		
	}
	
	public void verifyErrorMessage() 
	{
		ca.elementExist(msg_error, "error message validated successfully");
	}
	

}
