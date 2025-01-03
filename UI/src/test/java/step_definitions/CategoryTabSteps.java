package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_objects.CategoryPage;
import page_objects.HomePage;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class CategoryTabSteps {

    WebDriver driver;
    HomePage homePage;
    CategoryPage categoryPage;

    @Before
    @Step("Setup the WebDriver")
    public void setup() {
        driver = DriverFactory.get_driver();
        Allure.step("WebDriver initialized");
    }

    @Given("I am on the website homepage")
    @Step("Navigate to Homepage")
    public void i_am_on_the_website_homepage() {
        try {
            driver.get(Config.env_values("BASE_URL"));
            homePage = new HomePage(driver);
            Allure.step("Navigated to homepage successfully");
        } catch (Exception e) {
            attachScreenshot("Error navigating to homepage");
            Assert.fail("Blocked - Unable to navigate to homepage: " + e.getMessage());
        }
    }

    @When("I click on the category tab {string}")
    @Step("Select category tab: {categoryName}")
    public void i_click_on_the_category_tab(String categoryName) {
        try {
            categoryPage = homePage.selectCategory(categoryName);
            Allure.step("Clicked on category tab: " + categoryName);
        } catch (Exception e) {
            attachScreenshot("Error clicking on category tab");
            Assert.fail("Blocked - Unable to click category tab: " + e.getMessage());
        }
    }

    @Then("the products under {string} should load")
    @Step("Verify products under category: {categoryName}")
    public void the_products_under_should_load(String categoryName) throws InterruptedException {
        try {
            boolean productsDisplayed = categoryPage.areProductsDisplayed(categoryName);
            if (!productsDisplayed) {
                attachScreenshot("Products not displayed");
                Assert.fail("Products are not displayed under the category: " + categoryName);
            }
            Allure.step("Products displayed successfully under category: " + categoryName);
        } catch (Exception e) {
            attachScreenshot("Error verifying products under category");
            Assert.fail("Blocked - Unable to verify products under category: " + e.getMessage());
        }
    }

    @After
    @Step("Close the WebDriver")
    public void close_browser() {
        try {
            driver.quit();
            Allure.step("WebDriver closed successfully");
        } catch (Exception e) {
            Allure.step("Error closing WebDriver: " + e.getMessage());
        }
    }

    @Step("Attach screenshot for debugging")
    private void attachScreenshot(String description) {
        try {
            Allure.addAttachment(description, new ByteArrayInputStream(
                    ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
        } catch (Exception e) {
            Allure.step("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
