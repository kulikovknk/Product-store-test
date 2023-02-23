package productstore;

import dto.CustomerRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductStoreLoginPage extends ProductStoreMainPage {

    private final By fieldUserName = By.id("loginusername");
    private final By fieldPassword = By.id("loginpassword");
    private final By buttonLogin = By.xpath(".//button[text()='Log in']");

    public ProductStoreLoginPage(WebDriver driver) {
        super(driver);
    }

    public void inputCustomerCredentials(CustomerRequest customerRequest) {
        driver.findElement(fieldUserName).sendKeys(customerRequest.getUsername());
        driver.findElement(fieldPassword).sendKeys(customerRequest.getPassword());
    }

    public void clickLoginButtonOnLoginForm() {
        driver.findElement(buttonLogin).click();
    }

}
