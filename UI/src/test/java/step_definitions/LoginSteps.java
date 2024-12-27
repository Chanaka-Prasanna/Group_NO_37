//package step_definitions;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.Reporter;
//import page_objects.Login;
//import utils.Config;
//import utils.DriverFactory;
//
//public class LoginSteps {
//    WebDriver driver;
//    Login login;
//
//    @Before
//    public void setup(){
//        driver = DriverFactory.get_driver();
//    }
//
//    @Given("user is in the login page")
//    public void user_is_in_the_login_page() {
//        try{
//            driver.get(Config.get_base_url("BASE_URL") + "Login");
//            login = new Login(driver);
//            Reporter.log("Navigated to the login page",true);
//        }catch(Exception e){
//            Reporter.log("Error navigating to the login page: " + e.getMessage(),true);
//            Assert.fail("Blocked - Unable to reach login page");
//        }
//    }
//
//    @When("user enters text: {string} and password: {string}")
//    public void user_enters_text_and_password(String text, String password) {
//        try{
//            login.enter_email_or_phone(text);
//            login.enter_password(password);
//            Reporter.log("Entered Credentials: " + text + " / " + password, true);
//        }catch(Exception e){
//            Reporter.log("Failed to  enter credentials: " + e.getMessage(), true);
//            Assert.fail("Blocked - Unable to enter credentials");
//        }
//
//    }
//
//    @When("user clicks on the login button")
//    public void user_clicks_on_the_login_button() {
//        try{
//            login.click_login();
//            Reporter.log("Clicked login button",true);
//        }catch (Exception e){
//            Reporter.log("Blocked -  Unable to click login button",true);
//            Assert.fail("Blocked - Unable to click login button");
//        }
//
//
//
//    }
//
//    @Then("user should  see the my account section")
//    public void user_should_see_the_my_account_section() {
//        try{
//
//            String actual_txt =  login.is_acc_section_displayed();
//            String expected_txt = "My Account";
//
//            if(actual_txt.equals(expected_txt)){
//
//                Reporter.log("Test Passed",true);
//                Assert.assertEquals(actual_txt,expected_txt);
//
//            }else{
//
//                Reporter.log("Test Failed - Expected: " + expected_txt + " but received: " + actual_txt,true);
//                Assert.fail("Failed - User can't see the My Account section");
//
//            }
//        }catch (Exception e){
//
//            Reporter.log("Error verifying login result: " + e.getMessage(), true);
//            Assert.fail("Blocked - Exception occurred during verification");
//        }
//    }
//
//    @After
//    public void tear_down(){
//
//        try{
//
//            DriverFactory.close_driver();
//            Reporter.log("Driver closed successfully",true);
//
//        }catch(Exception e){
//            Reporter.log("Error closing driver: " + e.getMessage(),true);
//        }
//
//    }
//
//}



package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_objects.Login;
import utils.Config;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class LoginSteps {
    WebDriver driver;
    Login login;

    @Before
    public void setup() {
        driver = DriverFactory.get_driver();
    }

    @Given("user is in the login page")
    @Step("Navigate to Login Page")
    public void user_is_in_the_login_page() {
        try {
            driver.get(Config.env_values("BASE_URL") + "Login");
            login = new Login(driver);
            Allure.step("Successfully navigated to login page");
        } catch (Exception e) {
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Error navigating to login page: " + e.getMessage());
            Assert.fail("Blocked - Unable to reach login page");
        }
    }

    @When("user enters text: {string} and password: {string}")
    @Step("Enter Credentials")
    public void user_enters_text_and_password(String text, String password) {
        try {
            login.enter_email_or_phone(text);
            login.enter_password(password);
            Allure.step("Entered Credentials: " + text + " / " + password);
        } catch (Exception e) {
            Allure.addAttachment("Failed to Enter Credentials", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Failed to enter credentials: " + e.getMessage());
            Assert.fail("Blocked - Unable to enter credentials");
        }
    }

    @When("user clicks on the login button")
    @Step("Click Login Button")
    public void user_clicks_on_the_login_button() {
        try {
            login.click_login();
            Allure.step("Clicked login button");
        } catch (Exception e) {
            Allure.addAttachment("Click Failed", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Unable to click login button");
            Assert.fail("Blocked - Unable to click login button");
        }
    }

    @Then("user should  see the my account section")
    @Step("Verify My Account Section")
    public void user_should_see_the_my_account_section() {
        try {
            String actual_txt = login.is_acc_section_displayed();
            String expected_txt = "My Account";

            if (actual_txt.equals(expected_txt)) {
                Allure.step("Test Passed - User successfully logged in");
                Assert.assertEquals(actual_txt, expected_txt);
            } else {
                Allure.addAttachment("Failed Verification", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
                Allure.step("Test Failed - Expected: " + expected_txt + " but received: " + actual_txt);
                Assert.fail("Failed - User can't see the My Account section");
            }
        } catch (Exception e) {
            Allure.addAttachment("Error during Verification", new ByteArrayInputStream(((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES)));
            Allure.step("Blocked - Exception during verification: " + e.getMessage());
            Assert.fail("Blocked - Exception during verification");
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
