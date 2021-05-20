package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    @FindBy(name = "username")
    private WebElement username;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(name = "firstname")
    private WebElement firstname;
    @FindBy(name = "lastname")
    private WebElement lastname;
    @FindBy(id = "submit")
    private WebElement submitButton;

    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public String getUsername(){
        return username.getText();
    }

    public String getPassword(){
        return password.getText();
    }

    public String getFirstname(){
        return firstname.getText();
    }

    public String getLastname(){
        return lastname.getText();
    }

    public void signUp(String username, String password, String firstname, String lastname){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
        submitButton.click();
    }
}
