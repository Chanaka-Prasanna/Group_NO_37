//package page_objects;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//public class CategoryPage {
//    WebDriver driver;
//
//    public CategoryPage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    public boolean areProductsDisplayed() {
//        try {
//            Thread.sleep(5000);
//           return driver.findElement(By.xpath("//*[@id=\"h3head\"]/span[2]")).isDisplayed();
//
//        } catch (NoSuchElementException | InterruptedException e) {
//            return false; // Element not found
//        }
//    }
//}

package page_objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryPage {
    WebDriver driver;

    By readyToCookMeat = By.xpath("/html/body/div[3]/div[2]/div[4]/div/div[1]/div[2]/div[2]/ul/li[1]");
    By processedMeat = By.xpath("/html/body/div[3]/div[2]/div[4]/div/div[1]/div[2]/div[2]/ul/li[2]");
    By rawMeat = By.xpath("/html/body/div[3]/div[2]/div[4]/div/div[1]/div[2]/div[2]/ul/li[3]");

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean areProductsDisplayed(String category) throws InterruptedException {
        Thread.sleep(5000);
        try {
            WebElement productList;
            if (category.equalsIgnoreCase("Vegetables")) {
                productList = driver.findElement(By.xpath("//*[@id=\"h3head\"]/span[2]"));
            } else if (category.equalsIgnoreCase("Fruits")) {
                productList = driver.findElement(By.xpath("//*[@id=\"h3head\"]/span[2]"));
            } else if (category.equalsIgnoreCase("Gifting")) {
                productList = driver.findElement(By.xpath("//*[@id=\"h3head\"]/span[2]"));
            } else if (category.equalsIgnoreCase("Stationery")) {
                productList = driver.findElement(By.xpath("//*[@id=\"h3head\"]/span[2]"));
            } else {
                throw new IllegalArgumentException("Unknown category: " + category);
            }
            return productList.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void selectSubCategory(String subCategory) throws InterruptedException {
        Thread.sleep(5000);

        // Click the sub-category based on the provided input
        switch (subCategory) {
            case "Ready To Cook Meat":
                driver.findElement(readyToCookMeat).click();
                break;
            case "Processed Meat":
                driver.findElement(processedMeat).click();
                break;
            case "Raw Meat":
                driver.findElement(rawMeat).click();
                break;
            default:
                throw new IllegalArgumentException("Invalid Sub-Category: " + subCategory);
        }

        // Wait for the header to appear
        String expectedHeaderText = subCategory;
        String headerXPath = "//*[@id=\"h3head\"]/span[2]";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(headerXPath)));

            String actualHeaderText = headerElement.getText();
            if (actualHeaderText.equals(expectedHeaderText)) {
                System.out.println("Verified: The header text matches the selected subcategory.");
            } else {
                throw new AssertionError("Header text mismatch. Expected: '" + expectedHeaderText + "', but found: '" + actualHeaderText + "'.");
            }
        } catch (TimeoutException e) {
            System.out.println(driver.getPageSource()); // Print the current DOM for debugging
            throw new AssertionError("Header text for 'Showing Products for " + subCategory + "' was not found.");
        }
    }

}

