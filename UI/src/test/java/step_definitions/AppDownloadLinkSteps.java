package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import page_objects.CategoryPage;
import page_objects.HomePage;
import utils.Config;
import utils.DriverFactory;


public class AppDownloadLinkSteps {
    WebDriver driver;
    HomePage homePage;

    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
    }

    @Given("I am on the Cargills home page")
    public void iAmOnTheCargillsHomePage() {
        driver.get(Config.env_values("BASE_URL"));
        homePage = new HomePage(driver);
    }

    @When("I click on the {string} link")
    public void iClickOnTheLink(String linkName) {
        {
            homePage.clickAppDownloadLink(linkName);
        }
    }

    @Then("I verify the {string} page is loaded")
    public void iVerifyThePageIsLoaded(String expectedUrl) {
        Assert.assertEquals(homePage.getCurrentURL(), expectedUrl, "The URLs do not match!");
        driver.quit();
    }

    @After
    public void close_browser() {
        driver.quit();
    }
}

