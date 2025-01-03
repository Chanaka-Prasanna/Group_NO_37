Feature: Add to Cart
  As a user, I want to add products to my cart so that I can purchase them later.


  Scenario: Add a product to the cart
    Given I am logged in
    And   I am on the product listing page
    And   I see products
    When  I click on a product
    And   I click on the Add to Cart button
    Then  The product should be added to the cart