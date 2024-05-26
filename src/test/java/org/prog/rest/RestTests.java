package org.prog.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.prog.dto.LocationDto;
import org.prog.dto.PersonDto;
import org.prog.dto.ResponseDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestTests {

    @Test
    public void restTest() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://randomuser.me/");
        requestSpecification.basePath("api/");
        requestSpecification.queryParam("inc", "gender,name,nat,location");
        requestSpecification.queryParam("noinfo");

        Response response = requestSpecification.get();
        response.prettyPrint();

        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);
        validatableResponse.contentType(ContentType.JSON);

        ResponseDto responseDto = response.as(ResponseDto.class);
        Assert.assertFalse(responseDto.getResults().isEmpty(),
                "No persons generated! Failing test,");

        // Перевірка, що опис часового поясу не є пустим
        for (PersonDto person : responseDto.getResults()) {
            LocationDto location = person.getLocation();
            Assert.assertNotNull(location.getTimezone().getDescription(),
                    "Timezone description is empty!");
        }
    }

    @Test
    public void prettyTest() {
        RestAssured.given()
                .baseUri("https://randomuser.me/")
                .basePath("api/")
                .queryParam("inc", "gender,name,nat,location")
                .queryParam("noinfo")
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
}