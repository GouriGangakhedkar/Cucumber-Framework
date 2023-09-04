@Regression
Feature: Login functionality

Scenario:  TC_01Invalid_login
Given user should be on login page.
When user enters invalid credentials and click on login button
Then user should view error message


Scenario:  TC_02valid_login
Given user should be on login page.
When user enters valid credentials and click on login button
Then user should be navigated to Home page


