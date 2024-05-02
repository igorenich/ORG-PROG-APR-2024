package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AlloUaPage {



    private WebDriver driver;
    private WebDriverWait driverWait;

    private PromoSideBarPage promoSideBarPage;
    private PromoItemsPage promoItemsPage;

    public AlloUaPage(WebDriver driver) {
        this.driver = driver;
        this.promoSideBarPage = new PromoSideBarPage(driver);
        this.promoItemsPage = new PromoItemsPage(driver);
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(3L));
    }

    public void loadPage() {
        driver.get("https://allo.ua/");
    }

    public void openDiscounts() {
        WebElement discountsBtn = driver.findElement(By.partialLinkText("Акції"));
        discountsBtn.click();
    }

    public void openPresents() {
        promoSideBarPage.selectPromoByName("Подарунки");
    }

    public List<WebElement> getPromoItems() {
        return promoItemsPage.getPromoItems();
    }

    public String getPromoTitle() {
        WebElement promoTitle = driver.findElement(By.className("promo-info__title"));
        return promoTitle.getText();
    }

    public void openLoginForm() {
        WebElement profileBtn = driver.findElement(By.className("mh-profile"));
        profileBtn.click();
        WebElement pwdBtnWait = driverWait.until(
                ExpectedConditions.presenceOfElementLocated(By.className("auth__enter-password")));
        pwdBtnWait.click();
    }

    public void clickLoginButton() {
        WebElement loginBtn = driverWait.until(
                ExpectedConditions.presenceOfElementLocated(By.className("a-button--primary")));
        loginBtn.click();
    }

    public List<WebElement> getErrorElements() {
        return driver.findElements(By.className("a-input__message"));
    }
}
