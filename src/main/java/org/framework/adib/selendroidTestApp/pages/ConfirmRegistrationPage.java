package org.framework.adib.selendroidTestApp.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;




public class ConfirmRegistrationPage {
    AndroidDriver<AndroidElement> driver;

    public ConfirmRegistrationPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "io.selendroid.testapp:id/label_name_data")
    public WebElement nameVerText;
    @AndroidFindBy(id = "io.selendroid.testapp:id/label_username_data")
    public WebElement userNameVerText;
    @AndroidFindBy(id = "io.selendroid.testapp:id/label_email_data")
    public WebElement emailVerText;
    @AndroidFindBy(id = "io.selendroid.testapp:id/label_preferedProgrammingLanguage_data")
    public WebElement progDataVerText;
    @AndroidFindBy(id = "io.selendroid.testapp:id/label_acceptAdds_data")
    public WebElement acceptAddsVerText;
    @AndroidFindBy(id = "io.selendroid.testapp:id/label_password_data")
    public WebElement passwordVerText;

    @AndroidFindBy(id = "io.selendroid.testapp:id/buttonRegisterUser")
    public WebElement finalRegisterBttn;

    public String getTextNameVerText() {
        return nameVerText.getText();
    }

    public String getTextPasswordVerText() {
        return passwordVerText.getText();
    }

    public String getTextUserNameVerText() {
        return userNameVerText.getText();
    }

    public String getTextEmailVerText() {
        return emailVerText.getText();
    }

    public String getTextLangVerText() {
        return progDataVerText.getText();
    }

    public String getTextAcceptAddsVerText() {
        return acceptAddsVerText.getText();
    }

    public HomePage clickFinalRegisterBttn() {
        finalRegisterBttn.click();
        return new HomePage(driver);
    }

}
