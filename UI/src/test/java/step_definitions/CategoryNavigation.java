//package step_definitions;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.*;
//import org.openqa.selenium.WebDriver;
//import page_objects.HomePage;
//import page_objects.CategoryPage;
//import utils.Config;
//import utils.DriverFactory;
//
//public class CategoryNavigation {
//
//    WebDriver driver;
//    HomePage homePage;
//    CategoryPage categoryPage;
//
//    @Before
//    public void setup() {
//        driver = DriverFactory.get_driver();
//    }
//
//    @Given("I am on the Cargills Online homepage")
//    public void i_am_on_the_cargills_online_homepage() {
//        driver.get(Config.env_values("BASE_URL")); // Open the homepage
//        homePage = new HomePage(driver);
//    }
//
//    @When("I click on the Vegetables category")
//    public void i_click_on_the_vegetables_category() {
//        categoryPage = homePage.selectCategory();
//    }
//
//
//    @Then("I should see a list of products under Vegetables")
//    public void i_should_see_a_list_of_products_under_vegetables() {
//        boolean productsDisplayed = categoryPage.areProductsDisplayed();
//        if (!productsDisplayed) {
//            throw new AssertionError("Products are not displayed under the category: Vegetables");
//        }
//    }
//
//    @After
//    public void close_browser() {
//        driver.quit();
//    }
//}


package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import page_objects.HomePage;
import page_objects.CategoryPage;
import utils.Config;
import utils.DriverFactory;

public class CategoryNavigation {

    WebDriver driver;
    HomePage homePage;
    CategoryPage categoryPage;

    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
    }

    @Given("I am on the Cargills Online homepage")
    public void i_am_on_the_cargills_online_homepage() {
        driver.get(Config.env_values("BASE_URL")); // Open the homepage
        homePage = new HomePage(driver);
    }

    @When("I click on the {string} category")
    public void i_click_on_the_category(String category) {
        categoryPage = homePage.selectCategory(category); // Pass category name to selectCategory method
    }

    @Then("I should see a list of products under the {string}")
    public void i_should_see_a_list_of_products_under_the_category(String category) throws InterruptedException {
        boolean productsDisplayed = categoryPage.areProductsDisplayed(category); // Pass category name to areProductsDisplayed method
        if (!productsDisplayed) {
            throw new AssertionError("Products are not displayed under the category: " + category);
        }
    }

    @After
    public void close_browser() {
        driver.quit();
    }
}

