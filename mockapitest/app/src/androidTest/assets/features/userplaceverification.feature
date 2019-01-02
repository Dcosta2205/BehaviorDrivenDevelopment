Feature: Validate user place details

  Background:User is Logged in
    Given A login screen
    When I Enter username "sam"
    And I Enter password "sam123"
    And I press login button
    Then I should navigate to User Details Screen

  Scenario:User Places  Verification
    Given I'm on UserDetailsScreen
    Then I should see the user details in a Recycler View
    When I Click random RecyclerView Item
    Then I Should See Place Details Fragment
    Then Validate the clicked Recycler View Details