package productstoretest;

import dto.CustomerRequest;
import generator.CustomerRequestGenerator;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import productstore.ProductStoreSignUpPage;


public class ProductStoreSignUpTest extends ProductStoreBaseTest {

    @Step("Зарегистрировать новую учетную запись")
    private void signUpNewCustomer(CustomerRequest customerRequest) {

        ProductStoreSignUpPage signUpPage = getPageObjectSignUpPage();

        signUpPage.clickSignUpButton();
        signUpPage.inputCustomerCredentials(customerRequest);
        signUpPage.clickSignUpButtonOnSignUpForm();

        Assert.assertTrue(signUpPage.getTextAndAcceptAlert().equals("Sign up successful."));
    }

    @Test
    @DisplayName("Регистрация пользователя")
    // проверить успешную регистрацию
    public void checkCustomerSingUpPositiveTest() {

        CustomerRequest customerRequest = new CustomerRequestGenerator().getCustomerRequest(true);

        signUpNewCustomer(customerRequest);

        // для проверки войдем под созданной учетной записью
        logInCustomer(customerRequest);
    }

}
