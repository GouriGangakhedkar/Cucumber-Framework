package com.vtiger.stepdefinitions;

import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LeadStepdefinitions extends BaseTest {
	
	@When("user clicks on the New Lead link")
	public void user_clicks_on_the_New_Lead_link() 
	{
		LeadPage ldp = new LeadPage(driver, loggerExtentTest);
		ldp.clickNewLead();
		
	}
	
	@Then("user puts manditaory values within lead form and click on save button")
	public void enter_Manditary_Values() 
	{
		LeadPage ldp = new LeadPage(driver, loggerExtentTest);
		ldp.createLeadwithManditaoryField(td.get(TCName).get("Lname"), td.get(TCName).get("Company"));
    }
	
	@Then("lead should be created successfully")
	public void leadCreatedSuccessfully() 
	{
		LeadPage ldp = new LeadPage(driver, loggerExtentTest);
		ldp.verifyLeadCreation(td.get(TCName).get("Lname"), td.get(TCName).get("Company"));
    }

}
