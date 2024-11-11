import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public static final String URL = "/sd/operator/";

    public static class Locators {
        public static final String ADD_FAV_BUTTON_ID = "gwt-debug-favorite";
        public static final String ADD_FAV_TITLE_ID = "gwt-debug-itemTitle-value";
        public static final String ADD_FAV_SUBMIT_ID = "gwt-debug-apply";
        public static final String ADD_FAV_PANEL_BUTTON_CSS = ".e044";
        public static final String FAV_CARD_XPATH = "//a[@id='gwt-debug-title']/div[text()='%s']";
        public static final String LOGOUT_BUTTON_ID = "gwt-debug-logout";
        public static final String DELETE_BUTTON_XPATH = "//a[@id='gwt-debug-title']/div[text()='%s']/../../div[@class='GFIRBY5J3']/span";
        public static final String DISCARD_DELETE_BUTTON_ID = "gwt-debug-no";
        public static final String SUBMIT_DELETE_BUTTON_ID = "gwt-debug-yes";
    }

    public static void open() {
        Selenide.open(URL);
    }

    public static void crateNewFav(String title) {
        $(By.id(Locators.ADD_FAV_BUTTON_ID)).click();
        $(By.id(Locators.ADD_FAV_TITLE_ID)).clear();
        $(By.id(Locators.ADD_FAV_TITLE_ID)).sendKeys(title);
        $(By.id(Locators.ADD_FAV_SUBMIT_ID)).click();
    }

    private static boolean checkFavPanelIsOpen() {
        return Boolean.TRUE.equals(Selenide.executeJavaScript("var element = document.getElementById('gwt-debug-navContent'); return element.style.display !=='none'; "));
    }

    public static void openFavPanel() {
        if (!checkFavPanelIsOpen()) {
            $(By.cssSelector(Locators.ADD_FAV_PANEL_BUTTON_CSS)).click();
        }
    }

    public static void checkFavCardIsPresent(String title) {
        $(By.xpath(String.format(Locators.FAV_CARD_XPATH, title))).shouldBe(Condition.exist.because("Карточка в избранном с названием '%s' не найдена.".formatted(title)), Duration.ofSeconds(5));
    }

    public static void checkFavCardIsNotPresent(String title) {
        $(By.xpath(String.format(Locators.FAV_CARD_XPATH, title))).shouldNot(Condition.exist.because("Карточка в избранном с названием '%s' не найдена.".formatted(title)), Duration.ofSeconds(5));
    }

    public static void logout() {
        $(By.id(Locators.LOGOUT_BUTTON_ID)).click();
    }

    public static void mouseOverFavCard(String title) {
        $(By.xpath(String.format(Locators.FAV_CARD_XPATH, title))).hover();
    }

    public static void clickOnDeleteButton(String title) {
        $(By.xpath(String.format((Locators.DELETE_BUTTON_XPATH), title))).click();
    }

    public static void discardDelete() {
        $(By.id(Locators.DISCARD_DELETE_BUTTON_ID)).click(ClickOptions.withTimeout(Duration.ofSeconds(5)));
    }

    public static void submitDelete() {
        $(By.id(Locators.SUBMIT_DELETE_BUTTON_ID)).click(ClickOptions.withTimeout(Duration.ofSeconds(5)));
    }
}
