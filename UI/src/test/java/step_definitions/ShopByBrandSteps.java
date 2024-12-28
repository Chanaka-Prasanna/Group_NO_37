package step_definitions;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import page_objects.ShopByBrand;

import utils.Config;
import utils.DriverFactory;

import java.time.Duration;
import java.util.List;

public class ShopByBrandSteps {
    WebDriver driver;
    ShopByBrand shopByBrandPage;

    @Step("Navigate to the Shop by Brand page")
    @Given("user is on the Shop by Brand page")
    public void user_is_on_the_shop_by_brand_page() {
        driver = DriverFactory.get_driver();
        driver.get(Config.env_values("BASE_URL")); // Update URL
        shopByBrandPage = new ShopByBrand(driver);
        Allure.step("Navigated to Shop by Brand page: " + driver.getCurrentUrl());
        attachScreenshot("Shop by Brand Page");
    }

    @Step("Click each brand item and verify redirection")
    @When("user clicks on each brand item")
    public void user_clicks_on_each_brand_item() {
        List<WebElement> brandItems = shopByBrandPage.getAllBrandItems();
        System.out.println("Brand Items Found: " + brandItems.size());

        for (WebElement item : brandItems) {
            WebElement link = shopByBrandPage.getBrandLink(item);
            String brandName = link.getText().isEmpty() ? "Unknown Brand" : link.getText();
            Allure.step("Clicking brand item: " + brandName);

            try {
                // Scroll into view and adjust position
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150);"); // Adjust for sticky headers

                // Retry clicking the element
                for (int i = 0; i < 3; i++) {
                    try {
                        link.click();
                        break;
                    } catch (ElementClickInterceptedException e) {
                        Thread.sleep(500); // Retry after a short delay
                    }
                }

                // Wait for the page to load and the URL to change or a specific element to appear
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

                // Wait for a specific element or URL change indicating the new page has loaded
                wait.until(ExpectedConditions.urlContains("Product"));
                // OR wait for a specific element to appear on the new page
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("divProducts")));

                // Validate the current URL
                String currentUrl = driver.getCurrentUrl();
                System.out.println(currentUrl);
                Allure.step("Navigated to URL: " + currentUrl);
                attachScreenshot("Brand Page - " + brandName);

                Assert.assertTrue(currentUrl.matches("https://cargillsonline\\.com/Product/.*"),
                        "Invalid URL structure for brand: " + brandName + " - " + currentUrl);

                shopByBrandPage.navigateBack();
                Allure.step("Navigated back to Shop by Brand page");

            } catch (ElementClickInterceptedException e) {
                attachScreenshot("Error Page - " + brandName);
                Allure.step("Element not clickable for brand: " + brandName);
                Assert.fail("Element not clickable for brand: " + brandName, e);
            } catch (StaleElementReferenceException e) {
                // Handle stale element scenario by retrying or refreshing the element
                attachScreenshot("Stale Element Error - " + brandName);
                Allure.step("Stale element reference for brand: " + brandName);
                Assert.fail("Stale element reference for brand: " + brandName, e);
            } catch (Exception e) {
                attachScreenshot("Error Page - " + brandName);
                Allure.step("Failed to verify URL for brand: " + brandName);
                Assert.fail("Failed to verify URL for brand: " + brandName, e);
            }
        }
    }



    @Step("Verify user is redirected to the correct brand page")
    @Then("user should be redirected to the correct brand page")
    public void user_should_be_redirected_to_the_correct_brand_page() {

        Allure.step("Verification completed for all brand items.");
    }

    @Attachment(value = "{0}", type = "image/png")
    public void attachScreenshot(String description) {
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
