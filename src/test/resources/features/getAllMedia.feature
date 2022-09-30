Feature: Get OTT Platform media and validate response details
  The OTT Platform Media Endpoint returns details of available media. The following test scenarios ensure that the GET
  endpoint retrieves the data successfully in a performant manner and verify that JSON contents are as expected

  @validateMedia
  Scenario: Get request to Media endpoint will return 200 OK response in less than 1 second
    Given I send a request to the media endpoint
    Then the response will have status 200
    And the response time will be less than 1 second