package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_objects.Cart;
import page_objects.Login;
import page_objects.LoginPage;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;
import java.util.logging.Logger;

public class AddToCartSteps {

    WebDriver driver;
    Cart cart;
    final  String SAMPLE_PRODUCT_PAGE = "Product/Vegetables?IC=MjM=&NC=VmVnZXRhYmxlcw==";

    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
    }

    @Given("I am logged in")
    @Step("User Logged In")
    public void i_am_logged_in(){
        try {
            LoginPage loginpage = new LoginPage(driver);
            driver.get(Config.env_values("BASE_URL")+"Login");
            loginpage.login(Config.env_values("LOGIN_TEXT"),Config.env_values("PASSWORD"));

            driver.get(Config.env_values("BASE_URL") +"/index");
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
            Assert.fail("Blocked - Exception during login");

        }
    }

    @And("I am on the product listing page")
    @Step("Navigate to Product Page")
    public void i_am_on_the_product_listing_page(){
        try {

            String expected_txt = "Showing Products for";
            driver.get(Config.env_values("BASE_URL") + SAMPLE_PRODUCT_PAGE);
            String actual_txt =  cart.if_on_product_page();
            if (actual_txt.equals(expected_txt)){
                Allure.step("User in product page");
            }else {

                Allure.addAttachment("Failed to load product page", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Allure.step("Test Failed - Failed to load product page");
                Assert.fail("Test Failed - Failed to load product page");
            }
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception during loading products: " + e.getMessage());
            Assert.fail("Blocked - Exception during loading products");
        }
    }

    @And("I see products")
    @Step("Products Available")
    public void i_see_products(){
        try {
            if (cart.products_available()){
                Allure.step("User can see products");
            }else {

                Allure.addAttachment("Empty products", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Allure.step("Test Failed - No products to continue");
                Assert.fail("Test Failed - No products to continue");
            }
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on displaying products: " + e.getMessage());
            Assert.fail("Blocked - Exception on displaying products");
        }

    }


    @When("I click on a product")
    @Step("Click On Product")
    public void i_click_on_a_product(){
        try {
          cart.click_on_product();
            Allure.step("Clicked on product");
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception clicking on product: " + e.getMessage());
            Assert.fail("Blocked - Exception clicking on product");
        }

    }


    @And("I click on the Add to Cart button")
    @Step("Click On Add To Cart")
    public void i_click_on_the_add_to_cart_button(){
        try {
            cart.click_on_add_to_cart();
            Allure.step("Clicked on add to cart");
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception clicking on product: " + e.getMessage());
            Assert.fail("Blocked - Exception clicking on product");
        }
    }

    @Then("The product should be added to the cart")
    @Step("Verify The Cart Operation")
    public void the_product_should_be_added_to_the_cart(){
        try {
            String expected_txt = "block";
            String actual_txt =  cart.css_value_of_display();

            if (actual_txt.equals(expected_txt)){
                Allure.step("Product added to cart");
            }else {
                Allure.addAttachment("Failed to add to cart", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Allure.step("Test Failed - Failed to add to cart");
                Assert.fail("Test Failed - Failed to add to cart");
            }
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on adding to cart: " + e.getMessage());
            Assert.fail("Blocked - EException on adding to cart");
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
