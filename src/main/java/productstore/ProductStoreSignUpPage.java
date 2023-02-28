package productstore;

import com.codeborne.selenide.SelenideElement;
import dto.CustomerRequest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.switchTo;

public class ProductStoreSignUpPage extends ProductStoreMainPage {

    @FindBy(id = "sign-username")
    private SelenideElement fieldUserName;
    @FindBy(id = "sign-password")
    private SelenideElement fieldPassword;
    @FindBy(xpath = ".//button[text()='Sign up']")
    private SelenideElement buttonSignUpOnSignUpForm;


     private void setUserName(String userName) {
        fieldUserName.shouldBe(enabled).setValue(userName);
    }

    private void setPassword(String password) {
        fieldPassword.shouldBe(enabled).setValue(password);
    }

    public void inputCustomerCredentials(CustomerRequest customerRequest) {
        setUserName(customerRequest.getUsername());
        setPassword(customerRequest.getPassword());
    }

    public void clickSignUpButtonOnSignUpForm() {
        buttonSignUpOnSignUpForm.shouldBe(enabled).click();
    }

    public String getTextAndAcceptAlert() {

        Alert alert = switchTo().alert();
        String text = alert.getText();

        alert.accept();

        return text;
    }

}
