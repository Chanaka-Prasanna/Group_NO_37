package page_objects;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

import java.time.Duration;
import java.util.List;

public class Address {

    By page_title_path       =    By.xpath("//*[@id=\"divM\"]/div[1]/h3");
    By add_address_btn       =    By.xpath("//*[@id=\"btnNewAddress\"]");
    By house_no_path         =    By.xpath("//*[@id=\"txtHouseNo\"]");
    By apartment_name_path   =    By.xpath("//*[@id=\"txtApartmentName\"]");
    By street_path           =    By.xpath("//*[@id=\"txtStreetName\"]");
    By land_mark_path        =    By.xpath("//*[@id=\"txtLandmark\"]");
    By dropdown_path         =    By.xpath("//*[@id=\"ddlLocality\"]");
    By city_path             =    By.xpath("//*[@id=\"txtCity\"]");
    By type_path             =    By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[6]/div[2]/div");
    By check_box_path        =    By.xpath("//*[@id='cbIsDefault']");
    By add_address_btn_below =    By.xpath("//*[@id=\"btnAddAddress\"]");
    By parent_div_of_addresses =  By.xpath("//*[@id=\"divOrderShow\"]");
    By name_path             =    By.xpath("//*[@id=\"txtName\"]");
    By address_class_name = By.className("main-address");


    By delete_button = By.xpath("//button[contains(text(),'Delete')]");
    By confirm_button = By.xpath("//*[@id=\"btnOk\"]");
    By confirm_txt = By.xpath("//*[@id=\"txtAlertText1\"]");

    WebDriver driver;
    WebDriverWait wait;

    public Address(WebDriver driver){
       this.driver = driver;
       wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));

    }


    public String get_manage_address_page_title(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(page_title_path));

            // Retrieve the title text
            String s = driver.findElement(page_title_path).getText().trim();
           ;  // Print the title for debugging
            return s;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void click_add_address_btn(){
        try{
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(add_address_btn));

            driver.findElement(add_address_btn).click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void enter_address(){
        try{
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(house_no_path));
            driver.findElement(house_no_path).sendKeys("343/1");
            driver.findElement(apartment_name_path).sendKeys("Kurunegala 1");
            driver.findElement(street_path).sendKeys("Maha weediya");
            driver.findElement(land_mark_path).sendKeys("Post office");
//            driver.findElement(city_path).sendKeys("Kurunegala"); // will automatically fill

            WebElement dropdown = driver.findElement(dropdown_path);
            Select select = new Select(dropdown);
            select.selectByIndex(1);

            List<WebElement> elements = driver.findElements(type_path);
            //elements.getFirst().click();
            elements.get(0).click();

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
//            WebElement checkbox = driver.findElement(By.id("cbIsDefault"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
//
//            if (!checkbox.isSelected()) {
//                checkbox.click();
//            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void click_save_address() {
        try {
            // Wait for any loaders to disappear first
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));

            // Find the button
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnAddAddress\"]")));

            // Scroll the button into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);

            // Add a small wait after scrolling
            Thread.sleep(500);

            // Try multiple click strategies
            try {
                // Try regular click first
                saveButton.click();
            } catch (Exception e) {
                try {
                    // If regular click fails, try JavaScript click
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
                } catch (Exception e2) {
                    // If both fail, try moving to element and clicking
                    new org.openqa.selenium.interactions.Actions(driver)
                            .moveToElement(saveButton)
                            .click()
                            .perform();
                }
            }

            // Wait for the click to take effect
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("ManageAddress"),
                    ExpectedConditions.presenceOfElementLocated(parent_div_of_addresses)
            ));

        } catch (Exception e) {
            throw new RuntimeException("Failed to click save address button: " + e.getMessage());
        }
    }

    public boolean is_address_added() {
        try {
            // Wait for loader to disappear first
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            longWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));

            // Wait for parent div
            longWait.until(ExpectedConditions.presenceOfElementLocated(parent_div_of_addresses));
            WebElement parentDiv = driver.findElement(parent_div_of_addresses);


            // Try multiple selectors to find addresses
            List<WebElement> addressDivs = null;
            try {
                // Try the main-address class first
                addressDivs = parentDiv.findElements(address_class_name);
            } catch (Exception e) {
                // If that fails, try alternative selectors
                addressDivs = parentDiv.findElements(By.cssSelector(".address-box, .address-item, [data-type='address']"));
            }

            System.out.println("Number of addresses found: " + addressDivs.size());

            // Verify URL
            String current_url = driver.getCurrentUrl();
            String expected_url = Config.env_values("BASE_URL") + "ManageAddress";

            // If we found any addresses and we're on the right page, consider it successful
            boolean success = !addressDivs.isEmpty() && current_url.contains("ManageAddress");

            if (success) {
                System.out.println("Address verification successful");
            } else {
                System.out.println("Current URL: " + current_url);
                System.out.println("Expected URL contains: ManageAddress");
                System.out.println("Number of addresses: " + addressDivs.size());
            }

            return success;

        } catch (Exception e) {
            System.out.println("Error during address verification: " + e.getMessage());
            // Instead of throwing exception, return false
            return false;
        }
    }


    public  List<WebElement> getAddressDivs(){

        try{
            By parent_div_of_addresses = By.xpath("//*[@id=\"divOrderShow\"]");
            By address_class_name = By.className("mainAddress");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(parent_div_of_addresses));
            WebElement parentDiv = driver.findElement(parent_div_of_addresses);
            return parentDiv.findElements(address_class_name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // Method to click the delete button for a specific address
    public void click_on_delete_btn(WebElement addressDiv) {
        try{
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("DelAdd")));
            addressDiv.findElement(By.className("DelAdd")).click();
            Allure.step("Clicked delete button for the second address");
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            Allure.step("Accepted the alert for address deletion.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // Method to confirm address deletion
    public void wait_for_confirmation() {
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirm_txt));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void confirm_deletion() {
        try{
            driver.findElement(confirm_button).click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
