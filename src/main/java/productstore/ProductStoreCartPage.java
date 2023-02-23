package productstore;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ProductStoreCartPage extends ProductStoreBasePage {

    // локаторы окна списка товаров
    private final By productCard = By.className("success");
    private final By buttonPlaceOrder = By.xpath(".//button[text() = 'Place Order']");

    // локаторы окна "Place order"
//    private final By modalOrder = By.className("modal-content");
    //private final By modalOrder = By.xpath(".//div[@class = 'modal-content']");
    private final By modalOrder = By.xpath(".//*[@id='orderModalLabel' and text() = 'Place order']");
    private final By modalOrderName = By.className("modal-content").id("name");
    private final By modalOrderCountry = By.className("modal-content").id("country");
    private final By modalOrderCity = By.className("modal-content").id("city");
    private final By modalOrderCreditCard = By.className("modal-content").id("card");
    private final By modalOrderMonth = By.className("modal-content").id("month");
    private final By modalOrderYear = By.className("modal-content").id("year");
    private final By modalOrderButtonPurchase = By.className("modal-content").xpath(".//button[text() = 'Purchase']");

    // локаторы окна подтверждения заказа
    private final By alertTitle = By.cssSelector("sweet-alert").xpath(".//*[text() = 'Thank you for your purchase!']");
    private final By alertButtonOk = By.cssSelector("sweet-alert").xpath(".//button[contains(@class, 'confirm') and text() = 'OK']");




    public ProductStoreCartPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(PRODUCT_STORE_CART_PAGE);

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(productCard, 0));
    }

    public boolean deleteProductFromCart(String productTitle) {

        // первоначальное количество строчек продуктов в корзине
        int initialNumberOfProducts = driver.findElements(productCard).size();

        // найдем поле с заголовком товара, от него получим всю секцию строки товара как его предка
        // и на этой секции найдем кнопку Delete
        String buttonDeleteXpath = String.format(".//tbody[@id='tbodyid']" +
                "//td[text()='%s']/ancestor::tr[@class='success']" +
                "//*[text()='Delete']", productTitle);

        driver.findElement(By.xpath(buttonDeleteXpath)).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.numberOfElementsToBeLessThan(productCard, initialNumberOfProducts));

        // текущее количество строчек продуктов в корзине
        int currentNumberOfProducts = driver.findElements(productCard).size();

        // товар удален из корзины если текущее количество строчек товаров меньше исходного
        return currentNumberOfProducts == initialNumberOfProducts - 1;
    }

    public void clickButtonPlaceOrder() {
        driver.findElement(buttonPlaceOrder).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(modalOrder));
    }

    public void inputRandomOrderDetails() {

        Faker faker = new Faker();

        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");

        driver.findElement(modalOrderName).sendKeys(faker.name().username());
        driver.findElement(modalOrderCountry).sendKeys(faker.address().country());
        driver.findElement(modalOrderCity).sendKeys(faker.address().cityName());
        driver.findElement(modalOrderCreditCard).sendKeys(faker.business().creditCardNumber());
        driver.findElement(modalOrderMonth).sendKeys(month.format(faker.date().future(1, TimeUnit.DAYS)));
        driver.findElement(modalOrderYear).sendKeys(year.format(faker.date().future(1, TimeUnit.DAYS)));
    }

    public boolean clickButtonPurchase() {
        driver.findElement(modalOrderButtonPurchase).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(alertTitle));

        return driver.findElement(alertTitle).isDisplayed();
    }

    public void closePurchaseConfirmationAlert() {
        driver.findElement(alertButtonOk).click();
    }
}
