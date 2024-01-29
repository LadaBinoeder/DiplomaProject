package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class BaseTest {

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeMethod
    protected void beforeMethod() {
        driver = BaseUtils.createDriver();

    }
    @AfterMethod
    protected void afterMethod() {
        driver.quit();
        webDriverWait = null;

    }
    protected WebDriver getDriver() {
        return driver;

    }

    protected WebDriverWait getWait5() {
        if(webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }
        return webDriverWait;

    }
    protected WebDriverWait getWait10() {
        if(webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return webDriverWait;

    }

    protected WebDriverWait getWait20() {
        if(webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        }
        return webDriverWait;

    }
}
