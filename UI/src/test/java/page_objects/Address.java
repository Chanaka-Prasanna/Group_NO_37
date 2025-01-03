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
    By type_path             =    By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[6]/div[2]/div");
    By parent_div_of_addresses =  By.xpath("//*[@id=\"divOrderShow\"]");
    By address_class_name = By.className("main-address");

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
            return driver.findElement(page_title_path).getText().trim();


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


            WebElement dropdown = driver.findElement(dropdown_path);
            Select select = new Select(dropdown);
            select.selectByIndex(1);

            List<WebElement> elements = driver.findElements(type_path);
            elements.get(0).click();

            String addressId = "";
            int addressType = 2;
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

            WebElement addressElement = driver.findElement(By.id(addressId));
            addressElement.click();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void click_save_address() {
        try {

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnAddAddress\"]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
            Thread.sleep(500);


            try {

                saveButton.click();

            } catch (Exception e) {
                try {

                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);

                } catch (Exception e2) {

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

            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            longWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));

            longWait.until(ExpectedConditions.presenceOfElementLocated(parent_div_of_addresses));
            WebElement parentDiv = driver.findElement(parent_div_of_addresses);

            List<WebElement> addressDivs = null;
            try {

                addressDivs = parentDiv.findElements(address_class_name);

            } catch (Exception e) {

                addressDivs = parentDiv.findElements(By.cssSelector(".address-box, .address-item, [data-type='address']"));

            }

            String current_url = driver.getCurrentUrl();
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
