Feature: Get OTT Platform media and validate response details
  The OTT Platform Media Endpoint returns details of available media. The following test scenarios ensure that the GET
  endpoint retrieves the data successfully in a performant manner and verify that JSON contents are as expected

  @simpleCheck
  Scenario: Get request to Media endpoint will return 200 OK response in less than 1 second
    Given I send a request to the media endpoint
    Then the response will have status 200
    And the response time will be less than 1 second(s)

  @simpleCheck
  Scenario: Get request to Media endpoint will have date header in response
    Given I send a request to the media endpoint
    Then the response will have date header

  @checkContents
  Scenario: Response Body items must always have id field and be of segment type music
    Given I send a request to the media endpoint
    When I examine the data items in the response
    Then the id field on each item will have a value
    And the Segment Type on every track will be music

  @checkContents
  Scenario: Response Body items must always have primary field within title list set to a value
    Given I send a request to the media endpoint
    When I examine the data items in the response
    Then the primary title field on each item will have a value

  @checkContents
  Scenario: Response Body items must only have one track that has now playing as true
    Given I send a request to the media endpoint
    When I examine the data items in the response
    Then the now playing field will be true on only one item