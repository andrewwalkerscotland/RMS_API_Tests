package com.andrewwalkerscotland;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class API_GetDefinitions {

    private Response response;
    private ResponseBody mediaData;
    private List<Object> mediaItems = new ArrayList<>();

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

    @Then("the response time will be less than {int} second\\(s)")
    public void theResponseTimeWillBeLessThanSecond(int maxResponseTimeSeconds) {
        long maxResponseTimeMillis = maxResponseTimeSeconds * 1000L;
        StringBuilder failureMessage = new StringBuilder("Response took longer than ")
            .append(maxResponseTimeSeconds)
            .append(" second(s).");
        assertThat(response.getTime())
            .as(failureMessage.toString())
            .isLessThan(maxResponseTimeMillis);
    }

    @When("I examine the data items in the response")
    public void iCheckTheContentsOfEachItem() {
        mediaData = response.getBody();
        mediaItems = mediaData.jsonPath().getList("data");
    }

    @Then("the id field on each item will have a value")
    public void theIdFieldOnEachItemWillHaveAValue() {
        assertThat(mediaItems)
            .as("At least one data item has an empty Id field.")
            .extracting("id").doesNotContain("");
        assertThat(mediaItems)
            .as("At least one data item has a null Id field.")
            .extracting("id").doesNotContainNull();
    }
}
