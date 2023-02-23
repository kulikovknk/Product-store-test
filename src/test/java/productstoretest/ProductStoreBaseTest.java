package productstoretest;

import config.WebDriverFactory;
import dto.CustomerRequest;
import generator.CustomerRequestGenerator;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;
import productstore.ProductStoreLoginPage;
import productstore.ProductStoreMainPage;

public class ProductStoreBaseTest {

    protected static WebDriver driver;

    ProductStoreLoginPage objProductStoreLoginPage;

    @BeforeClass
    public static void startUp() {
        driver = WebDriverFactory.getWebDriver();
    }

    @After
    public void clearData(){

        logOutCustomer();

        driver.manage().deleteAllCookies();
        ((WebStorage) driver).getSessionStorage().clear();
        ((WebStorage) driver).getLocalStorage().clear();
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    public void logInCustomer() {

        CustomerRequest customerRequest = new CustomerRequestGenerator().getCustomerRequest();

        objProductStoreLoginPage = new ProductStoreLoginPage(driver);

        objProductStoreLoginPage.openPage();
        objProductStoreLoginPage.clickLoginButton();
        objProductStoreLoginPage.inputCustomerCredentials(customerRequest);
        objProductStoreLoginPage.clickLoginButtonOnLoginForm();

        Assert.assertTrue(objProductStoreLoginPage.nameOfUserIsVisible());
    }

    public void logOutCustomer() {

        ProductStoreMainPage objProductStoreMainPage = new ProductStoreMainPage(driver);

        objProductStoreMainPage.openPage();
        objProductStoreMainPage.clickLogoutButton();
    }
}
