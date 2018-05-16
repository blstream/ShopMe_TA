@inProgress
Feature: PRODPFIP-105 User registration - alternative flow for extended registration form
  As a user I will not be able to register new account with invalid credentials.

  Background:
    Given I go to ShopMe main page
    And I push Login button
    And I push SignUp button
    And I can see registration form
    And I fill in all necessary registration data with testEmail, "Jan", "Kowalski",
    And I push Register button
    And I can see expanded registration form
    And I can see name filled with "Jan"
    And I can see surname filled with "Kowalski"
    And I can see email filled with testEmail

  Scenario Outline: Signing up for ShopMe website with invalid password
    When I fill in password with "<password>"
    And I fill in all necessary personal data with "123456789", "11111111111111111111111111"
    And I fill in all necessary address data with "Kwiatowa", "5", "Szczecin", "71-000","Zachodniopomorskie"
    And I accept statute
    And I accept terms of personal data processing
    And I click Register button
    Then I can see an error message "<error_message>"
    Examples:
      | password  | error_message                                                     |
      | samemale  | Hasło musi zawierać co najmniej jedna wielką literę i jedną cyfrę |
      | samemale1 | Hasło musi zawierać co najmniej jedna wielką literę i jedną cyfrę |
      | TestTest  | Hasło musi zawierać co najmniej jedna wielką literę i jedną cyfrę |
      | Zamalo1   | Zbyt mała liczba znaków                                           |
      |           | Pole wymagane                                                     |


  Scenario Outline: Signing up for ShopMe website with invalid phone number and bank account number
    When I fill in password with "TestPassword1"
    And I fill in all necessary personal data with "<phone_number>", "<bank_account>"
    And I fill in all necessary address data with "Kwiatowa", "5", "Szczecin", "71-000","Zachodniopomorskie"
    And I accept statute
    And I accept terms of personal data processing
    And I click Register button
    Then I can see an error message "<error_message>"
    Examples:
      | phone_number | bank_account               | error_message                       |
      | A12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 12345678     | 11111111111111111111111111 | Zbyt mała liczba znaków             |
      | !12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | `12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | ~12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | @12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | #12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | $12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | %12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | ^12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | &12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | *12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | (12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | )12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | _12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | -12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | =12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | +12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | /12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | ,12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | .12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | >12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | <12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | /12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | ?12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | '12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | "12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | :12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | ;12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | \12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | ]12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | [12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | }12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | {12345678    | 11111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      |              | 11111111111111111111111111 | Pole wymagane                       |
      | 123456789    |                            | Pole wymagane                       |
      | 123456789    | 1111111111111111111111111  | Zbyt mała liczba znaków             |
      | 123456789    | a1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | !1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | `1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | ~1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | @1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | #1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | $1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | %1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | ^1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | (1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | *1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | &1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | )1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | -1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | _1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | =1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | +1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | /1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | \1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | ,1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | .1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | ?1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | >1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | <1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | '1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | "1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | :1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | ;1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | ]1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | [1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | }1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |
      | 123456789    | {1111111111111111111111111 | Pole powinno zawierać jedynie cyfry |

  Scenario Outline: Signing up for ShopMe website with invalid address data
    When I fill in password with "TestPassword1"
    And I fill in all necessary personal data with "123456789", "11111111111111111111111111"
    And I fill in all necessary address data with "<street>", "<number>", "<city>", "<post_code>","<voivodeship>"
    And I accept statute
    And I accept terms of personal data processing
    And I click Register button
    Then I can see an error message "<error_message>"
    Examples:
      | street   | number | city      | post_code | voivodeship        | error_message                       |
      | Te       | 1      | Szczecin  | 71-000    | Zachodniopomorskie | Zbyt mała liczba znaków             |
      |          | 1      | Szczecin  | 71-000    | Zachodniopomorskie | Pole wymagane                       |
      | Kwiatowa |        | Szczecin  | 71-000    | Zachodniopomorskie | Pole wymagane                       |
      | Kwiatowa | 1      | Szczecin  | a1-000    | Zachodniopomorskie | Niedozwolone znaki                  |
      | Kwiatowa | 1      | Szczecin  | !1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | `1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | ~1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | @1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | #1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | $1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | %1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | ^1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | &1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | *1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | (1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | )1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | -1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | _1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | +1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | =1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | /1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | \1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | ,1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | .1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | >1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | <1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | ?1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | '1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | "1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | :1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | ;1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | ]1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | [1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | }1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  | {1-000    | Zachodniopomorskie | Pole powinno zawierać jedynie cyfry |
      | Kwiatowa | 1      | Szczecin  |           | Zachodniopomorskie | Pole wymagane                       |
      | Kwiatowa | 1      | Szczecin1 | 71-000    | Zachodniopomorskie | Niedozwolone znaki                  |
      | Kwiatowa | 1      | !@#$%^&*? | 71-000    | Zachodniopomorskie | Niedozwolone znaki                  |
      | Kwiatowa | 1      |           | 71-000    | Zachodniopomorskie | Pole wymagane                       |
      | Kwiatowa | 1      | Szczecin  | 71-000    |                    | Pole wymagane                       |


  Scenario: Signing up for ShopMe website without accepting terms of personal data processing
    When I fill in password with "TestPassword1"
    And I fill in all necessary personal data with "123456789", "11111111111111111111111111"
    And I fill in all necessary address data with "Kwiatowa", "5", "Szczecin", "71-000","Zachodniopomorskie"
    And I accept statute
    And I don't accept terms of personal data processing
    And I click Register button
    Then I can see an error message "Pole wymagane"

  Scenario: Signing up for ShopMe website without accepting statue
    When I fill in password with "TestPassword1"
    And I fill in all necessary personal data with "123456789", "11111111111111111111111111"
    And I fill in all necessary address data with "Kwiatowa", "5", "Szczecin", "71-000","Zachodniopomorskie"
    And I don't accept statute
    And I accept terms of personal data processing
    And I click Register button
    Then I can see an error message "Pole wymagane"

  Scenario Outline: Signing up for ShopMe website with invalid invoice data
    When I fill in password with "TestPassword1"
    And I fill in all necessary personal data with "123456789", "11111111111111111111111111"
    And I fill in all necessary address data with "Kwiatowa", "5", "Szczecin", "71-000", "Zachodniopomorskie"
    And I select dataForInvoice checkbox
    And I fill in all necessary invoice data with "<c_name>", "<nip>", "<c_street>", "<c_number>", "<c_post_code>", "<c_city>"
    And I accept statute
    And I accept terms of personal data processing
    And I click Register button
    Then I can see an error message "<error_message>"
    Examples:
      | c_name    | nip        | c_street    | c_number | c_post_code | c_city    | error_message                       |
      |           | 0123456789 | Truskawkowa | 1        | 71-000      | Szczecin  | Pole wymagane                       |
      | !@#$%^&*? | 0123456789 | Truskawkowa | 1        | 71-000      | Szczecin  | Niedozwolone znaki                  |
      | Firma     | abc1234567 | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678  | Truskawkowa | 1        | 71-000      | Szczecin  | Zbyt mała liczba znaków             |
      | Firma     | 012345678! | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678  | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678` | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678~ | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678@ | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678# | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678$ | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678% | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678& | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678^ | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678* | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678( | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678) | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678- | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678_ | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678= | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678+ | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678/ | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678\ | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678, | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678. | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678? | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678> | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678< | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678' | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678" | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678: | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678; | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678] | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678[ | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678} | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 012345678{ | Truskawkowa | 1        | 71-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     |            | Truskawkowa | 1        | 71-000      | Szczecin  | Pole wymagane                       |
      | Firma     | 0123456789 | Te          | 1        | 71-000      | Szczecin  | Zbyt mała liczba znaków             |
      | Firma     | 0123456789 | !@#$%^&*?   | 1        | 71-000      | Szczecin  | Niedozwolone znaki                  |
      | Firma     | 0123456789 |             | 1        | 71-000      | Szczecin  | Pole wymagane                       |
      | Firma     | 0123456789 | Truskawkowa |          | 71-000      | Szczecin  | Pole wymagane                       |
      | Firma     | 0123456789 | Truskawkowa | 1        |             | Szczecin  | Pole wymagane                       |
      | Firma     | 0123456789 | Truskawkowa | 1        | a1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | !1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | `1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | ~1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | @1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | $1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | #1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | %1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | ^1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | &1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | *1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | (1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | )1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | -1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | _1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | +1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | =1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | /1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | \1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | ,1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | .1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | ?1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | >1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | <1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | '1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | "1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | :1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | ;1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | [1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | ]1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | {1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | }1-000      | Szczecin  | Pole powinno zawierać jedynie cyfry |
      | Firma     | 0123456789 | Truskawkowa | 1        | 71-000      | Szczecin1 | Niedozwolone znaki                  |
      | Firma     | 0123456789 | Truskawkowa | 1        | 71-000      |           | Pole wymagane                       |
      | Firma     | 0123456789 | Truskawkowa | 1        | 71-000      | !@#$%^&*? | Niedozwolone znaki                  |
      | Firma     | 0123456789 | Truskawkowa | 1        | 71-000      | Szczecin! | Niedozwolone znaki                  |


  Scenario: Signing up for ShopMe website with 31 character password
    When I fill in password with 31 characters
    Then I should see in password maximum 30 characters

  Scenario: Signing up for ShopMe website with 11 character phone number
    When I fill in userPhoneNumber with "01234567891"
    Then I should see in userPhoneNumber maximum 10 characters

  Scenario: Signing up for ShopMe website with 27 character bank account number
    When I fill in userBankAccountNumber with 27 characters
    Then I should see in userBankAccountNumber maximum 26 characters

  Scenario: Signing up for ShopMe website with 7 character zip code
    When I fill in addressZipCode with "71-0000"
    Then I should see in addressZipCode maximum 6 characters

  Scenario: Signing up for ShopMe website with 51 character city
    When I fill in addressCity with 51 characters
    Then I should see in addressCity maximum 50 characters

  Scenario: Signing up for ShopMe website with 11 character nip
    When I fill in nip with "01234567891"
    Then I should see in nip maximum 10 characters

  Scenario: Signing up for ShopMe website with 51 character company city
    When I fill in invoiceAddressCity with 51 characters
    Then I should see in invoiceAddressCity maximum 50 characters
