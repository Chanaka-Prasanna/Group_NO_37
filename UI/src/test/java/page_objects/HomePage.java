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

    By vegetable_path = By.xpath("//*[@id=\"divDynamicSection\"]/div[3]/div[1]/div/div[2]/div[2]/div[1]/div/a");
    By fruits_path = By.xpath("//*[@id=\"divDynamicSection\"]/div[3]/div[1]/div/div[2]/div[2]/div[2]/div/a");
    By faceBook_path = By.xpath("/html/body/div[7]/div[1]/div[3]/div/div[2]/ul/li[1]/a/i");
    By instagram_path = By.xpath("/html/body/div[7]/div[1]/div[3]/div/div[2]/ul/li[3]/a/i");

    By google_play_link = By.xpath("/html/body/div[7]/div[1]/div[3]/div/div[1]/a[1]");
    By app_store_link = By.xpath("/html/body/div[7]/div[1]/div[3]/div/div[1]/a[2]");

    By gifting_link = By.xpath("//*[@id=\"footerCategory\"]/li[23]/a");
    By stationery_link = By.xpath("//*[@id=\"footerCategory\"]/li[28]/a");
    private By baby_products_path = By.xpath("//*[@id=\"mainMenu\"]/li[2]/a");
    private By beverages_path = By.xpath("//*[@id=\"mainMenu\"]/li[4]/a");
    private By food_cupboard_path = By.xpath("//*[@id=\"mainMenu\"]/li[5]/a");
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public CategoryPage selectCategory(String category) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement categoryElement;
        if (category.equalsIgnoreCase("Vegetables")) {
            categoryElement = wait.until(ExpectedConditions.elementToBeClickable(vegetable_path));
        } else if (category.equalsIgnoreCase("Fruits")) {
            categoryElement = wait.until(ExpectedConditions.elementToBeClickable(fruits_path));
        } else if (category.equalsIgnoreCase("Baby Products")) {
            categoryElement = wait.until(ExpectedConditions.elementToBeClickable(baby_products_path));
        } else if (category.equalsIgnoreCase("Beverages")) {
            categoryElement = wait.until(ExpectedConditions.elementToBeClickable(beverages_path));
        } else if (category.equalsIgnoreCase("Food Cupboard")) {
            categoryElement = wait.until(ExpectedConditions.elementToBeClickable(food_cupboard_path));
        } else {
            throw new IllegalArgumentException("Unknown category: " + category);
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", categoryElement);

        return new CategoryPage(driver);
    }

    public CategoryPage selectCategoryInFooter(String category) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement categoryElement;

        if (category.equalsIgnoreCase("Gifting")) {
            categoryElement = wait.until(ExpectedConditions.presenceOfElementLocated(gifting_link));
        } else if (category.equalsIgnoreCase("Stationery")) {
            categoryElement = wait.until(ExpectedConditions.presenceOfElementLocated(stationery_link));
        } else {
            throw new IllegalArgumentException("Unknown category: " + category);
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", categoryElement);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoryElement);

        return new CategoryPage(driver);
    }



    public void clickSocialMediaIcon(String socialMedia) {
        By path = switch (socialMedia.toLowerCase()) {
            case "facebook" -> faceBook_path;
            case "instagram" -> instagram_path;
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
            case "google play" -> google_play_link;
            case "app store" -> app_store_link;
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


