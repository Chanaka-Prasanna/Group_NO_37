package step_definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.CategoryPage;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class FilterOnSubCategories {

    WebDriver driver;
    CategoryPage categoryPage;

    @Before
    @Step("Setup the WebDriver")
    public void setup() {
        driver = DriverFactory.get_driver();
        Allure.step("WebDriver initialized");
    }

    @Given("I am on the product page")
    @Step("Navigate to the product page")
    public void i_am_on_the_product_page() {
        try {
            driver.get(Config.env_values("BASE_URL") + "Product/Meats?IC=MTE=&NC=TWVhdHM=");
            categoryPage = new CategoryPage(driver);
            Allure.step("Navigated to the product page successfully");
        } catch (Exception e) {
            attachScreenshot("Error navigating to the product page");
            throw new AssertionError("Unable to navigate to the product page: " + e.getMessage());
        }
    }

    @When("I select {string} from the filter")
    @Step("Select sub-category: {subCategory}")
    public void i_select_from_the_filter(String subCategory) {
        try {
            categoryPage.selectSubCategory(subCategory);
            Allure.step("Selected sub-category: " + subCategory);
        } catch (Exception e) {
            attachScreenshot("Error selecting sub-category");
            throw new AssertionError("Unable to select sub-category: " + e.getMessage());
        }
    }

    @Then("I should see only products in the {string} category")
    @Step("Verify products under category: {category}")
    public void i_should_see_only_products_in_the_category(String category) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean isTextDisplayed = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//*[@id=\"h3head\"]/span[2]"), category
            ));
            if (!isTextDisplayed) {
                attachScreenshot("Category mismatch");
                throw new AssertionError("Products displayed do not match the category: " + category);
            }
            Allure.step("Verified products are displayed under category: " + category);
        } catch (Exception e) {
            attachScreenshot("Error verifying products under category");
            throw new AssertionError("Unable to verify products under category: " + e.getMessage());
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
