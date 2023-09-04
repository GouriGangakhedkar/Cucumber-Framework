package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.commom.CommonActions;


public class HeaderPage {
	
	public WebDriver driver;
	public CommonActions ca;
	
	
	public HeaderPage(WebDriver driver, ExtentTest loggerExtentTest) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		ca = new CommonActions(driver, loggerExtentTest);
	}

	@FindBy(linkText = "Logout")
	WebElement lnk_logout;
	
	@FindBy(linkText = "New Lead")
	WebElement lnk_newLead;
	
	@FindBy(linkText = "Leads")
	WebElement lnk_leads;
	
	@FindBy(linkText = "New Account")
	WebElement lnk_newAccount;
	
	
	public void clickNewLead() 
	{
		ca.clickElement(lnk_newLead, "newLead link clicked");
	}
	
	public void clickLead() 
	{
		ca.clickElement(lnk_leads, "Lead link clicked");
	}
	
	public void clicknewAccount() 
	{
		ca.clickElement(lnk_newAccount, "newAccount link clicked");
	}
	
	
	
	public void verifyLogout() 
	{
		ca.elementExist(lnk_logout, "Logout link exist on Home Page");
	}
	
	public void clickLogout() 
	{
		ca.clickElement(lnk_logout, "Logout link clicked");
	}
	
	

}
