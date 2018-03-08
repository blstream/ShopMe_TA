Feature: Access to My Personal Information view
  As a user I want to have access to My Personal Information view
  So that I can verify my account details

  @MyPersonalInformation
  Scenario: User's personal data in "My personal information" page is correct
    Given I am on the login page
    When I authenticate to the application with login "test@migmail.pl" and password "test1"
    And I click MyPersonalInformation button
    Then I should see Your Personal Information form
    And First name filed should be filled with name "Ann"
    And Last name filed should be filled with name "Johnson"
    And E-mail address should be filled with email "test@migmail.pl"

