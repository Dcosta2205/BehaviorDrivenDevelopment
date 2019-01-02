
Feature: Validate user details

  Background:User is Logged in
    Given A login screen
    When I Enter username "sam"
    And I Enter password "sam123"
    And I press login button
    Then I should navigate to User Details Screen

  @test-feature
  Scenario:User Details Verification
    Given I'm on UserDetailsScreen
    Then Verify first name and last name
