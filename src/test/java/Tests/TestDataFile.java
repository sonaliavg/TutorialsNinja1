package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseTest;
import allPageObjects.CartPage;
import allPageObjects.CheckoutPage;
import allPageObjects.HomePage;
import allPageObjects.LoginPage;
import allPageObjects.ProductPage;

public class TestDataFile extends BaseTest {
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


    @Test(dataProvider="CredentialsSupplier")
    public void LoginTestWithValidCredentials(String username, String password) {
        System.out.println(username + " ----- " + password);
    }

    @DataProvider(name="CredentialsSupplier")
    public Object[][] dataSupplier() throws IOException {
       
        String excelFilePath = System.getProperty("user.dir") + "/src/main/java/allTestData/TestData.xlsx";
        File excelFile = new File(excelFilePath);
        FileInputStream fis = new FileInputStream(excelFile);
        
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("Login");
        int rowCount = sheet.getPhysicalNumberOfRows();
        int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] data = new Object[rowCount - 1][columnCount];
        for (int r = 1; r < rowCount; r++) {
            XSSFRow row = sheet.getRow(r);
            
            for (int c = 0; c < columnCount; c++) {
                XSSFCell cell = row.getCell(c);
              if (cell == null) {
                    data[r - 1][c] = "";
                } else {
                    CellType cellType = cell.getCellType();
                    
                    switch (cellType) {
                        case STRING:
                            data[r - 1][c] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[r - 1][c] = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            data[r - 1][c] = cell.getBooleanCellValue();
                            break;
                        default:
                            data[r - 1][c] = "";
                            break;
                    }
                }
            }
        }
        
        
        wb.close();
        fis.close();
        return data;
    }
}
