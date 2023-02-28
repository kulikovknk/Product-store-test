package productstoretest;

import dto.CustomerRequest;
import generator.CustomerRequestGenerator;
import io.qameta.allure.Step;
import org.junit.*;
import productstore.ProductStoreCartPage;
import productstore.ProductStoreLoginPage;
import productstore.ProductStoreMainPage;
import productstore.ProductStoreSignUpPage;

import static com.codeborne.selenide.Selenide.open;
import static config.Config.PRODUCT_STORE_CART_PAGE;
import static config.Config.PRODUCT_STORE_MAIN_PAGE;

public class ProductStoreBaseTest {

    @After
    // при завершении теста выполним выход из учетной записи
    public void clearData(){
        logOutCustomer();
    }

    @Step("Войти под существующей учетной записью")
    public void logInCustomer() {
        getPageObjectLoginPage()
                .loginCustomer(new CustomerRequestGenerator().getCustomerRequest(false));
    }

    @Step("Войти под существующей учетной записью")
    public void logInCustomer(CustomerRequest customerRequest) {
        getPageObjectLoginPage()
                .loginCustomer(customerRequest);
    }

    @Step("Выйти из учетной записи")
    public void logOutCustomer() {
        getPageObjectMainPage().logoutCustomer();
    }

    public ProductStoreMainPage getPageObjectMainPage() {
        return open(PRODUCT_STORE_MAIN_PAGE, ProductStoreMainPage.class);
    }

    public ProductStoreSignUpPage getPageObjectSignUpPage() {
        return open(PRODUCT_STORE_MAIN_PAGE, ProductStoreSignUpPage.class);
    }

    public ProductStoreLoginPage getPageObjectLoginPage() {
        return open(PRODUCT_STORE_MAIN_PAGE, ProductStoreLoginPage.class);
    }

    public ProductStoreCartPage getPageObjectCartPage() {
        return open(PRODUCT_STORE_CART_PAGE, ProductStoreCartPage.class);
    }

}
