Feature: Test Background feature

  Background:User is Logged in
    Given A login screen
    When I Enter username "sam"
    And I Enter password "sam123"
    And I press login button
    Then I should navigate to User Details Screen

  @permission-feature
  Scenario:  Clicking on permission Allow should grant call permission
    Given I'm on UserDetailsScreen
    Then I click phone number
    Then I Should See Runtime Permission Dialog
    And I Click Allow Permission then Permission must be granted