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

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryPage {
    WebDriver driver;

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
            } else {
                throw new IllegalArgumentException("Unknown category: " + category);
            }
            return productList.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

