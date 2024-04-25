package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumHomework {

    /*
        1. load allo.ua
        2. search for iPhone or another Phone (webElement.sendKeys("phone name") then webElement.sentKeys(Keys.ENTER))
        3. print names for first 4 phones (webElement.getText())

     */

    public static void main(String[] args) {
        //start browser
        WebDriver driver = new ChromeDriver();
        try {
            //full screen
            driver.manage().window().maximize();
            //load allo ua
            driver.get("https://allo.ua/");
//            driver.findElement(By.name("search"));
//            driver.findElement(By.id("search-form__input"));
//            driver.findElement(By.cssSelector("#search-form__input"));
//            driver.findElement(By.cssSelector("#__layout > div > header > div.mh > div.mh__sr > div.mh-actions > div.mh-profile > button"));
//            driver.findElement(By.xpath("//*[@name='search']"));
            /**
             * id -> unique
             * name -> unique
             * className -> can duplicate
             * linkText -> only for hyperlinks
             */
            WebElement profileBtn = driver.findElement(By.className("mh-profile"));
            profileBtn.click();
            WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(3L));
            WebElement pwdBtnWait = driverWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.className("auth__enter-password")));
            pwdBtnWait.click();
            WebElement loginBtn = driverWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.className("a-button--primary")));
            loginBtn.click();
            System.out.println("done!");
        } finally {
            driver.quit();
        }
    }
}
