package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public class ProductsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By allProductsTitle = By.xpath("//h2[contains(normalize-space(),'All Products')]");
    private final By searchBox = By.id("search_product");
    private final By searchBtn = By.id("submit_search");
    private final By searchedTitle = By.xpath("//h2[contains(normalize-space(),'Searched Products')]");
    private final By firstAddToCart = By.xpath("(//a[contains(@class,'add-to-cart')])[1]");
    private final By cartModal = By.id("cartModal");
    private final By viewCartBtn = By.xpath("//a[normalize-space()='View Cart']");
    private final By continueShoppingBtn = By.xpath("//button[contains(normalize-space(),'Continue Shopping')]");
    private final By firstViewProduct = By.xpath("(//a[normalize-space()='View Product'])[1]");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("timeoutSeconds")));
    }

    public boolean isVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(allProductsTitle)).isDisplayed();
    }

    public void search(String keyword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).clear();
        driver.findElement(searchBox).sendKeys(keyword);
        driver.findElement(searchBtn).click();
    }

    public boolean isSearchResultVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchedTitle)).isDisplayed();
    }

    public void openFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstViewProduct)).click();
    }

    public void addFirstProductToCartAndGoToCart() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(firstAddToCart));
        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartModal));
        wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn)).click();
    }

    public void addFirstProductToCartAndContinue() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(firstAddToCart));
        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartModal));
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cartModal));
    }
}
