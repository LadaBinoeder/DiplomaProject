package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FooterMenuTest extends BaseTest {

    @Test
    public void testSocialPanelIconsNavigateToCorrespondingWebSites() {
        final List<String> links = new ArrayList<>();
        links.add("https://www.facebook.com/groups/270748973021342/?mibextid=6NoCDW");
        links.add("https://twitter.com/OpenWeatherMap");
        links.add("https://www.linkedin.com/uas/login?session_redirect=%2Fcompany%2F9816754");
        links.add("https://openweathermap.medium.com/");
        links.add("https://t.me/openweathermap");
        links.add("https://github.com/search?q=openweathermap&ref=cmdform]");

        final List<String> expectedDomains = new ArrayList<>();
        expectedDomains.add("www.facebook.com");
        expectedDomains.add("twitter.com");
        expectedDomains.add("www.linkedin.com");
        expectedDomains.add("openweathermap.medium.com");
        expectedDomains.add("t.me");
        expectedDomains.add("github.com");

        Assert.assertEquals(links.size(), expectedDomains.size());

        for (int i = 0; i < links.size(); i++) {
            String expectedDomain = expectedDomains.get(i);

            URL url = null;
            try {
                url = new URL(links.get(i));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            Assert.assertNotNull(url);

            String actualDomain = url.getHost();

            Assert.assertEquals(actualDomain, expectedDomain);
        }
    }

    @Test
    public void testStorePanelExistsAndHasIcons() {
        final int expectedQuantity = 2;

        MainPage mainPage = openBaseURL()
                .scrollToFooterMenu();

        Assert.assertTrue(mainPage.isStorePanelDisplayed(), " StorePanel is not displayed ");
        Assert.assertEquals(mainPage.getStoresIconsCount(), expectedQuantity);
    }

    @Test
    public void testSocialPanelExistsAndHasIcons() {
        final int expectedIconsQuantity = 6;

        MainPage mainPage = openBaseURL()
                .scrollToFooterMenu();

        Assert.assertTrue(mainPage.isSocialPanelDisplayed(), " SocialPanel is not displayed ");
        Assert.assertEquals(mainPage.getSocialPanelSize(), expectedIconsQuantity);
    }
}
