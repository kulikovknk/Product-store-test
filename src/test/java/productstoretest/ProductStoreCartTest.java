package productstoretest;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import productstore.ProductStoreCartPage;
import productstore.ProductStoreProductPage;
import productstore.ProductStoreMainPage;

public class ProductStoreCartTest extends ProductStoreBaseTest {

    @Before
    // для работы с корзиной выполним вход под существующей учетной записью
    public void setUp() throws Exception {
        logInCustomer();
    }

    @Step("Выбрать в случайном порядке и открыть карточку произвольного продукта")
    private String openRandomProductCard() {

        ProductStoreMainPage mainPage = getPageObjectMainPage();

        String productTitle = mainPage.openRandomProductCard();

        return productTitle;
    }

    @Step("Добавить карточку продукта в корзину путем нажатия на кнопку \"Add to cart\"")
    private String addProductToCart() {

        ProductStoreProductPage productPage = new ProductStoreProductPage();

        productPage.clickOrderButton();
        String alertText = productPage.getAlertTextAndAccept();

        return alertText;
    }

    @Step("Удалить карточку продукта из корзины путем нажатия на кнопку \"Delete\"")
    private boolean deleteProductFromCart(String productTitle) {

        ProductStoreCartPage cartPage = getPageObjectCartPage();

        boolean isProductDeleted = cartPage.deleteProductFromCart(productTitle);

        return isProductDeleted;
    }

    @Test
    @DisplayName("Переход на страницу корзины по кнопке \"Cart\" с главной страницы")
    // проверить переход на страницу корзины по кнопке "Cart" с главной страницы
    public void checkGoToCartSectionTest() {

        ProductStoreMainPage mainPage = getPageObjectMainPage();

        mainPage.clickButtonCart();

        Assert.assertTrue("Переход на страницу Cart не выполнен", mainPage.checkIfCartPageIsOpen());
    }

    @Test
    @DisplayName("Добавление товара в корзину по кнопке \"Add to cart\"")
    //  проверить добавление товара в корзину по кнопке "Add to cart"
    public void addProductToCartTest() {

        openRandomProductCard();

        String alertText = addProductToCart();

        Assert.assertEquals("Добавление продукта в корзины не выполнено","Product added.", alertText);
    }

    @Test
    @DisplayName("Удаление товара из корзины по кнопке \"Delete\"")
    //  проверить удаление товара из корзины по кнопке "Delete"
    public void deleteProductFromCartTest() {

        String productTitle = openRandomProductCard();

        String alertText = addProductToCart();

        Assert.assertEquals("Product added.", alertText);

        boolean isProductDeleted = deleteProductFromCart(productTitle);

        Assert.assertTrue("Удаление товара из корзины не выполнено", isProductDeleted);
    }

    @Test
    @DisplayName("Заказ товара по кнопке \"Place Order\"")
    // проверить заказ товара по кнопке "Place Order"
    public void placeOrderTest() {

        openRandomProductCard();

        String alertText = addProductToCart();

        Assert.assertEquals("Product added.", alertText);

        ProductStoreCartPage cartPage = getPageObjectCartPage();

        cartPage.clickButtonPlaceOrder();
        cartPage.inputRandomOrderDetails();
        cartPage.clickButtonPurchase();

        Assert.assertTrue("Заказ не оформлен", cartPage.checkAlertTitleIsDisplayed());

        cartPage.closePurchaseConfirmationAlert();
    }
}
