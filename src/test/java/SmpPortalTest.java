import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.UUID;

public class SmpPortalTest {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1200x800";
        Configuration.timeout = 10000;
        Configuration.baseUrl = "http://5.181.254.246:8080";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");

        Configuration.browserCapabilities = options;
    }

    @BeforeEach
    public void login() {
        SignUpPage.open();
        SignUpPage.fillUsername("user29");
        SignUpPage.fillPassword("GpCtkHRJ-123");
        SignUpPage.submit();
    }

    @AfterEach
    public void tearDown() {
        HomePage.open();
        HomePage.logout();
    }

    @Test
    public void testAddToFav() {
        String title = UUID.randomUUID().toString();

        HomePage.crateNewFav(title);
        HomePage.openFavPanel();
        HomePage.checkFavCardIsPresent(title);
    }

    @Test
    public void testAddToFavAndDelete() throws InterruptedException {
        String title = UUID.randomUUID().toString();
        HomePage.crateNewFav(title);
        HomePage.openFavPanel();

        HomePage.checkFavCardIsPresent(title);

        HomePage.mouseOverFavCard(title);
        HomePage.clickOnDeleteButton(title);
        HomePage.discardDelete();
        HomePage.checkFavCardIsPresent(title);

        HomePage.mouseOverFavCard(title);
        HomePage.clickOnDeleteButton(title);
        HomePage.submitDelete();

        // Без указания потоку уснуть selenium слишком быстро проводит проверку
        Thread.sleep(3000);

        HomePage.checkFavCardIsNotPresent(title);
    }
}
