package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_objects.Profile;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class UpdateProfile {
    private WebDriver driver;
    private Profile profile;

    @Before
    public void setup() {

        this.driver = DriverFactory.get_driver();

    }

    @And("I navigate to the profile page")
    public void navigate_to_profile_page() {
        try {

            driver.get(Config.env_values("BASE_URL") + "ManageProfile");
            profile = new Profile(driver);
            String actual = profile.get_profile_page_title();
            String expected = "My Account";

            if(actual.equals(expected)) {

                Allure.step("Navigated to profile page");

            } else {

                Allure.addAttachment("Navigation Failed", new ByteArrayInputStream(
                        ((org.openqa.selenium.TakesScreenshot) driver)
                                .getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Assert.fail("Failed to load my account page");

            }
        } catch (Exception e) {

            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(
                    ((org.openqa.selenium.TakesScreenshot) driver)
                            .getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Exception on navigating to my account: " + e.getMessage());
            Assert.fail("Failed to navigate to my account page");

        }
    }

    @When("I update my profile information")
    public void update_profile_information() {

        try {

            profile.update_profile();
            profile.click_save();
            Allure.step("Profile information updated");

        } catch (Exception e) {

            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(
                    ((org.openqa.selenium.TakesScreenshot) driver)
                            .getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Failed to update profile: " + e.getMessage());
            Assert.fail("Failed to update profile");

        }
    }

    @Then("I should see a success message")
    public void i_should_see_a_success_message() {

        try {

            String expected = "Profile Updated successfully";
            String actual = profile.get_success_message();

            if (expected.equals(actual)) {
                Allure.step("Profile is updated");
            } else {

                Allure.addAttachment("Update Failed", new ByteArrayInputStream(
                        ((org.openqa.selenium.TakesScreenshot) driver)
                                .getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Assert.fail("Failed to update profile");

            }
        } catch (Exception e) {

            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(
                    ((org.openqa.selenium.TakesScreenshot) driver)
                            .getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Exception checking success message: " + e.getMessage());
            throw new RuntimeException(e);

        }
    }

    @After
    public void tear_down() {
        try {

            DriverFactory.close_driver();
            Allure.step("Driver closed successfully");

        } catch (Exception e) {
            Allure.step("Error closing driver: " + e.getMessage());
        }
    }
}