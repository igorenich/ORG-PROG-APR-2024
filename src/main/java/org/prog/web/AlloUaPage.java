package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AlloUaPage {

    private final WebDriver driver;
    private final WebDriverWait driverWait;
    private String firstProductPrice;

    public AlloUaPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void loadPage() {
        driver.get("https://allo.ua/");
    }

    public void searchForGoods(String searchValue) {
        WebElement searchInput = driver.findElement(By.id("search-form__input"));
        searchInput.sendKeys(searchValue);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void waitForSearchResultsToBeAtLeast(int count) {
        driverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("product-card"), count));
    }

    public List<WebElement> getSearchResults() {
        return driver.findElements(By.className("products-layout__item"));
    }

    public String getGoodsPrice(WebElement goodsElement) {
        WebElement priceElement = goodsElement.findElement(By.className("v-pb__cur"));
        return priceElement.findElement(By.className("sum")).getText();
    }

    public void openFirstProductPage() {
        List<WebElement> searchResults = getSearchResults();
        if (!searchResults.isEmpty()) {
            WebElement firstProduct = searchResults.get(0);
            firstProduct.click();
        }
    }

    public String getProductPagePrice() {
        WebElement priceElement = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-pb__cur")));
        return priceElement.findElement(By.className("sum")).getText();
    }

    public String getProductTitle() {
        WebElement titleElement = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.product-name")));
        return titleElement.getText();
    }

    public void comparePrices() {
        System.out.println("First product price from search results: " + firstProductPrice);
        String productPagePrice = getProductPagePrice();
        System.out.println("Product page price: " + productPagePrice);
        System.out.println("Product title: " + getProductTitle());

        if (firstProductPrice.equals(productPagePrice)) {
            System.out.println("Prices match!");
        } else {
            System.out.println("Prices do not match!");
        }
    }

    public void saveFirstProductPrice() {
        List<WebElement> searchResults = getSearchResults();
        if (!searchResults.isEmpty()) {
            WebElement firstProduct = searchResults.get(0);
            firstProductPrice = getGoodsPrice(firstProduct);
        }
    }

    public List<WebElement> getHomePageSales() {
        return driver.findElements(By.className("h-pc"));
    }
}