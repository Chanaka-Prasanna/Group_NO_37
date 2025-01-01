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

public class FooterCategorySteps {

    WebDriver driver;
    HomePage homePage;
    CategoryPage categoryPage;

    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
    }

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        driver.get(Config.env_values("BASE_URL"));
        homePage = new HomePage(driver);
    }

    @When("I click on the {string} link in the footer")
    public void i_click_on_the_link_in_the_footer(String category) {
        categoryPage = homePage.selectCategoryInFooter(category);
    }

    @Then("I should be navigated to the {string}")
    public void i_should_be_navigated_to_the_category(String category) throws InterruptedException {
        boolean productsDisplayed = categoryPage.areProductsDisplayed(category);
        if (!productsDisplayed) {
            throw new AssertionError("Products are not displayed under the category: " + category);
        }
    }

    @After
    public void close_browser() {
        driver.quit();
    }
}
