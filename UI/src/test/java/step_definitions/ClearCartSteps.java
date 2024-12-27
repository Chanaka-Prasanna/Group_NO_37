package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_objects.Cart;
import page_objects.LoginPage;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class ClearCartSteps {

    WebDriver driver;
    Cart cart;

    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
    }


    @Given("I logged in")
    @Step("I logged in")
    public void i_logged_in() {
        try {
            LoginPage loginpage = new LoginPage(driver);
            driver.get(Config.env_values("BASE_URL")+"Login");
            loginpage.login(Config.env_values("LOGIN_TEXT"),Config.env_values("PASSWORD"));
            driver.get(Config.env_values("BASE_URL")+"/index");

            cart = new Cart(driver);

            String expected_txt = "My Account";
            String actual_txt =  cart.loggedIn();

            if (actual_txt.equals(expected_txt)){
                Allure.step("Successfully logged in");
            }else {

                Allure.addAttachment("Login Failed", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Allure.step("Test Failed - Expected to see " + expected_txt + " but saw "+ actual_txt);
                Assert.fail("Test Failed - User failed to login");

            }
        } catch (Exception e) {

            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception during login: " + e.getMessage());
            Assert.fail("Blocked - Exception during login: ");

        }
    }

    @And("I am on the cart page")
    @Step("I am on the cart page")
    public void i_am_on_the_cart_page() {
        try {
            if (cart.is_on_cart()){
                Allure.step("User can see the cart");
            }else {

                Allure.addAttachment("Failed to see the cart", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Allure.step("Test Failed - Failed to see the cart");
                Assert.fail("Test Failed - Failed to see the cart");
            }
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on cart displaying: " + e.getMessage());
            Assert.fail("Blocked - Exception on cart displaying");
        }
    }

    @And("I have multiple products in my cart")
    @Step("I have multiple products in my cart")
    public void i_have_multiple_products_in_my_cart() {
        try {
            if (cart.have_products()){
                Allure.step("User has products in cart");
            }else {

                Allure.addAttachment("User don't have product", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Allure.step("Blocked - user don't have products on cart");
                Assert.fail("Blocked - user don't have products on cart");
            }
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on getting products on cart: " + e.getMessage());
            Assert.fail("Blocked - Exception on getting products on cart");
        }
    }



    @When("I click on the clear cart button")
    @Step("I click on the clear cart button")
    public void i_click_on_the_clear_cart_button() {
        try {
            cart.click_clear_card();
            Allure.step("User has clicked in clear button");
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on clicking on clear button: " + e.getMessage());
            Assert.fail("Blocked - Exception on clicking on clear button");
        }
    }

    @And("I confirm the action in the popup \\(if applicable)")
    @Step("I confirm the action in the popup")
    public void i_confirm_the_action_in_the_popup() {

        try {
            if (cart.is_cleared_cart()){
                Allure.step("User has cleared the cart");
            }else {

                Allure.addAttachment("User don't have product", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Allure.step("Blocked - Something happened in clearing cart");
                Assert.fail("Blocked - Something happened in clearing cart");
            }
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on getting cleared the cart: " + e.getMessage());
            Assert.fail("Blocked - Exception on getting cleared the cart");
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
