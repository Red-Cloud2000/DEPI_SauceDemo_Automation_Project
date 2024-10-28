package features.f2_product_management;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ProductDetailsTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryList inventoryList;
    private ProductPage productPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventoryList = new InventoryList(driver);
        productPage = new ProductPage(driver);
    }

    @BeforeMethod
    public void navigateToInventory() {
        driver.get(config.base_url);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testProductDisplayOnProductPage() {
        List<WebElement> products = inventoryList.getAllProducts();
        
        for (WebElement product : products) {
            product = product.findElement(.productName);
            String expectedProductName = inventoryList.getProductName(product);
            product.click();
            
            boolean areDetailsDisplayed = productPage.areProductDetailsDisplayed();
            String actualProductName = productPage.getProductName();
            
            BaseTest.validateTestResult(areDetailsDisplayed && actualProductName.equals(expectedProductName), 
                                        "TestCase ID:DPSAT-T38 - Verify Product Display on Product Page for " + expectedProductName);
            Assert.assertTrue(areDetailsDisplayed, "All product details should be displayed on the product page for " + expectedProductName);
            Assert.assertEquals(actualProductName, expectedProductName, "The correct product should be displayed: " + expectedProductName);
            
            driver.navigate().back();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
