package UI;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AuthPageTest extends AbstractTest{
//    public AuthPageTest(WebDriver driver) {
//        super();
//    }


    public AuthPageTest() {
    }

    private void saveScreen(String name) throws IOException {
        File file = Utils.makeScreenshot(getDriver(),name + " " + System.currentTimeMillis() + ".png");
        saveScreenshot(Files.readAllBytes(file.toPath()));
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @Test
    @DisplayName("Valid username and password")
    @Description("Valid username and password")
    public void LoginValidTest() throws InterruptedException, IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(5000);
        saveScreen("Авторизация с валидными логин и пароль");
        assertEquals("https://test-stand.gb.ru/", getDriver().getCurrentUrl());
        assertEquals("Hello, SLE", authPage.getLogin());
    }


    @Test
    @DisplayName("Invalid username less then3 and valid password")
    @Description("Invalid username less then3 and valid password")
    public void loginLessThen3Test() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginLessThen3();
        saveScreen("Авторизация логин меньше 3х символов ,верный пароль");
        assertEquals("Invalid credentials.",authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }

    @Test
    @DisplayName("Valid username max20 and valid password")
    @Description("Valid username max20 and valid password")
    public void loginMax20Test() throws InterruptedException, IOException{
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginMax20();
        Thread.sleep(1000);
        saveScreen("Авторизация логин 20 символов и верный пароль");
        assertEquals("https://test-stand.gb.ru/", getDriver().getCurrentUrl());
        assertEquals("Hello, maxxxsymbolformytest", authPage.getLogin());
    }
    @Test
    @DisplayName("Unregistered user")
    @Description("Unregistered user")
    public void loginUnregisteredTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginUnregistered();
        saveScreen("Авторизация незарегистрированного пользователя");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }

    @Test
    @DisplayName("Invalid username russian letters and valid password")
    @Description("Invalid username russian letters and valid password")
    public void loginRusTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginRus();
        saveScreen("Авторизация русские буквы и валидный пароль");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }

    @Test
    @DisplayName("Invalid username (special characters) and valid password")
    @Description("Invalid username (special characters) and valid password")
    public void loginSpecSymbTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginSpecSymb();
        saveScreen("Авторизация логин значения специальные символы, валидный пароль");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }



    @Test
    @DisplayName("Invalid username over 20 symb and valid password")
    @Description("Invalid username over 20 symb and valid password")
    public void loginOver20Test() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginOver20();
        saveScreen("Авторизация с логин более 20 символов и валидный пароль");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }

    @Test
    @DisplayName("Valid username and incorrect password")
    @Description("Valid username and incorrect password")
    public void loginInvalidPasswordTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginInvalidPassword();
        saveScreen("Авторизация с валидным логин и неправильный пароль");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }


    @Test
    @DisplayName("Empty values username and password")
    @Description("Empty values username and password")
    public void loginEmptyLoginAndPasswordTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginEmptyLoginAndPassword();
        saveScreen("Авторизация с пустыми значениями логин и пароль");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }


}
