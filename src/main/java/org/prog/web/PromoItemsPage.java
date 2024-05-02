package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PromoItemsPage {

    private WebDriver driver;

    public PromoItemsPage(WebDriver driver){
        this.driver = driver;
    }

    public List<WebElement> getPromoItems(){
        WebElement promoParent = driver.findElement(By.className("promo-list__items"));
        return promoParent.findElements(By.tagName("a"));
    }

    public void switchToPromoPage(int pageNumber){
        // not implemented
    }
}
