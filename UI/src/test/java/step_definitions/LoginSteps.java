package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page_objects.Login;
import utils.Config;
import utils.DriverFactory;

public class LoginSteps {
    WebDriver driver;
    Login login;

    @Before
    public void setup(){
        driver = DriverFactory.get_driver();
    }

    @Given("user is in the login page")
    public void user_is_in_the_login_page() {
        driver.get(Config.get_base_url("BASE_URL"));
        login = new Login(driver);
    }

    @When("user enters text: {string} and password: {string}")
    public void user_enters_text_and_password(String text, String password) {
        login.enter_email_or_phone(text);
        login.enter_password(password);
    }

    @When("user clicks on the login button")
    public void user_clicks_on_the_login_button() {
        login.click_login();
        
    }

    @Then("user should  see the my account section")
    public void user_should_see_the_my_account_section() {
        login.is_acc_section_displayed();
    }

    @After
    public void tear_down(){
        DriverFactory.close_driver();
    }

}
