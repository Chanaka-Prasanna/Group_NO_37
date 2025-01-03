package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductCard {

    By addButtonPath = By.xpath("//*[@id=\"crtAdd2085\"]/a");
    By quantitySelectorPath = By.xpath(".//span[contains(@class, 'prdspan')]");
    By incrementButtonPath = By.xpath(".//span[contains(@class, 'plus')]");
    By decrementButtonPath = By.xpath(".//span[contains(@class, 'minus')]");
    By productLinkPath = By.id("card_");
    By my_acc_path      =  By.id("spnAcc");
    By product_list = By.xpath("//div[@id='divProducts']/div[contains(@class, 'cargillProd')]");
    By add_buttom = By.xpath(".//a[contains(text(), 'ADD')]");


    WebDriver driver;

    public String loggedIn(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
            wait.until(ExpectedConditions.visibilityOfElementLocated(my_acc_path));
            return driver.findElement(my_acc_path).getText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProductCard(WebDriver driver) {
        this.driver = driver;
    }

//    public void clickAddButton() {
//        driver.findElement(addButtonPath).click();
//    }

    public WebElement getACard() {
        // Find all product cards
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
        wait.until(ExpectedConditions.visibilityOfElementLocated(product_list));
        List<WebElement> productCards = driver.findElements(product_list);
        // Ensure there are product cards available
        if (productCards.isEmpty()) {
            throw new NoSuchElementException("No product cards are currently available.");
        }

        // Select the first card and click its "Add" button (deterministic)
        return productCards.get(0);
    }

    public boolean isQuantitySelectorDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantitySelectorPath));
        System.out.println(getACard().getText());
        return getACard().findElement(quantitySelectorPath).isDisplayed();
    }

    public void incrementQuantity() {
        getACard().findElement(incrementButtonPath).click();
    }

    public void decrementQuantity() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        getACard().findElement(decrementButtonPath).click();
    }

    public int getQuantity() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantitySelectorPath));
        WebElement quantityElement = getACard().findElement(quantitySelectorPath);
        return Integer.parseInt(quantityElement.getText());
    }

    public void clickProductLink() {
        // Find all product cards

        WebElement firstCard = getACard();
        firstCard.click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isItemAlreadyAdded() {
        // Check if the quantity selector or any "Remove" button exists
        try {
            driver.findElement(quantitySelectorPath); // Update selector as per your application
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void removeItemFromCart() {
        // Logic to remove the item from the cart (click "Remove" button)
        try {
            driver.findElement(decrementButtonPath).click(); // Update selector as per your application
            System.out.println("Item removed from cart.");
        } catch (NoSuchElementException e) {
            System.out.println("No item to remove.");
        }
    }

    public void clickAddItem() {
        // Logic to move to the next product card (e.g., click on the next add button)
        List<WebElement> addButtons = driver.findElements(By.xpath(".//a[contains(text(), 'ADD')]")); // Update selector as per your application
        for (WebElement button : addButtons) {
            if (button.isDisplayed() && button.isEnabled()) {
                button.click();
                break;
            }
        }
    }

}


