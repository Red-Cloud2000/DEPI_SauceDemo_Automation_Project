package features.f2_product_management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.;
import java.util.List;

public class InventorySort {
    private WebDriver driver;

    // Locators
    private By sortDropdown = By.className("product_sort_container");
    private By productPrices = By.className("inventory_item_price");

    public InventorySort(WebDriver driver) {
        this.driver = driver;
    }

    public void sortBy(String option) {
        Select dropdown = new Select(driver.findElement(sortDropdown));
        dropdown.selectByVisibleText(option);
    }

    public boolean isPriceSortedLowToHigh() {
        List<WebElement> prices = driver.findElements(productPrices);
        double previousPrice = Double.MIN_VALUE;
        for (WebElement priceElement : prices) {
            double currentPrice = Double.parseDouble(priceElement.getText().replace("$", ""));
            if (currentPrice < previousPrice) {
                return false;
            }
            previousPrice = currentPrice;
        }
        return true;
    }
}
