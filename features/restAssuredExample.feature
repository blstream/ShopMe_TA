Feature: Example test - using Rest Assured


  @rest
  Scenario: Test Backend Scenario using Rest-Assured
    Given Json config is provided
    When I call GET on Jsonplaceholder/posts/1
    Then I get Status code 200
  @json @rest
    Scenario: Get json with Rest-Assured
      When I request for json with weather

