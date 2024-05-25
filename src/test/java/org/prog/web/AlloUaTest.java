package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class AlloUaTest {

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

    @DataProvider(name = "searchData")
    public Object[][] searchData() {
        return new Object[][]{
                {"iPhone"},
                {"Xiaomi"},
                {"sennheiser"}
        };
    }

    @Test(dataProvider = "searchData")
    public void testSearchForPhone(String searchText) {
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys(searchText);
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-card__content")));

        List<WebElement> productCards = driver.findElements(By.className("product-card__content"));

        for (int i = 0; i < 4 && i < productCards.size(); i++) {
            WebElement productTitle = productCards.get(i).findElement(By.className("product-card__title"));
            String title = productTitle.getText();
            Assert.assertTrue(title.length() > 0, "Product title should have a length greater than 0");
        }
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