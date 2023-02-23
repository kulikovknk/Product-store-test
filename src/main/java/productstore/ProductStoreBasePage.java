package productstore;

import org.openqa.selenium.WebDriver;

public class ProductStoreBasePage {

    protected final WebDriver driver;

    public static final String PRODUCT_STORE_MAIN_PAGE = "https://www.demoblaze.com/index.html";
    public static final String PRODUCT_STORE_CART_PAGE = "https://www.demoblaze.com/cart.html";

    public ProductStoreBasePage(WebDriver driver) {
        this.driver = driver;
    }
}
