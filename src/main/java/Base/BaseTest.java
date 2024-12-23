package Base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import allPageObjects.LoginPage;
 

 
public class BaseTest {
 
	
	protected static WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
 
	public WebDriver Initialize() {
		
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\allTestData\\TestData.properties");
 
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return driver;
 
	}
 
	public WebDriver initializeBrowserAndOpenApplicationURL(String browser) {
 
		if (browser.equalsIgnoreCase("chrome")) {
 
			driver = new ChromeDriver();
 
		} else if (browser.equalsIgnoreCase("firefox")) {
 
			driver = new FirefoxDriver();
 
		} else if (browser.equalsIgnoreCase("edge")) {
 
			driver = new EdgeDriver();
 
		} else if (browser.equalsIgnoreCase("safari")) {
 
			driver = new SafariDriver();
 
		}
 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		//	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
 
		return driver;
 
	}
	 public void captureScreenshot() throws IOException {
	       
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File src = ts.getScreenshotAs(OutputType.FILE);

	        
	        LocalDateTime date = LocalDateTime.now();
	        DateTimeFormatter dateObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
	        String dateTime = date.format(dateObj);
	        
	        
	        String screenshotName = "screenshot_" + dateTime + ".png";
	        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName;
	        File finalDestination = new File(destination);
	        
	        
	        FileUtils.copyFile(src, finalDestination);
	    }

	
 @BeforeSuite
 public void launchApp() {
	 driver = Initialize();
	 loginPage= new LoginPage(driver);
 }
 @AfterSuite
 public void quit()
 {
	 driver.close();
 }
}
 
 
 

