package allPageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

	public WebDriver driver;

	 @FindBy(xpath = "//span[@class='title']")   
	 public WebElement cartTitle;

	 @FindBy(xpath = "//div[@class='cart_quantity']")   
    WebElement cartQty;
	 
	 @FindBy(xpath = "(//button[normalize-space()='Remove'])[1]")          
	    WebElement removeCTA;

	 @FindBy(id = "continue-shopping")       
	    WebElement continueShopping;
   @FindBy(id = "checkout")       
    WebElement checkoutCTA;

   // Constructor to initialize the driver
   public CartPage(WebDriver driver) {
       this.driver = driver;
       PageFactory.initElements(driver, this);      
   }
   
// Method to Click On First Item
   public void clickOnCheckout()  {
   	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
   	 driver.findElement(By.id("checkout")).click();
   	System.out.println("Navigated to checkoutpage");
   }
  
   
   
   
}
