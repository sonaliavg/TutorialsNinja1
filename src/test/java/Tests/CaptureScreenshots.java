package Tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Base.BaseTest;
import allPageObjects.HomePage;
import allPageObjects.LoginPage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CaptureScreenshots extends BaseTest {

    public WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    @BeforeClass
    public void setUp() {
        // Set up WebDriver and open the browser
    	driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
   	 homePage = new HomePage(driver);
       loginPage = new LoginPage(driver);   
    }

    @Test
    public void captureScreenshot() throws IOException {
        // Capture screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        // Generate a timestamp for the screenshot filename
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        String dateTime = date.format(dateObj);
        
        // Set the destination path for saving the screenshot
        String screenshotName = "screenshot_" + dateTime + ".png";
        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName;
        File finalDestination = new File(destination);
        
        // Save the screenshot to the destination
        FileUtils.copyFile(src, finalDestination);
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
