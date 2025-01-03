Feature: Shop By Brand

  Scenario: Validate all brand items
    Given user is on the Shop by Brand page
    When  user clicks on each brand item
    Then  user should be redirected to the correct brand page
