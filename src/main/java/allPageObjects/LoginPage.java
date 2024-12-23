package allPageObjects;

import org.openqa.selenium.WebDriver;

	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import java.time.Duration;

	public class LoginPage{
		
		public WebDriver driver;
		
	    @FindBy(id = "user-name")   
	     WebElement usernameField;

	    @FindBy(id = "password")   
	     WebElement passwordField;

	    @FindBy(xpath = "//button[@class='error-button']") 
	     WebElement errorMessage;

	    @FindBy(id = "login-button") 
	     WebElement loginButton;
	    
	    // Constructor to initialize the WebDriver and elements using PageFactory
	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);  
	    }
	    
	
	    // Method to enter the username
	    public void enterUsername(String username) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.visibilityOf(usernameField)); 
	        usernameField.sendKeys(username);
	    }

	    // Method to enter the password
	    public void enterPassword(String password) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.visibilityOf(passwordField)); 
	        
	        passwordField.sendKeys(password); 
	    }

	    // Method to click the login button
	    public void clickLogin() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(loginButton)); 
	        loginButton.click(); 
	    }

	    // Method to perform login
	    public HomePage login(String username, String password) {
	        enterUsername(username);  
	        enterPassword(password);  
	        clickLogin();             
	        return new HomePage(driver); 
	    }
	
	    public boolean isErrorMessageDisplayed() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        
	            wait.until(ExpectedConditions.visibilityOf(errorMessage));
	           
	            return errorMessage.isDisplayed();       
	    }
		public void refresh() {
			driver.navigate().refresh();
			
		}
	    
	    
	}
	    
	

	
	

