package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {


    By username_path    =  By.id("txtUser");
    By password_path    =  By.xpath("//*[@id=\"txtPwd\"]");
    By login_btn_path   =  By. id("btnSign");
    By my_acc_path = By.xpath("//*[@id=\"spnAcc\"]");
    WebDriver driver;

    public Login(WebDriver driver){
        this.driver =  driver;
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

    public void is_acc_section_displayed(){
        driver.findElement(my_acc_path).isDisplayed();
    }

}
