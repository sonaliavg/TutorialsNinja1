package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Base.BaseTest;
import allPageObjects.CartPage;
import allPageObjects.CheckoutPage;
import allPageObjects.HomePage;
import allPageObjects.LoginPage;
import allPageObjects.ProductPage;

public class ExtentTestReports extends BaseTest {

    private ExtentTest logger;
    private ExtentReports report;
    LoginPage loginPage;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void startTest() {
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Initialize ExtentReports
        report = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentTestReports.html", true);
    }

    @Test(priority=2)
    public void Orderplacement() throws InterruptedException {
        loginPage.login(prop.getProperty("validusername"), prop.getProperty("validpassword"));
        homePage.clickOnFirstItem();
        productPage.addItemToCart();
        productPage.goToCart();
        cartPage.clickOnCheckout();
        checkoutPage.fillCheckoutForm(prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("postalcode"));
        checkoutPage.clickContinue();
        checkoutPage.verifyOverviewAndSubmitOrder();
    }

    @Test(priority = 1)
    public void LoginTestWithValidCredentials() {
        logger = report.startTest("LoginTestWithValidCredentials");
        loginPage.login(prop.getProperty("validusername"), prop.getProperty("validpassword"));
        homePage.logout();
        loginPage.login(prop.getProperty("Invalidusername"), prop.getProperty("validpassword"));
        loginPage.isErrorMessageDisplayed();
        loginPage.login(prop.getProperty("validusername"), prop.getProperty("Invalidpassword"));
        loginPage.isErrorMessageDisplayed();
        loginPage.login(prop.getProperty("Invalidusername"), prop.getProperty("Invalidpassword"));
        loginPage.isErrorMessageDisplayed();

        loginPage.refresh();
    }

    @Test(priority = 3)
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
    @AfterMethod
    public void getResult(ITestResult result) {
        // Capture screenshot on failure or success
        String screenshotPath = captureScreenshotOnFailureOrSuccess(result);

        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, "Test case failed: " + result.getName());
            logger.log(LogStatus.FAIL, "Test case failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(LogStatus.SKIP, "Test case skipped: " + result.getName());
            logger.log(LogStatus.SKIP, "Test case skipped: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "Test case passed: " + result.getName());
            logger.log(LogStatus.PASS, "Test case passed: " + result.getThrowable());
        }

        // Attach the screenshot to the report
        if (screenshotPath != null) {
            logger.log(LogStatus.INFO, "Screenshot", logger.addScreenCapture(screenshotPath));
        }

        // End the test in the report
        report.endTest(logger);
    }

    @AfterClass
    public void endTest() {
        // Finalize the report and close it
        report.flush();
        report.close();
    }

    private String captureScreenshotOnFailureOrSuccess(ITestResult result) {
        String screenshotPath = null;

        // Only capture screenshot if test failed or passed
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SUCCESS) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            // Generate a timestamp for the screenshot filename
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter dateObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
            String dateTime = date.format(dateObj);

            // Set the destination path for saving the screenshot
            screenshotPath = System.getProperty("user.dir") + "/Screenshots/screenshot_" + dateTime + ".png";
            File destination = new File(screenshotPath);

            try {
                // Save the screenshot
                FileUtils.copyFile(src, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return screenshotPath;
    }
}
