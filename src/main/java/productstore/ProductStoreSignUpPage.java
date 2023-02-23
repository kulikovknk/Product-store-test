package productstore;

import dto.CustomerRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductStoreSignUpPage extends ProductStoreMainPage {

    private final By fieldUserName = By.id("sign-username");
    private final By fieldPassword = By.id("sign-password");
    private final By buttonSignUp = By.xpath(".//button[text()='Sign up']");

    public ProductStoreSignUpPage(WebDriver driver) {
        super(driver);
    }

    public void inputCustomerCredentials(CustomerRequest customerRequest) {
        driver.findElement(fieldUserName).sendKeys(customerRequest.getUsername());
        driver.findElement(fieldPassword).sendKeys(customerRequest.getPassword());
    }

    public void clickSignUpButtonOnSignUpForm() {
        driver.findElement(buttonSignUp).click();
    }

}
