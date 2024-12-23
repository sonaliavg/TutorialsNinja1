package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import allPageObjects.HomePage;
import allPageObjects.LoginPage;
import static org.testng.Assert.*;

public class LoginTestWithValidCredentials extends BaseTest{
	
	
    public WebDriver driver;
     LoginPage loginPage;
     HomePage homePage;
   
    @BeforeClass
    public void setUp() {
    	driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
    	 homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);  
        }

    @Test(priority = 1)
    public void testLoginWithValidCredentials() {
        loginPage.login(prop.getProperty("validusername"), prop.getProperty("validpassword"));
        // Perform assertions to verify successful login
        
        assertNotNull(homePage, "Home page is not null after successful login");
         System.out.println("Profile icon displayed");  
         homePage.logout();
    }
    @Test(priority = 2)
    public void testLoginWithInValidUsername()  {
        loginPage.login(prop.getProperty("Invalidusername"), prop.getProperty("validpassword"));
        loginPage.isErrorMessageDisplayed();
        
        
	}
    @Test(priority = 3)
    public void testLoginWithInValidPassword()  {
        loginPage.login(prop.getProperty("validusername"), prop.getProperty("Invalidpassword"));
        loginPage.isErrorMessageDisplayed();
        }
    @Test(priority = 4)
    public void testLoginWithInValidCredentials()  {
        loginPage.login(prop.getProperty("Invalidusername"), prop.getProperty("Invalidpassword"));
        loginPage.isErrorMessageDisplayed();   
    }
    @AfterClass
    public void tearDown() {
        // Close the browser after all tests are complete
        if (driver != null) {
            driver.quit();
        }
    }
}
