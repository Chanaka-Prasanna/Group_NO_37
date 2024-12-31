package step_definitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_objects.HomePage;
import utils.Config;
import utils.DriverFactory;

public class SocialMediaNavigationSteps {
    WebDriver driver;
    HomePage homePage;
    String actualURL;

    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
    }

    @Given("I launch the browser and open the home page")
    public void launchBrowserAndOpenHomePage() {
        driver.get(Config.env_values("BASE_URL"));
        homePage = new HomePage(driver);
    }

    @When("I click on the {string} icon")
    public void iClickOnTheIcon(String socialMedia) {
        homePage.clickSocialMediaIcon(socialMedia);
    }

    @Then("I verify the URL in the browser is {string}")
    public void iVerifyTheURLInTheBrowserIs(String expectedURL) {
        Assert.assertEquals(homePage.getCurrentURL(), expectedURL, "The URLs do not match!");
        driver.quit();
    }

    @After
    public void close_browser() {
        driver.quit();
    }
}


