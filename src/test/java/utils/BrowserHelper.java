package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;

public class BrowserHelper {

    public static WebDriver driver;
    static {

        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() { driver.quit(); }

        });

    }
    public WebDriver getDriver(){

        if (driver == null){


//            FirefoxOptions options = new FirefoxOptions();
//            options.addArguments("start-maximized");
//            options.addArguments("--remote-allow-origins=*");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            WebDriverManager.firefoxdriver().setup();

            return driver;

        }else {

            return driver;

        }

    }
    public  WebDriver getChromeDriver(){

        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C://Users//serba//Downloads//chromedriver-win64//chromedriver-win64//chromedriver.exe");

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver();
            driver.manage().window().maximize();
            WebDriverManager.chromedriver().setup();

            return driver;
        } else
            return driver;

        // in basepage initializez driverul
    }
    public static void closeDriverSession(){
        driver.quit();
    }
}
