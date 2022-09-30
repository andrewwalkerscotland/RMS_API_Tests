package com.andrewwalkerscotland;

import io.restassured.http.ContentType;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class API_GetDefinitions {

    private ValidatableResponse validatableResponse;

    @Given("I send request to the media url")
    public void iSendRequestToTheMediaUrl() {
        String endpoint = "http://dummy.restapiexample.com/api/v1/employee/1";
        validatableResponse =
            given().contentType(ContentType.JSON)
            .when().get(endpoint)
            .then();
    }

    @Then("the response will return ok status")
    public void theResponseWillReturnStatus() {
        validatableResponse.log().ifValidationFails().statusCode(HttpStatus.SC_OK);
    }
}
