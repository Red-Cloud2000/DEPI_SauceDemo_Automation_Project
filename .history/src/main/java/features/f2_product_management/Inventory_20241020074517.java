package features.f2_product_management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Inventory {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public Inventory(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Locators
    By productSort = By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select");


    public void sortProducts(String sortTechnique) throws InterruptedException {
        Select objSelect =new Select(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select")));
        switch (sortTechnique) {
            case "Name (A to Z)" -> {
                objSelect.selectByVisibleText("Name (A to Z)");
                Thread.sleep(3000);
            }
            case "Name (Z to A)" -> {
                objSelect.selectByVisibleText("Name (Z to A)");
                Thread.sleep(3000);
            }
            case "Price (low to high)" -> {
                objSelect.selectByVisibleText("Price (low to high)");
                Thread.sleep(3000);
            }
            case "Price (high to low)" -> {
                objSelect.selectByVisibleText("Price (high to low)");
                Thread.sleep(3000);
            }
        }
    }

}
