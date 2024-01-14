package com.example.selenium_java;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    public String homeUrl = "https://www.saucedemo.com/";

    public String loggedInUrl = "https://www.saucedemo.com/inventory.html";
    
    @FindBy(css = "input[id='user-name']")
    public WebElement inputUsername;

    @FindBy(css = "input[id='password']")
    public WebElement inputPassword;

    @FindBy(css = "input[id='login-button']")
    public WebElement inputLoginButton;

    @FindBy(css = "button[id='react-burger-menu-btn']")
    public WebElement buttonReactBurgerMenu;

    @FindBy(css = "a[id^='logout']")
    public WebElement linkLogoutSidebar;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this); 
    }

    
}