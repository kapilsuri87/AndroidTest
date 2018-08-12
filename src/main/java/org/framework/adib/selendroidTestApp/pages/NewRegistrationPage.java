package org.framework.adib.selendroidTestApp.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;



public class NewRegistrationPage {

    AndroidDriver<AndroidElement> driver;

    public NewRegistrationPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputUsername")
    public WebElement userNameText;

    @AndroidFindBy(accessibility = "email of the customer")
    public WebElement emailText;

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputPassword")
    public WebElement pwdText;

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputName")
    public WebElement nameText;

    @AndroidFindBy(id = "io.selendroid.testapp:id/input_adds")
    public WebElement addsCkBox;

    @AndroidFindBy(id = "io.selendroid.testapp:id/btnRegisterUser")
    public WebElement registerBttn;

    public void clearTypeUserName(String text) {
        userNameText.clear();
        userNameText.sendKeys(text);
    }

    public void clearTypeEmailText(String text) {
        emailText.clear();
        emailText.sendKeys(text);
    }

    public void clearTypePwdText(String text) {
        pwdText.clear();
        pwdText.sendKeys(text);
    }

    public void clearTypeNameText(String text) {
        nameText.clear();
        nameText.sendKeys(text);
    }

    public void selectCkBoxAdds() {
        addsCkBox.click();
    }

    public ConfirmRegistrationPage clickNewRegBttn() {
        registerBttn.click();
        return new ConfirmRegistrationPage(driver);
    }

    public boolean addsCkBoxValue() {
        return addsCkBox.isSelected();
    }

}
