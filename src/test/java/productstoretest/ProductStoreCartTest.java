package productstoretest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import productstore.ProductStoreCartPage;
import productstore.ProductStoreProductPage;
import productstore.ProductStoreMainPage;

public class ProductStoreCartTest extends ProductStoreBaseTest {

    @Before
    public void setUp() throws Exception {
        logInCustomer();
    }

    private String openRandomProductCard() {

        ProductStoreMainPage objProductStoreMainPage = new ProductStoreMainPage(driver);

        objProductStoreMainPage.openPage();
        String productTitle = objProductStoreMainPage.openRandomProductCard();

        return productTitle;
    }

    private String addProductToCart() {

        ProductStoreProductPage objProductStoreProductPage = new ProductStoreProductPage(driver);

        objProductStoreProductPage.clickOrderButton();
        String alertText = objProductStoreProductPage.acceptAlert();

        return alertText;
    }

    private boolean deleteProductFromCart(String productTitle) {

        ProductStoreCartPage objProductStoreCartPage = new ProductStoreCartPage(driver);

        objProductStoreCartPage.openPage();
        boolean isProductDeleted = objProductStoreCartPage.deleteProductFromCart(productTitle);

        return isProductDeleted;
    }

    @Test
    // проверить переход на страницу корзины по кнопке "Cart" с главной страницы
    public void checkGoToCartSectionTest() {

        ProductStoreMainPage objProductStoreMainPage = new ProductStoreMainPage(driver);

        objProductStoreMainPage.openPage();
        objProductStoreMainPage.clickButtonCart();

        Assert.assertTrue("Переход на страницу Cart не выполнен", driver.getCurrentUrl().contains("cart"));
    }

    @Test
    //  проверить добавление товара в корзину по кнопке "Add to cart"
    public void addProductToCartTest() {

        openRandomProductCard();

        String alertText = addProductToCart();

        Assert.assertEquals("Добавление продукта в корзины не выполнено","Product added.", alertText);
    }

    @Test
    //  проверить удаление товара из корзины по кнопке "Delete"
    public void deleteProductFromCartTest() {

        String productTitle = openRandomProductCard();

        String alertText = addProductToCart();

        Assert.assertEquals("Product added.", alertText);

        boolean isProductDeleted = deleteProductFromCart(productTitle);

        Assert.assertTrue("Удаление товара из корзины не выполнено", isProductDeleted);
    }

    @Test
    // проверить заказ товара по кнопке "Place Order"
    public void placeOrderTest() {

        openRandomProductCard();

        String alertText = addProductToCart();

        Assert.assertEquals("Product added.", alertText);

        ProductStoreCartPage objProductStoreCartPage = new ProductStoreCartPage(driver);

        objProductStoreCartPage.openPage();
        objProductStoreCartPage.clickButtonPlaceOrder();
        objProductStoreCartPage.inputRandomOrderDetails();
        boolean isPurchaseConfirmed = objProductStoreCartPage.clickButtonPurchase();

        Assert.assertTrue("Заказ не оформлен", isPurchaseConfirmed);

        objProductStoreCartPage.closePurchaseConfirmationAlert();
    }
}
