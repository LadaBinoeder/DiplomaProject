package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {

    private WebDriver driver;
    private WebDriverWait wait5;
    private WebDriverWait wait10;
    private WebDriverWait wait20;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    protected WebDriver getDriver() {
        return  driver;

    }
    protected WebDriverWait getWait5() {
        if(wait5 == null) {
            wait5 = new WebDriverWait(driver, Duration.ofSeconds(5));
        }
        return wait5;

    }
    protected WebDriverWait getWait10() {
        if(wait10 == null) {
            wait10 = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return wait10;

    }
    protected WebDriverWait getWait20() {
        if(wait20 == null) {
            wait20 = new WebDriverWait(driver, Duration.ofSeconds(20));
        }
        return wait20;

    }

    protected String getText(WebElement webElement) {
        if(webElement.getText().isEmpty()) {
            getWait10().until(ExpectedConditions.visibilityOf(webElement));
        }

        return webElement.getText();
    }

    protected boolean elementIsDisplayed(WebElement webElement) {

        return webElement.isDisplayed();
    }

    protected int getListSize(List<WebElement> webElements) {

        return webElements.size();
    }

    protected List<String> getListTexts(List<WebElement> webElements) {

        List<String> listTexts = new ArrayList<>();

        for (int i = 0; i < webElements.size(); i++) {
            listTexts.add(webElements.get(i).getText());
        }
        return listTexts;
    }

    protected void clickElement(WebElement webElement) {
        getWait10().until(ExpectedConditions.visibilityOfAllElements(webElement));
        getWait10().until(ExpectedConditions.elementToBeClickable(webElement)).click();

    }

    protected String getAttribute(WebElement webElement, String attribute) {

        return webElement.getAttribute(attribute);
    }

    protected void enterValue(WebElement webElement, String value) {

        webElement.sendKeys(value);
        webElement.sendKeys(Keys.ENTER);
    }

    protected void putInValue(WebElement webElement, String value) {

        webElement.sendKeys(value);
    }

    protected void waitTillElementIsNotVisible(WebElement webElement)  {

        getWait10().until(ExpectedConditions.invisibilityOf(webElement));
    }

    protected boolean elementIsNotDisplayed(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return true;

        } catch (NoSuchElementException e) {
            return false;

        }
    }

    protected boolean verifyNeededUnitsDisplayed(WebElement webElement, String units) {
        String text = webElement.getText();
        if(text.contains(units)){
            return true;

        };
        return false;

    }

    protected int getTemperatureFigureFromText(WebElement webElement) {

        String currentTemp = webElement.getText();
        char[] arrayCurrentTemp = currentTemp.toCharArray();
        currentTemp = "";

        for(int i = 0; i < arrayCurrentTemp.length - 2; i++) {
            currentTemp = currentTemp + arrayCurrentTemp[i];
        }
        return Integer.valueOf(currentTemp);

    }

    protected void waitTillTextChanges(WebElement webElement, String text) {
        getWait5().until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(webElement, text)));

    }

    protected int convertCelsiusToFahrenheit(int temperatureInCelsius) {

        int temperatureInFahrenheit = (int)(temperatureInCelsius * 1.8 + 32);
        return temperatureInFahrenheit;

    }

    protected boolean celsiusToFahrenheitConvertingCorresponds(int temperatureInCelsius, int temperatureInFahrenheit) {

        if(temperatureInFahrenheit == convertCelsiusToFahrenheit(temperatureInCelsius)
                || temperatureInFahrenheit == convertCelsiusToFahrenheit(temperatureInCelsius) + 1)
        {
            return true;

        }
        return false;

    }

    protected boolean verifyArrayContainsNeededUnits(List<WebElement> webElements, String units) {

        for(int i = 0; i < webElements.size(); i++) {
            if(webElements.get(i).getText().contains(units)) {
                return true;

            }
        }
        return false;

    }

    protected void clickListElement(List<WebElement> webElements, int index) {

        webElements.get(index).click();
    }

    protected boolean verifyNewPageOpen() {
        boolean newPageIsOpened = true;
        if (driver.getCurrentUrl().equals(TestUtils.getBaseUrl())) {
            newPageIsOpened = false;

        }
        return newPageIsOpened;

    }

    protected void switchToSecondWindow() {
        String handle = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(handle);

    }

    protected void scrollByVisibleElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected boolean areElementsInListDisplayed(List<WebElement> list) {
        boolean result = false;

        for (WebElement element : list) {
            if (element.isDisplayed()) {
                result = true;
            } else {

                return false;
            }
        }

        return result;
    }

    protected boolean isElementDisplayed(WebElement element) {

        return element.isDisplayed();
    }

    protected List<String> getTexts(List<WebElement> list) {
        if (list.size() > 0) {
            getWait20().until(ExpectedConditions.visibilityOfAllElements(list));
            List<String> textList = new ArrayList<>();
            for (WebElement element : list) {
                if (element.isEnabled() && element.isDisplayed()) {
                    textList.add(element.getText());
                }
            }

            return textList;
        }

        return new ArrayList<>();
    }

    protected void clear(WebElement element) {

        element.clear();
    }

    protected void input(String text, WebElement element) {

        element.sendKeys(text);
    }

    protected void clickEnter(WebElement element) {
        getWait10().until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(Keys.ENTER);
    }

    protected void wait10ElementToBeVisible(WebElement element) {
        getWait10().until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement wait10ElementToBeClickable(WebElement element) {

        return getWait10().until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void wait20ElementToBeVisible(WebElement element) {
        getWait20().until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement wait20ElementToBeClickable(WebElement element) {

        return getWait20().until(ExpectedConditions.elementToBeClickable(element));
    }


    protected void click(WebElement element) {
        wait10ElementToBeVisible(element);
        wait10ElementToBeClickable(element).click();
    }

    protected void switchToAnotherWindow() {
        String originalWindow = getDriver().getWindowHandle();

        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.equals(windowHandle) && getDriver().getWindowHandles().size() == 2) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    protected void click20(WebElement element) {
        wait20ElementToBeVisible(element);
        wait20ElementToBeClickable(element).click();
    }
}
