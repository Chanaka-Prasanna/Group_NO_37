package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    By username_path    =  By.id("txtUser");
    By password_path    =  By.xpath("//*[@id=\"txtPwd\"]");
    By login_btn_path   =  By. id("btnSign");
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String text, String password) throws InterruptedException {

       try{
           driver.findElement(username_path).sendKeys(text);
           driver.findElement(password_path).sendKeys(password);
           driver.findElement(login_btn_path).click();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }


    }
}
