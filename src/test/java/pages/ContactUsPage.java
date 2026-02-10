package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public class ContactUsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By getInTouch = By.xpath("//*[contains(text(),'Get In Touch')]");
    private final By name = By.name("name");
    private final By email = By.name("email");
    private final By subject = By.name("subject");
    private final By message = By.name("message");
    private final By submit = By.name("submit");
    private final By success = By.xpath("//*[contains(text(),'Success! Your details have been submitted successfully')]");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("timeoutSeconds")));
    }

    public boolean isVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getInTouch)).isDisplayed();
    }

    public void submitForm(String n, String e, String s, String m) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(name)).sendKeys(n);
        driver.findElement(email).sendKeys(e);
        driver.findElement(subject).sendKeys(s);
        driver.findElement(message).sendKeys(m);
        driver.findElement(submit).click();
        driver.switchTo().alert().accept();
    }

    public boolean isSuccessVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(success)).isDisplayed();
    }
}
