package org.prog.web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class IFrameDemo {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_images_trulli");
            WebElement iframe = driver.findElement(By.id("iframeResult"));
            driver.switchTo().frame(iframe);

            WebElement header = driver.findElement(By.tagName("h2"));
            System.out.println(header.getLocation());

            driver.switchTo().defaultContent();

            WebElement runBtn = driver.findElement(By.id("runbtn"));
            System.out.println(runBtn.getLocation());


        } finally {
            driver.quit();
        }
    }
}
