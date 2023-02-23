package productstore;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductStoreProductPage extends ProductStoreBasePage {

    private final By addToCart = By.xpath(".//*[text()='Add to cart']");

    public ProductStoreProductPage(WebDriver driver) {
        super(driver);
    }


    public void clickOrderButton() {

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(addToCart));

        driver.findElement(addToCart).click();
    }

    public String acceptAlert() {

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();

        alert.accept();

        return alertText;
    }
}
