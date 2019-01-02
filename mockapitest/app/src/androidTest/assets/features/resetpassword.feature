Feature: Perform reset Password


  @reset-feature
  Scenario Outline: Reset passwords mismatch
    Given I am on login screen
    Then I click on forgot password
    Then I should see a reset password screen
    And I enter new password <newPassword>
    Then I enter confirm password <confirmPassword>
    And I press reset button
    Then I should see error message

    Examples:
      | confirmPassword | newPassword |
      | sam      | sam1        |