package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        List<String> productTexts = new ArrayList<>();

        // Extract product details from WebElements
        for (WebElement product : products) {
            productTexts.add(product.getText().trim());
        }

        switch (filterCriterion) {
            case "Price - Low to High":
                return isPriceSorted(productTexts, true); // Ascending order
            case "Price - High to Low":
                return isPriceSorted(productTexts, false); // Descending order
            case "A - Z":
                return isAlphabeticallySorted(productTexts, true); // Ascending order
            case "Z - A":
                return isAlphabeticallySorted(productTexts, false); // Descending order
            case "":
                return true;
            default:
                throw new IllegalArgumentException("Unknown filter criterion: " + filterCriterion);
        }
    }

    private boolean isPriceSorted(List<String> productTexts, boolean ascending) {
        List<Double> prices = new ArrayList<>();

        for (String text : productTexts) {
            try {
                // Use a regular expression to extract the numeric part of the price
                Pattern pattern = Pattern.compile("Rs\\.\\s*(\\d+(?:\\.\\d{2})?)");
                Matcher matcher = pattern.matcher(text);

                if (matcher.find()) {
                    // Group 1 contains the numeric part of the price
                    String priceString = matcher.group(1);
                    prices.add(Double.parseDouble(priceString));
                } else {
                    throw new NumberFormatException("Price format not found in text: " + text);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid price format: " + text);
                throw e;
            }
        }

        // Validate sorting order
        for (int i = 1; i < prices.size(); i++) {
            if (ascending && prices.get(i) > prices.get(i - 1)) return false;
            if (!ascending && prices.get(i) < prices.get(i - 1)) return false;
        }

        return true;
    }



    // Helper to check if strings are sorted alphabetically
    private boolean isAlphabeticallySorted(List<String> productTexts, boolean ascending) {
        for (int i = 1; i < productTexts.size(); i++) {
            if (ascending && productTexts.get(i).compareToIgnoreCase(productTexts.get(i - 1)) < 0) return false;
            if (!ascending && productTexts.get(i).compareToIgnoreCase(productTexts.get(i - 1)) > 0) return false;
        }
        return true;
    }

}
