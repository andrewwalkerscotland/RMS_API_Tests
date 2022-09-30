Feature: Validation of get method

    @validateMedia
    Scenario: Simple test to prove setup
      Given I send request to the media url
      Then the response will return ok status