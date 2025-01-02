package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

import java.time.Duration;

public class Profile {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By account_page_txt = By.xpath("//*[@id=\"divM\"]/div[1]/h3");
    private final By f_name_path = By.xpath("//*[@id=\"txtFirstName\"]");
    private final By l_name_path = By.xpath("//*[@id=\"txtLastName\"]");
    private final By b_day_path = By.xpath("//*[@id=\"txtDob\"]");
    private final By gender_path = By.xpath("//*[@id=\"ddlGender\"]");
    private final By nic_path = By.xpath("//*[@id=\"txtNIC\"]");
    private final By save_btn_path = By.xpath("//*[@id=\"btnSave\"]");
    private final By success_message_path = By.xpath("//*[@id=\"txtAlertText1\"]");

    public Profile(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String get_profile_page_title() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(account_page_txt));
        return driver.findElement(account_page_txt).getText().trim();
    }

    public void update_profile() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(f_name_path));

        // Update First Name
        WebElement fname = driver.findElement(f_name_path);
        fname.clear();
        fname.sendKeys("Chanaka");

        // Update Last Name
        WebElement lname = driver.findElement(l_name_path);
        lname.clear();
        lname.sendKeys("Dilashan");

        // Update Birthday
        driver.findElement(b_day_path).sendKeys("01/12/2008");

        // Update Gender
        WebElement dropdown = driver.findElement(gender_path);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Female");

        // Update NIC if visible
        if (driver.findElement(nic_path).isDisplayed()) {
            driver.findElement(nic_path).sendKeys("200032569345");
        }
    }

    public void click_save() {
        wait.until(ExpectedConditions.elementToBeClickable(save_btn_path)).click();
    }

    public String get_success_message() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(success_message_path));
        return driver.findElement(success_message_path).getText().trim();
    }
}
