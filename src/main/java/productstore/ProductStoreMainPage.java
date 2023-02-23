package productstore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ProductStoreMainPage extends ProductStoreBasePage {

    private final By buttonSignup = By.id("signin2");
    private final By buttonLogin = By.id("login2");
    private final By titleNameOfUser = By.id("nameofuser");
    private final By buttonLogout = By.id("logout2");
    private final By buttonLogo = By.id("nava");
    private final By buttonCart = By.id("cartur");
    private final By itemCard = By.className("hrefch");

    public ProductStoreMainPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(PRODUCT_STORE_MAIN_PAGE);

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(itemCard, 0));
    }

    public void clickSignUpButton() {
        driver.findElement(buttonSignup).click();
    }

    public void clickLoginButton() {
        driver.findElement(buttonLogin).click();
    }

    public void clickLogoutButton() {
        driver.findElement(buttonLogout).click();
    }

    public void clickLogoButton() {
        driver.findElement(buttonLogo).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(itemCard, 0));
    }


    public boolean nameOfUserIsVisible() {

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(titleNameOfUser));

        return driver.findElement(titleNameOfUser).isDisplayed();
    }
    public String openRandomProductCard() {

        String productTitle = null;
        Random random = new Random();

        // получим список всех карточек продуктов на странице
        List<WebElement> itemsList = driver.findElements(itemCard);

        if (itemsList.size() > 0) {
            // найдем произвольную карточку товара и откроем ее страницу
            WebElement product = itemsList.get(random.nextInt(itemsList.size()));
            productTitle = product.getText();
            product.click();
        }
        return productTitle;
    }

    public void clickButtonCart() {
        driver.findElement(buttonCart).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains("cart"));
    }
}
