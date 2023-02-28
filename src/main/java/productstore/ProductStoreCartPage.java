package productstore;


import com.codeborne.selenide.SelenideElement;
import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProductStoreCartPage {

    private By productCard = By.className("success");
    @FindBy(xpath = ".//button[text() = 'Place Order']")
    private SelenideElement buttonPlaceOrder;

    // локаторы окна "Place order"
    @FindBy(xpath = ".//*[@id='orderModalLabel' and text() = 'Place order']")
    private SelenideElement modalOrder;
    private final By modalOrderName = By.className("modal-content").id("name");
    private final By modalOrderCountry = By.className("modal-content").id("country");
    private final By modalOrderCity = By.className("modal-content").id("city");
    private final By modalOrderCreditCard = By.className("modal-content").id("card");
    private final By modalOrderMonth = By.className("modal-content").id("month");
    private final By modalOrderYear = By.className("modal-content").id("year");
    private final By modalOrderButtonPurchase = By.className("modal-content")
            .xpath(".//button[text() = 'Purchase']");

    // локаторы окна подтверждения заказа
    private final By alertTitle = By.className("sweet-alert")
            .xpath(".//*[text() = 'Thank you for your purchase!']");
    private final By alertButtonOk = By.className("sweet-alert")
            .xpath(".//button[contains(@class, 'confirm') and text() = 'OK']");

    public boolean deleteProductFromCart(String productTitle) {

        // первоначальное количество строчек продуктов в корзине
        int initialNumberOfProducts = $$(productCard).shouldHave(sizeGreaterThan(0)).size();

        // найдем поле с заголовком товара, от него получим всю секцию строки товара как его предка
        // и на этой секции найдем кнопку Delete
        String buttonDeleteXpath = String.format(".//tbody[@id='tbodyid']" +
                "//td[text()='%s']/ancestor::tr[@class='success']" +
                "//*[text()='Delete']", productTitle);

        $(By.xpath(buttonDeleteXpath)).shouldBe(enabled).click();

        // текущее количество строчек продуктов в корзине
        int currentNumberOfProducts = $$(productCard)
                .shouldHave(sizeLessThan(initialNumberOfProducts)).size();

        // товар удален из корзины если текущее количество строчек товаров меньше исходного
        return currentNumberOfProducts < initialNumberOfProducts;
    }

    public void clickButtonPlaceOrder() {
        buttonPlaceOrder.shouldBe(enabled).click();
    }

    public void inputRandomOrderDetails() {

        Faker faker = new Faker();

        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");

        $(modalOrderName).setValue(faker.name().username());
        $(modalOrderCountry).setValue(faker.address().country());
        $(modalOrderCity).setValue(faker.address().cityName());
        $(modalOrderCreditCard).setValue(faker.business().creditCardNumber());
        $(modalOrderMonth).setValue(month.format(faker.date().future(1, TimeUnit.DAYS)));
        $(modalOrderYear).setValue(year.format(faker.date().future(1, TimeUnit.DAYS)));
    }

    public void clickButtonPurchase() {
        $(modalOrderButtonPurchase).shouldBe(enabled).click();
    }

    public void closePurchaseConfirmationAlert() {
        $(alertButtonOk).shouldBe(enabled).click();
    }

    public boolean checkAlertTitleIsDisplayed() {
        return $(alertTitle).shouldBe(enabled).isDisplayed();
    }
}
