package com.login.verification;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Account_Balannce {

	 WebDriver driver;

	    @Test
	    public void testAccountBalanceRetrieval() {
	        try {
	            // Set system property for ChromeDriver
	            System.setProperty("webdriver.chrome.driver", "C:\\Users\\\\Sanila\\eclipse-workspace\\ParaBankWebAppProject\\Drivers\\chromedriver.exe");

	            // Initialize ChromeDriver
	            driver = new ChromeDriver();

	            // Navigate to ParaBank login page
	            driver.get("https://parabank.parasoft.com/parabank/index.htm");

	            // Enter username and password
	            WebElement usernameField = driver.findElement(By.xpath("//input[@name='username']"));
	            WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
	            WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log In']"));

	            usernameField.sendKeys("TestS");
	            passwordField.sendKeys("Test123!");
	            loginButton.click();

	            // Click on Account Services link
	            WebElement accountServicesLink = driver.findElement(By.xpath("//a[contains(text(),'Accounts Overview')]"));
	            accountServicesLink.click();

	            // Retrieve account balance
	            WebElement accountBalance = driver.findElement(By.xpath("//span[@class='balance']"));
	            String balanceAmount = accountBalance.getText();

	            // Print account balance
	            System.out.println("Account Balance: " + balanceAmount);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close the browser
	            if (driver != null) {
	                driver.quit();
	            }
	        }
	    }
	}
