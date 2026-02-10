package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("ui.baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}