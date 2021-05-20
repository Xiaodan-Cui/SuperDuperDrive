package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(name = "username")
    private WebElement username;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(id = "submit")
    private WebElement submitButton;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public String getUsername(){
        return username.getText();
    }

    public String getPassword(){
        return password.getText();
    }

    public void login(String username, String password){
        this.password.sendKeys(password);
        this.username.sendKeys(username);
        submitButton.click();
    }


}
