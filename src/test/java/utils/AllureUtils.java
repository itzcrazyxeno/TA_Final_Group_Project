package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.nio.charset.StandardCharsets;

public class AllureUtils {

    public static void attachText(String name, String text) {
        Allure.addAttachment(name, "text/plain", text);
    }

    public static void attachJson(String name, String json) {
        Allure.addAttachment(name, "application/json", json);
    }

    public static void attachScreenshot(WebDriver driver, String name) {
        if (driver == null) return;
        byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.getLifecycle().addAttachment(name, "image/png", "png", bytes);
    }

    public static void attachRequestResponse(String request, String response) {
        Allure.addAttachment("Request", "text/plain", request, StandardCharsets.UTF_8.name());
        Allure.addAttachment("Response", "application/json", response, StandardCharsets.UTF_8.name());
    }
}
