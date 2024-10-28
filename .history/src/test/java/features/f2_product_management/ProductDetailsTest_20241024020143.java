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
            String productName = inventoryList.getProductName(product);
            System.out.println("\nTesting product: " + productName);
            product.click();
            
            boolean areDetailsDisplayed = productPage.areProductDetailsDisplayed();
            String productPageName = productPage.getProductName();
            String productPageDescription = productPage.getProductDescription();
            String productPagePrice = productPage.getProductPrice();
            
            BaseTest.validateTestResult(areDetailsDisplayed, 
                                        "TestCase ID:DPSAT-T38 - Verify Product Display on Product Page for " + productName);
            Assert.assertTrue(areDetailsDisplayed, "All product details should be displayed on the product page");
            Assert.assertEquals(productPageName, productName, "The correct product name should be displayed");
            Assert.assertFalse(productPageDescription.isEmpty(), "Product description should not be empty");
            Assert.assertFalse(productPagePrice.isEmpty(), "Product price should not be empty");
            
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
