Feature: PRODPFIP-104 User registration
  As a user I want to be able to sign up so that I can be a registered user of ShopMe website

  Background:
    Given I go to ShopMe main page
    And I push Login button
    And I can see registration form

  Scenario Outline: Signing up for ShopMe website - positive flow
    When I fill in all necessary registration data with testEmail, "<name>", "<surname>",
    And I push Register button
    And I can see expanded registration form
    And I can see name filled with "<name>"
    And I can see surname filled with "<surname>"
    And I can see email filled with testEmail
    And I fill in password and repeatPassword with "<password>"
    And I fill in all necessary personal data with "<phone_number>", "<bank_account>"
    And I fill in all necessary address data with "<street>", "<number>", "<city>", "<post_code>","<voivodeship>"
    And I select dataForInvoice checkbox
    And I fill in all necessary invoice data with "<c_name>", "<nip>", "<c_street>", "<c_number>", "<c_post_code>", "<c_city>"
    And I accept statute
    And I accept terms of personal data processing
    And I click Register button
    Then I should see register confirmation message "Rejestracja zakończona pomyślnie"
    And I should see Login button
    And I should be registered user in database

    Examples:
      | name | surname  | password      | phone_number | bank_account               | street   | number | city     | post_code | c_name       | nip        | c_street    | c_number | c_post_code | c_city   | voivodeship |
      | Jan  | Kowalski | testPassword0 | 012345678    | 55299610810888313485136811 | Kwiatowa | 2      | Szczecin | 70-000    | Test Company | 0123456789 | Truskawkowa | 2        | 70-111      | Szczecin | Pomorskie   |
