package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FilterMenu {
    WebDriver driver;

    // Locators for each filter section
    By sub_category_filter = By.xpath("//h4[contains(text(), 'Sub Category')]/following-sibling::ul/li");
    By discount_filter = By.xpath("//h4[contains(text(), 'Discount')]/following-sibling::ul/li");// Discount options
    By price_filter = By.xpath("//h4[contains(text(), 'Price')]/following-sibling::ul/li");// Price options
    By alphabetical_filter = By.xpath("//h4[contains(text(), 'Alphabetical')]/following-sibling::ul/li"); // Alphabetical options
    By clear_all_button = By.xpath("//*[@id=\"divM\"]/div[4]/div/div[1]/div[2]/div[1]/a[2]"); // Clear All button
    By product_list = By.xpath("//div[@id='divProducts']/div[contains(@class, 'cargillProd')]");// Locator for filtered products

    public FilterMenu(WebDriver driver) {
        this.driver = driver;
    }

    // Method to select an option from a filter
    public void select_filter_option(String filterType, String option) {
        List<WebElement> filterOptions;

        switch (filterType.toLowerCase()) {
            case "sub category":
                filterOptions = driver.findElements(sub_category_filter);
                break;
            case "discount":
                filterOptions = driver.findElements(discount_filter);
                break;
            case "price":
                filterOptions = driver.findElements(price_filter);
                break;
            case "alphabetical":
                filterOptions = driver.findElements(alphabetical_filter);
                break;
            default:
                throw new IllegalArgumentException("Invalid filter type: " + filterType);
        }


        for (WebElement opt : filterOptions) {
            if (opt.getText().equalsIgnoreCase(option)) {
                opt.click();
                break;
            }
        }
    }

    // Method to click "Clear All"
    public void click_clear_all() {
        driver.findElement(clear_all_button).click();
    }

    // Method to validate filtered products
    public boolean is_filter_applied(String filterCriterion) {
        List<WebElement> products = driver.findElements(product_list);

        for (WebElement product : products) {
            System.out.println(product.getText());
            if (!product.getText().contains(filterCriterion)) {
                return false;
            }
        }
        return true;
    }
}
