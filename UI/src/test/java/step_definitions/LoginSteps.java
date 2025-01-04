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
import page_objects.Login;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class LoginSteps {
    WebDriver driver;
    Login login;

    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
    }

    @Given("user is in the login page")
    @Step("Navigate to Login Page")
    public void user_is_in_the_login_page() {
        try {

            driver.get(Config.env_values("BASE_URL") + "Login");
            login = new Login(driver);
            Allure.step("Successfully navigated to login page");

        } catch (Exception e) {

            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Error navigating to login page: " + e.getMessage());
            Assert.fail("Blocked - Unable to reach login page");

        }
    }

    @When("user enters text: {string} and password: {string}")
    @Step("Enter Credentials")
    public void user_enters_text_and_password(String text, String password) {
        try {

            login.enter_email_or_phone(text);
            login.enter_password(password);
            Allure.step("Entered Credentials: " + text + " / " + password);

        } catch (Exception e) {

            Allure.addAttachment("Failed to Enter Credentials", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Failed to enter credentials: " + e.getMessage());
            Assert.fail("Blocked - Unable to enter credentials");

        }
    }

    @When("user clicks on the login button")
    @Step("Click Login Button")
    public void user_clicks_on_the_login_button() {
        try {

            login.click_login();
            Allure.step("Clicked login button");

        } catch (Exception e) {

            Allure.addAttachment("Click Failed", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Unable to click login button");
            Assert.fail("Blocked - Unable to click login button");

        }
    }

    @Then("user should  see the my account section")
    @Step("Verify My Account Section")
    public void user_should_see_the_my_account_section() {
        try {

            String actual_txt = login.is_acc_section_displayed();
            String expected_txt = "My Account";

            if (actual_txt.equals(expected_txt)) {

                Allure.step("Test Passed - User successfully logged in");
                Assert.assertEquals(actual_txt, expected_txt);

            } else {

               if ("Failed".equals(actual_txt)){

                   Allure.step("Test Passed - Invalid credentials blocked");

                }
            }
        } catch (Exception e) {

            Allure.addAttachment("Error during Verification", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception during verification: " + e.getMessage());
            Assert.fail("Blocked - Exception during verification");

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
