package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import page_objects.CategoryPage;
import page_objects.HomePage;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class FooterCategorySteps {

    WebDriver driver;
    HomePage homePage;
    CategoryPage categoryPage;

    @Before
    @Step("Initialize WebDriver")
    public void setup() {
        driver = DriverFactory.get_driver();
        Allure.step("WebDriver initialized successfully");
    }

    @Given("I am on the homepage")
    @Step("Navigate to the homepage")
    public void i_am_on_the_homepage() {
        try {
            driver.get(Config.env_values("BASE_URL"));
            homePage = new HomePage(driver);
            Allure.step("Navigated to homepage successfully");
        } catch (Exception e) {
            attachScreenshot("Error navigating to the homepage");
            throw new AssertionError("Failed to load homepage: " + e.getMessage());
        }
    }

    @When("I click on the {string} link in the footer")
    @Step("Click on the footer category link: {category}")
    public void i_click_on_the_link_in_the_footer(String category) {
        try {
            categoryPage = homePage.selectCategoryInFooter(category);
            Allure.step("Clicked on footer link for category: " + category);
        } catch (Exception e) {
            attachScreenshot("Error clicking footer category link");
            throw new AssertionError("Failed to click footer link for category: " + e.getMessage());
        }
    }

    @Then("I should be navigated to the {string}")
    @Step("Verify navigation to category: {category}")
    public void i_should_be_navigated_to_the_category(String category) {
        try {
            boolean productsDisplayed = categoryPage.areProductsDisplayed(category);
            if (!productsDisplayed) {
                attachScreenshot("Products not displayed under category");
                throw new AssertionError("Products are not displayed under the category: " + category);
            }
            Allure.step("Successfully navigated to and verified category: " + category);
        } catch (Exception e) {
            attachScreenshot("Error verifying category navigation");
            throw new AssertionError("Failed to verify category: " + e.getMessage());
        }
    }

    @After
    @Step("Close the browser")
    public void close_browser() {
        try {
            driver.quit();
            Allure.step("Browser closed successfully");
        } catch (Exception e) {
            Allure.step("Failed to close the browser: " + e.getMessage());
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
