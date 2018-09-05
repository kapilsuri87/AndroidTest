package org.framework.adib.selendroidTestApp.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.HashMap;

import org.framework.adib.core.utilities.XlsReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

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

    /**
     * Method to fill Username field.
     * 
     * @param text
     *            String Username
     */
    public void clearTypeUserName(String text) {
        userNameText.clear();
        userNameText.sendKeys(text);
        driver.hideKeyboard();
    }

    /**
     * Method to fill Email field.
     * 
     * @param text
     *            String Email
     */

    public void clearTypeEmailText(String text) {
        emailText.clear();
        emailText.sendKeys(text);
        driver.hideKeyboard();
    }

    /**
     * Method to fill Password field.
     * 
     * @param text
     *            String Password
     */

    public void clearTypePwdText(String text) {
        pwdText.clear();
        pwdText.sendKeys(text);
        driver.hideKeyboard();
    }

    /**
     * Method to fill Name field.
     * 
     * @param text
     *            String Name
     */

    public void clearTypeNameText(String text) {
        nameText.clear();
        nameText.sendKeys(text);
        driver.hideKeyboard();
    }

    /**
     * Select Adds checkbox.
     */

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

    /**
     * Read data record from excel file.
     * 
     * @param xlr
     *            object
     * @return map of data
     */

    public HashMap<String, String> readRegistrationData(XlsReader xlr) {
        HashMap<String, String> data = new HashMap<>();
        int rowCount = xlr.getRowCount("New_Registration");
        int rowNum = 0;
        for (int i = 2; i <= rowCount; i++) {
            if (xlr.getCellData("New_Registration", "Is_Used", i).isEmpty()) {
                data.put("name", xlr.getCellData("New_Registration", "Name", i));
                data.put("userName", xlr.getCellData("New_Registration", "UserName", i));
                data.put("email", xlr.getCellData("New_Registration", "Email", i));
                data.put("pwd", xlr.getCellData("New_Registration", "Password", i));
                data.put("lang", xlr.getCellData("New_Registration", "Language", i));
                data.put("adds", xlr.getCellData("New_Registration", "Add_Required", i));
                rowNum = i;
                break;
            }
        }
        if (rowNum == 0) {
            throw new NullPointerException("Please provide a valid record in the data file!!!");
        }
        return data;

    }

}
