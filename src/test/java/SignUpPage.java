import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SignUpPage {
    public static final String URL = "/sd";

    public static class Locators {
        public static final String USERNAME_ID = "username";
        public static final String PASSWORD_ID = "password";
        public static final String SUBMIT_BUTTON_ID = "submit-button";
    }

    public static void open() {
        Selenide.open(URL);
    }

    public static void fillUsername(String username) {
        $(By.id(Locators.USERNAME_ID)).sendKeys(username);
    }

    public static void fillPassword(String password) {
        $(By.id(Locators.PASSWORD_ID)).sendKeys(password);
    }

    public static void submit() {
        $(By.id(Locators.SUBMIT_BUTTON_ID)).click();
    }
}
