package org.prog.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.prog.dto.ResponseDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestTests {

    /**
     * HOMEWORK 8:
     * 1. call https://randomuser.me/api/?inc=gender,name,nat,location&noinfo
     * 2. Add location object with all details to PersonDto
     * 3. Add assertion that validates location.timezone.description is not empty
     */

    @Test
    public void restTest() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://randomuser.me/");
        requestSpecification.basePath("api/");
        requestSpecification.queryParam("inc", "gender,name,nat");
//TODO: UNCOMMENT >>>> requestSpecification.queryParam("inc", "gender,name,nat,location");
        requestSpecification.queryParam("noinfo");

        Response response = requestSpecification.get();
//TODO: SAMPLE>>> String[] names = response.jsonPath().get("results.name.first");
        response.prettyPrint();

        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);
        validatableResponse.contentType(ContentType.JSON);

        ResponseDto responseDto = response.as(ResponseDto.class);
        Assert.assertFalse(responseDto.getResults().isEmpty(),
                "No persons generated! Failing test,");
    }

    @Test
    public void prettyTest() {
        RestAssured.given()
                .baseUri("https://randomuser.me/")
                .basePath("api/")
                .queryParam("inc", "gender,name,nat")
                .queryParam("noinfo")
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
}
