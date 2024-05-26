Feature: Integration of Web, REST, and DB technologies

  Scenario: Allo UA Scenario
    Given I open the Allo UA website
    When I search for "Samsung"
    Then I should see at least 4 product titles in the search results