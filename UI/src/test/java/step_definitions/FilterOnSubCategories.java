package step_definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.CategoryPage;
import utils.Config;
import utils.DriverFactory;

import java.time.Duration;

public class FilterOnSubCategories {

    WebDriver driver;
    CategoryPage categoryPage;

    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
    }

    @Given("I am on the product page")
    public void i_am_on_the_product_page() {

        driver.get(Config.env_values("BASE_URL")+"Product/Meats?IC=MTE=&NC=TWVhdHM=");
        categoryPage = new CategoryPage (driver);

    }

    @When("I select {string} from the filter")
    public void i_select_from_the_filter(String subCategory) throws InterruptedException {
        categoryPage.selectSubCategory(subCategory);
    }

    @Then("I should see only products in the {string} category")
    public void i_should_see_only_products_in_the_category(String category) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//*[@id=\"h3head\"]/span[2]"), category
        ));
    }
}
