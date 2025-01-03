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

    WebDriver driver;
    WebDriverWait wait;


    public Login(WebDriver driver){
        this.driver =  driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enter_email_or_phone(String text){

        driver.findElement(username_path).sendKeys(text);
    }

    public void enter_password(String password){
        driver.findElement(password_path).sendKeys(password);
    }

    public void click_login(){

        driver.findElement(login_btn_path).click();

    }

    public String is_acc_section_displayed(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(my_acc_path));
        return driver.findElement(my_acc_path).getText().trim();
    }

}
