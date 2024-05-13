package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import org.prog.dto.PersonDto;
import org.prog.dto.ResponseDto;
import org.testng.Assert;

import java.util.List;

public class RestSteps {

    public static List<PersonDto> randomPersons;

    @Given("i request random people from randomuser.me")
    public void requestRandomPeople() {
        ResponseDto responseDto = RestAssured.given()
                .baseUri("https://randomuser.me/")
                .basePath("api/")
                .queryParam("inc", "gender,name,nat")
                .queryParam("noinfo")
                .queryParam("results", 2)
                .get()
                .as(ResponseDto.class);
        Assert.assertFalse(responseDto.getResults().isEmpty(),
                "At least one user must be retrieved!");
        randomPersons = responseDto.getResults();
    }
}
