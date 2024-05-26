package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class AlloSteps {

    private WebDriver driver;
    private List<WebElement> productCards;

    @Given("I open the Allo UA website")
    public void iOpenTheAlloUaWebsite() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://allo.ua/");
    }

    @When("I search for {string}")
    public void iSearchFor(String query) {
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys(query);
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-card__content")));
        productCards = driver.findElements(By.className("product-card__content"));
    }

    @Then("I should see at least 4 product titles in the search results")
    public void iShouldSeeAtLeastProductTitlesInTheSearchResults() {
        Assert.assertTrue(productCards.size() >= 4, "Less than 4 products found");

        for (int i = 0; i < 4 && i < productCards.size(); i++) {
            WebElement productTitle = productCards.get(i).findElement(By.className("product-card__title"));
            System.out.println(productTitle.getText());
        }

        driver.quit();
    }
}