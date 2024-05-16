package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import org.prog.dto.PersonDto;
import org.prog.dto.ResponseDto;
import org.prog.util.DataHolder;
import org.testng.Assert;

import javax.xml.crypto.Data;
import java.util.List;

public class RestSteps {

    @Given("i request {int} random people from randomuser.me as {string}")
    public void requestRandomPeople(int amount, String alias) {
        ResponseDto responseDto = RestAssured.given()
                .baseUri("https://randomuser.me/")
                .basePath("api/")
                .queryParam("inc", "gender,name,nat")
                .queryParam("noinfo")
                .queryParam("results", amount)
                .get()
                .as(ResponseDto.class);
        Assert.assertFalse(responseDto.getResults().isEmpty(),
                "At least one user must be retrieved!");
        DataHolder.dataHolder.put(alias, responseDto.getResults());
    }
}
