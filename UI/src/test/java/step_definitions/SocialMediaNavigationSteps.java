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
import page_objects.HomePage;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class SocialMediaNavigationSteps {

    WebDriver driver;
    HomePage homePage;

    @Before
    @Step("Setup WebDriver")
    public void setup() {
        driver = DriverFactory.get_driver();
        Allure.step("WebDriver initialized successfully");
    }

    @Given("I launch the browser and open the home page")
    @Step("Launch the browser and navigate to the homepage")
    public void launchBrowserAndOpenHomePage() {
        try {
            driver.get(Config.env_values("BASE_URL"));
            homePage = new HomePage(driver);
            Allure.step("Navigated to the homepage successfully");
        } catch (Exception e) {
            attachScreenshot("Error opening homepage");
            throw new AssertionError("Failed to open the homepage: " + e.getMessage());
        }
    }

    @When("I click on the {string} icon")
    @Step("Click on the social media icon: {socialMedia}")
    public void iClickOnTheIcon(String socialMedia) {
        try {
            homePage.clickSocialMediaIcon(socialMedia);
            Allure.step("Clicked on the " + socialMedia + " icon");
        } catch (Exception e) {
            attachScreenshot("Error clicking on social media icon");
            throw new AssertionError("Failed to click on the social media icon: " + e.getMessage());
        }
    }

    @Then("I verify the URL in the browser is {string}")
    @Step("Verify the URL is as expected: {expectedURL}")
    public void iVerifyTheURLInTheBrowserIs(String expectedURL) {
        try {
            String actualURL = homePage.getCurrentURL();
            Assert.assertEquals(actualURL, expectedURL, "The URLs do not match!");
            Allure.step("Verified URL successfully: " + actualURL);
        } catch (AssertionError e) {
            attachScreenshot("URL verification failed");
            throw e;
        } catch (Exception e) {
            attachScreenshot("Error verifying URL");
            throw new AssertionError("Failed to verify the URL: " + e.getMessage());
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
