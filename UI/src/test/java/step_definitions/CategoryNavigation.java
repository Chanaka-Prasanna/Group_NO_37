//package step_definitions;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.*;
//import org.openqa.selenium.WebDriver;
//import page_objects.HomePage;
//import page_objects.CategoryPage;
//import utils.Config;
//import utils.DriverFactory;
//
//public class CategoryNavigation {
//
//    WebDriver driver;
//    HomePage homePage;
//    CategoryPage categoryPage;
//
//    @Before
//    public void setup() {
//        driver = DriverFactory.get_driver();
//    }
//
//    @Given("I am on the Cargills Online homepage")
//    public void i_am_on_the_cargills_online_homepage() {
//        driver.get(Config.env_values("BASE_URL")); // Open the homepage
//        homePage = new HomePage(driver);
//    }
//
//    @When("I click on the Vegetables category")
//    public void i_click_on_the_vegetables_category() {
//        categoryPage = homePage.selectCategory();
//    }
//
//
//    @Then("I should see a list of products under Vegetables")
//    public void i_should_see_a_list_of_products_under_vegetables() {
//        boolean productsDisplayed = categoryPage.areProductsDisplayed();
//        if (!productsDisplayed) {
//            throw new AssertionError("Products are not displayed under the category: Vegetables");
//        }
//    }
//
//    @After
//    public void close_browser() {
//        driver.quit();
//    }
//}


package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import page_objects.HomePage;
import page_objects.CategoryPage;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class CategoryNavigation {

    WebDriver driver;
    HomePage homePage;
    CategoryPage categoryPage;

    @Before
    @Step("Initialize WebDriver and setup")
    public void setup() {
        driver = DriverFactory.get_driver();
        Allure.step("Driver initialized successfully");
    }

    @Given("I am on the Cargills Online homepage")
    @Step("Navigate to Cargills Online Homepage")
    public void i_am_on_the_cargills_online_homepage() {
        try {
            driver.get(Config.env_values("BASE_URL"));
            homePage = new HomePage(driver);
            Allure.step("Successfully navigated to the homepage");
        } catch (Exception e) {
            captureScreenshot("Error navigating to homepage");
            Allure.step("Error: Unable to navigate to homepage - " + e.getMessage());
            throw new AssertionError("Blocked - Unable to reach homepage", e);
        }
    }

    @When("I click on the {string} category")
    @Step("Click on category: {category}")
    public void i_click_on_the_category(String category) {
        try {
            categoryPage = homePage.selectCategory(category);
            Allure.step("Successfully clicked on category: " + category);
        } catch (Exception e) {
            captureScreenshot("Error clicking category");
            Allure.step("Error: Unable to click on category - " + e.getMessage());
            throw new AssertionError("Blocked - Unable to click category", e);
        }
    }

    @Then("I should see a list of products under the {string}")
    @Step("Verify products under category: {category}")
    public void i_should_see_a_list_of_products_under_the_category(String category) {
        try {
            boolean productsDisplayed = categoryPage.areProductsDisplayed(category);
            if (productsDisplayed) {
                Allure.step("Products are displayed under the category: " + category);
            } else {
                captureScreenshot("Products not displayed");
                Allure.step("Failed: Products not displayed under the category: " + category);
                throw new AssertionError("Products are not displayed under the category: " + category);
            }
        } catch (Exception e) {
            captureScreenshot("Error verifying products");
            Allure.step("Error: Unable to verify products under category - " + e.getMessage());
            throw new AssertionError("Blocked - Exception during verification", e);
        }
    }

    @After
    @Step("Close the browser")
    public void close_browser() {
        try {
            DriverFactory.close_driver();
            Allure.step("Browser closed successfully");
        } catch (Exception e) {
            Allure.step("Error closing browser: " + e.getMessage());
        }
    }

    @Step("Capture Screenshot for Allure Report")
    private void captureScreenshot(String stepName) {
        try {
            Allure.addAttachment(stepName, new ByteArrayInputStream(
                    ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
        } catch (Exception e) {
            Allure.step("Failed to capture screenshot: " + e.getMessage());
        }
    }
}


