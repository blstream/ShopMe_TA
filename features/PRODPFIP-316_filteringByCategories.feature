Feature: PRODPFIP-316 Filtering by categories
  As a user I want to be able to filter search result by categories, so that I can easily find results which I am interested of

  Scenario Outline: Displaying all search results by category
    Given I go to ShopMe main page
    And I add services
#   | service name                 | category                | user name | user email         | user phone | user info | base description | base price | extended description | extended price | extra description | extra price | province          | city     |
    | test                         | bandsAndMusic            | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | bookkeeping              | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | building                 | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | companyAndOffice         | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | garden                   | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | graphics                 | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | housework                | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | law                      | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | marketingAndAdvertising  | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | others                   | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | photography              | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | programming              | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | translations             | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | transport                | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | tutoring                 | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | workshopServices         | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    And I expand category list
    And I click category "<category>"
    Then I should see list of results of category "<category>"

    Examples:
      | category            |
      | Budowa              |
      | Firma i biuro       |
      | Fotografia          |
      | Grafika             |
      | Korepetycje         |
      | Księgowość          |
      | Marketing i reklama |
      | Naprawa i serwis    |
      | Ogród               |
      | Zespoły i muzyka    |
      | Usługi warsztatowe  |
      | Prace domowe        |
      | Prawo               |
      | Programowanie       |
      | Tłumaczenia         |
      | Transport           |

  Scenario Outline: Displaying all search results by category selected from main page footer
    Given I go to ShopMe main page
    And I add services
#   | service name                 | category                | user name | user email         | user phone | user info | base description | base price | extended description | extended price | extra description | extra price | province          | city     |
    | test                         | bandsAndMusic            | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | bookkeeping              | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | building                 | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | companyAndOffice         | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | garden                   | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | graphics                 | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | housework                | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | law                      | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | marketingAndAdvertising  | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | others                   | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | photography              | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | programming              | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | translations             | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | transport                | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | tutoring                 | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    | test                         | workshopServices         | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
    And I expand category list
    And I click category in main page footer"<category>"
    Then I should see list of results of category "<category>"

    Examples:
      | category            |
      | Budowa              |
      | Firma i biuro       |
      | Fotografia          |
      | Grafika             |
      | Korepetycje         |
      | Księgowość          |
      | Marketing i reklama |
      | Naprawa i serwis    |
      | Ogród               |
      | Zespoły i muzyka    |
      | Usługi warsztatowe  |
      | Prace domowe        |
      | Prawo               |
      | Programowanie       |
      | Tłumaczenia         |
      | Transport           |

    Scenario: Displaying search results by category and title
      Given I go to ShopMe main page
      And I add services
#     | service name                 | category         | user name | user email         | user phone | user info | base description | base price | extended description | extended price | extra description | extra price | province          | city     |
      | test                         | others           | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
      And I expand category list
      And I click category "Inne"
      And I can see list of results of category "Inne"
      And I enter a searching phrase "test" into the search field
      Then I should see list of results of category "<category>"
      And I see that title of the service contains "test"


    Scenario: Displaying search results by title and category
      Given I go to ShopMe main page
      And I add services
#     | service name                 | category         | user name | user email         | user phone | user info | base description | base price | extended description | extended price | extra description | extra price | province          | city     |
      | test                         | others           | John Doe  | john.doe@gmail.com | 888555222  | test      | test             | 10         | test                 | 20             | test              | 30          | WesternPomeranian | Szczecin |
      And I enter a searching phrase "test" into the search field
      And I click click category "Inne" on search result page
      Then I should see list of results of category "<category>"
      And I see that title of the service contains "test"
