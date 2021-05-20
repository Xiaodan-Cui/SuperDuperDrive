package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(id = "nav-files-tab")
    private WebElement navFiles;
    @FindBy(id = "add-new-note")
    private WebElement addNewNote;
    @FindBy(id = "note-title")
    private WebElement noteTitle;
    @FindBy(id = "note-description")
    private WebElement noteDescription;
    @FindBy(id = "save-note")
    private WebElement saveNote;
    @FindBy(id = "nav-notes-tab")
    private WebElement navNotes;
    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentials;

    private WebDriver driver;

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }


    public void getFiles(){
        navFiles.click();
    }

    public void getNotes(){
        navNotes.click();
    }

    public void getCredentials(){
        navCredentials.click();
    }

    public void addNote(String title, String description) throws InterruptedException {
        addNewNote.click();
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        saveNote.click();
    }
}
