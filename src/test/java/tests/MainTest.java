package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.TestData;

import java.io.IOException;

public class MainTest extends BaseTest {

    @Test
    public void testURLAndTitle() throws IOException {

        final String expectedURL = "https://openweathermap.org/";
        final String expectedTitle = "Сurrent weather and forecast - OpenWeatherMap";

        MainPage mainPage = openBaseURL();

        String actualURL = getDriver().getCurrentUrl();
        String actualTitle = getDriver().getTitle();

        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertTrue(mainPage.verifyPageIsNotEmpty());
        Assert.assertTrue(mainPage.verifyErrorsAtPage());
    }

    @Test
    public void testVerifyHeader() {

        final String expectedHeader = "OpenWeather";

        MainPage mainPage = openBaseURL();
        String actualHeader = mainPage.getHeaderText();

        Assert.assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testUnitsSwitcher() {

        final String expectedCelsiusSelected = "left: 2pt;";
        final String expectedFahrenheitSelected = "slideRight";

        MainPage mainPage = openBaseURL();

        String actualCelsiusSelected = mainPage.getSelectedUnitAttribute("style");

        String actualFahrenheitSelected = mainPage.clickFahrenheitUnit()
                                                  .getSelectedUnitAttribute("className");

        Assert.assertEquals(actualCelsiusSelected, expectedCelsiusSelected);
        Assert.assertEquals(actualFahrenheitSelected, expectedFahrenheitSelected);
    }

    @Test
    public void testVerifySearchButtonIsClickable() {

        final String cityName = "Madrid";

        MainPage mainPage = openBaseURL();

        mainPage.enterValueSearchBlock(cityName)
                .waitTillSearchDropdownMenuIsVisible();

        Assert.assertFalse(mainPage.verifySearchDropdownListIsEmpty());
    }

    @Test
    public void testVerifySearchButtonEmptySearch() {

        MainPage mainPage = openBaseURL();

        mainPage.clickSearchButton();

        Assert.assertFalse(mainPage.searchDropdownIsNotDisplayed());
    }


    @Test(dataProvider = "ApiIconsMainPage", dataProviderClass = TestData.class)
    public void testAPIIconsNavigateToCorrespondingPages(
            int index, String iconName, String iconDescription, String href, String expectedURL, String expectedTitle) {

        MainPage mainPage = openBaseURL();

        final String oldURL = mainPage.getCurrentURL();
        final String oldTitle = mainPage.getTitle();

        mainPage
                .scrollByOrangeBackground()
                .clickApiIcon(index);

        String actualURL = getDriver().getCurrentUrl();
        String actualTitle = getDriver().getTitle();

        Assert.assertNotEquals(oldURL, actualURL);
        Assert.assertNotEquals(oldTitle, actualTitle);
        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
