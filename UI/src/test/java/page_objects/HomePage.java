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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    By vegetablePath = By.xpath("//*[@id=\"divDynamicSection\"]/div[3]/div[1]/div/div[2]/div[2]/div[1]/div/a");
    By fruitsPath = By.xpath("//*[@id=\"divDynamicSection\"]/div[3]/div[1]/div/div[2]/div[2]/div[2]/div/a");
    By faceBookPath = By.xpath("/html/body/div[7]/div[1]/div[3]/div/div[2]/ul/li[1]/a/i");
    By instagramPath = By.xpath("/html/body/div[7]/div[1]/div[3]/div/div[2]/ul/li[3]/a/i");

    By googlePlayLink = By.xpath("/html/body/div[7]/div[1]/div[3]/div/div[1]/a[1]");
    By appStoreLink = By.xpath("/html/body/div[7]/div[1]/div[3]/div/div[1]/a[2]");
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public CategoryPage selectCategory(String category) {

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

    public void clickSocialMediaIcon(String socialMedia) {
        By path = switch (socialMedia.toLowerCase()) {
            case "facebook" -> faceBookPath;
            case "instagram" -> instagramPath;
            default -> throw new IllegalArgumentException("Unsupported social media: " + socialMedia);
        };

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(path));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            wait.until(ExpectedConditions.elementToBeClickable(element));

            element.click();
        } catch (Exception e) {
            System.out.println("Error clicking the element: " + e.getMessage());
            try {
                WebElement element = driver.findElement(path);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } catch (Exception jsException) {
                throw new RuntimeException("Failed to click the element using both standard and JavaScript methods: " + jsException.getMessage());
            }
        }
    }

        public String getCurrentURL() {
            return driver.getCurrentUrl();
        }

    public void clickAppDownloadLink(String link) {
        By path = switch (link.toLowerCase()) {
            case "google play" -> googlePlayLink;
            case "app store" -> appStoreLink;
            default -> throw new IllegalArgumentException("Unsupported social media: " + link);
        };

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(path));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            wait.until(ExpectedConditions.elementToBeClickable(element));

            element.click();
        } catch (Exception e) {
            System.out.println("Error clicking the element: " + e.getMessage());
            try {
                WebElement element = driver.findElement(path);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } catch (Exception jsException) {
                throw new RuntimeException("Failed to click the element using both standard and JavaScript methods: " + jsException.getMessage());
            }
        }
    }
    }


