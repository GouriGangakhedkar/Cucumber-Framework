@Regression
Feature: Leads creation

@CreateLead
Scenario:  TC_03CreateLeadWithManditaryFields
Given user should be on login page.
When user enters valid credentials and click on login button
Then user should be navigated to Home page
When user clicks on the New Lead link
And user puts manditaory values within lead form and click on save button
Then lead should be created successfully