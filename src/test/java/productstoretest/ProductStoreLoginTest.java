package productstoretest;

import dto.CustomerRequest;
import generator.CustomerRequestGenerator;
import org.junit.Assert;
import org.junit.Test;
import productstore.ProductStoreLoginPage;

public class ProductStoreLoginTest extends ProductStoreBaseTest {

    @Test
    public void CheckCustomerLoginPositiveTest() {

        CustomerRequest customerRequest = new CustomerRequestGenerator().getCustomerRequest();

        ProductStoreLoginPage objProductStoreLoginPage = new ProductStoreLoginPage(driver);

        objProductStoreLoginPage.openPage();
        objProductStoreLoginPage.clickLoginButton();
        objProductStoreLoginPage.inputCustomerCredentials(customerRequest);
        objProductStoreLoginPage.clickLoginButtonOnLoginForm();

        Assert.assertTrue(objProductStoreLoginPage.nameOfUserIsVisible());
    }
}
