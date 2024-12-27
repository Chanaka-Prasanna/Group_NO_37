package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Cart {

    By my_acc_path      =  By.id("spnAcc");
    By product_page_txt =  By.xpath("//*[@id=\"h3head\"]/span[1]");
    By products_paths   =  By.xpath("//*[@id=\"divProducts\"]//*[@id=\"card_\"]");
    By product_path     =  By.xpath("//*[@id=\"card_\"]");
    By add_to_cart_path =  By.xpath("//*[@id=\"crtAdd1242\"]/a");
    By cart_bbl_path    = By.xpath("//*[@id=\"divCartBubble\"]");
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

}
