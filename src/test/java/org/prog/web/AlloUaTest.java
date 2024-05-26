package org.prog.web;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class AlloUaTest {

    private WebDriver driver;
    private AlloUaPage alloUaPage;

    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
        alloUaPage = new AlloUaPage(driver);
    }

    @Test
    public void testSearchAndComparePrice() {
        alloUaPage.loadPage();
        alloUaPage.searchForGoods("Xiaomi");
        alloUaPage.waitForSearchResultsToBeAtLeast(1);

        List<WebElement> searchResults = alloUaPage.getSearchResults();
        WebElement firstProduct = searchResults.get(0);
        String firstProductPrice = alloUaPage.getGoodsPrice(firstProduct);

        System.out.println("First product price from search results: " + firstProductPrice);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        alloUaPage.openFirstProductPage();

        String productPagePrice = alloUaPage.getProductPagePrice();
        System.out.println("Product page price: " + productPagePrice);

        Assert.assertEquals(firstProductPrice, productPagePrice, "The price on the search page does not match the price on the product page!");
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}