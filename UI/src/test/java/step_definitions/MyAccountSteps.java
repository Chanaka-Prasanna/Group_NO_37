package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import page_objects.LoginPage;
import page_objects.MyAccount;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAccountSteps {
    WebDriver driver = DriverFactory.get_driver();
    MyAccount myAccount;

    @Step("User logs in ")
    @Given("user is logged in")
    @Description("Logs in the user before performing Add to Cart functionality.")
    public void user_is_logged_in() {
        try {
            LoginPage loginpage = new LoginPage(driver);
            driver.get(Config.env_values("BASE_URL")+"Login");
            loginpage.login(Config.env_values("LOGIN_TEXT"),Config.env_values("PASSWORD"));
            System.out.println("Login successful");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception during login: " + e.getMessage());
            Assert.fail("Blocked - Exception during login: ");

        }
    }

    @Step("click on the My Account dropdown")
    @When("user clicks on the My Account dropdown")
    public void user_clicks_on_my_account_dropdown() {
        myAccount = new MyAccount(driver);
        myAccount.openDropdown();
        Allure.step("Opened the My Account dropdown successfully.");
    }

    @Step("Check link navigates to the correct page")
    @Then("each link should navigate to the correct page")
    public void each_link_should_navigate_to_correct_page() {
        Map<String, String> linkPageMappings = new HashMap<>();
        linkPageMappings.put("ManageProfile", "My Profile");
        linkPageMappings.put("Orders", "My Orders");
        linkPageMappings.put("Rewards", "Cargills Rewards");
        linkPageMappings.put("Offers", "Offers");
        linkPageMappings.put("ManageAddress", "Manage Address");
        linkPageMappings.put("ManageCard", "Manage Card");
        linkPageMappings.put("Notifications", "Notifications");
        linkPageMappings.put("Faq", "Manage Address");

        List<WebElement> links = myAccount.getDropdownLinks();
        for (WebElement link : links) {
            String linkText = link.getText();
            Allure.step("Clicking on the link: " + linkText);
            link.click();

            String expectedTitle = linkPageMappings.get(linkText);
            String actualTitle = myAccount.getCurrentPageTitle();

            try {
                Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch for link: " + linkText);
                Allure.step("Successfully navigated to the correct page: " + expectedTitle);
            } catch (AssertionError e){
                Allure.step("Navigation failed for link: " + linkText);
                throw e;
            }

            driver.navigate().back();
            myAccount.openDropdown();
        }
    }


}
