package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By homeSlider = By.cssSelector("#slider-carousel");
    private final By productsLink = By.cssSelector("a[href='/products']");
    private final By contactUsLink = By.cssSelector("a[href='/contact_us']");

    private final By subscriptionEmail = By.id("susbscribe_email");
    private final By subscriptionButton = By.id("subscribe");
    private final By subscriptionSuccess = By.cssSelector(".alert-success.alert");

    private final By scrollUpContainer = By.cssSelector("#scrollUp");
    private final By scrollUpLink = By.cssSelector("#scrollUp a");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    public boolean isHomeVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(homeSlider)).isDisplayed();
    }

    public void openProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(productsLink)).click();
    }

    public void openContactUs() {
        wait.until(ExpectedConditions.elementToBeClickable(contactUsLink)).click();
    }

    public void subscribe(String email) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionEmail)).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(subscriptionButton)).click();
    }

    public boolean isSubscriptionSuccessVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionSuccess)).isDisplayed();
    }

    public void scrollDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public boolean isScrollUpVisible() {
        scrollDown();

        if (exists(scrollUpLink)) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(scrollUpLink)).isDisplayed();
        }

        if (exists(scrollUpContainer)) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(scrollUpContainer)).isDisplayed();
        }

        return false;
    }

    public void scrollUp() {
        scrollDown();

        if (exists(scrollUpLink)) {
            wait.until(ExpectedConditions.elementToBeClickable(scrollUpLink)).click();
            return;
        }

        if (exists(scrollUpContainer)) {
            wait.until(ExpectedConditions.elementToBeClickable(scrollUpContainer)).click();
            return;
        }

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    public boolean isAtTop() {
        Object y = ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;");
        if (y instanceof Number) {
            return ((Number) y).longValue() < 200;
        }
        return false;
    }

    private boolean exists(By locator) {
        List<WebElement> els = driver.findElements(locator);
        return !els.isEmpty();
    }
}