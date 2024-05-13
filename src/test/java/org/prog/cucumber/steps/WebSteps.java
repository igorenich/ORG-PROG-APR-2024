package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.prog.web.GooglePage;
import org.testng.Assert;

import java.util.List;

public class WebSteps {

    public static GooglePage googlePage;

    @Given("i load google page")
    public void loadGooglePage() {
        Assert.assertNotNull(googlePage, "Please, init google page object before using this step!");
        googlePage.load();
        googlePage.acceptCookiesIfPresent();
    }

    @When("i search for random user")
    public void searchForRandomUser() {
        Assert.assertNotNull(googlePage, "Please, init google page object before using this step!");
        Assert.assertNotNull(DBSteps.randomUserName, "Please, pick a random person from DB first!");

        googlePage.executeSearch(DBSteps.randomUserName);
    }

    @Then("i see random user's name in search results")
    public void validateSearchResultsForRandomName() {
        Assert.assertNotNull(googlePage, "Please, init google page object before using this step!");
        Assert.assertNotNull(DBSteps.randomUserName, "Please, pick a random person from DB first!");

        String userName = DBSteps.randomUserName;
        List<WebElement> searchResults = googlePage.getSearchHeaders();

        int searchCounter = 0;
        for (WebElement searchHeader : searchResults) {
            if (searchHeader.getText().contains(userName)) {
                searchCounter++;
            }
        }
        Assert.assertTrue(searchCounter >= 3, "Less than 3 results for name " + userName);
    }
}
