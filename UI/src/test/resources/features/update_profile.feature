Feature: Update User Profile
  As a user, I should be able to update my profile.


  Scenario: Successfully update user profile
    Given I am logged in
    And I navigate to the profile page
    When I update my profile information
    Then I should see a success message