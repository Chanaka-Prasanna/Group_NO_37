package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Config;

import java.io.UnsupportedEncodingException;

public class SearchBar {
    WebDriver driver;

    // Locators for the search bar and search button
    By searchBar = By.xpath("//*[@id=\"txtSearch\"]");
    By searchButton = By.xpath("//*[@id=\"btnSearch\"]"); // Update with the actual locator for the search button

    public SearchBar(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter a search term
    public void enterSearchTerm(String term) {
        driver.findElement(searchBar).clear();
        driver.findElement(searchBar).sendKeys(term);
    }

    // Method to click the search button
    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    // Method to validate the URL of the search results page
    public boolean isSearchResultsPageURLCorrect(String searchTerm) throws UnsupportedEncodingException {
        String encodedSearchTerm = searchTerm.replace(" ", "%20");
        String expectedURL = Config.env_values("BASE_URL") + "product/" + encodedSearchTerm + "?PS=" + encodedSearchTerm;
        String currentURL = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentURL);
        System.out.println("Expected URL: " + expectedURL);
        return currentURL.equals(expectedURL);
    }

    // Method to validate if search results are displayed
    public boolean areSearchResultsDisplayed() {
        return driver.findElements(By.cssSelector(".productListHeading")).size() > 0;
    }
}
