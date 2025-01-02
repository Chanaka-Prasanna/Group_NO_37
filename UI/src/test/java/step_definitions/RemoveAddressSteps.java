package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import page_objects.Address;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;

public class RemoveAddressSteps {
    WebDriver driver;
    WebDriverWait wait;
    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
    }
    Address address;

    By page_title_path      =    By.xpath("//*[@id=\"divM\"]/div[1]/h3");
    By delete_button = By.xpath("//button[contains(text(),'Delete')]");
    By confirm_button = By.xpath("//*[@id=\"btnOk\"]");
    By confirm_txt  =  By.xpath("//*[@id=\"txtAlertText1\"]");
    List<WebElement> dives_with_address;
    int number_of_addresses;

    @And("I am on addresses page")
    public void i_am_on_addresses_page() {

        try{
            driver.get(Config.env_values("BASE_URL") + "ManageAddress");
            address = new Address(driver);
            String actual_title = address.get_manage_address_page_title();
            String expected_title = "Manage Address";

            if(expected_title.equals(actual_title)){
                Allure.step("Successfully navigated to manage address page");
            }else{

                Allure.addAttachment("Navigation Failed", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Allure.step("Test Failed - Expected to see " + expected_title + " but saw "+ actual_title);
                Assert.fail("Test Failed - Manage address page didn't load");

            }

        } catch (Exception e) {

            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on manage address page: " + e.getMessage());
            Assert.fail("Blocked - Exception  manage address page");

        }


    }

    @And("I see the addresses")
    public void i_see_the_addresses() {

        try {

            dives_with_address = address.getAddressDivs();
            number_of_addresses = dives_with_address.size();
            if (!dives_with_address.isEmpty()) {
                Allure.step("Addresses are present");
            } else {
                Allure.addAttachment("No addresses remove", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Allure.step("Test Failed - No addresses to remove");
                Assert.fail("Test Failed -No addresses to remove");
            }
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on seeing address: " + e.getMessage());
            Assert.fail("Blocked - Exception on seeing address");
        }
    }

    @When("I click the delete button for one")
    public void i_click_delete_button_for_one() {
        try{
            if (dives_with_address.size() >= 2) {
//              // welawkata default ekak wath nethiwenna puluwan. handle krnna
                WebElement secondAddressDiv = dives_with_address.get(1);
                address.click_on_delete_btn(secondAddressDiv);
                Allure.step("Accepted the alert for address deletion.");
            } else {
                Allure.step("Less than two addresses found. Unable to delete default address.");
                Assert.fail("Test Failed - Default address not available for deletion.");
            }
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on deleting address: " + e.getMessage());
            Assert.fail("Blocked - Exception on deleting address");
        }


    }

    @Then("I should see a confirmation prompt")
    public void i_should_see_confirmation_prompt() {

        try {
            address.wait_for_confirmation();
            Allure.step("Address deleted successfully");
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on displaying alert: " + e.getMessage());
            Assert.fail("Blocked - Exception on displaying alert");
        }


    }

    @And("I confirm the deletion")
    public void i_confirm_deletion() {
        try{
            address.confirm_deletion();
            Allure.step("Confirmed address deletion");
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on confirmation of deletion: " + e.getMessage());
            Assert.fail("Blocked - Exception on confirmation of deletion");
        }

    }

    @Then("I should not see the address again")
    public void i_should_not_see_the_address_again() {

        try{
            int current_dives_of_address = address.getAddressDivs().size();
        if (current_dives_of_address == number_of_addresses - 1){
            Allure.step("Address has been deleted");
            System.out.println(current_dives_of_address);
            System.out.println( number_of_addresses - 1);

        }else{
            Allure.step("Address hasn't been deleted");
            Assert.fail("Address hasn't been deleted");
        }

        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on removing address: " + e.getMessage());
            Assert.fail("Blocked - Exception  on removing address");
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



