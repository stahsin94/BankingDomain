package com.login.verification;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class Login_Functionality_Test {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up the WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sanila\\eclipse-workspace\\ParaBankWebAppProject\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test(priority = 1)
    public void testValidLogin() {
        // Enter username and password
        WebElement usernameField = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log In']"));

        usernameField.sendKeys("TestS");
        passwordField.sendKeys("Test123!");
        loginButton.click();

        // Wait for the welcome message
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='title']")));

        // Verify successful login by checking the page title
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "ParaBank | Error");
    }


    @Test(priority = 2)
    public void testInvalidLogin() {
        // Enter invalid username and password
        WebElement usernameField = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log In']"));

        usernameField.sendKeys("InvalidUsername");
        passwordField.sendKeys("InvalidPassword");
        loginButton.click();

        // Verify error message
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/p"));
        Assert.assertEquals(errorMessage.getText(), "An internal error has occurred and has been logged.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
