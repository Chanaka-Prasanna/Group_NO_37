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
    }

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
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(page_title_path));
            Allure.step("Navigated to Manage Address page");
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception navigating to my addresses: " + e.getMessage());
            Assert.fail("Blocked - Exception navigating to my addresses");
        }

    }

    @And("I see the addresses")
    public void i_see_the_addresses() {

        try {
            // Find all address divs inside the parent div
            dives_with_address = getAddressDivs();
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
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("DelAdd")));
                secondAddressDiv.findElement(By.className("DelAdd")).click();
                Allure.step("Clicked delete button for the second address");
                // Wait for the alert to be present
                wait.until(ExpectedConditions.alertIsPresent());

// Switch to    the alert and accept it (click OK)
                Alert alert = driver.switchTo().alert();
                alert.accept();
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirm_txt));
        Allure.step("Address deleted successfully");
    }

    @And("I confirm the deletion")
    public void i_confirm_deletion() {
        driver.findElement(confirm_button).click();
        Allure.step("Confirmed address deletion");
    }

    @Then("I should not see the address again")
    public void i_should_not_see_the_address_again() {

        try{
            int current_dives_of_address = getAddressDivs().size();
        if (current_dives_of_address == number_of_addresses - 1){
            Allure.step("Address has been deleted");

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


    // add tp POM model
    public  List<WebElement> getAddressDivs(){
        By parent_div_of_addresses = By.xpath("//*[@id=\"divOrderShow\"]");
        By address_class_name = By.className("mainAddress");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(parent_div_of_addresses));
        WebElement parentDiv = driver.findElement(parent_div_of_addresses);
        return parentDiv.findElements(address_class_name);
    }
}



