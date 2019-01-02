Feature: Login
  Perform login on email and password are inputted

  @login-feature
  Scenario Outline: Input Wrong username and password.
    Given I am on login screen
    When I input username <username>
    And I input password "<password>"
    And I press login button
    Then I should see error on the Login Screen

    Examples:
      | username | password |
      | sam      | sam1     |

  @login-feature
  Scenario Outline: Input Correct username and password.
    Given I am on login screen
    When I input username <username>
    And I input password "<password>"
    And I press login button
    Then I should navigate to User Details Screen

    Examples:
      | username | password |
      | sam      | sam123   |