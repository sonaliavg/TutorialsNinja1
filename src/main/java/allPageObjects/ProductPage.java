package allPageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	WebDriver driver;
	
	@FindBy(id = "contents_wrapper") 
    WebElement Prductdetailspage;
	 
	@FindBy(id = "back-to-products")   
	     WebElement backtoproducts;

	@FindBy(id = "add-to-cart")   
	     WebElement addtocart;

	@FindBy(xpath = "//div[@class='inventory_details_price']") 
	     WebElement Pricedetail;
	    
	@FindBy(xpath = "//a[@class='shopping_cart_link']") 
	     WebElement cartIcon;
	
	    // Constructor to initialize the WebDriver and elements using PageFactory
	    public ProductPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);  
	    }
	    
	    public void addItemToCart() throws InterruptedException {
	    	
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	        wait.until(ExpectedConditions.visibilityOf(Prductdetailspage));
	        WebElement Addtocart=driver.findElement(By.id("add-to-cart"));
	        Addtocart.click();
	        System.out.println("Product added to cart Successfully");
	        Thread.sleep(2000);
	        
	    }

	    public void goToCart() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	 wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
	         cartIcon.click();
	         System.out.println("Navigated to cartpage");
	        
	    }
	    
}
