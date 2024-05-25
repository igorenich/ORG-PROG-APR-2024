package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumDemo {

    /*
     * 1. Open browser +
     * 2. Load allo.ua +
     * 3. Click profile button +
     * 4. Click login and password +
     * 5. Enter incorrect login and password +
     * 6. Click login button +
     * 7. Print error message
     */

    public static void main(String[] args) {
        // Start browser
        WebDriver driver = new ChromeDriver();
        try {
            // Maximize window
            driver.manage().window().maximize();
            // Set implicit wait
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            // Load allo.ua
            driver.get("https://allo.ua/");

            WebElement profileButton = driver.findElement(By.xpath("//button[@aria-label='Профіль']"));
            profileButton.click();
            // Click login and password
            WebElement loginPasswordBtn = driver.findElement(By.xpath("//span[contains(text(), 'Логін та пароль')]"));
            loginPasswordBtn.click();

            WebElement loginField = driver.findElement(By.name("phoneEmail"));
            loginField.clear();
            loginField.sendKeys("ффф");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.clear();
            passwordField.sendKeys("");

            WebElement loginBtn = driver.findElement(By.xpath("//span[contains(text(), 'Увійти')]"));
            loginBtn.click();

            WebElement loginErrorMessage = driver.findElement(By.xpath("//span[@class='a-input__message base-message is-error'][contains(text(),'некоректний email')]"));
            System.out.println("Логін: " + loginErrorMessage.getText());
            WebElement passwordErrorMessage = driver.findElement(By.xpath("//span[contains(@class,'a-input__message')][contains(text(),\"Це поле є обов'язковим для заповнення\")]"));
            System.out.println("Пароль: " + passwordErrorMessage.getText());
        } finally {
            driver.quit();
        }
    }
}