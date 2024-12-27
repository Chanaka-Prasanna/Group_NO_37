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
    By my_acc_path      =  By.xpath("//*[@id=\"spnAcc\"]");
    By product_list = By.xpath("//div[@id='divProducts']/div[contains(@class, 'cargillProd')]");// Locator for filtered products


    WebDriver driver;

    public String loggedIn(){
        try{
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

    public void clickAddButton() {


        // Select the first card and click its "Add" button (deterministic)
        WebElement firstCard = getACard();
        firstCard.findElement(By.xpath(".//a[contains(text(), 'ADD')]")).click();

        // Alternative: Randomly select a product card (non-deterministic)
        // Random random = new Random();
        // int index = random.nextInt(productCards.size());
        // WebElement randomCard = productCards.get(index);
        // randomCard.findElement(By.xpath(".//button[contains(@class, 'add-button-class')]")).click();
    }




    public boolean isQuantitySelectorDisplayed() {
        System.out.println(getACard().getText());
        return getACard().findElement(quantitySelectorPath).isDisplayed();
    }

    public void incrementQuantity() {
        getACard().findElement(incrementButtonPath).click();
    }

    public void decrementQuantity() {
        getACard().findElement(decrementButtonPath).click();
    }

    public int getQuantity() {
        WebElement quantityElement = getACard().findElement(quantitySelectorPath);
        return Integer.parseInt(quantityElement.getAttribute("value"));
    }

    public void clickProductLink() {
        // Find all product cards

        WebElement firstCard = getACard();
        firstCard.click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
