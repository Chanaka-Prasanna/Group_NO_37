Feature: Filter Sub Categories

  Scenario Outline: Verify filtering by sub category
    Given I am on the product page
    When I select "<SubCategory>" from the filter
    Then I should see only products in the "<SubCategory>" category

    Examples:
      | SubCategory           |
      | Ready To Cook Meat    |
      | Processed Meat        |
      | Raw Meat              |
