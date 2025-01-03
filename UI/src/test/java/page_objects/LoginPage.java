package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    By username_path    =  By.id("txtUser");
    By password_path    =  By.xpath("//*[@id=\"txtPwd\"]");
    By login_btn_path   =  By. id("btnSign");
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void login(String text, String password) throws InterruptedException {

       try{
           wait.until(ExpectedConditions.visibilityOfElementLocated(username_path));
           driver.findElement(username_path).sendKeys(text);
           wait.until(ExpectedConditions.visibilityOfElementLocated(password_path));
           driver.findElement(password_path).sendKeys(password);
           wait.until(ExpectedConditions.visibilityOfElementLocated(login_btn_path));
           driver.findElement(login_btn_path).click();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }
}
