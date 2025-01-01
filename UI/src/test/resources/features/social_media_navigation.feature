Feature: Social Media Icons Navigation

  Scenario Outline: Verify navigation to social media pages
    Given I launch the browser and open the home page
    When I click on the "<socialMedia>" icon
    Then I verify the URL in the browser is "<expectedURL>"

    Examples:
      | socialMedia   | expectedURL                                  |
      | Facebook      | https://web.facebook.com/cargillsonline      |
      | Instagram     | https://www.instagram.com/cargillsonline/    |
