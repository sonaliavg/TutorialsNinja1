package Tests;

import static org.testng.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import allPageObjects.HomePage;
import allPageObjects.LoginPage;

public class LoggerClass extends BaseTest {

    public static WebDriver driver;
    public static Logger log;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp() {
        
        log = LogManager.getLogger(LoggerClass.class);

       driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));

        // Initialize the page objects
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);  
        
        log.info("Browser initialized and application URL opened");
    }

    @Test(priority = 1)
    public void testLoginWithValidCredentials() {
       
        loginPage.login(prop.getProperty("validusername"), prop.getProperty("validpassword"));
        assertNotNull(homePage, "Home page is not null after successful login");
        log.info("Profile icon displayed");
        homePage.logout();
        log.info("Logged out successfully");
    }

    @Test(priority = 2)
    public void testLoginWithInvalidUsername() {
        
        loginPage.login(prop.getProperty("Invalidusername"), prop.getProperty("validpassword"));
        loginPage.isErrorMessageDisplayed();
        log.info("Entered Invalid Credentials (Invalid Username)");
    }

    @Test(priority = 3)
    public void testLoginWithInvalidPassword() {
        loginPage.login(prop.getProperty("validusername"), prop.getProperty("Invalidpassword"));
      
        loginPage.isErrorMessageDisplayed();
        log.info("Entered Invalid Credentials (Invalid Password)");
    }

    @Test(priority = 4)
    public void testLoginWithInvalidCredentials() {
    
        loginPage.login(prop.getProperty("Invalidusername"), prop.getProperty("Invalidpassword"));
        
        loginPage.isErrorMessageDisplayed();
        log.info("Entered Invalid Credentials (Both Invalid Username and Password)");
    }

    @AfterMethod
    public void tearDown() {
       
        if (driver != null) {
            driver.quit();
            log.info("Quitting the driver");
        }
    }
}
