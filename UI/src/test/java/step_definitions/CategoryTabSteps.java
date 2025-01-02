package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page_objects.CategoryPage;
import page_objects.HomePage;
import utils.Config;
import utils.DriverFactory;

public class CategoryTabSteps {

    WebDriver driver;
    HomePage homePage;
    CategoryPage categoryPage;

    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
    }

    @Given("I am on the website homepage")
    public void i_am_on_the_website_homepage() {
        driver.get(Config.env_values("BASE_URL"));
        homePage = new HomePage(driver);
    }

    @When("I click on the category tab {string}")
    public void i_click_on_the_category_tab(String categoryName) {
      categoryPage =  homePage.selectCategory(categoryName);
    }

    @Then("the products under {string} should load")
    public void the_products_under_should_load(String categoryName) throws InterruptedException {
        boolean productsDisplayed = categoryPage.areProductsDisplayed(categoryName);
        if (!productsDisplayed) {
            throw new AssertionError("Products are not displayed under the category: " + categoryName);
        }
    }

    @After
    public void close_browser() {
        driver.quit();
    }
}
