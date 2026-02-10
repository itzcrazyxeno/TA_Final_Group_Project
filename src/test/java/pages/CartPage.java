package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartTable = By.id("cart_info");
    private final By cartRows = By.cssSelector("#cart_info tbody tr");
    private final By deleteButtons = By.cssSelector(".cart_quantity_delete");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    public boolean isVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartTable)).isDisplayed();
    }

    public int itemsCount() {
        wait.until(ExpectedConditions.presenceOfElementLocated(cartTable));
        List<?> rows = driver.findElements(cartRows);
        return rows.size();
    }

    public void removeFirstIfExists() {
        wait.until(ExpectedConditions.presenceOfElementLocated(cartTable));
        List<?> buttons = driver.findElements(deleteButtons);
        if (!buttons.isEmpty()) {
            driver.findElements(deleteButtons).get(0).click();
            wait.until(d -> d.findElements(cartRows).size() == 0);
        }
    }
}