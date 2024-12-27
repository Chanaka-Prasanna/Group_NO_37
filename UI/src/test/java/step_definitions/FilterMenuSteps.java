package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_objects.FilterMenu;
import utils.Config;
import utils.DriverFactory;

public class FilterMenuSteps {
    WebDriver driver;
    FilterMenu filterMenu;

    @Given("user is on the fruits listing page")
    @Step("Navigate to the fruits listing page")
    public void user_is_on_the_fruits_listing_page() {
        driver = DriverFactory.get_driver();
        driver.get(Config.env_values("BASE_URL") + "Product/Fruits?IC=OQ==&NC=RnJ1aXRz"); // Replace "Fruits" with actual endpoint
        filterMenu = new FilterMenu(driver);
        Allure.addAttachment("Page URL", driver.getCurrentUrl());
    }

    @When("user selects {string} from {string} filter")
    @Step("User selects option {string} from filter type {string}")
    public void user_selects_from_filter(String option, String filterType) {
        filterMenu.select_filter_option(filterType, option);
        Allure.addAttachment("Selected Filter", "Filter Type: " + filterType + ", Option: " + option);
    }

    @When("user clicks on clear all")
    @Step("User clicks on 'Clear All' button")
    public void user_clicks_on_clear_all() {
        filterMenu.click_clear_all();
        Allure.addAttachment("Action", "Clicked on 'Clear All'");
    }

    @Then("only products related to {string} are displayed")
    @Step("Verify filtered products for criteria: {string}")
    public void only_products_related_to_are_displayed(String filterCriterion) {
        boolean isFilterApplied = filterMenu.is_filter_applied(filterCriterion);
        Allure.addAttachment("Validation Result", "Filter Applied: " + filterCriterion + " - " + isFilterApplied);
        Assert.assertTrue(isFilterApplied, "Filter was not applied correctly.");
    }

    @Then("all products are displayed")
    @Step("Verify all products are displayed after clearing filters")
    public void all_products_are_displayed() {
        boolean isAllProductsDisplayed = filterMenu.is_filter_applied(""); // Check if all products are shown
        Allure.addAttachment("Validation Result", "All Products Displayed: " + isAllProductsDisplayed);
        Assert.assertTrue(isAllProductsDisplayed, "Clear All did not reset the filters.");
    }
}
