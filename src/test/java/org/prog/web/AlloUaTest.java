package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AlloUaTest {

    /*
        0. Make it parametrized test. Set search text as parameter: iPhone, Xiaomi, sennheiser
        1. load allo.ua
        2. search for paramter from DataProvider (webElement.sendKeys("phone name") then webElement.sentKeys(Keys.ENTER))
        3. Assert length of names for first 4 phones (webElement.getText()) > 0
     */

    private WebDriver driver;

    @BeforeSuite
    public void setUp() {
        this.driver = new ChromeDriver();
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
        driver.get("https://allo.ua/");
    }

    @Test
    public void testPromoInfo() {
        WebElement discountsBtn = driver.findElement(By.partialLinkText("Акції"));
        discountsBtn.click();
        WebElement presentBtn = driver.findElement(By.partialLinkText("Подарунки"));
        presentBtn.click();
        WebElement promoParent = driver.findElement(By.className("promo-list__items"));
        List<WebElement> promoList = promoParent.findElements(By.tagName("a"));
        WebElement firstPromo = promoList.get(0);
        firstPromo.click();
        WebElement promoTitle = driver.findElement(By.className("promo-info__title"));
        String title = promoTitle.getText();
        Assert.assertNotNull(title, "Promo title must not be null!");
    }

    @Test
    public void checkLoginFormTest() {
        WebElement profileBtn = driver.findElement(By.className("mh-profile"));
        profileBtn.click();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(3L));
        WebElement pwdBtnWait = driverWait.until(
                ExpectedConditions.presenceOfElementLocated(By.className("auth__enter-password")));
        pwdBtnWait.click();
        WebElement loginBtn = driverWait.until(
                ExpectedConditions.presenceOfElementLocated(By.className("a-button--primary")));
        loginBtn.click();
        List<WebElement> errorMessages = driver.findElements(By.className("a-input__message"));
        for (WebElement em : errorMessages) {
            Assert.assertEquals(em.getText(),
                    "Це поле є обов'язковим для заповнення.", "Bad error message!");
        }
    }
}
