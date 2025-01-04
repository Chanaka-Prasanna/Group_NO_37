package page_objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class Cart {

    By my_acc_path      =  By.id("spnAcc");
    By product_page_txt =  By.xpath("//*[@id=\"h3head\"]/span[1]");
    By products_paths   =  By.xpath("//*[@id=\"divProducts\"]//*[@id=\"card_\"]");
    By product_path     =  By.xpath("//*[@id=\"card_\"]");
    By add_to_cart_path =  By.xpath("//*[@id=\"crtAdd1229\"]/a");
    By cart_bbl_path    =  By.xpath("//*[@id=\"divCartBubble\"]");
    By cart_icon_path   =  By.xpath("//*[@id=\"btnCart\"]/p/a");
    By products_div_path=  By.xpath("//*[@id=\"myCartItems\"]");
    By clear_cart_btn_path      = By.xpath("//*[@id=\"myCartClear\"]");
    By clear_alert_txt_path     = By.xpath("//*[@id=\"txtAlertText\"]");
    By confirm_clear_btn_path   = By.xpath("//*[@id=\"btnClearCartOk\"]");
    By cancell_cart_path        = By.xpath("//*[@id=\"cancelCart\"]");
    By plusMinusLocator     = By.id("crtplus1229");
    By plusButtonLocator    = By.xpath("//*[@id=\"plus1229\"]");

    WebDriver driver;

    WebDriverWait wait;

    public Cart(WebDriver driver){

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    public String loggedIn(){
        try{

            return driver.findElement(my_acc_path).getText();

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

    public String if_on_product_page(){
        try{

            wait.until(ExpectedConditions.visibilityOfElementLocated(product_page_txt));
            return driver.findElement(product_page_txt).getText();

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

    public boolean products_available(){
        try{

            return !driver.findElements(products_paths).isEmpty();

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

    public void click_on_product(){
        try{

            wait.until(ExpectedConditions.visibilityOfElementLocated(product_path));
            driver.findElement(product_path).click();

        } catch (Exception e) {

            System.out.println("Error : " + e.getMessage());
            throw new RuntimeException(e);

        }
    }

    public void click_on_add_to_cart() {
        try {

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader1")));


            try {

                WebElement plusMinusControl = driver.findElement(plusMinusLocator);

                if (plusMinusControl.isDisplayed()) {

                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("lII1229")));
                    WebElement plusButton = wait.until(ExpectedConditions.elementToBeClickable(plusButtonLocator));
                    plusButton.click();

                }
            } catch (NoSuchElementException e) {

                WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(add_to_cart_path));
                addToCartButton.click();

            }

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("lII1229")));

        } catch (Exception e) {

            System.out.println("Error clicking product: " + e.getMessage());
            throw new RuntimeException(e);

        }
    }

    public String css_value_of_display(){

        try{

            wait.until(ExpectedConditions.visibilityOfElementLocated(cart_bbl_path));
            return driver.findElement(cart_bbl_path).getCssValue("display");

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

    public boolean is_on_cart() {
        try {

            WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(cart_icon_path));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartIcon);
            WebElement cartModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModalCart")));
            WebElement cartContent = cartModal.findElement(By.id("cardData"));
            return cartContent.isDisplayed() && !cartContent.getCssValue("display").equals("none");


        } catch (Exception e) {

            System.out.println("Cart display error: " + e.getMessage());
            return false;

        }
    }

    public boolean have_products(){
        try{

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(products_div_path));
            List<WebElement> products = driver.findElements(products_div_path);
            return !products.isEmpty();

        } catch (Exception e) {

            return false;

        }
    }

    public void click_clear_card(){
        try{

            wait.until(ExpectedConditions.visibilityOfElementLocated(clear_cart_btn_path));
            driver.findElement(clear_cart_btn_path).click();

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }


    public void cancell_cart(){
        try{

        driver.findElement(cancell_cart_path).click();

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

    public boolean is_cleared_cart(){
        try{

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader1")));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(clear_alert_txt_path));
            String actual = driver.findElement(clear_alert_txt_path).getText().trim();
            String expected = "Are you sure you want to clear cart?";

            if(actual.equals(expected)){
                driver.findElement(confirm_clear_btn_path).click();
                return driver.findElement(cart_bbl_path).isDisplayed();
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public boolean isCartEmpty() {

        return driver.findElements(By.className("cart-item")).isEmpty();

    }

}
