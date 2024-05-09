package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GooglePage {

    // TODO: OPTIONAL HOMEWORK: Search for Ben Affleck

    private WebDriver driver;
    private WebDriverWait driverWait;

    private final static String URL = "https://google.com/";

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(3L));
    }

    public void load() {
        driver.get(URL);
    }

    public void acceptCookiesIfPresent() {
        List<WebElement> cookieButtons = driver.findElements(By.xpath(
                "//a[contains(@href, '/cookies')]/../../../..//button"
        ));
        if (!cookieButtons.isEmpty()) {
            cookieButtons.get(3).click();
        }
    }

    public void executeSearch(String searchValue) {
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys(searchValue);
        searchField.sendKeys(Keys.ENTER);
    }

    public List<WebElement> getSearchHeaders() {
        return driverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("//a/h3"), 1));
    }
}
