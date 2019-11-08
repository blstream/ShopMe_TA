@manual
Feature: PRODPFIP-22 failure error message
  As a user I want to be sufficiently informed that an error was occurring.

  Scenario: Display error message after network problems
    Given I have no internet connection
    When I go to ShopMe main page
    Then I can see error message "coś poszło nie tak"

  Scenario: Authentication failure due to missing credentials
    Given I go to ShopMe main page
    And I am a registered user in database with email "john.doe@gmail.com" and password "Password1234"
    And I am not authenticated
    When I go to ShopMe signin page
    And I fill in email with "john.doe@gmail.com"
    And I fill in password with "Password1"
    Then I can see error message "coś poszło nie tak"

  Scenario: Display error message after enter non existing address url
    When I go to ShopMe main page
    And I put non existing address into address field
    And I press Enter key
    Then I can see error message "coś poszło nie tak"

  Scenario: Display error message after delete offer
    Given I am on the offer profile page
    When I delete this offer
    And I refresh page
    Then I can see error message "coś poszło nie tak"

  Scenario: Display error message after inserting a hyperlink in adding offer form
    Given I go to ShopMe main page
    And I am an signed in to the application with email "john.doe@gmail.com" and password "Password1234"
    And I push add service button
    When I fill in title with "title"
    And I choose category "Inne"
    And I fill in province with "Zachodniopomorskie"
    And I fill in city with "Szczecin"
    And I fill in basicDescription with "https://www.google.com/"
    And I fill in basicPrice with "10"
    And I press Add service button
    Then I can see error message "coś poszło nie tak"

  Scenario: Attempt to register using an already registered e-mail
    Given I go to ShopMe main page
    And I push Login button
    And I push SignUp button
    And I fill in all necessary registration data with testEmail, "Jan", "Kowalski",
    And I push Register button
    When I change testEmail address into "john.doe@gmail.com"
    And I fill in password with "Password1"
    And I fill in all necessary personal data with "000000000", "55299610810888313485136811"
    And I fill in all necessary address data with "Ulica", "10", "Szczecin", "70-786", "Zachodniopomorskie"
    And I accept statute
    And I accept terms of personal data processing
    And I click Register button
    Then I can see error message "coś poszło nie tak"

  Scenario: Display no permission error message
    Given I go to ShopMe main page
    And I am an signed in to the application with email "john.doe@gmail.com" and password "Password1234"
    And I push add service button
    When I push logout button
    And I go back to previous page
    Then I can see error message "Nie masz uprawnień do przeglądania tej strony."
