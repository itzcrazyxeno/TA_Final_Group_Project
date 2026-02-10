package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public class ProductDetailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By productInfo = By.cssSelector(".product-information");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("timeoutSeconds")));
    }

    public boolean isVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productInfo)).isDisplayed();
    }
}
