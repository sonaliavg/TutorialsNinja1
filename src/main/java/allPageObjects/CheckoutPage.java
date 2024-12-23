package allPageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
	public WebDriver driver;

	 @FindBy(xpath = "//div[@class='checkout_info']")   
	 public WebElement checkoutForm;

	 @FindBy(id = "first-name")   
    WebElement firstnameField;
	 
	 @FindBy(id = "last-name")          
	    WebElement lastnameField;

   @FindBy(id = "postal-code")       
    WebElement postalcodeField;

   @FindBy(xpath = "//input[@id='continue']")       
    WebElement continueButton;
   
   @FindBy(xpath = "//div[@class='cart_item_label']")       
   WebElement cartInfo;

  @FindBy(xpath = "//div[normalize-space()='Payment Information:']")       
   WebElement paymentInfo;
  @FindBy(xpath = "//div[normalize-space()='Shipping Information:']")       
  WebElement shippingInfo;
  @FindBy(xpath = "//div[normalize-space()='Price Total']")       
  WebElement priceInfo;
  
  @FindBy(xpath = "//button[@id='finish']")       
  WebElement finishBtn;
  
  
   // Constructor to initialize the driver
   public CheckoutPage(WebDriver driver) {
       this.driver = driver;
       PageFactory.initElements(driver, this);      
   }
   
   public void enterFirstname(String firstname) {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOf(firstnameField)); 
       firstnameField.sendKeys(firstname);
   }

   // Method to enter the password
   public void enterLastname(String lastname) {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOf(lastnameField)); 
       
       lastnameField.sendKeys(lastname); 
   }
   public void enterPostalCode(String postalcode) {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOf(postalcodeField)); 
       postalcodeField.sendKeys(postalcode);
   }
   
// Method to fillCheckoutForm
   public void fillCheckoutForm(String firstname, String lastname, String postalcode)  {
   	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
   	enterFirstname( firstname);
   	enterLastname(lastname);
   	enterPostalCode(postalcode);
   	System.out.println("CheckoutForm filled Successfully");
   }
   public void clickContinue()  {
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.elementToBeClickable(continueButton)); 
       continueButton.click();
       System.out.println("Navigated to overview page");
}
   public void verifyOverviewAndSubmitOrder()  {
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   wait.until(ExpectedConditions.visibilityOf(cartInfo));
	   wait.until(ExpectedConditions.visibilityOf(paymentInfo));
	   wait.until(ExpectedConditions.visibilityOf(shippingInfo));
	   wait.until(ExpectedConditions.visibilityOf(priceInfo));
	   System.out.println("Verified overview page Successfully");
	   JavascriptExecutor js = (JavascriptExecutor) driver;
	   js.executeScript("window.scrollBy(0,100)"); 
	   wait.until(ExpectedConditions.elementToBeClickable(finishBtn));
	   finishBtn.click();	
	   System.out.println("Order placed Successfully");
}
}
