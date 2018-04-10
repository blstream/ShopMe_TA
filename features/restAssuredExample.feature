Feature: Example test - using Rest Assured


  @rest
  Scenario: Test Backend Scenario using Rest-Assured
    Given Json config is provided
    When I call GET on Jsonplaceholder/posts/1
    Then I get Status code 200
  @test
    Scenario: Get json with Rest-Assured
      When I request for json with weather
    When I navigate to the main page
    And I enter a searching phrase "test" into the search field
    And I click the search button

    Then I can see service with data
    | oferta testowa | 1 zł | 10/04/2018 |



