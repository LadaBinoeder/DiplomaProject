package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class FooterMenuPage extends TopMenuPage {

    private static final String FOOTER_MENU_ID = "//div[@id='footer-website']";
    @FindBy(xpath = "//div[@class = 'inner-footer-container']//li")
    List<WebElement> footerMenus;
    @FindBy(xpath = FOOTER_MENU_ID)
    private WebElement footerMenu;
    @FindBy(xpath = FOOTER_MENU_ID + "//div[@class='my-5']//a")
    private List<WebElement> storePanelIconsFooterMenu;
    @FindBy(className = "social")
    private WebElement socialPanelFooterMenu;
    @FindBy(xpath = FOOTER_MENU_ID + "//div[@class='social']/a")
    private List<WebElement> socialPanelIconsFooterMenu;

    public FooterMenuPage(WebDriver driver) {
        super(driver);
    }


    protected WebElement getFooterMenu() {

        return footerMenu;
    }

    public boolean isStorePanelDisplayed() {

        return areElementsInListDisplayed(storePanelIconsFooterMenu);
    }

    public boolean isSocialPanelDisplayed() {

        return isElementDisplayed(socialPanelFooterMenu);
    }

    public int getStoresIconsCount() {

        return getListSize(storePanelIconsFooterMenu);
    }

    public int getSocialPanelSize() {

        return getListSize(socialPanelIconsFooterMenu);
    }

}
