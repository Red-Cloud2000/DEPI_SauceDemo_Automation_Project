package features.f2_product_management;

import config.config;
import features.Base.BaseTest;
import features.f1_auth.LoginPage;
import helper.transformers.JSONHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class InventorySortTest extends BaseTest {

        WebDriver driver;
        LoginPage loginPage;
        Inventory inventory;

        @BeforeTest
        public void setup() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            loginPage = new LoginPage(driver);
            inventory = new Inventory(driver);
        }

        @BeforeMethod
        public void setupMethod() {
            driver.get(config.base_url);
        }


        @Test(priority = 1)
        public void test_sorting() throws FileNotFoundException, InterruptedException {
            String[] testData = JSONHelper.parseJSON(config.JsonFiles.AUTH_TEST_DATA.getFileName(), 0);
            if (testData[3].equals("valid_credentials")) {
                loginPage.login(testData[0], testData[1]);
                inventory.sortProducts("Price (high to low)");
                inventory.sortProducts("Name (Z to A)");
                inventory.sortProducts("Name (A to Z)");

            } else {
                System.out.println("Wrong Data!");
            }
        }


        @AfterTest
        public void tearDown() {
            driver.quit();
        }

}
