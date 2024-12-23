package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import allPageObjects.CartPage;
import allPageObjects.CheckoutPage;
import allPageObjects.HomePage;
import allPageObjects.LoginPage;
import allPageObjects.ProductPage;

public class OrderPlacement extends BaseTest{
	public WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutpage;
   @BeforeClass
   public void setUp() {
   	driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
   	homePage = new HomePage(driver);
    loginPage = new LoginPage(driver);
    productPage = new ProductPage(driver);
    cartPage=new CartPage(driver);
    checkoutpage=new CheckoutPage(driver);
       }
   
   @Test(priority = 1)
   public void Orderplacement() throws InterruptedException {
       loginPage.login(prop.getProperty("validusername"), prop.getProperty("validpassword"));
       homePage.clickOnFirstItem();
       productPage.addItemToCart();
       productPage.goToCart();  
       cartPage.clickOnCheckout();
       checkoutpage.fillCheckoutForm(prop.getProperty("firstname"), prop.getProperty("lastname"),prop.getProperty("postalcode"));
       checkoutpage.clickContinue();
       checkoutpage.verifyOverviewAndSubmitOrder();
       }
   
   @AfterClass
   public void tearDown() {
       if (driver != null) {
           driver.quit();
       }
   }

}
