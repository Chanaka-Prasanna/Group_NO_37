package step_definitions;

import io.cucumber.java.en.And;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import page_objects.Cart;
import page_objects.Login;
import page_objects.LoginPage;
import page_objects.ProductCard;
import utils.Config;
import utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class ProductCardSteps {
    WebDriver driver;
    ProductCard productCard;
    Login login;
    By my_acc_path      =  By.id("btnSign");

    @Before
    public void setUp() {
        driver = DriverFactory.get_driver(); // Ensure DriverFactory provides a properly configured WebDriver instance
    }

    @Step("User logs in ")
    @Given("the user is logged in")
    @Description("Logs in the user before performing Add to Cart functionality.")
    public void user_is_logged_in() {
        try {

            LoginPage loginpage = new LoginPage(driver);
            driver.get(Config.env_values("BASE_URL")+"Login");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            wait.until(ExpectedConditions.visibilityOfElementLocated(my_acc_path));
            loginpage.login(Config.env_values("LOGIN_TEXT"),Config.env_values("PASSWORD"));
            System.out.println("Login successful");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception during login: " + e.getMessage());
            Assert.fail("Blocked - Exception during login: ");

        }
    }

    @Step("Navigating to the product list page.")
    @And("the user is on the product list page")
    @Description("Ensures the user is on the product list page to test Add to Cart functionality.")
    public void user_is_on_the_product_list_page() {
        driver.get("https://cargillsonline.com/Product/Vegetables?IC=MjM=&NC=VmVnZXRhYmxlcw==");
        productCard = new ProductCard(driver);

        String expected_txt = "My Account";
        String actual_txt =  productCard.loggedIn();

        if (actual_txt.equals(expected_txt)){
            Allure.step("Successfully logged in");
        }else {

            Allure.addAttachment("Login Failed", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Test Failed - Expected to see " + expected_txt + " but saw "+ actual_txt);
            Assert.fail("Test Failed - User failed to login");

        }
        addAllureAttachment("Navigated to Product List Page", "URL:" +Config.env_values("BASE_URL")+ "Product/Vegetables?IC=MjM=&NC=VmVnZXRhYmxlcw==");
    }

    @Step("User clicks the Add button on a product card.")
    @When("the user clicks the Add button on a product card")
    @Description("Simulates clicking the Add button on a product card.")
    public void the_user_clicks_the_add_button_on_a_product_card() {
        productCard.clickAddButton();
        addAllureAttachment("Clicked Add Button", "Add button clicked on a product card.");
    }

    @Step("Verify that the quantity selector is displayed.")
    @Then("the quantity selector should be displayed")
    @Description("Asserts that the quantity selector appears after clicking the Add button.")
    public void the_quantity_selector_should_be_displayed() {
        Assert.assertTrue(productCard.isQuantitySelectorDisplayed(), "Quantity selector is not displayed");
        addAllureAttachment("Quantity Selector Verification", "Quantity selector is displayed.");
    }

    @Step("quantity selector is displayed")
    @Given("the quantity selector is displayed")
    @Description("checks whether quantity selector is displayed.")
    public void the_quantity_selector_is_displayed() {
        try {
            productCard.clickAddButton();
            Assert.assertTrue(productCard.isQuantitySelectorDisplayed(), "Quantity selector is not displayed");
            addAllureAttachment("Quantity Selector Verification", "Quantity selector is displayed.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception during login: " + e.getMessage());
            Assert.fail("Blocked - Exception during login: ");

        }
    }

    @Step("User clicks the + button to increment quantity.")
    @When("the user clicks the + button")
    @Description("Simulates clicking the + button on the quantity selector.")
    public void the_user_clicks_the_increment_button() {
        productCard.incrementQuantity();
        addAllureAttachment("Increment Button Clicked", "User clicked the + button.");
    }

    @Step("Verify that the quantity increases by 1.")
    @Then("the quantity should increase by 1")
    @Description("Asserts that the quantity increases correctly when the + button is clicked.")
    public void the_quantity_should_increase_by_1() {
        int quantity = productCard.getQuantity();
        Assert.assertEquals(quantity, 2, "Quantity did not increment as expected");
        addAllureAttachment("Quantity Verification", "Quantity incremented to: " + quantity);
    }

    @Step("User clicks the - button to decrement quantity.")
    @When("the user clicks the - button")
    @Description("Simulates clicking the - button on the quantity selector.")
    public void the_user_clicks_the_decrement_button() {
        productCard.decrementQuantity();
        addAllureAttachment("Decrement Button Clicked", "User clicked the - button.");
    }

    @Step("Verify that the quantity decreases by 1.")
    @Then("the quantity should decrease by 1")
    @Description("Asserts that the quantity decreases correctly when the - button is clicked.")
    public void the_quantity_should_decrease_by_1() {
        int quantity = productCard.getQuantity();
        Assert.assertEquals(quantity, 1, "Quantity did not decrement as expected");
        addAllureAttachment("Quantity Verification", "Quantity decremented to: " + quantity);
    }

    @Step("Verify that the minimum quantity is 0.")
    @Then("the minimum quantity should be 0")
    @Description("Asserts that the minimum allowed quantity is 0.")
    public void the_minimum_quantity_should_be_0() {
        productCard.decrementQuantity();
        int quantity = productCard.getQuantity();
        Assert.assertEquals(quantity, 0, "Minimum quantity is less than 0");
        addAllureAttachment("Minimum Quantity Verification", "Quantity is at the minimum: " + quantity);
    }

    @Step("User clicks on the product link to navigate to details.")
    @When("the user clicks anywhere else on the card")
    @Description("Simulates clicking on the product card to navigate to product details.")
    public void the_user_clicks_anywhere_else_on_the_card() {
        productCard.clickProductLink();
        addAllureAttachment("Product Link Clicked", "Navigated to product details.");
    }

    @Step("Verify that the user navigates to the product details page.")
    @Then("the user should be navigated to the product details page")
    @Description("Asserts that the user navigates to the correct product details page.")
    public void the_user_should_be_navigated_to_the_product_details_page() {
        String currentUrl = productCard.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("ProductDetails"), "User is not navigated to product details page");
        addAllureAttachment("Navigation Verification", "Current URL: " + currentUrl);
    }

    @Step("Verify that the URL matches the expected format.")
    @Then("the URL should match the expected format")
    @Description("Asserts that the product details page URL matches the expected format.")
    public void the_url_should_match_the_expected_format() {
        String currentUrl = productCard.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.matches(Config.env_values("BASE_URL")+"ProductDetails/.+\\?ID=.+"),
                "URL format is incorrect: " + currentUrl
        );
        addAllureAttachment("URL Format Verification", "Verified URL format: " + currentUrl);
    }

    // Utility method to add attachments to Allure reports
    private void addAllureAttachment(String name, String content) {
        Allure.addAttachment(name, new ByteArrayInputStream(content.getBytes()));
    }
}
