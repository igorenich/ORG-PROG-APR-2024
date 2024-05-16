package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.prog.util.DataHolder;
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

    @When("i search for {string}")
    public void searchForRandomUser(String alias) {
        Assert.assertNotNull(googlePage, "Please, init google page object before using this step!");
        Assert.assertNotNull(DataHolder.dataHolder.get(alias),
                "Please, pick a random person from DB first!");

        googlePage.executeSearch((String) DataHolder.dataHolder.get(alias));
    }

    @Then("i see at least {int} search results for {string}")
    public void validateSearchResultsForRandomName(int amount, String alias) {
        Assert.assertNotNull(googlePage, "Please, init google page object before using this step!");
        Assert.assertNotNull((String) DataHolder.dataHolder.get(alias),
                "Please, pick a random person from DB first!");

        String userName = (String) DataHolder.dataHolder.get(alias);
        List<WebElement> searchResults = googlePage.getSearchHeaders();

        int searchCounter = 0;
        for (WebElement searchHeader : searchResults) {
            if (searchHeader.getText().contains(userName)) {
                searchCounter++;
            }
        }
        Assert.assertTrue(searchCounter >= amount, "Less than 3 results for name " + userName);
    }

    @Given("my test user {string} is {string}")
    public void setTestUser(String alias, String value) {
        DataHolder.dataHolder.put(alias, value);
    }
}
