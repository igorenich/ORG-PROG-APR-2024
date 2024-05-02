package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PromoSideBarPage {

    private WebDriver driver;

    public PromoSideBarPage(WebDriver driver){
        this.driver = driver;
    }

    public void selectPromoByName(String name){
        WebElement sideBarBtn = driver.findElement(By.partialLinkText(name));
        sideBarBtn.click();
    }
}
