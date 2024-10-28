package features.f2_product_management;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class InventorySortTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventorySort inventorySort;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventorySort = new InventorySort(driver);
    }

    @BeforeMethod
    public void navigateToInventory() {
        driver.get(config.base_url);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testProductSortingByPriceLowToHigh() {
        inventorySort.sortBy("Price (low to high)");
        boolean isSorted = inventorySort.isPriceSortedLowToHigh();
        BaseTest.validateTestResult(isSorted, "TestCase ID:DPSAT-T35 - Verify Product Sorting Functionality by Price (Low to High)");
        Assert.assertTrue(isSorted, "Products should be sorted by price from low to high");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
