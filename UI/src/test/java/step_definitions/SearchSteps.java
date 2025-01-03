package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_objects.SearchBar;
import utils.DriverFactory;
import utils.Config;

import java.io.UnsupportedEncodingException;

public class SearchSteps {
    WebDriver driver;
    SearchBar searchBar;

    @Step("User is on the homepage")
    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {
        driver = DriverFactory.get_driver();
        driver.get(Config.env_values("BASE_URL")); // Replace with the actual homepage URL
        searchBar = new SearchBar(driver);
        Allure.step("Navigated to the homepage:"+ Config.env_values("BASE_URL"));
    }

    @Step("User searches for the term: {searchTerm}")
    @When("the user searches for {string}")
    public void the_user_searches_for(String searchTerm) {
        searchBar.enterSearchTerm(searchTerm);
        Allure.step("Entered search term: " + searchTerm);
        searchBar.clickSearchButton();
        Allure.step("Clicked on the search button");
    }

    @Step("Verify search results page for the term: {searchTerm}")
    @Then("the search results page for {string} should be displayed")
    public void the_search_results_page_for_should_be_displayed(String searchTerm) throws UnsupportedEncodingException {
        boolean isCorrectURL = searchBar.isSearchResultsPageURLCorrect(searchTerm);
        Allure.step("Expected URL:"+Config.env_values("BASE_URL")+"/product/" + searchTerm.toLowerCase() + "?PS=" + searchTerm.toLowerCase());
        Assert.assertTrue(isCorrectURL, "The URL of the search results page is incorrect for term: " + searchTerm);
        Allure.step("Search results page URL is correct");
    }

    @Step("Verify search results are displayed for the term: {searchTerm}")
    @Then("search results for {string} should be displayed")
    public void search_results_for_should_be_displayed(String searchTerm) {
        boolean areResultsDisplayed = searchBar.areSearchResultsDisplayed();
        Assert.assertTrue(areResultsDisplayed, "Search results are not displayed for term: " + searchTerm);
        Allure.step("Search results are displayed for term: " + searchTerm);
    }
}
