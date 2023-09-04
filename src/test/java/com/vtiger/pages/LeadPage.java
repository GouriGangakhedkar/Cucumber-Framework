package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class LeadPage extends HeaderPage {

	public LeadPage(WebDriver driver, ExtentTest loggerExtentTest) 
	{
		super(driver, loggerExtentTest);
		PageFactory.initElements(driver, this);
		
	}

	
	@FindBy(name= "lastname")
	WebElement tb_lastname;
	
	@FindBy(name = "company")
	WebElement tb_company;
	
	@FindBy(name = "button")
	WebElement tb_button;
	
	@FindBy (xpath = "//*[text()= 'Last Name:']/following::td[1]")
	WebElement lbl_lastName;
	
	@FindBy (xpath = "//*[text()='Company:']/following::td[1]")
	WebElement lbl_company;
	
	
	
	public void createLeadwithManditaoryField(String lname, String company) 
	{
		ca.enterValue(tb_lastname, lname, lname +"has been entered into the lastname field ");
		ca.enterValue(tb_company, company, company +"has been entered into the company field ");
		ca.clickElement(tb_button,"save button clicked");
    }
	
	public void verifyLeadCreation(String lname, String company) 
	{
	 ca.getTextValidate(lbl_lastName, lname, "Text"+ lname+ "validated against label lastname");  
	 ca.getTextValidate(lbl_company, company, "Text"+ company+ "validated against label company");  	 
	}
	
	
	
	
	
}
