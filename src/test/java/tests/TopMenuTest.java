package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.TestData;
import pages.TopMenuPage;

public class TopMenuTest extends BaseTest {


    @Test
    public void testAllTheElementsInDesktopMenuAreDisplayed() {

        final int expectedResult = 12;

        int actualResult = openBaseURL()
                .getDesktopMenuSize();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLogoIsClickable() {

        final String expectedLink = "https://openweathermap.org/";
        final String expectedImage = "https://openweathermap.org/themes/openweathermap/assets/img/logo_white_cropped.png";

        TopMenuPage topMenuPage = openBaseURL();

        String actualURL = getDriver().getCurrentUrl();
        String actualLink = topMenuPage.clickLogo()
                                       .getLogoLink();
        String actualImage = topMenuPage.getLogoImage();

        Assert.assertEquals(actualURL, BASE_URL);
        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertEquals(actualImage, expectedImage);
    }

    @Test
    public void testPlaceholderIsClickable() {

        final String city = "Rome";
        final String expectedLink = "https://openweathermap.org/find";
        final String expectedText = "Weather in your city";
        final String expectedPage = expectedLink + "?q=" + city;

        TopMenuPage topMenuPage = openBaseURL();

        String actualResultLink = topMenuPage.getWeatherInYourCityPlaceholderLink();
        String actualResultText = topMenuPage.getWeatherInYourCityPlaceholderText();

        topMenuPage.clickWeatherInYourCityPlaceholder()
                   .enterCityNameInWeatherInYourCityPlaceholder(city);

        String actualResultPage = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResultLink, expectedLink);
        Assert.assertEquals(actualResultText, expectedText);
        Assert.assertEquals(actualResultPage, expectedPage);
    }

    @Test
    public void testAskAQuestionIsClickable() {

        final String expectedLink = "https://home.openweathermap.org/questions";

        TopMenuPage topMenuPage = openBaseURL();

        String actualLink = topMenuPage.clickSupportMenu()
                                       .getAskAQuestionSubmenuLink();

        boolean newPageIsOpen = topMenuPage.clickAskAQuestionSubmenu().switchToSecondWindowTopMenu().verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test(dataProvider = "TopMenuTestData", dataProviderClass = TestData.class)
    public void testEachTopMenuNavigatesToCorrespondingPage(
            int index, String menuName, String href, String expectedURL, String expectedTitle) {

        MainPage mainPage = openBaseURL();

        String oldURL = mainPage.getCurrentURL();
        String oldTitle = mainPage.getTitle();

        mainPage.clickTopMenu(index);

        String actualURL = getDriver().getCurrentUrl();
        String actualTitle = getDriver().getTitle();

        if (index != 0) {
            Assert.assertNotEquals(actualURL, oldURL);
            Assert.assertNotEquals(actualTitle, oldTitle);
        }

        if (index != 6) {
            Assert.assertEquals(actualURL, expectedURL);
        } else {
            Assert.assertTrue(actualURL.contains(expectedURL));
        }

        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
