package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumHomework {

    public static void main(String[] args) {
        //start browser
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://allo.ua/");

            WebElement searchBox = driver.findElement(By.name("search"));
            searchBox.sendKeys("Samsung");
            searchBox.sendKeys(Keys.ENTER);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-card__content")));
            List<WebElement> productCards = driver.findElements(By.className("product-card__content"));
            for (int i = 0; i < 4 && i < productCards.size(); i++) {
                WebElement productTitle = productCards.get(i).findElement(By.className("product-card__title"));
                System.out.println(productTitle.getText());
            }
        } finally {
            driver.quit();
        }
    }
}