package com.example.selenium_java;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;


    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        mainPage = new MainPage(driver);
        driver.get(mainPage.homeUrl);
    }

    //reusable function with correct username and password
    public void correctLogin() throws java.lang.InterruptedException {
        mainPage.inputUsername.sendKeys("standard_user");
        mainPage.inputPassword.sendKeys("secret_sauce");
        mainPage.inputLoginButton.click();
        Thread.sleep(5000);
    }

    @Test
    public void loginTC1() throws InterruptedException {
        correctLogin();
        String expectedUrl = mainPage.loggedInUrl;
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void logoutTC1A() throws InterruptedException {
        correctLogin();
        mainPage.buttonReactBurgerMenu.click();
        Thread.sleep(5000);
        mainPage.linkLogoutSidebar.click();
        String expectedUrl = mainPage.homeUrl;
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void loginTC2() throws java.lang.InterruptedException {
        //try to log in with password only
        mainPage.inputPassword.sendKeys("secret_sauce");
        mainPage.inputLoginButton.click();
        Thread.sleep(5000);
        //check url
        String expectedUrl = mainPage.homeUrl;
        String actualUrl = driver.getCurrentUrl();
        //check if alert is shown
        List<WebElement> alert = driver.findElements(By.cssSelector("h3[data-test='error']"));
        //assert
        assertFalse(alert.isEmpty());
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void loginTC3() throws java.lang.InterruptedException {
        //try to log in with username only
        mainPage.inputUsername.sendKeys("standard_user");
        mainPage.inputLoginButton.click();
        Thread.sleep(5000);
        //check url
        String expectedUrl = mainPage.homeUrl;
        String actualUrl = driver.getCurrentUrl();
        //check if alert is shown
        List<WebElement> alert = driver.findElements(By.cssSelector("h3[data-test='error']"));
        //assert
        assertFalse(alert.isEmpty());
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void loginTC4() throws java.lang.InterruptedException {
        //try to log in with empty username & password fields
        mainPage.inputLoginButton.click();
        Thread.sleep(5000);
        //check url
        String expectedUrl = mainPage.homeUrl;
        String actualUrl = driver.getCurrentUrl();
        //check if alert is shown
        List<WebElement> alert = driver.findElements(By.cssSelector("h3[data-test='error']"));
        //assert
        assertFalse(alert.isEmpty());
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void loginTC5() throws java.lang.InterruptedException {
        //try to log in with correct username & incorrect password
        mainPage.inputUsername.sendKeys("standard_user");
        mainPage.inputPassword.sendKeys("bad_password");
        mainPage.inputLoginButton.click();
        Thread.sleep(5000);
        //check url
        String expectedUrl = mainPage.homeUrl;
        String actualUrl = driver.getCurrentUrl();
        //check if alert is shown
        List<WebElement> alert = driver.findElements(By.cssSelector("h3[data-test='error']"));
        //assert
        assertFalse(alert.isEmpty());
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void loginTC6() throws java.lang.InterruptedException {
        //try to log in with incorrect username & correct password
        mainPage.inputUsername.sendKeys("bad_user");
        mainPage.inputPassword.sendKeys("secret_sauce");
        mainPage.inputLoginButton.click();
        Thread.sleep(5000);
        //check url
        String expectedUrl = mainPage.homeUrl;
        String actualUrl = driver.getCurrentUrl();
        //check if alert is shown
        List<WebElement> alert = driver.findElements(By.cssSelector("h3[data-test='error']"));
        //assert
        assertFalse(alert.isEmpty());
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void loginTC7() throws java.lang.InterruptedException {
        //try to log in with incorrect username & correct password
        mainPage.inputUsername.sendKeys("bad_user");
        mainPage.inputPassword.sendKeys("bad_password");
        mainPage.inputLoginButton.click();
        Thread.sleep(5000);
        //check url
        String expectedUrl = mainPage.homeUrl;
        String actualUrl = driver.getCurrentUrl();
        //check if alert is shown
        List<WebElement> alert = driver.findElements(By.cssSelector("h3[data-test='error']"));
        //assert
        assertFalse(alert.isEmpty());
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void accessTC8() throws java.lang.InterruptedException {
        //try to access page shown after logging in
        driver.get(mainPage.loggedInUrl);
        Thread.sleep(5000);
        //check url
        String expectedUrl = mainPage.homeUrl;
        String actualUrl = driver.getCurrentUrl();
        //check if alert is shown
        List<WebElement> alert = driver.findElements(By.cssSelector("h3[data-test='error']"));
        //assert
        assertFalse(alert.isEmpty());
        assertEquals(expectedUrl, actualUrl);
    }
}
