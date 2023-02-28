package productstore;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;


public class ProductStoreProductPage {

    private final By buttonAddToCart = By.xpath(".//*[text()='Add to cart']");

    public void clickOrderButton() {
        $(buttonAddToCart).click();
    }

    public String getAlertTextAndAccept() {

        Alert alert = switchTo().alert();

        String alertText = alert.getText();

        alert.accept();

        return alertText;
    }
}
