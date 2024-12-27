package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Cart {

    By my_acc_path      =  By.id("spnAcc");
    By product_page_txt =  By.xpath("//*[@id=\"h3head\"]/span[1]");
    By products_paths   =  By.xpath("//*[@id=\"divProducts\"]//*[@id=\"card_\"]");
    By product_path     =  By.xpath("//*[@id=\"card_\"]");
    By add_to_cart_path =  By.xpath("//*[@id=\"crtAdd1242\"]/a");
    By cart_bbl_path    =  By.xpath("//*[@id=\"divCartBubble\"]");
    By cart_icon_path   =  By.xpath("//*[@id=\"btnCart\"]/p/a");
    By products_div_path=  By.xpath("//*[@id=\"myCartItems\"]");
    By cart_path = By.xpath("//*[@id=\"myModalCart\"]");
    By clear_cart_btn_path      = By.xpath("//*[@id=\"myCartClear\"]");
    By clear_alert_txt_path     = By.xpath("//*[@id=\"txtAlertText\"]");
    By confirm_clear_btn_path   = By.xpath("//*[@id=\"btnClearCartOk\"]");
    By empty_cart_txt_path      = By.xpath("");

    WebDriver driver;

    public Cart(WebDriver driver){
        this.driver = driver;
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(product_path));

            driver.findElement(product_path).click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void click_on_add_to_cart(){
        try{
//            We cant see the add button after cart is not empty-------> handle it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(add_to_cart_path));
            driver.findElement(add_to_cart_path).click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String css_value_of_display(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(cart_bbl_path));
            return driver.findElement(cart_bbl_path).getCssValue("display");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean is_on_cart(){
        try{

            driver.findElement(cart_icon_path).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(cart_path));
            String css_value = driver.findElement(cart_path).getCssValue("display");
            return css_value.equals("block");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean have_products(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(products_div_path));
            List<WebElement> products = driver.findElements(products_div_path);
            return !products.isEmpty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void click_clear_card(){
        try{            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(clear_cart_btn_path));
            driver.findElement(clear_cart_btn_path).click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean is_cleared_cart(){
        try{
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

    public void addItemToCart(String itemName) {
        driver.findElement(By.xpath("//div[text()='" + itemName + "']//button[text()='Add to Cart']")).click();
    }

    public boolean isCartEmpty() {
        return driver.findElements(By.className("cart-item")).isEmpty();
    }

}
