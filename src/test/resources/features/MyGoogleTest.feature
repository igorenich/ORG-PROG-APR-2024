Feature: Integration of Web, REST and DB technologies

  @severity=minor
  Scenario Template: Google search test
    Given i request <amout> random people from randomuser.me as "group_A"
    Given i store group "group_A" from randomuser.me to DB
    Given i load google page
    Given i pick random user from DB as "random person"
    When i search for "random person"
    Then i see at least 3 search results for "random person"
    Examples:
      | amout |
      | 1     |
      | 2     |

  @severity=critical
  Scenario: Google search test
    Given i load google page
    Given my test user "celebrity" is "Ben Affleck"
    When i search for "celebrity"
    Then i see at least 8 search results for "celebrity"
