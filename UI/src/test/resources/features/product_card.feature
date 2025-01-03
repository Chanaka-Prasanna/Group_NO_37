Feature: Product Card

  Scenario: Display quantity selector when Add button is clicked
    Given the user is logged in
    And   the user is on the product list page
    When  the user clicks the Add button on a product card
    Then  the quantity selector should be displayed
    When  the user clicks the + button
    Then  the quantity should increase by 1
    When  the user clicks the - button
    Then  the quantity should decrease by 1
    And   the minimum quantity should be 0


  Scenario: Navigate to product details page
    Given the user is logged in
    And the user is on the product list page
    When the user clicks anywhere else on the card
    Then the user should be navigated to the product details page
    And the URL should match the expected format
