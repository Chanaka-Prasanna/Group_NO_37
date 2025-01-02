Feature: Remove an existing address
  As a user, I want to manage my saved addresses so that I can delete unnecessary ones.

  Scenario: Remove an existing address
    Given I logged in
    And   I am on addresses page
    And   I see the addresses
    When  I click the delete button for one
    Then  I should see a confirmation prompt
    And   I confirm the deletion
    Then  I should not see the address again
