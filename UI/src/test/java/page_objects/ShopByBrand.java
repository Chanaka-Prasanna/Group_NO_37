package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShopByBrand {
    private WebDriver driver;

    // Updated Locator
    private By brandItemsLocator = By.cssSelector("div.need-box.need-box2.oils.popCatBo.gridCircle.dsGridBox.grdImg");
    private By brandLinkLocator = By.cssSelector("a.ng-scope");

    // Constructor
    public ShopByBrand(WebDriver driver) {
        this.driver = driver;
    }

    // Method to get all brand items
    public List<WebElement> getAllBrandItems() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(brandItemsLocator));
    }

    // Method to get the link from a specific brand item
    public WebElement getBrandLink(WebElement brandItem) {
        return brandItem.findElement(brandLinkLocator);
    }

    // Navigate back to the previous page
    public void navigateBack() {
        driver.navigate().back();
    }

    // Get the current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
