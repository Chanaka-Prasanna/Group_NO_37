Feature: Verify Product Loading for Category Tabs

  Scenario Outline: Validate that category tabs load correct products
    Given I am on the website homepage
    When I click on the category tab "<CategoryName>"
    Then the products under "<CategoryName>" should load

    Examples:
      | CategoryName  |
      | Baby Products |
      | Beverages     |
      | Food Cupboard |
