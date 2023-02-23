package productstoretest;

import dto.CustomerRequest;
import generator.CustomerRequestGenerator;
import org.junit.Assert;
import org.junit.Test;
import productstore.ProductStoreSignUpPage;

public class ProductStoreSignUpTest extends ProductStoreBaseTest {

    @Test
    public void CheckCustomerSingUpPositiveTest() {

        CustomerRequest customerRequest = new CustomerRequestGenerator().getCustomerRequest(true);

        ProductStoreSignUpPage objProductStoreSingUpPage = new ProductStoreSignUpPage(driver);

        objProductStoreSingUpPage.openPage();
        objProductStoreSingUpPage.clickSignUpButton();
        objProductStoreSingUpPage.inputCustomerCredentials(customerRequest);
        objProductStoreSingUpPage.clickSignUpButtonOnSignUpForm();

        Assert.assertTrue(objProductStoreSingUpPage.nameOfUserIsVisible());
    }


}
