package ui_tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class UiTests extends BaseTest {

    @Test
    public void TC01_verifyHomePageVisible() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isHomeVisible());
    }

    @Test
    public void TC02_openProductsPage() {
        HomePage home = new HomePage(driver);
        home.openProducts();
        ProductsPage products = new ProductsPage(driver);
        Assert.assertTrue(products.isVisible());
    }

    @Test
    public void TC03_searchProduct() {
        HomePage home = new HomePage(driver);
        home.openProducts();
        ProductsPage products = new ProductsPage(driver);
        products.search("Top");
        Assert.assertTrue(products.isSearchResultVisible());
    }

    @Test
    public void TC04_viewProductDetails() {
        HomePage home = new HomePage(driver);
        home.openProducts();
        ProductsPage products = new ProductsPage(driver);
        products.openFirstProduct();
        ProductDetailsPage details = new ProductDetailsPage(driver);
        Assert.assertTrue(details.isVisible());
    }

    @Test
    public void TC05_addProductToCart() {
        HomePage home = new HomePage(driver);
        home.openProducts();
        ProductsPage products = new ProductsPage(driver);
        products.addFirstProductToCartAndGoToCart();
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isVisible());
        Assert.assertTrue(cart.itemsCount() > 0);
    }

    @Test
    public void TC06_openCartPage() {
        driver.get("https://automationexercise.com/view_cart");
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isVisible());
    }

    @Test
    public void TC07_removeProductFromCart() {
        HomePage home = new HomePage(driver);
        home.openProducts();
        ProductsPage products = new ProductsPage(driver);
        products.addFirstProductToCartAndGoToCart();

        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isVisible());
        Assert.assertTrue(cart.itemsCount() > 0);

        cart.removeFirstIfExists();
        Assert.assertEquals(cart.itemsCount(), 0);
    }

    @Test
    public void TC08_contactUsFormSubmit() {
        HomePage home = new HomePage(driver);
        home.openContactUs();
        ContactUsPage contact = new ContactUsPage(driver);
        Assert.assertTrue(contact.isVisible());
        contact.submitForm("Test User", "testuser@mail.com", "Subject", "Message");
        Assert.assertTrue(contact.isSuccessVisible());
    }

    @Test
    public void TC09_verifySubscriptionOnHome() {
        HomePage home = new HomePage(driver);
        home.subscribe("sub" + System.currentTimeMillis() + "@mail.com");
        Assert.assertTrue(home.isSubscriptionSuccessVisible());
    }

    @Test
    public void TC10_scrollUpButtonVisible() {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");

        org.openqa.selenium.support.ui.WebDriverWait wait =
                new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(12));

        org.openqa.selenium.By scrollUp = org.openqa.selenium.By.id("scrollUp");

        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(scrollUp));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(scrollUp));

        org.testng.Assert.assertTrue(driver.findElement(scrollUp).isDisplayed());
    }
}
