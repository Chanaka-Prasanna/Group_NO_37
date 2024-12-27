package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import page_objects.Cart;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;

public class AddAddressSteps {

    WebDriver driver;

    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
    }

    By page_title_path      =    By.xpath("//*[@id=\"divM\"]/div[1]/h3");
    By add_address_btn      =    By.xpath("//*[@id=\"btnNewAddress\"]");
    By house_no_path         =    By.xpath("//*[@id=\"txtHouseNo\"]");
    By apartment_name_path   =    By.xpath("//*[@id=\"txtApartmentName\"]");
    By street_path           =    By.xpath("//*[@id=\"txtStreetName\"]");
    By land_mark_path        =    By.xpath("//*[@id=\"txtLandmark\"]");
    By dropdown_path         =    By.xpath("//*[@id=\"ddlLocality\"]");
    By city_path             =    By.xpath("//*[@id=\"txtCity\"]");
    By type_path             =    By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[6]/div[2]/div");
    By check_box_path        =    By.xpath("//*[@id='cbIsDefault']");
    By add_address_btn_below =      By.xpath("//*[@id=\"btnAddAddress\"]");
    @And("I navigate to the manage address page")
    public void i_navigate_to_the_mange_address_page(){
        try{
            driver.get(Config.env_values("BASE_URL")+"ManageAddress");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(page_title_path));
            String actual_title = driver.findElement(page_title_path).getText().trim();
            String expected_title = "Manage Address";

            if(actual_title.equals(expected_title)){
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
                driver.findElement(add_address_btn).click();
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

    @And("I enter new address")
    public void i_enter_new_address(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
            driver.findElement(house_no_path).sendKeys("343/1");
            driver.findElement(apartment_name_path).sendKeys("Kurunegala 1");
            driver.findElement(street_path).sendKeys("Maha weediya");
            driver.findElement(land_mark_path).sendKeys("Post office");
//            driver.findElement(city_path).sendKeys("Kurunegala"); // will automatically fill
            WebElement dropdown = driver.findElement(dropdown_path);
            Select select = new Select(dropdown);
            select.selectByIndex(1);

            List<WebElement> elements = driver.findElements(type_path);
            elements.getFirst().click();

            List<WebElement> addressTypes_as_p_tags = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[6]/div[2]/div"));


            String addressId = "";
            int addressType = 2;
            // Map addressType to specific ids
            switch (addressType) {
                case 1:
                    addressId = "typeHome";
                    break;
                case 2:
                    addressId = "typeWork";
                    break;
                case 3:
                    addressId = "typeOther";
                    break;
                default:
                    System.out.println("Invalid address type selected.");
                    return;
            }

            // Find the <a> tag by id and click
            WebElement addressElement = driver.findElement(By.id(addressId));
            addressElement.click();
            WebElement checkbox = driver.findElement(By.id("cbIsDefault"));

            if (!checkbox.isSelected()) {
                checkbox.click();
            }
            Allure.step("Address form has been  filled");

        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on filling form: " + e.getMessage());
            Assert.fail("Blocked - Exception on filling form");
        }

    }

    @When("I click on the save button")
    public void i_click_on_the_save_button(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnAddAddress")));
            driver.findElement(By.id("btnAddAddress")).click();
            Allure.step("Clicked on add address btn");
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on saving: " + e.getMessage());
            Assert.fail("Blocked - Exception on saving");
        }

    }

    @Then("I should see confirmation")
    public void i_should_see_confirmation(){
        try{
            // Locate the parent div
            By parent_div_of_addresses = By.id("//*[@id=\"divOrderShow\"]");
            By address_class_name = By.className("main-address");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(parent_div_of_addresses));
            WebElement parentDiv = driver.findElement(parent_div_of_addresses);
            // Find all address divs inside the parent div
            List<WebElement> addressDivs = parentDiv.findElements(address_class_name);
            // Check if address divs exist
            if (!addressDivs.isEmpty()) {
                Allure.step("Address has been added");
            } else {
                Allure.addAttachment("Failed to add address", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Allure.step("Test Failed - Failed add address");
                Assert.fail("Test Failed - Failed add address");
            }
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception on adding address: " + e.getMessage());
            Assert.fail("Blocked - Exception on adding address");
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
