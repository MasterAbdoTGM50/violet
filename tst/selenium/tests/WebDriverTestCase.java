package selenium.tests;

import selenium.Lib;
import selenium.UserAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class WebDriverTestCase {

    protected WebDriver driver;

    protected UserAction userAction;

    @BeforeMethod
    public void launchWebDriver() {

        System.setProperty("webdriver.chrome.driver", Lib.Paths.WEB_DRIVER);

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        userAction = new UserAction(driver);

    }

    @AfterMethod
    public void terminateWebDriver() { driver.close(); }

}
