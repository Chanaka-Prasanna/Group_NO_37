Feature: Verify Google Play and App Store links

  Scenario Outline: Check if the <linkName> link loads correctly
    Given I am on the Cargills home page
    When I click on the "<linkName>" link
    Then I verify the "<expectedURL>" page is loaded

    Examples:
      | linkName       |expectedURL                                                                     |
      | Google Play    | https://play.google.com/store/apps/details?id=com.service.online.cargills      |
      | App Store      | https://apps.apple.com/in/app/cargills-online/id1524310107                     |
