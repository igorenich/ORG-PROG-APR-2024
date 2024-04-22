package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumDemo {

    /*
     * 1. Open browser +
     * 2. Load allo.ua +
     * 3. Click Акції +
     * 4. Click Present +
     * 5. Click first present +
     * 6. Print present's name
     */

    public static void main(String[] args) {
        //start browser
        WebDriver driver = new ChromeDriver();
        try {
            //full screen
            driver.manage().window().maximize();
            //load allo ua
            driver.get("https://allo.ua/");
            //select discounts
            WebElement discountsBtn = driver.findElement(By.partialLinkText("Акції"));
            discountsBtn.click();
            // click presents
            WebElement presentBtn = driver.findElement(By.partialLinkText("Подарунки"));
            presentBtn.click();
            //find promo items parent element
            WebElement promoParent = driver.findElement(By.className("promo-list__items"));
            //find and click 1st promo item
            List<WebElement> promoList = promoParent.findElements(By.tagName("a"));
            WebElement firstPromo = promoList.get(0);
            firstPromo.click();
            // print promo title
            WebElement promoTitle = driver.findElement(By.className("promo-info__title"));
            System.out.println(promoTitle.getText());
        } finally {
            driver.quit();
        }
    }
}
