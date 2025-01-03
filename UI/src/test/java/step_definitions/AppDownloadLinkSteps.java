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

public class AppDownloadLinkSteps {

    WebDriver driver;
    HomePage homePage;

    @Before
    @Step("Initialize WebDriver and setup")
    public void setup() {
        driver = DriverFactory.get_driver();
        Allure.step("Driver initialized successfully");
    }

    @Given("I am on the Cargills home page")
    @Step("Navigate to Cargills Home Page")
    public void iAmOnTheCargillsHomePage() {
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

    @When("I click on the {string} link")
    @Step("Click on link: {linkName}")
    public void iClickOnTheLink(String linkName) {
        try {
            homePage.clickAppDownloadLink(linkName);
            Allure.step("Successfully clicked on link: " + linkName);
        } catch (Exception e) {
            captureScreenshot("Error clicking link");
            Allure.step("Error: Unable to click on link - " + e.getMessage());
            throw new AssertionError("Blocked - Unable to click link", e);
        }
    }

    @Then("I verify the {string} page is loaded")
    @Step("Verify page loaded with URL: {expectedUrl}")
    public void iVerifyThePageIsLoaded(String expectedUrl) {
        try {
            String currentUrl = homePage.getCurrentURL();
            if (currentUrl.equals(expectedUrl)) {
                Allure.step("Successfully verified the URL: " + expectedUrl);
            } else {
                captureScreenshot("URL mismatch");
                Allure.step("Failed: URLs do not match. Expected: " + expectedUrl + ", Found: " + currentUrl);
                throw new AssertionError("The URLs do not match! Expected: " + expectedUrl + ", Found: " + currentUrl);
            }
        } catch (Exception e) {
            captureScreenshot("Error verifying URL");
            Allure.step("Error: Unable to verify URL - " + e.getMessage());
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
