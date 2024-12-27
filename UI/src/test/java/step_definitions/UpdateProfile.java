package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
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
import utils.DriverFactory;

import java.time.Duration;

public class UpdateProfile {
    WebDriver driver;

    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
    }

    @And("I navigate to the profile page")
    public void navigate_to_profile_page() {
        try {
            driver.get("https://cargillsonline.com/ManageProfile");
            By account_page_txt = By.xpath("//*[@id=\"divM\"]/div[1]/h3");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(account_page_txt));
            String actual = driver.findElement(account_page_txt).getText().trim();
            String expected = "My Account";


            if(actual.equals(expected)){
                Allure.step("Navigated to profile page");
            }else{
                Assert.fail("Failed to load my account page");

            }

        } catch (Exception e) {
            Allure.step("Exception on navigating to my account");
        }
    }

    @When("I update my profile information")
    public void update_profile_information() {
        try {
            System.out.println("1--------");
            driver.findElement(By.xpath("//*[@id=\"txtFirstName\"]")).sendKeys("Chanaka 1");
            System.out.println("2--------");
            driver.findElement(By.xpath("//*[@id=\"txtLastName\"]")).sendKeys("ppasanna");
            System.out.println("2--------");
            driver.findElement(By.xpath("//*[@id=\"ddlGender\"]")).sendKeys("01/12/2008");;
//            WebElement dropdown = driver.findElement(By.id("countryDropdown"));
//            dropdown.getText().trim();
            System.out.println("4--------");
            WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"ddlGender\"]"));
            Select select = new Select(dropdown);
            select.selectByVisibleText("Male");
            System.out.println("5--------");
//
            if ( driver.findElement(By.xpath("//*[@id=\"txtNIC\"]")).isDisplayed()){
                driver.findElement(By.xpath("//*[@id=\"txtNIC\"]")).sendKeys("200032569345");
                System.out.println("6--------");
            }

            driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();
            Allure.step("Profile information updated");
        } catch (Exception e) {
            Assert.fail("Failed to update profile");
            Allure.step("Failed to update profile");

        }

    }

    @Then("I should see a success message")
    public void i_should_see_a_success_message(){
        try{

            String expected = "Profile Updated successfully";
            String actual =   driver.findElement(By.xpath("//*[@id=\"txtAlertText1\"]")).getText().trim();

            if (expected.equals(actual)){
                Allure.step("Profile is updated");

            }else{
                Assert.fail("Failed to update profile");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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
