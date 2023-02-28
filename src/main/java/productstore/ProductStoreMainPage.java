package productstore;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static config.Config.PRODUCT_STORE_CART_PAGE;

public class ProductStoreMainPage {

    // локаторы главной страницы
    @FindBy(id = "signin2")
    private SelenideElement buttonSignup;
    @FindBy(id = "login2")
    private SelenideElement buttonLogin;
    @FindBy(id = "nameofuser")
    private SelenideElement titleNameOfUser;
    @FindBy(id = "logout2")
    private SelenideElement buttonLogout;
    @FindBy(id = "nava")
    private SelenideElement buttonLogo;
    @FindBy(id = "cartur")
    private SelenideElement buttonCart;
    private final By productCard = By.className("hrefch");

    public void clickSignUpButton() {
        buttonSignup.shouldBe(enabled).click();
    }

    public void clickLoginButton() {
        buttonLogin.shouldBe(enabled).click();
    }

    public void clickLogoutButton() {
        buttonLogout.shouldBe(enabled).click();
    }

    public void checkIfNameOfUserIsVisible() {
        titleNameOfUser.shouldBe(visible);
    }

    public String openRandomProductCard() {

        String productTitle = null;
        Random random = new Random();

        // получим список всех карточек продуктов на странице
        ElementsCollection productsCollection = $$(productCard).shouldHave(sizeGreaterThan(0));

        if (productsCollection.size() > 0) {
            // найдем произвольную карточку товара и откроем ее страницу
            SelenideElement product = productsCollection.get(random.nextInt(productsCollection.size()));
            productTitle = product.getText();
            product.click();
        }

        return productTitle;
    }

    public void clickButtonCart() {
        buttonCart.shouldBe(enabled).click();

        // дождемся пока не исчезнет список продуктов, что будет означать переход на другую страницу
        $$(productCard).shouldHave(size(0));
    }

    public String getCurrentUrl() {
        return WebDriverRunner.url();
    }

    public void logoutCustomer() {
        clickLogoutButton();
    }

    public boolean checkIfCartPageIsOpen() {
        return getCurrentUrl().contains(PRODUCT_STORE_CART_PAGE);
    }
}
