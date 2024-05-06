package org.prog.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class AlloUaTest {

    /*
        1. Refactor tests to use PageObject pattern
        2. Pick 1st item among first 4 items
        3. "Store" its price (as variable)
        4. Go to goods page
        5. Assert that page price is same as "stored" price
     */

    private WebDriver driver;

    private AlloUaPage alloUaPage;

    @BeforeSuite
    public void setUp() {
        this.driver = new ChromeDriver();
        this.alloUaPage = new AlloUaPage(driver);
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void tearDown() {
        Assert.assertNotNull(driver, "Driver has not been initialized!");
        driver.quit();
    }

    @BeforeMethod
    public void preTest() {
        Assert.assertNotNull(driver, "Driver has not been initialized!");
        alloUaPage.loadPage();
    }

    //    @Test
    public void testPromoInfo() {
        alloUaPage.openDiscounts();
        alloUaPage.openPresents();
        List<WebElement> promoItems = alloUaPage.getPromoItems();
        promoItems.get(0).click();
        String title = alloUaPage.getPromoTitle();
        Assert.assertNotNull(title, "Promo title must not be null");
    }

    //    @Test
    public void checkLoginFormTest() {
        alloUaPage.openLoginForm();
        alloUaPage.clickLoginButton();
        List<WebElement> errorMessages = alloUaPage.getErrorElements();
        for (WebElement em : errorMessages) {
            Assert.assertEquals(em.getText(),
                    "Це поле є обов'язковим для заповнення.", "Bad error message!");
        }
    }

//    @Test
    public void testSearch() {
        alloUaPage.searchForGoods("samsung");
        alloUaPage.waitForSearchResultsToBeAtLeast(1);
        List<WebElement> goods = alloUaPage.getSearchResults();
        int goodsCount = 0;
        if (goods.size() > 4) {
            goodsCount = 4;
        } else {
            goodsCount = goods.size();
        }

        for (int i = 0; i < goodsCount; i++) {
            System.out.println(alloUaPage.getGoodsPrice(goods.get(i)));
        }
    }

    @Test
    public void testHomePageSales() {
        List<WebElement> goods = alloUaPage.getHomePageSales();
        for (WebElement webElement : goods) {
            System.out.println(alloUaPage.getGoodsPrice(webElement));
        }
    }
}
