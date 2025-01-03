package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Login {

    By username_path    =  By.id("txtUser");
    By password_path    =  By.xpath("//*[@id=\"txtPwd\"]");
    By login_btn_path   =  By.id("btnSign");
    By my_acc_path      =  By.id("spnAcc");
    By cancell_cart_path=   By.xpath("//*[@id=\"btnOk\"]");

    WebDriver driver;
    WebDriverWait wait;


    public Login(WebDriver driver){
        try{

            this.driver =  driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        } catch (Exception e) {

            throw new RuntimeException(e);

        }


    }

    public void enter_email_or_phone(String text){
       try{

           wait.until(ExpectedConditions.visibilityOfElementLocated(username_path));
           driver.findElement(username_path).sendKeys(text);

       } catch (Exception e) {

           throw new RuntimeException(e);

       }


    }

    public void enter_password(String password){

        try{

            wait.until(ExpectedConditions.visibilityOfElementLocated(password_path));
            driver.findElement(password_path).sendKeys(password);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

    public void click_login(){

        try{

            wait.until(ExpectedConditions.visibilityOfElementLocated(login_btn_path));
            driver.findElement(login_btn_path).click();

        } catch (Exception e) {

            throw new RuntimeException(e);

        }



    }

    public String is_acc_section_displayed(){

        try{

            wait.until(ExpectedConditions.visibilityOfElementLocated(my_acc_path));
            return driver.findElement(my_acc_path).getText().trim();

        } catch (Exception e) {

            driver.findElement(cancell_cart_path).click();
            return "Failed";

        }

    }

}
