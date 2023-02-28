package productstore;

import com.codeborne.selenide.SelenideElement;
import dto.CustomerRequest;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;

public class ProductStoreLoginPage extends ProductStoreMainPage {

    @FindBy(how = How.ID,using = "loginusername")
    private SelenideElement fieldUserName;
    @FindBy(how = How.ID, using = "loginpassword")
    private  SelenideElement fieldPassword;
    @FindBy(how = How.XPATH, using = ".//button[text()='Log in']")
    private SelenideElement buttonLogin;

    private void setUserName(String username) {
        fieldUserName.shouldBe(enabled).setValue(username);
    }

    private void setPassword(String password) {
        fieldPassword.shouldBe(enabled).setValue(password);
    }

    public void inputCustomerCredentials(CustomerRequest customerRequest) {
        setUserName(customerRequest.getUsername());
        setPassword(customerRequest.getPassword());
    }

    public void clickLoginButtonOnLoginForm() {
        buttonLogin.click();
    }

    public void loginCustomer(CustomerRequest customerRequest) {

        clickLoginButton();
        inputCustomerCredentials(customerRequest);
        clickLoginButtonOnLoginForm();
        checkIfNameOfUserIsVisible();
    }

}
