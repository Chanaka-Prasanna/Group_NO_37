Feature: My Account Dropdown Links

  Scenario: Verify each link in the My Account dropdown
    Given user is logged in
    When  user clicks on the My Account dropdown
    Then  each link should navigate to the correct page
