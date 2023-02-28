package productstoretest;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class ProductStoreLoginTest extends ProductStoreBaseTest {

    @Test
    @DisplayName("Вход по кнопке Log in под существующей учетной записью")
    // проверить вход по кнопке "Log in" на главной странице под существующей учетной записью
    public void checkCustomerLoginPositiveTest() {
        logInCustomer();
    }
}
