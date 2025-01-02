package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    private static WebDriver driver;


    public static WebDriver get_driver(){
        if(driver == null){
//            System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver.exe");
//            driver = new ChromeDriver();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-software-rasterizer");

            String os = System.getProperty("os.name").toLowerCase();
            String driverPath = "src/test/resources/driver/chromedriver";

            // Append .exe for Windows systems
            if (os.contains("win")) {
                driverPath += ".exe";
            }

            // Set the ChromeDriver system property dynamically
            System.setProperty("webdriver.chrome.driver", driverPath);

            // Initialize WebDriver
            driver = new ChromeDriver();
//            driver.get('www.google.com') -> mekaa page modesls waladi initialize karaganna
//            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void close_driver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
