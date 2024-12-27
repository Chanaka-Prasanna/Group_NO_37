//package page_objects;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import page_objects.CategoryPage;
//
//public class HomePage {
//
//    // XPath to the vegetable category link
//    By vegetable_path = By.xpath("//*[@id=\"divDynamicSection\"]/div[3]/div[1]/div/div[2]/div[2]/div[1]/div/a");
//
//    WebDriver driver;
//
//    public HomePage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    public CategoryPage selectCategory() {
//        // Explicit wait to wait for the element to be clickable
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Updated to use Duration
//        WebElement vegetableCategory = wait.until(ExpectedConditions.elementToBeClickable(vegetable_path));
//        vegetableCategory.click();
//        return new CategoryPage(driver);
//    }
//}

package page_objects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    // XPaths to the category links
    By vegetablePath = By.xpath("//*[@id=\"divDynamicSection\"]/div[3]/div[1]/div/div[2]/div[2]/div[1]/div/a");
    By fruitsPath = By.xpath("//*[@id=\"divDynamicSection\"]/div[3]/div[1]/div/div[2]/div[2]/div[2]/div/a");

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public CategoryPage selectCategory(String category) {
        // Explicit wait to wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement categoryElement;
        if (category.equalsIgnoreCase("Vegetables")) {
            categoryElement = wait.until(ExpectedConditions.elementToBeClickable(vegetablePath));
        } else if (category.equalsIgnoreCase("Fruits")) {
            categoryElement = wait.until(ExpectedConditions.elementToBeClickable(fruitsPath));
        } else {
            throw new IllegalArgumentException("Unknown category: " + category);
        }
        categoryElement.click();
        return new CategoryPage(driver);
    }
}

