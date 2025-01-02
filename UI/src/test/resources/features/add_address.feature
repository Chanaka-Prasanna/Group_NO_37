Feature: Adding an address

  Scenario: Successfully add a new address
    Given I logged in
    And   I navigate to the manage address page
    And   I enter new address
    When  I click on the save button
    Then  I should see address
