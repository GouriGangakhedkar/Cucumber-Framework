package com.vtiger.stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.vtiger.pages.HomePage;
import com.vtiger.pages.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginStepdefinitions extends BaseTest {
	
	
	@Before
	public void before (Scenario scenario) throws Exception 
	{
		initiation();
	    TCName = scenario.getName();
		loggerExtentTest=extentReports.createTest(TCName);
		
	}

	@After
	public void saveReport() 
	{
		extentReports.flush();
		driver.close();
	}
	
	
	@Given("user should be on login page.")
	public void user_should_be_on_login_page() throws Exception 
	{
	    launchApp();
	}
	
	@When("user enters invalid credentials and click on login button")
	public void user_enters_invalid_credentials_and_click_on_login_button() 
	{
		LoginPage lp =new LoginPage(driver, loggerExtentTest);
		lp.login(td.get(TCName).get("Userid"), td.get(TCName).get("Password"));
		
	}
	
	@Then("user should view error message")
	public void user_should_view_error_message() 
	{
		LoginPage lp = new LoginPage(driver, loggerExtentTest);
		lp.verifyErrorMessage();
    }
	
	
	@When("user enters valid credentials and click on login button")
	public void user_enters_valid_credentials_and_click_on_login_button() 
	{
		LoginPage lp = new LoginPage(driver, loggerExtentTest);
		lp.login(td.get(TCName).get("Userid"), td.get(TCName).get("Password"));
		
	}
	
	@Then("user should be navigated to Home page")
	public void user_should_be_navigated_to_home_page() 
	{
	    HomePage hp = new HomePage(driver, loggerExtentTest);
	    hp.verifyLogout();
	   
    }
	
	
	
	
	
	
	
	
//	@Given("user should be on login page.")
//	public void user_should_be_on_login_page() 
//	{
//	    WebDriverManager.chromedriver().setup();
//	    driver = new ChromeDriver();
//	    driver.get("http://localhost:100");
//	    driver.manage().window().maximize();
//	}
//	
//	@When("user enters valid credentials and click on login button")
//	public void user_enters_valid_credentials_and_click_on_login_button() 
//	{
//	    driver.findElement(By.name("user_name")).sendKeys("admin");
//	    driver.findElement(By.name("user_password")).sendKeys("admin");
//	    driver.findElement(By.name("Login")).click();
//	}
//	
//	@Then("user should be navigated to Home page")
//	public void user_should_be_navigated_to_home_page() 
//	{
//	    driver.findElement(By.linkText("Logout")).click();
//    }
//	
//	@When("user enters invalid credentials and click on login button")
//	public void user_enters_invalid_credentials_and_click_on_login_button() 
//	{
//	    driver.findElement(By.name("user_name")).sendKeys("admin123");
//	    driver.findElement(By.name("user_password")).sendKeys("admin123");
//	    driver.findElement(By.name("Login")).click();
//	}
//	
//	@Then("user should view error message")
//	public void user_should_view_error_message() 
//	{
//	    driver.findElement(By.xpath("//*[contains(text(), 'You must specify a valid username and password.')]")).isDisplayed();
//    }
	
}