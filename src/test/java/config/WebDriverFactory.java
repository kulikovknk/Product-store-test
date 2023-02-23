package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    public static WebDriver getWebDriver() {

        String driver = System.getProperty("browser", "yandex");
        String binaryYandexDriverFile = "C:\\Program Files\\WebDriver\\bin\\yandexdriver.exe";

        switch (driver) {
            case "chrome":

                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case "yandex":
                System.setProperty("webdriver.chrome.driver", binaryYandexDriverFile);
                return new ChromeDriver();

            default:
                throw new IllegalStateException("Incorrect browser name");
        }
    }
}
