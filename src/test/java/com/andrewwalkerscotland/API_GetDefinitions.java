package com.andrewwalkerscotland;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API_GetDefinitions {

    private Response response;

    @Given("I send a request to the media endpoint")
    public void getOttPlatformMedia() {
        String endpoint = "https://testapi.io/api/ottplatform/media";
        response = given()
            .contentType(ContentType.JSON)
            .get(endpoint);
    }

    @Then("the response will have status {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        assertThat(response.getStatusCode())
            .as("Response code was not 200 OK")
            .isEqualTo(expectedStatusCode);
    }

    @And("the response time will be less than {int} second")
    public void theResponseTimeWillBeLessThanSecond(int maxResponseTimeSeconds) {
        long maxResponseTimeMillis = maxResponseTimeSeconds * 1000L;
        StringBuilder failureMessage = new StringBuilder("Response took longer than ")
            .append(maxResponseTimeSeconds)
            .append(" seconds");
        assertThat(response.getTime())
            .as(failureMessage.toString())
            .isLessThan(maxResponseTimeMillis);
    }
}
