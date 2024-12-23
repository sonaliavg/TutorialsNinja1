package allPageObjects;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {
	public WebDriver driver;

	 @FindBy(xpath = "//div[@class='app_logo']")   
	 public WebElement appLogo;

	 @FindBy(xpath = "inventory_container")   
     WebElement inventory_container;
	 
	 @FindBy(xpath = "//div[@class='inventory_list']")          
	    WebElement inventorylist;

    @FindBy( xpath= "(//div[@class='inventory_item_name '])[1]")       
     WebElement inventoryFirstitem;
    
    @FindBy( id= "react-burger-menu-btn")       
    WebElement burgerMenubtn;
    
    @FindBy( xpath= "//div[@class='bm-menu']")       
    WebElement MenuSidebar;
    
    @FindBy( xpath= "//a[@id='logout_sidebar_link']")       
    WebElement Logout;

    // Constructor to initialize the driver
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);      
    }
 // Method to Click On First Item
    public void clickOnFirstItem() throws InterruptedException  {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	 WebElement FirstItem = wait.until(ExpectedConditions.visibilityOf(inventoryFirstitem)); 
    	 FirstItem.click();
    	 Thread.sleep(2000);
        System.out.println("Navigated to product details"); 
    }
    public void logout() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   	 WebElement burgermenubtn = wait.until(ExpectedConditions.visibilityOf(burgerMenubtn)); 
   	 burgermenubtn.click();
   	 wait.until(ExpectedConditions.visibilityOf(MenuSidebar)); 
   	Logout.click();             
       
}
}




