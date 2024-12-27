package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyAccount {
    WebDriver driver;

    // Locators
    By myAccountDropdown = By.xpath("//*[@id=\"spnSession\"]/div/button"); // Update with actual locator
    By dropdownLinks = By.cssSelector("dropdown a");

    public MyAccount(WebDriver driver) {
        this.driver = driver;
    }

    public void openDropdown() {
        driver.findElement(myAccountDropdown).click();
    }

    public List<WebElement> getDropdownLinks() {
        return driver.findElements(dropdownLinks);
    }

    public String getCurrentPageTitle() {
        return driver.getTitle();
    }
}
