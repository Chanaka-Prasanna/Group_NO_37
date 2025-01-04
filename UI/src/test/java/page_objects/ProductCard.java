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

    By quantitySelectorPath = By.xpath(".//span[contains(@class, 'prdspan')]");
    By incrementButtonPath = By.xpath(".//span[contains(@class, 'plus')]");
    By decrementButtonPath = By.xpath(".//span[contains(@class, 'minus')]");
    By my_acc_path      =  By.id("spnAcc");
    By product_list = By.xpath("//div[@id='divProducts']/div[contains(@class, 'cargillProd')]");

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


    public WebElement getACard() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
        wait.until(ExpectedConditions.visibilityOfElementLocated(product_list));
        List<WebElement> productCards = driver.findElements(product_list);

        if (productCards.isEmpty()) {
            throw new NoSuchElementException("No product cards are currently available.");
        }

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

        WebElement firstCard = getACard();
        firstCard.click();

    }

    public String getCurrentUrl() {

        try{

            return driver.getCurrentUrl();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public boolean isItemAlreadyAdded() {

        try {

            driver.findElement(quantitySelectorPath); // Update selector as per your application
            return true;

        } catch (NoSuchElementException e) {

            return false;

        }
    }

    public void removeItemFromCart() {

        try {

            driver.findElement(decrementButtonPath).click(); // Update selector as per your application
            System.out.println("Item removed from cart.");

        } catch (NoSuchElementException e) {

            System.out.println("No item to remove.");

        }
    }

    public void clickAddItem() {


        List<WebElement> addButtons = driver.findElements(By.xpath(".//a[contains(text(), 'ADD')]")); // Update selector as per your application
        for (WebElement button : addButtons) {

            if (button.isDisplayed() && button.isEnabled()) {

                button.click();
                break;

            }
        }
    }

}


