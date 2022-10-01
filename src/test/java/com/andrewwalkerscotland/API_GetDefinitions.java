package com.andrewwalkerscotland;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.filter.NotFilter.not;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Then("the response will have date header")
    public void theResponseWillHaveDateHeader() {
        assertThat(response.getHeader("date"))
            .as("Date header is not included in response.")
            .isEqualTo("Fri, 21 May");
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

    @Then("the Segment Type on every track will be music")
    public void theSegmentTypeOnEveryTrackWillBeMusic() {
        assertThat(mediaItems)
            .as("At least one data item has a segment type not set to music.")
            .filteredOn("segment_type", not("music")).isEmpty();
    }

    @Then("the primary title field on each item will have a value")
    public void thePrimaryTitleFieldOnEachItemWillHaveAValue() {
        assertThat(mediaItems)
            .as("At least one data item has an empty primary title field.")
            .extracting("title_list")
            .extracting("primary").doesNotContain("");
        assertThat(mediaItems)
            .as("At least one data item has a null primary title field.")
            .extracting("title_list")
            .extracting("primary").doesNotContainNull();
    }

    @Then("the now playing field will be true on only one item")
    public void theNowPlayingFieldWillBeTrueOnOnlyOneItem() {
        assertThat(mediaItems)
            .as("More than one item has now playing field set to true.")
            .extracting("offset")
            .extracting("now_playing").containsOnlyOnce(true);
    }
}
