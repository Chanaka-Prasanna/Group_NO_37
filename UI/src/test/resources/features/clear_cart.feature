Feature: Clear all products from the cart
  As a user, I want to clear cart

  Scenario: Clear all products from the cart
    Given I logged in
    And I am on the cart page
    And I have multiple products in my cart
    When I click on the clear cart button
    And I confirm the action in the popup (if applicable)
    Then All products should be removed from the cart
