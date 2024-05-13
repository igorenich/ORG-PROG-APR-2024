Feature: Integration of Web, REST and DB technologies

  Scenario: Google search test
    Given i request random people from randomuser.me
    Given i store random people from randomuser.me
    Given i load google page
    Given i pick random user from DB
    When i search for random user
    Then i see random user's name in search results