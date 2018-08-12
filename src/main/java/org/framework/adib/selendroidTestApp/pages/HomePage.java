package org.framework.adib.selendroidTestApp.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

public class HomePage {

    AndroidDriver<AndroidElement> driver;

    public HomePage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "io.selendroid.testapp:id/startUserRegistration")
    public WebElement newRegBttn;

    @AndroidFindBy(id = "android:id/title")
    public WebElement appTitle;

    @AndroidFindBy(id = "io.selendroid.testapp:id/showToastButton")
    public WebElement displayToast;

    @AndroidFindBy(id = "io.selendroid.testapp:id/showPopupWindowButton")
    public WebElement displayPopup;

    public NewRegistrationPage clickNewRegBttn() {
        newRegBttn.click();
        return new NewRegistrationPage(driver);
    }

    public void clickDisplayToast() {
        displayToast.click();
    }

    public void clickDisplayPopup() {
        displayPopup.click();
    }

    public boolean appTitleTextDisplayed() {
        return appTitle.isDisplayed();
    }
}
